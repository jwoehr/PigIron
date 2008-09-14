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

import com.softwoehr.pigiron.webobj.*;

/**
 * A class to represent the most simple view of a VSMAPI User.
 * @author jax
 */
public class User extends MarshallableObject {

    public String uid = null;
    public String password = null;

    /**
     * Get password to use for the Host.
     * @return the password to use for the Host
     */
    public String getpassword() {
        return password;
    }

    /**
     * Set password to use for the Host
     * @param password to use for the Host
     */
    public void setpassword(String password) {
        this.password = password;
    }

    /**
     * Userid as represented to the Host.
     * @return user id
     */
    public String getuid() {
        return uid;
    }

    /**
     * Userid as represented to the Host.
     * @param uid user id
     */
    public void setuid(String uid) {
        this.uid = uid;
    }

    /**
     * Base ctor
     */
    public User() {
        super();
    }

    /**
     * Instance with userid and password
     * @param uid
     * @param password
     */
    public User(String uid, String password) {
        this();
        setuid(uid);
        setpassword(password);
    }

    /**
     * Test run of marshalling
     * @param argv
     */
    public static void main(String[] argv) {
        String uid = argv[0];
        String password = argv[1];
        String representation = "{" + "\"uid\":" + "\"" + uid + "\"" + "," + "\"password\":" + "\"" + password + "\"" + "}";
        User user = new User("Fringle", "MmBoo");
        System.out.println("Here is the User created with some silly values (not yours):");
        System.out.println("user.uid == " + user.getuid());
        System.out.println("user.password == " + user.getpassword());
        Marshaller marshaller = new JSONMarshaller();
        System.out.println("Here is the string representation of User at present: " + user.toRepresentation(marshaller));
        System.out.println("Here is the string representation you submitted: " + representation);
        user.fromRepresentation(representation, marshaller);
        System.out.println("Here is the User after marshalling in your values: ");
        System.out.println("user.uid == " + user.getuid());
        System.out.println("user.password == " + user.getpassword());
        System.out.println("Here is the string representation of User at present: " + user.toRepresentation(marshaller));
    }

    @Override
    public String[] names() {
        return new String[]{"uid", "password"};
    }
}
