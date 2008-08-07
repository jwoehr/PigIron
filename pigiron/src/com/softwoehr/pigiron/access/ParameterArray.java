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

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Enumeration;
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

//    /**
//     *
//     * @param index
//     * @return
//     */
//    public int paramLengthAt(int index) {
//        return elementAt(index).paramLength();
//    }
//
//    /**
//     *
//     * @param index
//     * @return
//     */
//    public Class paramClassAt(int index) {
//        return elementAt(index).getClass();
//    }
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
            if (p instanceof VSMString) {
                total += SIZEOF_INT4; // the count for a string type
            }
            total += p.paramLength();
        }
        return total;
    }

    /**
     *
     * @param out
     * @throws java.io.IOException
     */
    public void writeAll(DataOutputStream out) throws IOException {
        /* Write the overall message count-after-this int4 */
        /* ... is the count always of type int4 ? */
        new VSMInt4(new Long(totalParameterLength()).intValue()).write(out);
        /* Write all the params */
        for (Enumeration<VSMParm> e = elements(); e.hasMoreElements();) {
            e.nextElement().write(out);
        }
    }
}