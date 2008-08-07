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
public class VSMInt1 implements VSMParm {

    private int value;
    private String formalName;

    /**
     * 
     */
    public VSMInt1() {
    }

    /** 
     * 
     * @param value
     */
    public VSMInt1(byte value) {
        this();
        setValue(value);
    }

    /** 
     * 
     * @param value
     */
    public VSMInt1(int value) {
        this();
        setValue(value);
    }

    /**
     * 
     * @param value
     */
    public void setValue(byte value) {
        this.value = value;

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
        return ParameterArray.SIZEOF_INT1;
    }

    /**
     * 
     * @param in
     * @throws java.io.IOException
     */
    public void read(DataInputStream in, int length) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * 
     * @param d
     * @throws java.io.IOException
     */
    public void write(DataOutputStream d)
            throws java.io.IOException {
        d.writeByte(new Integer(value).byteValue());
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
}
