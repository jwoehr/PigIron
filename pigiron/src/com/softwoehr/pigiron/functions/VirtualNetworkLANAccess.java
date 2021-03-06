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

/* Autogenerated Mon Jan  4 02:27:30 UTC 2016
 * by pigfunc.m4 Copyright *C* 2015 Jack J. Woehr
 * Part of the PigIron Project http://pigiron.sourceforge.net
 */
package com.softwoehr.pigiron.functions;

import java.io.IOException;
import com.softwoehr.pigiron.access.*;

/**
 * {@code Virtual_Network_LAN_Access} VSMAPI 5.4 Function
 * @since <a href="http://publib.boulder.ibm.com/infocenter/zvm/v5r4/index.jsp">VSMAPI 5.4</a>
 */
public class VirtualNetworkLANAccess extends VSMCall {

    /**
     * The transmitted name of the function.
     */
    public static final String FUNCTION_NAME = "Virtual_Network_LAN_Access";

    /**
     *  Create an instance of the function call with important fields not instanced.
     */
    public VirtualNetworkLANAccess() {
    }

    /**
     * Create an instance with the variables filled in.
     * @param hostname  VSMAPI Host DNS name
     * @param port port VSMAPI Host is listening on
     * @param userid userid executing the function
     * @param password the password
     * @param target_identifier the target of the VSMAPI function
     * @param lan_name instances {@code lanName}
     * @param lan_owner instances {@code lanOwner}
     * @param access_op instances {@code accessOp}
     * @param access_user instances {@code accessUser}
     * @param promiscuity instances {@code promiscuity}
     */
    public VirtualNetworkLANAccess(String hostname, int port, String userid, String password , String target_identifier, String lan_name, String lan_owner, String access_op, String access_user, String promiscuity) {
        this();
        setHostname(hostname);
        setPort(port);
        setUserid(userid);
        setPassword(password);
        setTarget_identifier(target_identifier);
        set_lanName(lan_name);
        set_lanOwner(lan_owner);
        set_accessOp(access_op);
        set_accessUser(access_user);
        set_promiscuity(promiscuity);
    }

    /** The name of the LAN to which access is being granted or revoked. */
    private String lanName = "";

    /** The virtual image owning the guest LAN segment to be created. */
    private String lanOwner = "";

    /** GRANT Grant access. REVOKE Revoke access. */
    private String accessOp = "";

    /** Virtual image to be granted access to the LAN. */
    private String accessUser = "";

    /** NONPROMISCUOUS Nonpromiscuous access. PROMISCUOUS Promiscuous access. */
    private String promiscuity = "";

    /** Set the value of {@code  lanName }.
     * @param val The value to set {@code  lanName }.
     */
    public final void set_lanName(String val) {
        lanName = val;
    }

    /** Get the value of {@code  lanName }.
     * @return The value of {@code  lanName }.
     */
    public String get_lanName() {
        return lanName;
    }

    /** Set the value of {@code  lanOwner }.
     * @param val The value to set {@code  lanOwner }.
     */
    public final void set_lanOwner(String val) {
        lanOwner = val;
    }

    /** Get the value of {@code  lanOwner }.
     * @return The value of {@code  lanOwner }.
     */
    public String get_lanOwner() {
        return lanOwner;
    }

    /** Set the value of {@code  accessOp }.
     * @param val The value to set {@code  accessOp }.
     */
    public final void set_accessOp(String val) {
        accessOp = val;
    }

    /** Get the value of {@code  accessOp }.
     * @return The value of {@code  accessOp }.
     */
    public String get_accessOp() {
        return accessOp;
    }

    /** Set the value of {@code  accessUser }.
     * @param val The value to set {@code  accessUser }.
     */
    public final void set_accessUser(String val) {
        accessUser = val;
    }

    /** Get the value of {@code  accessUser }.
     * @return The value of {@code  accessUser }.
     */
    public String get_accessUser() {
        return accessUser;
    }

    /** Set the value of {@code  promiscuity }.
     * @param val The value to set {@code  promiscuity }.
     */
    public final void set_promiscuity(String val) {
        promiscuity = val;
    }

    /** Get the value of {@code  promiscuity }.
     * @return The value of {@code  promiscuity }.
     */
    public String get_promiscuity() {
        return promiscuity;
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
        parameterArray.add(new CountedString(getTarget_identifier(), "target_identifier"));
        parameterArray.add(new VSMAsciiZ(get_lanName(), "lan_name"));
        parameterArray.add(new VSMAsciiZ(get_lanOwner(), "lan_owner"));
        parameterArray.add(new VSMAsciiZ(get_accessOp(), "access_op"));
        parameterArray.add(new VSMAsciiZ(get_accessUser(), "access_user"));
        parameterArray.add(new VSMAsciiZ(get_promiscuity(), "promiscuity"));
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

        VirtualNetworkLANAccess instance = null;

        if (argv.length != 10) {
            System.out.println("usage: args are:\ninetaddr port user pw target_id lan_name lan_owner access_op access_user promiscuity");
            System.exit(1);
        }

        System.out.println("Args are: " + argv[0] + " " + argv[1] + " " + argv[2] + " " + argv[3] + " " + argv[4] + " " + argv[5] + " " + argv[6] + " " + argv[7] + " " + argv[8] + " " + argv[9]);
        instance = new VirtualNetworkLANAccess(argv[0], Integer.valueOf(argv[1]).intValue(), argv[2], argv[3], argv[4], argv[5], argv[6], argv[7], argv[8], argv[9]);

        ParameterArray pA = instance.doIt();
        System.out.println("Returns from call to " + instance.getFunctionName() + ":");
        System.out.println(pA.prettyPrintAll());
    }
}
