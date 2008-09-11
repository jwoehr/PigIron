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
package com.softwoehr.pigiron.webobj;

import com.softwoehr.pigiron.webobj.Marshaller.MarshallingTraits;

/**
 * Parent class of all the web objects which can be marshalled
 * into string representation.
 * @see com.softwoehr.pigiron.webobj.Marshaller
 * @author jax
 */
public abstract class MarshallableObject implements Marshallable {

    public MarshallableObject() {
    }

    /**
     * Reinstance members of the Marshallable from a string representation
     * using the Marshaller specified.
     * @param representation string representation of the Marshallable
     * @param marshaller the Marshaller specified to do the marshalling
     */
    public void fromRepresentation(String representation, Marshaller marshaller) {
        marshaller.fromRepresentation(representation, this);
    }

    /**
     * Create marshalled representation in string of the marshallable object
     * using the marshaller provided.
     * @param marshaller the marshaller to do the work
     * @return representation in string of the marshallable object
     */
    public String toRepresentation(Marshaller marshaller) {
        return marshaller.represent(this);
    }

    /**
     * Reinstance members of the Marshallable from a string representation
     * using the Marshaller specified with the specified MarshallingTraits.
     * @param representation string representation of the Marshallable
     * @param marshaller the Marshaller specified to do the marshalling
     * @param marshallingTraits the traits applicable to the marshaller for this operation
     */
    public void fromRepresentation(String representation, Marshaller marshaller, MarshallingTraits marshallingTraits) {
        marshaller.fromRepresentation(representation, this, marshallingTraits);
    }

    /**
     * Identify marshallable fields to the marshaller
     * @return a string array naming the fields to be marshalled.
     */
    public abstract String [] names ();
}