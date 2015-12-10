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
 * <tt>Image_Volume_Delete</tt> VSMAPI Function
 */
public class ImageVolumeDelete extends VSMCall {

    /**
     * The transmitted name of the function.
     */
    public static final String FUNCTION_NAME = "Image_Volume_Delete";

    /**
     *  Create an instance of the function call with important fields not instanced.
     */
    public ImageVolumeDelete() {
    }

    /**
     * Create an instance with the variables filled in.
     * @param hostname  VSMAPI Host DNS name
     * @param port port VSMAPI Host is listening on
     * @param userid userid executing the function
     * @param password the password
     * @param target_identifier the target of the VSMAPI function
     * @param image_device_number instances {@code imageDeviceNumber}
     * @param image_vol_id instances {@code imageVolId}
     * @param system_config_name instances {@code systemConfigName}
     * @param system_config_type instances {@code systemConfigType}
     * @param parm_disk_owner instances {@code parmDiskOwner}
     * @param parm_disk_number instances {@code parmDiskNumber}
     * @param parm_disk_password instances {@code parmDiskPassword}
     * @param alt_system_config_name instances {@code altSystemConfigName}
     * @param alt_system_config_type instances {@code altSystemConfigType}
     * @param alt_parm_disk_owner instances {@code altParmDiskOwner}
     * @param alt_parm_disk_number instances {@code altParmDiskNumber}
     * @param alt_parm_disk_password instances {@code altParmDiskPassword}
     */
    public ImageVolumeDelete(String hostname, int port, String userid, String password , String target_identifier, String image_device_number, String image_vol_id, String system_config_name, String system_config_type, String parm_disk_owner, String parm_disk_number, String parm_disk_password, String alt_system_config_name, String alt_system_config_type, String alt_parm_disk_owner, String alt_parm_disk_number, String alt_parm_disk_password) {
        this();
        setHostname(hostname);
        setPort(port);
        setUserid(userid);
        setPassword(password);
        setTarget_identifier(target_identifier);
        set_imageDeviceNumber(image_device_number);
        set_imageVolId(image_vol_id);
        set_systemConfigName(system_config_name);
        set_systemConfigType(system_config_type);
        set_parmDiskOwner(parm_disk_owner);
        set_parmDiskNumber(parm_disk_number);
        set_parmDiskPassword(parm_disk_password);
        set_altSystemConfigName(alt_system_config_name);
        set_altSystemConfigType(alt_system_config_type);
        set_altParmDiskOwner(alt_parm_disk_owner);
        set_altParmDiskNumber(alt_parm_disk_number);
        set_altParmDiskPassword(alt_parm_disk_password);
    }

    /** The name of the memory segment to query */
    private String imageDeviceNumber = "*";

    /** The name of the memory segment to query */
    private String imageVolId = "*";

    /** The name of the memory segment to query */
    private String systemConfigName = "*";

    /** The name of the memory segment to query */
    private String systemConfigType = "*";

    /** The name of the memory segment to query */
    private String parmDiskOwner = "*";

    /** The name of the memory segment to query */
    private String parmDiskNumber = "*";

    /** The name of the memory segment to query */
    private String parmDiskPassword = "*";

    /** The name of the memory segment to query */
    private String altSystemConfigName = "*";

    /** The name of the memory segment to query */
    private String altSystemConfigType = "*";

    /** The name of the memory segment to query */
    private String altParmDiskOwner = "*";

    /** The name of the memory segment to query */
    private String altParmDiskNumber = "*";

    /** The name of the memory segment to query */
    private String altParmDiskPassword = "*";

    /** Set the value of {@code  imageDeviceNumber }.
     * @param val The value to set {@code  imageDeviceNumber }.
     */
    public final void set_imageDeviceNumber(String val) {
        imageDeviceNumber = val;
    }

