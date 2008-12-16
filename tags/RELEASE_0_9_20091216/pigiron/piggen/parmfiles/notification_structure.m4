include(`pigstruct.m4')dnl \\ notification_structure.m4
param_namespace(`notification',`Asynchronous_Notification_Query_DM')dnl
pigparm_start()dnl
pigparm_import(`com.softwoehr.pigiron.access.*')dnl
pigparm_class(structure_classname, `VSMStruct',`com.softwoehr.pigiron.access.paramstructs',`dnl

/**
 * structure_classname implements the {@code structure_formal_name} from {@code associated_function}
 * @see com.softwoehr.pigiron.functions.associated_function_javaname
 */')dnl
pigparm_constant(`public', `int', `SUBSCRIPTION_TYPE_INCLUDE', `1', `Include')dnl
pigparm_constant(`public', `int', `SUBSCRIPTION_TYPE_EXCLUDE', `2', `Exclude')dnl
pigparm_constant(`public', `int', `COMMUNICATION_TYPE_TCP', `1', `TCP')dnl
pigparm_constant(`public', `int', `COMMUNICATION_TYPE_UDP', `2', `UDP')dnl
pigparm_constant(`public', `int', `ENCODING_ASCII', `1', `ASCII')dnl
pigparm_constant(`public', `int', `ENCODING_EBCDIC', `2', `EBCDIC')dnl
pigparm_ctors()dnl
pigparm_model_start()dnl
pigparm_model_parm(`CountedString', `""', `userid')dnl
pigparm_model_parm(`VSMInt1', `0', `subscription_type')dnl
pigparm_model_parm(`VSMInt1', `0', `communication_type')dnl
pigparm_model_parm(`VSMInt4', `-1', `port_number')dnl
pigparm_model_parm(`CountedString', `""', `ip_address')dnl
pigparm_model_parm(`VSMInt1', `0', `encoding')dnl
pigparm_model_parm(`CountedString', `""', `subscriber_data')dnl
pigparm_model_end()dnl
pigparm_endclass()dnl
pigparm_end()dnl
param_namespace_end()dnl
