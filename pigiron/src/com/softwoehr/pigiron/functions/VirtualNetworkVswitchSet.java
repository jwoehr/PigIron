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

/* Autogenerated Thu Dec 10 04:29:34 UTC 2015
 * by pigfunc.m4 Copyright *C* 2015 Jack J. Woehr
 * Part of the PigIron Project http://pigiron.sourceforge.net
 */
package com.softwoehr.pigiron.functions;

import java.io.IOException;
import com.softwoehr.pigiron.access.*;

/**
 * {@code Virtual_Network_Vswitch_Set} VSMAPI Function
 */
public class VirtualNetworkVswitchSet extends VSMCall {

    /**
     * The transmitted name of the function.
     */
    public static final String FUNCTION_NAME = "Virtual_Network_Vswitch_Set";

    /** Unspecified */
    public static final int CONNECTION_VALUE_UNSPECIFIED = 0;

    /** Activate connection */
    public static final int CONNECTION_VALUE_ACTIVATE = 1;

    /** Dont activate connection' */
    public static final int CONNECTION_VALUE_DO_NOT_ACTIVATE = 2;

    /** Unspecified */
    public static final int ROUTING_VALUE_UNSPECIFIED = 0;

    /** Device identified in real_device_address will not act as a router to the virtual switch */
    public static final int ROUTING_VALUE_NONROUTER = 1;

    /** device identified in real_device_address will act as a primary router to the virtual switch */
    public static final int ROUTING_VALUE_PRIROUTER = 2;

    /** Unspecified */
    public static final int TRANSPORT_TYPE_UNSPECIFIED = 0;

    /** IP */
    public static final int TRANSPORT_TYPE_IP = 1;

    /** Ethernet */
    public static final int TRANSPORT_TYPE_ETHERNET = 2;

    /** Unspecified */
    public static final int PORT_TYPE_UNSPECIFIED = 0;

    /** Access */
    public static final int PORT_TYPE_ACCESS = 1;

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
    public VirtualNetworkVswitchSet() {
    }

    /**
     * Create an instance with the variables filled in.
     * @param hostname  VSMAPI Host DNS name
     * @param port port VSMAPI Host is listening on
     * @param userid userid executing the function
     * @param password the password
     * @param target_identifier the target of the VSMAPI function
     * @param switch_name instances {@code switchName}
     * @param grant_userid instances {@code grantUserid}
     * @param user_vlan_id instances {@code userVlanId}
     * @param revoke_userid instances {@code revokeUserid}
     * @param real_device_address instances {@code realDeviceAddress}
     * @param port_name instances {@code portName}
     * @param controller_name instances {@code controllerName}
     * @param connection_value instances {@code connectionValue}
     * @param queue_memory_limit instances {@code queueMemoryLimit}
     * @param routing_value instances {@code routingValue}
     * @param port_type instances {@code portType}
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
     * @param gvrp_value instances {@code gvrpValue}
     * @param mac_id instances {@code macId}
     */
    public VirtualNetworkVswitchSet(String hostname, int port, String userid, String password , String target_identifier, String switch_name, String grant_userid, String user_vlan_id, String revoke_userid, String real_device_address, String port_name, String controller_name, int connection_value, int queue_memory_limit, int routing_value, int port_type, int update_system_config_indicator, String system_config_name, String system_config_type, String parm_disk_owner, String parm_disk_number, String parm_disk_password, String alt_system_config_name, String alt_system_config_type, String alt_parm_disk_owner, String alt_parm_disk_number, String alt_parm_disk_password, int gvrp_value, String mac_id) {
        this();
        setHostname(hostname);
        setPort(port);
        setUserid(userid);
        setPassword(password);
        setTarget_identifier(target_identifier);
        set_switchName(switch_name);
        set_grantUserid(grant_userid);
        set_userVlanId(user_vlan_id);
        set_revokeUserid(revoke_userid);
        set_realDeviceAddress(real_device_address);
        set_portName(port_name);
        set_controllerName(controller_name);
        set_connectionValue(connection_value);
        set_queueMemoryLimit(queue_memory_limit);
        set_routingValue(routing_value);
        set_portType(port_type);
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
        set_gvrpValue(gvrp_value);
        set_macId(mac_id);
    }

