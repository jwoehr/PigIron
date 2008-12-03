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
import java.util.Vector;
import com.softwoehr.pigiron.webobj.WebObject;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A class representing a complete request to PigIron
 *
 * @author     jax
 * @created    November 30, 2008
 */
public class Requestor extends WebObject {

    /**
     *  A Vector of the JSON keys (names) that are valid for
     * a given WebObject extender
     */ 
    private static Vector <String> names = setNames(new String []{"user" ,"host" ,"function"}); 
    
    /**
     *  Get the array of JSON keys (names) that are valid for
     * a given WebObject extender
     *
     * @return    The names
     */ 
    public String []getNames() {
        return names.toArray(new String [names.size()]);
    }

    /**
     * Identifies whether a JSON key is one of the names a given
     * WebObject extender uses.
     *
     * @param  name  the JSON key
     * @return true if the key is one the class uses
     */ 
     public  boolean isName(String name) {
	 return isName(name, names);
     }

    /**
     * Create a default (empty) Requestor
     *
     * @exception  JSONException        on JSON err
     * @throws  org.json.JSONException  on JSON err
     */ 
    public Requestor() throws JSONException {
        super();
        initDefaults();
    }

    /**
     * Constructor for the Requestor from a JSONObject using only
     * the members named in Requestor.names
     *
     * @param  aRequestor               a requestor object or something like it
     * @exception  JSONException        on JSON err
     * @throws  org.json.JSONException  on JSON err
     */ 
    public Requestor(Requestor aRequestor) throws JSONException {
        super(aRequestor);
    }

    /**
     * Constructor for the Requestor from a JSONObject using only
     * the members named in Requestor.names
     *
     * @param  aRequestor               a requestor object or something like it
     * @throws  org.json.JSONException  on JSON err
     */ 
    public Requestor(JSONObject aRequestor) throws JSONException {
        super(aRequestor, new String []{"user" ,"host" ,"function"});
    }

    /**
     *Constructor for the Requestor from a string of JSON representation
     *
     * @param  jsonRepresentation  requestor described in JSON
     * @exception  JSONException   on JSON err
     */ 
    public Requestor(String jsonRepresentation) throws JSONException {
        super(jsonRepresentation);
    }

    /**
     *  Gets the user attribute of the Requestor object
     *
     * @return                    The user value
     * @exception  JSONException  on JSON err
     */ 
    public User getUser() throws JSONException {
        return new User(getJSONObject("user"));
    }

    /**
     *  Sets the user attribute of the Requestor object
     *
     * @param  user               The new user value
     * @exception  JSONException  on JSON err
     */ 
    public void setUser(User user) throws JSONException {
        put("user", user);
    }

    /**
     *  Gets the host attribute of the Requestor object
     *
     * @return                    The host value
     * @exception  JSONException  on JSON err
     */ 
    public Host getHost() throws JSONException {
        return new Host(getJSONObject("host"));
    }

    /**
     *  Sets the host attribute of the Requestor object
     *
     * @param  host               The new host value
     * @exception  JSONException  on JSON err
     */ 
    public void setHost(Host host) throws JSONException {
        put("host", host);
    }

    /**
     *  Gets the function attribute of the Requestor object
     *
     * @return                    The function value
     * @exception  JSONException  on JSON err
     */ 
    public Function getFunction() throws JSONException {
        Function result = new Function(getJSONObject("function"));
	return result;
    }

    /**
     *  Sets the function attribute of the Requestor object
     *
     * @param  function           The new function value
     * @exception  JSONException  on JSON err
     */ 
    public void setFunction(Function function) throws JSONException {
        put("function", function);
    }

    /**
     *  init defaults
     *
     * @exception  JSONException  on JSON err
     */ 
    private void initDefaults() throws JSONException {
        put("user", new User());
        put("host", new Host());
        put("function", new Function());
    }
}

