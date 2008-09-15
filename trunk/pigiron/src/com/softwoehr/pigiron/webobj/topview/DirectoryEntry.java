/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwoehr.pigiron.webobj.topview;

import com.softwoehr.pigiron.webobj.JSONMarshaller;
import com.softwoehr.pigiron.webobj.MarshallableObject;
import com.softwoehr.pigiron.webobj.Marshaller;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author jax
 */
public class DirectoryEntry extends MarshallableObject {

    public String name;
    public URI uriUri;
    public String uri;

    @Override
    public String[] names() {
        return new String[]{"name", "uri"};
    }

    public DirectoryEntry(String name, URI uri) {
        this.name = name;
        this.uriUri = uri;
    }

    public DirectoryEntry unpack_members(Marshaller marshaller) {
        try {
            uriUri = new URI(uri);
        } catch (URISyntaxException ex) {
            Logger.getLogger(DirectoryEntry.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this;
    }

    public DirectoryEntry prepare_members() {
        uri = uriUri.toString();
        return this;
    }

    @Override
    public void fromRepresentation(String representation, Marshaller marshaller) {
        super.fromRepresentation(representation, marshaller);
        unpack_members(marshaller);
    }

    /*@Override
    public void fromRepresentation(String representation, Marshaller marshaller, MarshallingTraits marshallingTraits) {
    super.fromRepresentation(representation, marshaller, marshallingTraits);
    }*/
    @Override
    public String toRepresentation(Marshaller marshaller) {
        prepare_members();
        return super.toRepresentation(marshaller);
    }

    /**
     *
     * @param argv
     * @throws URISyntaxException
     */
    public static void main(String[] argv) throws URISyntaxException {
        String name = argv[0];
        String uri = argv[1];
        String rerepresentation = argv[2];
        DirectoryEntry direntry = new DirectoryEntry(name, new URI(uri));
        JSONMarshaller marshaller = new JSONMarshaller();
        System.out.println("The starting DirectorEntry: " + direntry);
        System.out.println("   name: " + direntry.name);
        System.out.println("   uriUri:  " + direntry.uriUri);
        String representation = direntry.toRepresentation(marshaller);
        System.out.println("Here's a fresh representation: " + representation);
        direntry.fromRepresentation(rerepresentation, marshaller);
        System.out.println("The rerepresented DirectorEntry: " + direntry);
        System.out.println("   name:   " + direntry.name);
        System.out.println("   uriUri: " + direntry.uriUri);
    }
}