include(`pigarray.m4')dnl \\ adapter_array.m4
param_namespace(`adapter',`Virtual_Network_Adapter_Query')dnl
pigparm_start()dnl
pigparm_import(`com.softwoehr.pigiron.access.*')dnl
pigparm_class(array_classname, `VSMArray',`com.softwoehr.pigiron.access.paramstructs',`dnl

/**
 * array_classname implements the array_formal_name from associated_function
 * @see com.softwoehr.pigiron.functions.associated_function
 */')dnl
pigparm_ctors()dnl
pigparm_model_start()dnl
pigparm_model_parm(counted_structure_classname, `null', counted_structure_formal_name)dnl
pigparm_model_end()dnl
pigparm_endclass()dnl
pigparm_end()dnl
param_namespace_end()dnl
