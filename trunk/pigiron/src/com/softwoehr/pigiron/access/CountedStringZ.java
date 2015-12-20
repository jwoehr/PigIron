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
 * Implements and encapsulates the VSMAPI <tt>ASCIIZ string</tt> basic type with
 * a VSMInt4 count byte.
 *
 * @author jax
 * @see com.softwoehr.pigiron.access.VSMParm
 */
public class CountedStringZ implements VSMParm {

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
    private Integer lengthCount;

    /**
     * Create an instance of undefined value.
     */
    public CountedStringZ() {
        lengthCount = 0;
    }

    /**
     * Create an instance of specified value. Add a null terminator char to
     * value if needed.
     *
     * @param value the value
     */
    public CountedStringZ(String value) {
        this();
        this.value = value;
        if (value != null && !value.endsWith("\0")) {
            value = value.concat("\0");
        }
        this.lengthCount = value == null ? 0 : value.length();
    }

    /**
     * Create an instance of specified value and assign it a formal name.
     *
     * @param value the value
     * @param formalName the formal name
     */
    public CountedStringZ(String value, String formalName) {
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
     * Get length of the string
     *
     * @return The length of the string
     */
    public Integer getLengthCount() {
        return lengthCount;
    }

    /**
     * Set length of the string
     *
     * @param lengthCount length of the string
     */
    public void setLengthCount(Integer lengthCount) {
        this.lengthCount = lengthCount;
    }

    /**
     * Get the length in bytes of the parameter.
     *
     * @return the length in bytes of the parameter value.
     */
    public int paramLength() {
        return ParameterArray.SIZEOF_INT4 + lengthCount;
    }

    /**
     * Read in a VSMString from a stream.
     *
     * @param in the input stream
     * @param length the max length of the read
     * @throws java.io.IOException on comm error
     */
    public void read(DataInputStream in, int length) throws IOException, VSMException {
        if (length < ParameterArray.SIZEOF_INT4) {
            throw new VSMException("Remaining length shorter than size of CountedString count word");
        }
        setLengthCount(in.readInt());
        byte[] bytes = new byte[getLengthCount()];
        in.readFully(bytes);
        setValue(new String(bytes));
    }

    /**
     * Write the String parameter to the output stream preceded by its length
     * word.
     *
     * @param out the output stream
     * @throws java.io.IOException on comm error
     * @see com.softwoehr.pigiron.access.ParameterArray
     */
    public void write(DataOutputStream out)
            throws java.io.IOException {
        // /* debug */ System.err.println("length of CountedStringZ is " + getLengthCount());
        // /* debug */ System.err.println("value of CountedStringZ is " + getValue());
        out.writeInt(getLengthCount());
        if (value != null) {
            out.write(value.getBytes());
        }
    }

    /**
     * Get the formal name of the parameter conforming to the VSMAPI docs for a
     * given call.
     *
     * @return the formal name of the parameter
     * @see com.softwoehr.pigiron.access.VSMParm
     */
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
    public VSMParm copyOf() {
        return new CountedStringZ(getValue(), getFormalName());
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
    public String getFormalType() {
        return FORMAL_TYPE;
    }

    /**
     * Prettyprint the instance for debugging or simple output display.
     *
     * @return Prettyprint of the instance for debugging or simple output
     * display
     */
    public String prettyPrint() {
        StringBuilder sb = new StringBuilder();
        sb.append(getFormalName()).append("(").append(getFormalType()).append(") count:").append(getLengthCount()).append(" value: ").append(getValue());
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
