/*
 * Copyright (c) 2008, 2015, Jack J. Woehr jwoehr@softwoehr.com
 * PO Box 51, Golden, Colorado 80402-0051 USA
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *     * Redistributions of source code must retain the above copyright
 *         notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *         notice, this list of conditions and the following disclaimer
 *         in the documentation and/or other materials provided with the
 *         distribution.
 *     * Neither the name of the PigIron Project nor the names of its
 *         contributors may be used to endorse or promote products derived
 *         from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */

/* Autogenerated Mon Jan  4 02:27:41 UTC 2016
 * by pigproxfunc.m4 Copyright *C* 2015 Jack J. Woehr
 * Part of the PigIron Project http://pigiron.sourceforge.net
 */
package com.softwoehr.pigiron.webobj.topview.functions;

import com.softwoehr.pigiron.webobj.topview.*;

/**
 * Proxy function class to bridge JSON to PigIron
 *
 */
public class AsynchronousNotificationEnableDM extends FunctionProxy {

    /**
     *  Create an instance of the call with empty defaults
     */
    // public AsynchronousNotificationEnableDM() { super(); }

    /**
     * Create an instance of the function proxy with requestor and response instanced.
     * It will consume the requestor in execution and return the response suitably modified.
     * @param requestor the requestor spawning the instance execution
     * @param response the response to be modified and returned in the execution
     */
    public AsynchronousNotificationEnableDM(Requestor requestor, Response response) throws org.json.JSONException {
        super(requestor,response);
    }

    /**
     * Execute the PigIron VSMAPI call we have set up in this instance.
     *
     * @return                             the response from the call
     * @exception  org.json.JSONException  on JSON err
     *
     * The PigIron/VSMAPI parameters fed to the instancing within execute() are as follows:
     *   --  hostname  VSMAPI Host DNS name
     *   --  port port VSMAPI Host is listening on
     *   --  userid userid executing the function
     *   --  password the password
     *   --  target_identifier the target of the VSMAPI function
     *   --  entity_type instances {@code int}
     *   --  subscription_type instances {@code int}
     *   --  communication_type instances {@code int}
     *   --  port_number instances {@code int}
     *   --  ip_address instances {@code String}
     *   --  encoding instances {@code int}
     *   --  subscriber_data instances {@code String}
     */ 
    public Response execute() throws org.json.JSONException {
        com.softwoehr.pigiron.functions.AsynchronousNotificationEnableDM pigfunc = new com.softwoehr.pigiron.functions.AsynchronousNotificationEnableDM
	(
           getHostSpecifier()
         , host.getPortNumber()
         , user.getUid()
         , user.getPassword()
         , getTargetIdentifier()
         , (int)getInputArgumentLong("entity_type")
         , (int)getInputArgumentLong("subscription_type")
         , (int)getInputArgumentLong("communication_type")
         , (int)getInputArgumentLong("port_number")
         , getInputArgumentString("ip_address")
         , (int)getInputArgumentLong("encoding")
         , getInputArgumentString("subscriber_data")
	);
        execute(pigfunc,requestor,response);
        return response;
    }
       
}
