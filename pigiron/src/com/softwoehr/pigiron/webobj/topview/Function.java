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

import com.softwoehr.pigiron.webobj.WebObject;

import java.util.Iterator;

import org.json.JSONException;


/**
 * A class to represent PigIron VSMAPI Function execution
 * from start to finish.
 *
 * @author     jax
 * @created    November 23, 2008
 */
public class Function extends WebObject {

 
    static { setNames(new String [] {"function_name" ,"input_arguments" ,
        "output_arguments" ,"return_code" ,"result_code" ,"request_id" }

       ); }

    /**
     * Create a JSON map of a PigIron VSMAPI function to execute
     * with default values, i.e., "unspecified".
     * @exception  JSONException        on JSON error
     * @throws  org.json.JSONException
     */ 
    public Function() throws JSONException {
        super();
        initDefaults();
    }

    /**
     * Create a JSON map of a PigIron VSMAPI function to execute
     * with important values specified
     *
     * @param  functionName function name
     * @param  inputArray   an array representing PigIron VSMAPI input parameters
     * @param  outputArray  an array representing PigIron VSMAPI output parameters
     * @exception  JSONException   on JSON error
     */ 
    public Function(FunctionName functionName, InputArgumentArray inputArray,
             OutputArgumentArray outputArray) throws JSONException {

        this();
        put("function_name", functionName);
        put("input_arguments", inputArray);
        put("output_arguments", outputArray);
    }

    /**
     * Init some output fields with defaults.
     *
     */ 
    private void initDefaults() throws JSONException {
        put("function_name", new FunctionName(""));
        put("input_arguments", new InputArgumentArray());
        put("output_arguments", new OutputArgumentArray());
        put("return_code", - 1);
        put("result_code", - 1);
        put("request_id", - 1);
    }

    /**
     * Create a JSON map of a PigIron VSMAPI function
     * from another Function object
     *
     * @param  function  another instance whose values are copied to this
     */ 
    public Function(Function function) throws JSONException {
        super();
        copyFrom(function);
    }
 
    /**
     * Create a JSON map of a PigIron VSMAPI function
     * from a JSON String
     *
     * @param  jsonText  text in JSON that ought to be a Function
     */ 
    public Function(String jsonText) throws JSONException {
        super(jsonText);
    }
 
    /**
     *  Copy the keys we care about from instance A to instance B.
     *
     * @param  function  another instance whose values are copied to this
     */ 
    public void copyFrom(Function function) throws JSONException {
        Iterator keyIterator = function.keys();
        while (keyIterator.hasNext()) {
            Object key = keyIterator.next();
            put(key.toString(), function.get(key.toString()));
        }
    }
}
