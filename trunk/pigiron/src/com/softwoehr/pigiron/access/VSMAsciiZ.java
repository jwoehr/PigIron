/*
 * Copyright (c) 2008, 2015 Jack J. Woehr jwoehr@softwoehr.com
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
public class VSMAsciiZ implements VSMParm {

    /**
     *
     */
    /**
     * The new type of String introduced in VSMAPI 5.4, a String which is
     * null-terminated (ASCIIZ) so does not need a count. It is both an input
     * and output parameter employed in calls such as
     * Virtual_Network_LAN_Access_Query (5.4).
     *
     * @see com.softwoehr.pigiron.access.VSMParm
     * @see com.softwoehr.pigiron.access.CountedStruct
     * @since
     * <a href="http://publib.boulder.ibm.com/infocenter/zvm/v5r4/index.jsp">VSMAPI
     * 5.4</a>
     */
    public static final String FORMAL_TYPE = "asciiz";
    private String value;
    private String formalName;

    /**
     * Create an instance of undefined value.
     */
    public VSMAsciiZ() {
    }

    /**
     * Create an instance of specified value.
     *
     * @param value the value
     */
    public VSMAsciiZ(String value) {
        this();
        this.value = value;
    }

    /**
     * Create an instance of specified value and assign it a formal name.
     *
     * @param value the value
     * @param formalName the formal name
     */
    public VSMAsciiZ(String value, String formalName) {
        this(value);
        this.formalName = formalName;
    }

    /**
     * Strips a string of any null terminators and anything following such.
     *
     * @param string a string possibly terminated with a null
     * @return the string minus the null terminator (and minus any characters
     * accidentally supplied after the null terminator)
     */
    public static String stripTerminated(String string) {
        if (string != null) {
            // /* Debug */ System.out.println("String entering stripTerminated is " + string);
            if (string.indexOf('\0') > 0) {
                string = string.substring(0, string.indexOf('\0'));
            }
            // /* Debug */ System.out.println("String leaving stripTerminated is " + string);
        }
        return string;
    }

    public static String terminate(String string) {
        if (string != null) {
            string = string + '\0';
        }
        return string;
    }

    /**
     * Get the value minus the z-terminator.
     *
     * @return the value minus the z-terminator
     */
    public String getValue() {
        return stripTerminated(value);
    }

    /**
     * Strip any accidentally-supplied null terminator from the input argument
     * and any characters following that null terminator, append a null
     * terminator, and set the value.
     *
     * @param value a string to be null-terminated by {@code setValue} and set
     * as the value
     */
    public final void setValue(String value) {
        // /* Debug */ System.out.println("String entering setValue is " + value);
        this.value = terminate(stripTerminated(value));
        // /* Debug */ System.out.println("String leaving setValue is " + value);
    }

    /**
     * Get the length in bytes of the parameter (including the null terminator).
     *
     * @return the length in bytes of the parameter value (including the null
     * terminator)
     */
    public int paramLength() {
        int result = 0;
        if (value != null) {
            result = value.length();
        }
        return result;
    }

    /**
     * Read in a VSMAsciiZ from a stream.
     *
     * @param in the input stream
     * @param length the max length of the read
     * @throws java.io.IOException on comm error
     */
    public void read(DataInputStream in, int length) throws IOException {
        // if (in.available() >= length) {
        StringBuilder result = new StringBuilder();
        char c;
        for (int i = 0; i < length; i++) {
            c = in.readChar();
            if (c == '\0') {
                break;
            }
            result.append(c);
        }
        setValue(result.toString());
        // /* Debug */ System.err.println("Read a string: " + value);
        // }
    }

    /**
     * Write the String parameter to the output stream including the null
     * terminator.
     *
     * @param out the output stream
     * @throws java.io.IOException on comm error
     * @see com.softwoehr.pigiron.access.ParameterArray
     */
    public void write(DataOutputStream out)
            throws java.io.IOException {
        out.write(value.getBytes());
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
     * Return a functional copy of the instance.
     *
     * @return copy or null
     */
    public VSMParm copyOf() {
        return new VSMAsciiZ(getFormalName(), getValue());
    }

//    /**
//     * Clone the instance.
//     *
//     * @return clone of the instance
//     * @see #copyOf()
//     */
//    @Override
//    public Object clone() {
//        VSMAsciiZ proto = new VSMAsciiZ();
//        proto.setFormalName(formalName);
//        proto.setValue(getValue());
//        return proto;
//    }
    /**
     * String representation of the instance for debugging.
     *
     * @return String representation of the instance for debugging
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append(" Formal Name == ").append(getFormalName()).append(" Formal Type == ").append(getFormalType());
        sb.append(" Value == ").append(getValue());
        return sb.toString();
    }

    /**
     * Get the formal type of the parmeter, one of the formal parameter types
     * discussed in the VSMAPI documentation: <tt>int1</tt>, <tt>int4</tt>,
     * <tt>int8</tt>, <tt>string</tt>, <tt>struct</tt>, <tt>array</tt> .. and
     * this new type we're calling AsciiZ that is in VSMAPI 5.4 but not at this
     * writing fully documented.
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
        sb.append(getFormalName()).append("(").append(getFormalType()).append(") ").append(getValue());
        return sb.toString();
    }
}
