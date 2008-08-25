include(`pigarray.m4')dnl \\ failing_array.m4
param_namespace(`failing',`ImageRecycle')dnl
pigparm_start()dnl
pigparm_import(`com.softwoehr.pigiron.access.*')dnl
pigparm_class(`array_classname', `VSMArray',`com.softwoehr.pigiron.access.paramstructs',`dnl

/**
 * array_classname implements the memory_segment_array from associated_function
 * @see com.softwoehr.pigiron.functions.`'associated_function
 * @see com.softwoehr.pigiron.access.paramstructs.`'structure_classname
 * @see com.softwoehr.pigiron.access.paramstructs.`'counted_structure_classname
 */')dnl
pigparm_ctors()dnl
pigparm_model_start()dnl
pigparm_model_parm(`counted_structure_classname', `null', `counted_structure_formal_name')dnl
pigparm_model_end()dnl
pigparm_endclass()dnl
pigparm_end()dnl
