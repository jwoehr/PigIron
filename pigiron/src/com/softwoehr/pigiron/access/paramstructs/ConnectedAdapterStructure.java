/*
 * Copyright (c) 2008, 2015, Jack J. Woehr jwoehr@softwoehr.com
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

/* Autogenerated Sun Dec 20 20:23:23 UTC 2015
 * by pigstruct.m4 Copyright *C* 2015 Jack J. Woehr
 * Part of the PigIron Project http://pigiron.sourceforge.net
 */
package com.softwoehr.pigiron.access.paramstructs;

import com.softwoehr.pigiron.access.*;

/**
 * ConnectedAdapterStructure implements the {@code connected_adapter_structure} from {@code Virtual_Network_LAN_Query }
 * @see com.softwoehr.pigiron.functions.VirtualNetworkLANQuery
 */
public class ConnectedAdapterStructure extends VSMStruct {

    /**
     * Create an instance with a value derived by copying from a like instance
     * and instance its formal name at the same time.
     * null is legal value, means "just clear me and
     * re-initialize me with a valid list of yet-unread
     * parameters".
     * @param value a like instance to copy from
     * @param formalName the formal name
     * @see com.softwoehr.pigiron.access.VSMStruct
     */
    public ConnectedAdapterStructure(VSMStruct value, String formalName) {
        this(value);
        setFormalName(formalName);
    }

    /**
     * Create an instance with a value derived by copying from a like instance.
     * null is legal value, means "just clear me".
     * @param value a like instance to copy from
     */
    public ConnectedAdapterStructure(VSMStruct value) {
        super(value);
        if (value == null) {
            modelFormalParameters();
        }
    }

     /**
     * Create an instance with the formal name instanced
     * and the parameters modelled for reading.
     * @param formal_name the formal name of the instance
     */
    public ConnectedAdapterStructure(String formal_name) {
    	   super();
	   setFormalName(formal_name);
	   modelFormalParameters();
    }

    /**
     * Create a read-modelled instance.
     */
    public ConnectedAdapterStructure() {
        super();
        modelFormalParameters();
    }

    /**
     * Create an instance with all attributes instantiated
     * and instance its formal name at the same time.
     * This makes it easy to set up a VSMAPI input instance
     * of this structure.
     */
    public ConnectedAdapterStructure(CountedString adapter_owner, CountedString image_device_number, String formalName) {
        super();
        add(adapter_owner);
        add(image_device_number);
        setFormalName(formalName);
    }

    /**
     * Create a read-modelled instance.
     */
    public final void modelFormalParameters() {
        clear();
        add(new CountedString(null, "adapter_owner"));
        add(new CountedString(null, "image_device_number"));
    }
}

/* End of autogenerated code */

