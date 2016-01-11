include(`pigstruct.m4')dnl \\  memory_segment_structure.m4
param_namespace(`link',`Image_MDISK_Link_Query')dnl
pigparm_start()dnl
pigparm_import(`com.softwoehr.pigiron.access.*')dnl
pigparm_class(structure_classname, `StringBlankSeparatedNullTerminatedStruct',`com.softwoehr.pigiron.access.paramstructs',`dnl

/**
 * structure_classname implements the {@code structure_formal_name} from {@code associated_function}
 * @see com.softwoehr.pigiron.functions.associated_function_javaname
 */')dnl
pigparm_ctors()dnl
pigparm_model_start()dnl
pigparm_model_parm(`VSMString', `""', `system_name')dnl
pigparm_model_parm(`VSMString', `""', `user')dnl
pigparm_model_parm(`VSMString', `""', `vaddr')dnl
pigparm_model_parm(`VSMString', `""', `access_mode')dnl
pigparm_model_end()dnl
pigparm_endclass()dnl
pigparm_end()dnl
param_namespace_end()dnl