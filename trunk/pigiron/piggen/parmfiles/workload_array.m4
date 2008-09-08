include(`pigarray.m4')dnl \\ workload_array.m4
param_namespace(`workload',`VMRM_Measurement_Query')dnl
pigparm_start()dnl
pigparm_import(`com.softwoehr.pigiron.access.*')dnl
pigparm_class(array_classname, `VSMArray',`com.softwoehr.pigiron.access.paramstructs',`dnl

/**
 * array_classname implements the {@code array_formal_name} from {@code associated_function}
 * @see com.softwoehr.pigiron.functions.associated_function_javaname
 */')dnl
pigparm_ctors()dnl
pigparm_model_start()dnl
pigparm_model_parm(CountedString, `""', workload)dnl
pigparm_model_end()dnl
pigparm_endclass()dnl
pigparm_end()dnl
param_namespace_end()dnl
