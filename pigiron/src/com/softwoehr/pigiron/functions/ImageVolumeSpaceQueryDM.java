/*
 * Copyright (c) 2008, 2015, Jack J. Woehr jwoehr@softwoehr.com
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

/* Autogenerated Thu Nov 26 04:56:52 UTC 2015
 * by pigfunc.m4 Copyright *C* 2015 Jack J. Woehr
 * Part of the PigIron Project http://pigiron.sourceforge.net
 */
package com.softwoehr.pigiron.functions;

import java.io.IOException;
import com.softwoehr.pigiron.access.*;
import com.softwoehr.pigiron.access.paramstructs.RecordArray;

/**
 * {@code Image_Volume_Space_Query_DM} VSMAPI Function
 */
public class ImageVolumeSpaceQueryDM extends VSMCall {

    /**
     * The transmitted name of the function.
     */
    public static final String FUNCTION_NAME = "Image_Volume_Space_Query_DM";

    /** Query volume definition for the specified image device */
    public static final int QUERY_TYPE_DEFINITION = 1;

    /** Query amount of free space available on the specified image device */
    public static final int QUERY_TYPE_FREE = 2;

    /** Query amount of space used on the specified image device */
    public static final int QUERY_TYPE_USED = 3;

    /** Query specified volume */
    public static final int ENTRY_TYPE_VOLUME = 1;

    /** Query specified region */
    public static final int ENTRY_TYPE_REGION = 2;

    /** Query specified group */
    public static final int ENTRY_TYPE_GROUP = 3;

    /**
     *  Create an instance of the function call with important fields not instanced.
     */
    public ImageVolumeSpaceQueryDM() {
    }

    /**
     * Create an instance with the variables filled in.
     * @param hostname  VSMAPI Host DNS name
     * @param port port VSMAPI Host is listening on
     * @param userid userid executing the function
     * @param password the password
     * @param target_identifier the target of the VSMAPI function
     * @param query_type instances {@code queryType}
     * @param entry_type instances {@code entryType}
     * @param entry_names instances {@code entryNames}
     */
    public ImageVolumeSpaceQueryDM(String hostname, int port, String userid, String password , String target_identifier, int query_type, int entry_type, String entry_names) {
        this();
        setHostname(hostname);
        setPort(port);
        setUserid(userid);
        setPassword(password);
        setTarget_identifier(target_identifier);
        set_queryType(query_type);
        set_entryType(entry_type);
        set_entryNames(entry_names);
    }

    /** query_type */
    private int queryType = 0;

    /** entry_type */
    private int entryType = 0;

    /** Names of groups, regions or volumes to be queried */
    private String entryNames = "";

    /** Set the value of {@code  queryType }.
     * @param val The value to set {@code  queryType }.
     */
    public final void set_queryType(int val) {
        queryType = val;
    }

    /** Get the value of {@code  queryType }.
     * @return The value of {@code  queryType }.
     */
    public int get_queryType() {
        return queryType;
    }

    /** Set the value of {@code  entryType }.
     * @param val The value to set {@code  entryType }.
     */
    public final void set_entryType(int val) {
        entryType = val;
    }

    /** Get the value of {@code  entryType }.
     * @return The value of {@code  entryType }.
     */
    public int get_entryType() {
        return entryType;
    }

    /** Set the value of {@code  entryNames }.
     * @param val The value to set {@code  entryNames }.
     */
    public final void set_entryNames(String val) {
        entryNames = val;
    }

    /** Get the value of {@code  entryNames }.
     * @return The value of {@code  entryNames }.
     */
    public String get_entryNames() {
        return entryNames;
    }

    /**
     * Marshall parameters for the VSMAPI function call.
     * "Input" as in "input to VSMAPI".
     * @return the composed input ParameterArray
     * @see #composeOutputArray()
     * @see com.softwoehr.pigiron.access.ParameterArray
     */
    protected ParameterArray composeInputArray() {
        VSMString tempString;
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
        parameterArray.add(new VSMInt1(get_queryType(), "query_type"));
        parameterArray.add(new VSMInt1(get_entryType(), "entry_type"));
        tempString = new VSMString(get_entryNames(), "entry_names");
        parameterArray.add(new VSMInt4(tempString.paramLength(), "entry_names_length"));
        parameterArray.add(tempString);
        VSMInt4 outputLength = new VSMInt4(new Long(parameterArray.totalParameterLength()).intValue(), "output_length");
        parameterArray.add(0,outputLength);
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
        parameterArray.add(new VSMInt4(-1, "record_array_length"));
        parameterArray.add(RecordArray.modelArray("record_array"));
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
     * You can execute the VSMAPI call from {@code main()}, try it
     * with no args to see the usage message.
     * @param argv array of commandline args
     * @throws IOException on comm error
     * @throws VSMException on internal Pigiron param marshalling error
     */
    public static void main(String[] argv) throws IOException, VSMException {

        ImageVolumeSpaceQueryDM instance = null;

        if (argv.length != 8) {
            System.out.println("usage: args are:\ninetaddr port user pw target_id function_type region_name image_vol_id start_cylinder size group_name device_type");
            System.exit(1);
        }

        System.out.println("Args are: " + argv[0] + " " + argv[1] + " " + argv[2] + " " + argv[3] + " " + argv[4] + " " + argv[5] + " " + argv[6] + " " + argv[7]);
        instance = new ImageVolumeSpaceQueryDM(argv[0], Integer.valueOf(argv[1]).intValue(), argv[2], argv[3], argv[4],  Integer.valueOf(argv[5]).intValue(), Integer.valueOf(argv[6]).intValue(), argv[7]);

        ParameterArray pA = instance.doIt();
        System.out.println("Returns from call to " + instance.getFunctionName() + ":");
        System.out.println(pA.prettyPrintAll());
    }
}
