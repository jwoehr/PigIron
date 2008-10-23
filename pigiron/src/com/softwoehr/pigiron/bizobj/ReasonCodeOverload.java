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

/**
 * Object packaging infor for a VSMAPI Reason Code associated with a particular
 * Return Code.
 *
 * @see com.softwoehr.pigiron.bizobj.VsmapiRC
 * @author jax
 */
public class ReasonCodeOverload {
    /** The return/reason code message */ 
    public String message;
    /** The return/reason code name */ 
    public String name;
    /** The return/reason code numerical value */ 
    public int value;
    
    /**
     * The function associated with this particular overload of
     * return/reason code message
     */ 
    public Class function;

    /**
     * Create a representation of an overload to be added to a table of
     * same associated with the reason code
     *
     * @param message The return/reason code message
     * @param name The return/reason code name
     * @param value The return/reason code numerical value
     * @param function The function associated with this particular
     * overload of return/reason code message
     * @see com.softwoehr.pigiron.bizobj.ReasonCode
     */ 
    public ReasonCodeOverload(String message, String name, int value, Class function) {
        this.message = message;
        this.name = name;
        this.value = value;
        this.function = function;
	// /* Debug */ System.out.println(this);
    }
    
    @Override
    public String toString() {
	StringBuffer sb = new StringBuffer(super.toString() + " ");
	sb.append("message= " + message + " name = " + name + " value = " + value + " function = " + function);
	return sb.toString();
    }
}
