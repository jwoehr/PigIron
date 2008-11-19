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
import javax.net.ssl.SSLSocketFactory;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLContext;

/**
 * Represents the IP SSL Socket connection to the SMAPI Host.
 * NOT TESTED
 * @see com.softwoehr.pigiron.functions.VSMCall
 * @author jax
 */
public class SSLSocketConnection extends SocketConnection {

    /**
     * Create a <tt>Connection</tt> ready to <tt>connect</tt> to
     * a hostname and port.
     * @param hostname the DNS name of the Host
     * @param port the port to which SMAPI is listening on the Host
     */
    public SSLSocketConnection(String hostname, int port) {
       super(hostname, port);
    }

    /**
     * Establish the connection to the Host VSMAPI, this time as SSL
     * @throws UnknownHostException if the hostname can't be found
     * @throws IOException if there is an I/O error in connecting
     */
    public void connect() throws UnknownHostException, IOException {
        // /* Debug */ System.out.println("Connection.connect ... host and port are " + hostname + port);
        setSocket(SSLSocketFactory.getDefault().createSocket(hostname, port));
        setOutputStream(new DataOutputStream(new BufferedOutputStream(socket.getOutputStream())));
        setInputStream(new DataInputStream(new BufferedInputStream(socket.getInputStream())));
    }
}