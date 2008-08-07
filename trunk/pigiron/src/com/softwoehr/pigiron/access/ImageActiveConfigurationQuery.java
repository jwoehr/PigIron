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
package com.softwoehr.pigiron.access;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.UnknownHostException;

/**
 *
 * @author jax
 */
public class ImageActiveConfigurationQuery {

    public static final String IMAGE_ACTIVE_CONFIGURATION_QUERY = "IMAGE_ACTIVE_CONFIGURATION_QUERY";
    private String hostname;
    private int port;
    private String userid;
    private String password;
    private String target_identifier;
    /* "inParams" as in "Into the Host" */
    private ParameterArray inParams;
    private ParameterArray outParams;
    private Connection connection;

    /**
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @return
     */
    public String getTarget_identifier() {
        return target_identifier;
    }

    /**
     *
     * @param target_identifier
     */
    public void setTarget_identifier(String target_identifier) {
        this.target_identifier = target_identifier;
    }

    /**
     *
     * @return
     */
    public String getUserid() {
        return userid;
    }

    /**
     *
     * @param userid
     */
    public void setUserid(String userid) {
        this.userid = userid;
    }

    /**
     *
     */
    public ImageActiveConfigurationQuery() {
    }

    /**
     *
     * @param hostname
     * @param port
     * @param userid
     * @param password
     * @param target_identifier
     */
    public ImageActiveConfigurationQuery(String hostname, int port, String userid, String password, String target_identifier) {
        this();
        this.hostname = hostname;
        this.port = port;
        this.userid = userid;
        this.password = password;
        this.target_identifier = target_identifier;
    }

    /**
     *
     * "Input" as in "input to SMAPI".
     * @return
     * @see
     */
    protected ParameterArray composeInputArray() {
        inParams = new ParameterArray();
        inParams.add(new VSMString(IMAGE_ACTIVE_CONFIGURATION_QUERY));
        inParams.add(new VSMString(userid));
        inParams.add(new VSMString(password));
        inParams.add(new VSMString(target_identifier));
        return inParams;
    }

    /**
     *
     * @param out
     * @throws java.io.IOException
     * @see
     */
    protected void writeInput(DataOutputStream out) throws IOException {
        composeInputArray().writeAll(out);
    }

    /**
     *
     * "Input" as in "input to SMAPI".
     * @return
     * @see
     */
    protected ParameterArray composeOutputArray() {
        outParams = new ParameterArray();
        outParams.add(new VSMInt4(-1, "request_id_immediate"));
        outParams.add(new VSMInt4(-1, "output_length"));
        outParams.add(new VSMInt4(-1, "request_id"));
        outParams.add(new VSMInt4(-1, "return_code"));
        outParams.add(new VSMInt4(-1, "reason_code"));
        outParams.add(new VSMInt4(-1, "memory_size"));
        outParams.add(new VSMInt1(-1, "memory_unit"));
        outParams.add(new VSMInt1(-1, "share_type"));
        outParams.add(new VSMInt4(-1, "share_value_length"));
        outParams.add(new VSMInt4(-1, "number_CPUs"));
        outParams.add(new VSMInt4(-1, "cpu_info_array_length"));
        // outParams.add(new CpuInfoArray());
        return outParams;
    }

    /**
     *
     * @param in
     * @throws java.io.IOException
     * @see
     */
    protected void readOutput(DataInputStream in) throws IOException {
        // composeInputArray().readAll(in);
    }

    /**
     *
     * @throws UnknownHostException
     * @throws IOException
     */
    protected void connect() throws UnknownHostException, IOException {
        connection = new Connection(hostname, port);
        connection.connect();
    }

    /**
     *
     * @param hostname
     * @param port
     * @throws UnknownHostException
     * @throws IOException
     */
    protected void connect(String hostname, int port) throws UnknownHostException, IOException {
        this.hostname = hostname;
        this.port = port;
        connect();
    }

    /**
     *
     */
    protected void disconnect() {
        if (connection != null) {
            connection.disconnect();
        }
    }

    /**
     *
     * @return
     * @throws java.io.IOException
     */
    public ParameterArray doIt() throws IOException {
        /* This will hold return from SMAPI call */
        ParameterArray outputParameters = new ParameterArray();
        composeInputArray();
        connect();
        writeInput(connection.getOutputStream());
        /* readOutput(outputParameters) */
        disconnect();
        return outputParameters;
    }
}

/**
 * Input Parameters:

        input_length
        function_name_length
        function_name
        authenticated_userid_length
        authenticated_userid
        password_length
        password
        target_identifier_length
        target_identifier

Response 1 – Immediate Request Verification:

        request_id

Response 2 – Output Parameters:

        output_length
        request_id
        return_code
        reason_code
        memory_size
        memory_unit
        share_type
        share_value_length
        share_value
        number_CPUs
        CPU_info_array_length
        CPU_info_array (1)

            CPU_info_structure_length
            CPU_info_structure (2)

                CPU_number
                CPU_id_length
                CPU_id
                CPU_status

        device_info_array_length
        device_info_array (1)

            device_info_structure_length
            device_info_structure (2)

                device_type
                device_address_length
                device_address


 */