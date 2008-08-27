include(`pigfunc.m4')dnl \\ image_disk_create.m4
function_namespace(`Image_Disk_Create')dnl
pigfunc_start()dnl
pigfunc_import(`java.io.IOException')dnl
pigfunc_import(`java.util.Iterator')dnl
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
pigfunc_attribute(`private', `', `String', `javaize_lc(`image_disk_number')', `null', `', `The virtual device address of the disk to be added')dnl
pigfunc_attribute(`private', `', `String', `javaize_lc(`image_disk_mode')', `IMAGE_DISK_MODE_RR', `', `The access mode requested for the disk as seen by the owner when the virtual image is logged on')dnl
pigfunc_ctors(`String', `image_disk_number', javaize_lc(`image_disk_number'), `String', `image_disk_mode', javaize_lc(`image_disk_mode'))dnl
pigfunc_compose_input_start()dnl
pigfunc_compose_input_parm(`CountedString', `getTarget_identifier()', `target_identifier')dnl
pigfunc_compose_input_parm(`CountedString', `get_`'javaize_lc(`image_disk_number')`()'', `image_disk_number')dnl
pigfunc_compose_input_parm(`CountedString', `get_`'javaize_lc(`image_disk_mode')`()'', `image_disk_mode')dnl
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
            System.out.println("usage: args are:\ninetaddr port user pw target image_disk_number image_disk_mode");
            System.exit(1);
        }

        System.out.println("Args are: " + argv[0] + " " + argv[1] + " " + argv[2] + " " + argv[3] + " " + argv[4] + " " + argv[5] + " " + argv[6]);
        instance = new function_classname()(argv[0], Integer.valueOf(argv[1]).intValue(), argv[2], argv[3], argv[4], argv[5], argv[6]);

        ParameterArray pA = instance.doIt();
        System.out.println("Returns from call to " + instance.getFunctionName() + ":");
        Iterator<VSMParm> i = pA.iterator();
        while (i.hasNext()) {
            System.out.println(i.next().prettyPrint());
        }')dnl
pigfunc_endclass()dnl
pigfunc_end()dnl
function_namespace_end()dnl
