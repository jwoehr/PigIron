include(`pigfunc.m4')dnl \\ image_ipl_set_DM.m4
function_namespace(`Image_IPL_Set_DM' )dnl
pigfunc_start()dnl \\ function_namespace(`function_formal_name', `significant_parameter_formal_name')
pigfunc_import(`java.io.IOException')dnl
pigfunc_import(`com.softwoehr.pigiron.access.*')dnl \\ function_classname and function_formal_name bound in namespace
pigfunc_class(function_classname,`VSMCall',`com.softwoehr.pigiron.functions',function_formal_name,`dnl

/**
 * {@code function_formal_name} VSMAPI Function
 * @see com.softwoehr.pigiron.access.paramstructs.significant_parameter_classname
 */')dnl \\ significant_parameter_formal_name bound in namespace
pigfunc_attribute(`private', `', `String',  member_name(`saved_system'), `""', `', `The name of the saved system or virtual device address of the device containing the system to be loaded')dnl
pigfunc_attribute(`private', `', `String',  member_name(`load_parameter'), `""', `', `Load parameter (up to 8 characters) that is used by the IPLd system')dnl
pigfunc_attribute(`private', `', `String',  member_name(`parameter_string'), `""', `', `The parameters to be passed to the IPLd operating system')dnl
pigfunc_ctors(`String', `saved_system', member_name(`saved_system'),
`String', `load_parameter', member_name(`load_parameter'),
`String', `parameter_string', member_name(`parameter_string'))dnl
pigfunc_compose_input_start()dnl
pigfunc_compose_input_parm(`CountedString', `getTarget_identifier()', `target_identifier')dnl
pigfunc_compose_input_parm(`CountedString', member_getter(`saved_system')`()', `saved_system')dnl
pigfunc_compose_input_parm(`CountedString', member_getter(`load_parameter')`()', `load_parameter')dnl
pigfunc_compose_input_parm(`CountedString', member_getter(`parameter_string')`()', `parameter_string')dnl
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

        if (argv.length != 8) {
            System.out.println("usage: args are:\ninetaddr port user pw target_id saved_system load_parameter parameter_string");
            System.exit(1);
        }

        System.out.println("Args are: " + argv[0] + " " + argv[1] + " " + argv[2] + " " + argv[3] + " " + argv[4] + " " + argv[5] + " " + argv[6] + " " + argv[7]);
        instance = new function_classname()(argv[0], Integer.valueOf(argv[1]).intValue(), argv[2], argv[3], argv[4], argv[5], argv[6], argv[7]);

        ParameterArray pA = instance.doIt();
        System.out.println("Returns from call to " + instance.getFunctionName() + ":");
        System.out.println(pA.prettyPrintAll());')dnl
pigfunc_endclass()dnl
pigfunc_end()dnl
function_namespace_end()dnl
