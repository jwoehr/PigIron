include(`pigfunc.m4')dnl \\ query_all_DM.m4
function_namespace(`Query_All_DM', `directory_entry_array')dnl
pigfunc_start()dnl \\ function_namespace(`function_formal_name', `significant_parameter_formal_name')
pigfunc_import(`java.io.IOException')dnl
pigfunc_import(`com.softwoehr.pigiron.access.*')dnl \\ function_classname and function_formal_name bound in namespace
pigfunc_import(`com.softwoehr.pigiron.access.paramstructs.'significant_parameter_classname)dnl
pigfunc_class(function_classname,`VSMCall',`com.softwoehr.pigiron.functions',function_formal_name,`dnl

/**
 * {@code function_formal_name} VSMAPI 6.3 Function
 * @see com.softwoehr.pigiron.access.paramstructs.significant_parameter_classname
 * @since `<a href="http://publibz.boulder.ibm.com/epubs/pdf/hcse2c10.pdf">VSMAPI 6.3</a>'
 */')dnl \\ significant_parameter_formal_name bound in namespace
pigfunc_attribute(`private', `', `String', member_name(`query_keyword_parameter_list'), `null', `', `AsciiZ key-value pair FORMAT=YES -or- NO.')dnl
pigfunc_ctors(`String', `query_keyword_parameter_list', member_name(`query_keyword_parameter_list'))dnl
pigfunc_compose_input_start()dnl
pigfunc_compose_input_parm(`CountedString', `getTarget_identifier()', `target_identifier')dnl
pigfunc_compose_input_parm(`CountedStringZ',member_getter(`query_keyword_parameter_list')`()',`query_keyword_parameter_list')dnl
pigfunc_compose_input_end()dnl
pigfunc_compose_output_start()dnl
pigfunc_compose_output_parm(significant_parameter_classname, `null', significant_parameter_formal_name)dnl
pigfunc_compose_output_end()dnl
pigfunc_function(`public', `static', `void', `', `main', `String[] argv', `IOException`,' VSMException', `dnl
     * You can execute the VSMAPI call from {@code main()}, try it
     * with no args to see the usage message.
     * @param argv array of commandline args
     * @throws IOException on comm error
     * @throws VSMException on internal Pigiron param marshalling error', `dnl

        function_classname instance;

        if (argv.length != 8) {
            System.out.println("usage: args are:\ninetaddr port user pw target_id name buried_int");
            System.exit(1);
        }

        System.out.println("Args are: " + argv[0] + " " + argv[1] + " " + argv[2] + " " + argv[3] + " " + argv[4] + " " + argv[5]);
        instance = new function_classname()(argv[0], Integer.valueOf(argv[1]), argv[2], argv[3], argv[4], argv[5]);

        ParameterArray pA = instance.doIt();
        System.out.println("Returns from call to " + instance.getFunctionName() + ":");
        System.out.println(pA.prettyPrintAll());')dnl
pigfunc_endclass()dnl
pigfunc_end()dnl
function_namespace_end()dnl
