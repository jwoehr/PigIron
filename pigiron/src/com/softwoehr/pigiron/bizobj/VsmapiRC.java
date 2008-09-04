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
package com.softwoehr.pigiron.bizobj;

import java.util.HashMap;

/**
 * 
 * @author jax
 */
public class VsmapiRC {

    private HashMap<Integer, ReturnCode> rcMap = new HashMap<Integer, ReturnCode>(50);

    public VsmapiRC() {
        ReturnCode rc = new ReturnCode("RC_OK", 0);
        rc.addReasonCode(new ReasonCode("RS_NONE", "successful", 0));
        rc.addReasonCode(new ReasonCode("Segment was created or replaced, but specified userid in memory_access_identifier could not be found to give RSTD access", "RS_NOT_FOUND", 4));
        rc.addReasonCode(new ReasonCode("Request successful; object directory offline", "RS_OFFLINE", 8));
        rc.addReasonCode(new ReasonCode("Request successful; NAMESAVE statement already exists in directory", "RS_NAMESAVE_EXISTS", 12));
        rc.addReasonCode(new ReasonCode("Request successful; new list created", "RS_NEW_LIST", 12));
        rc.addReasonCode(new ReasonCode("Image not active", "RS_NOT_ACTIVE", 12));
        rc.addReasonCode(new ReasonCode("Request successful; no more entries, list destroyed", "RS_LIST_DESTROYED", 16));
        rc.addReasonCode(new ReasonCode("Request successful; new virtual network LAN created", "RS_VMLAN_CREATED", 20));
        rc.addReasonCode(new ReasonCode("No output; user(s) not authorized for specified segment", "RS_NOT_AUTHORIZED", 20));
        rc.addReasonCode(new ReasonCode("Request successful; virtual network LAN removed", "RS_VMLAN_REMOVED", 24));
        rc.addReasonCode(new ReasonCode("No matching entries found. Return buffer is empty.", "RS_NONE_FOUND", 28));
        rc.addReasonCode(new ReasonCode("There are no SCSI characteristics for this image.", "RS_EMPTY", 28));
        rc.addReasonCode(new ReasonCode("Query request successful, but segment not found", "RS_SEGMENT_NOT_FOUND", 28));
        rc.addReasonCode(new ReasonCode("Query request successful, but no data returned since no item was found matching the search criteria", "RS_NO_MATCH_ON_SEARCH", 28));
        rc.addReasonCode(new ReasonCode("No matching entries found", "RS_NOTIFY_NOT_FOUND", 28));
        rc.addReasonCode(new ReasonCode("Name was not in list", "RS_NOT_IN_LIST", 32));
        rc.addReasonCode(new ReasonCode("Name is already in list", "RS_NAME_IN_LIST", 36));
        rc.addReasonCode(new ReasonCode("Request successful; new virtual switch created", "RS_VSWITCH_CREATED", 40));
        rc.addReasonCode(new ReasonCode("Request successful; virtual switch removed", "RS_VSWITCH_REMOVED", 44));
        rc.addReasonCode(new ReasonCode("Multiple DEFINE or MODIFY statements are erased in system config", "RS_DEF_MOD_MULTI_ERASED", 66));
        rc.addReasonCode(new ReasonCode("Asynchronous operation succeeded", "RS_ASYNC_OP_SUCCEEDED", 100));
        rc.addReasonCode(new ReasonCode("Asynchronous operation in progress", "RS_ASYNC_OP_IN_PROGRESS", 104));
        rc.addReasonCode(new ReasonCode("Asynchronous operation failed", "RS_ASYNC_OP_FAILED", 108));
        rcMap.put(0, rc);
        rc =
                new ReturnCode("RCERR_SYNTAX", 24) {

                    public ReasonCode getReasonCode(int reason) {
                        ReasonCode result = null;
                        if (reasonCodes.containsKey(reason)) {
                            result = reasonCodes.get(reason);
                        } else {
                            result = new ReasonCode("RCERR_SYNTAX", "Syntax error in function parameter", reason);
                        }
                        return result;
                    }
                };

        rc.addReasonCode(new ReasonCode("Parameter value not recognized", "RS_UNRECOG", 19));
        rcMap.put(24, rc);
    }

    public ReturnCode getReturnCode(int returnCode) {
        return rcMap.get(returnCode);
    }

   
    public class ReturnCode {

        private String name = "";
        private int value = -1;
        protected HashMap<Integer, ReasonCode> reasonCodes = new HashMap<Integer, ReasonCode>(10);

        /**
         *
         * @param name
         * @param value
         */
        public ReturnCode(String name, int value) {
            setName(name);
            setValue(value);
        }

        public void addReasonCode(ReasonCode reason) {
            reasonCodes.put(reason.getValue(), reason);
        }

        public Integer toInteger() {
            return new Integer(value);
        }

        public ReasonCode getReasonCode(int reason) {
            ReasonCode result = null;
            if (reasonCodes.containsKey(reason)) {
                result = reasonCodes.get(reason);
            } else {
                result = new ReasonCode("RS_UNKNOWN_TO_PIGIRON", "PigIron does not know this reason code", reason);
            }
            return result;
        }

        /**
         *
         * @return
         */
        public String getName() {
            return name;
        }

        /**
         *
         * @param name
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         *
         * @return
         */
        public int getValue() {
            return value;
        }

        /**
         *
         * @param value
         */
        public void setValue(int value) {
            this.value = value;
        }
    }

    /**
     *
     */
    public class ReasonCode {

        private String name = "";
        private String message = "";
        private int value = -1;

        public ReasonCode(String name, String message, int value) {
            setName(name);
            setMessage(message);
            setValue(value);
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }
}
