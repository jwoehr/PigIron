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

/* Autogenerated Thu Nov 26 04:57:04 UTC 2015
 * by pigbuilderfunc.m4 Copyright *C* 2015 Jack J. Woehr
 * Part of the PigIron Project http://pigiron.sourceforge.net
 */
package com.softwoehr.piglet.builder.functions;


/**
 * {@code Virtual_Network_Adapter_Create_DM } VSMAPI Function
 * To non-specify the  {@code channel_path_id} just leave it at its default value of {@code ""}.
 * To non-specify the  {@code mac_id} just leave it at its default value of {@code ""}.
 */
public class VirtualNetworkAdapterCreateDM extends BuilderFunctionProxy {


    /** Simulated HiperSockets NIC */
    public static final int ADAPTER_TYPE_HIPERSOCKETS = 1;

    /** Simulated QDIO NIC */
    public static final int ADAPTER_TYPE_QDIO = 2;

    /**
     * Constructor does nothing.
     */ 
    public VirtualNetworkAdapterCreateDM() { super();}

    /**
     *  Gets the parameters array for this BuilderFunctionProxy extender
     *
     * @return    The parameters that need form building and filling in
     */ 
    public Parameter [] getParameters() {
        return new Parameter [] {
            new Parameter("target_identifier", "", "Target of operation"),
            new Parameter("image_device_number", "", "The virtual device address of the new adapter"),
            new Parameter("adapter_type", -1, "The adapter type"),
            new Parameter("network_adapter_devices", -1, "The number of virtual devices associated with this adapter"),
            new Parameter("channel_path_id", "", "For use only when configuring a second-level z/OS system where it is used to specify the hex CHPID numbers for the first- and second-level systems"),
            new Parameter("mac_id", "", "The MAC identifier") } ;
    }
}
