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
import java.util.Enumeration;
import java.util.Vector;

/**
 * 
 * @author jax
 */
public class VSMStruct extends Vector<VSMParm> implements VSMParm {

    // private ParameterArray value;
    /**
     *
     * @return
     */
    public VSMStruct getValue() {
        return this;
    }

    /**
     * null is legal value, means "just clear me".
     * @param value
     */
    public void setValue(VSMStruct value) {
        clear();
        if (value != null) { // null is legal value
            addAll(value);   // means "just clear me"
        }
    }
    /**
     * let the un-init'ed be null!
     */
    private String formalName;

    /**
     *
     * @param formalName
     */
    public void setFormalName(String formalName) {
        this.formalName = formalName;
    }

    /**
     *
     */
    public VSMStruct() {
    }

    /**
     *
     * @param value
     */
    public VSMStruct(VSMStruct value) {
        this();
        setValue(value);
    }

    /**
     *
     * @param value
     * @param formalName
     */
    public VSMStruct(VSMStruct value, String formalName) {
        this(value);
        setFormalName(formalName);
    }

    /**
     *
     * @return
     */
    public int paramLength() {
        int total = 0;
        for (Enumeration<VSMParm> e = elements(); e.hasMoreElements();) {
            total += e.nextElement().paramLength();
        }
        return total;
    }

    // Interface implementation
    /**
     *
     * @return
     */
    public String getFormalName() {
        return formalName;
    }

    /**
     *
     * @param out
     * @throws java.io.IOException
     */
    public void write(DataOutputStream out) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * The vector of the struct contains at runtime the model of
     * what it's going to need to read. VMSStruct iterates through
     * its vector as a strategy and creates a new vector of the
     * items it reads one-at-a-time.
     * @param in
     * @param length
     * @throws java.io.IOException
     * @throws VSMStructStringReadException
     */
    public void read(DataInputStream in, int length) throws IOException, VSMStructStringReadException, VSMException {
        VSMStruct v = new VSMStruct();
        for (Enumeration<VSMParm> e = elements(); e.hasMoreElements();) {
            VSMParm model = e.nextElement();
            if (model instanceof VSMString) {
                VSMParm putativeStringLength = v.lastElement();
                if (putativeStringLength instanceof VSMInt4) {
                    VSMString newReceiver = new VSMString(null, model.getFormalName());
                    newReceiver.read(in, (VSMInt4.class.cast(putativeStringLength)).getValue());
                    v.add(newReceiver);
                } else {
                    throw new VSMStructStringReadException("Couldn't read string because previous parameter read was not a count of type int4.");
                }
            } else {
                VSMParm intParam = model.copyOf();
                intParam.read(in, -1);
                v.add(intParam);
            }
        }
        // Make 'this' be the new read-in struct
        setValue(v);
    }

    /**
     *
     */
    public class VSMStructStringReadException extends VSMException {

        /**
         *
         * @param message
         */
        public VSMStructStringReadException(String message) {
            super(message);
        }
    }

    /**
     *
     * @return
     */
    public VSMParm copyOf() {
        return new VSMStruct(this, formalName);
    }
}
