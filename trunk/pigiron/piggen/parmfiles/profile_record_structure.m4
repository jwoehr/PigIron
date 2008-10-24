include(`pigstruct.m4')dnl \\ profile_record_structure.m4
param_namespace(`profile_record',`Profile_Create_DM')dnl
pigparm_start()dnl
pigparm_import(`com.softwoehr.pigiron.access.*')dnl
pigparm_class(structure_classname, `VSMStruct',`com.softwoehr.pigiron.access.paramstructs',`dnl

/**
 * structure_classname implements the {@code structure_formal_name} from {@code associated_function}
 * @see com.softwoehr.pigiron.functions.associated_function_javaname
 * @see com.softwoehr.pigiron.functions.ProfileQueryDM
 * @see com.softwoehr.pigiron.access.paramstructs.ProfileRecordArray
 */')dnl
pigparm_ctors()dnl
pigparm_model_start()dnl
pigparm_model_parm(`CountedString', `""', `name')dnl
pigparm_model_end()dnl
pigparm_endclass()dnl
pigparm_end()dnl
param_namespace_end()dnl
