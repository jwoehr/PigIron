include(`pigstruct.m4')dnl \\ auth_record_structure.m4
param_namespace(`auth_record', `Authorization_List_Query')dnl
pigparm_start()dnl
pigparm_import(`com.softwoehr.pigiron.access.*')dnl
pigparm_class(structure_classname, `VSMStruct',`com.softwoehr.pigiron.access.paramstructs',`dnl

/**
 * structure_classname implements the {@code structure_formal_name} from {@code associated_function}
 * @see com.softwoehr.pigiron.functions.associated_function_javaname
 */')dnl
pigparm_constant(`public', `int', `REQUESTING_LIST_INDICATOR_SINGLE', `0', `single userid')dnl
pigparm_constant(`public', `int', `REQUESTING_LIST_INDICATOR_LIST', `1', `list of userids')dnl
pigparm_constant(`public', `int', `FOR_LIST_INDICATOR_SINGLE', `0', `single userid')dnl
pigparm_constant(`public', `int', `FOR_LIST_INDICATOR_LIST', `1', `list of userids')dnl
pigparm_constant(`public', `int', `FUNCTION_LIST_INDICATOR_SINGLE', `0', `single userid')dnl
pigparm_constant(`public', `int', `FUNCTION_LIST_INDICATOR_LIST', `1', `list of userids')dnl
pigparm_ctors()dnl
pigparm_model_start()dnl
pigparm_model_parm(`CountedString', `""', `requesting_userid')dnl
pigparm_model_parm(`VSMInt1', `0', `requesting_list_indicator')dnl
pigparm_model_parm(`CountedString', `""', `for_userid')dnl
pigparm_model_parm(`VSMInt1', `0', `for_list_indicator')dnl
pigparm_model_parm(`CountedString', `""', `function_name')dnl
pigparm_model_parm(`VSMInt1', `0', `function_list_indicator')dnl
pigparm_model_end()dnl
pigparm_endclass()dnl
pigparm_end()dnl
param_namespace_end()dnl
