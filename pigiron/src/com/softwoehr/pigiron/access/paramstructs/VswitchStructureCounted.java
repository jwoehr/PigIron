/*
 * Copyright (c) 2008, 2016, Jack J. Woehr jwoehr@softwoehr.com
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

/* Autogenerated Sun Oct 30 06:28:37 UTC 2016
 * by pigstruct.m4 Copyright *C* 2016 Jack J. Woehr
 * Part of the PigIron Project http://pigiron.sourceforge.net
 */
package com.softwoehr.pigiron.access.paramstructs;

import com.softwoehr.pigiron.access.*;

/**
 * VswitchStructureCounted wrappers the {@code VswitchStructure} from {@code Virtual_Network_Vswitch_Query}
 * as a PigIron CountedStruct pseudotype.
 * @see com.softwoehr.pigiron.functions.VirtualNetworkVswitchQuery
 * @see com.softwoehr.pigiron.access.paramstructs.VswitchStructure
 */
public class VswitchStructureCounted extends CountedStruct {

    /**
     * Create an instance with a value derived by copying from a like instance
     * and instance its formal name at the same time.
     * null is legal value, means "just clear me and
     * re-initialize me with a valid list of yet-unread
     * parameters".
     * @param value a like instance to copy from
     * @param formalName the formal name
     * @see com.softwoehr.pigiron.access.CountedStruct
     */
    public VswitchStructureCounted(CountedStruct value, String formalName) {
        this(value);
        setFormalName(formalName);
    }

    /**
     * Create an instance with a value derived by copying from a like instance.
     * null is legal value, means "just clear me".
     * @param value a like instance to copy from
     */
    public VswitchStructureCounted(CountedStruct value) {
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
    public VswitchStructureCounted(String formal_name) {
    	   super();
	   setFormalName(formal_name);
	   modelFormalParameters();
    }

    /**
     * Create a read-modelled instance.
     */
    public VswitchStructureCounted() {
        super();
        modelFormalParameters();
    }

    /**
     * Create an instance with all attributes instantiated
     * and instance its formal name at the same time.
     * This makes it easy to set up a VSMAPI input instance
     * of this structure.
     * @param vswitch_structure_length the VSMInt4 argument to the ctor
     * @param vswitch_structure the VswitchStructure argument to the ctor
     * @param formalName the formal name of the data type for display/debug
     */
    public VswitchStructureCounted(VSMInt4 vswitch_structure_length, VswitchStructure vswitch_structure, String formalName) {
        super();
        add(vswitch_structure_length);
        add(vswitch_structure);
        setFormalName(formalName);
    }

    /**
     * Create a read-modelled instance.
     */
    public final void modelFormalParameters() {
        clear();
        add(new VSMInt4(-1, "vswitch_structure_length"));
        add(new VswitchStructure("vswitch_structure"));
    }
}

/* End of autogenerated code */

