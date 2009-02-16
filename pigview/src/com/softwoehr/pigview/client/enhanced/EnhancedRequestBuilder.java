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

import com.google.gwt.http.client.URL;
import com.google.gwt.http.client.*;

/**
 *  An extension of the request builder in GWT which is insufficient.
 *
 * @author     jax
 * @created    January 19, 2009
 */
public class EnhancedRequestBuilder {
 
    public static enum Methods { POST, PUT } ;

    private RequestCallback requestCallback = null;
    private RequestBuilder requestBuilder = null;
    private String httpMethodString = null;
    private RequestBuilder.Method httpMethod = null;
    private String url = "";
    private String parameters = "?";
    private String requestData = "";

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
     *Constructor for the EnhancedRequestBuilder object
     *
     * @param  url               Description of the Parameter
     * @param  requestCallback   Description of the Parameter
     * @param  httpMethodString  Description of the Parameter
     */ 
    public EnhancedRequestBuilder(String httpMethodString, java.lang.String url, RequestCallback requestCallback) {
        this.url = url;
        this.httpMethodString = httpMethodString;
        this.requestCallback = requestCallback;
    }

    /**
     *  Description of the Method
     *
     * @return                                                  Description of the Return Value
     * @exception  com.google.gwt.http.client.RequestException  Description of the Exception
     */ 
    public Request send() throws com.google.gwt.http.client.RequestException {
        Request result = null;
        if (httpMethodString != null) {
            result = sendMethodString();
        } else {
            if (httpMethod != null) {
                result = sendMethod();
            } else {
                // Some error or exception
            }
        }
        return result;
    }

    /**
     *  Description of the Method
     *
     * @return                                                  Description of the Return Value
     * @exception  com.google.gwt.http.client.RequestException  Description of the Exception
     */ 
    public Request sendMethod() throws com.google.gwt.http.client.RequestException {
        if (! parameters.equals("?")) {
            url = url + parameters;
        }
        requestBuilder = new EnhancedRequestBuilderSub(httpMethod, URL.encode(url));
        requestBuilder.setCallback(requestCallback);
        return requestBuilder.send();
    }

    /**
     *  Description of the Method
     *
     * @return                                                  Description of the Return Value
     * @exception  com.google.gwt.http.client.RequestException  Description of the Exception
     */ 
    public Request sendMethodString() throws com.google.gwt.http.client.RequestException {
        requestBuilder = new EnhancedRequestBuilderSub(httpMethodString, URL.encode(url));
        requestBuilder.setCallback(requestCallback);
        requestBuilder.setRequestData(requestData);
        return requestBuilder.send();
    }

    /**
     *  Description of the Method
     *
     * @param  parameter  Description of the Parameter
     */ 
    public void appendParameter(String parameter) {
        if (parameters.equals("?")) {
            parameters += parameter;
        } else {
            parameters += "&" + parameter;
        }
    }

    /**
     *  Sets the requestData attribute of the EnhancedRequestBuilder object
     *
     * @param  requestData  The new requestData value
     */ 
    public void setRequestData(String requestData) {
        this.requestData = requestData;
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

    /**
     *  Description of the Class
     *
     * @author     jax
     * @created    January 30, 2009
     */ 
    private class EnhancedRequestBuilderSub extends RequestBuilder {

        /**
         *Constructor for the EnhancedRequestBuilderSub object
         *
         * @param  httpMethod       Description of the Parameter
         * @param  url              Description of the Parameter
         */ 
        public EnhancedRequestBuilderSub(RequestBuilder.Method httpMethod,
                 java.lang.String url) {
            super(httpMethod,url);
        }

        /**
         *Constructor for the EnhancedRequestBuilder object
         *
         * @param  url               Description of the Parameter
         * @param  httpMethodString  Description of the Parameter
         */ 
        protected EnhancedRequestBuilderSub(String httpMethodString,
                 java.lang.String url) {
            super(httpMethodString,url);
        }
    }
 
    /**
     *  Description of the Method
     *
     * @param  url        Description of the Parameter
     * @param  requestor  Description of the Parameter
     */ 
    public static EnhancedRequestBuilder buildRequest(String url, Methods method, String requestor, RequestCallback requestCallback) {

        EnhancedRequestBuilder requestBuilder = null;
        if (method == Methods.POST) {
            requestBuilder = new EnhancedRequestBuilder(RequestBuilder.POST, url, requestCallback);
            requestBuilder.appendParameter("requestor", requestor);
        } else {
            if (method == Methods.PUT) {
                requestBuilder = new EnhancedRequestBuilder("PUT", url, requestCallback);
                requestBuilder.setRequestData(requestor);
            }
	    else {
		// some weird error here
	    }
        }
        return requestBuilder;
    }
}

