include(`pigarray.m4')dnl \\ page_range_struct.m4
pigparm_start()dnl
pigparm_import(`com.softwoehr.pigiron.access.*')dnl
pigparm_class(`PageRangeArray', `VSMArray',`com.softwoehr.pigiron.access.paramstructs',`dnl

/**
 * PageRangeArray implements the page_range_array from Shared_Memory_Query
 * @see com.softwoehr.pigiron.functions.SharedMemoryQuery
 * @see com.softwoehr.pigiron.access.paramstructs.PageRangeStruct
 */')dnl
pigparm_ctors()dnl
pigparm_model_start()dnl
pigparm_model_parm(`PageRangeStructCounted', `null', `page_range_struct_counted')dnl
pigparm_model_end()dnl
pigparm_endclass()dnl
pigparm_end()dnl
