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
package com.softwoehr.pigiron.webobj.topview.paramstructs;

import com.softwoehr.pigiron.access.VSMParm;
import com.softwoehr.pigiron.webobj.topview.*;
import org.json.JSONArray;
import org.json.JSONException;

/**
 *  Description of the Class
 *
 * @author     jax
 * @created    December 6, 2008
 */
public class ImageRecordArray extends ParamProxy {
    /**
     *  Description of the Method
     *
     * @param  arg                Argument whose value represent the ImageRecordArray
     * @return                    Instanced 
     * @exception  JSONException  on JSON err
     */ 
    public VSMParm from(Argument arg) throws JSONException {
	String formal_name = arg.getFormalName();
        com.softwoehr.pigiron.access.paramstructs.ImageRecordArray parm
	= new com.softwoehr.pigiron.access.paramstructs.ImageRecordArray(formal_name);
        JSONArray jo = arg.getJSONArrayValue();
	for (int i = 0; i < jo.length(); i++) {
	   Argument a = new Argument(jo.opt(i).toString());
	   parm.add((new ImageRecordStructure()).from(a));
	}
        return parm;
    }
}

