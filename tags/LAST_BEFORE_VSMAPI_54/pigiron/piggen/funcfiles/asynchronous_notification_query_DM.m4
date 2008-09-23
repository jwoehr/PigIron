include(`pigfunc.m4')dnl \\asynchronous_notification_query_DM.m4
function_namespace(`Asynchronous_Notification_Query_DM',`notification_array')dnl
pigfunc_start()dnl \\ function_namespace(`function_formal_name', `significant_parameter_formal_name')
pigfunc_import(`java.io.IOException')dnl
pigfunc_import(`com.softwoehr.pigiron.access.*')dnl
pigfunc_import(`com.softwoehr.pigiron.access.paramstructs.significant_parameter_classname')dnl  \\ function_classname and function_formal_name bound in namespace
pigfunc_class(function_classname,`VSMCall',`com.softwoehr.pigiron.functions',function_formal_name,`dnl

/**
 * {@code function_formal_name} VSMAPI Function
 * @see com.softwoehr.pigiron.access.paramstructs.significant_parameter_classname
 */')dnl \\ significant_parameter_formal_name bound in namespace
pigfunc_constant(`public', `int', `ENTITY_TYPE_DIRECTORY', `1', `DIRECTORY')dnl
pigfunc_constant(`public', `int', `COMMUNICATION_TYPE_Unspecified', `0', `Unspecified')dnl
pigfunc_constant(`public', `int', `COMMUNICATION_TYPE_TCP', `1', `TCP')dnl
pigfunc_constant(`public', `int', `COMMUNICATION_TYPE_UDP', `2', `UDP')dnl
pigfunc_constant(`public', `int', `ENCODING_UNSPECIFIED', `0', `Unspecified')dnl
pigfunc_constant(`public', `int', `ENCODING_ASCII', `1', `ASCII')dnl
pigfunc_constant(`public', `int', `ENCODING_EBCDIC', `2', `EBCDIC')dnl
pigfunc_attribute(`private', `', `int', member_name(`entity_type'), `0', `', `The entity type for which notifications will be sent')dnl
pigfunc_attribute(`private', `', `int', member_name(`communication_type'), `0', `', `The communication type of the notification strings being queried')dnl
pigfunc_attribute(`private', `', `int', member_name(`port_number'), `0', `', `The port number of the socket that will receive the notifications')dnl
pigfunc_attribute(`private', `', `String',  member_name(`ip_address'), `""', `', `The IPV4 IP address of the socket that will receive the notifications. A null selects all that qualify.')dnl
pigfunc_attribute(`private', `', `int', member_name(`encoding'), `0', `', `The encoding of the notification strings being queried')dnl
pigfunc_attribute(`private', `', `String',  member_name(`subscriber_data'), `"*"', `', `Anything the subscriber wishes to receive along with the notification. "*" selects all that qualify.')dnl
pigfunc_ctors(`int', `entity_type', member_name(`entity_type'),
`int', `communication_type', member_name(`communication_type'),
`int', `port_number', member_name(`port_number'),
`String', `ip_address', member_name(`ip_address'),
`int', `encoding', member_name(`encoding'),
`String', `subscriber_data', member_name(`subscriber_data'))dnl
pigfunc_compose_input_start()dnl
pigfunc_compose_input_parm(`CountedString', `getTarget_identifier()', `target_identifier')dnl
pigfunc_compose_input_parm(`VSMInt1', member_getter(`entity_type')`()', `entity_type')dnl
pigfunc_compose_input_parm(`VSMInt1', member_getter(`communication_type')`()', `communication_type')dnl
pigfunc_compose_input_parm(`VSMInt4', member_getter(`port_number')`()', `port_number')dnl
pigfunc_compose_input_parm(`CountedString',  member_getter(`ip_address')`()', `ip_address')dnl
pigfunc_compose_input_parm(`VSMInt1', member_getter(`encoding')`()', `encoding')dnl
pigfunc_compose_input_parm(`CountedString', member_getter(`subscriber_data')`()', `subscriber_data')dnl
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

        function_classname instance = null;

        if (argv.length != 11) {
            System.out.println("usage: args are:\ninetaddr port user pw target_id entity_type communication_type port_number ip_address encoding subscriber_data");
            System.exit(1);
        }

        System.out.println("Args are: " + argv[0] + " " + argv[1] + " " + argv[2] + " " + argv[3] + " " + argv[4] + " " + argv[5] + " " + argv[6] + " " + argv[7] + " " + argv[8] + " " + argv[9] + " " + argv[10]);

        instance = new function_classname()(argv[0], Integer.valueOf(argv[1]).intValue(), argv[2], argv[3], argv[4], Integer.valueOf(argv[5]).intValue(), Integer.valueOf(argv[6]).intValue(), Integer.valueOf(argv[7]).intValue(), argv[8], Integer.valueOf(argv[9]).intValue(), argv[10]);

        ParameterArray pA = instance.doIt();
        System.out.println("Returns from call to " + instance.getFunctionName() + ":");
        System.out.println(pA.prettyPrintAll());')dnl
pigfunc_endclass()dnl
pigfunc_end()dnl
function_namespace_end()dnl
