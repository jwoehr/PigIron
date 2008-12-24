/*
 *  Copyright (c) 2008, Jack J. Woehr jwoehr@softwoehr.com
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
package com.softwoehr.piglet.builder;

import com.softwoehr.pigiron.webobj.topview.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;

/**
 * Static methods used by the Builder web application.
 *
 * @author     jax
 * @created    December 23, 2008
 */
public class BuilderUtil {
    
    /** Class has only static members */
    private BuilderUtil() {}

    /**
     *  Sets the defaultHost attribute of the Builder in the caller's Session
     *
     * @param  request  The http request
     * @param  host     The new defaultHost value
     */ 
    public static void setDefaultHost(HttpServletRequest request, Host host) {
        request.getSession(true).setAttribute("default_host", host);
    }

    /**
     *  Gets the defaultHost attribute of the Builder in the caller's Session
     *
     * @param  request  The http request
     * @return          The defaultHost value
     */ 
    public static Host getDefaultHost(HttpServletRequest request) {
	Host host = null;
	Object obj = request.getSession(true).getAttribute("default_host");
	try {
	    host = obj != null ? Host.class.cast(obj) : new Host();
	}
	catch (org.json.JSONException ex) {
	    Logger.getLogger(BuilderUtil.class.getName()).log(Level.SEVERE, null, ex);
	}
        return host;
    }

    /**
     *  Sets the defaultUser attribute of the Builder in the caller's Session
     *
     * @param  request  The http request
     * @param  User     The new defaultUser value
     */ 
    public static void setDefaultUser(HttpServletRequest request, User user) {
        request.getSession(true).setAttribute("default_user", user);
    }

    /**
     *  Gets the defaultUser attribute of the Builder in the caller's Session
     *
     * @param  request The http request
     * @return         The defaultUser value
     */ 
    public static User getDefaultUser(HttpServletRequest request) {
	User user = null;
	Object obj = request.getSession(true).getAttribute("default_user");
	try {
	    user = obj != null ? User.class.cast(obj) : new User();
	 }
	catch (org.json.JSONException ex) {
	    Logger.getLogger(BuilderUtil.class.getName()).log(Level.SEVERE, null, ex);
	}
        return user;
    }

    /**
     * Flatten an array of String to a single String. Needed
     * because HttpServletRequest.getParameterMap() returns
     * each parameter as an array of String.
     *
     * @param stringArray that which needs flattening
     * @return the flattened String
     */
    public static String flatten (String [] stringArray) {
	StringBuffer sb = new StringBuffer();
	for (int i = 0; i <stringArray.length; i++) {
	    sb.append(stringArray[i]);
	}
	return sb.toString();
    }
}

