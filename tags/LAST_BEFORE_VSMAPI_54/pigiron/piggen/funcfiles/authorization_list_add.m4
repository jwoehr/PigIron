include(`pigfunc.m4')dnl \\ authorization_list_add.m4
function_namespace(`Authorization_List_Add')dnl
pigfunc_start()dnl
pigfunc_import(`java.io.IOException')dnl
pigfunc_import(`com.softwoehr.pigiron.access.*')dnl
pigfunc_class(function_classname,`VSMCall',`com.softwoehr.pigiron.functions',`function_formal_name',`dnl

/**
 * <tt>function_formal_name</tt> VSMAPI Function
 *
 */')dnl
dnl
pigfunc_attribute(`private', `', `String', `for_id', `""', `', `Virtual image or list of virtual images for which target_identifier will be authorized to perform the designated function')dnl
pigfunc_attribute(`private', `', `String', `function_id', `""', `', `specifies the name of the function(s) in the "Function(s)" field of the authorization file record(s) being queried')dnl
pigfunc_ctors(`String', `for_id', `for_id', String, `function_id', `function_id')dnl
pigfunc_compose_input_start()dnl
pigfunc_compose_input_parm(`CountedString', `getTarget_identifier()', `target_identifier')dnl
pigfunc_compose_input_parm(`CountedString', `get_for_id()', `for_id')dnl
pigfunc_compose_input_parm(`CountedString', `get_function_id()', `function_id')dnl
pigfunc_compose_input_end()dnl
pigfunc_compose_output_start()dnl
pigfunc_compose_output_end()dnl
pigfunc_function(`public', `static', `void', `', `main', `String[] argv', `IOException`,' VSMException', `dnl
     * You can execute the VSMAPI call from <tt>main()</tt>, try it
     * with no args to see the usage message.
     * @param argv array of commandline args
     * @throws IOException on comm error
     * @throws VSMException on internal Pigiron param marshalling error', `dnl

        function_classname instance = null;

        if (argv.length != 7) {
            System.out.println("usage: args are:\ninetaddr port user pw target_namelist for_id function_id");
            System.exit(1);
        }

        System.out.println("Args are: " + argv[0] + " " + argv[1] + " " + argv[2] + " " + argv[3] + " " + argv[4] + " " + argv[5] + " " + argv[6]);
        instance = new function_classname()(argv[0], Integer.valueOf(argv[1]).intValue(), argv[2], argv[3], argv[4], argv[5], argv[6]);

        ParameterArray pA = instance.doIt();
        System.out.println("Returns from call to " + instance.getFunctionName() + ":");
        System.out.println(pA.prettyPrintAll());')dnl
pigfunc_endclass()dnl
pigfunc_end()dnl
function_namespace_end()dnl
