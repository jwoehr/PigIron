include(`pigfunc.m4')dnl \\ image_volume_space_remove_DM.m4
function_namespace(`Image_Volume_Space_Remove_DM')dnl
pigfunc_start()dnl \\ function_namespace(`function_formal_name', `significant_parameter_formal_name')
pigfunc_import(`java.io.IOException')dnl
pigfunc_import(`com.softwoehr.pigiron.access.*')dnl \\ function_classname and function_formal_name bound in namespace
pigfunc_class(function_classname,`VSMCall',`com.softwoehr.pigiron.functions',function_formal_name,`dnl

/**
 * {@code function_formal_name} VSMAPI Function
 */')dnl \\ significant_parameter_formal_name bound in namespace 
pigfunc_constant(`public', `int', `FUNCTION_TYPE_REMOVE_NAMED', `1', `Remove named region')dnl
pigfunc_constant(`public', `int', `FUNCTION_TYPE_REMOVE_NAMED_FR0M_GROUP', `2', `Remove named region from group')dnl
pigfunc_constant(`public', `int', `FUNCTION_TYPE_REMOVE_NAMED_FR0M_GROUPS', `3', `Remove named region from all groups')dnl
pigfunc_constant(`public', `int', `FUNCTION_TYPE_REMOVE_ALL_FR0M_VOLUME', `4', `Remove all regions from specific volume')dnl
pigfunc_constant(`public', `int', `FUNCTION_TYPE_REMOVE_ALL_FR0M_VOLUME_AND_GROUP', `5', `Remove all regions from specific volume and group')dnl
pigfunc_constant(`public', `int', `FUNCTION_TYPE_REMOVE_ALL_FR0M_VOLUME_AND_ALL_GROUPS', `6', `Remove all regions from specific volume and all groups')dnl
pigfunc_constant(`public', `int', `FUNCTION_TYPE_REMOVE_ENTIRE_GROUP', `7', `Remove entire group')dnl
pigfunc_attribute(`private', `', `int', member_name(`function_type'), `0', `', `function_type')dnl
pigfunc_attribute(`private', `', `String',  member_name(`region_name'), `""', `', `The region to be defined')dnl
pigfunc_attribute(`private', `', `String',  member_name(`image_vol_id'), `""', `', `The DASD volume label')dnl
pigfunc_attribute(`private', `', `String',  member_name(`group_name'), `""', `', `The name of the group to which the region is assigned')dnl
pigfunc_ctors(`int', `function_type', member_name(`function_type'),
`String', `region_name', member_name(`region_name'),
`String', `image_vol_id', member_name(`image_vol_id'),
`String', `group_name', member_name(`group_name'))dnl
pigfunc_compose_input_start()dnl
pigfunc_compose_input_parm(`CountedString', `getTarget_identifier()', `target_identifier')dnl
pigfunc_compose_input_parm(`VSMInt1', member_getter(`function_type')`()', `function_type')dnl
pigfunc_compose_input_parm(`CountedString', member_getter(`region_name')`()', `region_name')dnl
pigfunc_compose_input_parm(`CountedString', member_getter(`image_vol_id')`()', `image_vol_id')dnl
pigfunc_compose_input_parm(`CountedString', member_getter(`group_name')`()', `group_name')dnl
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

        if (argv.length != 9) {
            System.out.println("usage: args are:\ninetaddr port user pw target_id function_type region_name image_vol_id start_cylinder size group_name device_type");
            System.exit(1);
        }

        System.out.println("Args are: " + argv[0] + " " + argv[1] + " " + argv[2] + " " + argv[3] + " " + argv[4] + " " + argv[5] + " " + argv[6] + " " + argv[7] + " " + argv[8]);
        instance = new function_classname()(argv[0], Integer.valueOf(argv[1]).intValue(), argv[2], argv[3], argv[4],  Integer.valueOf(argv[5]).intValue(), argv[6], argv[7], argv[8]);

        ParameterArray pA = instance.doIt();
        System.out.println("Returns from call to " + instance.getFunctionName() + ":");
        System.out.println(pA.prettyPrintAll());')dnl
pigfunc_endclass()dnl
pigfunc_end()dnl
function_namespace_end()dnl
