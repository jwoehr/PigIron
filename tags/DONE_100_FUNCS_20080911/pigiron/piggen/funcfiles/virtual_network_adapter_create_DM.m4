include(`pigfunc.m4')dnl \\ virtual_network_adapter_create_DM.m4
function_namespace(`Virtual_Network_Adapter_Create_DM')dnl
pigfunc_start()dnl
pigfunc_import(`java.io.IOException')dnl
pigfunc_import(`com.softwoehr.pigiron.access.*')dnl
pigfunc_class(function_classname,`VSMCall',`com.softwoehr.pigiron.functions',function_formal_name,`dnl

/**
 * {@code function_formal_name } VSMAPI Function
 * To non-specify the  {@code channel_path_id} just leave it at its default value of {@code ""}.
 * To non-specify the  {@code mac_id} just leave it at its default value of {@code ""}.
 */')dnl
pigfunc_constant(`public', `int', `ADAPTER_TYPE_HIPERSOCKETS', `1', `Simulated HiperSockets NIC')dnl
pigfunc_constant(`public', `int', `ADAPTER_TYPE_QDIO', `2', `Simulated QDIO NIC')dnl
pigfunc_attribute(`private', `', `String', javaize_lc(`image_device_number'), `""', `', `The virtual device address of the new adapter')dnl
pigfunc_attribute(`private', `', `int', javaize_lc(`adapter_type'), `-1', `', `The adapter type')dnl
pigfunc_attribute(`private', `', `int', javaize_lc(`network_adapter_devices'), `-1', `', `The number of virtual devices associated with this adapter')dnl
pigfunc_attribute(`private', `', `String', javaize_lc(`channel_path_id'), `""', `', `For use only when configuring a second-level z/OS system where it is used to specify the hex CHPID numbers for the first- and second-level systems')dnl
pigfunc_attribute(`private', `', `String', javaize_lc(`mac_id'), `""', `', `The MAC identifier')dnl
pigfunc_ctors(`String', `image_device_number', javaize_lc(`image_device_number'),
`int', `adapter_type', javaize_lc(`adapter_type'),
`int', `network_adapter_devices', javaize_lc(`network_adapter_devices'),
`String', `channel_path_id', javaize_lc(`channel_path_id'),
`String', `mac_id', javaize_lc(`mac_id'))dnl
pigfunc_compose_input_start()dnl
pigfunc_compose_input_parm(`CountedString', `getTarget_identifier()', `target_identifier')dnl
pigfunc_compose_input_parm(`CountedString', `get_imageDeviceNumber()', `image_device_number')dnl
pigfunc_compose_input_parm(`VSMInt1', `get_'javaize_lc(`adapter_type')`()', `adapter_type')dnl
pigfunc_compose_input_parm(`VSMInt4', `get_'javaize_lc(`network_adapter_devices')`()', `network_adapter_devices')dnl
pigfunc_compose_input_parm(`CountedString', `get_'javaize_lc(`channel_path_id')`()', `channel_path_id')dnl
pigfunc_compose_input_parm(`CountedString', `get_'javaize_lc(`mac_id')`()', `mac_id')dnl
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

        if (argv.length < 8 | argv.length > 10) {
            System.out.println("usage: args are:\ninetaddr port user pw target image_device_number adapter_type network_adapter_devices [channel_path_id] [mac_id]");
            System.exit(1);
        }

        if (argv.length == 8) { // channel_path_id not specified
            System.out.println("Args are: " + argv[0] + " " + argv[1] + " " + argv[2] + " " + argv[3] + " " + argv[4] + " " + argv[5] + " " + argv[6] + " " + argv[7]);
            instance = new function_classname()(argv[0], Integer.valueOf(argv[1]).intValue(), argv[2], argv[3], argv[4], argv[5], Integer.valueOf(argv[6]).intValue(), Integer.valueOf(argv[7]).intValue(), "", "");
        } else if (argv.length == 9) { // argv.length == 9 // channel_path_id yea specified
            System.out.println("Args are: " + argv[0] + " " + argv[1] + " " + argv[2] + " " + argv[3] + " " + argv[4] + " " + argv[5] + " " + argv[6] + " " + argv[7] + " " + argv[8]);
            instance = new function_classname()(argv[0], Integer.valueOf(argv[1]).intValue(), argv[2], argv[3], argv[4], argv[5], Integer.valueOf(argv[6]).intValue(), Integer.valueOf(argv[7]).intValue(), argv[8], "");
        } else { // argv.length == 9 // channel_path_id yea specified mac_id yea specified
            System.out.println("Args are: " + argv[0] + " " + argv[1] + " " + argv[2] + " " + argv[3] + " " + argv[4] + " " + argv[5] + " " + argv[6] + " " + argv[7] + " " + argv[8] + " " + argv[9]);
            instance = new function_classname()(argv[0], Integer.valueOf(argv[1]).intValue(), argv[2], argv[3], argv[4], argv[5], Integer.valueOf(argv[6]).intValue(), Integer.valueOf(argv[7]).intValue(), argv[8], argv[9]);
        }

        ParameterArray pA = instance.doIt();
        System.out.println("Returns from call to " + instance.getFunctionName() + ":");
        System.out.println(pA.prettyPrintAll());')dnl
pigfunc_endclass()dnl
pigfunc_end()dnl
function_namespace_end()dnl
