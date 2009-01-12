include(`pigfunc.m4')dnl \\ image_device_dedicate.m4
function_namespace(`Image_Device_Dedicate')dnl
pigfunc_start()dnl
pigfunc_import(`java.io.IOException')dnl
pigfunc_import(`com.softwoehr.pigiron.access.*')dnl
pigfunc_class(function_classname,`VSMCall',`com.softwoehr.pigiron.functions',function_formal_name,`dnl

/**
 * <tt>function_formal_name</tt> VSMAPI Function
 */')dnl
pigfunc_attribute(`private', `', `String', member_name(`image_device_number'), `""', `', `The virtual device number of the device')dnl
pigfunc_attribute(`private', `', `String', member_name(`real_device_number'), `""', `', `A real device number to be dedicated or attached to the specified virtual image')dnl
pigfunc_attribute(`private', `', `int', member_name(`readonly'), `1', `', `1 if the virtual device is to be in read-only mode otherwise 0')dnl
pigfunc_ctors(`String', `image_device_number', member_name(`image_device_number'),
`String', `real_device_number', member_name(`real_device_number'),
`int', `readonly', `readonly')dnl
pigfunc_compose_input_start()dnl
pigfunc_compose_input_parm(`CountedString', `getTarget_identifier()', `target_identifier')dnl
pigfunc_compose_input_parm(`CountedString', member_getter(`image_device_number')`()', `image_device_number')dnl
pigfunc_compose_input_parm(`CountedString', member_getter(`real_device_number')`()', `real_device_number')dnl
pigfunc_compose_input_parm(`VSMInt1', `get_readonly()', `readonly')dnl
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
            System.out.println("usage: args are:\ninetaddr port user pw target image_device_number real_device_number readonly");
            System.exit(1);
        }

        System.out.println("Args are: " + argv[0] + " " + argv[1] + " " + argv[2] + " " + argv[3] + " " + argv[4] + " " + argv[5] + " " + argv[6] + " " + argv[7]);
        instance = new function_classname()(argv[0], Integer.valueOf(argv[1]).intValue(), argv[2], argv[3], argv[4],  argv[5], argv[6], Integer.valueOf(argv[7]).intValue());

        ParameterArray pA = instance.doIt();
        System.out.println("Returns from call to " + instance.getFunctionName() + ":");
        System.out.println(pA.prettyPrintAll());')dnl
pigfunc_endclass()dnl
pigfunc_end()dnl
function_namespace_end()dnl
