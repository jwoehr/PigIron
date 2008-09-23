include(`pigstruct.m4')dnl \\ device_info_structure.m4
param_namespace(`device_info', `Image_Active_Configuration_Query')dnl
pigparm_start()dnl
pigparm_import(`com.softwoehr.pigiron.access.*')dnl
pigparm_class(structure_classname, `VSMStruct',`com.softwoehr.pigiron.access.paramstructs',`dnl

/**
 * structure_classname implements the {@code structure_formal_name} from {@code associated_function}
 * @see com.softwoehr.pigiron.functions.associated_function_javaname
 */')dnl
pigparm_constant(`public', `int', `DEVICE_TYPE_CONS', `1', `CONS')dnl
pigparm_constant(`public', `int', `DEVICE_TYPE_RDR', `2', `RDR')dnl
pigparm_constant(`public', `int', `DEVICE_TYPE_PUN', `3', `PUN')dnl
pigparm_constant(`public', `int', `DEVICE_TYPE_PRT', `4', `PRT')dnl
pigparm_constant(`public', `int', `DEVICE_TYPE_DASD', `5', `DASD')dnl
pigparm_ctors()dnl
pigparm_model_start()dnl
pigparm_model_parm(`VSMInt1', `0', `device_type')dnl
pigparm_model_parm(`CountedString', `""', `device_address')dnl
pigparm_model_end()dnl
pigparm_endclass()dnl
pigparm_end()dnl
param_namespace_end()dnl
