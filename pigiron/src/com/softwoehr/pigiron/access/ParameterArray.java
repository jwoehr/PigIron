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
import java.util.ListIterator;
import java.util.Vector;

/**
 *
 * @author jax
 */
public class ParameterArray extends Vector<VSMParm> {

    public final static int SIZEOF_INT1 = 1;
    public final static int SIZEOF_INT4 = 4;
    public final static int SIZEOF_INT8 = 8;

    // private Vector<VSMParm> params;
    /**
     * 
     */
    public ParameterArray() {
        super();
    }

    /**
     *
     * @param name
     * @return
     */
    public VSMParm parameterNamed(String name) {
        VSMParm result = null;
        for (Enumeration<VSMParm> e = elements(); e.hasMoreElements();) {
            VSMParm v = e.nextElement();
            if (v.getFormalName().equals(name)) {
                result = v;
                break;
            }
        }
        return result;
    }

    /**
     * 
     * @param index
     * @param out
     * @throws java.io.IOException
     */
    public void writeParameterAt(int index, DataOutputStream out) throws IOException {
        VSMParm p = elementAt(index);
        p.write(out);
    }

    /**
     *
     * @return
     */
    public long totalParameterLength() {
        long total = 0;
        for (Enumeration<VSMParm> e = elements(); e.hasMoreElements();) {
            VSMParm p = e.nextElement();
            if (p instanceof VSMString | p instanceof VSMStruct | p instanceof VSMArray) {
                total += SIZEOF_INT4; // the count for a string, struct or array type
            }
            total += p.paramLength();
        }
        return total;
    }

    /**
     *
     * @param out
     * @throws java.io.IOException
     * @see #readAll
     * @see <a href="http://publib.boulder.ibm.com/infocenter/zvm/v5r3/topic/com.ibm.zvm.v53.dmse6/hcsl8b20.htm" target="top">z/VM V5R3.0 Systems Management Application Programming SC24-6122-03</a>
     */
    public void writeAll(DataOutputStream out) throws IOException {
        /* Write the overall message count-after-this int4 */
        /* ... is the count always of type int4 ?     */
        /* Apparently so, see "Data Types" in         */
        /* z/VM V5R3.0 Systems Management Application */
        /* Programming SC24-6122-03                   */
        new VSMInt4(new Long(totalParameterLength()).intValue()).write(out);
        /* Write all the params */
        for (Enumeration<VSMParm> e = elements(); e.hasMoreElements();) {
            e.nextElement().write(out);
        }
    }

    /**
     * Read in the overall message that is modeled by
     * the compose() method of query/call classes.
     * @param in
     * @throws java.io.IOException
     * @throws VSMException
     * @see #writeAll
     * @see <a href="http://publib.boulder.ibm.com/infocenter/zvm/v5r3/topic/com.ibm.zvm.v53.dmse6/hcsl8b20.htm" target="top">z/VM V5R3.0 Systems Management Application Programming SC24-6122-03</a>
     */
    public void readAll(DataInputStream in) throws IOException, VSMException {
        /* Write all the params */
        ListIterator<VSMParm> list = listIterator();
        VSMParm previous = null;
        while (list.hasNext()) {
            VSMParm current = list.next();
            /* Debug */ System.err.println("next list item in ParameterArray.readAll is " + current);
            if (current instanceof VSMInt) {
                /* Debug */ System.err.println("reading a VSMInt ");
                current.read(in, -1);
                /* Debug */ System.err.println("Value of read VSMInt " + current.getFormalName() + " == " + VSMInt.class.cast(current).getLongValue());
            } else if (current instanceof VSMString | current instanceof VSMArray | current instanceof VSMStruct) {
                if (list.hasPrevious()) {
                    previous = list.previous();
                    list.next(); // reinstate cursor before we forget to do so!
                    if (previous instanceof VSMInt4) {
                        int countLength = VSMInt4.class.cast(previous).getValue();
                        current.read(in, countLength);
                    } else {
                        // The previous parm isn't the required count for the current counted parmtype
                        throw new ParameterArrayReadAllException("Previous parm was not a count for the current counted parmtype. " + current);
                    }
                } else {
                    // There's no count for the counted parmtype
                    throw new ParameterArrayReadAllException("There is no count for the current counted parmtype. " + current);
                }
            }
        }
    }

    /**
     *
     */
    public class ParameterArrayReadAllException extends VSMException {

        /**
         *
         * @param message
         */
        public ParameterArrayReadAllException(String message) {
            super(message);
        }
    }
}