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
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A class to represent PigIron VSMAPI Function execution from start to finish.
 *
 * @author jax
 * @created November 23, 2008
 */
public class Function extends WebObject {

    /**
     * A Vector of the JSON keys (names) that are valid for a given WebObject
     * extender
     */
    private static NameList names = setNames(new String[]{"function_name", "input_arguments", "output_arguments", "return_code", "reason_code", "request_id"});

    /**
     * Get the array of JSON keys (names) that are valid for a given WebObject
     * extender
     *
     * @return The names
     */
    public String[] getNames() {
        return names.toArray(new String[names.size()]);
    }

    /**
     * Identifies whether a JSON key is one of the names a given WebObject
     * extender uses.
     *
     * @param name the JSON key
     * @return true if the key is one the class uses
     */
    public boolean isName(String name) {
        return isName(name, names);
    }

    /**
     * Create a JSON map of a PigIron VSMAPI function to execute with default
     * values, i.e., "unspecified".
     *
     * @exception JSONException on JSON error
     */
    public Function() throws JSONException {
        super();
        initDefaults();
    }

    /**
     * Create a JSON map of a PigIron VSMAPI function to execute with important
     * values specified
     *
     * @param functionName function name
     * @param inputArray an array representing PigIron VSMAPI input parameters
     * @param outputArray an array representing PigIron VSMAPI output parameters
     * @exception JSONException on JSON error
     */
    public Function(String functionName, InputArgumentArray inputArray,
            OutputArgumentArray outputArray) throws JSONException {
        this();
        putImportantValues(functionName, inputArray, outputArray);
    }

    private void putImportantValues(String functionName, InputArgumentArray inputArray,
            OutputArgumentArray outputArray) throws JSONException {
        put("function_name", functionName);
        put("input_arguments", inputArray);
        put("output_arguments", outputArray);
    }

    /**
     * Create a JSON map of a PigIron VSMAPI function from another Function
     * object
     *
     * @param function another instance whose values are copied to this
     * @exception JSONException on JSON err
     */
    public Function(Function function) throws JSONException {
        super(function);
    }

    /**
     * Create a JSON map of a PigIron VSMAPI function from a like JSONObject
     * using only the members constrained
     *
     * @param jsonObject another instance as a JSONObject
     * @exception JSONException on JSON err
     */
    public Function(JSONObject jsonObject) throws JSONException {
        super(jsonObject, new String[]{"function_name", "input_arguments", "output_arguments", "return_code", "reason_code", "request_id"});
    }

    /**
     * Create a JSON map of a PigIron VSMAPI function from a hopefully apropos
     * JSON String.
     *
     * @param jsonText text in JSON that ought to be a Function
     * @exception JSONException on JSON err
     */
    public Function(String jsonText) throws JSONException {
        super(new JSONObject(jsonText));
    }

    /**
     * Init some output fields with defaults.
     *
     * @exception JSONException on JSON err
     */
    private void initDefaults() throws JSONException {
        put("function_name", "");
        put("input_arguments", new InputArgumentArray());
        put("output_arguments", new OutputArgumentArray());
        put("return_code", - 1);
        put("reason_code", - 1);
        put("request_id", - 1);
    }

    /**
     * Accessor to attributes associated with VSMAPI function
     *
     * @return function_name
     * @exception JSONException on JSON err
     */
    public String get_function_name() throws JSONException {
        return getString("function_name");
    }

    /**
     * Accessor to attributes associated with VSMAPI function
     *
     * @return input_arguments
     * @exception JSONException on JSON err
     */
    public InputArgumentArray get_input_arguments() throws JSONException {
        InputArgumentArray result = null;
        if (has("input_arguments")) {
            result = new InputArgumentArray(getJSONArray("input_arguments"));
        }
        return result;
    }

    /**
     * Accessor to attributes associated with VSMAPI function
     *
     * @return output_arguments
     * @exception JSONException on JSON err
     */
    public OutputArgumentArray get_output_arguments() throws JSONException {
        OutputArgumentArray result = null;
        if (has("output_arguments")) {
            result = new OutputArgumentArray(getJSONArray("output_arguments"));
        }
        return result;
    }

    /**
     * Accessor to attributes associated with VSMAPI function
     *
     * @return return_code
     * @exception JSONException on JSON err
     */
    public int get_return_code() throws JSONException {
        return getInt("return_code");
    }

    /**
     * Accessor to attributes associated with VSMAPI function
     *
     * @return reason_code
     * @exception JSONException on JSON err
     */
    public int get_reason_code() throws JSONException {
        return getInt("reason_code");
    }

    /**
     * Accessor to attributes associated with VSMAPI function
     *
     * @return request_id
     * @exception JSONException on JSON err
     */
    public int get_request_id() throws JSONException {
        return getInt("request_id");
    }
}
