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
     * Type in terms of one of the formal parameter type discussed in
     * the VSMAPI documentation: <tt>int1</tt>, <tt>int4</tt>,
     * <tt>int8</tt>, <tt>string</tt>, <tt>struct</tt>, <tt>array</tt>.
     * (Pigiron also recognizes <tt>counted_struct</tt>
     * as an extra type above and beyond the base types enumerated
     * by the VSMAPI documentation.)
     */
    public static final String FORMAL_TYPE = "string";
    private String value;
    private String formalName;

    /**
     * 
     */
    public VSMString() {
    }

    /**
     * 
     * @param value
     */
    public VSMString(String value) {
        this();
        setValue(value);
    }

    /**
     *
     * @param value
     * @param formalName
     */
    public VSMString(String value, String formalName) {
        this(value);
        setFormalName(formalName);
    }

    /**
     * 
     * @return
     */
    public String getValue() {
        return value;
    }

    /**
     * 
     * @param value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /*
     * Interface methods
     */
    /**
     * 
     * @return
     */
    public int paramLength() {
        int result = 0;
        if (value != null) {
            result = value.length();
        }
        return result;
    }

    /**
     *
     * @param in
     * @throws java.io.IOException
     */
    public void read(DataInputStream in, int length) throws IOException {
        byte[] bytes = new byte[length];
        in.readFully(bytes);
        setValue(new String(bytes));
    // /* Debug */ System.err.println("Read a string: " + value);
    }

    /**
     * Write the String parameter with a prepended count.
     * Apparently this count is always int4.
     * @param d
     * @throws java.io.IOException
     */
    public void write(DataOutputStream d)
            throws java.io.IOException {
        d.write(value.getBytes());
    }

    /**
     *
     * @return
     */
    public String getFormalName() {
        return formalName;
    }

    /**
     *
     * @param formalName
     */
    public void setFormalName(String formalName) {
        this.formalName = formalName;
    }

   /**
     *
     * @return copy or null
     */
    public VSMParm copyOf() {
        /* return new VSMInt8(value, formalName);*/
        VSMParm bozo = null;
        bozo = VSMParm.class.cast(clone());
        return bozo;
    }

    /**
     *
     * @return
     */
    @Override
    public Object clone() {
        VSMString proto = new VSMString();
        proto.setFormalName(formalName);
        proto.setValue(getValue());
        return proto;
    }
    /**
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer(super.toString());
        sb.append(" Formal Name == " + getFormalName() + " Formal Type == " + getFormalType());
        sb.append(" Value == " + value);
        return sb.toString();
    }

    public String getFormalType() {
        return FORMAL_TYPE;
    }

    /**
     *
     * @return
     */
    public String prettyPrint() {
        StringBuffer sb = new StringBuffer();
        sb.append(getFormalName() + "(" + getFormalType() + ") " + getValue());
        return sb.toString();
    }

    /**
     *
     * @param toCopy
     * @return
     */
    public static boolean testCopyOf(VSMString toCopy) {
        boolean result = false;
        VSMString theCopy = VSMString.class.cast(toCopy.copyOf());
        System.out.println("theCopy: " + theCopy);
        System.out.println("theCopy: " + theCopy.prettyPrint());
        System.out.println("toCopy: " + toCopy);
        System.out.println("toCopy: " + toCopy.prettyPrint());
        System.out.println("theCopy == toCopy: " + (theCopy == toCopy));
        System.out.println("theCopy.equals(toCopy): " + (theCopy.equals(toCopy)));
        return result;
    }

    /**
     *
     * @param argv
     */
    public static void main(String[] argv) {
        VSMString toCopy = new VSMString("Elephants march at night without rest!", "test_string");
        System.out.println("Testing VSMString.testCopyOf()");
        testCopyOf(toCopy);

    }
}
