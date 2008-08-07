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

/**
 * 
 * @author jax
 */
public class VSMArray extends VSMStruct implements VSMParm {

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
     *
     * @param out
     * @throws java.io.IOException
     */
    @Override
    public void write(DataOutputStream out) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * The vector of the Array contains at runtime the model of
     * ONE INSTANCE of the contained type. VMSArray re-iterates
     * through this instance until the count is exhausted and
     * creates a new vector of the items it reads one-at-a-time
     * which it then instantiates in itself.
     * @param in
     * @param length
     * @throws java.io.IOException
     * @throws VSMStructStringReadException
     */
    @Override
    public void read(DataInputStream in, int length) throws IOException, VSMException {
        VSMArray v = new VSMArray();
        VSMParm model = elementAt(0);
        while (length > 0) {
            VSMParm target = model.copyOf();
            target.read(in, length);
            v.add(target);
            length -= target.paramLength();
        }
        setValue(v);
    }

    /**
     *
     * @return
     */
    @Override
    public VSMParm copyOf() {
        return new VSMArray(this, getFormalName());
    }
}
