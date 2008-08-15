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
package com.softwoehr.pigiron.access.paramstructs;

import com.softwoehr.pigiron.access.VSMArray;
// import com.softwoehr.pigiron.access.VSMArray.VSMStructStringReadException;
import com.softwoehr.pigiron.access.VSMException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * A repeating array of <tt>DeviceInfoStructCounted</tt>
 * instances.
 * @see com.softwoehr.pigiron.access.paramstructs.DeviceInfoStructCounted
 * @author jax
 */
public class DeviceInfoArray extends VSMArray {

    /**
     * Create a modelled-for-read instance with a specified formal name.
     * @param formalName the formal name
     * @return the modelled instance.
     */
    public static DeviceInfoArray modelArray(String formalName) {
        DeviceInfoArray result = new DeviceInfoArray();
        result.add(new DeviceInfoStructCounted(null, "device_info_struct_counted"));
        result.setFormalName(formalName);
        return result;
    }

    /**
     * Create an instance by copying the value from a like instance, and
     * assign also the formal name.
     * @param value a like instance to copy from
     * @param formalName the formal name
     */
    public DeviceInfoArray(VSMArray value, String formalName) {
        super(value, formalName);
    }

    /**
     * Create an instance by copying the value from a like instance.
     * @param value a like instance to copy from
     */
    public DeviceInfoArray(VSMArray value) {
        super(value);
    }

    /**
     * Create an instance of undefined value.
     */
    public DeviceInfoArray() {
    }
    /**
     *
     * @return
     */
    /*@Override
    public VSMParm copyOf() {
    return super.copyOf();
    }*/
    /**
     *
     * @param in
     * @param length
     * @throws java.io.IOException
     * @throws com.softwoehr.pigiron.access.VSMStruct.VSMStructStringReadException
     * @throws com.softwoehr.pigiron.access.VSMException
     */
    /*@Override
    public void read(DataInputStream in, int length) throws IOException, VSMStructStringReadException, VSMException {
    super.read(in, length);
    }*/
    /**
     *
     * @param out
     * @throws java.io.IOException
     */
    /*@Override
    public void write(DataOutputStream out) throws IOException {
    super.write(out);
    }*/
}
