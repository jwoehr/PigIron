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
import com.softwoehr.pigiron.access.SocketConnection;
import com.softwoehr.pigiron.access.SSLSocketConnection;
import com.softwoehr.pigiron.access.VSMException;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;

/**
 * Abstract base class of all the class embodiments of VSMAPI fuction calls.
 *
 * Private data stored in the base and accessed via accessors in this and the
 * subclasses (the actual VSMAPI function objects) include:
 * <ul>
 * <li>hostname</li>
 * <li>port</li>
 * <li>userid</li>
 * <li>password</li>
 * <li>target identifier</li>
 * </ul>
 *
 * @author jax
 */
public abstract class VSMCall {

    /**
     * PigIron Version String
     */
    public static final String PIGIRON_RELEASE_VERSION = "0.9.7";

    /**
     * ctor/0
     */
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
     * Compose the array of input params to be transmitted in order to the
     * VSMAPI Host. Subclass are the actual function calls which implement
     * composeInputArray() to their needs. "Input" as in "input to VSMAPI".
     *
     * @return ParameterArray of input params to be transmitted in order to the
     * VSMAPI Host
     * @see #composeOutputArray()
     */
    protected abstract ParameterArray composeInputArray();

    /**
     * Compose the array of output params to be read in order from the VSMAPI
     * Host. Subclass are the actual function calls which implement
     * composeInputArray() to their needs. "output" as in "output from VSMAPI"
     *
     * @return ParameterArray of input params to be read in order from the
     * VSMAPI Host
     * @see #composeInputArray()
     */
    protected abstract ParameterArray composeOutputArray();

    /**
     * Create a new connection to the VSMAPI Host and open it.
     *
     * @throws java.net.UnknownHostException if hostname can't be found
     * @throws java.io.IOException if error in connect.
     * @see com.softwoehr.pigiron.access.Connection
     */
    protected void connect() throws UnknownHostException, IOException {
        connection = new SocketConnection(hostname, port);
        connection.connect();
    }

    /**
     * Create a new connection to the VSMAPI Host and open it in either SSL mode
     * or plain socket depending on boolean.
     *
     * @param ssl if true, connect in SSL mode, plain socket otherwise
     * @throws java.net.UnknownHostException if hostname can't be found
     * @throws java.io.IOException if error in connect.
     * @see com.softwoehr.pigiron.access.Connection
     */
    protected void connect(boolean ssl) throws UnknownHostException, IOException {
        if (ssl) {
            connection = new SSLSocketConnection(hostname, port);
        } else {
            connection = new SocketConnection(hostname, port);
        }
        connection.connect();
    }

    /**
     * Create a new connection to the VSMAPI Host and open it.
     *
     * @param hostname the name of the Host to which to connect
     * @param port the number of the Host port to which to connect
     * @throws java.net.UnknownHostException if hostname can't be found
     * @throws java.io.IOException if error in connect.
     * @see com.softwoehr.pigiron.access.Connection
     */
    protected void connect(String hostname, int port) throws UnknownHostException, IOException {
        this.hostname = hostname;
        this.port = port;
        connect();
    }

    /**
     * Create a new connection to the VSMAPI Host and open it in either plain
     * socket or SSL socket mode dependingon boolean.
     *
     * @param hostname the name of the Host to which to connect
     * @param port the number of the Host port to which to connect
     * @param ssl if true, connect in SSL mode, plain socket otherwise
     * @throws java.net.UnknownHostException if hostname can't be found
     * @throws java.io.IOException if error in connect.
     * @see com.softwoehr.pigiron.access.Connection
     */
    protected void connect(String hostname, int port, boolean ssl) throws UnknownHostException, IOException {
        this.hostname = hostname;
        this.port = port;
        connect(ssl);
    }

    /**
     * Disconnect from the Host.
     *
     * @see com.softwoehr.pigiron.access.Connection
     */
    protected void disconnect() {
        if (connection != null) {
            connection.disconnect();
        }
    }

