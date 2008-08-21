include(`pigstruct.m4')dnl \\ adapter_struct.m4
pigparm_start()dnl
pigparm_import(`com.softwoehr.pigiron.access.*')dnl
pigparm_class(`AdapterStruct', `VSMStruct',`com.softwoehr.pigiron.access.paramstructs',`dnl

/**
 * AdapterStruct implements the adapter_struct from VirtualNetworkAdapterQuery
 * @see com.softwoehr.pigiron.functions.VirtualNetworkAdapterQuery
 */')dnl
pigparm_constant(`public', `int', `ADAPTER_TYPE_HIPERSOCKETS', `1', `Shared read/write access')dnl
pigparm_constant(`public', `int', `ADAPTER_TYPE_QDIO', `2', `Exclusive read/write access')dnl
pigparm_constant(`public', `int', `ADAPTER_STATUS_NOT_COUPLED', `0', `Shared read/write access')dnl
pigparm_constant(`public', `int', `ADAPTER_STATUS_COUPLED_NOT_ACTIVE', `1', `Exclusive read-only access')dnl
pigparm_constant(`public', `int', `ADAPTER_STATUS_COUPLED_ACTIVE', `2', `Shared read/write access, no data saved')dnl
pigparm_ctors()dnl
pigparm_model_start()dnl
pigparm_model_parm(`VSMInt4', `-1', `image_device_number_length')dnl
pigparm_model_parm(`VSMString', `null', `image_device_number')dnl
pigparm_model_parm(`VSMInt1', `-1', `adapter_type')dnl
pigparm_model_parm(`VSMInt4', `-1', `network_adapter_devices')dnl
pigparm_model_parm(`VSMInt1', `-1', `adapter_status')dnl
pigparm_model_parm(`VSMInt4', `-1', `lan_owner_length')dnl
pigparm_model_parm(`VSMString', `null', `lan_owner')dnl
pigparm_model_parm(`VSMInt4', `-1', `lan_name_length')dnl
pigparm_model_parm(`VSMString', `null', `lan_name')dnl
pigparm_model_end()dnl
pigparm_endclass()dnl
pigparm_end()dnl