include(`pigfunc.m4')dnl \\ directory_manager_local_tag_define_DM.m4
function_namespace(`Directory_Manager_Local_Tag_Define_DM')dnl
pigfunc_start()dnl \\ function_namespace(`function_formal_name', `significant_parameter_formal_name')
pigfunc_import(`java.io.IOException')dnl
pigfunc_import(`com.softwoehr.pigiron.access.*')dnl \\ function_classname function_formal_name etc. bound in namespace
pigfunc_class(function_classname,`VSMCall',`com.softwoehr.pigiron.functions',function_formal_name,`dnl

/**
 * {@code function_formal_name} VSMAPI Function
 */')dnl \\ significant_parameter_formal_name bound in namespace
pigfunc_attribute(`private', `', `String', member_name(`tag_name'), `""', `', `The name of the local tag or named comment to be defined')dnl
pigfunc_attribute(`private', `', `int',  member_name(`tag_ordinal'), `-1', `', `The value of the tag sort ordinal relative to other defined local tags')dnl
pigfunc_ctors(`String', `tag_name', member_name(`tag_name'),
`int', `tag_ordinal', member_name(`tag_ordinal'))dnl
pigfunc_compose_input_start()dnl
pigfunc_compose_input_parm(`CountedString', `getTarget_identifier()', `target_identifier')dnl
pigfunc_compose_input_parm(CountedString, member_getter(`tag_name')`()', `tag_name')dnl
pigfunc_compose_input_parm(`VSMInt4', member_getter(`tag_ordinal')`()',`tag_ordinal')dnl
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
            System.out.println("usage: args are:\ninetaddr port user pw target_name tag_name tag_ordinal");
            System.exit(1);
        }

        System.out.println("Args are: " + argv[0] + " " + argv[1] + " " + argv[2] + " " + argv[3] + " " + argv[4] + " " + argv[5] + " " + argv[6]);
        instance = new function_classname()(argv[0], Integer.valueOf(argv[1]).intValue(), argv[2], argv[3], argv[4], argv[5],  Integer.valueOf(argv[6]).intValue());

        ParameterArray pA = instance.doIt();
        System.out.println("Returns from call to " + instance.getFunctionName() + ":");
        System.out.println(pA.prettyPrintAll());')dnl
pigfunc_endclass()dnl
pigfunc_end()dnl
function_namespace_end()dnl
