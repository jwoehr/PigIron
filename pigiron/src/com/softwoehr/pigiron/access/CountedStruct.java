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
 * Implements and encapsulates an "extra" base type <tt>counted_struct</tt>
 * which Pigiron recognizes alongside the VSMAPI documented base types.
 *
 * <p>
 * Used to read in Array elements which consist of a repeating
 * count_of_struct_size + struct_itself pair, this class is actually an "extra"
 * base type <tt>counted_struct</tt>
 * which Pigiron recognizes alongside the VSMAPI documented base types:
 * <tt>int1</tt>, <tt>int4</tt>,
 * <tt>int8</tt>, <tt>string</tt>, <tt>struct</tt>, <tt>array</tt>.</p>
 *
 * <p>
 * Pigiron differentiates between a <tt>VSMStruct</tt> consisting of:</p>
 * <ol>
 * <li>a <tt>VSMInt4</tt> count followed by a <tt>VSMStruct</tt></li>
 * <li>an arbitrary collection of type entries</li>
 * </ol>
 * <p>
 * Type (1), a <tt>VSMInt4</tt> count followed by a <tt>VSMStruct</tt></li>
 * is the case represented by <tt>CountedStruct</tt>
 * </p>
 *
 * @author jax
 * @see com.softwoehr.pigiron.access.VSMParm
 */
public class CountedStruct extends VSMStruct {

    /**
     * The formal type name. Pigiron recognizes <tt>counted_struct</tt>
     * as an extra type above and beyond the base types enumerated by the VSMAPI
     * documentation.
     */
    static {
        FORMAL_TYPE = "counted_struct";
    }

    /**
     * Create an instance assigning its value from another instance. and
     * assigning the new instance a formal name.
     *
     * @param value the instance to copy value from
     * @param formalName the formal name giving significance to the instance as
     * a parameter of a VSMAPI function
     */
    public CountedStruct(CountedStruct value, String formalName) {
        super(value, formalName);
    }

    /**
     * Create an instance assigning its value from another instance.
     *
     * @param value the instance to copy value from
     */
    public CountedStruct(CountedStruct value) {
        super(value);
    }

    /**
     * Create an instance with an empty value and no formal name.
     */
    public CountedStruct() {
    }

    /**
     * Return a correctly typed copy of the present instance.
     *
     * @return correctly typed copy of the present instance
     */
    @Override
    public VSMParm copyOf() {
        return VSMParm.class.cast(clone());
    }

    /**
     * Return a deep copy of the present instances.
     *
     * @return deep copy of the present instances
     */
    @Override
    public Object clone() {
        CountedStruct proto = new CountedStruct();
        proto.setFormalName(getFormalName());
        Iterator<VSMParm> it = iterator();
        while (it.hasNext()) {
            proto.add(it.next().copyOf());
        }
        return proto;
    }

    /**
     * Get the formal type of the parameter conforming to the VSMAPI docs.
     *
     * @return the formal type of the parameter
     * @see com.softwoehr.pigiron.access.VSMParm
     */
    @Override
    public String getFormalType() {
        return FORMAL_TYPE;
    }

    /**
     * Assumes the CountedStruct is modelled correctly. Unlike some of the
     * struct reads which read into copies of the model and then replace,
     * CountedStruct reads into its model parms because itself it is always
     * already a copy spawned from the VSMArray that contained the model.
     *
     * @param in
     * @param length
     * @throws java.io.IOException
     * @throws
     * com.softwoehr.pigiron.access.VSMStruct.VSMStructStringReadException
     * @throws com.softwoehr.pigiron.access.VSMException
     */
    @Override
    public void read(DataInputStream in, int length) throws IOException, VSMStructStringReadException, VSMException {
        if (length >= ParameterArray.SIZEOF_INT4) {
             /* Debug */ System.err.println(" **** About to read in  CountedStruct.read() ");
             /* Debug */ System.err.println(" **** " + this);
             /* Debug */ System.err.println(" Counted struct size is " + size());
            if (size() == 2) { // Must be modelled before a read, ergo, then has two (2) elements: a count and a struct
                VSMParm purportedCountParm = get(0);
                if (purportedCountParm instanceof VSMInt4) {
                    purportedCountParm.read(in, 4); // read the count
                    // /* Debug */ System.err.println("purportedCountParm value is " + VSMInt4.class.cast(purportedCountParm).getValue());
                    length -= 4;
                } else {
                    throw new CountedStructStructReadException("First element in counted struct named " + getFormalName() + " of type " + getFormalType()
                            + " is not of type VMSInt4 so cannot be a count.");
                }
                int structLength = purportedCountParm.paramLength();
                if (structLength > length) {
                    throw new CountedStructStructReadException("CountedStruct named " + getFormalName() + " of type " + getFormalType()
                            + "had a count greater than the remaining read length.");
                }
                VSMStruct myStruct = VSMStruct.class.cast(get(1)); // If it ain't  the right class it will throw here on its own!
                /* Debug */ System.err.println("class-cast item is " + myStruct);
                myStruct.read(in, length); // read the struct
                /* Debug */
                System.err.println("Done reading in " + myStruct);
            } else {
                throw new CountedStructStructReadException("CountedStruct named " + getFormalName() + " of type " + getFormalType()
                        + "is missing a second (struct) element ergo appears unmodelled.");
            }
        }
    }

    /**
     * This returns element 0 of the array which is always (if instanced) the
     * count of the struct which follows it.
     *
     * @return the count of the struct which follows
     */
    public int getCountInt() {
        return VSMInt4.class.cast(get(0)).getValue();
    }

    /**
     * String representation of the instance for debugging.
     *
     * @return String representation of the instance for debugging
     */
    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * Exception thrown when read errors occur internally in Pigiron's parameter
     * marshalling.
     */
    public class CountedStructStructReadException extends VSMException {

        /**
         * Instance assigned a message.
         *
         * @param message the message
         */
        public CountedStructStructReadException(String message) {
            super(message);
        }
    }
    /**
     *
     * @param toCopy
     * @return
     */
    /*public static boolean testCopyOf(CountedStruct toCopy) {
    boolean result = false;
    CountedStruct theCopy = CountedStruct.class.cast(toCopy.copyOf());
    System.out.println("theCopy: " + theCopy);
    System.out.println("theCopy: " + theCopy.prettyPrint());
    System.out.println("toCopy: " + toCopy);
    System.out.println("toCopy: " + toCopy.prettyPrint());
    System.out.println("theCopy == toCopy: " + (theCopy == toCopy));
    System.out.println("theCopy.equals(toCopy): " + (theCopy.equals(toCopy)));
    return result;
    }*/
    /**
     *
     * @param argv
     */
    /*public static void main(String[] argv) {
    CountedStruct toCopy = new CountedStruct(null, "test_struct");
    VSMString tempString = new VSMString("I am very silly ", "target_identifier");
    toCopy.add(new VSMInt4(tempString.paramLength(), "target_identifier_length"));
    toCopy.add(tempString);
    toCopy.add(new VSMInt1(42, "life_the_universe_and_everything"));
    System.out.println("Testing CountedStruct.testCopyOf()");
    testCopyOf(toCopy);
    }*/
}
