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
package com.softwoehr.pigiron.webobj.topview;

import com.softwoehr.pigiron.webobj.MarshallableObject;
import com.softwoehr.pigiron.webobj.Marshaller;
import com.softwoehr.pigiron.webobj.Marshaller.MarshallingTraits;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.html.HTMLDocument.Iterator;
import org.json.JSONArray;
import org.json.JSONException;

/**
 * 
 * @author jax
 */
public class ArgumentArray extends MarshallableObject {

    private Vector<Argument> argumentVector = new Vector<Argument>();
    public JSONArray arguments = new JSONArray();

    public ArgumentArray prepareArguments() {
        arguments = new JSONArray(argumentVector);
        return this;
    }

    public void unpackArguments(Marshaller marshaller) {
        argumentVector = new Vector<Argument>();
        Argument tempArgument = null;
        for (int i = 0; i < arguments.length(); i++) {
            tempArgument = new Argument();
            try {
                tempArgument.fromRepresentation(arguments.get(i).toString(), marshaller);
            } catch (JSONException ex) {
                Logger.getLogger(ArgumentArray.class.getName()).log(Level.SEVERE, null, ex);
            }
            argumentVector.add(tempArgument);
        }
    }

    public void unpackArguments(Marshaller marshaller, MarshallingTraits marshallingTraits) {
        argumentVector = new Vector<Argument>();
        Argument tempArgument = null;
        for (int i = 0; i < arguments.length(); i++) {
            tempArgument = new Argument();
            try {
                tempArgument.fromRepresentation(arguments.get(i).toString(), marshaller, marshallingTraits);
            } catch (JSONException ex) {
                Logger.getLogger(ArgumentArray.class.getName()).log(Level.SEVERE, null, ex);
            }
            argumentVector.add(tempArgument);
        }
    }

    @Override
    public void fromRepresentation(String representation, Marshaller marshaller) {
        super.fromRepresentation(representation, marshaller);
        unpackArguments(marshaller);
    }

    @Override
    public void fromRepresentation(String representation, Marshaller marshaller, MarshallingTraits marshallingTraits) {
        super.fromRepresentation(representation, marshaller, marshallingTraits);
    }

    @Override
    public String toRepresentation(Marshaller marshaller) {
        prepareArguments();
        return super.toRepresentation(marshaller);
    }

    public String[] names() {
        return new String[]{"arguments"};
    }
}
