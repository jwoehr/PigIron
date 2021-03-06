dnl \\ Query_All_DM has two different sorts of structures it returns
dnl \\ based on the FORMAT=YES/NO optional parameter (default=NO).
dnl \\ This Array is for FORMAT=YES.
dnl \\ The corresponding array for FORMAT=NO is directory_entry_format_no_array 
include(`pigarray.m4')dnl \\ directory_entry_array.m4
param_namespace(`directory_entry',`Query_All_DM')dnl
pigparm_start()dnl
pigparm_import(`com.softwoehr.pigiron.access.*')dnl
pigparm_class(array_classname, `CountedArray',`com.softwoehr.pigiron.access.paramstructs',`dnl

/**
 * array_classname implements the {@code array_formal_name} from {@code associated_function}
 * Query_All_DM has two different sorts of structures it returns
 * based on the FORMAT=YES/NO optional parameter (default=YES).
 * This structure is for FORMAT=YES.
 * The corresponding array for FORMAT=NO is DirectoryEntryFormatNoArray 
 * @see com.softwoehr.pigiron.functions.associated_function_javaname
 * @see com.softwoehr.pigiron.access.paramstructs.counted_structure_classname
 */')dnl
pigparm_ctors()dnl
pigparm_model_start()dnl
pigparm_model_parm(`counted_structure_classname', `null', `counted_structure_formal_name')dnl
pigparm_model_end()dnl
pigparm_endclass()dnl
pigparm_end()dnl
param_namespace_end()dnl
