include(`pigfunc.m4')dnl \\ event_stream_add.m4
function_namespace(`Event_Stream_Add')dnl
dnl pigfunc_import(`java.io.IOException')dnl
pigfunc_start()dnl
pigfunc_import(`com.softwoehr.pigiron.access.*')dnl
pigfunc_class(function_classname,`VSMCall',`com.softwoehr.pigiron.functions',function_formal_name,`dnl

/**
 * <tt>function_formal_name</tt> VSMAPI Function
 */')dnl
dnl
dnl pigfunc_constant(`public', `int', `SYNCHECK_AND_UPDATE', `0', `both  a syntax check and configuration update')dnl
dnl pigfunc_constant(`public', `int', `SYNCHECK_SYNTAX_ONLY', `1', `only a syntax check of the configuration is done')dnl
pigfunc_attribute(`private', `', `String', member_name(`event_info'),   `""', `', `(string,1-maxlength,charNA)dnl
Data to be added to the event stream. Note that dnl
the first 4 bytes are an int4 event_type, dnl
and that values 0-16777215 are reserved dnl
for IBM use.dnl
')dnl
dnl
pigfunc_ctors(`String', `event_info', member_name(`event_info'))dnl
pigfunc_compose_input_start()dnl
pigfunc_compose_input_parm(`CountedString', `getTarget_identifier()', `target_identifier')dnl
pigfunc_compose_input_parm(`CountedString', member_getter(`event_info')(), `event_info')dnl
pigfunc_compose_input_end()dnl
pigfunc_compose_output_start()dnl
pigfunc_compose_output_end()dnl
dnl pigfunc_function(`public', `static', `void', `', `main', `String[] argv', `IOException`,' VSMException', `dnl
dnl      * You can execute the VSMAPI call from <tt>main()</tt>, try it
dnl      * with no args to see the usage message.
dnl      * @param argv array of commandline args
dnl      * @throws IOException on comm error
dnl      * @throws VSMException on internal Pigiron param marshalling error', `dnl
dnl 
dnl         function_classname instance = null;
dnl 
dnl         if (argv.length != `8') {
dnl             System.out.println("usage: args are:\ninetaddr port user pw target_namelist configuration_file_name configuration_file_type configuration_dir_name syncheck_only update_file");
dnl             System.exit(1);
dnl         }

dnl         System.out.println("Args are: " + argv[0] + " " + argv[1] + " " + argv[2] + " " + argv[3] + " " + argv[4] + " " + argv[5] + " " + argv[6]  + " " + argv[7]);
dnl         instance = new function_classname()(argv[0], Integer.valueOf(argv[1]).intValue(), argv[2], argv[3], argv[4], argv[5], argv[6], argv[7]);
dnl 
dnl         ParameterArray pA = instance.doIt();
dnl         System.out.println("Returns from call to " + instance.getFunctionName() + ":");
dnl         System.out.println(pA.prettyPrintAll());')dnl
pigfunc_endclass()dnl
pigfunc_end()dnl
function_namespace_end()dnl
