/*
 * Copyright (c) 2008, 2015 Jack J. Woehr jwoehr@softwoehr.com
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
package com.softwoehr.pigiron.access;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Implements and encapsulates the VSMAPI <tt>string</tt> basic type.
 *
 * @author jax
 * @see com.softwoehr.pigiron.access.VSMParm
 */
public class VSMString implements VSMParm {

    /**
     *
     */
    /**
     * Type in terms of one of the formal parameter type discussed in the VSMAPI
     * documentation: <tt>int1</tt>, <tt>int4</tt>,
     * <tt>int8</tt>, <tt>string</tt>, <tt>struct</tt>, <tt>array</tt>. (Pigiron
     * also recognizes <tt>counted_struct</tt>
     * as an extra type above and beyond the base types enumerated by the VSMAPI
     * documentation.)
     *
     * @see com.softwoehr.pigiron.access.VSMParm
     * @see com.softwoehr.pigiron.access.CountedStruct
     */
    public static final String FORMAL_TYPE = "string";
    private String value;
    private String formalName;

    /**
     * Create an instance of undefined value.
     */
    public VSMString() {
    }

    /**
     * Create an instance of specified value.
     *
     * @param value the value
     */
    public VSMString(String value) {
        this();
        this.value = value;
    }

    /**
     * Copy ctor
     *
     * @param vSMString
     */
    public VSMString(VSMString vSMString) {
        this(vSMString.getValue(), vSMString.getFormalName());
    }

    /**
     * Create an instance of specified value and assign it a formal name.
     *
     * @param value the value
     * @param formalName the formal name
     */
    public VSMString(String value, String formalName) {
        this(value);
        this.formalName = formalName;
    }

    /**
     * Get the value.
     *
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * Set the value.
     *
     * @param value the value
     */
    public final void setValue(String value) {
        this.value = value;
    }

    /**
     * Get the length in bytes of the parameter.
     *
     * @return the length in bytes of the parameter value.
     */
    @Override
    public int paramLength() {
        int result = 0;
        if (value != null) {
            result = value.length();
        }
        return result;
    }

    /**
     * Read in a VSMString from a stream.
     *
     * @param in the input stream
     * @param length the max length of the read
     * @throws java.io.IOException on comm error
     */
    @Override
    public void read(DataInputStream in, int length) throws IOException {
        // if (in.available() >= length) {
        // /* Debug */ System.err.println("About to read a string of length " + length);
        byte[] bytes = new byte[length];
        in.readFully(bytes);
        setValue(new String(bytes));
        // /* Debug */ System.err.println("Read a string: " + value);
        // }
    }

    /**
     * Write the String parameter to the output stream. Its length <tt>int4</tt>
     * must already have been written by the enclosing <tt>ParameterArray</tt>
     * having had a VSMInt4 entry with the length indexed one (1) prior to this
     * VSMString.
     *
     * @param out the output stream
     * @throws java.io.IOException on comm error
     * @see com.softwoehr.pigiron.access.ParameterArray
     */
    @Override
    public void write(DataOutputStream out)
            throws java.io.IOException {
        out.write(value.getBytes());
    }

    /**
     * Get the formal name of the parameter conforming to the VSMAPI docs for a
     * given call.
     *
     * @return the formal name of the parameter
     * @see com.softwoehr.pigiron.access.VSMParm
     */
    @Override
    public String getFormalName() {
        return formalName;
    }

    /**
     * Set the formal name of the parameter conforming to the VSMAPI docs for a
     * given call.
     *
     * @param formalName the formal name of the parameter
     * @see com.softwoehr.pigiron.access.VSMParm
     */
    public final void setFormalName(String formalName) {
        this.formalName = formalName;
    }

    /**
     * Return a deep copy of the instance.
     *
     * @return copy or null
     */
    @Override
    public VSMParm copyOf() {
        return new VSMString(getValue(), getFormalName());
    }

    /**
     * String representation of the instance for debugging.
     *
     * @return String representation of the instance for debugging
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append(" Formal Name == ").append(getFormalName()).append(" Formal Type == ").append(getFormalType());
        sb.append(" Value == ").append(getValue());
        return sb.toString();
    }

    /**
     * Get the formal type of the parmeter, one of the formal parameter types
     * discussed in the VSMAPI documentation: <tt>int1</tt>, <tt>int4</tt>,
     * <tt>int8</tt>, <tt>string</tt>, <tt>struct</tt>, <tt>array</tt>.
     *
     * Pigiron recognizes <tt>counted_struct</tt>
     * as an extra type above and beyond the base types enumerated by the VSMAPI
     * documentation.
     *
     * @return the fornal type in a string with the case set as in the docs
     */
    @Override
    public String getFormalType() {
        return FORMAL_TYPE;
    }

    /**
     * Prettyprint the instance for debugging or simple output display.
     *
     * @return Prettyprint of the instance for debugging or simple output
     * display
     */
    @Override
    public String prettyPrint() {
        StringBuilder sb = new StringBuilder();
        sb.append(getFormalName()).append("(").append(getFormalType()).append(") ").append(getValue());
        return sb.toString();
    }

    /**
     *
     * @param argv
     */
    /*public static void main(String[] argv) {
    VSMString toCopy = new VSMString("Elephants march at night without rest!", "test_string");
    System.out.println("Testing VSMString.testCopyOf()");
    testCopyOf(toCopy);

    }*/
}
