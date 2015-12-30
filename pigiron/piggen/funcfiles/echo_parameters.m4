include(`pigfunc.m4')dnl \\ echo_parameters.m4
function_namespace(`Echo_Parameters')dnl
pigfunc_start()dnl \\ function_namespace(`function_formal_name', `significant_parameter_formal_name')
pigfunc_import(`java.io.IOException')dnl
pigfunc_import(`com.softwoehr.pigiron.access.*')dnl \\ function_classname and function_formal_name bound in namespace
dnl pigfunc_import(`com.softwoehr.pigiron.access.paramstructs.'significant_parameter_classname)dnl
pigfunc_class(function_classname,`VSMCall',`com.softwoehr.pigiron.functions',function_formal_name,`dnl

/**
 * {@code function_formal_name} VSMAPI Example 6.3 Function
 * This is a sample from the SMAPI manual hcsl8c24.pdf Appendix B. Creating Custom APIs
 * @since `<a href="http://publibz.boulder.ibm.com/epubs/pdf/hcse2c10.pdf">VSMAPI 6.3</a>'
 */')dnl \\ significant_parameter_formal_name bound in namespace
pigfunc_attribute(`private', `', `String',  member_name(`switch_name'), `"*"', `', `Sample string parameter')dnl
pigfunc_attribute(`private', `', `int', member_name(`queue_limit'), `0', `', `Sample int4 parameter')dnl
pigfunc_ctors(`String', `switch_name', member_name(`switch_name'),
`int', `queue_limit', member_name(`queue_limit'))dnl
pigfunc_compose_input_start()dnl
pigfunc_compose_input_parm(`CountedString', `getTarget_identifier()', `target_identifier')dnl
pigfunc_compose_input_parm(`CountedString', member_getter(`switch_name')`()', `switch_name')dnl
pigfunc_compose_input_parm(`VSMInt4', member_getter(`queue_limit')`()', `queue_limit')dnl
pigfunc_compose_input_end()dnl
pigfunc_compose_output_start()dnl
pigfunc_compose_output_parm(`CountedString', `""', `switch_name')dnl
pigfunc_compose_output_parm(`VSMInt4', `-1', `queue_limit')dnl

pigfunc_compose_output_end()dnl
pigfunc_function(`public', `static', `void', `', `main', `String[] argv', `IOException`,' VSMException', `dnl
     * You can execute the VSMAPI call from {@code main()}, try it
     * with no args to see the usage message.
     * @param argv array of commandline args
     * @throws IOException on comm error
     * @throws VSMException on internal Pigiron param marshalling error', `dnl

        function_classname instance;

        if (argv.length != 7) {
            System.out.println("usage: args are:\ninetaddr port user pw target_id switch_name queue_limit");
            System.exit(1);
        }

        System.out.println("Args are: " + argv[0] + " " + argv[1] + " " + argv[2] + " " + argv[3] + " " + argv[4] + " " + argv[5] + " " + argv[6]);
        instance = new function_classname()(argv[0], Integer.valueOf(argv[1]), argv[2], argv[3], argv[4], argv[5], Integer.valueOf(argv[6]).intValue());

        ParameterArray pA = instance.doIt`()';
        System.out.println("Returns from call to " + instance.getFunctionName() + ":");
        System.out.println(pA.prettyPrintAll());')dnl
pigfunc_endclass()dnl
pigfunc_end()dnl
function_namespace_end()dnl
