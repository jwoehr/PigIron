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

import com.google.gwt.json.client.*;

/**
 *  Parses the JSON responses from PigIron/PigLet
 *
 * @author     jax
 * @created    February 16, 2009
 */
public class ResponseParser {

    private final JSONValue jsonValue;

    /**
     *  Description of the Method
     *
     * @param  jsonResponse  Description of the Parameter
     * @return               Description of the Return Value
     */ 
    public static ResponseParser parse(String jsonResponse) {
        return new ResponseParser(jsonResponse);
    }

    /**
     *Constructor for the ResponseParser object
     *
     * @param  jsonResponse  Description of the Parameter
     */ 
    ResponseParser(String jsonResponse) {
        jsonValue = JSONParser.parse(jsonResponse);
    }

    /**
     *  Gets the responseObject attribute of the ResponseParser object
     *
     * @return    The responseObject value
     */ 
    public JSONObject getResponseObject() {
        JSONObject responseObject = null;
        if (jsonValue != null) {
            responseObject = jsonValue.isObject();
        }
        return responseObject;
    }

    /**
     *  Gets the messageTextString attribute of the ResponseParser object
     *
     * @return    The messageTextString value
     */ 
    public JSONString getMessageTextString() {
        JSONString messageTextString = null;
        JSONObject responseObject = getResponseObject();
        if (responseObject != null) {
            JSONValue messageTextValue = responseObject.get("messageText");
            if (messageTextValue != null) {
                messageTextString = messageTextValue.isString();
            }
        }
        return messageTextString;
    }

    /**
     *  Gets the messageText attribute of the ResponseParser object
     *
     * @return    The messageText value
     */ 
    public String getMessageText() {
        String messageText = null;
        JSONString messageTextString = getMessageTextString();
        if (messageTextString != null) {
            messageText = messageTextString.stringValue();
        }
        return messageText;
    }

    /**
     *  Gets the resultString attribute of the ResponseParser object
     *
     * @return    The resultString value
     */ 
    public JSONString getResultString() {
        JSONString resultString = null;
        JSONObject responseObject = getResponseObject();
        if (responseObject != null) {
            JSONValue resultValue = responseObject.get("result");
            if (resultValue != null) {
                resultString = resultValue.isString();
            }
        }
        return resultString;
    }

    /**
     *  Gets the requestorObject attribute of the ResponseParser object
     *
     * @return    The requestorObject value
     */ 
    public JSONObject getRequestorObject() {
        JSONObject requestorObject = null;
        JSONObject responseObject = getResponseObject();
        if (responseObject != null) {
            JSONValue requestorValue = responseObject.get("requestor");
            if (requestorValue != null) {
                requestorObject = requestorValue.isObject();
            }
        }
        return requestorObject;
    }

    /**
     *  Gets the functionObject attribute of the ResponseParser object
     *
     * @return    The functionObject value
     */ 
    public JSONObject getFunctionObject() {
        JSONObject functionObject = null;
        JSONObject requestorObject = getRequestorObject();
        if (requestorObject != null) {
            JSONValue functionValue = requestorObject.get("function");
            if (functionValue != null) {
                functionObject = functionValue.isObject();
            }
        }
        return functionObject;
    }

    /**
     *  Gets the returnCodeNumber attribute of the ResponseParser object
     *
     * @return    The returnCodeNumber value
     */ 
    public JSONNumber getReturnCodeNumber() {
        JSONNumber returnCodeNumber = null;
        JSONObject functionObject = getFunctionObject();
        if (functionObject != null) {
            JSONValue returnCodeValue = functionObject.get("return_code");
            if (returnCodeValue != null) {
                returnCodeNumber = returnCodeValue.isNumber();
            }
        }
        return returnCodeNumber;
    }

    /**
     *  Gets the resultCodeNumber attribute of the ResponseParser object
     *
     * @return    The resultCodeNumber value
     */ 
    public JSONNumber getResultCodeNumber() {
        JSONNumber resultCodeNumber = null;
        JSONObject functionObject = getFunctionObject();
        if (functionObject != null) {
            JSONValue resultCodeValue = functionObject.get("return_code");
            if (resultCodeValue != null) {
                resultCodeNumber = resultCodeValue.isNumber();
            }
        }
        return resultCodeNumber;
    }

    /**
     *  Gets the inputArgumentsArray attribute of the ResponseParser object
     *
     * @return    The inputArgumentsArray value
     */ 
    public JSONArray getInputArgumentsArray() {
        JSONArray inputArgumentsArray = null;
        JSONObject functionObject = getFunctionObject();
        if (functionObject != null) {
            JSONValue inputArgumentsValue = functionObject.get("input_arguments");
            if (inputArgumentsValue != null) {
                inputArgumentsArray = inputArgumentsArray.isArray();
            }
        }
        return inputArgumentsArray;
    }

    /**
     *  Gets the outputArgumentsArray attribute of the ResponseParser object
     *
     * @return    The outputArgumentsArray value
     */ 
    public JSONArray getOutputArgumentsArray() {
        JSONArray outputArgumentsArray = null;
        JSONObject functionObject = getFunctionObject();
        if (functionObject != null) {
            JSONValue outputArgumentsValue = functionObject.get("output_arguments");
            if (outputArgumentsValue != null) {
                outputArgumentsArray = outputArgumentsArray.isArray();
            }
        }
        return outputArgumentsArray;
    }

    /**
     *  Gets the outputArgumentValueNamed attribute of the ResponseParser object
     *
     * @param  formalName  Description of the Parameter
     * @return             The outputArgumentValueNamed value
     */ 
    public JSONValue getOutputArgumentValueNamed(String formalName) {
        JSONValue outputArgumentValue = null;
        JSONArray outputArgumentsArray = getOutputArgumentsArray();
        if (outputArgumentsArray != null) {
            JSONValue argumentValue = null;
            for (int i = 0; i < outputArgumentsArray.size(); i++) {
                argumentValue = outputArgumentsArray.get(i);
                if (argumentValue != null) {
                    JSONObject argumentObject = argumentValue.isObject();
                    if (argumentObject != null) {
                        JSONValue formalNameValue = argumentObject.get("formal_name");
                        if (formalNameValue != null) {
                            JSONString formalNameString = formalNameValue.isString();
                            if (formalNameString != null) {
                                String aFormalName = formalNameString.stringValue();
                                if (aFormalName.equals(formalName)) {
                                    outputArgumentValue = argumentObject.get("value");
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        return outputArgumentValue;
    }

    /**
     *  Gets the returnCode attribute of the ResponseParser object
     *
     * @return    The returnCode
     */ 
    public double getReturnCode() {
        double returnCode = - 1;
        JSONNumber returnCodeNumber = getReturnCodeNumber();
        if (returnCodeNumber != null) {
            returnCode = returnCodeNumber.doubleValue();
        }
        return returnCode;
    }

    /**
     *  Gets the resultCodeNumber attribute of the ResponseParser object
     *
     * @return    The resultCode
     */ 
    public double getResultCode() {
        double resultCode = - 1;
        JSONNumber resultCodeNumber = getResultCodeNumber();
        if (resultCodeNumber != null) {
            resultCode = resultCodeNumber.doubleValue();
        }
        return resultCode;
    }
    
    public String getResult() {
	String result = null;
	JSONString resultString = getResultString();
	if (resultString != null) {
	    result = resultString.stringValue();
	}
	return result;
    }
}

