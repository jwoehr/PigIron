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

import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A JSON implementation of Marshaller.
 * @author jax
 */
public class JSONMarshaller extends Marshaller {

    @Override
    public String represent(Marshallable marshallable) {
        String result = new JSONObject(marshallable, marshallable.names()).toString();
        return result;
    }

    @Override
    public Marshallable fromRepresentation(String representation, Marshallable marshallable) {
        JSONObject j = null;
        Field[] fields = marshallable.getClass().getFields();
        try {
            j = new JSONObject(representation);
        /* debug */
        // System.out.println("the new JSON object in Requestor made from the representation looks like this: " + j.toString());
        } catch (JSONException ex) {
            Logger.getLogger(JSONMarshaller.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int i = 0; i < fields.length; i++) {
            try {
                Object obj = null;
                Field field = fields[i];
                if (j.has(field.getName())) {
                    obj = j.get(field.getName());
                    /* debug */
                    // System.out.println("The name of the field we're working on is " + field.getName());
                    // System.out.println("The obj create by j.get(field.getName()) is" + obj);
                    Class type = field.getType();
                    field.set(marshallable, type.cast(obj));
                }
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(JSONMarshaller.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(JSONMarshaller.class.getName()).log(Level.SEVERE, null, ex);
            } catch (JSONException ex) {
                Logger.getLogger(JSONMarshaller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return marshallable;
    }
}