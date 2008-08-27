include(`pigstruct.m4')dnl \\ page_range_structure.m4
param_namespace(`page_range',`Shared_Memory_Query')dnl
pigparm_start()dnl
pigparm_import(`com.softwoehr.pigiron.access.*')dnl
pigparm_class(structure_classname, `VSMStruct',`com.softwoehr.pigiron.access.paramstructs',`dnl

/**
 * structure_classname implements the structure_formal_name from associated_function
 * @see com.softwoehr.pigiron.functions.associated_function
 */')dnl
pigparm_constant(`public', `int', `PAGE_ACCESS_SW', `1', `Shared read/write access')dnl
pigparm_constant(`public', `int', `PAGE_ACCESS_EW', `2', `Exclusive read/write access')dnl
pigparm_constant(`public', `int', `PAGE_ACCESS_SR', `3', `Shared read/write access')dnl
pigparm_constant(`public', `int', `PAGE_ACCESS_ER', `4', `Exclusive read-only access')dnl
pigparm_constant(`public', `int', `PAGE_ACCESS_SN', `5', `Shared read/write access, no data saved')dnl
pigparm_constant(`public', `int', `PAGE_ACCESS_EN', `6', `Exclusive read/write access, no data saved')dnl
pigparm_constant(`public', `int', `PAGE_ACCESS_SC', `7', `Shared read/write access, no data saved, CP writeable pages')dnl
pigparm_ctors()dnl
pigparm_model_start()dnl
pigparm_model_parm(`VSMInt8', `-1', `begin_page')dnl
pigparm_model_parm(`VSMInt8', `-1', `end_page')dnl
pigparm_model_parm(`VSMInt1', `-1', `page_access_descriptor')dnl
pigparm_model_end()dnl
pigparm_endclass()dnl
pigparm_end()dnl
param_namespace_end()dnl
