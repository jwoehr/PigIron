include(`pigfunc.m4')dnl \\ shared_memory_query.m4
pigfunc_start()dnl
pigfunc_import(`java.io.IOException')dnl
pigfunc_import(`java.util.Iterator')dnl
pigfunc_import(`com.softwoehr.pigiron.access.*')dnl
pigfunc_import(`com.softwoehr.pigiron.access.paramstructs.MemorySegmentArray')dnl
pigfunc_class(`SharedMemoryQuery',`VSMCall',`com.softwoehr.pigiron.functions',`shared_memory_query',`dnl

/**
 * SharedMemoryQuery VSMAPI Fuction
 * @see com.softwoehr.pigiron.access.paramstructs.MemorySegmentArray
 * @see com.softwoehr.pigiron.access.paramstructs.PageRangeArray
 */')dnl
pigfunc_attribute(`private', `', `String', `memorySegmentName', `"*"', `', `The name of the memory segment to query')dnl
pigfunc_ctors(`String', `memory_segment_name', `memorySegmentName')dnl
pigfunc_compose_input_start()dnl
pigfunc_compose_input_parm(`CountedString', `getTarget_identifier()', `target_identifier')dnl
pigfunc_compose_input_parm(`CountedString', `get_memorySegmentName()', `memory_segment_name')dnl
pigfunc_compose_input_end()dnl
pigfunc_compose_output_start()dnl
pigfunc_compose_output_parm(`VSMInt4', `-1', `memory_segment_array_length')dnl
pigfunc_compose_output_parm(`MemorySegmentArray', `null', `memory_segment_array')dnl
pigfunc_compose_output_end()dnl
pigfunc_function(`public', `static', `void', `', `main', `String[] argv', `IOException`,' VSMException', `dnl
     * You can execute the VSMAPI call from <tt>main()</tt>, try it
     * with no args to see the usage message.
     * @param argv array of command-line args
     * @throws IOException on comm error
     * @throws VSMException on internal Pigiron param marshalling error', `dnl

        SharedMemoryQuery instance = null;

        if (argv.length < 5 | argv.length > 6) {
            System.out.println("usage: args are:\ninetaddr port user pw target [memseg_name]");
            System.exit(1);
        }

        if (argv.length == 5) {
            System.out.println("Args are: " + argv[0] + " " + argv[1] + " " + argv[2] + " " + argv[3] + " " + argv[4]);
            instance = new SharedMemoryQuery(argv[0], Integer.valueOf(argv[1]).intValue(), argv[2], argv[3], argv[4], "*");
        } else { // argv.length == 6
            System.out.println("Args are: " + argv[0] + " " + argv[1] + " " + argv[2] + " " + argv[3] + " " + argv[4] + " " + argv[5]);
            instance = new SharedMemoryQuery(argv[0], Integer.valueOf(argv[1]).intValue(), argv[2], argv[3], argv[4], argv[5]);
        }

        ParameterArray pA = instance.doIt();
        System.out.println("Returns from call to " + instance.getFunctionName() + ":");
        Iterator<VSMParm> i = pA.iterator();
        while (i.hasNext()) {
            System.out.println(i.next().prettyPrint());
        }')dnl
pigfunc_endclass()dnl
pigfunc_end()dnl
