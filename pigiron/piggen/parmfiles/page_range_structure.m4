include(`pigparm.m4')dnl \\ page_range_structure.m4
pigparm_start()dnl
pigparm_import(`com.softwoehr.pigiron.access.*')dnl
pigparm_import(`com.softwoehr.pigiron.access.paramstructs.*')dnl
pigparm_class(`PageRangeStruct', `VSMStruct',`com.softwoehr.pigiron.access.paramstructs',`
    /**
     * SomeParamStruct extends com.softwoehr.pigiron.access.VSMStruct so that
     * we can implement a custom VSMAPI function on our host and access it from
     * PigIron class libs.
     */')dnl
pigparm_constant(`public', `int', `PAGE_RANGE_STRUCT_SW', `1', `Shared read/write access')dnl
pigparm_constant(`public', `int', `PAGE_RANGE_STRUCT_EW', `2', `Exclusive read/write access')dnl
pigparm_constant(`public', `int', `PAGE_RANGE_STRUCT_SR', `3', `Shared read/write access')dnl
pigparm_constant(`public', `int', `PAGE_RANGE_STRUCT_ER', `4', `Exclusive read-only access')dnl
pigparm_constant(`public', `int', `PAGE_RANGE_STRUCT_SN', `5', `Shared read/write access, no data saved')dnl
pigparm_constant(`public', `int', `PAGE_RANGE_STRUCT_EN', `6', `Exclusive read/write access, no data saved')dnl
pigparm_constant(`public', `int', `PAGE_RANGE_STRUCT_SC', `7', `Shared read/write access, no data saved, CP writeable pages')dnl
pigparm_ctors()dnl
pigparm_model_start()dnl
pigparm_model_parm(`VSMInt8', `-1', `begin_page')dnl
pigparm_model_parm(`VSMInt8', `-1', `end_page')dnl
pigparm_model_parm(`VSMInt1', `-1', `page_access_descriptor')dnl
pigparm_model_end()dnl
pigparm_endclass()dnl
pigparm_end()dnl
