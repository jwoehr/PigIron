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
import java.io.IOException;
import java.util.Iterator;

/**
 * Implements and encapsulates the VSMAPI <tt>array</tt> basic type including
 * its count word
 *
 * @author jax
 * @see com.softwoehr.pigiron.access.VSMParm
 */
public class CountedArray extends VSMStruct implements VSMParm {

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
    static {
        FORMAL_TYPE = "array";
    }

    private Integer lengthCount;

    /**
     * Get length of the array
     *
     * @return The length of the string
     */
    public Integer getLengthCount() {
        return lengthCount;
    }

    /**
     * Set length of the array
     *
     * @param lengthCount length of the string
     */
    public void setLengthCount(Integer lengthCount) {
        this.lengthCount = lengthCount;
    }

    @Override
    public int paramLength() {
        int result = 0;
        if (getValue() != null) {
            result = super.paramLength() + ParameterArray.SIZEOF_INT4;
            /* add the length the count word */
        }
        return result;
    }

    /**
     * Create a VSMArray that has as its one (1) member a model of the parameter
     * it is intended to read iteratively.
     *
     * @param model The VSMParm (often CountedStruct) which will be read
     * iteratively
     * @param formalName the formal name
     * @return the new modeled VSMArray ready for a read.
     */
    static public CountedArray modelArray(VSMParm model, String formalName) {
        CountedArray result = new CountedArray();
        result.add(model);
        result.setFormalName(formalName);
        return result;
    }

    /**
     * Create an instance of undefined value.
     */
    public CountedArray() {
        super();
    }

    /**
     * Create an instance of specified value copied from a like instance.
     *
     * @param value a like instance to be copied from
     */
    public CountedArray(CountedArray value) {
        super(value);
        lengthCount = (super.paramLength());
    }

    /**
     * Create an instance of specified value copied from a like instance, and
     * specify the formal name at the same time.
     *
     * @param value a like instance to be copied from
     * @param formalName the formal name
     */
    public CountedArray(CountedArray value, String formalName) {
        this(value);
        setFormalName(formalName);

    }

    /**
     * The vector of the Array contains at runtime the model of ONE INSTANCE of
     * the contained type. VMSArray re-iterates through this instance until the
     * count is exhausted and creates a new vector of the items it reads
     * one-at-a-time which it then instantiates in itself.
     *
     * @param in the byte count remaining
     * @param length
     * @throws java.io.IOException
     */
    @Override
    public void read(DataInputStream in, int length) throws IOException, VSMException {
        // /* Debug */ System.err.println(" VSMArray.read has read length of  " + length);
        // /* Debug */ System.err.println("VSMArray about to read is: " + this);
        // /* Debug */ System.err.flush();
        if (length < ParameterArray.SIZEOF_INT4) {
            throw new VSMException("Remaining length shorter than size of CountedArray count word");
        }
        CountedArray v = new CountedArray();
        v.lengthCount = in.readInt();
        int remaining_array_length = v.lengthCount;
        length -= ParameterArray.SIZEOF_INT4;
        VSMParm model = get(0);
        // /* Debug */ System.err.println(" model Array parm is " + model);
        // /* Debug */ System.err.flush();
        // while (length > 0 && in.available() >= length) {
        while (remaining_array_length > 0 && length > 0) {
            VSMParm target = model.copyOf();
            // /* Debug */ System.err.println(" VSMArray.read about to read " + target);
            // /* Debug */ System.err.flush();
            target.read(in, length);
            v.add(target);
            // /* Debug */ System.err.println(" VSMArray.read after read has target param length of   " + target.paramLength());
            // /* Debug */ System.err.flush();
            length -= target.paramLength();
            remaining_array_length -= target.paramLength();
            // /* Debug */ System.err.println(" Array read length remaining: " + length);
            // /* Debug */ System.err.flush();
        }
        setValue(v);
    }

    /**
     * Return a deep copy of the instance.
     *
     * @return copy or null
     */
    @Override
    public VSMParm copyOf() {
        CountedArray bozo = new CountedArray();
        bozo.setFormalName(getFormalName());
        Iterator<VSMParm> it = iterator();
        while (it.hasNext()) {
            bozo.add(it.next().copyOf());
        }
        return bozo;
    }

    /**
     * String representation of the instance for debugging.
     *
     * @return String representation of the instance for debugging
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("VSMArray " + super.toString());
        sb.append(" Formal Name == ").append(getFormalName()).append(" Formal Type == ").append(getFormalType());
        /*sb.append(" Array members follow:\n");
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
     * A class to express internal Pigiron errors in assimilating a wrong type
     * of CountedStruct in the constructor.
     */
    public class VSMArrayCountedStructCTORException extends VSMException {

        /**
         * Create instance with a message.
         *
         * @param message the message
         */
        public VSMArrayCountedStructCTORException(String message) {
            super(message);
        }
    }

    /**
     *
     * @param argv
     */
    /*public static void main(String[] argv) {
    VSMArray toCopy = DeviceInfoArray.modelArray("Fred");
    System.out.println("Testing VSMArray.testCopyOf()");
    testCopyOf(toCopy);
    }*/
}
