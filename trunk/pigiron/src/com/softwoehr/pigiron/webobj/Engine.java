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
package com.softwoehr.pigiron.webobj;

import com.softwoehr.pigiron.webobj.topview.*;
import com.softwoehr.pigiron.webobj.topview.functions.FunctionProxy;
import com.softwoehr.pigiron.webobj.topview.functions.FunctionTable;
import org.json.JSONException;

/**
 *  An executor of JSON descriptions of PigIron VSMAPI Functions
 * which consumes a Requestor and returns a Response.
 *
 * @author     jax
 * @see com.softwoehr.pigiron.webobj.topview.Requestor
 * @see com.softwoehr.pigiron.webobj.topview.Response
 */
public class Engine {

    /**
     *Constructor for the Engine object
     */ 
    public Engine() { }

    /**
     *  Execute from a Requestor, a JSON description of a proposed PigIron
     * VSMAPI Function instance, and return a Response which contains (among
     * other things) the original object with the response fields filled out.
     *
     * @param  requestor                   Description of the Parameter
     * @return                             a Response which contains (among other things) the
     * original object with the response fields filled out
     * @exception  org.json.JSONException  on JSON error creating the empty Response
     */ 
    public Response execute(Requestor requestor) throws org.json.JSONException {

        Response response = new Response();
        Function function = null;
        String functionName = null;
        String jsonErr = "";
        try {
            response = new Response(requestor);
            function = requestor.getFunction();
            functionName = function.get_function_name();
            System.err.println("Function name in execute is: " + functionName);
            try {
                if (functionName != null) {
                    Class <? extends FunctionProxy> functionProxy = FunctionTable.get(functionName);
                    System.err.println("Class in execute is: " + functionProxy);
                    response = functionProxy.newInstance().execute(requestor, response);

                } else {
                    response.setResult(Response.Results.JSON_ERR.name());
                    response.setMessageText("No function name found in JSON stream");
                }
            } catch (java.lang.InstantiationException ex) {
                response.setResult(Response.Results.PIGIRON_ERR.name());
                response.setMessageText("InstantiationException instancing FunctionProxy instance" + ex.getMessage());
            }
	    catch (java.lang.IllegalAccessException ex) {
                response.setResult(Response.Results.PIGIRON_ERR.name());
                response.setMessageText("InstantiationException instancing FunctionProxy instance: " + ex.getMessage());
            }
        } catch (JSONException ex) {
            jsonErr = ex.getMessage();
            response.setResult(Response.Results.JSON_ERR.name());
            response.setMessageText(jsonErr);
        }

        return response;
    }

    /**
     *  A simple main program for testing the Engine class
     *
     * @param  args                        arg0 JSON string of a Requestor
     * @exception  org.json.JSONException  on JSON error
     */ 
    public static void main(String [] args) throws org.json.JSONException {
        Requestor requestor = new Requestor(args[0]);
        Engine engine = new Engine();
        Response response = engine.execute(requestor);
        System.out.println(response);
    }
}

