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

/* Autogenerated Sat Dec 19 03:56:25 UTC 2015
 * by pigfunc.m4 Copyright *C* 2015 Jack J. Woehr
 * Part of the PigIron Project http://pigiron.sourceforge.net
 */
package com.softwoehr.pigiron.functions;

import java.io.IOException;
import com.softwoehr.pigiron.access.*;

/**
 * {@code Virtual_Network_Adapter_Create } VSMAPI Function
 * To non-specify the  {@code channel_path_id} just leave it at its default value of {@code ""}.
 */
public class VirtualNetworkAdapterCreate extends VSMCall {

    /**
     * The transmitted name of the function.
     */
    public static final String FUNCTION_NAME = "Virtual_Network_Adapter_Create";

    /** Simulated HiperSockets NIC */
    public static final int ADAPTER_TYPE_HIPERSOCKETS = 1;

    /** Simulated QDIO NIC */
    public static final int ADAPTER_TYPE_QDIO = 2;

    /**
     *  Create an instance of the function call with important fields not instanced.
     */
    public VirtualNetworkAdapterCreate() {
    }

    /**
     * Create an instance with the variables filled in.
     * @param hostname  VSMAPI Host DNS name
     * @param port port VSMAPI Host is listening on
     * @param userid userid executing the function
     * @param password the password
     * @param target_identifier the target of the VSMAPI function
     * @param image_device_number instances {@code imageDeviceNumber}
     * @param adapter_type instances {@code adapterType}
     * @param network_adapter_devices instances {@code networkAdapterDevices}
     * @param channel_path_id instances {@code channelPathId}
     */
    public VirtualNetworkAdapterCreate(String hostname, int port, String userid, String password , String target_identifier, String image_device_number, int adapter_type, int network_adapter_devices, String channel_path_id) {
        this();
        setHostname(hostname);
        setPort(port);
        setUserid(userid);
        setPassword(password);
        setTarget_identifier(target_identifier);
        set_imageDeviceNumber(image_device_number);
        set_adapterType(adapter_type);
        set_networkAdapterDevices(network_adapter_devices);
        set_channelPathId(channel_path_id);
    }

    /** The virtual device address of the new adapter */
    private String imageDeviceNumber = "";

    /** The adapter type */
    private int adapterType = -1;

    /** The number of virtual devices associated with this adapter */
    private int networkAdapterDevices = -1;

    /** For use only when configuring a second-level z/OS system where it is used to specify the hex CHPID numbers for the first- and second-level systems */
    private String channelPathId = "";

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

    /** Set the value of {@code  adapterType }.
     * @param val The value to set {@code  adapterType }.
     */
    public final void set_adapterType(int val) {
        adapterType = val;
    }

    /** Get the value of {@code  adapterType }.
     * @return The value of {@code  adapterType }.
     */
    public int get_adapterType() {
        return adapterType;
    }

    /** Set the value of {@code  networkAdapterDevices }.
     * @param val The value to set {@code  networkAdapterDevices }.
     */
    public final void set_networkAdapterDevices(int val) {
        networkAdapterDevices = val;
    }

    /** Get the value of {@code  networkAdapterDevices }.
     * @return The value of {@code  networkAdapterDevices }.
     */
    public int get_networkAdapterDevices() {
        return networkAdapterDevices;
    }

    /** Set the value of {@code  channelPathId }.
     * @param val The value to set {@code  channelPathId }.
     */
    public final void set_channelPathId(String val) {
        channelPathId = val;
    }

    /** Get the value of {@code  channelPathId }.
     * @return The value of {@code  channelPathId }.
     */
    public String get_channelPathId() {
        return channelPathId;
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
        parameterArray.add(new CountedString(get_imageDeviceNumber(), "image_device_number"));
        parameterArray.add(new VSMInt1(get_adapterType(), "adapter_type"));
        parameterArray.add(new VSMInt4(get_networkAdapterDevices(), "network_adapter_devices"));
        parameterArray.add(new CountedString(get_channelPathId(), "channel_path_id"));
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

        VirtualNetworkAdapterCreate instance = null;

        if (argv.length < 8 | argv.length > 9) {
            System.out.println("usage: args are:\ninetaddr port user pw target image_device_number adapter_type network_adapter_devices [channel_path_id]");
            System.exit(1);
        }

        if (argv.length == 8) { // channel_path_id not specified
            System.out.println("Args are: " + argv[0] + " " + argv[1] + " " + argv[2] + " " + argv[3] + " " + argv[4] + " " + argv[5] + " " + argv[6] + " " + argv[7]);
            instance = new VirtualNetworkAdapterCreate(argv[0], Integer.valueOf(argv[1]).intValue(), argv[2], argv[3], argv[4], argv[5], Integer.valueOf(argv[6]).intValue(), Integer.valueOf(argv[7]).intValue(), "");
        } else { // argv.length == 9 // channel_path_id yea specified
            System.out.println("Args are: " + argv[0] + " " + argv[1] + " " + argv[2] + " " + argv[3] + " " + argv[4] + " " + argv[5] + " " + argv[6] + " " + argv[7] + " " + argv[8]);
            instance = new VirtualNetworkAdapterCreate(argv[0], Integer.valueOf(argv[1]).intValue(), argv[2], argv[3], argv[4], argv[5], Integer.valueOf(argv[6]).intValue(), Integer.valueOf(argv[7]).intValue(), argv[8]);
        }

        ParameterArray pA = instance.doIt();
        System.out.println("Returns from call to " + instance.getFunctionName() + ":");
        System.out.println(pA.prettyPrintAll());
    }
}
