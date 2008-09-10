include(`pigfunc.m4')dnl \\ image_scsi_characteristics_define_DM.m4
function_namespace(`Image_SCSI_Characteristics_Define_DM')dnl
pigfunc_start()dnl \\ function_namespace(`function_formal_name', `significant_parameter_formal_name')
pigfunc_import(`java.io.IOException')dnl
pigfunc_import(`com.softwoehr.pigiron.access.*')dnl \\ function_classname and function_formal_name bound in namespace
pigfunc_class(function_classname,`VSMCall',`com.softwoehr.pigiron.functions',function_formal_name,`dnl

/**
 * {@code function_formal_name} VSMAPI Function
 */')dnl \\ significant_parameter_formal_name bound in namespace
pigfunc_constant(`public', `int', `SCP_DATA_TYPE_UNSPECIFIED', `0', `Unspecified')dnl
pigfunc_constant(`public', `int', `SCP_DATA_TYPE_DELETE', `1', `delete the SCP_data for the image')dnl
pigfunc_constant(`public', `int', `SCP_DATA_TYPE_EBCDIC', `2', `EBCDIC (codepage 924) data')dnl
pigfunc_constant(`public', `int', `SCP_DATA_TYPE_HEX', `3', `UTF-8 encoded hex data')dnl
pigfunc_attribute(`private', `', `String',  member_name(`boot_program'), `""', `', `The boot program number, or the keyword "DELETE" to delete the existing boot program number. If null, the boot program number will be unchanged.')dnl
pigfunc_attribute(`private', `', `String',  member_name(`BR_LBA'), `""', `', `The logical-block address of the boot record, or the keyword "DELETE" to delete the existing logical-block address. If null, the logical-block address will be unchanged.')dnl
pigfunc_attribute(`private', `', `String',  member_name(`LUN'), `""', `', `The logical unit number, or the keyword "DELETE" to delete the existing logical unit number. If null, the logical unit number will be unchanged.')dnl
pigfunc_attribute(`private', `', `String',  member_name(`port_name'), `""', `', `The port name, or the keyword "DELETE" to delete the existing port name. If null, the port name will be unchanged.')dnl
pigfunc_attribute(`private', `', `int', member_name(`SCP_data_type'), `0', `', `The type of data specified in the SCP_data parameter')dnl
pigfunc_attribute(`private', `', `String',  member_name(`SCP_data'), `""', `', `The SCP data')dnl
pigfunc_ctors(`String', `boot_program', member_name(`boot_program'),
`String', `BR_LBA', member_name(`BR_LBA'),
`String', `LUN', member_name(`LUN'),
`String', `port_name', member_name(`port_name'),
`int', `SCP_data_type', member_name(`SCP_data_type'),
`String', `SCP_data', member_name(`SCP_data'))dnl
pigfunc_compose_input_start()dnl
pigfunc_compose_input_parm(`CountedString', `getTarget_identifier()', `target_identifier')dnl
pigfunc_compose_input_parm(`CountedString', member_getter(`boot_program')`()', `boot_program')dnl
pigfunc_compose_input_parm(`CountedString', member_getter(`BR_LBA')`()', `BR_LBA')dnl
pigfunc_compose_input_parm(`CountedString', member_getter(`LUN')`()', `LUN')dnl
pigfunc_compose_input_parm(`CountedString', member_getter(`port_name')`()', `port_name')dnl
pigfunc_compose_input_parm(`VSMInt1', member_getter(`SCP_data_type')`()', `SCP_data_type')dnl
pigfunc_compose_input_parm(`CountedString', member_getter(`SCP_data')`()', `SCP_data')dnl
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

        if (argv.length != 11) {
            System.out.println("usage: args are:\ninetaddr port user pw target_id boot_program BR_LBA LUN port_name SCP_data_type SCP_data");
            System.exit(1);
        }

        System.out.println("Args are: " + argv[0] + " " + argv[1] + " " + argv[2] + " " + argv[3] + " " + argv[4] + " " + argv[5] + " " + argv[6] + " " + argv[7] + " " + argv[8] + " " + argv[9] + " " + argv[10]);
        instance = new function_classname()(argv[0], Integer.valueOf(argv[1]).intValue(), argv[2], argv[3], argv[4], argv[5], argv[6], argv[7], argv[8], Integer.valueOf(argv[9]).intValue(), argv[10]);

        ParameterArray pA = instance.doIt();
        System.out.println("Returns from call to " + instance.getFunctionName() + ":");
        System.out.println(pA.prettyPrintAll());')dnl
pigfunc_endclass()dnl
pigfunc_end()dnl
function_namespace_end()dnl
