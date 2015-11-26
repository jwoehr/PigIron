include(`pigfunc.m4')dnl \\ event_subscribe.m4
function_namespace(`Event_Subscribe')dnl
pigfunc_start()dnl
pigfunc_import(`java.io.IOException')dnl
pigfunc_import(`com.softwoehr.pigiron.access.*')dnl \\ function_classname and function_formal_name bound in namespace
pigfunc_class(function_classname,`VSMCall',`com.softwoehr.pigiron.functions',function_formal_name,`dnl

/**
 * {@code function_formal_name} VSMAPI 6.3 Function
 * @since `<a href="http://publibz.boulder.ibm.com/epubs/pdf/hcse2c10.pdf">VSMAPI 6.3</a>'
 */')dnl
pigfunc_attribute(`private', `', `String', member_name(`match_key'), `""', `', `Binary match key either exact or fuzzy determines which events are seen.')dnl
pigfunc_ctors(`String', `match_key', member_name(`match_key'))dnl
pigfunc_compose_input_start()dnl
pigfunc_compose_input_parm(`CountedString', `getTarget_identifier()', `target_identifier')dnl
pigfunc_compose_input_parm(`CountedString', member_getter(`match_key')`()', `match_key')dnl
pigfunc_compose_input_end()dnl
pigfunc_compose_output_start()dnl
pigfunc_compose_output_parm(`VSMInt4', `-1', `operation_id')dnl
pigfunc_compose_output_end()dnl
pigfunc_function(`public', `static', `void', `', `main', `String[] argv', `IOException`,' VSMException', `dnl
     * You can execute the VSMAPI call from {@code main()}, try it
     * with no args to see the usage message.
     * @param argv array of commandline args
     * @throws IOException on comm error
     * @throws VSMException on internal Pigiron param marshalling error', `dnl

        function_classname instance;

        if (argv.length != 6) {
            System.out.println("usage: args are:\ninetaddr port user pw target_id match_key");
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
