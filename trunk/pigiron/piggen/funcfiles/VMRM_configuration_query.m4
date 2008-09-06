include(`pigfunc.m4')dnl \\ vmrm_configuration_query.m4
function_namespace(`VMRM_Configuration_Query')dnl
pigfunc_start()dnl
pigfunc_import(`java.io.IOException')dnl
pigfunc_import(`com.softwoehr.pigiron.access.*')dnl
pigfunc_class(function_classname,`VSMCall',`com.softwoehr.pigiron.functions',function_formal_name,`dnl

/**
 * <tt>function_formal_name</tt> VSMAPI Function
 */')dnl
dnl
pigfunc_constant(`public', `int', `SYNCHECK_AND_UPDATE', `0', `both  a syntax check and configuration update')dnl
pigfunc_constant(`public', `int', `SYNCHECK_SYNTAX_ONLY', `1', `only a syntax check of the configuration is done')dnl
pigfunc_attribute(`private', `', `String', javaize_lc(`configuration_file_name'),   `""', `', `The name of the configuration file.')dnl
pigfunc_attribute(`private', `', `String', javaize_lc(`configuration_file_type'),   `""', `', `The name of the configuration file.')dnl
pigfunc_attribute(`private', `', `String', javaize_lc(`configuration_dir_name'),   `""', `', `The fully-qualified Shared File System (SFS) directory name where the configuration file is located.')dnl
pigfunc_ctors(`String', `configuration_file_name', javaize_lc(`configuration_file_name'),
`String', `configuration_file_type', javaize_lc(`configuration_file_type'),
`String', `configuration_dir_name', javaize_lc(`configuration_dir_name'))dnl
pigfunc_compose_input_start()dnl
pigfunc_compose_input_parm(`CountedString', `getTarget_identifier()', `target_identifier')dnl
pigfunc_compose_input_parm(`CountedString', `get_'javaize_lc(`configuration_file_name')(), `configuration_file_name')dnl
pigfunc_compose_input_parm(`CountedString', `get_'javaize_lc(`configuration_file_type')(), `configuration_file_type')dnl
pigfunc_compose_input_parm(`CountedString', `get_'javaize_lc(`configuration_dir_name')(), `configuration_dir_name')dnl
pigfunc_compose_input_end()dnl
pigfunc_compose_output_start()dnl
pigfunc_compose_output_end()dnl
pigfunc_function(`public', `static', `void', `', `main', `String[] argv', `IOException`,' VSMException', `dnl
     * You can execute the VSMAPI call from <tt>main()</tt>, try it
     * with no args to see the usage message.
     * @param argv array of commandline args
     * @throws IOException on comm error
     * @throws VSMException on internal Pigiron param marshalling error', `dnl

        function_classname instance = null;

        if (argv.length != `8') {
            System.out.println("usage: args are:\ninetaddr port user pw target_namelist configuration_file_name configuration_file_type configuration_dir_name syncheck_only update_file");
            System.exit(1);
        }

        System.out.println("Args are: " + argv[0] + " " + argv[1] + " " + argv[2] + " " + argv[3] + " " + argv[4] + " " + argv[5] + " " + argv[6]  + " " + argv[7]);
        instance = new function_classname()(argv[0], Integer.valueOf(argv[1]).intValue(), argv[2], argv[3], argv[4], argv[5], argv[6], argv[7]);

        ParameterArray pA = instance.doIt();
        System.out.println("Returns from call to " + instance.getFunctionName() + ":");
        System.out.println(pA.prettyPrintAll());')dnl
pigfunc_endclass()dnl
pigfunc_end()dnl
function_namespace_end()dnl
