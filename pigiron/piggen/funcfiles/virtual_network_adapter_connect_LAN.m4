include(`pigfunc.m4')dnl \\ virtual_network_adapter_connect_lan.m4
function_namespace(`Virtual_Network_Adapter_Connect_LAN')dnl
pigfunc_start()dnl
pigfunc_import(`java.io.IOException')dnl
pigfunc_import(`com.softwoehr.pigiron.access.*')dnl
pigfunc_class(function_classname,`VSMCall',`com.softwoehr.pigiron.functions',function_formal_name,`dnl

/**
 * <tt>function_formal_name</tt> VSMAPI Function
 */')dnl
pigfunc_attribute(`private', `', `String', member_name(`lan_name'), `""', `', `The name of the guest LAN segment to connect the virtual image')dnl
pigfunc_attribute(`private', `', `String', member_name(`lan_owner'), `""', `', `The virtual image owning the guest LAN segment to be connected')dnl
pigfunc_ctors(`String', `lan_name', member_name(`lan_name'),
`String', `lan_owner', member_name(`lan_owner'))dnl
pigfunc_compose_input_start()dnl
pigfunc_compose_input_parm(`CountedString', `getTarget_identifier()', `target_identifier')dnl
pigfunc_compose_input_parm(`CountedString', member_getter(`lan_name')`()', `lan_name')dnl
pigfunc_compose_input_parm(`CountedString', member_getter(`lan_owner')`()', `lan_owner')dnl
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

        if (argv.length != 7) {
            System.out.println("usage: args are:\ninetaddr port user pw target lan_name lan_owner");
            System.exit(1);
        }

        System.out.println("Args are: " + argv[0] + " " + argv[1] + " " + argv[2] + " " + argv[3] + " " + argv[4] + " " + argv[5] + " " + argv[6]);
        instance = new function_classname()(argv[0], Integer.valueOf(argv[1]).intValue(), argv[2], argv[3], argv[4],  argv[5], argv[6]);

        ParameterArray pA = instance.doIt();
        System.out.println("Returns from call to " + instance.getFunctionName() + ":");
        System.out.println(pA.prettyPrintAll());')dnl
pigfunc_endclass()dnl
pigfunc_end()dnl
function_namespace_end()dnl
