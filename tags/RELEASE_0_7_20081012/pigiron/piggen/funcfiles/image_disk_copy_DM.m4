include(`pigfunc.m4')dnl \\ image_disk_copy_DM.m4
function_namespace(`Image_Disk_Copy_DM')dnl
pigfunc_start()dnl
pigfunc_import(`java.io.IOException')dnl
pigfunc_import(`com.softwoehr.pigiron.access.*')dnl
pigfunc_class(function_classname,`VSMCall',`com.softwoehr.pigiron.functions',function_formal_name,`dnl

/**
 * <tt>function_formal_name</tt> VSMAPI Function
 */')dnl
pigfunc_constant(`public', `String',  `IMAGE_DISK_MODE_R',  `"R"', `Read-only (R/O) access')dnl
pigfunc_constant(`public', `String', `IMAGE_DISK_MODE_RR', `"RR"', `Read-only (R/O) access is desired even if the owner or another user has a link to the minidisk in write status')dnl
pigfunc_constant(`public', `String',  `IMAGE_DISK_MODE_W',  `"W"', `Write access')dnl
pigfunc_constant(`public', `String', `IMAGE_DISK_MODE_WR', `"WR"', `Write access is desired. Only R/O access allowed if the owner or any other user has a link to the minidisk in read or write status.')dnl
pigfunc_constant(`public', `String',  `IMAGE_DISK_MODE_M',  `"M"',  Multiple access is desired')dnl
pigfunc_constant(`public', `String', `IMAGE_DISK_MODE_MR', `"MR"', `Write or any exclusive access is allowed to the minidisk unless another user already has write access to it.')dnl
pigfunc_constant(`public', `String', `IMAGE_DISK_MODE_MW', `"MW"', `Write access is allowed to the disk unconditionally except for existing stable or exclusive links')dnl
pigfunc_attribute(`private', `', `String', member_name(`image_disk_number'), `""', `', `The virtual device address of the disk to be copied')dnl
pigfunc_attribute(`private', `', `String', member_name(`source_image_name'), `""', `', `The name of the virtual image that owns the image disk being copied')dnl
pigfunc_attribute(`private', `', `String', member_name(`source_image_disk_number'), `""', `', `The image disk number of the virtual image that owns the disk being copied')dnl
pigfunc_attribute(`private', `', `String', member_name(`image_disk_allocation_type'), `""', `', `Location or allocation')dnl
pigfunc_attribute(`private', `', `String', member_name(`allocation_area_name_or_volser'), `""', `', `Like it says')dnl
pigfunc_attribute(`private', `', `String', member_name(`image_disk_mode'), `""', `', `The access mode requested for the disk')dnl
pigfunc_attribute(`private', `', `String', member_name(`read_password'), `""', `', `Defines the read password that will be used for accessing the disk')dnl
pigfunc_attribute(`private', `', `String', member_name(`write_password'), `""', `', `Defines the write password that will be used for accessing the disk')dnl
pigfunc_attribute(`private', `', `String', member_name(`multi_password'), `""', `', `Defines the multi password that will be used for accessing the disk')dnl
pigfunc_ctors(`String', `image_disk_number', member_name(`image_disk_number'),
`String', `source_image_name', member_name(`source_image_name'),
`String', `source_image_disk_number', member_name(`source_image_disk_number'),
`String', `image_disk_allocation_type', member_name(`image_disk_allocation_type'),
`String', `allocation_area_name_or_volser', member_name(`allocation_area_name_or_volser'),
`String', `image_disk_mode', member_name(`image_disk_mode'),
`String', `read_password', member_name(`read_password'),
`String', `write_password', member_name(`write_password'),
`String', `multi_password', member_name(`multi_password'))dnl
pigfunc_compose_input_start()dnl
pigfunc_compose_input_parm(`CountedString', `getTarget_identifier()', `target_identifier')dnl
pigfunc_compose_input_parm(`CountedString', member_getter(`image_disk_number')`()', `image_disk_number')dnl
pigfunc_compose_input_parm(`CountedString', member_getter(`source_image_name')`()', `source_image_name')dnl
pigfunc_compose_input_parm(`CountedString', member_getter(`source_image_disk_number')`()', `source_image_disk_number')dnl
pigfunc_compose_input_parm(`CountedString', member_getter(`image_disk_allocation_type')`()', `image_disk_allocation_type')dnl
pigfunc_compose_input_parm(`CountedString', member_getter(`allocation_area_name_or_volser')`()', `allocation_area_name_or_volser')dnl
pigfunc_compose_input_parm(`CountedString', member_getter(`image_disk_mode')`()', `image_disk_mode')dnl
pigfunc_compose_input_parm(`CountedString', member_getter(`read_password')`()', `read_password')dnl
pigfunc_compose_input_parm(`CountedString', member_getter(`write_password')`()', `write_password')dnl
pigfunc_compose_input_parm(`CountedString', member_getter(`multi_password')`()', `multi_password')dnl
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

        if (argv.length != 14) {
            System.out.println("usage: args are:\ninetaddr port user pw target image_disk_number");
            System.exit(1);
        }

        System.out.println("Args are: " + argv[0] + " " + argv[1] + " " + argv[2] + " " + argv[3] + " " + argv[4] + " " + argv[5] + " " + argv[6] + " " + argv[7] + " " + argv[8] + " " + argv[9] + " " + argv[10] + " " + argv[11] + " " + argv[12] + " " + argv[13]);
        instance = new function_classname()(argv[0], Integer.valueOf(argv[1]).intValue(), argv[2], argv[3], argv[4], argv[5], argv[6], argv[7], argv[8], argv[9], argv[10], argv[11], argv[12], argv[13]);

        ParameterArray pA = instance.doIt();
        System.out.println("Returns from call to " + instance.getFunctionName() + ":");
        System.out.println(pA.prettyPrintAll());')dnl
pigfunc_endclass()dnl
pigfunc_end()dnl
function_namespace_end()dnl
