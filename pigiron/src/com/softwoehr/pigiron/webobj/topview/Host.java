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
        setNames(new String []{"name" ,"dns_name" ,"ip_addr" ,"port_number"}
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
     * @param  dns_name
     * @param  ip_addr
     * @param  port_number
     * @exception  JSONException  Description of the Exception
     * @throws  JSONException
     */ 
    public Host(String name, String dns_name, String ip_addr, int port_number) throws JSONException {
        super();
	/* Debug */  System.err.println("Host ctor, dns_name: " + dns_name + " ip_addr : " + ip_addr);
        setName(name);
        setDnsName(dns_name);
        setIpAddr(ip_addr);
        setPortNumber(port_number);
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
     *Constructor for the Host from a like JSONObject using only
     * the members constrained
     *
     * @param  anHost             a like Host as a JSONObject
     * @exception  JSONException  on JSON err
     */ 
    public Host(JSONObject anHost) throws JSONException {
	this(anHost.toString());	
        // super(anHost);
	// /* Debug */ System.out.println("anHost in Host(JSONObject anHost): " + anHost);
	// /* Debug */ System.out.println("this in Host(JSONObject anHost): " + this);
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
        return getString("dns_name");
    }

    /**
     * get IpAddr
     *
     * @return                          Ip Addr
     * @exception  JSONException        Description of the Exception
     * @throws  org.json.JSONException  on JSON err
     */ 
    public String getIpAddr() throws JSONException {
        return getString("ip_addr");
    }

    /**
     * get PortNumber
     *
     * @return                          port Number
     * @exception  JSONException        Description of the Exception
     * @throws  org.json.JSONException  on JSON err
     */ 
    public int getPortNumber() throws JSONException {
        return getInt("port_number");
    }

    /**
     * get Dns Name
     *
     * @param  dns_name
     * @throws  JSONException  on JSON err
     */ 
    public void setDnsName(String dns_name) throws JSONException {
        put("dns_name", dns_name);
    }

    /**
     * set Ip Addr
     *
     * @param  ip_addr
     * @throws  JSONException  on JSON err
     */ 
    public void setIpAddr(String ip_addr) throws JSONException {
        put("ip_addr", ip_addr);
    }

    /**
     * set Port Number
     *
     * @param  port_number      The new port_number value
     * @throws  JSONException  on JSON err
     */ 
    public void setPortNumber(int port_number) throws JSONException {
        put("port_number", port_number);
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

