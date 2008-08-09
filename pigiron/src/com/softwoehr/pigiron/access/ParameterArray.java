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
 *         notice, this currentListIterator of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *         notice, this currentListIterator of conditions and the following disclaimer
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
import java.util.Iterator;
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
     * Replace the contents of this ParameterArray with the contents of another.
     * @param value the container of the replacement data
     */
    public void setValue(ParameterArray value) {
        clear();
        if (value != null) { // null is legal value
            addAll(value);   // means "just clear me"
        }
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
        Iterator<VSMParm> thisIterator = iterator();
        while (thisIterator.hasNext()) {
            total += thisIterator.next().paramLength();
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
        /* Write all the params */
        Iterator<VSMParm> myIterator = iterator();
        while (myIterator.hasNext()) {
            myIterator.next().write(out);
        }
        out.flush();
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
        // /* Debug */ int howmanytimes = 0;
        int output_length = -1; // -1 means we haven't instanced it yet.
        ListIterator<VSMParm> currentListIterator = listIterator();
        ParameterArray replacement = new ParameterArray();
        VSMParm previous = null;
        while (currentListIterator.hasNext()) {
            VSMParm copyOfCurrentParm = currentListIterator.next().copyOf();
            // /* Debug */ System.err.println("next list item in ParameterArray.readAll is " + copyOfCurrentParm);
            if (copyOfCurrentParm instanceof VSMInt) {
                // /* Debug */ System.err.println("reading a VSMInt ");
                copyOfCurrentParm.read(in, -1);
                if (replacement.size() == 1) { // If this is the second thing we read
                    // Then this should be the output length param
                    output_length = VSMInt4.class.cast(copyOfCurrentParm).getValue();
                }
            //  /* Debug */ System.err.println("Value of read VSMInt " + copyOfCurrentParm.getFormalName() + " == " + VSMInt.class.cast(copyOfCurrentParm).getLongValue());
            } else if (copyOfCurrentParm instanceof VSMString | copyOfCurrentParm instanceof VSMArray | copyOfCurrentParm instanceof VSMStruct) {
                if (!replacement.isEmpty()) {
                    previous = replacement.lastElement();
                    //  /* Debug */ System.err.println("previous param is " + previous);
                    if (previous instanceof VSMInt4) {
                        int countLength = VSMInt4.class.cast(previous).getValue();
                        copyOfCurrentParm.read(in, countLength);
                    } else {
                        // The previous parm isn't the required count for the copyOfCurrentParm counted parmtype
                        throw new ParameterArrayReadAllException("Previous parm was not a count for the current counted parmtype. " + copyOfCurrentParm);
                    }
                } else {
                    // There's no count for the counted parmtype
                    throw new ParameterArrayReadAllException("There is no count for the current counted parmtype. " + copyOfCurrentParm);
                }
            }
            replacement.add(copyOfCurrentParm);
            // System.err.flush();
            // System.err.println("how many times? " + howmanytimes++);

            /* Check that we don't read past end when we get error documents */
            /* "- 2 * SIZEOF_INT4" because output_length doesn't count the */
            /* immediate reply and the output_length itself */
            if (output_length != -1 & output_length <= replacement.totalParameterLength() - 2 * SIZEOF_INT4) {
                break;
            }
        }
        setValue(replacement);
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