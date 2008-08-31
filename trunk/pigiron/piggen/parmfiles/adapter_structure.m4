include(`pigstruct.m4')dnl \\ adapter_structure.m4
param_namespace(`adapter',`Virtual_Network_Adapter_Query')dnl
pigparm_start()dnl
pigparm_import(`com.softwoehr.pigiron.access.*')dnl
pigparm_class(structure_classname, `VSMStruct',`com.softwoehr.pigiron.access.paramstructs',`dnl

/**
 * structure_classname implements the {@code structure_formal_name} from {@code associated_function}
 * @see com.softwoehr.pigiron.functions.associated_function_javaname
 */')dnl
pigparm_constant(`public', `int', `ADAPTER_TYPE_HIPERSOCKETS', `1', `Shared read/write access')dnl
pigparm_constant(`public', `int', `ADAPTER_TYPE_QDIO', `2', `Exclusive read/write access')dnl
pigparm_constant(`public', `int', `ADAPTER_STATUS_NOT_COUPLED', `0', `Shared read/write access')dnl
pigparm_constant(`public', `int', `ADAPTER_STATUS_COUPLED_NOT_ACTIVE', `1', `Exclusive read-only access')dnl
pigparm_constant(`public', `int', `ADAPTER_STATUS_COUPLED_ACTIVE', `2', `Shared read/write access, no data saved')dnl
pigparm_ctors()dnl
pigparm_model_start()dnl
pigparm_model_parm(`CountedString', `""', `image_device_number')dnl
pigparm_model_parm(`VSMInt1', `0', `adapter_type')dnl
pigparm_model_parm(`VSMInt4', `-1', `network_adapter_devices')dnl
pigparm_model_parm(`VSMInt1', `0', `adapter_status')dnl
pigparm_model_parm(`CountedString', `""', `lan_owner')dnl
pigparm_model_parm(`CountedString', `""', `lan_name')dnl
pigparm_model_end()dnl
pigparm_endclass()dnl
pigparm_end()dnl
param_namespace_end()dnl
