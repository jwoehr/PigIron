include(`pigfunc.m4')dnl \\ vmrm_configuration_update.m4
function_namespace(`VMRM_Configuration_Update')dnl
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
pigfunc_attribute(`private', `', `String', member_name(`configuration_file_name'),   `""', `', `The name of the configuration file.')dnl
pigfunc_attribute(`private', `', `String', member_name(`configuration_file_type'),   `""', `', `The name of the configuration file.')dnl
pigfunc_attribute(`private', `', `String', member_name(`configuration_dir_name'),   `""', `', `The fully-qualified Shared File System (SFS) directory name where the configuration file is located.')dnl
pigfunc_attribute(`private', `', `int', member_name(`syncheck_only'), `0', `', `Specify a 1 to choose the SYNCHECK option meaning that only a syntax check is done and a 0 to indicate both a syntax check and a configuration file update')dnl
pigfunc_attribute(`private', `', `String', member_name(`update_file'), `""', `', `A new complete VMRM configuration file to syntax-check or to replace the old file.')dnl
pigfunc_ctors(`String', `configuration_file_name', member_name(`configuration_file_name'),
`String', `configuration_file_type', member_name(`configuration_file_type'),
`String', `configuration_dir_name', member_name(`configuration_dir_name'),
`int', `syncheck_only', member_name(`syncheck_only'),
`String', `update_file', member_name(`update_file'))dnl
pigfunc_compose_input_start()dnl
pigfunc_compose_input_parm(`CountedString', `getTarget_identifier()', `target_identifier')dnl
pigfunc_compose_input_parm(`CountedString', member_getter(`configuration_file_name')`()', `configuration_file_name')dnl
pigfunc_compose_input_parm(`CountedString', member_getter(`configuration_file_type')`()', `configuration_file_type')dnl
pigfunc_compose_input_parm(`CountedString', member_getter(`configuration_dir_name')`()', `configuration_dir_name')dnl
pigfunc_compose_input_parm(`VSMInt1', member_getter(`syncheck_only')(), `syncheck_only')dnl
pigfunc_compose_input_parm(`CountedString', member_getter(`update_file')(), `update_file')dnl
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

        if (argv.length != `10') {
            System.out.println("usage: args are:\ninetaddr port user pw target_namelist configuration_file_name configuration_file_type configuration_dir_name syncheck_only update_file");
            System.exit(1);
        }

        System.out.println("Args are: " + argv[0] + " " + argv[1] + " " + argv[2] + " " + argv[3] + " " + argv[4] + " " + argv[5] + " " + argv[6]  + " " + argv[7]  + " " + argv[8]  + " " + argv[9]);
        instance = new function_classname()(argv[0], Integer.valueOf(argv[1]).intValue(), argv[2], argv[3], argv[4], argv[5], argv[6], argv[7],  Integer.valueOf(argv[8]).intValue(), argv[9]);

        ParameterArray pA = instance.doIt();
        System.out.println("Returns from call to " + instance.getFunctionName() + ":");
        System.out.println(pA.prettyPrintAll());')dnl
pigfunc_endclass()dnl
pigfunc_end()dnl
function_namespace_end()dnl
