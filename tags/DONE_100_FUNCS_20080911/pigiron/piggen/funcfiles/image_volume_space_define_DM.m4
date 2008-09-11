include(`pigfunc.m4')dnl \\ image_volume_space_define_DM.m4
function_namespace(`Image_Volume_Space_Define_DM')dnl
pigfunc_start()dnl \\ function_namespace(`function_formal_name', `significant_parameter_formal_name')
pigfunc_import(`java.io.IOException')dnl
pigfunc_import(`com.softwoehr.pigiron.access.*')dnl \\ function_classname and function_formal_name bound in namespace
pigfunc_class(function_classname,`VSMCall',`com.softwoehr.pigiron.functions',function_formal_name,`dnl

/**
 * {@code function_formal_name} VSMAPI Function
 */')dnl \\ significant_parameter_formal_name bound in namespace 
pigfunc_constant(`public', `int', `FUNCTION_TYPE_DEFINE', `1', `Define region as specified')dnl
pigfunc_constant(`public', `int', `FUNCTION_TYPE_DEFINE_AND_ADD', `2', `Define region as specified and add to group')dnl
pigfunc_constant(`public', `int', `FUNCTION_TYPE_FULL_VOLUME', `3', `Define region as full volume')dnl
pigfunc_constant(`public', `int', `FUNCTION_TYPE_FULL_VOLUME_AND_ADD', `4', `Define region as full volume and add to group')dnl
pigfunc_constant(`public', `int', `FUNCTION_TYPE_ADD_EXISTING', `5', `Add existing region to group')dnl
pigfunc_constant(`public', `int', `DEVICE_TYPE_UNSPECIFIED',  `0', `Unspecified')dnl
pigfunc_constant(`public', `int', `DEVICE_TYPE_3390',  `1', `3390')dnl
pigfunc_constant(`public', `int', `DEVICE_TYPE_9336',  `2', `9336')dnl
pigfunc_constant(`public', `int', `DEVICE_TYPE_3380',  `3', `3380')dnl
pigfunc_constant(`public', `int', `DEVICE_TYPE_FB_512',  `4', `FB-512')dnl
pigfunc_attribute(`private', `', `int', member_name(`function_type'), `0', `', `function_type')dnl
pigfunc_attribute(`private', `', `String',  member_name(`region_name'), `""', `', `The region to be defined')dnl
pigfunc_attribute(`private', `', `String',  member_name(`image_vol_id'), `""', `', `The DASD volume label')dnl
pigfunc_attribute(`private', `', `int', member_name(`start_cylinder'), `0', `', `The starting point of the region')dnl
pigfunc_attribute(`private', `', `int', member_name(`size'), `0', `', `The number of cylinders to be used by region')dnl
pigfunc_attribute(`private', `', `String',  member_name(`group_name'), `""', `', `The name of the group to which the region is assigned')dnl
pigfunc_attribute(`private', `', `int', member_name(`device_type'), `0', `', `The device type designation')dnl
pigfunc_ctors(`int', `function_type', member_name(`function_type'),
`String', `region_name', member_name(`region_name'),
`String', `image_vol_id', member_name(`image_vol_id'),
`int', `start_cylinder', member_name(`start_cylinder'),
`int', `size', member_name(`size'),
`String', `group_name', member_name(`group_name'),
`int', `device_type', member_name(`device_type'))dnl
pigfunc_compose_input_start()dnl
pigfunc_compose_input_parm(`CountedString', `getTarget_identifier()', `target_identifier')dnl
pigfunc_compose_input_parm(`VSMInt1', member_getter(`function_type')`()', `function_type')dnl
pigfunc_compose_input_parm(`CountedString', member_getter(`region_name')`()', `region_name')dnl
pigfunc_compose_input_parm(`CountedString', member_getter(`image_vol_id')`()', `image_vol_id')dnl
pigfunc_compose_input_parm(`VSMInt4', member_getter(`start_cylinder')`()', `start_cylinder')dnl
pigfunc_compose_input_parm(`VSMInt4', member_getter(`size')`()', `size')dnl
pigfunc_compose_input_parm(`CountedString', member_getter(`group_name')`()', `group_name')dnl
pigfunc_compose_input_parm(`VSMInt1', member_getter(`device_type')`()', `device_type')dnl
pigfunc_compose_input_end()dnl
pigfunc_compose_output_start()dnl
pigfunc_compose_output_end()dnl
pigfunc_function(`public', `static', `void', `', `main', `String[] argv', `IOException`,' VSMException', `dnl
     * You can execute the VSMAPI call from {@code main()}, try it
     * with no args to see the usage message.
     * @param argv array of commandline args
     * @throws IOException on comm error
     * @throws VSMException on internal Pigiron param marshalling error', `dnl

        function_classname instance = null;

        if (argv.length != 12) {
            System.out.println("usage: args are:\ninetaddr port user pw target_id function_type region_name image_vol_id start_cylinder size group_name device_type");
            System.exit(1);
        }

        System.out.println("Args are: " + argv[0] + " " + argv[1] + " " + argv[2] + " " + argv[3] + " " + argv[4] + " " + argv[5] + " " + argv[6] + " " + argv[7] + " " + argv[8] + " " + argv[9] + " " + argv[10] + " " + argv[11]);
        instance = new function_classname()(argv[0], Integer.valueOf(argv[1]).intValue(), argv[2], argv[3], argv[4],  Integer.valueOf(argv[5]).intValue(), argv[6], argv[7], Integer.valueOf(argv[8]).intValue(), Integer.valueOf(argv[9]).intValue(), argv[10], Integer.valueOf(argv[11]).intValue());

        ParameterArray pA = instance.doIt();
        System.out.println("Returns from call to " + instance.getFunctionName() + ":");
        System.out.println(pA.prettyPrintAll());')dnl
pigfunc_endclass()dnl
pigfunc_end()dnl
function_namespace_end()dnl
