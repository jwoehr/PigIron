include(`pigfunc.m4')dnl \\ image_CPU_define.m4
function_namespace(`Image_CPU_Define')dnl
pigfunc_start()dnl \\ function_namespace(`function_formal_name', `significant_parameter_formal_name')
pigfunc_import(`java.io.IOException')dnl
pigfunc_import(`com.softwoehr.pigiron.access.*')dnl \\ function_classname and function_formal_name bound in namespace
pigfunc_class(function_classname,`VSMCall',`com.softwoehr.pigiron.functions',function_formal_name,`dnl

/**
 * {@code function_formal_name} VSMAPI 5.4 Function
 * @see com.softwoehr.pigiron.access.paramstructs.significant_parameter_classname
 * @since `<a href="http://publib.boulder.ibm.com/infocenter/zvm/v5r4/`index'.jsp">VSMAPI 5.4</a>'
 */')dnl \\ significant_parameter_formal_name bound in namespace
pigfunc_constant(`public', `int', `CPU_TYPE_UNSPECIFIED', `0', `Unspecified')dnl
pigfunc_constant(`public', `int', `CPU_TYPE_CP', `1', `CP')dnl
pigfunc_constant(`public', `int', `CPU_TYPE_IFL', `2', `IFL')dnl
pigfunc_constant(`public', `int', `CPU_TYPE_ZAAP', `3', `ZAAP')dnl
pigfunc_constant(`public', `int', `CPU_TYPE_ZIIP', `4', ZIIP`')dnl
pigfunc_attribute(`private', `', `String',  member_name(`cpu_address'), `""', `', `The virtual CPU address to add to the virtual image in the hexadecimal range of 0-3F.')dnl
pigfunc_attribute(`private', `', `int', member_name(`cpu_type'), `0', `', `The type of processor to add.')dnl
pigfunc_ctors(`String', `cpu_address', member_name(`cpu_address'),
`int', `cpu_type', member_name(`cpu_type'))dnl
pigfunc_compose_input_start()dnl
pigfunc_compose_input_parm(`CountedString', `getTarget_identifier()', `target_identifier')dnl
pigfunc_compose_input_parm(`CountedString', member_getter(`cpu_address')`()', `cpu_address')dnl
pigfunc_compose_input_parm(`VSMInt1', member_getter(`cpu_type')`()', `cpu_type')dnl
pigfunc_compose_input_end()dnl
pigfunc_compose_output_start()dnl
pigfunc_compose_output_end()dnl
pigfunc_function(`public', `static', `void', `', `main', `String[] argv', `IOException`,' VSMException', `dnl
     * You can execute the VSMAPI call from {@code main()}, try it
     * with no args to see the usage message.
     * @param argv array of commandline args
     * @throws IOException on comm error
     * @throws VSMException on internal Pigiron param marshalling error', `dnl

        function_classname instance = null;

        if (argv.length != 7) {
            System.out.println("usage: args are:\ninetaddr port user pw target_id cpu_address cpu_type");
            System.exit(1);
        }

        System.out.println("Args are: " + argv[0] + " " + argv[1] + " " + argv[2] + " " + argv[3] + " " + argv[4] + " " + argv[5] + " " + argv[6]);
        instance = new function_classname()(argv[0], Integer.valueOf(argv[1]).intValue(), argv[2], argv[3], argv[4], argv[5], Integer.valueOf(argv[6]).intValue());

        ParameterArray pA = instance.doIt();
        System.out.println("Returns from call to " + instance.getFunctionName() + ":");
        System.out.println(pA.prettyPrintAll());')dnl
pigfunc_endclass()dnl
pigfunc_end()dnl
function_namespace_end()dnl
