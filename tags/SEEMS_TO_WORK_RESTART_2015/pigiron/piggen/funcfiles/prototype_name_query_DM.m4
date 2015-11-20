include(`pigfunc.m4')dnl \\ prototype_name_query_DM.m4
function_namespace(`Prototype_Name_Query_DM', `prototype_name_array')dnl
pigfunc_start()dnl \\ function_namespace(`function_formal_name', `significant_parameter_formal_name')
dnl pigfunc_import(`java.io.IOException')dnl
pigfunc_import(`com.softwoehr.pigiron.access.*')dnl \\ function_classname function_formal_name etc. bound in namespace
pigfunc_import(`com.softwoehr.pigiron.access.paramstructs.significant_parameter_classname')dnl \\ significant_parameter_classname bound
pigfunc_class(function_classname,`VSMCall',`com.softwoehr.pigiron.functions',function_formal_name,`dnl

/**
 * {@code function_formal_name} VSMAPI Function
 * @see com.softwoehr.pigiron.access.paramstructs.significant_parameter_classname
 */')dnl \\ significant_parameter_formal_name bound in namespace
pigfunc_ctors()dnl
pigfunc_compose_input_start()dnl
pigfunc_compose_input_parm(`CountedString', `getTarget_identifier()', `target_identifier')dnl
pigfunc_compose_input_end()dnl
pigfunc_compose_output_start()dnl
pigfunc_compose_output_parm(`VSMInt4', `-1', significant_parameter_formal_name`_length')dnl
pigfunc_compose_output_parm(significant_parameter_classname, `null', significant_parameter_formal_name)dnl
pigfunc_compose_output_end()dnl
dnl pigfunc_function(`public', `static', `void', `', `main', `String[] argv', `IOException`,' VSMException', `dnl
dnl  * You can execute the VSMAPI call from {@code main()}, try it
dnl  * with no args to see the usage message.
dnl  * @param argv array of commandline args
dnl  * @throws IOException on comm error
dnl  * @throws VSMException on internal Pigiron param marshalling error', `dnl
dnl
dnl     function_classname instance = null;
dnl
dnl     if (argv.length != 7) {
dnl         System.out.println("usage: args are:\ninetaddr port user pw target_id prototype_name initial_password initial_account_number ");
dnl         System.exit(1);
dnl     }
dnl
dnl     System.out.println("Args are: " + argv[0] + " " + argv[1] + " " + argv[2] + " " + argv[3] + " " + argv[4] + " " + argv[5] + " " + argv[6] + " " + argv[7] + " " + argv[8]);
dnl 	significant_parameter_classname spc = new significant_parameter_classname`(someval, notherval, astring, etc)';
dnl     instance = new function_classname()(argv[0], Integer.valueOf(argv[1]).intValue(), argv[2], argv[3], argv[4], argv[5], Integer.valueOf(argv[6]).intValue(), Integer.valueOf(argv[7]).intValue(), spc)dnl ;
dnl
dnl     ParameterArray pA = instance.doIt();
dnl     System.out.println("Returns from call to " + instance.getFunctionName() + ":");
dnl     Iterator<VSMParm> i = pA.iterator();
dnl     while (i.hasNext()) {
dnl         System.out.println(i.next().prettyPrint());
dnl     }')dnl
pigfunc_endclass()dnl
pigfunc_end()dnl
function_namespace_end()dnl
