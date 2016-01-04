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

/* Autogenerated Mon Jan  4 02:27:24 UTC 2016
 * by pigstruct.m4 Copyright *C* 2015 Jack J. Woehr
 * Part of the PigIron Project http://pigiron.sourceforge.net
 */
package com.softwoehr.pigiron.access.paramstructs;

import com.softwoehr.pigiron.access.*;

/**
 * NotificationStructure implements the {@code notification_structure} from {@code Asynchronous_Notification_Query_DM}
 * @see com.softwoehr.pigiron.functions.AsynchronousNotificationQueryDM
 */
public class NotificationStructure extends VSMStruct {

    /** Include */
    public static final int SUBSCRIPTION_TYPE_INCLUDE = 1;
    /** Exclude */
    public static final int SUBSCRIPTION_TYPE_EXCLUDE = 2;
    /** TCP */
    public static final int COMMUNICATION_TYPE_TCP = 1;
    /** UDP */
    public static final int COMMUNICATION_TYPE_UDP = 2;
    /** ASCII */
    public static final int ENCODING_ASCII = 1;
    /** EBCDIC */
    public static final int ENCODING_EBCDIC = 2;
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
    public NotificationStructure(VSMStruct value, String formalName) {
        this(value);
        setFormalName(formalName);
    }

    /**
     * Create an instance with a value derived by copying from a like instance.
     * null is legal value, means "just clear me".
     * @param value a like instance to copy from
     */
    public NotificationStructure(VSMStruct value) {
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
    public NotificationStructure(String formal_name) {
    	   super();
	   setFormalName(formal_name);
	   modelFormalParameters();
    }

    /**
     * Create a read-modelled instance.
     */
    public NotificationStructure() {
        super();
        modelFormalParameters();
    }

    /**
     * Create an instance with all attributes instantiated
     * and instance its formal name at the same time.
     * This makes it easy to set up a VSMAPI input instance
     * of this structure.
     */
    public NotificationStructure(CountedString userid, VSMInt1 subscription_type, VSMInt1 communication_type, VSMInt4 port_number, CountedString ip_address, VSMInt1 encoding, CountedString subscriber_data, String formalName) {
        super();
        add(userid);
        add(subscription_type);
        add(communication_type);
        add(port_number);
        add(ip_address);
        add(encoding);
        add(subscriber_data);
        setFormalName(formalName);
    }

    /**
     * Create a read-modelled instance.
     */
    public final void modelFormalParameters() {
        clear();
        add(new CountedString("", "userid"));
        add(new VSMInt1(0, "subscription_type"));
        add(new VSMInt1(0, "communication_type"));
        add(new VSMInt4(-1, "port_number"));
        add(new CountedString("", "ip_address"));
        add(new VSMInt1(0, "encoding"));
        add(new CountedString("", "subscriber_data"));
    }
}

/* End of autogenerated code */

