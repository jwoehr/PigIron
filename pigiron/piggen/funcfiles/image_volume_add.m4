include(`pigfunc.m4')dnl \\ image_volume_add.m4
function_namespace(`Image_Volume_Add')dnl
pigfunc_start()dnl
pigfunc_import(`java.io.IOException')dnl
pigfunc_import(`com.softwoehr.pigiron.access.*')dnl
pigfunc_class(function_classname,`VSMCall',`com.softwoehr.pigiron.functions',function_formal_name,`dnl

/**
 * <tt>function_formal_name</tt> VSMAPI Function
 *
 */')dnl
dnl
pigfunc_attribute(`private', `', `String', `member_name(`image_device_number')',   `"*"', `', `The virtual device number of the device')dnl
pigfunc_attribute(`private', `', `String', `member_name(`image_vol_id')',          `"*"', `', `The DASD volume label')dnl
pigfunc_attribute(`private', `', `String', `member_name(`system_config_name')',    `"*"', `', `File name of system configuration file - default is "SYSTEM"')dnl
pigfunc_attribute(`private', `', `String', `member_name(`system_config_type')',    `"*"', `', `File type of system configuration file - default is "CONFIG"')dnl
pigfunc_attribute(`private', `', `String', `member_name(`parm_disk_owner')',       `"*"', `', `Owner of the parm disk - default is "MAINT"')dnl
pigfunc_attribute(`private', `', `String', `member_name(`parm_disk_number')',      `"*"', `', `Number of the parm disk as defined in the VSMWORK1 directory')dnl
pigfunc_attribute(`private', `', `String', `member_name(`parm_disk_password')',    `"*"', `', `Multiwrite password for the parm disk - default is "``,''"')dnl
pigfunc_attribute(`private', `', `String', `member_name(`alt_system_config_name')',`"*"', `', `File name of the  alternative system configuration file')dnl
pigfunc_attribute(`private', `', `String', `member_name(`alt_system_config_type')',`"*"', `', `File type of the alternative system configuration file')dnl
pigfunc_attribute(`private', `', `String', `member_name(`alt_parm_disk_owner')',   `"*"', `', `Owner of the alternative parm disk')dnl
pigfunc_attribute(`private', `', `String', `member_name(`alt_parm_disk_number')',  `"*"', `', `Number of the alternative parm disk.')dnl
pigfunc_attribute(`private', `', `String', `member_name(`alt_parm_disk_password')',`"*"', `', `Multiwrite password for the alternate parm disk')dnl
pigfunc_ctors(`String', `image_device_number', member_name(`image_device_number'),
`String', `image_vol_id', member_name(`image_vol_id'),
`String', `system_config_name', member_name(`system_config_name'),
`String', `system_config_type', member_name(`system_config_type'),
`String', `parm_disk_owner', member_name(`parm_disk_owner'),
`String', `parm_disk_number', member_name(`parm_disk_number'),
`String', `parm_disk_password', member_name(`parm_disk_password'),
`String', `alt_system_config_name', member_name(`alt_system_config_name'),
`String', `alt_system_config_type', member_name(`alt_system_config_type'),
`String', `alt_parm_disk_owner', member_name(`alt_parm_disk_owner'),
`String', `alt_parm_disk_number', member_name(`alt_parm_disk_number'),
`String', `alt_parm_disk_password', member_name(`alt_parm_disk_password'))dnl
pigfunc_compose_input_start()dnl
pigfunc_compose_input_parm(`CountedString', `getTarget_identifier()', `target_identifier')dnl
pigfunc_compose_input_parm(`CountedString', member_getter(`image_device_number')`()', `image_device_number')dnl
pigfunc_compose_input_parm(`CountedString', member_getter(`image_vol_id')`()', `image_vol_id')dnl
pigfunc_compose_input_parm(`CountedString', member_getter(`system_config_name')`()', `system_config_name')dnl
pigfunc_compose_input_parm(`CountedString', member_getter(`system_config_type')`()', `system_config_type')dnl
pigfunc_compose_input_parm(`CountedString', member_getter(`parm_disk_owner')`()', `parm_disk_owner')dnl
pigfunc_compose_input_parm(`CountedString', member_getter(`parm_disk_number')`()', `parm_disk_number')dnl
pigfunc_compose_input_parm(`CountedString', member_getter(`parm_disk_password')`()', `parm_disk_password')dnl
pigfunc_compose_input_parm(`CountedString', member_getter(`alt_system_config_name')`()', `alt_system_config_name')dnl
pigfunc_compose_input_parm(`CountedString', member_getter(`alt_system_config_type')`()', `alt_system_config_type')dnl
pigfunc_compose_input_parm(`CountedString', member_getter(`alt_parm_disk_owner')`()', `alt_parm_disk_owner')dnl
pigfunc_compose_input_parm(`CountedString', member_getter(`alt_parm_disk_number')`()', `alt_parm_disk_number')dnl
pigfunc_compose_input_parm(`CountedString', member_getter(`alt_parm_disk_password')`()', `alt_parm_disk_password')dnl
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

        if (argv.length < 17) {
            System.out.println("usage: args are:\ninetaddr port user pw target");
            System.exit(1);
        }
       
        System.out.println("Args are: " + argv[0] + " " + argv[1] + " " + argv[2] + " " + argv[3] + " " + argv[4] + " " + argv[5] + " " + argv[6] + " " + argv[7] + " " + argv[8] + " " + argv[9] + " " + argv[10] + " " + argv[11] + " " + argv[12] + " " + argv[0x0d] + " " + argv[14] + " " + argv[15] + " " + argv[16]);
        instance = new function_classname()(argv[0], Integer.valueOf(argv[1]).intValue(), argv[2], argv[3], argv[4], argv[5], argv[6], argv[7], argv[8], argv[9], argv[10], argv[11], argv[12], argv[0x0d], argv[14], argv[15], argv[16]);
       
        ParameterArray pA = instance.doIt();
        System.out.println("Returns from call to " + instance.getFunctionName() + ":");
        System.out.println(pA.prettyPrintAll());')dnl
pigfunc_endclass()dnl
pigfunc_end()dnl
function_namespace_end()dnl
