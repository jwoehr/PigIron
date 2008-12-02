/*
 *  Copyright (c) 2008, Jack J. Woehr jwoehr@softwoehr.com
 *  PO Box 51, Golden, Colorado 80402-0051 USA
 *  All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions
 *  are met:
 *
 *  * Redistributions of source code must retain the above copyright
 *  notice, this list of conditions and the following disclaimer.
 *  * Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer
 *  in the documentation and/or other materials provided with the
 *  distribution.
 *  * Neither the name of the PigIron Project nor the names of its
 *  contributors may be used to endorse or promote products derived
 *  from this software without specific prior written permission.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 *  AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 *  IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 *  ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 *  LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 *  SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 *  INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 *  CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 *  ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 *  THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.softwoehr.pigiron.webobj.topview;

import com.softwoehr.pigiron.webobj.WebObject;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A class to represent the JSON view of a VSMAPI Host.
 *
 * @author     jax
 */
public class Host extends WebObject {

    static {
        setNames(new String []{"name" ,"dnsName" ,"ipAddr" ,"portNumber"}
       ); 
    }

    /**
     * Base ctor
     *
     * @exception  JSONException  Description of the Exception
     * @throws  JSONException
     */ 
    public Host() throws JSONException {
        this("","","", - 1);
    }

    /**
     * Instance with hostName name and URI
     *
     * @param  name
     * @param  dnsName
     * @param  ipAddr
     * @param  portNumber
     * @exception  JSONException  Description of the Exception
     * @throws  JSONException
     */ 
    public Host(String name, String dnsName, String ipAddr, int portNumber) throws JSONException {
        super();
        setName(name);
        setDnsName(dnsName);
        setIpAddr(ipAddr);
        setPortNumber(portNumber);
    }

    /**
     *Constructor for the Host from a string of JSON representation
     *
     * @param  jsonRepresentation  argument described in JSON
     * @exception  JSONException   on JSON err
     * @throws  JSONException      on JSON err
     */ 
    public Host(String jsonRepresentation) throws JSONException {
        super(jsonRepresentation);
    }

    /**
     *Constructor for the Host from a like WebObject using only
     * the members constrained
     *
     * @param  anHost             a like Host
     * @exception  JSONException  on JSON err
     */ 
    public Host(WebObject anHost) throws JSONException {
        super(anHost);
    }

    /**
     * get Name
     *
     * @return                          name
     * @exception  JSONException        Description of the Exception
     * @throws  org.json.JSONException  on JSON err
     */ 
    public String getName() throws JSONException {
        return getString("name");
    }

    /**
     * get DnsName
     *
     * @return                          Dns Name
     * @exception  JSONException        Description of the Exception
     * @throws  org.json.JSONException  on JSON err
     */ 
    public String getDnsName() throws JSONException {
        return getString("dnsName");
    }

    /**
     * get IpAddr
     *
     * @return                          Ip Addr
     * @exception  JSONException        Description of the Exception
     * @throws  org.json.JSONException  on JSON err
     */ 
    public String getIpAddr() throws JSONException {
        return getString("ipAddr");
    }

    /**
     * get PortNumber
     *
     * @return                          port Number
     * @exception  JSONException        Description of the Exception
     * @throws  org.json.JSONException  on JSON err
     */ 
    public int getPortNumber() throws JSONException {
        return getInt("portNumber");
    }

    /**
     * get Dns Name
     *
     * @param  dnsName
     * @throws  JSONException  on JSON err
     */ 
    public void setDnsName(String dnsName) throws JSONException {
        put("dnsName", dnsName);
    }

    /**
     * set Ip Addr
     *
     * @param  ipAddr
     * @throws  JSONException  on JSON err
     */ 
    public void setIpAddr(String ipAddr) throws JSONException {
        put("ipAddr", ipAddr);
    }

    /**
     * set Port Number
     *
     * @param  portNumber      The new portNumber value
     * @throws  JSONException  on JSON err
     */ 
    public void setPortNumber(int portNumber) throws JSONException {
        put("portNumber", portNumber);
    }

    /**
     * Host name as represented to a Web application.
     * May or may not be related to DNS name .. that latter
     * sort of thing will be found deeper, follow the URI trail.
     *
     * @param  name            the name of the name
     * @throws  JSONException  on JSON err
     */ 
    public void setName(String name) throws JSONException {
        put("name", name);
    }
}

