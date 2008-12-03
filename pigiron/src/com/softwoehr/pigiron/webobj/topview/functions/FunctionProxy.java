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
package com.softwoehr.pigiron.webobj.topview.functions;

import com.softwoehr.pigiron.functions.VSMCall;
import com.softwoehr.pigiron.access.ParameterArray;
import com.softwoehr.pigiron.access.VSMException;
import com.softwoehr.pigiron.access.VSMInt4;
import com.softwoehr.pigiron.bizobj.VsmapiRC;
import com.softwoehr.pigiron.webobj.topview.*;

/**
 *  The abstract parent of all our function proxies that take
 * JSON input and call PigIron VSMAPI functionality.
 *
 * @author     jax
 * @created    December 1, 2008
 */
public abstract class FunctionProxy {

    /**
     *  Target host assimilated from the requestor
     */ 
    public Host host = null;
    /**
     *  User assimilated from the requestor
     */ 
    public User user = null;
    /**
     *  Response to be filled in and returned
     */ 
    public Response response = null;
    /**
     *  Requestor which is also copied into response
     */ 
    public Requestor requestor = null;
    /**
     *  Function assimilated from the requestor
     */ 
    public Function function = null;

    /**
     *  InputArgumentArray assimilated from the Function
     */ 
    public InputArgumentArray inArray = null;
    /**
     *  OutputArgumentArray assimilated from the Function
     */ 
    public OutputArgumentArray outArray = null;

    /**
     *Constructor for the FunctionProxy object
     *
     * @param  requestor                   the request to be executed
     * @param  response                    the response to be returned
     * @exception  org.json.JSONException  Description of the Exception
     */ 
    public FunctionProxy(Requestor requestor, Response response) throws org.json.JSONException {

        assimilate(requestor,response);
	 /* Debug */ System.err.println("Leaving FunctionProxy(Requestor" + requestor + ", Response " + response + ")");
    }

    /**
     * Execute the request passed in to the ctor
     *
     * @return                             the Response composed partly by the
     * Engine and partly by this FunctionProxy
     * @exception  org.json.JSONException  on JSON err
     */ 
    public abstract Response execute() throws org.json.JSONException;

    /**
     *  Description of the Method
     *
     * @param  requestor                   the request to be executed
     * @param  response                    the response to be returned
     * @exception  org.json.JSONException  on JSON err
     */ 
    public void assimilate(Requestor requestor, Response response) throws org.json.JSONException {
        // /* Debug */ System.err.println("starting assimilate");
        this.requestor = requestor;
        this.response = response;
        host = requestor.getHost();
	// /* Debug */ System.err.println("got host in assimilate: " + host );
        function = requestor.getFunction();
        user = requestor.getUser();
        inArray = function.get_input_arguments();
        outArray = function.get_output_arguments();
        // /* Debug */ System.err.println("Ending assimilate"); 
    }

    /**
     *  Description of the Method
     *
     * @param  requestor                   Description of the Parameter
     * @param  response                    Description of the Parameter
     * @param  pigfunc                     Description of the Parameter
     * @exception  org.json.JSONException  on JSON err
     */ 
    public static void execute(VSMCall pigfunc, Requestor requestor,
             Response response) throws org.json.JSONException {

        try {
            /* Debug */ System.err.println("Requestor in FunctionProxy.execute() : " + requestor);
            /* Debug */ System.err.println("Response in FunctionProxy.execute() : " + response);
            Function f = requestor.getFunction();
 
            /* Debug */ System.err.println("Function in FunctionProxy.execute() : " + f);
            ParameterArray pA = pigfunc.doIt();
            // /* Debug */ System.err.println(pA.prettyPrintAll());
            f.put("output_arguments", OutputArgumentArray.from(pA));
            requestor.setFunction(f);
            response.setRequestor(requestor);
            VSMInt4 rc_int4 = VSMInt4 .class.cast(pA.parameterNamed("return_code"));
            VSMInt4 reason_int4 = VSMInt4 .class.cast(pA.parameterNamed("reason_code"));
            response.setMessageText(VsmapiRC.prettyPrint(rc_int4.getValue(), reason_int4.getValue(), pigfunc).replace("\n", " ; "));
            long rc = rc_int4.getLongValue();
            if (rc == 0) {
                response.setResult(Response.Results.SUCCESS.name());
            } else {
                response.setResult(Response.Results.FAILURE.name());
            }
        } catch (java.io.IOException ex) {
            response.setResult(Response.Results.FAILURE.name());
            response.setMessageText(ex.toString());
        } catch (VSMException ex) {
            response.setResult(Response.Results.PIGIRON_ERR.name());
            response.setMessageText(ex.toString());
        }

    }
}

