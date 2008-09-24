include(`pigarray.m4')dnl \\ authorized_users_array.m4
param_namespace(`authorized_users',`Virtual_Network_LAN_Access_Query')dnl
pigparm_start()dnl
pigparm_import(`com.softwoehr.pigiron.access.*')dnl
pigparm_class(array_classname, `VSMAsciiZArray',`com.softwoehr.pigiron.access.paramstructs',`dnl

/**
 * array_classname implements the {@code array_formal_name} from {@code associated_function}
 * @see com.softwoehr.pigiron.functions.associated_function_javaname
 * @since `<a href="http://publib.boulder.ibm.com/infocenter/zvm/v5r4/`index'.jsp">VSMAPI 5.4</a>'
 */')dnl
pigparm_ctors()dnl
pigparm_model_start()dnl
pigparm_model_parm(`VSMAsciiZ', `null', `authorized_user_record')dnl
pigparm_model_end()dnl
pigparm_endclass()dnl
pigparm_end()dnl
param_namespace_end()dnl
