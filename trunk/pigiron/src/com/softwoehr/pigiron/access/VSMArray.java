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

import com.softwoehr.pigiron.access.paramstructs.DeviceInfoArray;
import com.softwoehr.pigiron.access.paramstructs.DeviceInfoStructCounted;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Iterator;

/**
 * Implements and encapsulates the VSMAPI <tt>array</tt> basic type.
 *
 * @author jax
 * @see com.softwoehr.pigiron.access.VSMParm
 */
public class VSMArray extends VSMStruct implements VSMParm {

    /**
     * Type in terms of one of the formal parameter type discussed in
     * the VSMAPI documentation: <tt>int1</tt>, <tt>int4</tt>,
     * <tt>int8</tt>, <tt>string</tt>, <tt>struct</tt>, <tt>array</tt>.
     * (Pigiron also recognizes <tt>counted_struct</tt>
     * as an extra type above and beyond the base types enumerated
     * by the VSMAPI documentation.)
     */
    public static final String FORMAL_TYPE = "array";

    /**
     *
     * @param model
     * @param formalName
     * @return
     */
    static public VSMArray modelArray(VSMParm model, String formalName) {
        VSMArray result = new VSMArray();
        result.add(model);
        result.setFormalName(formalName);
        return result;
    }

    /**
     *
     */
    public VSMArray() {
        super();
    }

    /**
     *
     * @param value
     */
    public VSMArray(VSMArray value) {
        super(value);
    }

    /**
     *
     * @param value
     * @param formalName
     */
    public VSMArray(VSMArray value, String formalName) {
        super(value, formalName);
    }

    /**
     * The vector of the Array contains at runtime the model of
     * ONE INSTANCE of the contained type. VMSArray re-iterates
     * through this instance until the count is exhausted and
     * creates a new vector of the items it reads one-at-a-time
     * which it then instantiates in itself.
     * @param in the byte count remaining
     * @param length
     * @throws java.io.IOException
     */
    @Override
    public void read(DataInputStream in, int length) throws IOException, VSMException {
        // /* Debug */ System.out.println(" VSMArray.read has read length of  " + length);
        // /* Debug */ System.out.flush();
        VSMArray v = new VSMArray();
        VSMParm model = elementAt(0);
        // /* Debug */ System.err.println(" model Array parm is " + model);
        // /* Debug */ System.err.flush();
        while (length > 0) {
            VSMParm target = model.copyOf();
            // /* Debug */ System.out.println(" VSMArray.read about to read " + target);
            // /* Debug */ System.out.flush();
            target.read(in, length);
            v.add(target);
            // /* Debug */ System.out.println(" VSMArray.read after read has target param length of   " + target.paramLength());
            // /* Debug */ System.out.flush();
            length -= target.paramLength();
        // /* Debug */ System.err.println(" Array read length remaining: " + length);
        // /* Debug */ System.out.flush();
        }
        setValue(v);
    }

    /**
     *
     * @return
     */
    /*@Override
    public VSMParm copyOf() {
    VSMArray result = new VSMArray();
    result.setFormalName(getFormalName());
    Iterator<VSMParm> it = iterator();
    while (it.hasNext()) {
    result.add(it.next().copyOf());
    }
    return result;
    }*/
    /**
     *
     * @return
     */
    @Override
    public VSMParm copyOf() {
        return VSMParm.class.cast(clone());
    }

    /**
     *
     * @return
     */
    @Override
    public Object clone() {
        VSMArray proto = new VSMArray();
        proto.setFormalName(getFormalName());
        Iterator<VSMParm> it = iterator();
        while (it.hasNext()) {
            proto.add(it.next().copyOf());
        }
        return proto;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("VSMArray " + super.toString());
        sb.append(" Formal Name == " + getFormalName() + " Formal Type == " + getFormalType());
        /*sb.append(" Array members follow:\n");
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
    @Override
    public String getFormalType() {
        return FORMAL_TYPE;
    }

    /**
     *
     * @param toCopy
     * @return
     */
    public static boolean testCopyOf(VSMArray toCopy) {
        boolean result = false;
        VSMArray theCopy = VSMArray.class.cast(toCopy.copyOf());
        System.out.println("theCopy: " + theCopy);
        System.out.println("theCopy: " + theCopy.prettyPrint());
        System.out.println("toCopy: " + toCopy);
        System.out.println("toCopy: " + toCopy.prettyPrint());
        System.out.println("theCopy == toCopy: " + (theCopy == toCopy));
        System.out.println("theCopy.equals(toCopy): " + (theCopy.equals(toCopy)));
        System.out.println("Changing stuff now in toCopy");
        DeviceInfoStructCounted.class.cast(toCopy.lastElement()).setValue(new DeviceInfoStructCounted());
        System.out.println("theCopy: " + theCopy);
        System.out.println("theCopy: " + theCopy.prettyPrint());
        System.out.println("toCopy: " + toCopy);
        System.out.println("toCopy: " + toCopy.prettyPrint());
        System.out.println("theCopy == toCopy: " + (theCopy == toCopy));
        System.out.println("theCopy.equals(toCopy): " + (theCopy.equals(toCopy)));
        return result;
    }

    /**
     *
     * @param argv
     */
    public static void main(String[] argv) {
        VSMArray toCopy = DeviceInfoArray.modelArray("Fred");
        System.out.println("Testing VSMArray.testCopyOf()");
        testCopyOf(toCopy);
    }
}