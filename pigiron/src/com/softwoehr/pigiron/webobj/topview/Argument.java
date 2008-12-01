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

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Represents an argument to a VSMAPI function
 *
 * @author     jax
 */
public class Argument extends JSONObject {

    /**
     *  Names we use for members
     */ 
    public static final String []names = {"formalName" ,"value"} ;

    /**
     * Create with defaults (empty).
     *
     * @exception  JSONException        on JSON err
     * @throws  org.json.JSONException  on JSON err
     */ 
    public Argument() throws JSONException {
        super();
        initDefaults();
    }

    /**
     *Constructor for the Argument from a string of JSON representation
     *
     * @param  jsonRepresentation  argument described in JSON
     * @exception  JSONException   on JSON err
     * @throws  JSONException      on JSON err
     */ 
    public Argument(String jsonRepresentation) throws JSONException {
        super(jsonRepresentation);
    }

    /**
     * Create with the formal name of the argument and the value
     *
     * @param  formalName
     * @param  value
     * @exception  JSONException        on JSON err
     * @throws  org.json.JSONException  on JSON err
     */ 
    public Argument(String formalName, String value) throws JSONException {
        this();
        setFormalName(formalName);
        setValue(value);

    }

    /**
     *Constructor for the Argument from a JSONObject using only
     * the members named in Argument.names
     *
     * @param  anArgument         a like Argument
     * @exception  JSONException  on JSON err
     */ 
    public Argument(JSONObject anArgument) throws JSONException {
        super(anArgument,names);
    }

    /**
     *   init defaults
     *
     * @exception  JSONException  on JSON err
     */ 
    private void initDefaults() throws JSONException {
        setFormalName("");
        setValue(null);
    }

    /**
     * Get the value
     *
     * @return                 the value
     * @throws  JSONException  on JSON err
     */ 
    public String getValue() throws JSONException {
        return getString("value");
    }

    /**
     * Set the value
     *
     * @param  value           the value
     * @throws  JSONException  on JSON err
     */ 
    public void setValue(String value) throws JSONException {
        if (value == null) {
            put("value", JSONObject.NULL);
        } else {
            put("value", value);
        }
    }

    /**
     * Get the formal name
     *
     * @return                 the formal name
     * @throws  JSONException  on JSON err
     */ 
    public String getFormalName() throws JSONException {
        return getString("formalName");
    }

    /**
     * Set the formal name
     *
     * @param  formalName      the formal name
     * @throws  JSONException  on JSON err
     */ 
    public void setFormalName(String formalName) throws JSONException {
        put("formalName", formalName);
    }

    /**
     *  The main program for the Argument class
     *
     * @param  args               two strings to pretend to be formal name and value.
     * @exception  JSONException  Description of the Exception
     */ 
    public static void main(String [] args) throws JSONException {
        Argument a = new Argument();
        Argument b = new Argument();
        if (args.length > 1) {
            b = new Argument(args[0], args[1]);
        }
        Argument c = new Argument("{\"name\":\"iyamanarg\",\"value\":\"Popeye\"}");
        System.out.println("A == " + a);
        System.out.println("B == " + b);
        System.out.println("C == " + c);
    }
}
/*
 *  End
 */
