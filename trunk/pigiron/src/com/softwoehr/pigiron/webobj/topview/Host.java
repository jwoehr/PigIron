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
package com.softwoehr.pigiron.webobj.topview;

import com.softwoehr.pigiron.webobj.*;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * A class to represent the most simple view of a VSMAPI Host.
 * @author jax
 */
public class Host extends MarshallableObject {

    public String name = null;
    public URI uri = null;

    /**
     * Get URI for deeper examination of this hostName.
     * @return the URI for deeper examination of this hostName.
     */
    public URI getUri() {
        return uri;
    }

    /**
     * Set URI for deeper examination of this hostName.
     * @param uri the URI for deeper examination of this hostName
     */
    public void setUri(URI href) {
        this.uri = href;
    }

    /**
     * Host name as represented to a Web application.
     * May or may not be related to DNS name .. that latter
     * sort of thing will be found deeper, follow the URI trail.
     * @return hostName name
     */
    public String getName() {
        return name;
    }

    /**
     * Host name as represented to a Web application.
     * May or may not be related to DNS name .. that latter
     * sort of thing will be found deeper, follow the URI trail.
     * @param name the name of the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Base ctor
     */
    public Host() {
        super();
    }

    /**
     * Instance with hostName name and URI
     * @param name
     * @param uri
     */
    public Host(String name, URI href) {
        this();
        setName(name);
        setUri(href);
    }

    /**
     * Test run of marshalling
     * @param argv
     * @throws java.net.URISyntaxException
     */
    public static void main(String[] argv) throws URISyntaxException {
        String hostName = argv[0];
        String hrefString = argv[1];
        String representation = "{" + "\"name\":" + "\"" + hostName + "\"" + "," + "\"href\":" + "\"" + hrefString + "\"" + "}";
        Host host = new Host("MYZSYS", new URI("http://192.168.0.99/topview/MYZSYS"));
        Marshaller marshaller = new JSONMarshaller();
        System.out.println("Here is the string representation of Host at present: " + host.toRepresentation(marshaller));
        System.out.println("Here is the string representation you submitted: " + representation);
        host.fromRepresentation(representation, marshaller);
        System.out.println("Here is the Host after marshalling in your values: ");
        System.out.println("host.name == " + host.getName());
        System.out.println("host.href == " + host.getUri().toString());
        System.out.println("Here is the string representation of Host at present: " + host.toRepresentation(marshaller));
    }

    @Override
    public String[] names() {
        return new String[]{"name", "href"};
    }
}
