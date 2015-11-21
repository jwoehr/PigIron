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

/* Autogenerated Sat Nov 21 22:13:50 UTC 2015
 * by pigfunc.m4 Copyright *C* 2015 Jack J. Woehr
 * Part of the PigIron Project http://pigiron.sourceforge.net
 */
package com.softwoehr.pigiron.functions;

import java.io.IOException;
import com.softwoehr.pigiron.access.*;

/**
 * <tt>Image_Device_Dedicate_DM</tt> VSMAPI Function
 */
public class ImageDeviceDedicateDM extends VSMCall {

    /**
     * The transmitted name of the function.
     */
    public static final String FUNCTION_NAME = "Image_Device_Dedicate_DM";

    /**
     *  Create an instance of the function call with important fields not instanced.
     */
    public ImageDeviceDedicateDM() {
    }

    /**
     * Create an instance with the variables filled in.
     * @param hostname  VSMAPI Host DNS name
     * @param port port VSMAPI Host is listening on
     * @param userid userid executing the function
     * @param password the password
     * @param target_identifier the target of the VSMAPI function
     * @param image_device_number instances {@code imageDeviceNumber}
     * @param real_device_number instances {@code realDeviceNumber}
     * @param readonly instances {@code readonly}
     */
    public ImageDeviceDedicateDM(String hostname, int port, String userid, String password, String target_identifier, String image_device_number, String real_device_number, int readonly) {
        this();
        setHostname(hostname);
        setPort(port);
        setUserid(userid);
        setPassword(password);
        setTarget_identifier(target_identifier);
        set_imageDeviceNumber(image_device_number);
        set_realDeviceNumber(real_device_number);
        set_readonly(readonly);
    }

    /** The virtual device number of the device */
    private String imageDeviceNumber = "";

    /** A real device number to be dedicated or attached to the specified virtual image */
    private String realDeviceNumber = "";

    /** 1 if the virtual device is to be in read-only mode otherwise 0 */
    private int readonly = 1;

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

    /** Set the value of {@code  realDeviceNumber }.
     * @param val The value to set {@code  realDeviceNumber }.
     */
    public final void set_realDeviceNumber(String val) {
        realDeviceNumber = val;
    }

    /** Get the value of {@code  realDeviceNumber }.
     * @return The value of {@code  realDeviceNumber }.
     */
    public String get_realDeviceNumber() {
        return realDeviceNumber;
    }

    /** Set the value of {@code  readonly }.
     * @param val The value to set {@code  readonly }.
     */
    public final void set_readonly(int val) {
        readonly = val;
    }

    /** Get the value of {@code  readonly }.
     * @return The value of {@code  readonly }.
     */
    public int get_readonly() {
        return readonly;
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
        tempString = new VSMString(get_imageDeviceNumber(), "image_device_number");
        parameterArray.add(new VSMInt4(tempString.paramLength(), "image_device_number_length"));
        parameterArray.add(tempString);
        tempString = new VSMString(get_realDeviceNumber(), "real_device_number");
        parameterArray.add(new VSMInt4(tempString.paramLength(), "real_device_number_length"));
        parameterArray.add(tempString);
        parameterArray.add(new VSMInt1(get_readonly(), "readonly"));
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
     * You can execute the VSMAPI call from <tt>main()</tt>, try it
     * with no args to see the usage message.
     * @param argv array of commandline args
     * @throws IOException on comm error
     * @throws VSMException on internal Pigiron param marshalling error
     */
    public static void main(String[] argv) throws IOException, VSMException {

        ImageDeviceDedicateDM instance = null;

        if (argv.length != 8) {
            System.out.println("usage: args are:\ninetaddr port user pw target image_device_number real_device_number readonly");
            System.exit(1);
        }

        System.out.println("Args are: " + argv[0] + " " + argv[1] + " " + argv[2] + " " + argv[3] + " " + argv[4] + " " + argv[5] + " " + argv[6] + " " + argv[7]);
        instance = new ImageDeviceDedicateDM(argv[0], Integer.valueOf(argv[1]).intValue(), argv[2], argv[3], argv[4],  argv[5], argv[6], Integer.valueOf(argv[7]).intValue());

        ParameterArray pA = instance.doIt();
        System.out.println("Returns from call to " + instance.getFunctionName() + ":");
        System.out.println(pA.prettyPrintAll());
    }
}
