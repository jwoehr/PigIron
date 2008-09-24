include(`pigfunc.m4')dnl \\ virtual_network_vswitch_create.m4
function_namespace(`Virtual_Network_Vswitch_Create',`vswitch_array')dnl
pigfunc_start()dnl
pigfunc_import(`java.io.IOException')dnl
pigfunc_import(`com.softwoehr.pigiron.access.*')dnl
pigfunc_import(`com.softwoehr.pigiron.access.paramstructs.significant_parameter_classname')dnl
pigfunc_class(function_classname,`VSMCall',`com.softwoehr.pigiron.functions',function_formal_name,`dnl

/**
 * {@code function_formal_name} VSMAPI Function
 * @see com.softwoehr.pigiron.access.paramstructs.significant_parameter_classname
 */')dnl
pigfunc_constant(`public', `int', `CONNECTION_VALUE_UNSPECIFIED', `0', `Unspecified')dnl
pigfunc_constant(`public', `int', `CONNECTION_VALUE_ACTIVATE', `1', `Activate connection')dnl
pigfunc_constant(`public', `int', `CONNECTION_VALUE_DO_NOT_ACTIVATE', `2', `Don't activate connection')dnl
pigfunc_constant(`public', `int', `ROUTING_VALUE_UNSPECIFIED', `0', `Unspecified')dnl
pigfunc_constant(`public', `int', `ROUTING_VALUE_NONROUTER', `1', `Device identified in real_device_address will not act as a router to the virtual switch')dnl
pigfunc_constant(`public', `int', `ROUTING_VALUE_PRIROUTER', `2', `device identified in real_device_address will act as a primary router to the virtual switch')dnl
pigfunc_constant(`public', `int', `TRANSPORT_TYPE_UNSPECIFIED', `0', `Unspecified')dnl
pigfunc_constant(`public', `int', `TRANSPORT_TYPE_IP', `1', `IP')dnl
pigfunc_constant(`public', `int', `TRANSPORT_TYPE_ETHERNET', `2', `Ethernet')dnl
pigfunc_constant(`public', `int', `PORT_TYPE_UNSPECIFIED', `0', `Unspecified')dnl
pigfunc_constant(`public', `int', `PORT_TYPE_ACCESS', `1', `Access')dnl
pigfunc_constant(`public', `int', `PORT_TYPE_TRUNK', `2', `Trunk')dnl
pigfunc_constant(`public', `int', `UPDATE_SYSTEM_CONFIG_INDICATOR_UNSPECIFIED', `0', `Unspecified')dnl
pigfunc_constant(`public', `int', `UPDATE_SYSTEM_CONFIG_INDICATOR_CREATE', `1', `Create virtual switch on the active system')dnl
pigfunc_constant(`public', `int', `UPDATE_SYSTEM_CONFIG_INDICATOR_CREATE_ADD', `2', `Create virtual switch on the active system and add virtual switch definition to system configuration file')dnl
pigfunc_constant(`public', `int', `UPDATE_SYSTEM_CONFIG_INDICATOR_ADD', `3', `Add virtual switch definition to system configuration file')dnl
pigfunc_constant(`public', `int', `GVRP_VALUE_UNSPECIFIED', `0', `GVRP unspecified')dnl
pigfunc_constant(`public', `int', `GVRP_VALUE_GVRP', `1', `GVRP')dnl
pigfunc_constant(`public', `int', `GVRP_VALUE_NOGVRP', `2', `non-GVRP')dnl
pigfunc_attribute(`private', `', `String', member_name(`switch_name'), `""', `', `The name of the virtual switch')dnl
pigfunc_attribute(`private', `', `String', member_name(`real_device_address'), `""', `', `The real device address of a real OSA-Express QDIO device used to create the switch')dnl
pigfunc_attribute(`private', `', `String', member_name(`port_name'), `""', `', `The name used to identify the OSA Expanded adapter')dnl
pigfunc_attribute(`private', `', `String', member_name(`controller_name'), `"*"', `', `The userid controlling the real device or * for any available device')dnl
pigfunc_attribute(`private', `', `int', member_name(`connection_value'), `0', `', `Activate/do not')dnl
pigfunc_attribute(`private', `', `int', member_name(`queue_memory_limit'), `0', `', `Specifies the QDIO buffer size in megabytes.')dnl
pigfunc_attribute(`private', `', `int', member_name(`routing_value'), `0', `', `Specifies whether the OSA-Express QDIO device will act as a router to the virtual switch')dnl
pigfunc_attribute(`private', `', `int', member_name(`transport_type'), `0', `', `Specifies the transport mechanism to be used for the virtual switch')dnl
pigfunc_attribute(`private', `', `int', member_name(`vlan_id'), `-1', `', `The VLAN ID')dnl
pigfunc_attribute(`private', `', `int', member_name(`port_type'), `-1', `', `Specifies the port type')dnl
pigfunc_attribute(`private', `', `int', member_name(`update_system_config_indicator'), `-1', `', `Configuration options')dnl
pigfunc_attribute(`private', `', `String', member_name(`system_config_name'), `""', `', `File name of the system configuration file')dnl
pigfunc_attribute(`private', `', `String', member_name(`system_config_type'), `""', `', `File type of the system configuration file')dnl
pigfunc_attribute(`private', `', `String', member_name(`parm_disk_owner'), `""', `', `Owner of the parm disk')dnl
pigfunc_attribute(`private', `', `String', member_name(`parm_disk_number'), `""', `', `Number of the parm disk as defined in the server directory')dnl
pigfunc_attribute(`private', `', `String', member_name(`parm_disk_password'), `"`,'"', `', `Multiwrite password for the parm disk')dnl
pigfunc_attribute(`private', `', `String', member_name(`alt_system_config_name'), `"`,'"', `', `File name of the second (alternative) system configuration file')dnl
pigfunc_attribute(`private', `', `String', member_name(`alt_system_config_type'), `"`,'"', `', `File type of the second (alternative) system configuration file')dnl
pigfunc_attribute(`private', `', `String', member_name(`alt_parm_disk_owner'), `"`,'"', `', `Owner of the second (alternative) parm disk')dnl
pigfunc_attribute(`private', `', `String', member_name(`alt_parm_disk_number'), `"`,'"', `', `Number of the second (alternative) parm disk')dnl
pigfunc_attribute(`private', `', `String', member_name(`alt_parm_disk_password'), `"`,'"', `', `Multiwrite password for the second (alternative) parm disk')dnl
pigfunc_attribute(`private', `', `int', member_name(`gvrp_value'), `GVRP_VALUE_UNSPECIFIED', `',  `GVRP value')dnl
pigfunc_ctors(`String', `switch_name', member_name(`switch_name'))dnl
pigfunc_compose_input_start()dnl
pigfunc_compose_input_parm(`CountedString', `getTarget_identifier()', `target_identifier')dnl
pigfunc_compose_input_parm(`CountedString', member_getter(`switch_name')(), `switch_name')dnl
pigfunc_compose_input_parm(`CountedString', member_getter(`real_device_address')(), `real_device_address')dnl
pigfunc_compose_input_parm(`CountedString', member_getter(`port_name'()), `port_name')dnl
pigfunc_compose_input_parm(`CountedString', member_getter(`controller_name')(),`controller_name')dnl
pigfunc_compose_input_parm(VSMInt1, member_getter(`connection_value')(),`connection_value')dnl
pigfunc_compose_input_parm(VSMInt4, member_getter(`queue_memory_limit')(),`queue_memory_limit')dnl
pigfunc_compose_input_parm(VSMInt1, member_getter(`routing_value')(),`routing_value')dnl
pigfunc_compose_input_parm(VSMInt1, member_getter(`transport_type')(),`transport_type')dnl
pigfunc_compose_input_parm(VSMInt4, member_getter(`vlan_id')(),`vlan_id')dnl
pigfunc_compose_input_parm(VSMInt1, member_getter(`port_type')(),`port_type')dnl
pigfunc_compose_input_parm(VSMInt1, member_getter(`update_system_config_indicator')(),`update_system_config_indicator')dnl
pigfunc_compose_input_parm(`CountedString', member_getter(`system_config_name')(),`system_config_name')dnl
pigfunc_compose_input_parm(`CountedString', member_getter(`system_config_type')(),`system_config_type')dnl
pigfunc_compose_input_parm(`CountedString', member_getter(`parm_disk_owner')(),`parm_disk_owner')dnl
pigfunc_compose_input_parm(`CountedString', member_getter(`parm_disk_number')(),`parm_disk_number')dnl
pigfunc_compose_input_parm(`CountedString', member_getter(`parm_disk_password')(),`parm_disk_password')dnl
pigfunc_compose_input_parm(`CountedString', member_getter(`alt_system_config_name')(),`alt_system_config_name')dnl
pigfunc_compose_input_parm(`CountedString', member_getter(`alt_system_config_type')(),`alt_system_config_type')dnl
pigfunc_compose_input_parm(`CountedString', member_getter(`alt_parm_disk_owner')(),`alt_parm_disk_owner')dnl
pigfunc_compose_input_parm(`CountedString', member_getter(`alt_parm_disk_number')(),`alt_parm_disk_number')dnl
pigfunc_compose_input_parm(`CountedString', member_getter(`alt_parm_disk_password')(),`alt_parm_disk_password')dnl
pigfunc_compose_input_parm(VSMInt1, member_getter(`gvrp_value')(),`gvrp_value')dnl
pigfunc_compose_input_end()dnl
pigfunc_compose_output_start()dnl
pigfunc_compose_output_end()dnl
dnl pigfunc_function(`public', `static', `void', `', `main', `String[] argv', `IOException`,' VSMException', `dnl
dnl      * You can execute the VSMAPI call from {@code main()}, try it
dnl      * with no args to see the usage message.
dnl      * @param argv array of commandline args
dnl      * @throws IOException on comm error
dnl      * @throws VSMException on internal Pigiron param marshalling error', `dnl
dnl 
dnl         function_classname instance = null;
dnl 
dnl         if (argv.length != 6) {
dnl             System.out.println("usage: args are:\ninetaddr port user pw target switch_name");
dnl             System.exit(1);
dnl         }
dnl 
dnl         System.out.println("Args are: " + argv[0] + " " + argv[1] + " " + argv[2] + " " + argv[3] + " " + argv[4] + " " + argv[5]);
dnl         instance = new function_classname()(argv[0], Integer.valueOf(argv[1]).intValue(), argv[2], argv[3], argv[4], argv[5]);
dnl 
dnl         ParameterArray pA = instance.doIt();
dnl         System.out.println("Returns from call to " + instance.getFunctionName() + ":");
dnl         Iterator<VSMParm> i = pA.iterator();
dnl         while (i.hasNext()) {
dnl             System.out.println(i.next().prettyPrint());
dnl         }')dnl
pigfunc_endclass()dnl
pigfunc_end()dnl
function_namespace_end()dnl
