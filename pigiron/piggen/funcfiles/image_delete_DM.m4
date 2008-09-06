include(`pigfunc.m4')dnl \\ image_delete_DM.m4
function_namespace(`Image_Delete_DM')dnl
pigfunc_start()dnl \\ function_namespace(`function_formal_name', `significant_parameter_formal_name')
pigfunc_import(`java.io.IOException')dnl
pigfunc_import(`com.softwoehr.pigiron.access.*')dnl \\ function_classname and function_formal_name bound in namespace
pigfunc_class(function_classname,`VSMCall',`com.softwoehr.pigiron.functions',function_formal_name,`dnl

/**
 * {@code function_formal_name} VSMAPI Function
 */')dnl \\ significant_parameter_formal_name bound in namespace
pigfunc_constant(`public', `int', `DATA_SECURITY_ERASE_UNSPECIFIED', `0', `Unspecified')dnl
pigfunc_constant(`public', `int', `DATA_SECURITY_ERASE_DO_NOT', `1', `Do not erase')dnl
pigfunc_constant(`public', `int', `DATA_SECURITY_ERASE', `2', `Erase')dnl
pigfunc_attribute(`private', `', `int', member_name(`data_security_erase'), `0', `', `Indicates whether to erase data from the disk(s) being released')dnl
pigfunc_ctors(`int', `data_security_erase', member_name(`data_security_erase'))dnl
pigfunc_compose_input_start()dnl
pigfunc_compose_input_parm(`VSMInt1', member_getter(`data_security_erase')`()', `data_security_erase')dnl
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
            System.out.println("usage: args are:\ninetaddr port user pw target_id name data_security_erase");
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
