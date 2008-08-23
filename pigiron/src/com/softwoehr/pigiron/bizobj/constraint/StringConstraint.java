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
package com.softwoehr.pigiron.bizobj.constraint;

import com.softwoehr.pigiron.access.VSMInt;
import com.softwoehr.pigiron.access.VSMString;

/**
 * Implementation of the VSMParamConstraint interface for Strings.
 * @author jax
 */
public class StringConstraint implements VSMParamConstraint {

    /**
     * Exercise a constraint by throwing if constraint is violated.
     * @param vsmString target of constraint
     * @throws com.softwoehr.pigiron.bizobj.constraint.VSMConstraintException if constraint is violated
     */
    public void constrain(VSMString vsmString) throws VSMConstraintException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Exercise a constraint by throwing if constraint is violated
     * @param vsmInt target of constraint
     * @throws com.softwoehr.pigiron.bizobj.constraint.VSMConstraintException if constraint is violated
     */
    public void constrain(VSMInt vsmInt) throws VSMConstraintException {
        throw new VSMStringConstraintTypeException("String constraint applied to a Integer.");
    }

    /**
     * Class to represent inappropriate target of constraint
     */
    public class VSMStringConstraintTypeException extends VSMConstraintException {

        /**
         * Instance assigned a message.
         * @param message the message
         */
        public VSMStringConstraintTypeException(String message) {
            super(message);
        }
    }
}