    /** The name of the virtual switch */
    private String switchName = "";

    /** A userid to be added to the access list for the specified virtual switch */
    private String grantUserid = "";

    /** The user VLAN ID */
    private String userVlanId = "";

    /** A userid to be removed from the access list for the specified virtual switch */
    private String revokeUserid = "";

    /** The real device address of a real OSA-Express QDIO device */
    private String realDeviceAddress = "";

    /** The name used to identify the OSA Expanded adapter */
    private String portName = "";

    /** The userid controlling the real device or * for any available */
    private String controllerName = "*";

    /** Activate/do not */
    private int connectionValue = 0;

    /** Specifies the QDIO buffer size in megabytes. */
    private int queueMemoryLimit = 0;

    /** Specifies whether the OSA-Express QDIO device will act as a router to the virtual switch */
    private int routingValue = 0;

    /** Specifies the port type */
    private int portType = -1;

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

    /** GVRP value */
    private int gvrpValue = GVRP_VALUE_UNSPECIFIED;

    /** The MAC identifier */
    private String macId = "";

    /** Set the value of {@code  switchName }.
     * @param val The value to set {@code  switchName }.
     */
    public final void set_switchName(String val) {
        switchName = val;
    }

    /** Get the value of {@code  switchName }.
     * @return The value of {@code  switchName }.
     */
    public String get_switchName() {
        return switchName;
    }

    /** Set the value of {@code  grantUserid }.
     * @param val The value to set {@code  grantUserid }.
     */
    public final void set_grantUserid(String val) {
        grantUserid = val;
    }

    /** Get the value of {@code  grantUserid }.
     * @return The value of {@code  grantUserid }.
     */
    public String get_grantUserid() {
        return grantUserid;
    }

    /** Set the value of {@code  userVlanId }.
     * @param val The value to set {@code  userVlanId }.
     */
    public final void set_userVlanId(String val) {
        userVlanId = val;
    }

    /** Get the value of {@code  userVlanId }.
     * @return The value of {@code  userVlanId }.
     */
    public String get_userVlanId() {
        return userVlanId;
    }

    /** Set the value of {@code  revokeUserid }.
     * @param val The value to set {@code  revokeUserid }.
     */
    public final void set_revokeUserid(String val) {
        revokeUserid = val;
    }

    /** Get the value of {@code  revokeUserid }.
     * @return The value of {@code  revokeUserid }.
     */
    public String get_revokeUserid() {
        return revokeUserid;
    }

    /** Set the value of {@code  realDeviceAddress }.
     * @param val The value to set {@code  realDeviceAddress }.
     */
    public final void set_realDeviceAddress(String val) {
        realDeviceAddress = val;
    }

    /** Get the value of {@code  realDeviceAddress }.
     * @return The value of {@code  realDeviceAddress }.
     */
    public String get_realDeviceAddress() {
        return realDeviceAddress;
    }

    /** Set the value of {@code  portName }.
     * @param val The value to set {@code  portName }.
     */
    public final void set_portName(String val) {
        portName = val;
    }

    /** Get the value of {@code  portName }.
     * @return The value of {@code  portName }.
     */
    public String get_portName() {
        return portName;
    }

    /** Set the value of {@code  controllerName }.
     * @param val The value to set {@code  controllerName }.
     */
    public final void set_controllerName(String val) {
        controllerName = val;
    }

    /** Get the value of {@code  controllerName }.
     * @return The value of {@code  controllerName }.
     */
    public String get_controllerName() {
        return controllerName;
    }

    /** Set the value of {@code  connectionValue }.
     * @param val The value to set {@code  connectionValue }.
     */
    public final void set_connectionValue(int val) {
        connectionValue = val;
    }

    /** Get the value of {@code  connectionValue }.
     * @return The value of {@code  connectionValue }.
     */
    public int get_connectionValue() {
        return connectionValue;
    }

    /** Set the value of {@code  queueMemoryLimit }.
     * @param val The value to set {@code  queueMemoryLimit }.
     */
    public final void set_queueMemoryLimit(int val) {
        queueMemoryLimit = val;
    }

