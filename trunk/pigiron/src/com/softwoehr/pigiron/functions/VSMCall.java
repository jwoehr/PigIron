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

import com.softwoehr.pigiron.access.Connection;
import com.softwoehr.pigiron.access.ParameterArray;
import com.softwoehr.pigiron.access.VSMException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.UnknownHostException;

/**
 * 
 * @author jax
 */
public abstract class VSMCall {

    public VSMCall() {
    }
    private String hostname;
    private int port;
    private String userid;
    private String password;
    /* "inParams" as in "Into the Host" */
    private ParameterArray inParams;
    private ParameterArray outParams;
    private Connection connection;
    private String target_identifier;

    /**
     *
     * "Input" as in "input to VSMAPI".
     * @return
     * @see
     */
    protected abstract ParameterArray composeInputArray();

    /**
     *
     * "output" as in "output from VSMAPI"
     * @return
     * @see
     */
    protected abstract ParameterArray composeOutputArray();

    protected void connect() throws UnknownHostException, IOException {
        connection = new Connection(hostname, port);
        connection.connect();
    }

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
     * @throws com.softwoehr.pigiron.access.VSMException
     */
    public ParameterArray doIt() throws IOException, VSMException {
        /* This will hold return from VSMAPI call */
        composeInputArray();
        composeOutputArray();
        connect();
        writeInput(connection.getOutputStream());
        readOutput(connection.getInputStream());
        disconnect();
        return outParams;
    }

    /**
     *
     * @return
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * 
     * @return
     */
    public abstract String getFunctionName();

    /**
     *
     * @return
     */
    public String getHostname() {
        return hostname;
    }

    /**
     *
     * @return
     */
    public ParameterArray getInParams() {
        return inParams;
    }

    /**
     *
     * @return
     */
    public ParameterArray getOutParams() {
        return outParams;
    }

    /**
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @return
     */
    public int getPort() {
        return port;
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
     * @return
     */
    public String getUserid() {
        return userid;
    }

    /**
     *
     * @param in
     * @throws java.io.IOException
     * @throws com.softwoehr.pigiron.access.VSMException
     */
    protected void readOutput(DataInputStream in) throws IOException, VSMException {
        outParams.readAll(in);
    }

    /**
     *
     * @param connection
     */
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    /**
     *
     * @param hostname
     */
    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    /**
     *
     * @param inParams
     */
    public void setInParams(ParameterArray inParams) {
        this.inParams = inParams;
    }

    /**
     *
     * @param outParams
     */
    public void setOutParams(ParameterArray outParams) {
        this.outParams = outParams;
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
     * @param port
     */
    public void setPort(int port) {
        this.port = port;
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
     * @param userid
     */
    public void setUserid(String userid) {
        this.userid = userid;
    }

    /**
     *
     * @param out
     * @throws java.io.IOException
     */
    protected void writeInput(DataOutputStream out) throws IOException {
        inParams.writeAll(out);
    }
}
