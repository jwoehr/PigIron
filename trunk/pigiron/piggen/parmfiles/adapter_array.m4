include(`pigarray.m4')dnl \\ adapter_array.m4
param_namespace(`adapter',`VirtualNetworkAdapterQuery')dnl
pigparm_start()dnl
pigparm_import(`com.softwoehr.pigiron.access.*')dnl
pigparm_class(array_classname, `VSMArray',`com.softwoehr.pigiron.access.paramstructs',`dnl

/**
 * structure_classname implements the structure_formal_name from associated_function
 * @see com.softwoehr.pigiron.functions.`'associated_function
 */')dnl
pigparm_ctors()dnl
pigparm_model_start()dnl
pigparm_model_parm(`AdapterStructCounted', `null', `adapter_struct_counted')dnl
pigparm_model_end()dnl
pigparm_endclass()dnl
pigparm_end()dnl
param_namespace_end()dnl
