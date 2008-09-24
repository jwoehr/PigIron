include(`pigfunc.m4')dnl \\ image_CPU_set_maximum_DM.m4
function_namespace(`Image_CPU_Set_Maximum_DM')dnl
pigfunc_start()dnl \\ function_namespace(`function_formal_name', `significant_parameter_formal_name')
pigfunc_import(`java.io.IOException')dnl
pigfunc_import(`com.softwoehr.pigiron.access.*')dnl \\ function_classname and function_formal_name bound in namespace
pigfunc_class(function_classname,`VSMCall',`com.softwoehr.pigiron.functions',function_formal_name,`dnl

/**
 * {@code function_formal_name} VSMAPI 5.4 Function
 * @since `<a href="http://publib.boulder.ibm.com/infocenter/zvm/v5r4/`index'.jsp">VSMAPI 5.4</a>'
 */')dnl \\ significant_parameter_formal_name bound in namespace
pigfunc_attribute(`private', `', `int', member_name(`max_cpu'), `0', `', `The maximum number of virtual processors the user can `define' - between 1 and 64.')dnl
pigfunc_ctors(`int', `max_cpu', member_name(`max_cpu'))dnl
pigfunc_compose_input_start()dnl
pigfunc_compose_input_parm(`CountedString', `getTarget_identifier()', `target_identifier')dnl
pigfunc_compose_input_parm(`VSMInt4', member_getter(`max_cpu')`()', `max_cpu')dnl
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

        if (argv.length != 6) {
            System.out.println("usage: args are:\ninetaddr port user pw target_id name max_cpu");
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
