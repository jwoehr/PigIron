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
import java.util.Iterator;
import java.util.Vector;

/**
 * 
 * @author jax
 */
public class VSMStruct extends Vector<VSMParm> implements VSMParm {

    /**
     * Type in terms of one of the formal parameter type discussed in
     * the VSMAPI documentation: int1, int4, int8, string, struct, array.
     */
    public static final String FORMAL_TYPE = "struct";

    /**
     * Struct types like CPU_info_array
     * This sort of thing probably belongs
     * in the yet-to-be-retroactively-written
     * superclass of all paramstructs.
     */
    // public static final String  STRUCTURE_TYPE = "";
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
        super();
    }

    /**
     *
     * @param value
     */
    public VSMStruct(VSMStruct value) {
        this();
        // setValue(value);
        Iterator<VSMParm> it = iterator();
        while (it.hasNext()) {
            add(it.next().copyOf());
        }
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
        Iterator<VSMParm> i = iterator();
        // /* Debug */ System.err.println("((( In VSMSTruct.paramLength() we're examining " + this);
        while (i.hasNext()) {
            VSMParm parm = i.next();
            total += parm.paramLength();
        // /* Debug */ System.err.println("((( In VSMSTruct.paramLength() " + parm + " added " + parm.paramLength() + " for total == " + total);
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
        Iterator<VSMParm> i = iterator();
        while (i.hasNext()) {
            i.next().write(out);
        }
    }

    /**
     * The vector of the struct contains at runtime the model of
     * what it's going to need to read. VMSStruct iterates through
     * its vector as a strategy and creates a new vector of the
     * items it reads one-at-a-time. It then instantiates the new
     * vector in itself.
     * @param in
     * @param length
     * @throws java.io.IOException
     * @throws VSMStructStringReadException
     */
    public void read(DataInputStream in, int length) throws IOException, VSMStructStringReadException, VSMException {
        VSMStruct myNewContents = new VSMStruct();
        VSMParm member = null;
        Iterator<VSMParm> i = iterator(); // Walk through our output model
        while (i.hasNext() & length > 0) {
            VSMParm model = i.next();
            if (model instanceof VSMStruct) {
                VSMParm putativeLength = myNewContents.lastElement(); /* What did we last read? */
                if (putativeLength instanceof VSMInt4) {
                    member = model.copyOf();
                    member.read(in, (VSMInt4.class.cast(putativeLength)).getValue());
                } else {
                    throw new VSMStructStructReadException("Couldn't read struct because previous parameter read was not a count of type int4.");
                }
            } else if (model instanceof VSMArray) {
                VSMParm putativeLength = myNewContents.lastElement();
                if (putativeLength instanceof VSMInt4) {
                    member = new VSMArray();
                    member.read(in, (VSMInt4.class.cast(putativeLength)).getValue());
                } else {
                    throw new VSMStructStructReadException("Couldn't read struct because previous parameter read was not a count of type int4.");
                }
            } else if (model instanceof VSMString) {
                VSMParm putativeStringLength = myNewContents.lastElement();
                // /* Debug */ System.err.println(" putativeStringLength == " + putativeStringLength);
                // /* Debug */ System.err.flush();
                if (putativeStringLength instanceof VSMInt4) {
                    member = new VSMString(null, model.getFormalName());
                    member.read(in, (VSMInt4.class.cast(putativeStringLength)).getValue());
                } else {
                    // /* Debug */ System.err.println(" About to throw VSMStructStringReadException -- myNewContents is: " + myNewContents);
                    // /* Debug */ System.err.flush();
                    throw new VSMStructStringReadException("Couldn't read string because previous parameter read was not a count of type int4.");
                }
            } else {
                member = model.copyOf();
                member.read(in, -1);
            }
            myNewContents.add(member);
        }
        // Convert 'this' into the new struct we have read in.
        setValue(myNewContents);
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
     */
    public class VSMStructStructReadException extends VSMException {

        /**
         *
         * @param message
         */
        public VSMStructStructReadException(String message) {
            super(message);
        }
    }

    /**
     *
     * @return
     */
    public VSMParm copyOf() {
        VSMStruct result = new VSMStruct();
        result.setFormalName(getFormalName());
        Iterator<VSMParm> it = iterator();
        while (it.hasNext()) {
            result.add(it.next().copyOf());
        }
        return result;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("VSMStruct " + super.toString());
        sb.append(" Formal Name == " + getFormalName() + " Formal Type == " + getFormalType());
        /*sb.append(" Struct members follow:\n");
        Iterator<VSMParm> i = iterator();
        while (i.hasNext()) {
        sb.append(i.next().toString());
        }*/
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
        sb.append(getFormalName() + "(" + getFormalType() + "):\n");
        Iterator<VSMParm> it = iterator();
        while (it.hasNext()) {
            sb.append("  " + it.next().prettyPrint() + "\n");
        }
        return sb.toString();
    }
}