    /**
     * Run the VSMAPI call and return its output parameters. This is the big one
     * after all the instance's private data has been set and the input and
     * output parameters have been composed.
     *
     * @return ParameterArray of output paramters resulting from the VSMAPI call
     * @throws java.io.IOException if there is a communication error
     * @throws com.softwoehr.pigiron.access.VSMException if there is an error in
     * parameter reading/writing
     */
    public ParameterArray doIt() throws IOException, VSMException {
        composeInputArray();
        composeOutputArray();
        connect();
        writeInput(connection.getOutputStream());
        readOutput(connection.getInputStream());
        disconnect();
        return outParams;
    }

    /**
     * Run the VSMAPI call and return its output parameters. This is the big one
     * after all the instance's private data has been set and the input and
     * output parameters have been composed.
     *
     * @param ssl if true, connect in SSL mode, plain socket otherwise
     * @return ParameterArray of output paramters resulting from the VSMAPI call
     * @throws java.io.IOException if there is a communication error
     * @throws com.softwoehr.pigiron.access.VSMException if there is an error in
     * parameter reading/writing
     */
    public ParameterArray doIt(boolean ssl) throws IOException, VSMException {
        /* This will hold return from VSMAPI call */
        composeInputArray();
        composeOutputArray();
        connect(ssl);
        writeInput(connection.getOutputStream());
        readOutput(connection.getInputStream());
        disconnect();
        return outParams;
    }

    /**
     * Get the Connection object currently assigned to the VSMCall
     *
     * @return the Connection object currently assigned to the VSMCall or
     * <tt>null</tt>
     * @see com.softwoehr.pigiron.access.Connection
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Get the string name of the VSMAPI Function exactly as it will be sent to
     * call that function. This name is implemented static public in each class
     * extending VSMCall.
     *
     * @return the string name of the VSMAPI Function
     */
    public abstract String getFunctionName();

    /**
     * Return the name of the Host which will be connected to by the instance of
     * the function call.
     *
     * @return the name of the Host which will be connected to by the instance
     */
    public String getHostname() {
        return hostname;
    }

    /**
     * Get the array of input parameters to the VSMAPI function. These are
     * instanced in <tt>composeInputArray</tt>.
     *
     * @return the array of input parameters to the VSMAPI function
     * @see #composeInputArray()
     */
    public ParameterArray getInParams() {
        return inParams;
    }

    /**
     * Get the array of input parameters to the VSMAPI function.
     *
     * @return the array of output parameters from the VSMAPI function
     * @see #composeOutputArray()
     */
    public ParameterArray getOutParams() {
        return outParams;
    }

    /**
     * Get password to be used to connect to the VSMAPI Host.
     *
     * @return password to be used to connect to the VSMAPI Host
     */
    public String getPassword() {
        return password;
    }

    /**
     * Get the number of the Host port to which the call instance will connect
     * on.
     *
     * @return the number of the Host port to which the call instance will
     * connect on
     */
    public int getPort() {
        return port;
    }

    /**
     * Get the target identifier, an input param representing the object of the
     * function for most VSMAPI fuction calls.
     *
     * @return the target identifier representing the object of the function
     * call
     */
    public String getTarget_identifier() {
        return target_identifier;
    }

    /**
     * Get the userid making the function call.
     *
     * @return userid making the function call
     */
    public String getUserid() {
        return userid;
    }

