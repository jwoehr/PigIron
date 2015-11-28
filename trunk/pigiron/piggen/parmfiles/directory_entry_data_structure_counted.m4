dnl \\ Query_All_DM has two different sorts of structures it returns
dnl \\ based on the FORMAT=YES/NO optional parameter (default=NO).
dnl \\ This structure is the tail of the FORMAT=NO style
include(`pigstruct.m4')dnl \\  directory_entry_data_structure_counted.m4
param_namespace(`directory_entry_data',`Query_All_DM')dnl
pigparm_start()dnl
pigparm_import(`com.softwoehr.pigiron.access.*')dnl
pigparm_class(counted_structure_classname, `CountedStruct',`com.softwoehr.pigiron.access.paramstructs',`dnl

/**
 * counted_structure_classname wrappers the {@code structure_classname} from {@code associated_function}
 * as a PigIron CountedStruct pseudotype.
 * @see com.softwoehr.pigiron.functions.associated_function_javaname
 * @see com.softwoehr.pigiron.access.paramstructs.structure_classname
 */')dnl
pigparm_ctors()dnl
pigparm_model_start()dnl
pigparm_model_parm(`VSMInt4', `-1', structure_formal_name`_length')dnl
pigparm_model_parm(structure_classname, `', structure_formal_name)dnl
pigparm_model_end()dnl
pigparm_endclass()dnl
pigparm_end()dnl
param_namespace_end()dnl
