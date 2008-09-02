include(`pigfunc.m4')dnl \\ piggen_prototype_function.m44
function_namespace(`Piggen_Prototype_Function', `some_kinda_array' )dnl
pigfunc_start()dnl \\ function_namespace(`function_formal_name', `significant_parameter_formal_name')
pigfunc_import(`java.io.IOException')dnl
pigfunc_import(`java.util.Iterator')dnl
pigfunc_import(`com.softwoehr.pigiron.access.*')dnl \\ function_classname and function_formal_name bound in namespace
pigfunc_class(function_classname,`VSMCall',`com.softwoehr.pigiron.functions',function_formal_name,`dnl

/**
 * {@code function_formal_name} VSMAPI Function
 * @see com.softwoehr.pigiron.access.paramstructs.significant_parameter_classname
 */')dnl \\ significant_parameter_formal_name bound in namespace
dnl
pigfunc_attribute(`private', `', `String',  member_name(`name'), `"*"', `', `The name to be added to the list specified in target_identifier.')dnl
pigfunc_attribute(`private', `', `int', member_name(`some_buried_int'), `0', `', `Someone buried this int.')dnl
pigfunc_attribute(`private', `', `int', member_name(`some_buried_int4'), `0', `', `Someone buried this int4.')dnl
pigfunc_attribute(`private', `', significant_parameter_classname, significant_parameter_membername, `null', `', `An array instanced in the ctor.')dnl
pigfunc_ctors(`String', `name', member_name(`name'),
`int', `some_buried_int', member_name(`some_buried_int'),
`int', `some_buried_int4', member_name(`some_buried_int4'),
significant_parameter_classname, significant_parameter_formal_name, significant_parameter_membername)dnl
pigfunc_compose_input_start()dnl
pigfunc_compose_input_parm(`CountedString', `getTarget_identifier()', `target_identifier')dnl
pigfunc_compose_input_parm(`CountedString', member_getter(`name')`()', `name')dnl
pigfunc_compose_input_parm(`VSMInt1', member_getter(`some_buried_int')`()', `some_buried_int')dnl
pigfunc_compose_input_parm(`VSMInt4', member_getter(`some_buried_int4')`()', `some_buried_int4')dnl
pigfunc_compose_input_parm(significant_parameter_classname, significant_parameter_member_getter`()', significant_parameter_formal_name)dnl
pigfunc_compose_input_end()dnl
pigfunc_compose_output_start()dnl
pigfunc_compose_output_end()dnl
pigfunc_function(`public', `static', `void', `', `main', `String[] argv', `IOException`,' VSMException', `dnl
     * You can execute the VSMAPI call from {@code main()}, try it
     * with no args to see the usage message.
     * @param argv array of commandline args
     * @throws IOException on comm error
     * @throws VSMException on internal Pigiron param marshalling error', `dnl

        function_classname instance = null;

        if (argv.length != 8) {
            System.out.println("usage: args are:\ninetaddr port user pw target_id name buried_int");
            System.exit(1);
        }

        System.out.println("Args are: " + argv[0] + " " + argv[1] + " " + argv[2] + " " + argv[3] + " " + argv[4] + " " + argv[5] + " " + argv[6] + " " + argv[7]);
 	significant_parameter_classname spc = new significant_parameter_classname`(someval, notherval, astring, etc)';
        instance = new function_classname()(argv[0], Integer.valueOf(argv[1]).intValue(), argv[2], argv[3], argv[4], argv[5], Integer.valueOf(argv[6]).intValue(), Integer.valueOf(argv[7]).intValue(), spc);

        ParameterArray pA = instance.doIt();
        System.out.println("Returns from call to " + instance.getFunctionName() + ":");
        Iterator<VSMParm> i = pA.iterator();
        while (i.hasNext()) {
            System.out.println(i.next().prettyPrint());
        }')dnl
pigfunc_endclass()dnl
pigfunc_end()dnl
function_namespace_end()dnl
