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

/* Autogenerated Sat Nov 28 03:11:09 UTC 2015
 * by pigstruct.m4 Copyright *C* 2015 Jack J. Woehr
 * Part of the PigIron Project http://pigiron.sourceforge.net
 */
package com.softwoehr.pigiron.access.paramstructs;

import com.softwoehr.pigiron.access.*;

/**
 * VswitchStructure implements the {@code vswitch_structure} from {@code Virtual_Network_Vswitch_Query}
 * @see com.softwoehr.pigiron.functions.VirtualNetworkVswitchQuery
 */
public class VswitchStructure extends VSMStruct {

    /** IP */
    public static final int TRANSPORT_TYPE_IP = 1;
    /** Ethernet */
    public static final int TRANSPORT_TYPE_ETHERNET = 2;
    /** Access */
    public static final int PORT_TYPE_ACCESS = 1;
    /** Trunk */
    public static final int PORT_TYPE_TRUNK = 2;
    /** The device will not act as a router */
    public static final int DEVICE_NOT_ROUTER = 1;
    /** The device will act as a router */
    public static final int DEVICE_ROUTER = 2;
    /** GVRP requested */
    public static final int GVRP_REQUESTED = 1;
    /** GVRP not requested */
    public static final int GVRP_NOT_REQUESTED = 2;
    /** GVRP enabled */
    public static final int GVRP_ENABLED = 1;
    /** GVRP not enabled */
    public static final int GVRP_NOT_ENABLED = 2;
    /** Virtual switch defined */
    public static final int SWITCH_STATUS_VIRTUAL_SWITCH_DEFINED = 1;
    /** Controller not available */
    public static final int SWITCH_STATUS_CONTROLLER_NOT_AVAILABLE = 2;
    /** Operator intervention required */
    public static final int SWITCH_STATUS_OPERATOR_INTERVENTION_REQUIRED = 3;
    /** Disconnected */
    public static final int SWITCH_STATUS_DISCONNECTED = 4;
    /**  Virtual devices attached to controller */
    public static final int SWITCH_STATUS_VIRTUAL_DEVICES_ATTACHED_TO_CONTROLLER = 5;
    /** OSA initialization in progress */
    public static final int SWITCH_STATUS_OSA_INITIALIZATION_IN_PROGRESS = 6;
    /** OSA device not ready */
    public static final int SWITCH_STATUS_OSA_DEVICE_NOT_READY = 7;
    /** OSA device ready */
    public static final int SWITCH_STATUS_OSA_DEVICE_READY = 8;
    /** OSA devices being detached */
    public static final int SWITCH_STATUS_OSA_DEVICES_BEING_DETACHED = 9;
    /** Virtual switch delete pending */
    public static final int SWITCH_STATUS_VIRTUAL_SWITCH_DELETE_PENDING = 10;
    /** Virtual switch failover recovering */
    public static final int SWITCH_STATUS_VIRTUAL_SWITCH_FAILOVER_RECOVERING = 11;
    /** Autorestart in progress */
    public static final int SWITCH_STATUS_AUTORESTART_IN_PROGRESS = 12;
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
    public VswitchStructure(VSMStruct value, String formalName) {
        this(value);
        setFormalName(formalName);
    }

    /**
     * Create an instance with a value derived by copying from a like instance.
     * null is legal value, means "just clear me".
     * @param value a like instance to copy from
     */
    public VswitchStructure(VSMStruct value) {
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
    public VswitchStructure(String formal_name) {
    	   super();
	   setFormalName(formal_name);
	   modelFormalParameters();
    }

    /**
     * Create a read-modelled instance.
     */
    public VswitchStructure() {
        super();
        modelFormalParameters();
    }

    /**
     * Create an instance with all attributes instantiated
     * and instance its formal name at the same time.
     * This makes it easy to set up a VSMAPI input instance
     * of this structure.
     */
    public VswitchStructure(VSMString switch_name, VSMInt1 transport_type, VSMInt1 port_type, VSMInt4 queue_memory_limit, VSMInt1 routing_value, VSMInt4 vlan_id, VSMInt4 native_vlan_id, VSMInt8 mac_id, VSMInt1 gvrp_request_attribute, VSMInt1 gvrp_enabled_attribute, VSMInt1 switch_status, VSMInt4 real_device_array_length, RealDeviceArray real_device_array, String formalName) {
        super();
        add(new VSMInt4(switch_name.paramLength(), "switch_name_length"));
        add(switch_name);
        add(transport_type);
        add(port_type);
        add(queue_memory_limit);
        add(routing_value);
        add(vlan_id);
        add(native_vlan_id);
        add(mac_id);
        add(gvrp_request_attribute);
        add(gvrp_enabled_attribute);
        add(switch_status);
        add(real_device_array_length);
        add(real_device_array);
        setFormalName(formalName);
    }

    /**
     * Create a read-modelled instance.
     */
    public final void modelFormalParameters() {
        clear();
        add(new VSMInt4(-1, "switch_name_length"));
        add(new VSMString("", "switch_name"));
        add(new VSMInt1(0, "transport_type"));
        add(new VSMInt1(0, "port_type"));
        add(new VSMInt4(-1, "queue_memory_limit"));
        add(new VSMInt1(0, "routing_value"));
        add(new VSMInt4(-1, "vlan_id"));
        add(new VSMInt4(-1, "native_vlan_id"));
        add(new VSMInt8(-1, "mac_id"));
        add(new VSMInt1(0, "gvrp_request_attribute"));
        add(new VSMInt1(0, "gvrp_enabled_attribute"));
        add(new VSMInt1(0, "switch_status"));
        add(new VSMInt4(-1, "real_device_array_length"));
        add(RealDeviceArray.modelArray("real_device_array"));

    }
}

/* End of autogenerated code */

