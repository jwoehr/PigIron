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
 * CpuInfo2Structure implements the {@code cpu_info2_structure} from {@code Image_CPU_Query}
 * @see com.softwoehr.pigiron.functions.ImageCPUQuery
 */
public class CpuInfo2Structure extends VSMStruct {

    /** Base CPU */
    public static final int CPU_BASE_BASE = 1;
    /** Not Base CPU */
    public static final int CPU_BASE_NOT_BASE = 2;
    /** Stopped */
    public static final int CPU_STATUS_STOPPED = 1;
    /** Check-stopped */
    public static final int CPU_STATUS_CHECK_STOPPED = 2;
    /** Soft-stopped or active */
    public static final int CPU_STATUS_SOFT_STOPPED_OR_ACTIVE = 3;
    /** CP */
    public static final int CPU_TYPE_CP = 1;
    /** IFL */
    public static final int CPU_TYPE_IFL = 2;
    /** ZAAP */
    public static final int CPU_TYPE_ZAAP = 3;
    /** ZIIP */
    public static final int CPU_TYPE_ZIIP = 4;
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
    public CpuInfo2Structure(VSMStruct value, String formalName) {
        this(value);
        setFormalName(formalName);
    }

    /**
     * Create an instance with a value derived by copying from a like instance.
     * null is legal value, means "just clear me".
     * @param value a like instance to copy from
     */
    public CpuInfo2Structure(VSMStruct value) {
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
    public CpuInfo2Structure(String formal_name) {
    	   super();
	   setFormalName(formal_name);
	   modelFormalParameters();
    }

    /**
     * Create a read-modelled instance.
     */
    public CpuInfo2Structure() {
        super();
        modelFormalParameters();
    }

    /**
     * Create an instance with all attributes instantiated
     * and instance its formal name at the same time.
     * This makes it easy to set up a VSMAPI input instance
     * of this structure.
     * @param cpu_address the VSMInt4 argument to the ctor
     * @param cpu_id the CountedString argument to the ctor
     * @param cpu_base the VSMInt1 argument to the ctor
     * @param cpu_status the VSMInt1 argument to the ctor
     * @param cpu_type the VSMInt1 argument to the ctor
     * @param formalName the formal name of the data type for display/debug
     */
    public CpuInfo2Structure(VSMInt4 cpu_address, CountedString cpu_id, VSMInt1 cpu_base, VSMInt1 cpu_status, VSMInt1 cpu_type, String formalName) {
        super();
        add(cpu_address);
        add(cpu_id);
        add(cpu_base);
        add(cpu_status);
        add(cpu_type);
        setFormalName(formalName);
    }

    /**
     * Create a read-modelled instance.
     */
    public final void modelFormalParameters() {
        clear();
        add(new VSMInt4(-1, "cpu_address"));
        add(new CountedString("", "cpu_id"));
        add(new VSMInt1(0, "cpu_base"));
        add(new VSMInt1(0, "cpu_status"));
        add(new VSMInt1(0, "cpu_type"));
    }
}

/* End of autogenerated code */

