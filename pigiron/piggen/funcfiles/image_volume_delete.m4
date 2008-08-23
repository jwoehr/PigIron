include(`pigfunc.m4')dnl \\ image_volume_delete.m4 ... not finished
pigfunc_start()dnl
pigfunc_import(`java.io.IOException')dnl
pigfunc_import(`java.util.Iterator')dnl
pigfunc_import(`com.softwoehr.pigiron.access.*')dnl
pigfunc_class(`javaize(`image_volume_delete')',`VSMCall',`com.softwoehr.pigiron.functions',`image_volume_delete',`dnl

/**
 * ImageVolumeDelete VSMAPI Fuction
 */')dnl
dnl
pigfunc_attribute(`private', `', `String', `javaize_lc(`image_device_number')',   `"*"', `', `The name of the memory segment to query')dnl
pigfunc_attribute(`private', `', `String', `javaize_lc(`image_vol_id')',          `"*"', `', `The name of the memory segment to query')dnl
pigfunc_attribute(`private', `', `String', `javaize_lc(`system_config_name')',    `"*"', `', `The name of the memory segment to query')dnl
pigfunc_attribute(`private', `', `String', `javaize_lc(`system_config_type')',    `"*"', `', `The name of the memory segment to query')dnl
pigfunc_attribute(`private', `', `String', `javaize_lc(`parm_disk_owner')',       `"*"', `', `The name of the memory segment to query')dnl
pigfunc_attribute(`private', `', `String', `javaize_lc(`parm_disk_number')',      `"*"', `', `The name of the memory segment to query')dnl
pigfunc_attribute(`private', `', `String', `javaize_lc(`parm_disk_password')',    `"*"', `', `The name of the memory segment to query')dnl
pigfunc_attribute(`private', `', `String', `javaize_lc(`alt_system_config_name')',`"*"', `', `The name of the memory segment to query')dnl
pigfunc_attribute(`private', `', `String', `javaize_lc(`alt_system_config_type')',`"*"', `', `The name of the memory segment to query')dnl
pigfunc_attribute(`private', `', `String', `javaize_lc(`alt_parm_disk_owner')',   `"*"', `', `The name of the memory segment to query')dnl
pigfunc_attribute(`private', `', `String', `javaize_lc(`alt_parm_disk_number')',  `"*"', `', `The name of the memory segment to query')dnl
pigfunc_attribute(`private', `', `String', `javaize_lc(`alt_parm_disk_password')',`"*"', `', `The name of the memory segment to query')dnl
pigfunc_ctors(`String', `image_device_number', javaize_lc(`image_device_number'),
`String', `image_vol_id', javaize_lc(`image_vol_id'),
`String', `system_config_name', javaize_lc(`system_config_name'),
`String', `system_config_type', javaize_lc(`system_config_type'),
`String', `parm_disk_owner', javaize_lc(`parm_disk_owner'),
`String', `parm_disk_number', javaize_lc(`parm_disk_number'),
`String', `parm_disk_password', javaize_lc(`parm_disk_password'),
`String', `alt_system_config_name', javaize_lc(`alt_system_config_name'),
`String', `alt_system_config_type', javaize_lc(`alt_system_config_type'),
`String', `alt_parm_disk_owner', javaize_lc(`alt_parm_disk_owner'),
`String', `alt_parm_disk_number', javaize_lc(`alt_parm_disk_number'),
`String', `alt_parm_disk_password', javaize_lc(`alt_parm_disk_password'))dnl
pigfunc_compose_input_start()dnl
pigfunc_compose_input_parm(`CountedString', `getTarget_identifier()', `target_identifier')dnl
pigfunc_compose_input_parm(`CountedString', `getTarget_identifier()', `image_device_number')dnl
pigfunc_compose_input_parm(`CountedString', `get_`'javaize_lc(`image_device_number')()', `image_device_number')dnl
pigfunc_compose_input_parm(`CountedString', `get_`'javaize_lc(`image_vol_id')()', `image_vol_id')dnl
pigfunc_compose_input_parm(`CountedString', `get_`'javaize_lc(`system_config_name')()', `system_config_name')dnl
pigfunc_compose_input_parm(`CountedString', `get_`'javaize_lc(`system_config_type')()', `system_config_type')dnl
pigfunc_compose_input_parm(`CountedString', `get_`'javaize_lc(`parm_disk_owner')()', `parm_disk_owner')dnl
pigfunc_compose_input_parm(`CountedString', `get_`'javaize_lc(`parm_disk_number')()', `parm_disk_number')dnl
pigfunc_compose_input_parm(`CountedString', `get_`'javaize_lc(`parm_disk_password')()', `parm_disk_password')dnl
pigfunc_compose_input_parm(`CountedString', `get_`'javaize_lc(`alt_system_config_name')()', `alt_system_config_name')dnl
pigfunc_compose_input_parm(`CountedString', `get_`'javaize_lc(`alt_system_config_type')()', `alt_system_config_type')dnl
pigfunc_compose_input_parm(`CountedString', `get_`'javaize_lc(`alt_parm_disk_owner')()', `alt_parm_disk_owner')dnl
pigfunc_compose_input_parm(`CountedString', `get_`'javaize_lc(`alt_parm_disk_number')()', `alt_parm_disk_number')dnl
pigfunc_compose_input_parm(`CountedString', `get_`'javaize_lc(`alt_parm_disk_password')()', `alt_parm_disk_password')dnl
pigfunc_compose_input_end()dnl
pigfunc_compose_output_start()dnl
pigfunc_compose_output_end()dnl
pigfunc_function(`public', `static', `void', `', `main', `String[] argv', `IOException`,' VSMException', `dnl
     * You can execute the VSMAPI call from <tt>main()</tt>, try it
     * with no args to see the usage message.
     * @param argv array of command-line args
     * @throws IOException on comm error
     * @throws VSMException on internal Pigiron param marshalling error', `dnl

        javaize(`image_volume_delete') instance = null;

        if (argv.length < 17) {
            System.out.println("usage: args are:\ninetaddr port user pw target");
            System.exit(1);
        }
       
        System.out.println("Args are: " + argv[0] + " " + argv[1] + " " + argv[2] + " " + argv[3] + " " + argv[4] + " " + argv[5] + " " + argv[6] + " " + argv[7] + " " + argv[8] + " " + argv[9] + " " + argv[10] + " " + argv[11] + " " + argv[12] + " " + argv[0x0d] + " " + argv[14] + " " + argv[15] + " " + argv[16]);
        instance = new javaize(`image_volume_delete')(argv[0], Integer.valueOf(argv[1]).intValue(), argv[2], argv[3], argv[4], argv[5], argv[6], argv[7], argv[8], argv[9], argv[10], argv[11], argv[12], argv[0x0d], argv[14], argv[15], argv[16]);
       
        ParameterArray pA = instance.doIt();
        System.out.println("Returns from call to " + instance.getFunctionName() + ":");
        Iterator<VSMParm> i = pA.iterator();
        while (i.hasNext()) {
            System.out.println(i.next().prettyPrint());
        }')dnl
pigfunc_endclass()dnl
pigfunc_end()dnl
