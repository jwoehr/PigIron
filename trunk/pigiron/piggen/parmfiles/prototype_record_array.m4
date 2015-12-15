include(`pigarray.m4')dnl \\ prototype_record_array.m4
param_namespace(`prototype_record',`Prototype_Create_DM')dnl
pigparm_start()dnl
pigparm_import(`com.softwoehr.pigiron.access.*')dnl
pigparm_class(array_classname, `CountedArray',`com.softwoehr.pigiron.access.paramstructs',`dnl

/**
 * array_classname implements the {@code array_formal_name} from {@code associated_function}
 * @see com.softwoehr.pigiron.functions.associated_function_javaname
 * @see com.softwoehr.pigiron.functions.javaize(`Prototype_Delete_DM')
 * @see com.softwoehr.pigiron.functions.javaize(`Prototype_Name_Query_DM')
 * @see com.softwoehr.pigiron.functions.javaize(`Prototype_Query_DM')
 */')dnl
pigparm_ctors()dnl
pigparm_model_start()dnl
pigparm_model_parm(structure_classname, `', structure_formal_name)dnl
pigparm_model_end()dnl
pigparm_endclass()dnl
pigparm_end()dnl
param_namespace_end()dnl
