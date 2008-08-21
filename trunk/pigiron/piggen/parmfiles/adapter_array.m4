include(`pigarray.m4')dnl \\ adapter_array.m4
pigparm_import(`com.softwoehr.pigiron.access.*')dnl
pigparm_class(`AdapterArray', `VSMArray',`com.softwoehr.pigiron.access.paramstructs',`dnl

/**
 * AdapterArray implements the page_range_array from Virtual_Adapter_Query
 * @see com.softwoehr.pigiron.functions.Virtual_Adapter_Query
 * @see com.softwoehr.pigiron.access.paramstructs.AdapterStruct
 */')dnl
pigparm_ctors()dnl
pigparm_model_start()dnl
pigparm_model_parm(`AdapterStructCounted', `null', `adapter_struct_counted')dnl
pigparm_model_end()dnl
pigparm_endclass()dnl
pigparm_end()dnl
