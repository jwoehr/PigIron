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
 * {@code Response} represents the JSON-style response to a completed request to PigIron.
 * It contains embedded within it the spawning Requestor with the Requestor's response fields
 * filled in (e.g., function.output_arguments[]), as well as Response-specific
 * fields such as <tt>"result"</tt> and <tt>"messageText"</tt>.
 *
 * @author     jax
 * @created    November 30, 2008
 */
public class Response extends WebObject {

    /**
     *  A Vector of the JSON keys (names) that are valid for
     * a given WebObject extender
     */ 
    private static Vector <String> names = setNames(new String []{"result" ,"messageText" ,"requestor"}); 
    
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
    
    /** Represents the various semantics of a Response:
     * <ul><br>
     * <li>- success all the way through in making the VSMAPI call;<br></li>
     * <li>- failure returned by a VSMAPI call itself successfully propagated through the layers of PigIron code;<br></li>
     * <li>- error in formatting or content of the JSON value of the Requestor;<br></li>
     * <li>- error in the PigIron execution of a Requestor containing well-formed JSON;<br></li>
     * <li>- the value of the Response is at the moment undefined, e.g., in default state before use.</li>
     *</ul>
     */ 
    public static enum Results {    /** Undefined, the default state of the Response object before it used */ 
    NO_RESPONSE,    /** Your request succeeded, results returned in the embedded copy of the original Requestor. */ 
    SUCCESS,    /** Your request failed at the VSMAPI level, results returned in the embedded copy of the original Requestor. */ 
    FAILURE,    /** Something was wrong with the JSON input provided, see Response.messageText. */ 
    JSON_ERR,    /** PigIron encountered an error, see Response.messageText. */ 
    PIGIRON_ERR } ;

    /**
     * Create a default (empty) Response.
     *
     * @throws  org.json.JSONException  on JSON err
     */ 
    public Response() throws JSONException {
        super();
        initDefaults();
    }

    /**
     * Create a Response around a Requestor
     *
     * @throws  org.json.JSONException  on JSON err
     */ 
    public Response(Requestor requestor) throws JSONException {
        this();
        setRequestor(requestor);
    }

    /**
     * Constructor for the Response from a WebObject using only
     * the members named in Response.names
     *
     * @param  aResponse               a requestor object or something like it
     * @throws  org.json.JSONException  on JSON err
     */ 
    public Response(WebObject aResponse) throws JSONException {
        super(aResponse);
    }
    
    /**
     * Constructor for the Response from a JSONObject using only
     * the members named in Response.names
     *
     * @param  aResponse               a requestor object or something like it
     * @throws  org.json.JSONException  on JSON err
     */ 
    public Response(JSONObject aResponse) throws JSONException {
        super(aResponse, new String []{"result" ,"messageText" ,"requestor"});
    }
    
    /**
     *Constructor for the Response from a string of JSON representation
     *
     * @param  jsonRepresentation  requestor described in JSON
     * @throws  JSONException   on JSON err
     */ 
    public Response(String jsonRepresentation) throws JSONException {
        super(jsonRepresentation);
    }

    /**
     *  Gets the result attribute of the Response object.
     *
     * @return                    The result value
     * @throws  JSONException  on JSON err
     */ 
    public String getResult() throws JSONException {
        return getString("result");
    }

    /**
     *  Sets the result attribute of the Response object.
     *
     * @param  result               The new result value
     * @throws  JSONException  on JSON err
     */ 
    public void setResult(String result) throws JSONException {
        put("result", result);
    }

    /**
     *  Gets the messageText attribute of the Response object. Message text
     * can be VSMAPI return/reason code intrepetation, JSON or other exception
     * text, or PigIron error messages.
     *
     * @return                    The messageText value
     * @throws  JSONException  on JSON err
     */ 
    public String getMessageText() throws JSONException {
        return getString("messageText");
    }

    /**
     *  Sets the messageText attribute of the Response object. Message text
     * can be VSMAPI return/reason code intrepetation, JSON or other exception
     * text, or PigIron error messages.
     *
     * @param  messageText               The new messageText value
     * @throws  JSONException  on JSON err
     */ 
    public void setMessageText(String messageText) throws JSONException {
        put("messageText", messageText);
    }
 
    /**
     *  Gets a copy of the requestor attribute of the Response object.
     * This is the copy of the original Requestor that let to the Response
     *
     * @return                    The requestor value
     * @throws  JSONException  on JSON err
     */ 
    public Requestor getRequestor() throws JSONException {
        return new Requestor(getJSONObject("requestor"));
    }

    /**
     *  Sets the requestor attribute of the Response object.
     * This is the copy of the original Requestor that let to the Response.
     *
     * @param  requestor               The new requestor value
     * @throws  JSONException  on JSON err
     */ 
    public void setRequestor(Requestor requestor) throws JSONException {
        put("requestor", requestor);
    }
 
    /**
     *  Init the fields to a "no-news" default.
     *
     * @throws  JSONException  on JSON err
     */ 
    private void initDefaults() throws JSONException {
        put("result", Results.NO_RESPONSE);
        put("messageText", "");
        put("requestor", new Requestor());
    }
}

