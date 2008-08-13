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
package com.softwoehr.pigiron.functions;

import com.softwoehr.pigiron.access.*;
import com.softwoehr.pigiron.access.paramstructs.ImageNameArray;
import java.io.IOException;
import java.util.Iterator;

/**
 * Embodiment of a VSMAPI Check_Authentication function call.
 * Models and marshalls both input and output parameters, gets a connection,
 * makes the call, returns a ParameterArray of the result from doIt().
 * @author jax
 * @see com.softwoehr.pigiron.access.ParameterArray
 */
public class ImageStatusQuery extends VSMCall {

    /**
     * The transmitted name of the function
     */
    public static final String FUNCTION_NAME = "Image_Status_Query";

    /**
     *
     * "Because it does not include a target_identifier parameter, Check_Authentication
     * is the only API that does not conform to the set of common input parameters."
     * - z/VM V5R3.0 Systems Management Application Programming SC24-6122-03
     */
    public ImageStatusQuery() {
        setTarget_identifier(""); // Just for good luck!
    }

    /**
     *
     * @param hostname
     * @param port
     * @param userid
     * @param password
     * @param target_identifier
     */
    public ImageStatusQuery(String hostname, int port, String userid, String password, String target_identifier) {
        this();
        setHostname(hostname);
        setPort(port);
        setUserid(userid);
        setPassword(password);
        setTarget_identifier(target_identifier);
    }

    /**
     *
     * "Input" as in "input to VSMAPI".
     * @return
     * @see
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
        VSMInt4 outputLength = new VSMInt4(new Long(parameterArray.totalParameterLength()).intValue(), "output_length");
        parameterArray.insertElementAt(outputLength, 0);
        // /* Debug */ System.out.println("composed input array :" + parameterArray);
        setInParams(parameterArray);
        return parameterArray;
    }

    /**
     *
     * "output" as in "output from VSMAPI"
     * @return
     * @see
     */
    protected ParameterArray composeOutputArray() {
        ParameterArray parameterArray = new ParameterArray();
        parameterArray.add(new VSMInt4(-1, "request_id_immediate"));
        parameterArray.add(new VSMInt4(-1, "output_length"));
        parameterArray.add(new VSMInt4(-1, "request_id"));
        parameterArray.add(new VSMInt4(-1, "return_code"));
        parameterArray.add(new VSMInt4(-1, "reason_code"));
        parameterArray.add(new VSMInt4(-1, "image_name_array_length"));
        parameterArray.add(ImageNameArray.modelArray("image_name_array"));
        setOutParams(parameterArray);
        return parameterArray;
    }

    /**
     *
     * @return
     */
    @Override
    public String getFunctionName() {
        return FUNCTION_NAME;
    }

    /**
     *
     * @param argv
     * @throws IOException
     * @throws VSMException
     */
    public static void main(String[] argv) throws IOException, VSMException {
        ImageStatusQuery iq = null;

        if (argv.length < 5) {
            System.out.println("usage: args are:\ninetaddr port user pw target");
            System.exit(1);
        }

        System.out.println("Args are: " + argv[0] + " " + argv[1] + " " + argv[2] + " " + argv[3] + " " + argv[4]);
        iq = new ImageStatusQuery(argv[0], Integer.valueOf(argv[1]).intValue(), argv[2], argv[3], argv[4]);

        ParameterArray result = iq.doIt();
        System.out.println("Returns from call to " + iq.getFunctionName() + ":");
        Iterator<VSMParm> i = result.iterator();
        while (i.hasNext()) {
            System.out.println(i.next().prettyPrint());
        }
    }
}