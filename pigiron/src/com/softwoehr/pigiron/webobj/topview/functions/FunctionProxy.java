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

import java.util.logging.Level;
import java.util.logging.Logger;

import com.softwoehr.pigiron.functions.VSMCall;
import com.softwoehr.pigiron.access.ParameterArray;
import com.softwoehr.pigiron.access.VSMException;
import com.softwoehr.pigiron.access.VSMParm;
import com.softwoehr.pigiron.access.VSMInt4;
import com.softwoehr.pigiron.bizobj.VsmapiRC;
import com.softwoehr.pigiron.webobj.topview.*;
import com.softwoehr.pigiron.webobj.topview.paramstructs.ParamProxy;

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
    public final void assimilate(Requestor requestor, Response response) throws org.json.JSONException {
        this.requestor = requestor;
        this.response = response;
        if (requestor != null) {
            host = requestor.getHost();
            function = requestor.getFunction();
            user = requestor.getUser();
            inArray = function.get_input_arguments();
            outArray = function.get_output_arguments();
        }
    }

    /**
     *  Gets the host specifier: either the dns name or the ip address. If both
     * are present, the dns name is picked.
     *
     * @return                             The hostSpecifier value (either the dns name or the dotted ip address)
     * @exception  org.json.JSONException  Description of the Exception
     */ 
    public String getHostSpecifier() throws org.json.JSONException {
        String host_specifier = null;
        if (host != null) {
            host_specifier = host.getDnsName();
            if (host_specifier == null || host_specifier.equals("")) {
                host_specifier = host.getIpAddress();
            }
        }
        return host_specifier;
    }

    /**
     *  Gets the input argument from the input argument array by formal name.
     *
     * @param  formal_name                 Description of the Parameter
     * @return                             Argument encapsulating the named input argument
     * @exception  org.json.JSONException  Description of the Exception
     */ 
    public Argument getInputArgument(String formal_name) throws org.json.JSONException {
        Argument argument = inArray != null ? inArray.argumentNamed(formal_name) : null;
        return argument;
    }

    /**
     *  Gets the string represented by an Argument in the input argument array (assuming
     * that argument represents a string).
     *
     * @param  formal_name                 Description of the Parameter
     * @return                             The string represented by the named input argument
     * @exception  org.json.JSONException  Description of the Exception
     */ 
    public String getInputArgumentString(String formal_name) throws org.json.JSONException {
        Argument argument = getInputArgument(formal_name);
        String string = argument != null ? argument.getStringValue() : "";
        return string;
    }

    /**
     *  Gets the long represented by an Argument in the input argument array (assuming
     * that argument represents a string).
     *
     * @param  formal_name                 Description of the Parameter
     * @return                             The long represented by the named input argument
     * @exception  org.json.JSONException  Description of the Exception
     */ 
    public long getInputArgumentLong(String formal_name) throws org.json.JSONException {
        Argument argument = getInputArgument(formal_name);
        long along = argument != null ? argument.getLongValue() : - 1L;        // This is the "unspecified" value of VSMInt8 and VSMInt4
        return along;        // but not of VSMInt1! The whole approach needs some work.
    }

    /**
     *  Gets the inputArgumentObject attribute of the FunctionProxy object
     *
     * @param  c                   ParamProxy extender that represents the desired VSMParm result type
     * @param  formal_name         Formal name of the parameter in the Input Array
     * @return                     The VSMParm represented by the ParamProxy instanced with values from the InputArray
     */ 
    public VSMParm getInputArgumentObject(Class <? extends ParamProxy> c,
             String formal_name) throws org.json.JSONException {
        VSMParm o = null;
        try {
            o = c.newInstance().from(getInputArgument(formal_name));
        } catch (java.lang.InstantiationException ex) {
            Logger.getLogger(FunctionProxy .class.getName()).log(Level.SEVERE,
                     null, ex);
        } catch (java.lang.IllegalAccessException ex) {
            Logger.getLogger(FunctionProxy .class.getName()).log(Level.SEVERE,
                     null, ex);
        }
        return o;
    }

    /**
     *  Gets the target identifier. Usually we have the extender fetch things from the
     * input args but this one is so common (all VSMAPI functions but one).
     *
     * @return                             The Target Identifier value (much-used param)
     * @exception  org.json.JSONException  Description of the Exception
     */ 
    public String getTargetIdentifier() throws org.json.JSONException {
        return getInputArgumentString("target_identifier");
    }

    /**
     *  Execute PigIron VSMAPI functionality as specified by a Requestor.
     *
     * @param  requestor                   Requestor for VSMAPI functionality
     * @param  response                    a partially filled-in Response
     * @param  pigfunc                     The actual PigIron function web created
     *                                in response to the Requestor specification
     * @exception  org.json.JSONException  on JSON err
     */ 
    public static void execute(VSMCall pigfunc, Requestor requestor,
             Response response) throws org.json.JSONException {
	Host host = requestor.getHost();
	boolean useSSL = host.getSSL();
        try {
            Function f = requestor.getFunction();
            ParameterArray pA = pigfunc.doIt(useSSL);
	    OutputArgumentArray outputArgumentArray = OutputArgumentArray.from(pA);
            f.put("output_arguments", outputArgumentArray);
            VSMInt4 rc_int4 = VSMInt4 .class.cast(pA.parameterNamed("return_code"));
            VSMInt4 reason_int4 = VSMInt4 .class.cast(pA.parameterNamed("reason_code"));
	    VSMInt4 request_id_int4 = VSMInt4 .class.cast(pA.parameterNamed("request_id"));
	    f.put("return_code", rc_int4.getValue());
	    f.put("reason_code", reason_int4.getValue());
	    f.put("request_id", request_id_int4.getValue());
	    requestor.setFunction(f);
            response.setRequestor(requestor);
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

