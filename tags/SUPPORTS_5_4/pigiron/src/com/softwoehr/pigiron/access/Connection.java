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
public interface Connection {

    /**
     * Get the input stream to the socket.
     * @return the input stream to the socket
     */
    public DataInputStream getInputStream();

    /**
     * Get the output stream from the socket.
     * @return the output stream from the socket
     */
    public DataOutputStream getOutputStream();

    /**
     * Instance the input stream.
     * @param inputStream the input stream to assign to this Connection
     */
    public void setInputStream(DataInputStream inputStream);

    /**
     * Instance the output stream.
     * @param outputStream the output stream to assign to this Connection
     */
    public void setOutputStream(DataOutputStream outputStream);

    /**
     * Get the name of the Host to which this Connection pertains.
     * @return the name of the Host to which this Connection pertains
     */
    public String getHostname();

    /**
     * Set the name of the Host to which this Connection pertains.
     * @param hostname the name of the Host to which this Connection pertains
     */
    public void setHostname(String hostname);

    /**
     * Get the number of the Host port to which this Connection pertains.
     * @return the number of the Host port to which this Connection pertains
     */
    public int getPort();

    /**
     * Set the number of the Host port to which this Connection pertains.
     * @param port the number of the Host port to which this Connection pertains
     */
    public void setPort(int port);

    /**
     * Get the socket to which this Connection pertains.
     * @return the socket to which this Connection pertains
     */
    public Socket getSocket();

    /**
     * Establish the connection to the Host VSMAPI.
     * @throws UnknownHostException if the hostname can't be found
     * @throws IOException if there is an I/O error in connecting
     */
    public void connect() throws UnknownHostException, IOException;

    /**
     * Disestablish the connection to the Host VSMAPI.
     */
    public void disconnect();

    /**
     * True if currently connected.
     * @return true if currently connected, false otherwise.
     */
    public boolean isConnected();
}
