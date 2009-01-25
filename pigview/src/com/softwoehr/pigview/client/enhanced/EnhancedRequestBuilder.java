/*
 *  Copyright (c) 2009, Jack J. Woehr jwoehr@softwoehr.com
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
package com.softwoehr.pigview.client.enhanced;

import com.google.gwt.http.client.*;

/**
 *  Description of the Class
 *
 * @author     jax
 * @created    January 19, 2009
 */
public class EnhancedRequestBuilder {

    private RequestCallback requestCallback = null;
    private RequestBuilder requestBuilder = null;
    /**
     *  Description of the Field
     */ 
    public RequestBuilder.Method httpMethod = null;
    /**
     *  Description of the Field
     */ 
    public String url = "";
    private String parameters = "?";

    /**
     *Constructor for the EnhancedRequestBuilder object
     *
     * @param  httpMethod       Description of the Parameter
     * @param  url              Description of the Parameter
     * @param  requestCallback  Description of the Parameter
     */ 
    public EnhancedRequestBuilder(RequestBuilder.Method httpMethod, java.lang.String url, RequestCallback requestCallback) {
        this.url = url;
        this.httpMethod = httpMethod;
        this.requestCallback = requestCallback;
    }

    /**
     *  Description of the Method
     *
     * @param  parameter  Description of the Parameter
     * @return            Description of the Return Value
     */ 
    public String normalizeParameter(String parameter) {
        String result = parameter;
	result = result.replace("\n", "%20");
        result = result.replace(" ", "%20");
        result = result.replace("\"", "%22");
        result = result.replace("&", "%26");
        result = result.replace("?", "%3F");
        return result;
    }

    /**
     *  Description of the Method
     *
     * @return                                                  Description of the Return Value
     * @exception  com.google.gwt.http.client.RequestException  Description of the Exception
     */ 
    public Request send() throws com.google.gwt.http.client.RequestException {
        if (! parameters.equals("?")) {
            url = url + parameters;
        }
        requestBuilder = new RequestBuilder(httpMethod, url);
        requestBuilder.setCallback(requestCallback);
        return requestBuilder.send();
    }

    /**
     *  Description of the Method
     *
     * @param  parameter  Description of the Parameter
     */ 
    public void appendParameter(String parameter) {
        if (parameters.equals("?")) {
            parameters += normalizeParameter(parameter);
        } else {
            parameters += ("&" + normalizeParameter(parameter));
        }
    }

    /**
     *  Description of the Method
     *
     * @param  parameter  Description of the Parameter
     * @param  paramName  Description of the Parameter
     */ 
    public void appendParameter(String paramName, String parameter) {
        appendParameter(paramName + "=" + parameter);
    }

}

