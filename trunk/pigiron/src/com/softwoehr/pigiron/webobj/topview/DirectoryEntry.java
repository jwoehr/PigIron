/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwoehr.pigiron.webobj.topview;

import java.net.URI;
import java.net.URISyntaxException;
import com.softwoehr.pigiron.webobj.WebObject;
import org.json.JSONException;

/**
 *
 * @author jax
 */
public class DirectoryEntry extends WebObject {

    /**
     * A Vector of the JSON keys (names) that are valid for a given WebObject
     * extender
     */
    private static NameList names = setNames(new String[]{"name", "uri"});

    /**
     * Get the array of JSON keys (names) that are valid for a given WebObject
     * extender
     *
     * @return The names
     */
    public String[] getNames() {
        return names.toArray(new String[names.size()]);
    }

    /**
     * Identifies whether a JSON key is one of the names a given WebObject
     * extender uses.
     *
     * @param name the JSON key
     * @return true if the key is one the class uses
     */
    public boolean isName(String name) {
        return isName(name, names);
    }

    /**
     *
     * @return @throws org.json.JSONException
     */
    public String getName() throws JSONException {
        return getString("name");
    }

    /**
     *
     * @return @throws java.net.URISyntaxException
     * @throws org.json.JSONException
     */
    public URI getURI() throws URISyntaxException, JSONException {
        return new URI(getString("uri"));
    }

    /**
     *
     * @param name
     * @param uri
     * @throws org.json.JSONException
     */
    public DirectoryEntry(String name, URI uri) throws JSONException {
        put("name", name);
        put("uri", uri);
    }

    /**
     *
     * @param source
     * @throws org.json.JSONException
     */
    protected DirectoryEntry(String source) throws JSONException {
        super(source);
    }

    /**
     *
     * @param argv
     * @throws URISyntaxException
     * @throws JSONException
     */
    public static void main(String[] argv) throws URISyntaxException,
            JSONException {

        String name = argv[0];
        String uri = argv[1];
        // String rerepresentation = argv[2];
        DirectoryEntry direntry = new DirectoryEntry(name, new URI(uri));
        System.out.println("The starting DirectorEntry: " + direntry);
    }
}
