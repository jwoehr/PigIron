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
import com.softwoehr.pigiron.access.VSMParm;
import com.softwoehr.pigiron.access.VSMInt;
import com.softwoehr.pigiron.access.VSMString;
import com.softwoehr.pigiron.access.VSMAsciiZ;
import com.softwoehr.pigiron.access.VSMStruct;
import com.softwoehr.pigiron.access.VSMArray;
import com.softwoehr.pigiron.access.VSMAsciiZArray;
import com.softwoehr.pigiron.webobj.WebObject;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Represents an argument to a VSMAPI function
 *
 * @author     jax
 */
public class Argument extends WebObject {

    /**
     *  Names we use for members
     */ 
    static {
        setNames(new String []{"formal_name" ,"value"}
       ); 
    }

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
     *Constructor for the Argument from a WebObject using only
     * the members named in Argument.names
     *
     * @param formal_name      the PigIron VSMAPI formal parameter name
     * @param  jsonObj         the object to become the value of the param
     * @exception  JSONException  on JSON err
     */ 
    public Argument(String formal_name, JSONObject jsonObj) throws JSONException {

        this();
        setFormalName(formal_name);
        setValue(jsonObj.toString());
    }

    /**
     * Create with the formal name of the argument and the string value
     *
     * @param  formal_name               formal name
     * @param  value                    string value
     * @throws  org.json.JSONException  on JSON err
     */ 
    public Argument(String formal_name, String value) throws JSONException {
        this();
        setFormalName(formal_name);
        setValue(value);
    }

    /**
     * Create with the formal name of the argument and the numerical value
     *
     * @param  formal_name               formal name
     * @param  value                    numerical value
     * @exception  JSONException        on JSON err
     * @throws  org.json.JSONException  on JSON err
     */ 
    public Argument(String formal_name, long value) throws JSONException {
        this();
        setFormalName(formal_name);
        setValue(value);
    }

    /**
     *Constructor for the Argument from a WebObject using only
     * the members named in Argument.names
     *
     * @param  anArgument         a like Argument
     * @exception  JSONException  on JSON err
     */ 
    public Argument(WebObject anArgument) throws JSONException {
        super(anArgument);
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
     * Get the value if it's a String
     *
     * @return                 the value
     * @throws  JSONException  on JSON err
     */ 
    public String getStringValue() throws JSONException {
        return getString("value");
    }
 
    /**
     * Set the string value
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
     * Set the numerical value
     *
     * @param  value           the value
     * @throws  JSONException  on JSON err
     */ 
    public void setValue(long value) throws JSONException {
        put("value", value);
    }

    /**
     * Get the formal name
     *
     * @return                 the formal name
     * @throws  JSONException  on JSON err
     */ 
    public String getFormalName() throws JSONException {
        return getString("formal_name");
    }

    /**
     * Set the formal name
     *
     * @param  formal_name      the formal name
     * @throws  JSONException  on JSON err
     */ 
    public void setFormalName(String formal_name) throws JSONException {
        put("formal_name", formal_name);
    }

    /**
     *  Assimilate a VSMInt as an Argument
     *
     * @param  vsmInt  A VSMIntinstance to be used as an Argument
     * @return        An Argument created from the type
     * @throws  JSONException  on JSON err
     */ 
    public static Argument from(VSMInt vsmInt) throws JSONException {
        Argument result = new Argument(VSMParm .class.cast(vsmInt).getFormalName(), vsmInt.getLongValue());
                

        return result;
    }

    /**
     *  Assimilate a VSMString as an Argument
     *
     * @param  vsmString  A VSMString instance to be used as an Argument
     * @return         An Argument created from the type
     * @throws  JSONException  on JSON err
     */ 
    public static Argument from(VSMString vsmString) throws JSONException {
        Argument result = new Argument(vsmString.getFormalName(), vsmString.getValue());
        return result;
    }

    /**
     *  Assimilate a VSMAsciiZ as an Argument
     *
     * @param  vsmAsciiZ  A VSMAsciiZ instance to be used as an Argument
     * @return         An Argument created from the type
     * @throws  JSONException  on JSON err
     */ 
    public static Argument from(VSMAsciiZ vsmAsciiZ) throws JSONException {
        Argument result = new Argument(vsmAsciiZ.getFormalName(), vsmAsciiZ.getValue());
        return result;
    }

    /**
     *  Assimilate a VSMStruct as an Argument
     *
     * @param  vsmStruct  A VSMStruct instance to be used as an Argument
     * @return         An Argument created from the type
     * @throws  JSONException  on JSON err
     */ 
    public static Argument from(VSMStruct vsmStruct) throws JSONException {
        Argument result = null;
        JSONObject jo = new JSONObject();
        Iterator <VSMParm> it = vsmStruct.iterator();
        while (it.hasNext()) {
            Argument a = Argument.from(it.next());
            jo.put(a.getFormalName(), a.getStringValue());
        }
        result = new Argument(vsmStruct.getFormalName(), jo);
        return result;
    }

    /**
     *  Assimilate a VSMArray as an Argument
     *
     * @param  vsmArray  A VSMArray instance to be used as an Argument
     * @return         An Argument created from the type
     * @throws  JSONException  on JSON err
     */ 
    public static Argument from(VSMArray vsmArray) throws JSONException {
        Argument result = from(VSMStruct .class.cast(vsmArray));
        return result;
    }


    /**
     *  Assimilate a VSMAsciiZArray as an Argument
     *
     * @param  vsmAsciiZArray  A VSMAsciiZArray instance to be used as an Argument
     * @return         An Argument created from the type
     * @throws  JSONException  on JSON err
     */ 
    public static Argument from(VSMAsciiZArray vsmAsciiZArray) throws JSONException {
        Argument result = from(VSMStruct .class.cast(vsmAsciiZArray));
        return result;
    }

    /**
     *  Assimilate a VSMParm as an Argument
     *
     * @param  vsmParm  A VSMParm instance to be used as an Argument
     * @return         An Argument created from the type
     * @throws  JSONException  on JSON err
     */ 
    public static Argument from(VSMParm vsmParm) throws JSONException {
        Argument result = null;
        if (vsmParm instanceof VSMInt) {
            result = from(VSMInt .class.cast(vsmParm));
        } else {
            if (vsmParm instanceof VSMString) {
                result = from(VSMString .class.cast(vsmParm));
            } else {
                if (vsmParm instanceof VSMAsciiZ) {
                    result = from(VSMAsciiZ .class.cast(vsmParm));
                } else {
                    if (vsmParm instanceof VSMStruct) {
                        result = from(VSMStruct .class.cast(vsmParm));
                    } else {
                        if (vsmParm instanceof VSMArray) {
                            result = from(VSMArray .class.cast(vsmParm));
                        } else {
                            if (vsmParm instanceof VSMAsciiZArray) {
                                result = from(VSMAsciiZArray .class.cast(vsmParm));
                            }
                        }
                    }
                }
            }
        }
        return result;
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