    /** Get the value of {@code  imageDeviceNumber }.
     * @return The value of {@code  imageDeviceNumber }.
     */
    public String get_imageDeviceNumber() {
        return imageDeviceNumber;
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

    /** Set the value of {@code  systemConfigName }.
     * @param val The value to set {@code  systemConfigName }.
     */
    public final void set_systemConfigName(String val) {
        systemConfigName = val;
    }

    /** Get the value of {@code  systemConfigName }.
     * @return The value of {@code  systemConfigName }.
     */
    public String get_systemConfigName() {
        return systemConfigName;
    }

    /** Set the value of {@code  systemConfigType }.
     * @param val The value to set {@code  systemConfigType }.
     */
    public final void set_systemConfigType(String val) {
        systemConfigType = val;
    }

    /** Get the value of {@code  systemConfigType }.
     * @return The value of {@code  systemConfigType }.
     */
    public String get_systemConfigType() {
        return systemConfigType;
    }

    /** Set the value of {@code  parmDiskOwner }.
     * @param val The value to set {@code  parmDiskOwner }.
     */
    public final void set_parmDiskOwner(String val) {
        parmDiskOwner = val;
    }

    /** Get the value of {@code  parmDiskOwner }.
     * @return The value of {@code  parmDiskOwner }.
     */
    public String get_parmDiskOwner() {
        return parmDiskOwner;
    }

    /** Set the value of {@code  parmDiskNumber }.
     * @param val The value to set {@code  parmDiskNumber }.
     */
    public final void set_parmDiskNumber(String val) {
        parmDiskNumber = val;
    }

    /** Get the value of {@code  parmDiskNumber }.
     * @return The value of {@code  parmDiskNumber }.
     */
    public String get_parmDiskNumber() {
        return parmDiskNumber;
    }

    /** Set the value of {@code  parmDiskPassword }.
     * @param val The value to set {@code  parmDiskPassword }.
     */
    public final void set_parmDiskPassword(String val) {
        parmDiskPassword = val;
    }

    /** Get the value of {@code  parmDiskPassword }.
     * @return The value of {@code  parmDiskPassword }.
     */
    public String get_parmDiskPassword() {
        return parmDiskPassword;
    }

    /** Set the value of {@code  altSystemConfigName }.
     * @param val The value to set {@code  altSystemConfigName }.
     */
    public final void set_altSystemConfigName(String val) {
        altSystemConfigName = val;
    }

    /** Get the value of {@code  altSystemConfigName }.
     * @return The value of {@code  altSystemConfigName }.
     */
    public String get_altSystemConfigName() {
        return altSystemConfigName;
    }

    /** Set the value of {@code  altSystemConfigType }.
     * @param val The value to set {@code  altSystemConfigType }.
     */
    public final void set_altSystemConfigType(String val) {
        altSystemConfigType = val;
    }

    /** Get the value of {@code  altSystemConfigType }.
     * @return The value of {@code  altSystemConfigType }.
     */
    public String get_altSystemConfigType() {
        return altSystemConfigType;
    }

    /** Set the value of {@code  altParmDiskOwner }.
     * @param val The value to set {@code  altParmDiskOwner }.
     */
    public final void set_altParmDiskOwner(String val) {
        altParmDiskOwner = val;
    }

    /** Get the value of {@code  altParmDiskOwner }.
     * @return The value of {@code  altParmDiskOwner }.
     */
    public String get_altParmDiskOwner() {
        return altParmDiskOwner;
    }

    /** Set the value of {@code  altParmDiskNumber }.
     * @param val The value to set {@code  altParmDiskNumber }.
     */
    public final void set_altParmDiskNumber(String val) {
        altParmDiskNumber = val;
    }

    /** Get the value of {@code  altParmDiskNumber }.
     * @return The value of {@code  altParmDiskNumber }.
     */
    public String get_altParmDiskNumber() {
        return altParmDiskNumber;
    }

    /** Set the value of {@code  altParmDiskPassword }.
     * @param val The value to set {@code  altParmDiskPassword }.
     */
    public final void set_altParmDiskPassword(String val) {
        altParmDiskPassword = val;
    }

    /** Get the value of {@code  altParmDiskPassword }.
     * @return The value of {@code  altParmDiskPassword }.
     */
    public String get_altParmDiskPassword() {
        return altParmDiskPassword;
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
        parameterArray.add(new CountedString(getTarget_identifier(), "target_identifier"));        parameterArray.add(new CountedString(get_imageDeviceNumber(), "image_device_number"));        parameterArray.add(new CountedString(get_imageVolId(), "image_vol_id"));        parameterArray.add(new CountedString(get_systemConfigName(), "system_config_name"));        parameterArray.add(new CountedString(get_systemConfigType(), "system_config_type"));        parameterArray.add(new CountedString(get_parmDiskOwner(), "parm_disk_owner"));        parameterArray.add(new CountedString(get_parmDiskNumber(), "parm_disk_number"));        parameterArray.add(new CountedString(get_parmDiskPassword(), "parm_disk_password"));        parameterArray.add(new CountedString(get_altSystemConfigName(), "alt_system_config_name"));        parameterArray.add(new CountedString(get_altSystemConfigType(), "alt_system_config_type"));        parameterArray.add(new CountedString(get_altParmDiskOwner(), "alt_parm_disk_owner"));        parameterArray.add(new CountedString(get_altParmDiskNumber(), "alt_parm_disk_number"));        parameterArray.add(new CountedString(get_altParmDiskPassword(), "alt_parm_disk_password"));        VSMInt4 outputLength = new VSMInt4(new Long(parameterArray.totalParameterLength()).intValue(), "output_length");
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
     * You can execute the VSMAPI call from <tt>main()</tt>, try it
     * with no args to see the usage message.
     * @param argv array of command-line args
     * @throws IOException on comm error
     * @throws VSMException on internal Pigiron param marshalling error
     */
    public static void main(String[] argv) throws IOException, VSMException {

        ImageVolumeDelete instance = null;

        if (argv.length < 17) {
            System.out.println("usage: args are:\ninetaddr port user pw target");
            System.exit(1);
        }
       
        System.out.println("Args are: " + argv[0] + " " + argv[1] + " " + argv[2] + " " + argv[3] + " " + argv[4] + " " + argv[5] + " " + argv[6] + " " + argv[7] + " " + argv[8] + " " + argv[9] + " " + argv[10] + " " + argv[11] + " " + argv[12] + " " + argv[0x0d] + " " + argv[14] + " " + argv[15] + " " + argv[16]);
        instance = new ImageVolumeDelete(argv[0], Integer.valueOf(argv[1]).intValue(), argv[2], argv[3], argv[4], argv[5], argv[6], argv[7], argv[8], argv[9], argv[10], argv[11], argv[12], argv[0x0d], argv[14], argv[15], argv[16]);
       
        ParameterArray pA = instance.doIt();
        System.out.println("Returns from call to " + instance.getFunctionName() + ":");
        System.out.println(pA.prettyPrintAll());
    }
}
