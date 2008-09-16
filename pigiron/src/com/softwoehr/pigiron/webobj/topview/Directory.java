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

import com.softwoehr.pigiron.webobj.JSONMarshaller;
import com.softwoehr.pigiron.webobj.MarshallableObject;
import com.softwoehr.pigiron.webobj.Marshaller;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.Vector;
import org.json.JSONArray;

/**
 *
 * @author jax
 */
public class Directory extends MarshallableObject {

    public Vector<DirectoryEntry> entriesVector = new Vector<DirectoryEntry>();
    public JSONArray entries;

    @Override
    public String[] names() {
        return new String[]{"entries"};
    }

    /*public Directory unpack_members(Marshaller marshaller) {
    entryVector = new Vector<DirectoryEntry>(entries.length);
    for (int i = 0; i < entries.length ; i++) {
    // entryVector.add
    }
    return this;
    }*/
    public Directory prepare_members(Marshaller marshaller) {
        Iterator<DirectoryEntry> it = entriesVector.iterator();
        entries = new JSONArray(entriesVector);
        return this;
    }

    /*@Override
    public void fromRepresentation(String representation, Marshaller marshaller) {
    super.fromRepresentation(representation, marshaller);
    unpack_members(marshaller);
    }*/

    /*@Override
    public void fromRepresentation(String representation, Marshaller marshaller, MarshallingTraits marshallingTraits) {
    super.fromRepresentation(representation, marshaller, marshallingTraits);
    }*/
    @Override
    public String toRepresentation(Marshaller marshaller) {
        prepare_members(marshaller);
        return entries.toString();
    }

    /**
     *
     * @param argv
     * @throws URISyntaxException
     */
    public static void main(String[] argv) throws URISyntaxException {
        /*String name = argv[0];
        String uri = argv[1];
        String rerepresentation = argv[2];*/
        Directory dir = new Directory();
        dir.entriesVector.add(new DirectoryEntry("Fred", new URI("/one/two/three")));
        dir.entriesVector.add(new DirectoryEntry("Argle", new URI("/four/five/siz")));

        System.out.println("The starting Directory: ");
        Iterator<DirectoryEntry> it = dir.entriesVector.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }

        JSONMarshaller marshaller = new JSONMarshaller();
        String representation = dir.toRepresentation(marshaller);
        System.out.println("Here's the representation: " + representation);
    }
}
