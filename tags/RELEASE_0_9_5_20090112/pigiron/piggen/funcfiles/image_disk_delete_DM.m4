include(`pigfunc.m4')dnl \\ image_disk_delete_DM.m4
function_namespace(`Image_Disk_Delete_DM')dnl
pigfunc_start()dnl
pigfunc_import(`java.io.IOException')dnl
pigfunc_import(`com.softwoehr.pigiron.access.*')dnl
pigfunc_class(function_classname,`VSMCall',`com.softwoehr.pigiron.functions',function_formal_name,`dnl

/**
 * <tt>function_formal_name</tt> VSMAPI Function
 */')dnl
pigfunc_constant(`public', `int',  `DATA_SECURITY_ERASE_UNSPECIFIED',  `0', `Unspecified (use installation default)')dnl
pigfunc_constant(`public', `int',  `DATA_SECURITY_ERASE_DO_NOT_ERASE',  `1', `Do not erase (override installation default)')dnl
pigfunc_constant(`public', `int',  `DATA_SECURITY_ERASE_ERASE',  `2', `Erase (override installation default)')dnl
pigfunc_attribute(`private', `', `String', member_name(`image_disk_number'), `""', `', `The virtual device address of the disk to be deleted')dnl
pigfunc_attribute(`private', `', `int', member_name(`data_security_erase'), `0', `', `Indicates whether to erase data from the disk(s) being released')dnl
pigfunc_ctors(`String', `image_disk_number', member_name(`image_disk_number'),
`int', `data_security_erase', member_name(`data_security_erase'))dnl
pigfunc_compose_input_start()dnl
pigfunc_compose_input_parm(`CountedString', `getTarget_identifier()', `target_identifier')dnl
pigfunc_compose_input_parm(`CountedString', member_getter(`image_disk_number')`()', `image_disk_number')dnl
pigfunc_compose_input_parm(`VSMInt1', member_getter(`data_security_erase')`()', `data_security_erase')dnl
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
            System.out.println("usage: args are:\ninetaddr port user pw target image_disk_number data_security_erase");
            System.exit(1);
        }

        System.out.println("Args are: " + argv[0] + " " + argv[1] + " " + argv[2] + " " + argv[3] + " " + argv[4] + " " + argv[5] + " " + argv[6]);
        instance = new function_classname()(argv[0], Integer.valueOf(argv[1]).intValue(), argv[2], argv[3], argv[4], argv[5], Integer.valueOf(argv[6]).intValue());

        ParameterArray pA = instance.doIt();
        System.out.println("Returns from call to " + instance.getFunctionName() + ":");
        System.out.println(pA.prettyPrintAll());')dnl
pigfunc_endclass()dnl
pigfunc_end()dnl
function_namespace_end()dnl
