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

import java.util.Vector;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * A class to represent PigIron VSMAPI Function execution
 * from start to finish.
 *
 * @author     jax
 * @created    November 23, 2008
 */
public class WebObject extends JSONObject {

    /**
     *  A Vector of the JSON keys (names) that are valid for
     * a given WebObject extender
     */ 
    protected static Vector <String> names;

    /**
     * Construct an (empty) WebObject
     */ 
    public WebObject() {
        super();
    }

    /**
     *Construct a  WebObject passing a JSON string to the superclass
     *
     * @param  jsonText                    Description of the Parameter
     * @exception  org.json.JSONException  Description of the Exception
     */ 
    protected WebObject(String jsonText) throws org.json.JSONException {
        super(jsonText);
    }

   /**
     *Construct a  WebObject from a WebObject using JSON's constrained copy ctor
     *
     * @param  webObject                   The object to copy key/vals from
     * @exception  org.json.JSONException  on JSON error
     */ 
    protected WebObject(WebObject webObject) throws org.json.JSONException {
        super(webObject, getNames());
    }

   /**
     *Construct a  WebObject from a JSONObject using JSON's constrained copy ctor
     *
     * @param  webObject                   The object to copy key/vals from
     * @exception  org.json.JSONException  on JSON error
     */ 
    protected WebObject(JSONObject jsonObject) throws org.json.JSONException {
        super(jsonObject, getNames());
    }
    
    /**
     *  Sets Vector of the JSON keys (names) that are valid for
     * a given WebObject extender.
     *
     * @param  someNames The array of JSON keys (names) that are valid for
     * a given WebObject extender
     */ 
    public static void setNames(String [] someNames) {
        names = new Vector <String>(someNames.length);
        for (int i = 0; i < someNames.length; i++) {
            names.add(someNames[i]);
        }
    }

    /**
     *  Get the array of JSON keys (names) that are valid for
     * a given WebObject extender
     *
     * @return    The names
     */ 
    public static String []getNames() {
        return names.toArray(new String [names.size()]);
    }

    /**
     * Identifies whether a JSON key is one of the names a given
     * WebObject extender uses.
     *
     * @param  name  the JSON key
     * @return       true if the key is one the class uses
     */ 
    public static boolean isName(String name) {
        return names.indexOf(name) != - 1;
    }
}

