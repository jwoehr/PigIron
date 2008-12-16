include(`pigstruct.m4')dnl \\ vswitch_structure.m4
param_namespace(`vswitch', `Virtual_Network_Vswitch_Query')dnl
pigparm_start()dnl
pigparm_import(`com.softwoehr.pigiron.access.*')dnl
pigparm_class(structure_classname, `VSMStruct',`com.softwoehr.pigiron.access.paramstructs',`dnl

/**
 * structure_classname implements the {@code structure_formal_name} from {@code associated_function}
 * @see com.softwoehr.pigiron.functions.associated_function_javaname
 */')dnl
pigparm_constant(`public', `int', `TRANSPORT_TYPE_IP', `1', `IP')dnl
pigparm_constant(`public', `int', `TRANSPORT_TYPE_ETHERNET', `2', `Ethernet')dnl
pigparm_constant(`public', `int', `PORT_TYPE_ACCESS', `1', `Access')dnl
pigparm_constant(`public', `int', `PORT_TYPE_TRUNK', `2', `Trunk')dnl
pigparm_constant(`public', `int', `DEVICE_NOT_ROUTER', `1', `The device will not act as a router')dnl
pigparm_constant(`public', `int', `DEVICE_ROUTER', `2', `The device will act as a router')dnl
pigparm_constant(`public', `int', `GVRP_REQUESTED', `1', `GVRP requested')dnl
pigparm_constant(`public', `int', `GVRP_NOT_REQUESTED', `2', `GVRP not requested')dnl
pigparm_constant(`public', `int', `GVRP_ENABLED', `1', `GVRP enabled')dnl
pigparm_constant(`public', `int', `GVRP_NOT_ENABLED', `2', `GVRP not enabled')dnl
pigparm_constant(`public', `int', `SWITCH_STATUS_VIRTUAL_SWITCH_DEFINED', `1', `Virtual switch defined')dnl
pigparm_constant(`public', `int', `SWITCH_STATUS_CONTROLLER_NOT_AVAILABLE', `2', `Controller not available')dnl
pigparm_constant(`public', `int', `SWITCH_STATUS_OPERATOR_INTERVENTION_REQUIRED', `3', `Operator intervention required')dnl
pigparm_constant(`public', `int', `SWITCH_STATUS_DISCONNECTED', `4', `Disconnected')dnl
pigparm_constant(`public', `int', `SWITCH_STATUS_VIRTUAL_DEVICES_ATTACHED_TO_CONTROLLER', `5', ` Virtual devices attached to controller')dnl
pigparm_constant(`public', `int', `SWITCH_STATUS_OSA_INITIALIZATION_IN_PROGRESS', `6', `OSA initialization in progress')dnl
pigparm_constant(`public', `int', `SWITCH_STATUS_OSA_DEVICE_NOT_READY', `7', `OSA device not ready')dnl
pigparm_constant(`public', `int', `SWITCH_STATUS_OSA_DEVICE_READY', `8', `OSA device ready')dnl
pigparm_constant(`public', `int', `SWITCH_STATUS_OSA_DEVICES_BEING_DETACHED', `9', `OSA devices being detached')dnl
pigparm_constant(`public', `int', `SWITCH_STATUS_VIRTUAL_SWITCH_DELETE_PENDING', `10', `Virtual switch delete pending')dnl
pigparm_constant(`public', `int', `SWITCH_STATUS_VIRTUAL_SWITCH_FAILOVER_RECOVERING', `11', `Virtual switch failover recovering')dnl
pigparm_constant(`public', `int', `SWITCH_STATUS_AUTORESTART_IN_PROGRESS', `12', `Autorestart in progress')dnl
pigparm_ctors()dnl
pigparm_model_start()dnl
pigparm_model_parm(`CountedString', `""', `switch_name')dnl
pigparm_model_parm(`VSMInt1', `0', `transport_type')dnl
pigparm_model_parm(`VSMInt1', `0', `port_type')dnl
pigparm_model_parm(`VSMInt4', `-1', `queue_memory_limit')dnl
pigparm_model_parm(`VSMInt1', `0', `routing_value')dnl
pigparm_model_parm(`VSMInt4', `-1', `vlan_id')dnl
pigparm_model_parm(`VSMInt4', `-1', `native_vlan_id')dnl
pigparm_model_parm(`VSMInt8', `-1', `mac_id')dnl
pigparm_model_parm(`VSMInt1', `0', `gvrp_request_attribute')dnl
pigparm_model_parm(`VSMInt1', `0', `gvrp_enabled_attribute')dnl
pigparm_model_parm(`VSMInt1', `0', `switch_status')dnl
pigparm_model_parm(`VSMInt4', `-1', `real_device_array_length')dnl
pigparm_model_parm(javaize(`real_device_array'), `null', `real_device_array')dnl
pigparm_model_end()dnl
pigparm_endclass()dnl
pigparm_end()dnl
param_namespace_end()dnl
