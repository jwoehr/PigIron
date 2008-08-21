include(`pigstruct.m4')dnl \\ adapter_struct_counted.m4
pigparm_start()dnl
pigparm_import(`com.softwoehr.pigiron.access.*')dnl
pigparm_class(`AdapterStructCounted', `CountedStruct',`com.softwoehr.pigiron.access.paramstructs',`dnl

/**
 * AdapterStructCounted wrappers the adapter_structure from VirtualNetworkAdapterQuery
 * as a PigIron CountedStruct pseudotype
 * @see com.softwoehr.pigiron.functions.VirtualNetworkAdapterQuery
 */')dnl
pigparm_ctors()dnl
pigparm_model_start()dnl
pigparm_model_parm(`VSMInt4', `-1', `adapter_structure_length')dnl
pigparm_model_parm(`AdapterStruct', `null', `adapter_structure')dnl
pigparm_model_end()dnl
pigparm_endclass()dnl
pigparm_end()dnl
