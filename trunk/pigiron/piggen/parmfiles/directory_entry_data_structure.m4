dnl \\ Query_All_DM has two different sorts of structures it returns
dnl \\ based on the FORMAT=YES/NO optional parameter (default=YES).
dnl \\ This structure is the tail of the FORMAT=NO style
include(`pigstruct.m4')dnl \\ directory_entry_data_structure.m4
param_namespace(`directory_entry_data', `Query_All_DM')dnl
pigparm_start()dnl
pigparm_import(`com.softwoehr.pigiron.access.*')dnl
pigparm_class(structure_classname, `VSMStruct',`com.softwoehr.pigiron.access.paramstructs',`dnl
/**
 * structure_classname implements the {@code structure_formal_name} from {@code associated_function}
 * Query_All_DM has two different sorts of structures it returns
 * based on the FORMAT=YES/NO optional parameter (default=YES).
 * This structure is the tail of the FORMAT=NO style
 * @see com.softwoehr.pigiron.functions.associated_function_javaname
 */')dnl
pigparm_ctors()dnl
pigparm_model_start()dnl
pigparm_model_parm(`CountedString', `""', `directory_entry_record')dnl
pigparm_model_end()dnl
pigparm_endclass()dnl
pigparm_end()dnl
param_namespace_end()dnl
