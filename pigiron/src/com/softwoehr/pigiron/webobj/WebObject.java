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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A class to represent those elements of PigIron VSMAPI Function execution
 * which can be represented as JSONObject.
 *
 * @author jax
 * @created November 23, 2008
 */
public abstract class WebObject extends JSONObject {

    /**
     * Construct an (empty) WebObject
     */
    public WebObject() {
        super();
    }

    /**
     * Construct a WebObject passing a JSON string to the superclass
     *
     * @param jsonText Description of the Parameter
     * @exception org.json.JSONException Description of the Exception
     */
    protected WebObject(String jsonText) throws org.json.JSONException {
        super(jsonText);
    }

    /**
     * Construct a WebObject from a WebObject unconstrained copy ctor
     *
     * @param webObject The object to copy key/vals from
     * @exception org.json.JSONException on JSON error
     */
    protected WebObject(WebObject webObject) throws org.json.JSONException {
        super(webObject);
    }

    /**
     * Construct a WebObject from a WebObject using JSON's constrained copy ctor
     *
     * @param webObject The object to copy key/vals from
     * @param names
     * @exception org.json.JSONException on JSON error
     */
    protected WebObject(WebObject webObject, String[] names) throws org.json.JSONException {
        super(webObject, names);
    }

    /**
     * Construct a WebObject from a JSONObject unconstrained copy ctor
     *
     * @param jsonObject The object to copy key/vals from
     * @exception org.json.JSONException on JSON error
     */
    protected WebObject(JSONObject jsonObject) throws org.json.JSONException {
        super(jsonObject);
    }

    /**
     * Construct a WebObject from a JSONObject constrained copy ctor
     *
     * @param jsonObject The object to copy key/vals from
     * @param names
     * @exception org.json.JSONException on JSON error
     */
    protected WebObject(JSONObject jsonObject, String[] names) throws org.json.JSONException {
        super(jsonObject, names);
    }

    /**
     * Sets Vector of the JSON keys (names) that are valid for a given WebObject
     * extender.
     *
     * @param someNames The array of JSON keys (names) that are valid for a
     * given WebObject extender
     */
    public static NameList setNames(String[] someNames) {
        NameList names = new NameList(someNames.length);
        names.addAll(Arrays.asList(someNames));
        return names;
    }

    /**
     * Identifies whether a JSON key is one of the names given
     *
     * @param name the JSON key
     * @param names the vector of names that 'name' must be a member of
     * @return true if the key is a member
     */
    public static boolean isName(String name, NameList names) {
        return names.indexOf(name) != - 1;
    }

    /**
     * Returns the contents of the object as HTML with no enclosing markup. The
     * caller provides enclosing markup, if any.
     *
     * @return HTML content suitable for inclusion in an extant HTML body
     * section, with <b>no</b> leading nor trailing {@code &lt;br&nbsp;/&gt;}
     * though there can be internal {@code &lt;br&nbsp;/&gt;}'s. .
     * @throws org.json.JSONException
     */
    public String toHTML() throws JSONException {
        StringBuilder sb = new StringBuilder();
        Iterator it = keys();
        while (it.hasNext()) {
            String key = it.next().toString();
            sb.append("<b>").append(key).append(":</b> ").append(get(key).toString());
        }
        return sb.toString();
    }

    public static class NameList extends ArrayList<String> {

        public NameList(int initialCapacity) {
            super(initialCapacity);
        }

        public NameList() {
        }

        public NameList(Collection<? extends String> c) {
            super(c);
        }
    }
}