    /**
     * Read all from the network and build a new stream for the subsequent
     * protocol parsing.
     *
     * @param socketIn the net connection to SMAPI
     * @return a data input stream for our protocol parsing
     * @throws IOException
     */
    public DataInputStream receiveAll(DataInputStream socketIn) throws IOException {
        DataInputStream dis;
        int request_id = socketIn.readInt();
        int output_length = socketIn.readInt();
        ByteBuffer bB = ByteBuffer.allocate(8);
        // bB.order(ByteOrder.BIG_ENDIAN); // this is the default for ByteBuffer
        bB.putInt(0, request_id);
        bB.putInt(4, output_length);
        byte[] bA = new byte[output_length + 8];
        bB.get(bA, 0, 8);
        int iHaveRead = 0;
        int numRead;
        while (iHaveRead < output_length) {
            numRead = socketIn.read(bA, iHaveRead + 8, output_length - iHaveRead);
            iHaveRead += numRead;
            if (numRead < 1) {
                break;
            }
        }
        dis = new DataInputStream((new ByteArrayInputStream(bA)));
        return dis;
    }

    /**
     * Read the output of the VSMAPI function call into the individual params as
     * ordered in the output params array modeled by the function instance. The
     * entire output of the call was already received from the network in
     * VSMCall.receiveAll.
     *
     * @param in the DataInputStream to read
     * @throws java.io.IOException on communication error
     * @throws com.softwoehr.pigiron.access.VSMException on parameter
     * composition error
     * @see #composeOutputArray()
     * @see #writeInput(java.io.DataOutputStream)
     */
    protected void readOutput(DataInputStream in) throws IOException, VSMException {
        outParams.readAll(receiveAll(in));
    }

    /**
     * Instance the Connection object used to communicate to the VSMAPI Host.
     *
     * @param connection the Connection object used to communicate to the VSMAPI
     * Host
     * @see com.softwoehr.pigiron.access.Connection
     */
    public final void setConnection(Connection connection) {
        this.connection = connection;
    }

    /**
     * Set the name of the Host which will be connected to by the instance of
     * the function call.
     *
     * @param hostname the name of the Host which will be connected to by the
     * instance
     */
    public final void setHostname(String hostname) {
        this.hostname = hostname;
    }

    /**
     * Instance the input parameter array which holds the params to be sent to
     * VSMAPI Host.
     *
     * @param inParams the input parameter array which holds the params to be
     * sent to VSMAPI Host
     * @see #composeInputArray()
     */
    public final void setInParams(ParameterArray inParams) {
        this.inParams = inParams;
    }

    /**
     * Instance the output parameter array which holds the params to be read, or
     * which have been read, from the VSMAPI Host.
     *
     * @param outParams output parameter array which holds the params to be
     * read, or which have been read, from the VSMAPI Host
     */
    public final void setOutParams(ParameterArray outParams) {
        this.outParams = outParams;
    }

    /**
     * Set password to be used to connect to the VSMAPI Host.
     *
     * @param password password to be used to connect to the VSMAPI Host
     */
    public final void setPassword(String password) {
        this.password = password;
    }

    /**
     * Set the number of the Host port to which the call instance will connect
     * on.
     *
     * @param port the number of the Host port to which the call instance will
     * connect on
     */
    public final void setPort(int port) {
        this.port = port;
    }

    /**
     * Get the target identifier, an input param representing the object of the
     * function for most VSMAPI fuction calls.
     *
     * @param target_identifier an input param representing the object of the
     * function for most VSMAPI fuction calls
     */
    public final void setTarget_identifier(String target_identifier) {
        this.target_identifier = target_identifier;
    }

    /**
     * Set the userid making the function call.
     *
     * @param userid the userid making the function call.
     */
    public final void setUserid(String userid) {
        this.userid = userid;
    }

    /**
     * Write out all the input params to the VSMAPI function call as ordered in
     * the inpput params array composed by the function instance.
     *
     * @param out the DataOutputStream to write
     * @throws java.io.IOException on communication error
     * @see #composeInputArray()
     * @see #readOutput(java.io.DataInputStream)
     */
    protected void writeInput(DataOutputStream out) throws IOException {
        inParams.writeAll(out);
    }

    /**
     * Version string
     *
     * @return PigIron version string
     */
    public static final String version() {
        return PIGIRON_RELEASE_VERSION;
    }
}
