dnl \\ Query_All_DM has two different sorts of structures it returns
dnl \\ based on the FORMAT=YES/NO optional parameter (default=NO).
dnl \\ This structure is in the head of both FORMATs.
include(`pigstruct.m4')dnl \\ directory_entry_structure.m4
param_namespace(`directory_entry', `Query_All_DM')dnl
pigparm_start()dnl
pigparm_import(`com.softwoehr.pigiron.access.*')dnl
pigparm_class(structure_classname, `VSMStruct',`com.softwoehr.pigiron.access.paramstructs',`dnl

/**
 * structure_classname implements the {@code structure_formal_name} from {@code associated_function}
 * @see com.softwoehr.pigiron.functions.associated_function_javaname
 */')dnl
pigparm_constant(`public', `int', `DIRECTORY_ENTRY_TYPE_USER', `0', `USER')dnl
pigparm_constant(`public', `int', `DIRECTORY_ENTRY_TYPE_PROFILE', `1', `PROFILE')dnl
pigparm_constant(`public', `int', `DIRECTORY_ENTRY_TYPE_USER_DEFINED_VIA_POOL', `2', `USER_DEFINED_VIA_POOL')dnl
pigparm_constant(`public', `int', `DIRECTORY_ENTRY_TYPE_POOL', `3', `POOL')dnl
pigparm_constant(`public', `int', `DIRECTORY_ENTRY_TYPE_DIRECTORY', `4', `DIRECTORY')dnl
pigparm_constant(`public', `int', `DIRECTORY_ENTRY_TYPE_GLOBAL', `5', `GLOBAL')dnl
pigparm_constant(`public', `int', `DIRECTORY_ENTRY_TYPE_IDENTITY', `6', `IDENTITY')dnl
pigparm_constant(`public', `int', `DIRECTORY_ENTRY_TYPE_SUBCON', `7', `SUBCON')dnl
pigparm_constant(`public', `int', `DIRECTORY_ENTRY_TYPE_OTHER', `8', `OTHER')dnl
pigparm_ctors()dnl
pigparm_model_start()dnl
pigparm_model_parm(`VSMInt4', `0', `directory_entry_type')dnl
pigparm_model_parm(`CountedString', `""', `directory_entry_id')dnl
pigparm_model_end()dnl
pigparm_endclass()dnl
pigparm_end()dnl
param_namespace_end()dnl
