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

/* Autogenerated Mon Jan  4 02:27:35 UTC 2016
 * by pigbuilderfunc.m4 Copyright *C* 2015 Jack J. Woehr
 * Part of the PigIron Project http://pigiron.sourceforge.net
 */
package com.softwoehr.piglet.builder.functions;


/**
 * {@code Asynchronous_Notification_Query_DM} VSMAPI Function
 * @see com.softwoehr.pigiron.access.paramstructs.NotificationArray
 */
public class AsynchronousNotificationQueryDM extends BuilderFunctionProxy {


    /** DIRECTORY */
    public static final int ENTITY_TYPE_DIRECTORY = 1;

    /** Unspecified */
    public static final int COMMUNICATION_TYPE_UNSPECIFIED = 0;

    /** TCP */
    public static final int COMMUNICATION_TYPE_TCP = 1;

    /** UDP */
    public static final int COMMUNICATION_TYPE_UDP = 2;

    /** Unspecified */
    public static final int ENCODING_UNSPECIFIED = 0;

    /** ASCII */
    public static final int ENCODING_ASCII = 1;

    /** EBCDIC */
    public static final int ENCODING_EBCDIC = 2;

    /**
     * Constructor does nothing.
     */ 
    public AsynchronousNotificationQueryDM() { super();}

    /**
     *  Gets the parameters array for this BuilderFunctionProxy extender
     *
     * @return    The parameters that need form building and filling in
     */ 
    public Parameter [] getParameters() {
        return new Parameter [] {
            new Parameter("target_identifier", "", "Target of operation"),
            new Parameter("entity_type", 0, "The entity type for which notifications will be sent"),
            new Parameter("communication_type", 0, "The communication type of the notification strings being queried"),
            new Parameter("port_number", 0, "The port number of the socket that will receive the notifications"),
            new Parameter("ip_address", "", "The IPV4 IP address of the socket that will receive the notifications. A null selects all that qualify."),
            new Parameter("encoding", 0, "The encoding of the notification strings being queried"),
            new Parameter("subscriber_data", "*", "Anything the subscriber wishes to receive along with the notification. * selects all that qualify.") } ;
    }
}
