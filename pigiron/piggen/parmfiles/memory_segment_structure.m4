include(`pigstruct.m4')dnl \\  memory_segment_structure.m4
param_namespace(`memory_segment',`Shared_Memory_Query')dnl
pigparm_start()dnl
pigparm_import(`com.softwoehr.pigiron.access.*')dnl
pigparm_class(structure_classname, `VSMStruct',`com.softwoehr.pigiron.access.paramstructs',`dnl

/**
 * structure_classname implements the {@code structure_formal_name} from {@code associated_function}
 * @see com.softwoehr.pigiron.functions.associated_function_javaname
 */')dnl
pigparm_constant(`public', `int', `MEMORY_SEGMENT_STATUS_SKELETON', `1', `Skeleton')dnl
pigparm_constant(`public', `int', `MEMORY_SEGMENT_STATUS_AVAILABLE_NONRESTRICTED', `2', `Available and nonrestricted')dnl
pigparm_constant(`public', `int', `MEMORY_SEGMENT_STATUS_AVAILABLE_RESTRICTED', `3', `Available and restricted')dnl
pigparm_constant(`public', `int', `MEMORY_SEGMENT_STATUS_PENDING_PURGE', `4', `Pending purge')dnl
pigparm_ctors()dnl
pigparm_model_start()dnl
pigparm_model_parm(`CountedString', `""', `memory_segment_name')dnl
pigparm_model_parm(`VSMInt1', `0', `memory_segment_status')dnl
pigparm_model_parm(`PageRangeArray', `null', `page_range_array')dnl
pigparm_model_end()dnl
pigparm_endclass()dnl
pigparm_end()dnl
param_namespace_end()dnl
