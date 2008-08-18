include(`pigstruct.m4')dnl \\  memory_segment_struct.m4
pigparm_start()dnl
pigparm_import(`com.softwoehr.pigiron.access.*')dnl
pigparm_class(`MemorySegmentStruct', `VSMStruct',`com.softwoehr.pigiron.access.paramstructs',`dnl

/**
 * MemorySegmentStruct implements the memory_segment_structure from Shared_Memory_Query
 * @see com.softwoehr.pigiron.functions.SharedMemoryQuery
 */')dnl
pigparm_constant(`public', `int', `MEMORY_SEGMENT_STATUS_SKELETON', `1', `Skeleton')dnl
pigparm_constant(`public', `int', `MEMORY_SEGMENT_STATUS_AVAILABLE_NONRESTRICTED', `2', `Available and nonrestricted')dnl
pigparm_constant(`public', `int', `MEMORY_SEGMENT_STATUS_AVAILABLE_RESTRICTED', `3', `Available and restricted')dnl
pigparm_constant(`public', `int', `MEMORY_SEGMENT_STATUS_PENDING_PURGE', `4', `Pending purge')dnl
pigparm_ctors()dnl
pigparm_model_start()dnl
pigparm_model_parm(`VSMInt4', `-1', `memory_segment_name_length')dnl
pigparm_model_parm(`VSMString', `null', `memory_segment_name')dnl
pigparm_model_parm(`VSMInt1', `-1', `memory_segment_status')dnl
pigparm_model_parm(`VSMInt4', `-1', `page_range_array_length')dnl
pigparm_model_parm(`PageRangeArray', `null', `page_range_array')dnl
pigparm_model_end()dnl
pigparm_endclass()dnl
pigparm_end()dnl
