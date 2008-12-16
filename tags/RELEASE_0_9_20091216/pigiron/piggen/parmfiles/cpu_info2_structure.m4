include(`pigstruct.m4')dnl \\ cpu_info_structure2.m4
param_namespace(`cpu_info2', `Image_CPU_Query')dnl
pigparm_start()dnl
pigparm_import(`com.softwoehr.pigiron.access.*')dnl
pigparm_class(structure_classname, `VSMStruct',`com.softwoehr.pigiron.access.paramstructs',`dnl

/**
 * structure_classname implements the {@code structure_formal_name} from {@code associated_function}
 * @see com.softwoehr.pigiron.functions.associated_function_javaname
 */')dnl
pigparm_constant(`public', `int', `CPU_BASE_BASE', `1', `Base CPU')dnl
pigparm_constant(`public', `int', `CPU_BASE_NOT_BASE', `2', `Not Base CPU')dnl
pigparm_constant(`public', `int', `CPU_STATUS_STOPPED', `1', `Stopped')dnl
pigparm_constant(`public', `int', `CPU_STATUS_CHECK_STOPPED', `2', `Check-stopped')dnl
pigparm_constant(`public', `int', `CPU_STATUS_SOFT_STOPPED_OR_ACTIVE', `3', `Soft-stopped or active')dnl
pigparm_constant(`public', `int', `CPU_TYPE_CP', `1', `CP')dnl
pigparm_constant(`public', `int', `CPU_TYPE_IFL', `2', `IFL')dnl
pigparm_constant(`public', `int', `CPU_TYPE_ZAAP', `3', `ZAAP')dnl
pigparm_constant(`public', `int', `CPU_TYPE_ZIIP', `4', `ZIIP')dnl
pigparm_ctors()dnl
pigparm_model_start()dnl
pigparm_model_parm(`VSMInt4', `-1', `cpu_address')dnl
pigparm_model_parm(`CountedString', `""', `cpu_id')dnl
pigparm_model_parm(`VSMInt1', `0', `cpu_base')dnl
pigparm_model_parm(`VSMInt1', `0', `cpu_status')dnl
pigparm_model_parm(`VSMInt1', `0', `cpu_type')dnl
pigparm_model_end()dnl
pigparm_endclass()dnl
pigparm_end()dnl
param_namespace_end()dnl
