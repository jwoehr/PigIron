include(`pigfunc.m4')dnl \\ image_volume_space_query_DM.m4
function_namespace(`Image_Volume_Space_Query_DM')dnl
pigfunc_start()dnl \\ function_namespace(`function_formal_name', `significant_parameter_formal_name')
pigfunc_import(`java.io.IOException')dnl
pigfunc_import(`com.softwoehr.pigiron.access.*')dnl \\ function_classname and function_formal_name bound in namespace
pigfunc_class(function_classname,`VSMCall',`com.softwoehr.pigiron.functions',function_formal_name,`dnl

/**
 * {@code function_formal_name} VSMAPI Function
 */')dnl \\ significant_parameter_formal_name bound in namespace 
pigfunc_constant(`public', `int', `QUERY_TYPE_DEFINITION', `1', `Query volume definition for the specified image device')dnl
pigfunc_constant(`public', `int', `QUERY_TYPE_FREE', `2', `Query amount of free space available on the specified image device')dnl
pigfunc_constant(`public', `int', `QUERY_TYPE_USED', `3', `Query amount of space used on the specified image device')dnl
pigfunc_constant(`public', `int', `ENTRY_TYPE_VOLUME', `1', `Query specified volume')dnl
pigfunc_constant(`public', `int', `ENTRY_TYPE_REGION', `2', `Query specified region')dnl
pigfunc_constant(`public', `int', `ENTRY_TYPE_GROUP', `3', `Query specified group')dnl
pigfunc_attribute(`private', `', `int', member_name(`query_type'), `0', `', `query_type')dnl
pigfunc_attribute(`private', `', `int', member_name(`entry_type'), `0', `', `entry_type')dnl
pigfunc_attribute(`private', `', `String',  member_name(`entry_names'), `""', `', `Names of groups, regions or volumes to be queried')dnl
pigfunc_ctors(`int', `query_type', member_name(`query_type'),
`int', `entry_type', member_name(`entry_type'),
`String', `entry_names', member_name(`entry_names'))dnl
pigfunc_compose_input_start()dnl
pigfunc_compose_input_parm(`CountedString', `getTarget_identifier()', `target_identifier')dnl
pigfunc_compose_input_parm(`VSMInt1', member_getter(`query_type')`()', `query_type')dnl
pigfunc_compose_input_parm(`VSMInt1', member_getter(`entry_type')`()', `entry_type')dnl
pigfunc_compose_input_parm(`CountedString', member_getter(`entry_names')`()', `entry_names')dnl
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
            System.out.println("usage: args are:\ninetaddr port user pw target_id function_type region_name image_vol_id start_cylinder size group_name device_type");
            System.exit(1);
        }

        System.out.println("Args are: " + argv[0] + " " + argv[1] + " " + argv[2] + " " + argv[3] + " " + argv[4] + " " + argv[5] + " " + argv[6] + " " + argv[7]);
        instance = new function_classname()(argv[0], Integer.valueOf(argv[1]).intValue(), argv[2], argv[3], argv[4],  Integer.valueOf(argv[5]).intValue(), Integer.valueOf(argv[6]).intValue(), argv[7]);

        ParameterArray pA = instance.doIt();
        System.out.println("Returns from call to " + instance.getFunctionName() + ":");
        System.out.println(pA.prettyPrintAll());')dnl
pigfunc_endclass()dnl
pigfunc_end()dnl
function_namespace_end()dnl
