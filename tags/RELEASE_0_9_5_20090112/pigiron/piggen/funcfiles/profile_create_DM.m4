include(`pigfunc.m4')dnl \\ profile_create_DM.m4
function_namespace(`Profile_Create_DM', `profile_record_array')dnl
pigfunc_start()dnl \\ function_namespace(`function_formal_name', `significant_parameter_formal_name')
pigfunc_import(`java.io.IOException')dnl
pigfunc_import(`com.softwoehr.pigiron.access.*')dnl \\ function_classname and function_formal_name bound in namespace
pigfunc_import(`com.softwoehr.pigiron.access.paramstructs.'significant_parameter_classname)dnl
pigfunc_class(function_classname,`VSMCall',`com.softwoehr.pigiron.functions',function_formal_name,`dnl

/**
 * {@code function_formal_name} VSMAPI 5.4 Function
 * @see com.softwoehr.pigiron.access.paramstructs.significant_parameter_classname
 * @since `<a href="http://publib.boulder.ibm.com/infocenter/zvm/v5r4/`index'.jsp">VSMAPI 5.4</a>'
 */')dnl \\ significant_parameter_formal_name bound in namespace
pigfunc_attribute(`private', `', significant_parameter_classname, significant_parameter_membername, `null', `', `An array instanced in the ctor.')dnl
pigfunc_ctors(significant_parameter_classname, significant_parameter_formal_name, significant_parameter_membername)dnl
pigfunc_compose_input_start()dnl
pigfunc_compose_input_parm(`CountedString', `getTarget_identifier()', `target_identifier')dnl
pigfunc_compose_input_parm(significant_parameter_classname, significant_parameter_member_getter`()', significant_parameter_formal_name)dnl
pigfunc_compose_input_end()dnl
pigfunc_compose_output_start()dnl
pigfunc_compose_output_end()dnl
dnl pigfunc_function(`public', `static', `void', `', `main', `String[] argv', `IOException`,' VSMException', `dnl
dnl      * You can execute the VSMAPI call from {@code main()}, try it
dnl      * with no args to see the usage message.
dnl      * @param argv array of commandline args
dnl      * @throws IOException on comm error
dnl      * @throws VSMException on internal Pigiron param marshalling error', `dnl
dnl 
dnl         function_classname instance = null;
dnl 
dnl         if (argv.length != 8) {
dnl             System.out.println("usage: args are:\ninetaddr port user pw target_id name buried_int");
dnl             System.exit(1);
dnl         }
dnl 
dnl         System.out.println("Args are: " + argv[0] + " " + argv[1] + " " + argv[2] + " " + argv[3] + " " + argv[4] + " " + argv[5] + " " + argv[6] + " " + argv[7]);
dnl  	significant_parameter_classname spc = new significant_parameter_classname`(someval, notherval, astring, etc)';
dnl         instance = new function_classname()(argv[0], Integer.valueOf(argv[1]).intValue(), argv[2], argv[3], argv[4], argv[5], Integer.valueOf(argv[6]).intValue(), Integer.valueOf(argv[7]).intValue(), spc);
dnl 
dnl         ParameterArray pA = instance.doIt();
dnl         System.out.println("Returns from call to " + instance.getFunctionName() + ":");
dnl         System.out.println(pA.prettyPrintAll());')dnl
pigfunc_endclass()dnl
pigfunc_end()dnl
function_namespace_end()dnl
