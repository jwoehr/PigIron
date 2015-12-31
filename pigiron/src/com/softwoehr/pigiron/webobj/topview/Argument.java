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

import com.softwoehr.pigiron.access.CountedArray;
import com.softwoehr.pigiron.access.CountedString;
import com.softwoehr.pigiron.access.CountedStringZ;
import com.softwoehr.pigiron.access.CountedStruct;
import com.softwoehr.pigiron.access.VSMArray;
import com.softwoehr.pigiron.access.VSMAsciiZ;
import com.softwoehr.pigiron.access.VSMAsciiZArray;
import com.softwoehr.pigiron.access.VSMInt;
import com.softwoehr.pigiron.access.VSMParm;
import com.softwoehr.pigiron.access.VSMString;
import com.softwoehr.pigiron.access.VSMStruct;
import com.softwoehr.pigiron.webobj.WebObject;

import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

/**
 * Represents an argument to a VSMAPI function
 *
 * @author jax
 */
public class Argument extends WebObject {

    /**
     * Names we use for members
     */
    /**
     * A Vector of the JSON keys (names) that are valid for a given WebObject
     * extender
     */
    private static NameList names = setNames(new String[]{"formal_name", "value"});

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
     * Create with defaults (empty).
     *
     * @exception JSONException on JSON err
     */
    public Argument() throws JSONException {
        super();
        initDefaults();
    }

    /**
     * Constructor for the Argument from a JSONObject that one supposes to be an
     * Argument instance.
     *
     * @param jsonObject argument as JSONObject
     * @exception JSONException on JSON err
     */
    public Argument(JSONObject jsonObject) throws JSONException {
        this();
        setFormalName(jsonObject.get("formal_name").toString());
        setValue(jsonObject.get("value"));
    }

    /**
     * Constructor for the Argument from a string of JSON representation
     *
     * @param jsonRepresentation argument described in JSON
     * @exception JSONException on JSON err
     */
    public Argument(String jsonRepresentation) throws JSONException {
        super(jsonRepresentation);
    }

    /**
     * Constructor for the Argument from a WebObject using only the members
     * named in Argument.names
     *
     * @param formal_name the PigIron VSMAPI formal parameter name
     * @param jsonObj the object to become the value of the param
     * @exception JSONException on JSON err
     */
    public Argument(String formal_name, JSONObject jsonObj) throws JSONException {
        this();
        setFormalName(formal_name);
        setValue(jsonObj);
    }

    /**
     * Constructor for the Argument from a JSONArray
     *
     * @param formal_name the PigIron VSMAPI formal parameter name
     * @param jsonArray the Array to become the value of the param
     * @exception JSONException on JSON err
     */
    public Argument(String formal_name, JSONArray jsonArray) throws JSONException {
        this();
        setFormalName(formal_name);
        setValue(jsonArray);
    }

    /**
     * Create with the formal name of the argument and the string value
     *
     * @param formal_name formal name
     * @param value string value
     * @throws org.json.JSONException on JSON err
     */
    public Argument(String formal_name, String value) throws JSONException {
        this();
        setFormalName(formal_name);
        setValue(value);
    }

    /**
     * Create with the formal name of the argument and the numerical value
     *
     * @param formal_name formal name
     * @param value numerical value
     * @exception JSONException on JSON err
     */
    public Argument(String formal_name, long value) throws JSONException {
        this();
        setFormalName(formal_name);
        setValue(value);
    }

    /**
     * Constructor for the Argument from a WebObject using only the members
     * named in Argument.names
     *
     * @param anArgument a like Argument
     * @exception JSONException on JSON err
     */
    public Argument(WebObject anArgument) throws JSONException {
        super(anArgument);
    }

    /**
     * init defaults
     *
     * @exception JSONException on JSON err
     */
    private void initDefaults() throws JSONException {
        setFormalName("");
        setValue(NULL);
    }

    /**
     * Get the value if it's a String
     *
     * @return the value
     * @throws JSONException on JSON err
     */
    public String getStringValue() throws JSONException {
        return getString("value");
    }

    /**
     * Get the value if it's a long
     *
     * @return the value
     * @throws JSONException on JSON err
     */
    public long getLongValue() throws JSONException {
        return getLong("value");
    }

    /**
     * Get the value if it's a JSONArray
     *
     * @return the value
     * @throws JSONException on JSON err
     */
    public JSONArray getJSONArrayValue() throws JSONException {
        return getJSONArray("value");
    }

    /**
     * Get the value if it's a JSONArray or null if not
     *
     * @return the value as a JSONArray or null if no dice
     */
    public JSONArray optJSONArrayValue() {
        return optJSONArray("value");
    }

    /**
     * Get the value if it's a JSONObject
     *
     * @return the value
     * @throws JSONException on JSON err
     */
    public JSONObject getJSONObjectValue() throws JSONException {
        return getJSONObject("value");
    }

    /**
     * Get the value if it's a JSONObject or null if not
     *
     * @return the value as a JSONObject or null if no dice
     */
    public JSONObject optJSONObjectValue() {
        return optJSONObject("value");
    }

