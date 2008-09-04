include(`pigfunc.m4')dnl \\ virtual_channel_connection_create.m4
function_namespace(`Virtual_Channel_Connection_Create')dnl
pigfunc_start()dnl
pigfunc_import(`java.io.IOException')dnl
pigfunc_import(`java.util.Iterator')dnl
pigfunc_import(`com.softwoehr.pigiron.access.*')dnl
pigfunc_class(function_classname,`VSMCall',`com.softwoehr.pigiron.functions',function_formal_name,`dnl

/**
 * <tt>function_formal_name</tt> VSMAPI Function
 */')dnl
pigfunc_attribute(`private', `', `String', member_name(`image_device_number'), `""', `', `The virtual device number of the network device in the active virtual image')dnl
pigfunc_attribute(`private', `', `String', member_name(`coupled_image_name'), `""', `', `The virtual image name of the target virtual image that is to be connected')dnl
pigfunc_attribute(`private', `', `String', member_name(`coupled_image_device_number'), `""', `', `The virtual device number of the network device in another virtual image')dnl
pigfunc_ctors(`String', `image_device_number', member_name(`image_device_number'),
`String', `coupled_image_name', member_name(`coupled_image_name'),
`String', `coupled_image_device_number', member_name(`coupled_image_device_number'))dnl
pigfunc_compose_input_start()dnl
pigfunc_compose_input_parm(`CountedString', `getTarget_identifier()', `target_identifier')dnl
pigfunc_compose_input_parm(`CountedString', member_getter(`image_device_number')`()', `image_device_number')dnl
pigfunc_compose_input_parm(`CountedString', member_getter(`coupled_image_name')`()', `coupled_image_name')dnl
pigfunc_compose_input_parm(`CountedString', member_getter(`coupled_image_device_number')`()', `coupled_image_device_number')dnl
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

        if (argv.length != 8) {
            System.out.println("usage: args are:\ninetaddr port user pw target coupled_image_name coupled_image_device_number");
            System.exit(1);
        }

        System.out.println("Args are: " + argv[0] + " " + argv[1] + " " + argv[2] + " " + argv[3] + " " + argv[4] + " " + argv[5] + " " + argv[6] + " " + argv[7]);
        instance = new function_classname()(argv[0], Integer.valueOf(argv[1]).intValue(), argv[2], argv[3], argv[4],  argv[5], argv[6], argv[7]);

        ParameterArray pA = instance.doIt();
        System.out.println("Returns from call to " + instance.getFunctionName() + ":");
        Iterator<VSMParm> i = pA.iterator();
        while (i.hasNext()) {
            System.out.println(i.next().prettyPrint());
        }')dnl
pigfunc_endclass()dnl
pigfunc_end()dnl
function_namespace_end()dnl
