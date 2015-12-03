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
// import java.util.logging.Level;
// import java.util.logging.Logger;

/**
 * Implements and encapsulates the VSMAPI <tt>int1</tt> basic type.
 *
 * @author jax
 * @see com.softwoehr.pigiron.access.VSMParm
 */
public class VSMInt1 implements VSMParm, VSMInt {

    private int value;
    private String formalName;
    /**
     * Type in terms of one of the formal parameter type discussed in the VSMAPI
     * documentation: int1, int4, int8, string, struct, array. (Pigiron also
     * recognizes <tt>counted_struct</tt>
     * as an extra type above and beyond the base types enumerated by the VSMAPI
     * documentation.)
     */
    public static final String FORMAL_TYPE = "int1";

    /**
     * Create an instance of specified value.
     *
     * @param value the value
     */
    public VSMInt1(int value) {
        setValue(value);
    }

    /**
     * Create an instance of specified value.
     *
     * @param value the value
     */
    public VSMInt1(byte value) {
        this.value = value;
    }

    /**
     * Create an instance of specified value and assign it a formal name
     *
     * @param value the value
     * @param formalName the formal name
     */
    public VSMInt1(int value, String formalName) {
        this(value);
        this.formalName = formalName;
    }

    /**
     * Create an instance of specified value and assign it a formal name
     *
     * @param value the value
     * @param formalName the formal name
     */
    public VSMInt1(byte value, String formalName) {
        this(value);
        setFormalName(formalName);
    }

    /**
     * Set the value.
     *
     * @param value the value
     */
    public final void setValue(byte value) {
        this.value = value;
    }

    /**
     * Set the value.
     *
     * @param value the value
     */
    public final void setValue(int value) {
        this.value = value;
    }

    /**
     * Get the value.
     *
     * @return the value
     */
    public int getValue() {
        return value;
    }

    /**
     * Get the length in bytes of the parameter.
     *
     * @return the length in bytes of the parameter value.
     */
    public int paramLength() {
        return ParameterArray.SIZEOF_INT1;
    }

    /**
     * Read in a VSMInt1 from a stream.
     *
     * @param in the input stream
     * @param length the byte length to read
     * @throws java.io.IOException on comm error
     */
    public void read(DataInputStream in, int length) throws IOException {
        // if (in.available() >= paramLength()) {
        setValue(in.readByte());
        // }
        // /* Debug */ System.err.println("Read an int1: " + value);
    }

    /**
     * Write a VSMInt1 on a stream.
     *
     * @param out the output stream
     * @throws java.io.IOException on comm error
     */
    public void write(DataOutputStream out)
            throws java.io.IOException {
        out.writeByte(new Integer(value).byteValue());
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
     * Return a functional copy of the instance.
     *
     * @return copy or null
     */
    public VSMParm copyOf() {
        return new VSMInt1(getValue(), getFormalName());
    }

//    /**
//     * Clone the instance.
//     * @return clone of the instance
//     * @see #copyOf()
//     */
//    @Override
//    public Object clone() {
//        VSMInt1 proto = new VSMInt1();
//        proto.setFormalName(formalName);
//        proto.setValue(getValue());
//        return proto;
//    }
    /**
     * The value as a <tt>long</tt>.
     *
     * @return the value as a <tt>long</tt>
     */
    public long getLongValue() {
        return new Long(getValue());
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
        sb.append(getFormalName()).append("(").append(getFormalType()).append(") ").append(getValue());
        return sb.toString();
    }
    /**
     * Used during debugging to test clone()/copyOf implementation.
     *
     * @param toCopy instance to copy
     * @return the copy of <tt>toCopy</tt>
     */
    /*public static boolean testCopyOf(VSMInt1 toCopy) {
    boolean result = false;
    VSMInt1 theCopy = VSMInt1.class.cast(toCopy.copyOf());
    System.out.println("theCopy: " + theCopy);
    System.out.println("theCopy: " + theCopy.prettyPrint());
    System.out.println("toCopy: " + toCopy);
    System.out.println("toCopy: " + toCopy.prettyPrint());
    System.out.println("theCopy == toCopy: " + (theCopy == toCopy));
    System.out.println("theCopy.equals(toCopy): " + (theCopy.equals(toCopy)));
    return result;
    }*/
    /**
     * Run a test on the copying of VSMInt1.
     *
     * @param argv ignored
     */
    /*public static void main(String[] argv) {
    VSMInt1 toCopy = new VSMInt1(997, "test_int1");
    System.out.println("Testing VSMInt1.testCopyOf()");
    testCopyOf(toCopy);

    }*/
}
