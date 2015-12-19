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

/* Autogenerated Sat Dec 19 03:56:18 UTC 2015
 * by pigstruct.m4 Copyright *C* 2015 Jack J. Woehr
 * Part of the PigIron Project http://pigiron.sourceforge.net
 */
package com.softwoehr.pigiron.access.paramstructs;

import com.softwoehr.pigiron.access.*;

/**
 * RealDeviceStructure implements the {@code real_device_structure} from {@code Virtual_Network_Vswitch_Query}
 * @see com.softwoehr.pigiron.functions.VirtualNetworkVswitchQuery
 */
public class RealDeviceStructure extends VSMStruct {

    /** Device is not active */
    public static final int DEVICE_STATUS_NOT_ACTIVE = 0;
    /** Device is active */
    public static final int DEVICE_STATUS_ACTIVE = 1;
    /** Device is a backup device */
    public static final int DEVICE_STATUS_BACKUP_DEVICE = 2;
    /** No error */
    public static final int DEVICE_ERROR_STATUS_NO_ERROR = 0;
    /** Port name conflict */
    public static final int DEVICE_ERROR_STATUS_PORT_NAME_CONFLICT = 1;
    /** No layer 2 support */
    public static final int DEVICE_ERROR_STATUS_NO_LAYER_2_SUPPORT = 2;
    /** Real device does not exist */
    public static final int DEVICE_ERROR_STATUS_REAL_DEVICE_DOES_NOT_EXIST = 3;
    /** Real device is attached elsewhere */
    public static final int DEVICE_ERROR_STATUS_REAL_DEVICE_ATTACHED_ELSEWHERE = 4;
    /** Real device is not QDIO OSA-E */
    public static final int DEVICE_ERROR_STATUS_REAL_DEVICE_NOT_QDIO_OSA_E = 5;
    /** Initialization error */
    public static final int DEVICE_ERROR_STATUS_INITIALIZATION_ERROR = 6;
    /** Stalled OSA */
    public static final int DEVICE_ERROR_STATUS_STALLED_OSA = 7;
    /** Stalled controller */
    public static final int DEVICE_ERROR_STATUS_STALLED_CONTROLLER = 8;
    /** Controller connection severed */
    public static final int DEVICE_ERROR_STATUS_CONTROLLER_CONNECTION_SEVERED = 9;
    /** Primary or secondary routing conflict */
    public static final int DEVICE_ERROR_STATUS_PRIMARY_SECONDARY_ROUTING_CONFLICT = 10;
    /** Device is offline */
    public static final int DEVICE_ERROR_STATUS_DEVICE_OFFLINE = 11;
    /** Device was detached */
    public static final int DEVICE_ERROR_STATUS_DEVICE_DETACHED = 12;
    /** IP/Ethernet type mismatch */
    public static final int DEVICE_ERROR_STATUS_IP_ETHERNET_TYPE_MISMATCH = 13;
    /** Insufficient memory in controller virtual machine */
    public static final int DEVICE_ERROR_STATUS_INSUFFICIENT_MEMORY_CONTROLLER_VIRTUAL_MACHINE = 14;
    /** TCP/IP configuration conflict */
    public static final int DEVICE_ERROR_STATUS_TCP_IP_CONFIGURATION_CONFLICT = 15;
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
    public RealDeviceStructure(VSMStruct value, String formalName) {
        this(value);
        setFormalName(formalName);
    }

    /**
     * Create an instance with a value derived by copying from a like instance.
     * null is legal value, means "just clear me".
     * @param value a like instance to copy from
     */
    public RealDeviceStructure(VSMStruct value) {
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
    public RealDeviceStructure(String formal_name) {
    	   super();
	   setFormalName(formal_name);
	   modelFormalParameters();
    }

    /**
     * Create a read-modelled instance.
     */
    public RealDeviceStructure() {
        super();
        modelFormalParameters();
    }

    /**
     * Create an instance with all attributes instantiated
     * and instance its formal name at the same time.
     * This makes it easy to set up a VSMAPI input instance
     * of this structure.
     */
    public RealDeviceStructure(VSMInt4 real_device_address, CountedString controller_name, CountedString port_name, VSMInt1 device_status, VSMInt1 device_error_status, VSMInt4 authorized_user_array_length, AuthorizedUserArray authorized_user_array, VSMInt4 connected_adapter_array_length, ConnectedAdapterArray connected_adapter_array, String formalName) {
        super();
        add(real_device_address);
        add(controller_name);
        add(port_name);
        add(device_status);
        add(device_error_status);
        add(authorized_user_array_length);
        add(authorized_user_array);
        add(connected_adapter_array_length);
        add(connected_adapter_array);
        setFormalName(formalName);
    }

    /**
     * Create a read-modelled instance.
     */
    public final void modelFormalParameters() {
        clear();
        add(new VSMInt4(-1, "real_device_address"));
        add(new CountedString("", "controller_name"));
        add(new CountedString("", "port_name"));
        add(new VSMInt1(0, "device_status"));
        add(new VSMInt1(0, "device_error_status"));
        add(new VSMInt4(-1, "authorized_user_array_length"));
        add(AuthorizedUserArray.modelArray("authorized_user_array"));

        add(new VSMInt4(-1, "connected_adapter_array_length"));
        add(ConnectedAdapterArray.modelArray("connected_adapter_array"));

    }
}

/* End of autogenerated code */

