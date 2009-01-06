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

import java.util.logging.Level;
import java.util.logging.Logger;
import com.softwoehr.pigiron.access.ParameterArray;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

/**
 * An array of arguments for PigIron VSMAPI functionality.
 *
 * @author     jax
 * @created    December 1, 2008
 */
public class ArgumentArray extends JSONArray {

    /**
     *Constructor for the empty ArgumentArray
     */ 
    public ArgumentArray() {
        super();
    }

    /**
     *Constructor for the ArgumentArray object from a JSONArray.
     *
     * @param  array              the array of Arguments to assimilate
     * @exception  JSONException  on JSON err
     */ 
    public ArgumentArray(JSONArray array) throws JSONException {
        super(array.toString());
    }

    /**
     *  Put an Argument to the array
     *
     * @param  argument  Description of the Parameter
     */ 
    public void put(Argument argument) {
        super.put(argument);
    }

    /**
     *  Get an Argument from the Array if there's one there with that name.
     * Return null if not.
     *
     * @param  argumentFormalName  Formal name of the VSMAPI parameter sought
     *                             since this name is embedded in the Argument
     * @return                     Argument from the Array if there's one there
     *                             with that name, null if not.
     */ 
    public Argument argumentNamed(String argumentFormalName) {
        Argument result = null;
        Argument temp = null;
        for (int i = 0; i < length(); i++) {
            try {
                temp = new Argument(optJSONObject(i).toString());
                // /* Debug */ System.err.println("Argument is : " + temp);
                // /* Debug */ System.err.println("Argument formalName is : " + temp.getFormalName());
                if (temp != null && temp.getFormalName().equals(argumentFormalName)) {
                    result = temp;
                    break;
                }
            } catch (JSONException ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,
                         null, ex);
            }
        }
        return result;
    }

    /**
     * Returns the contents of the object as HTML with list item markup surrounding
     * each element.
     * The caller provides enclosing markup, if any.
     * @return HTML content suitable for inclusion in an extant HTML body
     * section, with <b>no</b> leading nor trailing {@code &lt;br&nbsp;/&gt;}
     * though there can be internal {@code &lt;br&nbsp;/&gt;}'s. .
     */
    public String toHTML() throws JSONException {
        StringBuffer sb = new StringBuffer();
	for (int i = 0; i < length(); i++) {
	    sb.append("<li>");
	    sb.append((new Argument(JSONObject.class.cast(get(i)))).toHTML());
	    sb.append("</li>\n");
	}
        return sb.toString();
    }
}

