include(`pigstruct.m4')dnl \\ failing_structure_counted.m4
param_namespace(`failing',`Image_Recycle')dnl
pigparm_start()dnl
pigparm_import(`com.softwoehr.pigiron.access.*')dnl
pigparm_class(counted_structure_classname, `CountedStruct',`com.softwoehr.pigiron.access.paramstructs',`dnl

/**
 * counted_structure_classname wrappers the {@code structure_formal_name} from {@code associated_function}
 * as a PigIron CountedStruct pseudotype.
 * @see com.softwoehr.pigiron.functions.associated_function_javaname
 * @see com.softwoehr.pigiron.access.paramstructs.structure_classname
 */')dnl
pigparm_ctors()dnl
pigparm_model_start()dnl
pigparm_model_parm(`VSMInt4', `-1', structure_formal_name`_length')dnl
pigparm_model_parm(structure_classname, `null', structure_formal_name)dnl
pigparm_model_end()dnl
pigparm_endclass()dnl
pigparm_end()dnl
param_namespace_end()dnl
