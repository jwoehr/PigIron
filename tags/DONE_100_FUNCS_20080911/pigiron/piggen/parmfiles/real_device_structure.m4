include(`pigstruct.m4')dnl \\ real_device_structure.m4
param_namespace(`real_device', javaize(`Virtual_Network_Vswitch_Query'))dnl
pigparm_start()dnl
pigparm_import(`com.softwoehr.pigiron.access.*')dnl
pigparm_class(structure_classname, `VSMStruct',`com.softwoehr.pigiron.access.paramstructs',`dnl

/**
 * structure_classname implements the {@code structure_formal_name} from {@code associated_function}
 * @see com.softwoehr.pigiron.functions.associated_function_javaname
 */')dnl
pigparm_constant(`public', `int', `DEVICE_STATUS_NOT_ACTIVE', `0', `Device is not active')dnl
pigparm_constant(`public', `int', `DEVICE_STATUS_ACTIVE', `1', `Device is active')dnl
pigparm_constant(`public', `int', `DEVICE_STATUS_BACKUP_DEVICE', `2', `Device is a backup device')dnl
pigparm_constant(`public', `int', `DEVICE_ERROR_STATUS_NO_ERROR', `0', `No error')dnl
pigparm_constant(`public', `int', `DEVICE_ERROR_STATUS_PORT_NAME_CONFLICT', `1', `Port name conflict')dnl
pigparm_constant(`public', `int', `DEVICE_ERROR_STATUS_NO_LAYER_2_SUPPORT', `2', `No layer 2 support')dnl
pigparm_constant(`public', `int', `DEVICE_ERROR_STATUS_REAL_DEVICE_DOES_NOT_EXIST', `3', `Real device does not exist')dnl
pigparm_constant(`public', `int', `DEVICE_ERROR_STATUS_REAL_DEVICE_ATTACHED_ELSEWHERE', `4', `Real device is attached elsewhere')dnl
pigparm_constant(`public', `int', `DEVICE_ERROR_STATUS_REAL_DEVICE_NOT_QDIO_OSA_E', `5', `Real device is not QDIO OSA-E')dnl
pigparm_constant(`public', `int', `DEVICE_ERROR_STATUS_INITIALIZATION_ERROR', `6', `Initialization error')dnl
pigparm_constant(`public', `int', `DEVICE_ERROR_STATUS_STALLED_OSA', `7', `Stalled OSA')dnl
pigparm_constant(`public', `int', `DEVICE_ERROR_STATUS_STALLED_CONTROLLER', `8', `Stalled controller')dnl
pigparm_constant(`public', `int', `DEVICE_ERROR_STATUS_CONTROLLER_CONNECTION_SEVERED', `9', `Controller connection severed')dnl
pigparm_constant(`public', `int', `DEVICE_ERROR_STATUS_PRIMARY_SECONDARY_ROUTING_CONFLICT', `10', `Primary or secondary routing conflict')dnl
pigparm_constant(`public', `int', `DEVICE_ERROR_STATUS_DEVICE_OFFLINE', `11', `Device is offline')dnl
pigparm_constant(`public', `int', `DEVICE_ERROR_STATUS_DEVICE_DETACHED', `12', `Device was detached')dnl
pigparm_constant(`public', `int', `DEVICE_ERROR_STATUS_IP_ETHERNET_TYPE_MISMATCH', `13', `IP/Ethernet type mismatch')dnl
pigparm_constant(`public', `int', `DEVICE_ERROR_STATUS_INSUFFICIENT_MEMORY_CONTROLLER_VIRTUAL_MACHINE', `14', `Insufficient memory in controller virtual machine')dnl
pigparm_constant(`public', `int', `DEVICE_ERROR_STATUS_TCP_IP_CONFIGURATION_CONFLICT', `15', `TCP/IP configuration conflict')dnl
pigparm_ctors()dnl
pigparm_model_start()dnl
pigparm_model_parm(`VSMInt4', `-1', `real_device_address')dnl
pigparm_model_parm(`CountedString', `""', `controller_name')dnl
pigparm_model_parm(`CountedString', `""', `port_name')dnl
pigparm_model_parm(`VSMInt1', `0', `device_status')dnl
pigparm_model_parm(`VSMInt1', `0', `device_error_status')dnl
pigparm_model_parm(`VSMInt4', `-1', `authorized_user_array_length')dnl
pigparm_model_parm(javaize(`authorized_user_array'), `null', `authorized_user_array')dnl
pigparm_model_parm(`VSMInt4', `-1', `connected_adapter_array_length')dnl
pigparm_model_parm(javaize(`connected_adapter_array'), `null', `connected_adapter_array')dnl
pigparm_model_end()dnl
pigparm_endclass()dnl
pigparm_end()dnl
param_namespace_end()dnl
