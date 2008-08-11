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
import java.io.IOException;
import java.util.Iterator;

/**
 * Used to read in those Arrays that consist of a
 * repeating count_of_struct_size + struct_itself pair.
 * @author jax
 */
public class CountedStruct extends VSMStruct {

    /**
     *
     */
    public static final String FORMAL_TYPE = "counted_struct";

    /**
     *
     * @param value
     * @param formalName
     */
    public CountedStruct(VSMStruct value, String formalName) {
        super(value, formalName);
    }

    /**
     *
     * @param value
     */
    public CountedStruct(VSMStruct value) {
        super(value);
    }

    /**
     *
     */
    public CountedStruct() {
    }

    /**
     *
     * @return
     */
    @Override
    public VSMParm copyOf() {
        CountedStruct cs = new CountedStruct();
        cs.setValue(this);
        cs.setFormalName(this.getFormalName());
        return cs;
    }

    /**
     *
     * @return
     */
    @Override
    public String getFormalType() {
        return FORMAL_TYPE;
    }

    /**
     * Assumes the CountedStruct is modelled correctly.
     * Unlike some of the struct reads which read into copies
     * of the model and then replace, CountedStruct reads
     * into its model parms because itself it is always
     * already a copy spawned from the VSMArray that
     * contained the model.
     * @param in
     * @param length
     * @throws java.io.IOException
     * @throws com.softwoehr.pigiron.access.VSMStruct.VSMStructStringReadException
     * @throws com.softwoehr.pigiron.access.VSMException
     */
    @Override
    public void read(DataInputStream in, int length) throws IOException, VSMStructStringReadException, VSMException {
        if (length >= ParameterArray.SIZEOF_INT4) {
            // /* Debug */ System.out.println(" **** About to read in  CountedStruct.read() ");
            // /* Debug */ System.out.println(" **** " + this);
            // /* Debug */ System.out.println(" Counted struct size is " + size());
            if (size() == 2) { // Must be modelled before a read, ergo, then has two (2) elements: a count and a struct
                VSMParm purportedCountParm = elementAt(0);
                if (purportedCountParm instanceof VSMInt4) {
                    purportedCountParm.read(in, 4); // read the count
                    length -= 4;
                } else {
                    throw new CountedStructStructReadException("First element in counted struct named " + getFormalName() + " of type " + getFormalType() +
                            " is not of type VMSInt4 so cannot be a count.");
                }
                int structLength = purportedCountParm.paramLength();
                if (structLength > length) {
                    throw new CountedStructStructReadException("CountedStruct named " + getFormalName() + " of type " + getFormalType() +
                            "had a count greater than the remaining read length.");
                }
                VSMStruct myStruct = VSMStruct.class.cast(elementAt(1)); // If it ain't  the right class it will throw here on its own!
                myStruct.read(in, length); // read the struct
            } else {
                throw new CountedStructStructReadException("CountedStruct named " + getFormalName() + " of type " + getFormalType() +
                        "is missing a second (struct) element ergo appears unmodelled.");
            }
        }
    }

    /**
     * This returns element 0 of the array which is always
     * (if instanced) the count of the struct which follows it.
     * @return
     */
    public int getCountInt() {
        return VSMInt4.class.cast(elementAt(0)).getValue();
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return super.toString();
    }

    /**
     *
     */
    public class CountedStructStructReadException extends VSMException {

        /**
         *
         * @param message
         */
        public CountedStructStructReadException(String message) {
            super(message);
        }
    }
}
