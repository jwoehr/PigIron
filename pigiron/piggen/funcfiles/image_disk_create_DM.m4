include(`pigfunc.m4')dnl \\ image_disk_create_DM.m4
function_namespace(`Image_Disk_Create_DM')dnl
pigfunc_start()dnl
pigfunc_import(`java.io.IOException')dnl
pigfunc_import(`com.softwoehr.pigiron.access.*')dnl
pigfunc_class(function_classname,`VSMCall',`com.softwoehr.pigiron.functions',function_formal_name,`dnl

/**
 * <tt>function_formal_name</tt> VSMAPI Function
 */')dnl
pigfunc_constant(`public', `int',  `ALLOCATION_UNIT_SIZE_CYLINDERS', `1', `CYLINDERS')dnl
pigfunc_constant(`public', `int',  `ALLOCATION_UNIT_SIZE_BLK0512', `2', `BLK0512')dnl
pigfunc_constant(`public', `int',  `ALLOCATION_UNIT_SIZE_BLK1024', `3', `BLK1024')dnl
pigfunc_constant(`public', `int',  `ALLOCATION_UNIT_SIZE_BLK2048', `4', `BLK2048')dnl
pigfunc_constant(`public', `int',  `ALLOCATION_UNIT_SIZE_BLK4096', `5', `BLK4096')dnl
pigfunc_constant(`public', `String',  `IMAGE_DISK_MODE_R',  `"R"', `Read-only (R/O) access')dnl
pigfunc_constant(`public', `String', `IMAGE_DISK_MODE_RR', `"RR"', `Read-only (R/O) access is desired even if the owner or another user has a link to the minidisk in write status')dnl
pigfunc_constant(`public', `String',  `IMAGE_DISK_MODE_W',  `"W"', `Write access')dnl
pigfunc_constant(`public', `String', `IMAGE_DISK_MODE_WR', `"WR"', `Write access is desired. Only R/O access allowed if the owner or any other user has a link to the minidisk in read or write status.')dnl
pigfunc_constant(`public', `String',  `IMAGE_DISK_MODE_M',  `"M"',  Multiple access is desired')dnl
pigfunc_constant(`public', `String', `IMAGE_DISK_MODE_MR', `"MR"', `Write or any exclusive access is allowed to the minidisk unless another user already has write access to it.')dnl
pigfunc_constant(`public', `String', `IMAGE_DISK_MODE_MW', `"MW"', `Write access is allowed to the disk unconditionally except for existing stable or exclusive links')dnl
pigfunc_constant(`public', `int',  `IMAGE_DISK_FORMATTING_UNSPECIFIED', `0', `Unspecified')dnl
pigfunc_constant(`public', `int',  `IMAGE_DISK_FORMATTING_NONE', `1', `Unformatted')dnl
pigfunc_constant(`public', `int',  `IMAGE_DISK_FORMATTING_CMS0512', `2', `CMS formatted with 512 bytes per block')dnl
pigfunc_constant(`public', `int',  `IMAGE_DISK_FORMATTING_CMS1024', `3', `CMS formatted with 1024 bytes per block')dnl
pigfunc_constant(`public', `int',  `IMAGE_DISK_FORMATTING_CMS2048', `4', `CMS formatted with 2048 bytes per block')dnl
pigfunc_constant(`public', `int',  `IMAGE_DISK_FORMATTING_CMS4096', `5', `CMS formatted with 4096 bytes per block')dnl
pigfunc_constant(`public', `int',  `IMAGE_DISK_FORMATTING_CMS', `6', `CMS formatted with the default block size for the allocated device type')dnl
pigfunc_attribute(`private', `', `String', member_name(`image_disk_number'), `""', `', `The virtual device address of the disk to be copied')dnl
pigfunc_attribute(`private', `', `String', member_name(`image_disk_device_type'), `""', `', `The device type of the volume to which the disk is assigned')dnl
pigfunc_attribute(`private', `', `String', member_name(`image_disk_allocation_type'), `""', `', `Location or allocation')dnl
pigfunc_attribute(`private', `', `String', member_name(`allocation_area_name_or_volser'), `""', `', `Like it says')dnl
pigfunc_attribute(`private', `', `int', member_name(`allocation_unit_size'), `0', `', `Like it says')dnl
pigfunc_attribute(`private', `', `int', member_name(`image_disk_size'), `0', `', `Like it says')dnl
pigfunc_attribute(`private', `', `String', member_name(`image_disk_mode'), `""', `', `The access mode requested for the disk')dnl
pigfunc_attribute(`private', `', `int', member_name(`image_disk_formatting'), `0', `', `Like it says')dnl
pigfunc_attribute(`private', `', `String', member_name(`image_disk_label'), `""', `', `The disk label to use when formatting the new extent')dnl
pigfunc_attribute(`private', `', `String', member_name(`read_password'), `""', `', `Defines the read password that will be used for accessing the disk')dnl
pigfunc_attribute(`private', `', `String', member_name(`write_password'), `""', `', `Defines the write password that will be used for accessing the disk')dnl
pigfunc_attribute(`private', `', `String', member_name(`multi_password'), `""', `', `Defines the multi password that will be used for accessing the disk')dnl
pigfunc_ctors(`String', `image_disk_number', member_name(`image_disk_number'),
`String', `image_disk_device_type', member_name(`image_disk_device_type'),
`String', `image_disk_allocation_type', member_name(`image_disk_allocation_type'),
`String', `allocation_area_name_or_volser', member_name(`allocation_area_name_or_volser'),
`int', `allocation_unit_size', member_name(`allocation_unit_size'),
`int', `image_disk_size', member_name(`image_disk_size'),
`String', `image_disk_mode', member_name(`image_disk_mode'),
`int', `image_disk_formatting', member_name(`image_disk_formatting'),
`String', `image_disk_label', member_name(`image_disk_label'),
`String', `read_password', member_name(`read_password'),
`String', `write_password', member_name(`write_password'),
`String', `multi_password', member_name(`multi_password'))dnl
pigfunc_compose_input_start()dnl
pigfunc_compose_input_parm(`CountedString', `getTarget_identifier()', `target_identifier')dnl
pigfunc_compose_input_parm(`CountedString', member_getter(`image_disk_number')`()', `image_disk_number')dnl
pigfunc_compose_input_parm(`CountedString', member_getter(`image_disk_device_type')`()', `image_disk_device_type')dnl
pigfunc_compose_input_parm(`CountedString', member_getter(`image_disk_allocation_type')`()', `image_disk_allocation_type')dnl
pigfunc_compose_input_parm(`CountedString', member_getter(`allocation_area_name_or_volser')`()', `allocation_area_name_or_volser')dnl
pigfunc_compose_input_parm(`VSMInt1', member_getter(`allocation_unit_size')`()', `allocation_unit_size')dnl
pigfunc_compose_input_parm(`VSMInt4', member_getter(`image_disk_size')`()', `image_disk_size')dnl
pigfunc_compose_input_parm(`CountedString', member_getter(`image_disk_mode')`()', `image_disk_mode')dnl
pigfunc_compose_input_parm(`VSMInt1', member_getter(`image_disk_formatting')`()', `image_disk_formatting')dnl
pigfunc_compose_input_parm(`CountedString', member_getter(`image_disk_label')`()', `image_disk_label')dnl
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

        if (argv.length != 17) {
            System.out.println("usage: args are:\ninetaddr port user pw target image_disk_number image_disk_device_type image_disk_allocation_type allocation_area_name_or_volser allocation_unit_size image_disk_size image_disk_mode image_disk_formatting image_disk_label read_password write_password multi_password");
            System.exit(1);
        }

        System.out.println("Args are: " + argv[0] + " " + argv[1] + " " + argv[2] + " " + argv[3] + " " + argv[4] + " " + argv[5] + " " + argv[6] + " " + argv[7] + " " + argv[8] + " " + argv[9] + " " + argv[10] + " " + argv[11] + " " + argv[12] + " " + argv[0x0b] + " " + argv[14] + " " + argv[15] + " " + argv[16]);
        instance = new function_classname()(argv[0], Integer.valueOf(argv[1]).intValue(), argv[2], argv[3], argv[4], argv[5], argv[6], argv[7], argv[8], Integer.valueOf(argv[9]).intValue(), Integer.valueOf(argv[10]).intValue(), argv[11], Integer.valueOf(argv[12]).intValue(), argv[0x0b], argv[14],  argv[15], argv[16]);

        ParameterArray pA = instance.doIt();
        System.out.println("Returns from call to " + instance.getFunctionName() + ":");
        System.out.println(pA.prettyPrintAll());')dnl
pigfunc_endclass()dnl
pigfunc_end()dnl
function_namespace_end()dnl
