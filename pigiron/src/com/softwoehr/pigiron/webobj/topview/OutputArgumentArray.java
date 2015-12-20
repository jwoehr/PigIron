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
package com.softwoehr.pigiron.webobj.topview;

import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.softwoehr.pigiron.access.ParameterArray;
import com.softwoehr.pigiron.access.VSMParm;

import org.json.JSONException;
import org.json.JSONArray;

/**
 * Class mapping PigIron VSMAPI output param arrays to JSONArray.
 * @author     jax
 */
public class OutputArgumentArray extends ArgumentArray {

    /**
     * Empty default instance
     */ 
    public OutputArgumentArray() {
        super();
    }

    /**
     *Constructor for the OutputArgumentArray object from a JSONArray.
     *
     * @param  array              the array of output params to assimilate
     * @exception  JSONException  on JSON err
     */ 
    public OutputArgumentArray(JSONArray array) throws JSONException {
        super(array);
    }
 
    /** Factory method to create an OutputArgumentArray from a ParameterArray,
     * i.e., one returned from a PigIron VSMAPI call.
     *
     * @param pA the parameter array returned from a PigIron VSMAPI call
     * @return the new JSON-formatted OutputArgumentArray
     * @exception  JSONException  on JSON err
     */ 
    public static OutputArgumentArray from(ParameterArray pA) throws JSONException {
        OutputArgumentArray out = new OutputArgumentArray();
        Iterator <VSMParm> it = pA.iterator();
        int i = 0;
        while (it.hasNext()) {
            VSMParm p = it.next();
            if (p != null) {
                Argument a = Argument.from(p);
                if (a != null) {
                    out.put(i++,a);
                } else { Logger.getLogger(OutputArgumentArray .class.getName()).log(Level.WARNING,
         "null Argument in from()", p);
                }
            } else { Logger.getLogger(OutputArgumentArray .class.getName()).log(Level.WARNING,
         "null VSMParm in from()", p);
            }
        }
        return out;
    }
}