    /** Get the value of {@code  queueMemoryLimit }.
     * @return The value of {@code  queueMemoryLimit }.
     */
    public int get_queueMemoryLimit() {
        return queueMemoryLimit;
    }

    /** Set the value of {@code  routingValue }.
     * @param val The value to set {@code  routingValue }.
     */
    public final void set_routingValue(int val) {
        routingValue = val;
    }

    /** Get the value of {@code  routingValue }.
     * @return The value of {@code  routingValue }.
     */
    public int get_routingValue() {
        return routingValue;
    }

    /** Set the value of {@code  portType }.
     * @param val The value to set {@code  portType }.
     */
    public final void set_portType(int val) {
        portType = val;
    }

    /** Get the value of {@code  portType }.
     * @return The value of {@code  portType }.
     */
    public int get_portType() {
        return portType;
    }

    /** Set the value of {@code  updateSystemConfigIndicator }.
     * @param val The value to set {@code  updateSystemConfigIndicator }.
     */
    public final void set_updateSystemConfigIndicator(int val) {
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

    /** Set the value of {@code  gvrpValue }.
     * @param val The value to set {@code  gvrpValue }.
     */
    public final void set_gvrpValue(int val) {
        gvrpValue = val;
    }

    /** Get the value of {@code  gvrpValue }.
     * @return The value of {@code  gvrpValue }.
     */
    public int get_gvrpValue() {
        return gvrpValue;
    }

    /** Set the value of {@code  macId }.
     * @param val The value to set {@code  macId }.
     */
    public final void set_macId(String val) {
        macId = val;
    }

    /** Get the value of {@code  macId }.
     * @return The value of {@code  macId }.
     */
    public String get_macId() {
        return macId;
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
        parameterArray.add(new CountedString(getTarget_identifier(), "target_identifier"));        parameterArray.add(new CountedString(get_switchName(), "switch_name"));        parameterArray.add(new CountedString(get_grantUserid(), "grant_userid"));        parameterArray.add(new CountedString(get_userVlanId(), "user_vlan_id"));        parameterArray.add(new CountedString(get_revokeUserid(), "revoke_userid"));        parameterArray.add(new CountedString(get_realDeviceAddress(), "real_device_address"));        parameterArray.add(new CountedString(get_portName(), "port_name"));        parameterArray.add(new CountedString(get_controllerName(), "controller_name"));        parameterArray.add(new VSMInt1(get_connectionValue(), "connection_value"));        parameterArray.add(new VSMInt4(get_queueMemoryLimit(), "queue_memory_limit"));        parameterArray.add(new VSMInt1(get_routingValue(), "routing_value"));        parameterArray.add(new VSMInt1(get_portType(), "port_type"));        parameterArray.add(new VSMInt1(get_updateSystemConfigIndicator(), "update_system_config_indicator"));        parameterArray.add(new CountedString(get_systemConfigName(), "system_config_name"));        parameterArray.add(new CountedString(get_systemConfigType(), "system_config_type"));        parameterArray.add(new CountedString(get_parmDiskOwner(), "parm_disk_owner"));        parameterArray.add(new CountedString(get_parmDiskNumber(), "parm_disk_number"));        parameterArray.add(new CountedString(get_parmDiskPassword(), "parm_disk_password"));        parameterArray.add(new CountedString(get_altSystemConfigName(), "alt_system_config_name"));        parameterArray.add(new CountedString(get_altSystemConfigType(), "alt_system_config_type"));        parameterArray.add(new CountedString(get_altParmDiskOwner(), "alt_parm_disk_owner"));        parameterArray.add(new CountedString(get_altParmDiskNumber(), "alt_parm_disk_number"));        parameterArray.add(new CountedString(get_altParmDiskPassword(), "alt_parm_disk_password"));        parameterArray.add(new VSMInt1(get_gvrpValue(), "gvrp_value"));        parameterArray.add(new CountedString(get_macId(), "mac_id"));        VSMInt4 outputLength = new VSMInt4(new Long(parameterArray.totalParameterLength()).intValue(), "output_length");
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

}
