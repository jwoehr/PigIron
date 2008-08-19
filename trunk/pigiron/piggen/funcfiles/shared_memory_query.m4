include(`pigfunc.m4')dnl \\ shared_memory_query.m4
pigfunc_start()dnl
pigfunc_import(`com.softwoehr.pigiron.access.*')dnl
pigfunc_import(`com.softwoehr.pigiron.access.paramstructs.MemorySegmentArray')dnl
pigfunc_class(`Shared_Memory_Query',`VSMCall',`com.softwoehr.pigiron.functions,`shared_memory_query',`dnl

/**
 * Shared_Memory_Query VSMAPI Fuction
 * @see com.softwoehr.pigiron.access.parmstructs.MemorySegmentArray
 * @see com.softwoehr.pigiron.access.parmstructs.PageRangeArray
 */')dnl
pigfunc_ctors()dnl
pigfunc_compose_input_start()dnl
pigfunc_compose_input_parm(`CountedString', `-1', `target_identifier')dnl
pigfunc_compose_input_parm(`CountedString', `-1', `memory_segment_name')dnl
pigfunc_compose_input_end()dnl
pigfunc_compose_output_start()dnl
pigfunc_compose_output_parm(`VSMInt4', `-1', `memory_segment_array_length')dnl
pigfunc_compose_output_parm(`MemorySegmentArray', `-1', `memory_segment_array')dnl
pigfunc_compose_output_end()dnl
pigfunc_endclass()dnl
pigfunc_end()dnl
