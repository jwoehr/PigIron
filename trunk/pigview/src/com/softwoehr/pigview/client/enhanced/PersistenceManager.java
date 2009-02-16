/*
 *  Copyright (c) 2009, Jack J. Woehr jwoehr@softwoehr.com
 *  PO Box 51, Golden, Colorado 80402-0051 USA
 *  All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions
 *  are met:
 *
 *  * Redistributions of source code must retain the above copyright
 *  notice, this list of conditions and the following disclaimer.
 *  * Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer
 *  in the documentation and/or other materials provided with the
 *  distribution.
 *  * Neither the name of the PigIron Project nor the names of its
 *  contributors may be used to endorse or promote products derived
 *  from this software without specific prior written permission.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 *  AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 *  IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 *  ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 *  LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 *  SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 *  INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 *  CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 *  ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 *  THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.softwoehr.pigview.client.enhanced;

import com.google.gwt.http.client.*;
import com.google.gwt.user.client.Cookies;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;

/**
 *  Static methods to persist data in some fashion. Current implementation is cookies,
 *  because why send this sensitive stuff to the server? No encryption used.
 *
 * @author     jax
 * @created    February 15, 2009
 */
public class PersistenceManager {

    /**
     *  Prefix on cookies set by persistence management
     */ 
    public static final String PREFIX = "PigView:";
    /**
     *  2030-01-01 is as close to forever as we need to go now.
     */ 
    public static final Date FOREVER = new Date(2030, 1, 1);

    /**
     * Constructor for the PersistenceManager object is not used.
     */ 
    protected PersistenceManager() { }

    /**
     *  Persist a datum by name until expiry date.
     *
     * @param  name     name of datum
     * @param  value    datum
     * @param  expires  Date of expiry
     */ 
    public static void persist(String name, String value, Date expires) {
        Cookies.setCookie(PREFIX + name, value, expires);
    }

    /**
     *  Persist a datum by name until current browser session is ended.
     *
     * @param  name   name of datum
     * @param  value  datum
     */ 
    public static void persist(String name, String value) {
        Cookies.setCookie(PREFIX + name, value);
    }

    /**
     *  Fetch a datum by name.
     *
     * @param  name  name of datum
     * @return       datum
     */ 
    public static String fetch(String name) {
        return Cookies.getCookie(PREFIX + name);
    }

    /**
     *  Delete a datum by name.
     *
     * @param  name  datum to delete
     */ 
    public static void remove(String name) {
        Cookies.removeCookie(PREFIX + name);
    }

    /**
     *  Description of the Method
     *
     * @return    Description of the Return Value
     */ 
    public static Collection hostNames() {
        HashSet hostNameCollection = new HashSet();
        Iterator it = Cookies.getCookieNames().iterator();
	String radix = PREFIX + "host.DisplayName.";
        while (it.hasNext()) {
            String cookieName = it.next().toString();
            if (cookieName.startsWith(radix)) {
                hostNameCollection.add(Cookies.getCookie(cookieName));
            }
        }
        return hostNameCollection;
    }

    /**
     *  Gets the host's named property
     *
     * @param  displayName   display name of host
     * @param  propertyName  name of persistent property
     * @return               The host property value
     */ 
    public static String getHostProperty(String displayName, String propertyName) {
        return fetch("host." + displayName + "." + propertyName);
    }
 
    /**
     *  Sets the host's named property
     *
     * @param  displayName   display name of host
     * @param  propertyName  name of persistent property
     */ 
    public static void setHostProperty(String displayName, String propertyName, String value) {
        persist("host." + displayName + "." + propertyName, value);
    }
}

