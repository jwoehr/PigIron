include(`pigarray.m4')dnl \\ page_range_array.m4
param_namespace(`page_range',`Shared_Memory_Query')dnl
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
pigparm_model_parm(`counted_structure_classname', `null', `counted_structure_formal_name')dnl
pigparm_model_end()dnl
pigparm_endclass()dnl
pigparm_end()dnl
param_namespace_end()dnl
