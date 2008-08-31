include(`pigstruct.m4')dnl \\  memory_segment_structure.m4
param_namespace(`lan',`Virtual_Network_LAN_Query')dnl
pigparm_start()dnl
pigparm_import(`com.softwoehr.pigiron.access.*')dnl
pigparm_class(structure_classname, `VSMStruct',`com.softwoehr.pigiron.access.paramstructs',`dnl

/**
 * structure_classname implements the {@code structure_formal_name} from {@code associated_function}
 * @see com.softwoehr.pigiron.functions.associated_function_javaname
 */')dnl
pigparm_constant(`public', `int', `LAN_TYPE_HIPERSOCKETS', `1', `Simulated HiperSockets NIC')dnl
pigparm_constant(`public', `int', `LAN_TYPE_QDIO', `1', `Simulated QDIO NIC')dnl
pigparm_ctors()dnl
pigparm_model_start()dnl
pigparm_model_parm(`CountedString', `""', `lan_name')dnl
pigparm_model_parm(`CountedString', `""', `lan_owner')dnl
pigparm_model_parm(`VSMInt1', `-0', `lan_type')dnl
pigparm_model_parm(`VSMInt4', `-1', `connected_adapter_array_length')dnl
pigparm_model_parm(`ConnectedAdapterArray', `null', `connected_adapter_array')dnl
pigparm_model_end()dnl
pigparm_endclass()dnl
pigparm_end()dnl
param_namespace_end()dnl
