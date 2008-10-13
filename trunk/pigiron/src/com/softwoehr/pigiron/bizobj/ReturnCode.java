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
 * Object interpreting the VSMAPI Return Code. Contains a string
 * name and a value. The full textual interpretation comes from the associated
 * ReasonCode.
 * 
 * The appearance is that Error Code space is a sparse-to-moderate
 * two-dimensional matrix.
 *
 * The reality is that <i>a few</i> reason codes have multiple interpretations
 * per VSMAPI  * function ... thus there is a <i>three</i>-level hierarchy:
 * <ol>
 * <li>Return code</li>
 * <li>Reason code</li>
 * <li>VSMAPI function</li>
 * </ol>
 *
 * I am informed by VSMAPI Development that this 3-levelness of
 * RetC/ReasC/Function is the way it is intended to be and always
 * will be in VSMAPI.
 *
 * This 3-level system is not fully implemented yet in PigIron so a few
 * ReturnCode/ReasonCode pairs are incorrect here in some function contexts,
 * probably about 16 of them.
 *
 * @author jax
 */
public class ReturnCode {

    private final String name;
    private final int value;
    /**
     *
     */
    protected HashMap<Integer, ReasonCode> reasonCodes = new HashMap<Integer, ReasonCode>(10);

    /**
     * Instance with the {@code final} name and value.
     * @param name Error name, e.g., {@code RS_NONE}
     * @param value numerical return code.
     */
    public ReturnCode(String name, int value) {
        this.name = name;
        this.value = value;
    }

    /**
     * Add a {@code ReasonCode} to the hash of {@code ReasonCode}s associated
     * witha given {@code ReturnCode}.
     * @param reason the numerical reason code
     */
    public void addReasonCode(ReasonCode reason) {
        reasonCodes.put(reason.getValue(), reason);
    }

    /**
     * Return a {@code ReasonCode} associated with {@code ReturnCode}.
     * If not found, return a special reason from PigIron
     * @param reason numerical VSMAPI Reason Code
     * @return the interpretive object representing the Reason Code
     */
    public ReasonCode getReasonCode(int reason) {
        ReasonCode result = null;
        if (reasonCodes.containsKey(reason)) {
            result = reasonCodes.get(reason);
        } else {
            result = new ReasonCode("PigIron does not know this reason code", "RS_UNKNOWN_TO_PIGIRON", reason);
        }
        return result;
    }

    /**
     * Get the name of the Return Code, e.g, {@code RS_NONE}
     * @return the name of the Return Code, e.g, {@code RS_NONE}
     */
    public String getName() {
        return name;
    }

    /**
     * Get the numerical value of the Return Code
     * @return the numerical value of the Return Code
     */
    public int getValue() {
        return value;
    }
}
