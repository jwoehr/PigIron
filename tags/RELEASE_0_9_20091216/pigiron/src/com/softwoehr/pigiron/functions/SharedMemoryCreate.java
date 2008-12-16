/*
 * Copyright (c) 2008, Jack J. Woehr jwoehr@softwoehr.com
 * PO Box 51, Golden, Colorado 80402-0051 USA
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *     * Redistributions of source code must retain the above copyright
 *         notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *         notice, this list of conditions and the following disclaimer
 *         in the documentation and/or other materials provided with the
 *         distribution.
 *     * Neither the name of the PigIron Project nor the names of its
 *         contributors may be used to endorse or promote products derived
 *         from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */

/* Autogenerated Mon Dec 15 20:44:50 UTC 2008
 * by pigfunc.m4 Copyright *C* 2008 Jack J. Woehr
 * Part of the PigIron Project http://pigiron.sourceforge.net
 */
package com.softwoehr.pigiron.functions;

import java.io.IOException;
import com.softwoehr.pigiron.access.*;

/**
 * <tt>Shared_Memory_Create</tt> VSMAPI Function
 */
public class SharedMemoryCreate extends VSMCall {

    /**
     * The transmitted name of the function.
     */
    public static final String FUNCTION_NAME = "Shared_Memory_Create";

    /** Shared read/write access */
    public static final int PAGE_ACCESS_SW = 1;

    /** Exclusive read/write access */
    public static final int PAGE_ACCESS_EW = 2;

    /** Shared read/write access */
    public static final int PAGE_ACCESS_SR = 3;

    /** Exclusive read-only access */
    public static final int PAGE_ACCESS_ER = 4;

    /** Shared read/write access, no data saved */
    public static final int PAGE_ACCESS_SN = 5;

    /** Exclusive read/write access, no data saved */
    public static final int PAGE_ACCESS_EN = 6;

    /** Shared read/write access, no data saved, CP writeable pages */
    public static final int PAGE_ACCESS_SC = 7;

    /** Unspecified */
    public static final int MEMORY_ATTRIBUTES_UNSPECIFIED = 0;

    /** Restricted saved memory */
    public static final int MEMORY_ATTRIBUTES_RSTD = 0;

    /** Unrestricted saved memory */
    public static final int MEMORY_ATTRIBUTES_UNRSTD = 0;

    /**
     *  Create an instance of the function call with important fields not instanced.
     */
    public SharedMemoryCreate() {
    }

    /**
     * Create an instance with the variables filled in.
     * @param hostname  VSMAPI Host DNS name
     * @param port port VSMAPI Host is listening on
     * @param userid userid executing the function
     * @param password the password
     * @param target_identifier the target of the VSMAPI function
     * @param memory_segment_name instances {@code memorySegmentName}
     * @param begin_page instances {@code beginPage}
     * @param end_page instances {@code endPage}
     * @param page_access_descriptor instances {@code pageAccessDescriptor}
     * @param memory_attributes instances {@code memoryAttributes}
     * @param memory_access_identifier instances {@code memoryAccessIdentifier}
     */
    public SharedMemoryCreate(String hostname, int port, String userid, String password, String target_identifier, String memory_segment_name, long begin_page, long end_page, int page_access_descriptor, int memory_attributes, String memory_access_identifier) {
        this();
        setHostname(hostname);
        setPort(port);
        setUserid(userid);
        setPassword(password);
        setTarget_identifier(target_identifier);
        set_memorySegmentName(memory_segment_name);
        set_beginPage(begin_page);
        set_endPage(end_page);
        set_pageAccessDescriptor(page_access_descriptor);
        set_memoryAttributes(memory_attributes);
        set_memoryAccessIdentifier(memory_access_identifier);
    }

    /** The name of the memory segment to create */
    private String memorySegmentName = "";

    /** The beginning page to be saved */
    private long beginPage = -1;

    /** The ending page to be saved */
    private long endPage = -1;

    /** The type of page access */
    private int pageAccessDescriptor = 0;

    /** The memory attributes */
    private int memoryAttributes = MEMORY_ATTRIBUTES_UNRSTD;

    /** The name of the image or list of images authorized to access the RSTD segment */
    private String memoryAccessIdentifier = "";

    /** Set the value of {@code  memorySegmentName }.
     * @param val The value to set {@code  memorySegmentName }.
     */
    public void set_memorySegmentName(String val) {
        memorySegmentName = val;
    }

    /** Get the value of {@code  memorySegmentName }.
     * @return The value of {@code  memorySegmentName }.
     */
    public String get_memorySegmentName() {
        return memorySegmentName;
    }

    /** Set the value of {@code  beginPage }.
     * @param val The value to set {@code  beginPage }.
     */
    public void set_beginPage(long val) {
        beginPage = val;
    }

    /** Get the value of {@code  beginPage }.
     * @return The value of {@code  beginPage }.
     */
    public long get_beginPage() {
        return beginPage;
    }

    /** Set the value of {@code  endPage }.
     * @param val The value to set {@code  endPage }.
     */
    public void set_endPage(long val) {
        endPage = val;
    }

    /** Get the value of {@code  endPage }.
     * @return The value of {@code  endPage }.
     */
    public long get_endPage() {
        return endPage;
    }

    /** Set the value of {@code  pageAccessDescriptor }.
     * @param val The value to set {@code  pageAccessDescriptor }.
     */
    public void set_pageAccessDescriptor(int val) {
        pageAccessDescriptor = val;
    }

