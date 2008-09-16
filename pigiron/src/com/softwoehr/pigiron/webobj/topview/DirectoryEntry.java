/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwoehr.pigiron.webobj.topview;

import java.net.URI;
import java.net.URISyntaxException;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 
 * @author jax
 */
public class DirectoryEntry extends JSONObject {

    public String getName() throws JSONException {
        return getString("name");
    }

    public URI getURI() throws URISyntaxException, JSONException {
        return new URI(getString("uri"));
    }

    public DirectoryEntry(String name, URI uri) throws JSONException {
        put("name", name);
        put("uri", uri);
    }

    public DirectoryEntry(String source) throws JSONException {
        super(source);
    }

    /**
     *
     * @param argv
     * @throws URISyntaxException
     */
    public static void main(String[] argv) throws URISyntaxException, JSONException {
        String name = argv[0];
        String uri = argv[1];
        String rerepresentation = argv[2];
        DirectoryEntry direntry = new DirectoryEntry(name, new URI(uri));
        System.out.println("The starting DirectorEntry: " + direntry);
    }
}