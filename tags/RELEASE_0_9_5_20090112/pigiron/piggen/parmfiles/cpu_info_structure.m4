include(`pigstruct.m4')dnl \\ cpu_info_structure.m4
param_namespace(`cpu_info', `Image_Active_Configuration_Query')dnl
pigparm_start()dnl
pigparm_import(`com.softwoehr.pigiron.access.*')dnl
pigparm_class(structure_classname, `VSMStruct',`com.softwoehr.pigiron.access.paramstructs',`dnl

/**
 * structure_classname implements the {@code structure_formal_name} from {@code associated_function}
 * @see com.softwoehr.pigiron.functions.associated_function_javaname
 */')dnl
pigparm_ctors()dnl
pigparm_model_start()dnl
pigparm_model_parm(`VSMInt4', `-1', `cpu_number')dnl
pigparm_model_parm(`CountedString', `""', `cpu_id')dnl
pigparm_model_parm(`VSMInt1', `-1', `cpu_status')dnl
pigparm_model_end()dnl
pigparm_endclass()dnl
pigparm_end()dnl
param_namespace_end()dnl
