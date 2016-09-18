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

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Implements and encapsulates the VSMAPI struct of strings separated by blanks
 * e.g., link_structure for Image_MDISK_Link_Query discussed in the VSMAPI
 * documentation. We don't store the blanks, we just write them and use them on
 * a read.
 *
 * @author jax
 * @see com.softwoehr.pigiron.access.VSMParm
 */
public class StringBlankSeparatedNullTerminatedStruct extends ArrayList<VSMString> implements VSMParm {

    /**
     * Type like link_structure for Image_MDISK_Link_Query discussed in the
     * VSMAPI documentation. Strings are separated by blanks.
     *
     * @see com.softwoehr.pigiron.access.VSMParm
     * @see com.softwoehr.pigiron.access.CountedStruct
     */
    public static String FORMAL_TYPE = "string_blank_separated_struct";
    /**
     * let the un-init'ed be null!
     */
    private String formalName;

    /**
     * Ctor with no values
     */
    public StringBlankSeparatedNullTerminatedStruct() {
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
    public StringBlankSeparatedNullTerminatedStruct(StringBlankSeparatedNullTerminatedStruct value) {
        setValue(value);
        if (value != null) {
            this.formalName = value.getFormalName();
        }
    }

    /**
     * Create an instance of specified value and assign it a formal name.
     *
     * @param value the value (another like instance to be copied from)
     * @param formalName the formal name
     */
    public StringBlankSeparatedNullTerminatedStruct(StringBlankSeparatedNullTerminatedStruct value, String formalName) {
        this(value);
        this.formalName = formalName;
    }

    /**
     * Get the value i.e., a Vector of struct members, i.e., <tt>this</tt>.
     *
     * @return the value
     */
    public StringBlankSeparatedNullTerminatedStruct getValue() {
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
    public final void setValue(StringBlankSeparatedNullTerminatedStruct value) {
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
    public String getFormalName() {
        return formalName;
    }

    /**
     * Get the length in bytes of the parameter arrived at by recursively
     * calling interface method <tt>paramLength()</tt> on members.
     *
     * @return the length in bytes of the parameter value.
     */
    public int paramLength() {
        int total = 0;
        Iterator<VSMString> i = iterator();
        // /* Debug */ System.err.println("((( In VSMSTruct.paramLength() we're examining " + this);
        while (i.hasNext()) {
            VSMParm parm = i.next();
            total += parm.paramLength();
            total++; // for the blank separator between strings
            // then finally for the null
        }
        return total;
    }

    /**
     * Write the struct parameter to the output stream. Write a blank between
     * each string. Write a null at the end.
     *
     * @param out the output stream
     * @throws java.io.IOException on comm error
     * @see com.softwoehr.pigiron.access.ParameterArray
     */
    public void write(DataOutputStream out) throws IOException {
        Iterator<VSMString> i = iterator();
        while (i.hasNext()) {
            i.next().write(out);
            if (i.hasNext()) { // if it's not the last write the blank separator
                out.writeByte(' ');
            }
        }
        out.writeByte(0);
    }

    public byte[] readInUntilNullOrLength(DataInputStream in, int length) throws IOException {
        ArrayList<Byte> bytes = new ArrayList<Byte>();
        // /* debug */ System.err.println("readInUntilNullOrLength in.available() == " + in.available());
        while (length-- > 0 && in.available() > 0) {
            Byte b = in.readByte();
            bytes.add(b); // add even the null
            if (b == 0) { // quit on null
                break;
            }
        }
        byte[] result = new byte[bytes.size()];
        for (int i = 0; i < bytes.size(); i++) {
            result[i] = bytes.get(i);
        }
        // /* debug */ System.err.println("readInUntilNullOrLength :: " + Arrays.toString(result));
        return result;
    }

    public String readStringUntilBlankNullOrExhaustedOrLength(DataInputStream in, int length) throws IOException {
        ArrayList<Byte> bytes = new ArrayList<Byte>();
        byte b;
        // /* debug */ System.err.println("readStringUntilBlankNullOrExhaustedOrLength in.available() == " + in.available());
        while (length-- > 0 && in.available() > 0) {
            b = in.readByte();
            if (b == 0x20 || b == 0) {
                break;
            }
            bytes.add(b);
        }
        byte[] result = new byte[bytes.size()];
        for (int i = 0; i < bytes.size(); i++) {
            result[i] = bytes.get(i);
        }
        // /* debug */ System.err.println("readStringUntilBlankNullOrExhaustedOrLength :: " + bytes.toString());
        return new String(result);
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
     */
    public void read(DataInputStream in, int length) throws IOException {
        StringBlankSeparatedNullTerminatedStruct myNewContents = new StringBlankSeparatedNullTerminatedStruct(null);
        ByteArrayInputStream bis = new ByteArrayInputStream(readInUntilNullOrLength(in, length));
        DataInputStream dis = new DataInputStream(bis);
        Iterator<VSMString> i = iterator(); // Walk through our output model
        VSMString nextVSMString;
        while (i.hasNext() && length > 0 && bis.available() > 0) {
            VSMString model = i.next();
            nextVSMString = new VSMString(model);
            nextVSMString.setValue(readStringUntilBlankNullOrExhaustedOrLength(dis, length));
            length -= nextVSMString.paramLength();
            if (i.hasNext()) { // if this wasn't the last member
                length--; // then we have to account for the blank separator
            }
            myNewContents.add(nextVSMString);
        }
        // Convert 'this' into the new struct we have read in.
        setValue(myNewContents);
    }

    /**
     * Return a deep copy of the instance.
     *
     * @return copy or null
     */
    public VSMParm copyOf() {
        StringBlankSeparatedNullTerminatedStruct proto = new StringBlankSeparatedNullTerminatedStruct(this);
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
     * Get the formal type of the parameter, one of the formal parameter types
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
        sb.append(getFormalName()).append("(").append(getFormalType()).append("):\n");
        Iterator<VSMString> it = iterator();
        while (it.hasNext()) {
            sb.append("  ").append(it.next().prettyPrint()).append("\n");
        }
        return sb.toString();
    }
}
