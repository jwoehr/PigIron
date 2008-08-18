include(`pigarray.m4')dnl \\ memory_segment_array.m4
pigparm_start()dnl
pigparm_import(`com.softwoehr.pigiron.access.*')dnl
pigparm_class(`MemorySegmentArray', `VSMArray',`com.softwoehr.pigiron.access.paramstructs',`dnl

/**
 * MemorySegmentArray implements the memory_segment_array from Shared_Memory_Query
 * @see com.softwoehr.pigiron.functions.SharedMemoryQuery
 * @see com.softwoehr.pigiron.access.paramstructs.PageRangeStruct
 */')dnl
pigparm_ctors()dnl
pigparm_model_start()dnl
pigparm_model_parm(`MemorySegmentStructCounted', `null', `memory_segment_structure_counted')dnl
pigparm_model_end()dnl
pigparm_endclass()dnl
pigparm_end()dnl
