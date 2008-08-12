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
 * 
 * @author jax
 */
public class VSMInt4 implements VSMParm, VSMInt {

    private int value = -1;
    // -1 indicates "Unspecified"
    private String formalName;
    /**
     * Type in terms of one of the formal parameter type discussed in
     * the VSMAPI documentation: int1, int4, int8, string, struct, array.
     */
    public static final String FORMAL_TYPE = "int4";

    /**
     * 
     */
    public VSMInt4() {
    }

    /** 
     * 
     * @param value
     */
    public VSMInt4(int value) {
        this();
        setValue(value);
    }

    /**
     *
     * @param value
     * @param formalName
     */
    public VSMInt4(int value, String formalName) {
        this(value);
        setFormalName(formalName);
    }

    /**
     * 
     * @param value
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * 
     * @return 
     */
    public int getValue() {
        return value;
    }

    /*
     * Interface methods
     */
    /**
     * 
     * @return
     */
    public int paramLength() {
        return ParameterArray.SIZEOF_INT4;
    }

    /**
     * 
     * @param in
     * @throws java.io.IOException
     */
    public void read(DataInputStream in, int length) throws IOException {
        setValue(in.readInt());
    // /* Debug */ System.err.println("Read an int4: " + value);
    }

    /**
     * 
     * @param d
     * @throws java.io.IOException
     */
    public void write(DataOutputStream d)
            throws java.io.IOException {
        d.writeInt(value);
    }

    public String getFormalName() {
        return formalName;
    }

    public void setFormalName(String formalName) {
        this.formalName = formalName;
    }

    /**
     *
     * @return copy or null
     */
    public VSMParm copyOf() {
        /* return new VSMInt4(value, formalName);*/
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
        VSMInt4 proto = new VSMInt4();
        proto.setFormalName(formalName);
        proto.setValue(getValue());
        return proto;
    }

    /**
     *
     * @return
     */
    public long getLongValue() {
        return new Long(getValue()).longValue();
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

    /**
     *
     * @return
     */
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
    public static boolean testCopyOf(VSMInt4 toCopy) {
        boolean result = false;
        VSMInt4 theCopy = VSMInt4.class.cast(toCopy.copyOf());
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
        VSMInt4 toCopy = new VSMInt4(787, "test_int4");
        System.out.println("Testing VSMInt4.testCopyOf()");
        testCopyOf(toCopy);
    }
}
