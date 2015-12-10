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

/* Autogenerated Thu Dec 10 04:29:31 UTC 2015
 * by pigfunc.m4 Copyright *C* 2015 Jack J. Woehr
 * Part of the PigIron Project http://pigiron.sourceforge.net
 */
package com.softwoehr.pigiron.functions;

import java.io.IOException;
import com.softwoehr.pigiron.access.*;

/**
 * {@code Image_Volume_Space_Define_DM} VSMAPI Function
 */
public class ImageVolumeSpaceDefineDM extends VSMCall {

    /**
     * The transmitted name of the function.
     */
    public static final String FUNCTION_NAME = "Image_Volume_Space_Define_DM";

    /** Define region as specified */
    public static final int FUNCTION_TYPE_DEFINE = 1;

    /** Define region as specified and add to group */
    public static final int FUNCTION_TYPE_DEFINE_AND_ADD = 2;

    /** Define region as full volume */
    public static final int FUNCTION_TYPE_FULL_VOLUME = 3;

    /** Define region as full volume and add to group */
    public static final int FUNCTION_TYPE_FULL_VOLUME_AND_ADD = 4;

    /** Add existing region to group */
    public static final int FUNCTION_TYPE_ADD_EXISTING = 5;

    /** Unspecified */
    public static final int DEVICE_TYPE_UNSPECIFIED = 0;

    /** 3390 */
    public static final int DEVICE_TYPE_3390 = 1;

    /** 9336 */
    public static final int DEVICE_TYPE_9336 = 2;

    /** 3380 */
    public static final int DEVICE_TYPE_3380 = 3;

    /** FB-512 */
    public static final int DEVICE_TYPE_FB_512 = 4;

    /**
     *  Create an instance of the function call with important fields not instanced.
     */
    public ImageVolumeSpaceDefineDM() {
    }

    /**
     * Create an instance with the variables filled in.
     * @param hostname  VSMAPI Host DNS name
     * @param port port VSMAPI Host is listening on
     * @param userid userid executing the function
     * @param password the password
     * @param target_identifier the target of the VSMAPI function
     * @param function_type instances {@code functionType}
     * @param region_name instances {@code regionName}
     * @param image_vol_id instances {@code imageVolId}
     * @param start_cylinder instances {@code startCylinder}
     * @param size instances {@code size}
     * @param group_name instances {@code groupName}
     * @param device_type instances {@code deviceType}
     */
    public ImageVolumeSpaceDefineDM(String hostname, int port, String userid, String password , String target_identifier, int function_type, String region_name, String image_vol_id, int start_cylinder, int size, String group_name, int device_type) {
        this();
        setHostname(hostname);
        setPort(port);
        setUserid(userid);
        setPassword(password);
        setTarget_identifier(target_identifier);
        set_functionType(function_type);
        set_regionName(region_name);
        set_imageVolId(image_vol_id);
        set_startCylinder(start_cylinder);
        set_size(size);
        set_groupName(group_name);
        set_deviceType(device_type);
    }

    /** function_type */
    private int functionType = 0;

    /** The region to be defined */
    private String regionName = "";

    /** The DASD volume label */
    private String imageVolId = "";

    /** The starting point of the region */
    private int startCylinder = 0;

    /** The number of cylinders to be used by region */
    private int size = 0;

    /** The name of the group to which the region is assigned */
    private String groupName = "";

    /** The device type designation */
    private int deviceType = 0;

    /** Set the value of {@code  functionType }.
     * @param val The value to set {@code  functionType }.
     */
    public final void set_functionType(int val) {
        functionType = val;
    }

    /** Get the value of {@code  functionType }.
     * @return The value of {@code  functionType }.
     */
    public int get_functionType() {
        return functionType;
    }

    /** Set the value of {@code  regionName }.
     * @param val The value to set {@code  regionName }.
     */
    public final void set_regionName(String val) {
        regionName = val;
    }

    /** Get the value of {@code  regionName }.
     * @return The value of {@code  regionName }.
     */
    public String get_regionName() {
        return regionName;
    }

    /** Set the value of {@code  imageVolId }.
     * @param val The value to set {@code  imageVolId }.
     */
    public final void set_imageVolId(String val) {
        imageVolId = val;
    }

    /** Get the value of {@code  imageVolId }.
     * @return The value of {@code  imageVolId }.
     */
    public String get_imageVolId() {
        return imageVolId;
    }

    /** Set the value of {@code  startCylinder }.
     * @param val The value to set {@code  startCylinder }.
     */
    public final void set_startCylinder(int val) {
        startCylinder = val;
    }

    /** Get the value of {@code  startCylinder }.
     * @return The value of {@code  startCylinder }.
     */
    public int get_startCylinder() {
        return startCylinder;
    }

    /** Set the value of {@code  size }.
     * @param val The value to set {@code  size }.
     */
    public final void set_size(int val) {
        size = val;
    }

    /** Get the value of {@code  size }.
     * @return The value of {@code  size }.
     */
    public int get_size() {
        return size;
    }

    /** Set the value of {@code  groupName }.
     * @param val The value to set {@code  groupName }.
     */
    public final void set_groupName(String val) {
        groupName = val;
    }

    /** Get the value of {@code  groupName }.
     * @return The value of {@code  groupName }.
     */
    public String get_groupName() {
        return groupName;
    }

    /** Set the value of {@code  deviceType }.
     * @param val The value to set {@code  deviceType }.
     */
    public final void set_deviceType(int val) {
        deviceType = val;
    }

    /** Get the value of {@code  deviceType }.
     * @return The value of {@code  deviceType }.
     */
    public int get_deviceType() {
        return deviceType;
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
        parameterArray.add(new CountedString(getTarget_identifier(), "target_identifier"));        parameterArray.add(new VSMInt1(get_functionType(), "function_type"));        parameterArray.add(new CountedString(get_regionName(), "region_name"));        parameterArray.add(new CountedString(get_imageVolId(), "image_vol_id"));        parameterArray.add(new VSMInt4(get_startCylinder(), "start_cylinder"));        parameterArray.add(new VSMInt4(get_size(), "size"));        parameterArray.add(new CountedString(get_groupName(), "group_name"));        parameterArray.add(new VSMInt1(get_deviceType(), "device_type"));        VSMInt4 outputLength = new VSMInt4(new Long(parameterArray.totalParameterLength()).intValue(), "output_length");
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

        ImageVolumeSpaceDefineDM instance = null;

        if (argv.length != 12) {
            System.out.println("usage: args are:\ninetaddr port user pw target_id function_type region_name image_vol_id start_cylinder size group_name device_type");
            System.exit(1);
        }

        System.out.println("Args are: " + argv[0] + " " + argv[1] + " " + argv[2] + " " + argv[3] + " " + argv[4] + " " + argv[5] + " " + argv[6] + " " + argv[7] + " " + argv[8] + " " + argv[9] + " " + argv[10] + " " + argv[11]);
        instance = new ImageVolumeSpaceDefineDM(argv[0], Integer.valueOf(argv[1]).intValue(), argv[2], argv[3], argv[4],  Integer.valueOf(argv[5]).intValue(), argv[6], argv[7], Integer.valueOf(argv[8]).intValue(), Integer.valueOf(argv[9]).intValue(), argv[10], Integer.valueOf(argv[11]).intValue());

        ParameterArray pA = instance.doIt();
        System.out.println("Returns from call to " + instance.getFunctionName() + ":");
        System.out.println(pA.prettyPrintAll());
    }
}
