/*
 * Copyright (c) 2015, Jack J. Woehr
 * jax@well.com jwoehr@softwoehr.com PO Box 51, Golden CO 80402-0051 USA
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
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Implements and encapsulates the VSMAPI <tt>struct</tt> basic type.
 *
 * @author jax
 * @see com.softwoehr.pigiron.access.VSMParm
 */
public class VSMStruct extends ArrayList<VSMParm> implements VSMParm {

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
    public static String FORMAL_TYPE = "struct";
    /**
     * let the un-init'ed be null!
     */
    private String formalName;

    /**
     * Ctor with no values
     */
    public VSMStruct() {
    }

    /**
     *
     * @return
     */
    public VSMParm lastElement() {
        return get(size() - 1);
    }

    /**
     * Create an instance of specified value
     *
     * @param value the value (another like instance to be copied from)
     */
    public VSMStruct(VSMStruct value) {
        setValue(value);
    }

    /**
     * Create an instance of specified value and assign it a formal name.
     *
     * @param value the value (another like instance to be copied from)
     * @param formalName the formal name
     */
    public VSMStruct(VSMStruct value, String formalName) {
        this(value);
        this.formalName = formalName;
    }

    /**
     * Get the value i.e., a Vector of struct members, i.e., <tt>this</tt>.
     *
     * @return the value
     */
    public VSMStruct getValue() {
        return this;
    }

    /**
     *
     * Set instance to specified value.
     *
     * Should only be used when appropriating the value(s) from a temporary
     * instance used for this purpose since it does not deep copy.
     *
     * {@code null} is legal value which means "just clear me".
     *
     * @param value the value
     */
    public final void setValue(VSMStruct value) {
        clear();
        if (value != null) { // but null is legal value means "just clear me"
            addAll(value);  // is this right? or should we do deep copy?
        }
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
     * Get the length in bytes of the parameter arrived at by recursively
     * calling interface method <tt>paramLength()</tt> on members.
     *
     * @return the length in bytes of the parameter value.
     */
    @Override
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

    /**
     * Write the struct parameter to the output stream. Its length <tt>int4</tt>
     * must already have been written by the enclosing <tt>ParameterArray</tt>
     * having had a VSMInt4 entry with the length indexed one (1) prior to this
     * VSMStruct.
     *
     * @param out the output stream
     * @throws java.io.IOException on comm error
     * @see com.softwoehr.pigiron.access.ParameterArray
     */
    @Override
    public void write(DataOutputStream out) throws IOException {
        Iterator<VSMParm> i = iterator();
        while (i.hasNext()) {
            i.next().write(out);
        }
    }

    /**
     * The vector of the struct contains at runtime the model of what it's going
     * to need to read. VMSStruct iterates through its vector as a strategy and
     * creates a new vector of the items it reads one-at-a-time. It then
     * instantiates the new vector in itself.
     *
     * @param in
     * @param length
     * @throws java.io.IOException
     * @throws VSMStructStringReadException
     */
    @Override
    public void read(DataInputStream in, int length) throws IOException, VSMStructStringReadException, VSMException {
        VSMStruct myNewContents = new VSMStruct(null);
        Iterator<VSMParm> i = iterator(); // Walk through our output model
        // while (i.hasNext() && length > 0 && in.available() >= length) {
        while (i.hasNext() && length > 0) { // we should check at each read instead
            VSMParm model = i.next();
            VSMParm member = model.copyOf();
            member.read(in, length);
            // /* Debug */ System.err.print("in VSMStruct.read() ... ");
            // /* Debug */ member.prettyPrint();
            myNewContents.add(member);
            length -= member.paramLength();
        }
        // Convert 'this' into the new struct we have read in.
        setValue(myNewContents);
    }

    /**
     * A class to express internal Pigiron errors in VSMString read marshalling.
     */
    public class VSMStructStringReadException extends VSMException {

        /**
         * Create instance with a message.
         *
         * @param message the message
         */
        public VSMStructStringReadException(String message) {
            super(message);
        }
    }

    /**
     * A class to express internal Pigiron errors in VSMStruct read marshalling.
     */
    public class VSMStructStructReadException extends VSMException {

        /**
         * Create instance with a message.
         *
         * @param message the message
         */
        public VSMStructStructReadException(String message) {
            super(message);
        }
    }

    /**
     * Return a deep copy of the instance.
     *
     * @return copy or null
     */
    @Override
    public VSMParm copyOf() {
        VSMStruct proto = new VSMStruct();
        proto.setFormalName(getFormalName());
        Iterator<VSMParm> it = iterator();
        while (it.hasNext()) {
            proto.add(it.next().copyOf());
        }
        return proto;
    }

    /**
     * String representation of the instance for debugging.
     *
     * @return String representation of the instance for debugging
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("VSMStruct " + super.toString());
        sb.append(" Formal Name == ").append(getFormalName()).append(" Formal Type == ").append(getFormalType());
        /*sb.append(" Struct members follow:\n");
        Iterator<VSMParm> i = iterator();
        while (i.hasNext()) {
        sb.append(i.next().toString());
        }*/
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
        sb.append(getFormalName()).append("(").append(getFormalType()).append("):\n");
        Iterator<VSMParm> it = iterator();
        while (it.hasNext()) {
            sb.append("  ").append(it.next().prettyPrint()).append("\n");
        }
        return sb.toString();
    }

    /**
     *
     * @param argv
     */
    /*public static void main(String[] argv) {
    VSMStruct toCopy = new VSMStruct(null, "test_struct");
    VSMString tempString = new VSMString("I am very silly ", "target_identifier");
    toCopy.add(new VSMInt4(tempString.paramLength(), "target_identifier_length"));
    toCopy.add(tempString);
    toCopy.add(new VSMInt1(42, "life_the_universe_and_everything"));
    System.out.println("Testing VSMStruct.testCopyOf()");
    testCopyOf(toCopy);
    }*/
}
