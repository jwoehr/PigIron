include(`pigfunc.m4')dnl \\ image_disk_share.m4
function_namespace(`Image_Disk_Share')dnl
pigfunc_start()dnl
pigfunc_import(`java.io.IOException')dnl
pigfunc_import(`java.util.Iterator')dnl
pigfunc_import(`com.softwoehr.pigiron.access.*')dnl
pigfunc_class(function_classname,`VSMCall',`com.softwoehr.pigiron.functions',function_formal_name,`dnl

/**
 * function_formal_name VSMAPI Function
 * <b>Note:</b> In this version of PigIron the <tt>optional_password</tt> must always be provided
 */')dnl
pigfunc_constant(`public', `String',  `READ_WRITE_MODE_R',  `"R"', `Read-only (R/O) access')dnl
pigfunc_constant(`public', `String', `READ_WRITE_MODE_RR', `"RR"', `Read-only (R/O) access is desired even if the owner or another user has a link to the minidisk in write status')dnl
pigfunc_constant(`public', `String',  `READ_WRITE_MODE_W',  `"W"', `Write access')dnl
pigfunc_constant(`public', `String', `READ_WRITE_MODE_WR', `"WR"', `Write access is desired. Only R/O access allowed if the owner or any other user has a link to the minidisk in read or write status.')dnl
pigfunc_constant(`public', `String',  `READ_WRITE_MODE_M',  `"M"',  Multiple access is desired')dnl
pigfunc_constant(`public', `String', `READ_WRITE_MODE_MR', `"MR"', `Write or any exclusive access is allowed to the minidisk unless another user already has write access to it.')dnl
pigfunc_constant(`public', `String', `READ_WRITE_MODE_MW', `"MW"', `Write access is allowed to the disk unconditionally except for existing stable or exclusive links')dnl
pigfunc_attribute(`private', `', `String', `javaize_lc(`image_disk_number')', `null', `', `The target_image_name's virtual device address of the disk to be shared')dnl
pigfunc_attribute(`private', `', `String', `javaize_lc(`target_image_name')', `null', `', `The name of the virtual image that owns the image disk being shared')dnl
pigfunc_attribute(`private', `', `String', `javaize_lc(`target_image_disk_number')', `null', `', `The virtual device number to assign to the shared disk for target_identifier')dnl
pigfunc_attribute(`private', `', `String', `javaize_lc(`read_write_mode')', `READ_WRITE_MODE_RR', `', `The access mode requested for the disk as seen by the owner when the virtual image is logged on')dnl
pigfunc_attribute(`private', `', `String', `javaize_lc(`optional_password')', `null', `', `The password that may be required to share the disk')dnl
pigfunc_ctors(`String', `image_disk_number', javaize_lc(`image_disk_number'),
`String',`target_image_name', javaize_lc(`target_image_name'),
`String',`target_image_disk_number',javaize_lc(`target_image_disk_number'),
`String',`read_write_mode',javaize_lc(`read_write_mode'),
`String',`optional_password', javaize_lc(`optional_password'))dnl
pigfunc_compose_input_start()dnl
pigfunc_compose_input_parm(`CountedString', `getTarget_identifier()', `target_identifier')dnl
pigfunc_compose_input_parm(`CountedString', `get_`'javaize_lc(`image_disk_number')`()'', `image_disk_number')dnl
pigfunc_compose_input_parm(`CountedString', `get_`'javaize_lc(`target_image_name')`()'', `target_image_name')dnl
pigfunc_compose_input_parm(`CountedString', `get_`'javaize_lc(`target_image_disk_number')`()'', `target_image_disk_number')dnl
pigfunc_compose_input_parm(`CountedString', `get_`'javaize_lc(`read_write_mode')`()'', `read_write_mode')dnl
pigfunc_compose_input_parm(`CountedString', `get_`'javaize_lc(`optional_password')`()'', `optional_password')dnl
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

        if (argv.length != 10) {
            System.out.println("usage: args are:\ninetaddr port user pw target image_disk_number target_image_name target_image_disk_number read_write_mode optional_password");
            System.exit(1);
        }

        System.out.println("Args are: " + argv[0] + " " + argv[1] + " " + argv[2] + " " + argv[3] + " " + argv[4] + " " + argv[5] + " " + argv[6] + " " + argv[7] + " " + argv[8] + " " + argv[9]);
        instance = new function_classname()(argv[0], Integer.valueOf(argv[1]).intValue(), argv[2], argv[3], argv[4], argv[5], argv[6], argv[7], argv[8], argv[9]);

        ParameterArray pA = instance.doIt();
        System.out.println("Returns from call to " + instance.getFunctionName() + ":");
        Iterator<VSMParm> i = pA.iterator();
        while (i.hasNext()) {
            System.out.println(i.next().prettyPrint());
        }')dnl
pigfunc_endclass()dnl
pigfunc_end()dnl
function_namespace_end()dnl
