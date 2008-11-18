include(`pigfunc.m4')dnl \\ image_CPU_define_DM.m4
function_namespace(`Image_CPU_Define_DM')dnl
pigfunc_start()dnl \\ function_namespace(`function_formal_name', `significant_parameter_formal_name')
pigfunc_import(`java.io.IOException')dnl
pigfunc_import(`com.softwoehr.pigiron.access.*')dnl \\ function_classname and function_formal_name bound in namespace
pigfunc_class(function_classname,`VSMCall',`com.softwoehr.pigiron.functions',function_formal_name,`dnl

/**
 * {@code function_formal_name} VSMAPI 5.4 Function
 * @since `<a href="http://publib.boulder.ibm.com/infocenter/zvm/v5r4/`index'.jsp">VSMAPI 5.4</a>'
 */')dnl \\ significant_parameter_formal_name bound in namespace
pigfunc_constant(`public', `int', `BASE_CPU_UNSPECIFIED', `0', `Unspecified')dnl
pigfunc_constant(`public', `int', `BASE_CPU_BASE', `1', `BASE')dnl
pigfunc_constant(`public', `int', `DEDICATE_CPU_UNSPECIFIED', `0', `Unspecified')dnl
pigfunc_constant(`public', `int', `DEDICATE_CPU_NODEDICATE', `1', `NODEDICATE')dnl
pigfunc_constant(`public', `int', `DEDICATE_CPU_DEDICATE', `2', `DEDICATE')dnl
pigfunc_constant(`public', `int', `CRYPTO_UNSPECIFIED', `0', `Unspecified - no CRYPTO')dnl
pigfunc_constant(`public', `int', `CRYPTO_CRYPTO', `1', `ZAAP')dnl
pigfunc_attribute(`private', `', `String',  member_name(`cpu_address'), `""', `', `The virtual CPU address to add to the static definition of the virtual image in the hexadecimal range of 0-3F.')dnl
pigfunc_attribute(`private', `', `int', member_name(`base_cpu'), `0', `', `Whether this CPU defines the base virtual processor.')dnl
pigfunc_attribute(`private', `', `String',  member_name(`cpu_id'), `""', `', `The processor identification number to be stored in bits 8 through 31 of the CPU ID.')dnl
pigfunc_attribute(`private', `', `int', member_name(`dedicate_cpu'), `0', `', `Whether the virtual processor is to be dedicated at LOGON time to a real processor.')dnl
pigfunc_attribute(`private', `', `int', member_name(`crypto'), `0', `', `Whether the virtual Cryptographic Coprocessor Facility (CCF) should be defined automatically for the virtual CPU at LOGON time.')dnl
pigfunc_ctors(`String', `cpu_address', member_name(`cpu_address'),
`int', `base_cpu', member_name(`base_cpu'),
`String', `cpu_id', member_name(`cpu_id'),
`int', `dedicate_cpu', member_name(`dedicate_cpu'),
`int', `crypto', member_name(`crypto'))dnl
pigfunc_compose_input_start()dnl
pigfunc_compose_input_parm(`CountedString', `getTarget_identifier()', `target_identifier')dnl
pigfunc_compose_input_parm(`CountedString', member_getter(`cpu_address')`()', `cpu_address')dnl
pigfunc_compose_input_parm(`VSMInt1', member_getter(`base_cpu')`()', `base_cpu')dnl
pigfunc_compose_input_parm(`CountedString', member_getter(`cpu_id')`()', `cpu_id')dnl
pigfunc_compose_input_parm(`VSMInt1', member_getter(`dedicate_cpu')`()', `dedicate_cpu')dnl
pigfunc_compose_input_parm(`VSMInt1', member_getter(`crypto')`()', `crypto')dnl
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

        if (argv.length != 10) {
            System.out.println("usage: args are:\ninetaddr port user pw target_id cpu_address base_cpu cpu_id dedicate_cpu crypto");
            System.exit(1);
        }

        System.out.println("Args are: " + argv[0] + " " + argv[1] + " " + argv[2] + " " + argv[3] + " " + argv[4] + " " + argv[5] + " " + argv[6] + " " + argv[7] + " " + argv[8] + " " + argv[9]);
        instance = new function_classname()(argv[0], Integer.valueOf(argv[1]).intValue(), argv[2], argv[3], argv[4], argv[5], Integer.valueOf(argv[6]).intValue(), argv[7],Integer.valueOf(argv[8]).intValue(), Integer.valueOf(argv[9]).intValue());

        ParameterArray pA = instance.doIt();
        System.out.println("Returns from call to " + instance.getFunctionName() + ":");
        System.out.println(pA.prettyPrintAll());')dnl
pigfunc_endclass()dnl
pigfunc_end()dnl
function_namespace_end()dnl
