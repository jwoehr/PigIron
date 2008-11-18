include(`pigfunc.m4')dnl \\ virtual_network_vswitch_delete.m4
function_namespace(`Virtual_Network_Vswitch_Delete',`vswitch_array')dnl
pigfunc_start()dnl
pigfunc_import(`java.io.IOException')dnl
pigfunc_import(`com.softwoehr.pigiron.access.*')dnl
pigfunc_import(`com.softwoehr.pigiron.access.paramstructs.significant_parameter_classname')dnl
pigfunc_class(function_classname,`VSMCall',`com.softwoehr.pigiron.functions',function_formal_name,`dnl

/**
 * {@code function_formal_name} VSMAPI Function
 * @see com.softwoehr.pigiron.access.paramstructs.significant_parameter_classname
 */')dnl
pigfunc_constant(`public', `int', `PORT_TYPE_TRUNK', `2', `Trunk')dnl
pigfunc_constant(`public', `int', `UPDATE_SYSTEM_CONFIG_INDICATOR_UNSPECIFIED', `0', `Unspecified')dnl
pigfunc_constant(`public', `int', `UPDATE_SYSTEM_CONFIG_INDICATOR_CREATE', `1', `Create virtual switch on the active system')dnl
pigfunc_constant(`public', `int', `UPDATE_SYSTEM_CONFIG_INDICATOR_CREATE_ADD', `2', `Create virtual switch on the active system and add virtual switch definition to system configuration file')dnl
pigfunc_constant(`public', `int', `UPDATE_SYSTEM_CONFIG_INDICATOR_ADD', `3', `Add virtual switch definition to system configuration file')dnl
pigfunc_constant(`public', `int', `GVRP_VALUE_UNSPECIFIED', `0', `GVRP unspecified')dnl
pigfunc_constant(`public', `int', `GVRP_VALUE_GVRP', `1', `GVRP')dnl
pigfunc_constant(`public', `int', `GVRP_VALUE_NOGVRP', `2', `non-GVRP')dnl
pigfunc_attribute(`private', `', `String', member_name(`switch_name'), `""', `', `The name of the virtual switch')dnl
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
pigfunc_ctors(`String', `switch_name', member_name(`switch_name'),
`int', `update_system_config_indicator', member_name(`update_system_config_indicator'),
`String',`system_config_name', member_name(`system_config_name'),
`String',`system_config_type', member_name(`system_config_type'),
`String',`parm_disk_owner', member_name(`parm_disk_owner'),
`String',`parm_disk_number', member_name(`parm_disk_number'),
`String',`parm_disk_password', member_name(`parm_disk_password'),
`String',`alt_system_config_name', member_name(`alt_system_config_name'),
`String',`alt_system_config_type', member_name(`alt_system_config_type'),
`String',`alt_parm_disk_owner', member_name(`alt_parm_disk_owner'),
`String',`alt_parm_disk_number', member_name(`alt_parm_disk_number'),
`String',`alt_parm_disk_password', member_name(`alt_parm_disk_password'))dnl
pigfunc_compose_input_start()dnl
pigfunc_compose_input_parm(`CountedString', `getTarget_identifier()', `target_identifier')dnl
pigfunc_compose_input_parm(`CountedString', member_getter(`switch_name')(), `switch_name')dnl
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
