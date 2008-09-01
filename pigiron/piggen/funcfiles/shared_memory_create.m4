include(`pigfunc.m4')dnl \\ shared_memory_create.m4
function_namespace(`Shared_Memory_Create')dnl
pigfunc_start()dnl
pigfunc_import(`java.io.IOException')dnl
pigfunc_import(`java.util.Iterator')dnl
pigfunc_import(`com.softwoehr.pigiron.access.*')dnl
pigfunc_class(function_classname,`VSMCall',`com.softwoehr.pigiron.functions',function_formal_name,`dnl

/**
 * <tt>function_formal_name</tt> VSMAPI Function
 */')dnl
pigfunc_constant(`public', `int', `PAGE_ACCESS_SW', `1', `Shared read/write access')dnl
pigfunc_constant(`public', `int', `PAGE_ACCESS_EW', `2', `Exclusive read/write access')dnl
pigfunc_constant(`public', `int', `PAGE_ACCESS_SR', `3', `Shared read/write access')dnl
pigfunc_constant(`public', `int', `PAGE_ACCESS_ER', `4', `Exclusive read-only access')dnl
pigfunc_constant(`public', `int', `PAGE_ACCESS_SN', `5', `Shared read/write access, no data saved')dnl
pigfunc_constant(`public', `int', `PAGE_ACCESS_EN', `6', `Exclusive read/write access, no data saved')dnl
pigfunc_constant(`public', `int', `PAGE_ACCESS_SC', `7', `Shared read/write access, no data saved, CP writeable pages')dnl
pigfunc_attribute(`private', `', `String', javaize_lc(`memory_segment_name'), `""', `', `The name of the memory segment to create')dnl
pigfunc_attribute(`private', `', `long', javaize_lc(`begin_page'), `-1', `', `The beginning page to be saved')dnl
pigfunc_attribute(`private', `', `long', javaize_lc(`end_page'), `-1', `', `The ending page to be saved')dnl
pigfunc_attribute(`private', `', `int', javaize_lc(`page_access_descriptor'), `-1', `', `The type of page access')dnl
pigfunc_ctors(`String', `memory_segment_name', javaize_lc(`memory_segment_name'),
`long', `begin_page', javaize_lc(`begin_page'),
`long', `end_page', javaize_lc(`end_page'),
`int', `page_access_descriptor', javaize_lc(`page_access_descriptor'))dnl
pigfunc_compose_input_start()dnl
pigfunc_compose_input_parm(`CountedString', `getTarget_identifier()', `target_identifier')dnl
pigfunc_compose_input_parm(`CountedString', `get_'javaize_lc(`memory_segment_name')`()', `memory_segment_name')dnl
pigfunc_compose_input_parm(`VSMInt8', `get_'javaize_lc(`begin_page')`()', `begin_page')dnl
pigfunc_compose_input_parm(`VSMInt8', `get_'javaize_lc(`end_page')`()', `end_page')dnl
pigfunc_compose_input_parm(`VSMInt1', `get_'javaize_lc(`page_access_descriptor')`()', `page_access_descriptor')dnl
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

        if (argv.length != 9) {
            System.out.println("usage: args are:\ninetaddr port user pw target memory_segment_name begin_page end_page page_access_descriptor");
            System.exit(1);
        }

       
        System.out.println("Args are: " + argv[0] + " " + argv[1] + " " + argv[2] + " " + argv[3] + " " + argv[4] + " " + argv[5] + " " + argv[6] + " " + argv[7] + " " + argv[8]);
        instance = new function_classname()(argv[0], Integer.valueOf(argv[1]).intValue(), argv[2], argv[3], argv[4], argv[5], Long.valueOf(argv[6]).longValue(),  Long.valueOf(argv[7]).longValue(), Integer.valueOf(argv[1]).intValue());
      
        ParameterArray pA = instance.doIt();
        System.out.println("Returns from call to " + instance.getFunctionName() + ":");
        Iterator<VSMParm> i = pA.iterator();
        while (i.hasNext()) {
            System.out.println(i.next().prettyPrint());
        }')dnl
pigfunc_endclass()dnl
pigfunc_end()dnl
function_namespace_end()dnl
