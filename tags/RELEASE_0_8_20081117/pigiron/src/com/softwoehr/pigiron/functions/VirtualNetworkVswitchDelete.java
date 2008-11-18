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

/* Autogenerated Thu Oct 23 13:05:34 UTC 2008
 * by pigfunc.m4 Copyright *C* 2008 Jack J. Woehr
 * Part of the PigIron Project http://pigiron.sourceforge.net
 */
package com.softwoehr.pigiron.functions;

import java.io.IOException;
import com.softwoehr.pigiron.access.*;
import com.softwoehr.pigiron.access.paramstructs.VswitchArray;

/**
 * {@code Virtual_Network_Vswitch_Delete} VSMAPI Function
 * @see com.softwoehr.pigiron.access.paramstructs.VswitchArray
 */
public class VirtualNetworkVswitchDelete extends VSMCall {

    /**
     * The transmitted name of the function.
     */
    public static final String FUNCTION_NAME = "Virtual_Network_Vswitch_Delete";

    /** Trunk */
    public static final int PORT_TYPE_TRUNK = 2;

    /** Unspecified */
    public static final int UPDATE_SYSTEM_CONFIG_INDICATOR_UNSPECIFIED = 0;

    /** Create virtual switch on the active system */
    public static final int UPDATE_SYSTEM_CONFIG_INDICATOR_CREATE = 1;

    /** Create virtual switch on the active system and add virtual switch definition to system configuration file */
    public static final int UPDATE_SYSTEM_CONFIG_INDICATOR_CREATE_ADD = 2;

    /** Add virtual switch definition to system configuration file */
    public static final int UPDATE_SYSTEM_CONFIG_INDICATOR_ADD = 3;

    /** GVRP unspecified */
    public static final int GVRP_VALUE_UNSPECIFIED = 0;

    /** GVRP */
    public static final int GVRP_VALUE_GVRP = 1;

    /** non-GVRP */
    public static final int GVRP_VALUE_NOGVRP = 2;

    /**
     *  Create an instance of the function call with important fields not instanced.
     */
    public VirtualNetworkVswitchDelete() {
    }

    /**
     * Create an instance with the variables filled in.
     * @param hostname  VSMAPI Host DNS name
     * @param port port VSMAPI Host is listening on
     * @param userid userid executing the function
     * @param password the password
     * @param target_identifier the target of the VSMAPI function
     * @param switch_name instances {@code switchName}
     * @param update_system_config_indicator instances {@code updateSystemConfigIndicator}
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
    public VirtualNetworkVswitchDelete(String hostname, int port, String userid, String password, String target_identifier, String switch_name, int update_system_config_indicator, String system_config_name, String system_config_type, String parm_disk_owner, String parm_disk_number, String parm_disk_password, String alt_system_config_name, String alt_system_config_type, String alt_parm_disk_owner, String alt_parm_disk_number, String alt_parm_disk_password) {
        this();
        setHostname(hostname);
        setPort(port);
        setUserid(userid);
        setPassword(password);
        setTarget_identifier(target_identifier);
        set_switchName(switch_name);
        set_updateSystemConfigIndicator(update_system_config_indicator);
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

    /** The name of the virtual switch */
    private String switchName = "";

    /** Configuration options */
    private int updateSystemConfigIndicator = -1;

    /** File name of the system configuration file */
    private String systemConfigName = "";

    /** File type of the system configuration file */
    private String systemConfigType = "";

    /** Owner of the parm disk */
    private String parmDiskOwner = "";

    /** Number of the parm disk as defined in the server directory */
    private String parmDiskNumber = "";

    /** Multiwrite password for the parm disk */
    private String parmDiskPassword = ",";

    /** File name of the second (alternative) system configuration file */
    private String altSystemConfigName = ",";

    /** File type of the second (alternative) system configuration file */
    private String altSystemConfigType = ",";

    /** Owner of the second (alternative) parm disk */
    private String altParmDiskOwner = ",";

    /** Number of the second (alternative) parm disk */
    private String altParmDiskNumber = ",";

    /** Multiwrite password for the second (alternative) parm disk */
    private String altParmDiskPassword = ",";

    /** Set the value of {@code  switchName }.
     * @param val The value to set {@code  switchName }.
     */
    public void set_switchName(String val) {
        switchName = val;
    }

    /** Get the value of {@code  switchName }.
     * @return The value of {@code  switchName }.
     */
    public String get_switchName() {
        return switchName;
    }

    /** Set the value of {@code  updateSystemConfigIndicator }.
     * @param val The value to set {@code  updateSystemConfigIndicator }.
     */
    public void set_updateSystemConfigIndicator(int val) {
        updateSystemConfigIndicator = val;
    }

    /** Get the value of {@code  updateSystemConfigIndicator }.
     * @return The value of {@code  updateSystemConfigIndicator }.
     */
    public int get_updateSystemConfigIndicator() {
        return updateSystemConfigIndicator;
    }

    /** Set the value of {@code  systemConfigName }.
     * @param val The value to set {@code  systemConfigName }.
     */
    public void set_systemConfigName(String val) {
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
    public void set_systemConfigType(String val) {
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
    public void set_parmDiskOwner(String val) {
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
    public void set_parmDiskNumber(String val) {
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
    public void set_parmDiskPassword(String val) {
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
    public void set_altSystemConfigName(String val) {
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
    public void set_altSystemConfigType(String val) {
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
    public void set_altParmDiskOwner(String val) {
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
    public void set_altParmDiskNumber(String val) {
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
    public void set_altParmDiskPassword(String val) {
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
        tempString = new VSMString(get_switchName(), "switch_name");
        parameterArray.add(new VSMInt4(tempString.paramLength(), "switch_name_length"));
        parameterArray.add(tempString);
        parameterArray.add(new VSMInt1(get_updateSystemConfigIndicator(), "update_system_config_indicator"));
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

}
