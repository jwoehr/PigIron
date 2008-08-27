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
package com.softwoehr.pigiron.webobj;

import java.net.URI;

/**
 * A class to represent the most simple view of a VSMAPI Host.
 * @author jax
 */
public class Host {

    private String hostName = null;
    private URI hostURI = null;

    /**
     * Get URI for deeper examination of this host.
     * @return the URI for deeper examination of this host.
     */
    public URI getHostURI() {
        return hostURI;
    }

    /**
     * Set URI for deeper examination of this host.
     * @param hostURI the URI for deeper examination of this host
     */
    public void setHostURI(URI hostURI) {
        this.hostURI = hostURI;
    }

    /**
     * Host name as represented to a Web application.
     * May or may not be related to DNS name .. that latter
     * sort of thing will be found deeper, follow the URI trail.
     * @return host name
     */
    public String getHostName() {
        return hostName;
    }

    /**
     * Host name as represented to a Web application.
     * May or may not be related to DNS name .. that latter
     * sort of thing will be found deeper, follow the URI trail.
     * @param hostName
     */
    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    /**
     * Base ctor
     */
    public Host() {
    }

    /**
     * Instance with host name and URI
     * @param hostName the host name as represented to a Web application.
     * @param hostURI URI for deeper examination of this host.
     */
    public Host(String hostName, URI hostURI) {
        this();
        setHostName(hostName);
        setHostURI(hostURI);
    }
}
