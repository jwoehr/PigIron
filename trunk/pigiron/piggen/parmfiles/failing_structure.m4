include(`pigstruct.m4')dnl \\  failing_structure.m4
param_namespace(`failing',`Image_Recycle')dnl
pigparm_start()dnl
pigparm_import(`com.softwoehr.pigiron.access.*')dnl
pigparm_class(structure_classname, `VSMStruct',`com.softwoehr.pigiron.access.paramstructs',`dnl

/**
 * structure_classname implements the {@code structure_formal_name} from {@code associated_function}
 * @see com.softwoehr.pigiron.functions.associated_function_javaname
 */')dnl
pigparm_ctors()dnl
pigparm_model_start()dnl
pigparm_model_parm(`CountedString', `""', `image_name')dnl
pigparm_model_parm(`VSMInt4', `-1', `return_code')dnl
pigparm_model_parm(`VSMInt4', `-1', `reason_code')dnl
pigparm_model_end()dnl
pigparm_endclass()dnl
pigparm_end()dnl
param_namespace_end()dnl