    /**
     * Set the string value
     *
     * @param value the value
     * @throws JSONException on JSON err
     */
    public final void setValue(Object value) throws JSONException {
        if (value == null) {
            put("value", JSONObject.NULL);
        } else {
            put("value", value);
        }
    }

    /**
     * Set the string value
     *
     * @param value the value
     * @throws JSONException on JSON err
     */
    public final void setValue(String value) throws JSONException {
        if (value == null) {
            put("value", JSONObject.NULL);
        } else {
            put("value", value);
        }
    }

    /**
     * Set the numerical value
     *
     * @param value the value
     * @throws JSONException on JSON err
     */
    public final void setValue(long value) throws JSONException {
        put("value", value);
    }

    /**
     * Set the JSONObject value
     *
     * @param jsonObj the value
     * @throws JSONException on JSON err
     */
    public final void setValue(JSONObject jsonObj) throws JSONException {
        if (jsonObj == null) {
            put("value", JSONObject.NULL);
        } else {
            put("value", jsonObj);
        }
    }

    /**
     * Get the formal name
     *
     * @return the formal name
     * @throws JSONException on JSON err
     */
    public String getFormalName() throws JSONException {
        return getString("formal_name");
    }

    /**
     * Set the formal name
     *
     * @param formal_name the formal name
     * @throws JSONException on JSON err
     */
    public final void setFormalName(String formal_name) throws JSONException {
        put("formal_name", formal_name);
    }

    /**
     * Assimilate a VSMInt as an Argument
     *
     * @param vsmInt A VSMIntinstance to be used as an Argument
     * @return An Argument created from the type
     * @throws JSONException on JSON err
     */
    public static Argument from(VSMInt vsmInt) throws JSONException {
        Argument result = new Argument(VSMParm.class.cast(vsmInt).getFormalName(), vsmInt.getLongValue());
        return result;
    }

    /**
     * Assimilate a VSMString as an Argument
     *
     * @param vsmString A VSMString instance to be used as an Argument
     * @return An Argument created from the type
     * @throws JSONException on JSON err
     */
    public static Argument from(VSMString vsmString) throws JSONException {
        Argument result = new Argument(vsmString.getFormalName(), vsmString.getValue());
        return result;
    }

    /**
     * Assimilate a VSMString as an Argument
     *
     * @param countedString A CountedString instance to be used as an Argument
     * @return An Argument created from the type
     * @throws JSONException on JSON err
     */
    public static Argument from(CountedString countedString) throws JSONException {
        Argument result = new Argument(countedString.getFormalName(), countedString.getValue());
        return result;
    }

    /**
     * Assimilate a VSMAsciiZ as an Argument
     *
     * @param vsmAsciiZ A VSMAsciiZ instance to be used as an Argument
     * @return An Argument created from the type
     * @throws JSONException on JSON err
     */
    public static Argument from(VSMAsciiZ vsmAsciiZ) throws JSONException {
        Argument result = new Argument(vsmAsciiZ.getFormalName(), vsmAsciiZ.getValue());
        return result;
    }

    /**
     * Assimilate a VSMAsciiZ as an Argument
     *
     * @param countedStringZ A CountedStringZ instance to be used as an Argument
     * @return An Argument created from the type
     * @throws JSONException on JSON err
     */
    public static Argument from(CountedStringZ countedStringZ) throws JSONException {
        Argument result = new Argument(countedStringZ.getFormalName(), countedStringZ.getValue());
        return result;
    }

    /**
     * Assimilate a VSMStruct as an Argument
     *
     * @param vsmStruct A VSMStruct instance to be used as an Argument
     * @return An Argument created from the type
     * @throws JSONException on JSON err
     */
    public static Argument from(VSMStruct vsmStruct) throws JSONException {
        Argument result;
        JSONArray ja = new JSONArray();
        Iterator<VSMParm> it = vsmStruct.iterator();
        while (it.hasNext()) {
            Argument a = Argument.from(it.next());
            ja.put(a);
        }
        result = new Argument(vsmStruct.getFormalName(), ja);
        return result;
    }

    /**
     * Assimilate a CountedStruct as an Argument
     *
     * @param countStruct A CountedStruct instance to be used as an Argument
     * @return An Argument created from the type
     * @throws JSONException on JSON err
     */
    public static Argument from(CountedStruct countStruct) throws JSONException {
        Argument result;
        JSONArray ja = new JSONArray();
        Iterator<VSMParm> it = countStruct.iterator();
        while (it.hasNext()) {
            Argument a = Argument.from(it.next());
            ja.put(a);
        }
        result = new Argument(countStruct.getFormalName(), ja);
        return result;
    }

    /**
     * Assimilate a VSMArray as an Argument
     *
     * @param vsmArray A VSMArray instance to be used as an Argument
     * @return An Argument created from the type
     * @throws JSONException on JSON err
     */
    public static Argument from(VSMArray vsmArray) throws JSONException {
        Argument result;
        JSONArray ja = new JSONArray();
        Iterator<VSMParm> it = vsmArray.iterator();
        int i = 0;
        while (it.hasNext()) {
            Argument a = Argument.from(it.next());
            ja.put(a);
        }
        result = new Argument(vsmArray.getFormalName(), ja);
        return result;
    }

