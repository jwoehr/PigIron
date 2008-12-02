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
     *  Description of the Field
     */ 
    public Host host = null;
    /**
     *  Description of the Field
     */ 
    public User user = null;
    /**
     *  Description of the Field
     */ 
    public Response response = null;
    /**
     *  Description of the Field
     */ 
    public Requestor requestor = null;
    /**
     *  Description of the Field
     */ 
    public Function function = null;

    /**
     *Constructor for the FunctionProxy object
     *
     * @param  requestor  Description of the Parameter
     * @param  response   Description of the Parameter
     */ 
    public FunctionProxy(Requestor requestor, Response response) throws org.json.JSONException {
        assimilate(requestor,response);
    }

    /**
     *  Description of the Method
     *
     * @return                             Description of the Return Value
     * @exception  org.json.JSONException  Description of the Exception
     */ 
    public abstract Response execute() throws org.json.JSONException;

    /**
     *  Description of the Method
     *
     * @param  requestor                   Description of the Parameter
     * @param  response                    Description of the Parameter
     * @exception  org.json.JSONException  Description of the Exception
     */ 
    public void assimilate(Requestor requestor, Response response) throws org.json.JSONException {
        this.requestor = requestor;
        this.response = response;
        this.host = requestor.getHost();
        this.function = requestor.getFunction();
        this.user = requestor.getUser();
    }

}

