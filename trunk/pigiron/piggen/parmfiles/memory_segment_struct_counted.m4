include(`pigstruct.m4')dnl \\ memory_segment_struct_counted.m4
pigparm_start()dnl
pigparm_import(`com.softwoehr.pigiron.access.*')dnl
pigparm_class(`MemorySegmentStructCounted', `CountedStruct',`com.softwoehr.pigiron.access.paramstructs',`dnl

/**
 * MemorySegmentStructCounted wrappers the memory_segment_structure from Shared_Memory_Query
 * as a PigIron CountedStruct pseudotype.
 * @see com.softwoehr.pigiron.functions.SharedMemoryQuery
 */')dnl
pigparm_ctors()dnl
pigparm_model_start()dnl
pigparm_model_parm(`VSMInt4', `-1', `memory_segment_structure_length')dnl
pigparm_model_parm(`MemorySegmentStruct', `null', `memory_segment_structure')dnl
pigparm_model_end()dnl
pigparm_endclass()dnl
pigparm_end()dnl
