include(`pigfunc.m4')dnl \\ image_query_activate_time.m4
function_namespace(`Image_Query_Activate_Time')dnl
pigfunc_start()dnl \\ function_namespace(`function_formal_name', `significant_parameter_formal_name')
pigfunc_import(`java.io.IOException')dnl
pigfunc_import(`com.softwoehr.pigiron.access.*')dnl \\ function_classname and function_formal_name bound in namespace
pigfunc_class(function_classname,`VSMCall',`com.softwoehr.pigiron.functions',function_formal_name,`dnl

/**
 * {@code function_formal_name} VSMAPI Function
 */')dnl \\ significant_parameter_formal_name bound in namespace
pigfunc_constant(`public', `int', `DATE_FORMAT_INDICATOR_MMDDYY', `1', `mm/dd/yy')dnl
pigfunc_constant(`public', `int', `DATE_FORMAT_INDICATOR_MMDDYYYY', `2', `mm/dd/yyyy')dnl
pigfunc_constant(`public', `int', `DATE_FORMAT_INDICATOR_YYMMDD', `3', `yy-mm-dd')dnl
pigfunc_constant(`public', `int', `DATE_FORMAT_INDICATOR_YYYYMMDD', `4', `yyyy-mm-dd')dnl
pigfunc_constant(`public', `int', `DATE_FORMAT_INDICATOR_DDMMYY', `5', `dd/mm/yy')dnl
pigfunc_constant(`public', `int', `DATE_FORMAT_INDICATOR_DDMMYYYY', `6', `dd/mm/yyyy')dnl
pigfunc_attribute(`private', `', `int', member_name(`date_format_indicator'), `0', `', `The format of the date stamp that is returned')dnl
pigfunc_ctors(`int', `date_format_indicator', member_name(`date_format_indicator'))dnl
pigfunc_compose_input_start()dnl
pigfunc_compose_input_parm(`CountedString', `getTarget_identifier()', `target_identifier')dnl
pigfunc_compose_input_parm(`VSMInt1', member_getter(`date_format_indicator')`()', `date_format_indicator')dnl
pigfunc_compose_input_end()dnl
pigfunc_compose_output_start()dnl
pigfunc_compose_output_parm(`CountedString', `""', `image_name')dnl
pigfunc_compose_output_parm(`CountedString', `""', `activation_date')dnl
pigfunc_compose_output_parm(`CountedString', `""', `activation_time')dnl
pigfunc_compose_output_end()dnl
pigfunc_function(`public', `static', `void', `', `main', `String[] argv', `IOException`,' VSMException', `dnl
     * You can execute the VSMAPI call from {@code main()}, try it
     * with no args to see the usage message.
     * @param argv array of commandline args
     * @throws IOException on comm error
     * @throws VSMException on internal Pigiron param marshalling error', `dnl

        function_classname instance = null;

        if (argv.length != 6) {
            System.out.println("usage: args are:\ninetaddr port user pw target_id date_format_indicator");
            System.exit(1);
        }

        System.out.println("Args are: " + argv[0] + " " + argv[1] + " " + argv[2] + " " + argv[3] + " " + argv[4] + " " + argv[5]);
        instance = new function_classname()(argv[0], Integer.valueOf(argv[1]).intValue(), argv[2], argv[3], argv[4], Integer.valueOf(argv[5]).intValue());

        ParameterArray pA = instance.doIt();
        System.out.println("Returns from call to " + instance.getFunctionName() + ":");
        System.out.println(pA.prettyPrintAll());')dnl
pigfunc_endclass()dnl
pigfunc_end()dnl
function_namespace_end()dnl
