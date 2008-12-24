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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Map;

/**
 * Deals with setting and getting the default Host
 *
 * @author     jax
 * @created    December 23, 2008
 */

public class DefaultUser {
    /**
     * Constructor does nothing.
     */ 
    public DefaultUser() {
    }

    /**
     * Performs the HTTP <code>GET</code> method for Default User, including
     * closing the PrintWriter. Gets the default User into a form for setting
     * the default user.
     *
     * @param  request            servlet request
     * @param  response           servlet response
     * @throws  ServletException  if a servlet-specific error occurs
     * @throws  IOException       if an I/O error occurs
     */ 
    public void doGet(HttpServletRequest request,
             HttpServletResponse response) throws ServletException,  IOException {
	printForm(request,response);
        response.getWriter().close();
    }

    /**
     * Performs the HTTP <code>POST</code> method. Sets the default User from
     * form data and then gets it into a form for setting the default user from
     * form data.
     *
     *
     * @param  request            servlet request
     * @param  response           servlet response
     * @throws  ServletException  if a servlet-specific error occurs
     * @throws  IOException       if an I/O error occurs
     */ 
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,  IOException {
	User user = BuilderUtil.getDefaultUser(request);
	Map map = request.getParameterMap();
	try {
            if (map.containsKey("uid")) {user.setUid(BuilderUtil.flatten((String[])map.get("uid")));}
	    if (map.containsKey("password")) {user.setPassword(BuilderUtil.flatten((String[])map.get("password")));}
	}
	catch (org.json.JSONException ex) {
	    Logger.getLogger(DefaultUser.class.getName()).log(Level.SEVERE, null, ex);
	}
	BuilderUtil.setDefaultUser(request, user);
	printForm(request, response);
	response.getWriter().close();
    }

    public void printForm(HttpServletRequest request,
             HttpServletResponse response) throws ServletException,  IOException {
	String uid = null;
	User currentDefaultUser = BuilderUtil.getDefaultUser(request);
	try {
	    uid = currentDefaultUser == null ? "_userid_" : currentDefaultUser.getUid();
	}
	catch (org.json.JSONException ex) {
	    Logger.getLogger(DefaultUser.class.getName()).log(Level.SEVERE, null, ex);
	}
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
	out.println("<http><body>");
	out.println("<form method=\"post\" action=\"/piglet/BuilderServlet/default_user\">");
      	out.println("<input type=\"text\"  name=\"uid\" value = \"" + uid + "\"/>");
        out.println("<p><input value=\"Submit\" type=\"submit\"></p>");
        out.println("</form></body></http>");
    }
}

