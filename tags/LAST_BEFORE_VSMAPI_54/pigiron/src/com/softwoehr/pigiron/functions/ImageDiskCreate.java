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

/* Autogenerated Thu Sep 11 17:05:56 GMT 2008
 * by pigfunc.m4 Copyright *C* 2008 Jack J. Woehr
 * Part of the PigIron Project http://pigiron.sourceforge.net
 */
package com.softwoehr.pigiron.functions;

import java.io.IOException;
import com.softwoehr.pigiron.access.*;

/**
 * <tt>Image_Disk_Create</tt> VSMAPI Function
 */
public class ImageDiskCreate extends VSMCall {

    /**
     * The transmitted name of the function.
     */
    public static final String FUNCTION_NAME = "Image_Disk_Create";

    /** Read-only (R/O) access */
    public static final String IMAGE_DISK_MODE_R = "R";

    /** Read-only (R/O) access is desired even if the owner or another user has a link to the minidisk in write status */
    public static final String IMAGE_DISK_MODE_RR = "RR";

    /** Write access */
    public static final String IMAGE_DISK_MODE_W = "W";

    /** Write access is desired. Only R/O access allowed if the owner or any other user has a link to the minidisk in read or write status. */
    public static final String IMAGE_DISK_MODE_WR = "WR";

    /** Multiple access is desired' */
    public static final String IMAGE_DISK_MODE_M = "M";

    /** Write or any exclusive access is allowed to the minidisk unless another user already has write access to it. */
    public static final String IMAGE_DISK_MODE_MR = "MR";

    /** Write access is allowed to the disk unconditionally except for existing stable or exclusive links */
    public static final String IMAGE_DISK_MODE_MW = "MW";

    /**
     *  Create an instance of the function call with important fields not instanced.
     */
    public ImageDiskCreate() {
    }

    /**
     * Create an instance with the variables filled in.
     * @param hostname  VSMAPI Host DNS name
     * @param port port VSMAPI Host is listening on
     * @param userid userid executing the function
     * @param password the password
     * @param target_identifier the target of the VSMAPI function
     * @param image_disk_number instances {@code imageDiskNumber}
     * @param image_disk_mode instances {@code imageDiskMode}
     */
    public ImageDiskCreate(String hostname, int port, String userid, String password, String target_identifier, String image_disk_number, String image_disk_mode) {
        this();
        setHostname(hostname);
        setPort(port);
        setUserid(userid);
        setPassword(password);
        setTarget_identifier(target_identifier);
        set_imageDiskNumber(image_disk_number);
        set_imageDiskMode(image_disk_mode);
    }

    /** The virtual device address of the disk to be added */
    private String imageDiskNumber = "";

    /** The access mode requested for the disk as seen by the owner when the virtual image is logged on */
    private String imageDiskMode = IMAGE_DISK_MODE_RR;

    /** Set the value of {@code  imageDiskNumber }.
     * @param val The value to set {@code  imageDiskNumber }.
     */
    public void set_imageDiskNumber(String val) {
        imageDiskNumber = val;
    }

    /** Get the value of {@code  imageDiskNumber }.
     * @return The value of {@code  imageDiskNumber }.
     */
    public String get_imageDiskNumber() {
        return imageDiskNumber;
    }

    /** Set the value of {@code  imageDiskMode }.
     * @param val The value to set {@code  imageDiskMode }.
     */
    public void set_imageDiskMode(String val) {
        imageDiskMode = val;
    }

    /** Get the value of {@code  imageDiskMode }.
     * @return The value of {@code  imageDiskMode }.
     */
    public String get_imageDiskMode() {
        return imageDiskMode;
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
        tempString = new VSMString(get_imageDiskNumber(), "image_disk_number");
        parameterArray.add(new VSMInt4(tempString.paramLength(), "image_disk_number_length"));
        parameterArray.add(tempString);
        tempString = new VSMString(get_imageDiskMode(), "image_disk_mode");
        parameterArray.add(new VSMInt4(tempString.paramLength(), "image_disk_mode_length"));
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

    /**
     * You can execute the VSMAPI call from <tt>main()</tt>, try it
     * with no args to see the usage message.
     * @param argv array of commandline args
     * @throws IOException on comm error
     * @throws VSMException on internal Pigiron param marshalling error
     */
    public static void main(String[] argv) throws IOException, VSMException {

        ImageDiskCreate instance = null;

        if (argv.length != 7) {
            System.out.println("usage: args are:\ninetaddr port user pw target image_disk_number image_disk_mode");
            System.exit(1);
        }

        System.out.println("Args are: " + argv[0] + " " + argv[1] + " " + argv[2] + " " + argv[3] + " " + argv[4] + " " + argv[5] + " " + argv[6]);
        instance = new ImageDiskCreate(argv[0], Integer.valueOf(argv[1]).intValue(), argv[2], argv[3], argv[4], argv[5], argv[6]);

        ParameterArray pA = instance.doIt();
        System.out.println("Returns from call to " + instance.getFunctionName() + ":");
        System.out.println(pA.prettyPrintAll());
    }
}
