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
 * Implements and encapsulates the VSMAPI <tt>int8</tt> basic type.
 *
 * @author jax
 * @see com.softwoehr.pigiron.access.VSMParm
 */
public class VSMInt8 implements VSMParm, VSMInt {

    private long value = -1;
    // -1 indicates "Unspecified"
    private String formalName;
    /**
     * Type in terms of one of the formal parameter type discussed in the VSMAPI
     * documentation: int1, int4, int8, string, struct, array. (Pigiron also
     * recognizes <tt>counted_struct</tt>
     * as an extra type above and beyond the base types enumerated by the VSMAPI
     * documentation.)
     */
    public static final String FORMAL_TYPE = "int8";

    /**
     * Create an instance of specified value.
     *
     * @param value the value
     */
    public VSMInt8(long value) {
        this.value = value;
    }

    /**
     * Create an instance of specified value and assign it a formal name.
     *
     * @param value the value
     * @param formalName the formal name
     */
    public VSMInt8(long value, String formalName) {
        this(value);
        this.formalName = formalName;
    }

    /**
     * Set the value.
     *
     * @param value the value
     */
    public final void setValue(long value) {
        this.value = value;
    }

    /**
     * Get the value.
     *
     * @return the value
     */
    public long getValue() {
        return value;
    }

    /**
     * Get the length in bytes of the parameter.
     *
     * @return the length in bytes of the parameter value.
     */
    @Override
    public int paramLength() {
        return ParameterArray.SIZEOF_INT8;
    }

    /**
     * Read in a VSMInt8 from a stream.
     *
     * @param in the input stream
     * @param length the max length of the read
     * @throws java.io.IOException on comm error
     */
    @Override
    public void read(DataInputStream in, int length) throws IOException {
        // if (in.available() >= paramLength()) {
        setValue(in.readLong());
        // }
    }

    /**
     * Write a VSMInt8 on a stream.
     *
     * @param out output stream
     * @throws java.io.IOException on comm error
     */
    @Override
    public void write(DataOutputStream out)
            throws java.io.IOException {
        out.writeLong(value);
    }

    /**
     * The value as a <tt>long</tt>.
     *
     * @return the value as a <tt>long</tt>
     */
    @Override
    public long getLongValue() {
        return getValue();
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
        return new VSMInt8(value, formalName);
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
        sb.append(" Value == ").append(value);
        return sb.toString();
    }

    /**
     * Get the formal type of the parameter conforming to the VSMAPI docs.
     *
     * @return the formal type of the parameter
     * @see com.softwoehr.pigiron.access.VSMParm
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
    VSMInt8 toCopy = new VSMInt8(62345678, "test_int8");
    System.out.println("Testing VSMInt8.testCopyOf()");
    testCopyOf(toCopy);

    }*/
}
