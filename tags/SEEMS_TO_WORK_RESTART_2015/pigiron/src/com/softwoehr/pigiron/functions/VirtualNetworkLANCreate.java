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

/* Autogenerated Thu Jan  8 23:19:33 UTC 2009
 * by pigfunc.m4 Copyright *C* 2008 Jack J. Woehr
 * Part of the PigIron Project http://pigiron.sourceforge.net
 */
package com.softwoehr.pigiron.functions;

import java.io.IOException;
import com.softwoehr.pigiron.access.*;

/**
 * {@code Virtual_Network_LAN_Create} VSMAPI Function
 */
public class VirtualNetworkLANCreate extends VSMCall {

    /**
     * The transmitted name of the function.
     */
    public static final String FUNCTION_NAME = "Virtual_Network_LAN_Create";

    /** Simulated HiperSockets NIC */
    public static final int LAN_TYPE_HIPERSOCKETS = 1;

    /** Simulated QDIO NIC */
    public static final int LAN_TYPE_QDIO = 2;

    /** Unspecified */
    public static final int TRANSPORT_TYPE_UNSPECIFIED = 0;

    /** IP */
    public static final int TRANSPORT_TYPE_IP = 1;

    /** Ethernet */
    public static final int TRANSPORT_TYPE_ETHERNET = 2;

    /**
     *  Create an instance of the function call with important fields not instanced.
     */
    public VirtualNetworkLANCreate() {
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
     * @param lan_type instances {@code lanType}
     * @param transport_type instances {@code transportType}
     */
    public VirtualNetworkLANCreate(String hostname, int port, String userid, String password, String target_identifier, String lan_name, String lan_owner, int lan_type, int transport_type) {
        this();
        setHostname(hostname);
        setPort(port);
        setUserid(userid);
        setPassword(password);
        setTarget_identifier(target_identifier);
        set_lanName(lan_name);
        set_lanOwner(lan_owner);
        set_lanType(lan_type);
        set_transportType(transport_type);
    }

    /** The name of the guest LAN segment to connect the virtual image */
    private String lanName = "";

    /** The virtual image owning the guest LAN segment to be created */
    private String lanOwner = "";

    /** The type of guest LAN segment */
    private int lanType = 0;

    /** Specifies the transport mechanism to be used for guest LANs and virtual switches */
    private int transportType = TRANSPORT_TYPE_UNSPECIFIED;

    /** Set the value of {@code  lanName }.
     * @param val The value to set {@code  lanName }.
     */
    public void set_lanName(String val) {
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
    public void set_lanOwner(String val) {
        lanOwner = val;
    }

    /** Get the value of {@code  lanOwner }.
     * @return The value of {@code  lanOwner }.
     */
    public String get_lanOwner() {
        return lanOwner;
    }

    /** Set the value of {@code  lanType }.
     * @param val The value to set {@code  lanType }.
     */
    public void set_lanType(int val) {
        lanType = val;
    }

    /** Get the value of {@code  lanType }.
     * @return The value of {@code  lanType }.
     */
    public int get_lanType() {
        return lanType;
    }

    /** Set the value of {@code  transportType }.
     * @param val The value to set {@code  transportType }.
     */
    public void set_transportType(int val) {
        transportType = val;
    }

    /** Get the value of {@code  transportType }.
     * @return The value of {@code  transportType }.
     */
    public int get_transportType() {
        return transportType;
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
        tempString = new VSMString(get_lanName(), "lan_name");
        parameterArray.add(new VSMInt4(tempString.paramLength(), "lan_name_length"));
        parameterArray.add(tempString);
        tempString = new VSMString(get_lanOwner(), "lan_owner");
        parameterArray.add(new VSMInt4(tempString.paramLength(), "lan_owner_length"));
        parameterArray.add(tempString);
        parameterArray.add(new VSMInt1(get_lanType(), "lan_type"));
        parameterArray.add(new VSMInt1(get_transportType(), "transport_type"));
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

        VirtualNetworkLANCreate instance = null;

        if (argv.length != 9) {
            System.out.println("usage: args are:\ninetaddr port user pw target lan_name lan_owner lan_type transport_type");
            System.exit(1);
        }

        System.out.println("Args are: " + argv[0] + " " + argv[1] + " " + argv[2] + " " + argv[3] + " " + argv[4] + " " + argv[5] + " " + argv[6] + " " + argv[7] + " " + argv[8]);
        instance = new VirtualNetworkLANCreate(argv[0], Integer.valueOf(argv[1]).intValue(), argv[2], argv[3], argv[4],  argv[5], argv[6],  Integer.valueOf(argv[7]).intValue(),  Integer.valueOf(argv[8]).intValue());

        ParameterArray pA = instance.doIt();
        System.out.println("Returns from call to " + instance.getFunctionName() + ":");
        System.out.println(pA.prettyPrintAll());
    }
}