    /** Get the value of {@code  pageAccessDescriptor }.
     * @return The value of {@code  pageAccessDescriptor }.
     */
    public int get_pageAccessDescriptor() {
        return pageAccessDescriptor;
    }

    /** Set the value of {@code  memoryAttributes }.
     * @param val The value to set {@code  memoryAttributes }.
     */
    public void set_memoryAttributes(int val) {
        memoryAttributes = val;
    }

    /** Get the value of {@code  memoryAttributes }.
     * @return The value of {@code  memoryAttributes }.
     */
    public int get_memoryAttributes() {
        return memoryAttributes;
    }

    /** Set the value of {@code  memoryAccessIdentifier }.
     * @param val The value to set {@code  memoryAccessIdentifier }.
     */
    public void set_memoryAccessIdentifier(String val) {
        memoryAccessIdentifier = val;
    }

    /** Get the value of {@code  memoryAccessIdentifier }.
     * @return The value of {@code  memoryAccessIdentifier }.
     */
    public String get_memoryAccessIdentifier() {
        return memoryAccessIdentifier;
    }

    /**
     * Marshall parameters for the VSMAPI function call.
     * "Input" as in "input to VSMAPI".
     * @return the composed input ParameterArray
     * @see #composeOutputArray()
     * @see com.softwoehr.pigiron.access.ParameterArray
     */
    protected ParameterArray composeInputArray() {
        VSMString tempString = null;
        ParameterArray parameterArray = new ParameterArray(this);
        tempString = new VSMString(getFunctionName(), getFunctionName());
        parameterArray.add(new VSMInt4(tempString.paramLength(), "function_name_length"));
        parameterArray.add(tempString);
        tempString = new VSMString(getUserid(), "authenticated_userid");
        parameterArray.add(new VSMInt4(tempString.paramLength(), "authenticated_userid_length"));
        parameterArray.add(tempString);
        tempString = new VSMString(getPassword(), "password");
        parameterArray.add(new VSMInt4(tempString.paramLength(), "password_length"));
        parameterArray.add(tempString);
        tempString = new VSMString(getTarget_identifier(), "target_identifier");
        parameterArray.add(new VSMInt4(tempString.paramLength(), "target_identifier_length"));
        parameterArray.add(tempString);
        tempString = new VSMString(get_memorySegmentName(), "memory_segment_name");
        parameterArray.add(new VSMInt4(tempString.paramLength(), "memory_segment_name_length"));
        parameterArray.add(tempString);
        parameterArray.add(new VSMInt8(get_beginPage(), "begin_page"));
        parameterArray.add(new VSMInt8(get_endPage(), "end_page"));
        parameterArray.add(new VSMInt1(get_pageAccessDescriptor(), "page_access_descriptor"));
        parameterArray.add(new VSMInt1(get_memoryAttributes(), "memory_attributes"));
        tempString = new VSMString(get_memoryAccessIdentifier(), "memory_access_identifier");
        parameterArray.add(new VSMInt4(tempString.paramLength(), "memory_access_identifier_length"));
        parameterArray.add(tempString);
        VSMInt4 outputLength = new VSMInt4(new Long(parameterArray.totalParameterLength()).intValue(), "output_length");
        parameterArray.insertElementAt(outputLength, 0);
        setInParams(parameterArray);
        return parameterArray;
    }

    /**
     * Marshall parameters for the return of the VSMAPI function call.
     * "output" as in "output from VSMAPI"
     * @return the composed output ParameterArray
     * @see #composeInputArray()
     * @see com.softwoehr.pigiron.access.ParameterArray
     */
    protected ParameterArray composeOutputArray() {
        ParameterArray parameterArray = new ParameterArray(this);
        parameterArray.add(new VSMInt4(-1, "request_id_immediate"));
        parameterArray.add(new VSMInt4(-1, "output_length"));
        parameterArray.add(new VSMInt4(-1, "request_id"));
        parameterArray.add(new VSMInt4(-1, "return_code"));
        parameterArray.add(new VSMInt4(-1, "reason_code"));
        setOutParams(parameterArray);
        return parameterArray;
    }

    /**
     * Get the formal name of the VSMAPI function.
     * @return the formal name of the VSMAPI function.
     */
    @Override
    public String getFunctionName() {
        return FUNCTION_NAME;
    }

    /**
     * You can execute the VSMAPI call from <tt>main()</tt>, try it
     * with no args to see the usage message.
     * @param argv array of commandline args
     * @throws IOException on comm error
     * @throws VSMException on internal Pigiron param marshalling error
     */
    public static void main(String[] argv) throws IOException, VSMException {

        SharedMemoryCreate instance = null;

        if (argv.length != 11) {
            System.out.println("usage: args are:\ninetaddr port user pw target memory_segment_name begin_page end_page page_access_descriptor");
            System.exit(1);
        }

        System.out.println("Args are: " + argv[0] + " " + argv[1] + " " + argv[2] + " " + argv[3] + " " + argv[4] + " " + argv[5] + " " + argv[6] + " " + argv[7] + " " + argv[8] + " " + argv[9] + " " + argv[10]);
        instance = new SharedMemoryCreate(argv[0], Integer.valueOf(argv[1]).intValue(), argv[2], argv[3], argv[4], argv[5], Long.valueOf(argv[6]).longValue(),  Long.valueOf(argv[7]).longValue(), Integer.valueOf(argv[8]).intValue(), Integer.valueOf(argv[9]).intValue(), argv[10]);
      
        ParameterArray pA = instance.doIt();
        System.out.println("Returns from call to " + instance.getFunctionName() + ":");
        System.out.println(pA.prettyPrintAll());
    }
}
