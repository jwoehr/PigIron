/*
 * Copyright (c) 2008, Jack J. Woehr jwoehr@softwoehr.com
 * PO Box 51, Golden, Colorado 80402-0051 USA
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *     * Redistributions of source code must retain the above copyright
 *         notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *         notice, this list of conditions and the following disclaimer
 *         in the documentation and/or other materials provided with the
 *         distribution.
 *     * Neither the name of the PigIron Project nor the names of its
 *         contributors may be used to endorse or promote products derived
 *         from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.softwoehr.pigiron.bizobj;

import com.softwoehr.pigiron.functions.VSMCall;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

/**
 * Object interpreting a VSMAPI Reason Code associated with a particular
 * Return Code.
 *
 * @author jax
 */
public class ReasonCode {

    private final String message;
    private final String name;
    private final int value;
 
    private final Hashtable <Class, ReasonCodeOverload> reasonCodeOverloads = new Hashtable<Class, ReasonCodeOverload>();

    /**
     * Instance providing message name and value
     * @param message interpretive message
     * @param name VSMAPI name of the reason code
     * @param value numeric value of the reason code
     */ 
    public ReasonCode(String message, String name, int value) {
        this.message = message;
        this.name = name;
        this.value = value;
    }
 
    /**
     * Instance providing message, name, and value, and a Vector of
     * overloads. The base message, name and value pair is whichever
     * of the overloads one wants. Then at runtime first the overload
     * list is searched to see if it matches a function before defaulting
     * to the base.
     * @param message interpretive message
     * @param name VSMAPI name of the reason code
     * @param value numeric value of the reason code
     * @param reasonCodeOverloads a Vector of message/name/value/function
     *        unities to use as overloads on per-function basis  
     */ 
    public ReasonCode(String message, String name, int value, Vector <ReasonCodeOverload> reasonCodeOverloads) {
        this(message,name,value);
        Iterator <ReasonCodeOverload> it = reasonCodeOverloads.iterator();
        ReasonCodeOverload rco = null;
        while (it.hasNext()) {
            rco = it.next();
            this.reasonCodeOverloads.put(rco.function, rco);
        }
    }

    /**
     * Return interpretive message of the reason code
     * @return interpretive message
     */ 
    public String getMessage(VSMCall function) {
        String result = this.message;
	if (function != null) {
	    if (reasonCodeOverloads != null) {
		if (reasonCodeOverloads.containsKey(function.getClass())) {
		    result = reasonCodeOverloads.get(function.getClass()).message;
		}
            }
        }
        return result;
    }

    /**
     * Return VSMAPI name of the reason code for a given function.
     * @param function the calling function in order to disambiguate any
     * overloaded return/reason pairs - {@code null} means return the default.
     * @return VSMAPI name of the reason code
     */ 
    public String getName(VSMCall function) {
        String result = this.name;
	if (function != null) {
	    if (reasonCodeOverloads != null) {
		if (reasonCodeOverloads.containsKey(function.getClass())) {
		    result = reasonCodeOverloads.get(function.getClass()).name;
		}
            }
        }
        return result;
    }

    /**
     * Return numeric value of the reason code
     * @param function the calling function in order to disambiguate any
     * overloaded return/reason pairs - {@code null} means return the default.
     * @return numeric value of the reason code
     */ 
    public int getValue(VSMCall function) {
        int result = this.value;
	if (function != null) {
	    if (reasonCodeOverloads != null) {
		if (reasonCodeOverloads.containsKey(function.getClass())) {
		    result = reasonCodeOverloads.get(function.getClass()).value;
		}
            }
        } 
        return result;
    }
}