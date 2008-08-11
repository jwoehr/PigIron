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

import com.softwoehr.pigiron.access.VSMInt1;
import com.softwoehr.pigiron.access.VSMInt4;
import com.softwoehr.pigiron.access.VSMString;
import com.softwoehr.pigiron.access.VSMStruct;

/**
 * 
 * @author jax
 */
public class DeviceInfoStruct extends VSMStruct {

    /**
     * null is legal value, means "just clear me and
     * re-initialize me with a valid list of yet-unread
     * parameters".
     * @param value
     * @param formalName
     */
    public DeviceInfoStruct(VSMStruct value, String formalName) {
        this(value);
        setFormalName(formalName);
    }

    /**
     * null is legal value, means "just clear me".
     * @param value
     */
    public DeviceInfoStruct(VSMStruct value) {
        super(value);
        if (value == null) {
            modelFormalParameters();
        }
    }

    /**
     *
     */
    public DeviceInfoStruct() {
        super();
        modelFormalParameters();
    }

    /**
     *
     * @param device_info_structure_length
     * @param device_type
     * @param device_address_length
     * @param device_address
     */
    public DeviceInfoStruct(VSMInt4 device_info_structure_length, VSMInt1 device_type, VSMInt4 device_address_length, VSMString device_address) {
        add(device_info_structure_length);
        add(device_type);
        add(device_address_length);
        add(device_address);
    }

    public void modelFormalParameters() {
        add(new VSMInt1(-1, "device_type"));
        add(new VSMInt4(-1, "device_address_length"));
        add(new VSMString(null, "device_address"));
    }
}
