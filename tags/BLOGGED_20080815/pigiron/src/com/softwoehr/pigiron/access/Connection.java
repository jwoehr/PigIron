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

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Represents the IP Socket connection to the SMAPI Host.
 * @see com.softwoehr.pigiron.functions.VSMCall
 * @author jax
 */
public class Connection {

    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    private Socket socket;
    private String hostname;
    private int port;

    /**
     * Create a <tt>Connection</tt> ready to <tt>connect</tt> to
     * a hostname and port.
     * @param hostname the DNS name of the Host
     * @param port the port to which SMAPI is listening on the Host
     */
    public Connection(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }

    /**
     * Get the input stream to the socket.
     * @return the input stream to the socket
     */
    public DataInputStream getInputStream() {
        return inputStream;
    }

    /**
     * Get the output stream from the socket.
     * @return the output stream from the socket
     */
    public DataOutputStream getOutputStream() {
        return outputStream;
    }

    /**
     * Instance the input stream.
     * @param inputStream the input stream to assign to this Connection
     */
    public void setInputStream(DataInputStream inputStream) {
        this.inputStream = inputStream;
    }

    /**
     * Instance the output stream.
     * @param outputStream the output stream to assign to this Connection
     */
    public void setOutputStream(DataOutputStream outputStream) {
        this.outputStream = outputStream;
    }

    /**
     * Get the name of the Host to which this Connection pertains.
     * @return the name of the Host to which this Connection pertains
     */
    public String getHostname() {
        return hostname;
    }

    /**
     * Set the name of the Host to which this Connection pertains.
     * @param hostname the name of the Host to which this Connection pertains
     */
    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    /**
     * Get the number of the Host port to which this Connection pertains.
     * @return the number of the Host port to which this Connection pertains
     */
    public int getPort() {
        return port;
    }

    /**
     * Set the number of the Host port to which this Connection pertains.
     * @param port the number of the Host port to which this Connection pertains
     */
    public void setPort(int port) {
        this.port = port;
    }

    /**
     * Get the socket to which this Connection pertains.
     * @return the socket to which this Connection pertains
     */
    public Socket getSocket() {
        return socket;
    }

    /**
     * Set the socket to which this Connection pertains.
     * @param socket the socket to which this Connection pertain
     */
    protected void setSocket(Socket socket) {
        this.socket = socket;
    }

    /**
     * Establish the connection to the Host VSMAPI.
     * @throws UnknownHostException if the hostname can't be found
     * @throws IOException if there is an I/O error in connecting
     */
    public void connect() throws UnknownHostException, IOException {
        // /* Debug */ System.out.println("Connection.connect ... host and port are " + hostname + port);
        socket = new Socket(hostname, port);
        outputStream = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
        inputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
    }

    /**
     * Disestablish the connection to the Host VSMAPI.
     */
    public void disconnect() {
        try {
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
        try {
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
        try {
            socket.close();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    /**
     * True if currently connected.
     * @return true if currently connected, false otherwise.
     */
    public boolean isConnected() {
        return socket.isConnected();
    }
}