    /**
     * Assimilate a VSMAsciiZArray as an Argument
     *
     * @param vsmAsciiZArray A VSMAsciiZArray instance to be used as an argument
     * to a PigIron VSMAPI function
     * @return An Argument created from the type
     * @throws JSONException on JSON err
     */
    public static Argument from(VSMAsciiZArray vsmAsciiZArray) throws JSONException {
        Argument result;
        JSONArray ja = new JSONArray();
        Iterator<VSMParm> it = vsmAsciiZArray.iterator();
        int i = 0;
        while (it.hasNext()) {
            Argument a = Argument.from(it.next());
            ja.put(a);
        }
        result = new Argument(vsmAsciiZArray.getFormalName(), ja);
        return result;
    }

    /**
     * Assimilate a CountedArray as an Argument
     *
     * @param countedArray A CountedArray instance to be used as an argument to
     * a PigIron VSMAPI function
     * @return An Argument created from the type
     * @throws JSONException on JSON err
     */
    public static Argument from(CountedArray countedArray) throws JSONException {
        Argument result;
        JSONArray ja = new JSONArray();
        Iterator<VSMParm> it = countedArray.iterator();
        int i = 0;
        while (it.hasNext()) {
            Argument a = Argument.from(it.next());
            ja.put(a);
        }
        result = new Argument(countedArray.getFormalName(), ja);
        return result;
    }

    /**
     * Assimilate a VSMParm as an Argument
     *
     * @param vsmParm A VSMParm instance to be used as an argument to a PigIron
     * VSMAPI function
     * @return An Argument created from the type
     * @throws JSONException on JSON err
     */
    public static Argument from(VSMParm vsmParm) throws JSONException {
        Argument result = null;
        if (vsmParm instanceof CountedArray) {                // /* Debug */ System.err.println("VSMArray in Argument from(VSMParm vsmParm)");
            result = from(CountedArray.class.cast(vsmParm));
        } else if (vsmParm instanceof VSMAsciiZArray) {            // /* Debug */ System.err.println("VSMAsciiZArray in Argument from(VSMParm vsmParm)");
            result = from(VSMAsciiZArray.class.cast(vsmParm));
        } else if (vsmParm instanceof VSMArray) {                // /* Debug */ System.err.println("VSMArray in Argument from(VSMParm vsmParm)");
            result = from(VSMArray.class.cast(vsmParm));
        } else if (vsmParm instanceof CountedStruct) {                    // /* Debug */ System.err.println("CountedStruct in Argument from(VSMParm vsmParm)");
            result = from(CountedStruct.class.cast(vsmParm));
        } else if (vsmParm instanceof VSMStruct) {                        // /* Debug */ System.err.println("VSMStruct in Argument from(VSMParm vsmParm)");
            result = from(VSMStruct.class.cast(vsmParm));
        } else if (vsmParm instanceof CountedStringZ) {
            result = from(CountedStringZ.class.cast(vsmParm));
        } else if (vsmParm instanceof VSMAsciiZ) {                            // /* Debug */ System.err.println("VSMAsciiZ in Argument from(VSMParm vsmParm)");
            result = from(VSMAsciiZ.class.cast(vsmParm));
        } else if (vsmParm instanceof CountedString) {
            result = from(CountedString.class.cast(vsmParm));
        } else if (vsmParm instanceof VSMString) {                                // /* Debug */ System.err.println("VSMString in Argument from(VSMParm vsmParm)");
            result = from(VSMString.class.cast(vsmParm));
        } else if (vsmParm instanceof VSMInt) {                                    // /* Debug */ System.err.println("VSMInt in Argument from(VSMParm vsmParm)");
            result = from(VSMInt.class.cast(vsmParm));
        } else {
            Logger.getLogger(Argument.class.getName()).log(Level.WARNING, "unrecogized Argument in from(VSMParm)", vsmParm);
        }
        return result;
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
    @Override
    public String toHTML() throws JSONException {
        StringBuilder sb = new StringBuilder();
        sb.append("<tt>").append(getFormalName()).append("</tt>: ");
        JSONArray ja = optJSONArrayValue();
        if (ja == null) { // it's not a JSONArray
            JSONObject jo = optJSONObjectValue();
            if (jo == null) { // it's not a JSONObject
                sb.append(getStringValue());
            } else { // it's a JSONObject
                sb.append("<blockquote>");
                // sb.append(new Argument(jo).toHTML());
                sb.append(getStringValue());
                sb.append("</blockquote>\n");
            }
        } else { // it's a JSONArray
            sb.append("<ul>\n");
            sb.append(new ArgumentArray(ja).toHTML());
            sb.append("</ul>\n");
        }
        return sb.toString();
    }

    /**
     * The main program for the Argument class
     *
     * @param args two strings to pretend to be formal name and value.
     * @exception JSONException Description of the Exception
     */
    public static void main(String[] args) throws JSONException {
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
