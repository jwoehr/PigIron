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
 *
 * @author jax
 */
public class Connection {

    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    private Socket socket;
    private String hostname;
    private int port;

    /**
     * 
     * @param hostname
     * @param portnumber
     */
    public Connection(String hostname, int portnumber) {
    }

    /**
     * 
     * @param out
     */
    public void output(ParameterArray out) {
    }

    /**
     * 
     * @param in
     */
    public void input(ParameterArray in) {
    }

    /**
     * 
     * @return
     */
    public DataInputStream getInputStream() {
        return inputStream;
    }

    /**
     * 
     * @return
     */
    public DataOutputStream getOutputStream() {
        return outputStream;
    }

    /**
     * 
     * @param inputStream
     */
    public void setInputStream(DataInputStream inputStream) {
        this.inputStream = inputStream;
    }

    /**
     * 
     * @param outputStream
     */
    public void setOutputStream(DataOutputStream outputStream) {
        this.outputStream = outputStream;
    }

    /**
     * 
     * @return
     */
    public String getHostname() {
        return hostname;
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
     * @return
     */
    public int getPort() {
        return port;
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
     * @return
     */
    public Socket getSocket() {
        return socket;
    }

    /**
     * 
     * @param socket
     */
    protected void setSocket(Socket socket) {
        this.socket = socket;
    }

    /**
     * 
     * @throws UnknownHostException
     * @throws IOException 
     */
    public void connect() throws UnknownHostException, IOException {
        socket = new Socket(hostname, port);
        outputStream = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
        inputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
    }

    /**
     * 
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
     * 
     * @return
     */
    public boolean isConnected() {
        return socket.isConnected();
    }
}
