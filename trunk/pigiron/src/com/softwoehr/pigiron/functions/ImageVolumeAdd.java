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

/* Autogenerated Fri Aug 22 09:35:54 GMT 2008
 * by pigfunc.m4 Copyright *C* 2008 Jack J. Woehr
 * Part of the PigIron Project http://pigiron.sourceforge.net
 */
package com.softwoehr.pigiron.functions;

import com.softwoehr.pigiron.access.*;

/**
 * ImageVolumeAdd VSMAPI Fuction
 */
public class ImageVolumeAdd extends VSMCall {

    /**
     * The transmitted name of the function.
     */
    public static final String FUNCTION_NAME = "image_volume_add";

    /**
     *  Create an instance of the function call with important fields not instanced.
     */
    public ImageVolumeAdd() {
    }

    /**
     * Create an instance with the variables filled in.
     * @param hostname  VSMAPI Host DNS name
     * @param port port VSMAPI Host is listening on
     * @param userid userid executing the function
     * @param password the password
     * @param target_identifier the target of the VSMAPI function
     * @param image_device_number instances imageDeviceNumber
     * @param image_vol_id instances imageVolId
     * @param system_config_name instances systemConfigName
     * @param system_config_type instances systemConfigType
     * @param parm_disk_owner instances parmDiskOwner
     * @param parm_disk_number instances parmDiskNumber
     * @param parm_disk_password instances parmDiskPassword
     * @param alt_system_config_name instances altSystemConfigName
     * @param alt_system_config_type instances altSystemConfigType
     * @param alt_parm_disk_owner instances altParmDiskOwner
     * @param alt_parm_disk_number instances altParmDiskNumber
     * @param alt_parm_disk_password instances altParmDiskPassword
     */
    public ImageVolumeAdd(String hostname, int port, String userid, String password, String target_identifier, String image_device_number, String image_vol_id, String system_config_name, String system_config_type, String parm_disk_owner, String parm_disk_number, String parm_disk_password, String alt_system_config_name, String alt_system_config_type, String alt_parm_disk_owner, String alt_parm_disk_number, String alt_parm_disk_password) {
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

    /** Set the value of private String imageDeviceNumber .
     * @param val The value to set private String imageDeviceNumber .
     */
    public void set_imageDeviceNumber(String val) {
        imageDeviceNumber = val;
    }

    /** Get the value of private String imageDeviceNumber .
     * @return The value of private String imageDeviceNumber .
     */
    public String get_imageDeviceNumber() {
        return imageDeviceNumber;
    }

    /** Set the value of private String imageVolId .
     * @param val The value to set private String imageVolId .
     */
    public void set_imageVolId(String val) {
        imageVolId = val;
    }

    /** Get the value of private String imageVolId .
     * @return The value of private String imageVolId .
     */
    public String get_imageVolId() {
        return imageVolId;
    }

    /** Set the value of private String systemConfigName .
     * @param val The value to set private String systemConfigName .
     */
    public void set_systemConfigName(String val) {
        systemConfigName = val;
    }

    /** Get the value of private String systemConfigName .
     * @return The value of private String systemConfigName .
     */
    public String get_systemConfigName() {
        return systemConfigName;
    }

    /** Set the value of private String systemConfigType .
     * @param val The value to set private String systemConfigType .
     */
    public void set_systemConfigType(String val) {
        systemConfigType = val;
    }

    /** Get the value of private String systemConfigType .
     * @return The value of private String systemConfigType .
     */
    public String get_systemConfigType() {
        return systemConfigType;
    }

    /** Set the value of private String parmDiskOwner .
     * @param val The value to set private String parmDiskOwner .
     */
    public void set_parmDiskOwner(String val) {
        parmDiskOwner = val;
    }

    /** Get the value of private String parmDiskOwner .
     * @return The value of private String parmDiskOwner .
     */
    public String get_parmDiskOwner() {
        return parmDiskOwner;
    }

    /** Set the value of private String parmDiskNumber .
     * @param val The value to set private String parmDiskNumber .
     */
    public void set_parmDiskNumber(String val) {
        parmDiskNumber = val;
    }

    /** Get the value of private String parmDiskNumber .
     * @return The value of private String parmDiskNumber .
     */
    public String get_parmDiskNumber() {
        return parmDiskNumber;
    }

    /** Set the value of private String parmDiskPassword .
     * @param val The value to set private String parmDiskPassword .
     */
    public void set_parmDiskPassword(String val) {
        parmDiskPassword = val;
    }

    /** Get the value of private String parmDiskPassword .
     * @return The value of private String parmDiskPassword .
     */
    public String get_parmDiskPassword() {
        return parmDiskPassword;
    }

    /** Set the value of private String altSystemConfigName .
     * @param val The value to set private String altSystemConfigName .
     */
    public void set_altSystemConfigName(String val) {
        altSystemConfigName = val;
    }

    /** Get the value of private String altSystemConfigName .
     * @return The value of private String altSystemConfigName .
     */
    public String get_altSystemConfigName() {
        return altSystemConfigName;
    }

    /** Set the value of private String altSystemConfigType .
     * @param val The value to set private String altSystemConfigType .
     */
    public void set_altSystemConfigType(String val) {
        altSystemConfigType = val;
    }

    /** Get the value of private String altSystemConfigType .
     * @return The value of private String altSystemConfigType .
     */
    public String get_altSystemConfigType() {
        return altSystemConfigType;
    }

    /** Set the value of private String altParmDiskOwner .
     * @param val The value to set private String altParmDiskOwner .
     */
    public void set_altParmDiskOwner(String val) {
        altParmDiskOwner = val;
    }

    /** Get the value of private String altParmDiskOwner .
     * @return The value of private String altParmDiskOwner .
     */
    public String get_altParmDiskOwner() {
        return altParmDiskOwner;
    }

    /** Set the value of private String altParmDiskNumber .
     * @param val The value to set private String altParmDiskNumber .
     */
    public void set_altParmDiskNumber(String val) {
        altParmDiskNumber = val;
    }

    /** Get the value of private String altParmDiskNumber .
     * @return The value of private String altParmDiskNumber .
     */
    public String get_altParmDiskNumber() {
        return altParmDiskNumber;
    }

    /** Set the value of private String altParmDiskPassword .
     * @param val The value to set private String altParmDiskPassword .
     */
    public void set_altParmDiskPassword(String val) {
        altParmDiskPassword = val;
    }

    /** Get the value of private String altParmDiskPassword .
     * @return The value of private String altParmDiskPassword .
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
        VSMString tempString = null;
        ParameterArray parameterArray = new ParameterArray();
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
        tempString = new VSMString(getTarget_identifier(), "image_device_number");
        parameterArray.add(new VSMInt4(tempString.paramLength(), "image_device_number_length"));
        parameterArray.add(tempString);
        tempString = new VSMString(get_imageDeviceNumber(), "image_device_number");
        parameterArray.add(new VSMInt4(tempString.paramLength(), "image_device_number_length"));
        parameterArray.add(tempString);
        tempString = new VSMString(get_imageVolId(), "image_vol_id");
        parameterArray.add(new VSMInt4(tempString.paramLength(), "image_vol_id_length"));
        parameterArray.add(tempString);
        tempString = new VSMString(get_systemConfigName(), "system_config_name");
        parameterArray.add(new VSMInt4(tempString.paramLength(), "system_config_name_length"));
        parameterArray.add(tempString);
        tempString = new VSMString(get_systemConfigType(), "system_config_type");
        parameterArray.add(new VSMInt4(tempString.paramLength(), "system_config_type_length"));
        parameterArray.add(tempString);
        tempString = new VSMString(get_parmDiskOwner(), "parm_disk_owner");
        parameterArray.add(new VSMInt4(tempString.paramLength(), "parm_disk_owner_length"));
        parameterArray.add(tempString);
        tempString = new VSMString(get_parmDiskNumber(), "parm_disk_number");
        parameterArray.add(new VSMInt4(tempString.paramLength(), "parm_disk_number_length"));
        parameterArray.add(tempString);
        tempString = new VSMString(get_parmDiskPassword(), "parm_disk_password");
        parameterArray.add(new VSMInt4(tempString.paramLength(), "parm_disk_password_length"));
        parameterArray.add(tempString);
        tempString = new VSMString(get_altSystemConfigName(), "alt_system_config_name");
        parameterArray.add(new VSMInt4(tempString.paramLength(), "alt_system_config_name_length"));
        parameterArray.add(tempString);
        tempString = new VSMString(get_altSystemConfigType(), "alt_system_config_type");
        parameterArray.add(new VSMInt4(tempString.paramLength(), "alt_system_config_type_length"));
        parameterArray.add(tempString);
        tempString = new VSMString(get_altParmDiskOwner(), "alt_parm_disk_owner");
        parameterArray.add(new VSMInt4(tempString.paramLength(), "alt_parm_disk_owner_length"));
        parameterArray.add(tempString);
        tempString = new VSMString(get_altParmDiskNumber(), "alt_parm_disk_number");
        parameterArray.add(new VSMInt4(tempString.paramLength(), "alt_parm_disk_number_length"));
        parameterArray.add(tempString);
        tempString = new VSMString(get_altParmDiskPassword(), "alt_parm_disk_password");
        parameterArray.add(new VSMInt4(tempString.paramLength(), "alt_parm_disk_password_length"));
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
        ParameterArray parameterArray = new ParameterArray();
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
}
