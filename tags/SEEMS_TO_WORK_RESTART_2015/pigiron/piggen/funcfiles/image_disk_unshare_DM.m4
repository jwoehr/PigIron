include(`pigfunc.m4')dnl \\ image_disk_unshare_DM.m4
function_namespace(`Image_Disk_Unshare_DM')dnl
pigfunc_start()dnl
pigfunc_import(`java.io.IOException')dnl
pigfunc_import(`com.softwoehr.pigiron.access.*')dnl
pigfunc_class(function_classname,`VSMCall',`com.softwoehr.pigiron.functions',function_formal_name,`dnl

/**
 * {@code function_formal_name} VSMAPI Function
 */')dnl
pigfunc_attribute(`private', `', `String', `member_name(`image_disk_number')', `""', `', `The target_image_name's virtual device address of the disk to be unshared')dnl
pigfunc_attribute(`private', `', `String', `member_name(`target_image_name')', `""', `', `The virtual device address of the previously-shared disk to be removed from the configuration')dnl
pigfunc_attribute(`private', `', `String', `member_name(`target_image_disk_number')', `""', `', `The virtual device number previously assigned to the shared disk for target_identifier')dnl
pigfunc_ctors(`String', `image_disk_number', member_name(`image_disk_number'),
`String',`target_image_name', member_name(`target_image_name'),
`String',`target_image_disk_number', member_name(`target_image_disk_number'))dnl
pigfunc_compose_input_start()dnl
pigfunc_compose_input_parm(`CountedString', `getTarget_identifier()', `target_identifier')dnl
pigfunc_compose_input_parm(`CountedString', member_getter(`image_disk_number')`()', `image_disk_number')dnl
pigfunc_compose_input_parm(`CountedString', member_getter(`target_image_name')`()', `target_image_name')dnl
pigfunc_compose_input_parm(`CountedString', member_getter(`target_image_disk_number')`()', `target_image_disk_number')dnl
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
            System.out.println("usage: args are:\ninetaddr port user pw target image_disk_number target_image_name target_image_disk_number");
            System.exit(1);
        }

        System.out.println("Args are: " + argv[0] + " " + argv[1] + " " + argv[2] + " " + argv[3] + " " + argv[4] + " " + argv[5] + " " + argv[6] + " " + argv[7]);
        instance = new function_classname()(argv[0], Integer.valueOf(argv[1]).intValue(), argv[2], argv[3], argv[4], argv[5], argv[6], argv[7]);

        ParameterArray pA = instance.doIt();
        System.out.println("Returns from call to " + instance.getFunctionName() + ":");
        System.out.println(pA.prettyPrintAll());')dnl
pigfunc_endclass()dnl
pigfunc_end()dnl
function_namespace_end()dnl
