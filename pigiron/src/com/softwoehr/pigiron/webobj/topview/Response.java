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

import org.json.JSONException;
import org.json.JSONObject;

/**
 * A class representing a completed request to PigIron
 *
 * @author     jax
 * @created    November 30, 2008
 */
public class Response extends JSONObject {

    public enum Results { NO_RESPONSE, SUCCESS, FAILURE, JSON_ERR, PIGIRON_ERR } ;

    /**
     *  Names we use for members
     */ 
    public static final String []names = {"result" ,"messageText" ,"requestor"} ;

    /**
     * Create a default (empty) Response
     *
     * @exception  JSONException        on JSON err
     * @throws  org.json.JSONException  on JSON err
     */ 
    public Response() throws JSONException {
        super();
        initDefaults();
    }

    /**
     * Create a Response around a Requestor
     *
     * @exception  JSONException        on JSON err
     * @throws  org.json.JSONException  on JSON err
     */ 
    public Response(Requestor requestor) throws JSONException {
        this();
	setRequestor(requestor);
    }

    /**
     * Constructor for the Response from a JSONObject using only
     * the members named in Response.names
     *
     * @param  aResponse               a requestor object or something like it
     * @exception  JSONException        on JSON err
     * @throws  org.json.JSONException  on JSON err
     */ 
    public Response(JSONObject aResponse) throws JSONException {
        super(aResponse,names);
    }

    /**
     *Constructor for the Response from a string of JSON representation
     *
     * @param  jsonRepresentation  requestor described in JSON
     * @exception  JSONException   on JSON err
     */ 
    public Response(String jsonRepresentation) throws JSONException {
        super(jsonRepresentation);
    }

    /**
     *  Gets the result attribute of the Response object
     *
     * @return                    The result value
     * @exception  JSONException  on JSON err
     */ 
    public int getResult() throws JSONException {
        return getInt("result");
    }

    /**
     *  Sets the result attribute of the Response object
     *
     * @param  result               The new result value
     * @exception  JSONException  on JSON err
     */ 
    public void setResult(int result) throws JSONException {
        put("result", result);
    }

    /**
     *  Gets the messageText attribute of the Response object
     *
     * @return                    The messageText value
     * @exception  JSONException  on JSON err
     */ 
    public String getMessageText() throws JSONException {
        return getString("messageText");
    }

    /**
     *  Sets the messageText attribute of the Response object
     *
     * @param  messageText               The new messageText value
     * @exception  JSONException  on JSON err
     */ 
    public void setMessageText(String messageText) throws JSONException {
        put("messageText", messageText);
    }
 
    /**
     *  Gets the requestor attribute of the Response object
     *
     * @return                    The requestor value
     * @exception  JSONException  on JSON err
     */ 
    public Requestor getRequestor() throws JSONException {
        return new Requestor(getJSONObject("requestor"));
    }

    /**
     *  Sets the requestor attribute of the Response object
     *
     * @param  requestor               The new requestor value
     * @exception  JSONException  on JSON err
     */ 
    public void setRequestor(Requestor requestor) throws JSONException {
        put("requestor", requestor);
    }
 
    /**
     *  init defaults
     *
     * @exception  JSONException  on JSON err
     */ 
    private void initDefaults() throws JSONException {
        put("result", Results.NO_RESPONSE);
        put("messageText", "");
        put("requestor", new Requestor());
    }
}

