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

/* Autogenerated Sat Dec 19 03:56:40 UTC 2015
 * by pigproxfunc.m4 Copyright *C* 2015 Jack J. Woehr
 * Part of the PigIron Project http://pigiron.sourceforge.net
 */
package com.softwoehr.pigiron.webobj.topview.functions;

import com.softwoehr.pigiron.webobj.topview.*;

/**
 * Proxy function class to bridge JSON to PigIron
 *
 */
public class ImageDiskCreateDM extends FunctionProxy {

    /**
     *  Create an instance of the call with empty defaults
     */
    // public ImageDiskCreateDM() { super(); }

    /**
     * Create an instance of the function proxy with requestor and response instanced.
     * It will consume the requestor in execution and return the response suitably modified.
     * @param requestor the requestor spawning the instance execution
     * @param response the response to be modified and returned in the execution
     */
    public ImageDiskCreateDM(Requestor requestor, Response response) throws org.json.JSONException {
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
     *   --  image_disk_number instances {@code String}
     *   --  image_disk_device_type instances {@code String}
     *   --  image_disk_allocation_type instances {@code String}
     *   --  allocation_area_name_or_volser instances {@code String}
     *   --  allocation_unit_size instances {@code int}
     *   --  image_disk_size instances {@code int}
     *   --  image_disk_mode instances {@code String}
     *   --  image_disk_formatting instances {@code int}
     *   --  image_disk_label instances {@code String}
     *   --  read_password instances {@code String}
     *   --  write_password instances {@code String}
     *   --  multi_password instances {@code String}
     */ 
    public Response execute() throws org.json.JSONException {
        com.softwoehr.pigiron.functions.ImageDiskCreateDM pigfunc = new com.softwoehr.pigiron.functions.ImageDiskCreateDM
	(
           getHostSpecifier()
         , host.getPortNumber()
         , user.getUid()
         , user.getPassword()
         , getTargetIdentifier()
         , getInputArgumentString("image_disk_number")
         , getInputArgumentString("image_disk_device_type")
         , getInputArgumentString("image_disk_allocation_type")
         , getInputArgumentString("allocation_area_name_or_volser")
         , (int)getInputArgumentLong("allocation_unit_size")
         , (int)getInputArgumentLong("image_disk_size")
         , getInputArgumentString("image_disk_mode")
         , (int)getInputArgumentLong("image_disk_formatting")
         , getInputArgumentString("image_disk_label")
         , getInputArgumentString("read_password")
         , getInputArgumentString("write_password")
         , getInputArgumentString("multi_password")
	);
        execute(pigfunc,requestor,response);
        return response;
    }
       
}
