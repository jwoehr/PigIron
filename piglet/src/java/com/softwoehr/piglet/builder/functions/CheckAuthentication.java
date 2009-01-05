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
package com.softwoehr.piglet.builder.functions;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import com.softwoehr.piglet.builder.BuilderUtil;

/**
 *  Description of the Class
 *
 * @author     jax
 * @created    January 3, 2009
 */
public class CheckAuthentication {
    /**
     * Constructor does nothing.
     */ 
    public CheckAuthentication() { }
    
    /**
     *  State machine for posts to CheckAuthentication from the call builder
     *
     * @param  request            servlet request
     * @param  response           servlet response
     * @throws  ServletException  if a servlet-specific error occurs
     * @throws  IOException       if an I/O error occurs
     */ 
    public void doPost(HttpServletRequest request,
             HttpServletResponse response) throws ServletException,  IOException {
        String state = request.getParameter("piglet.buildcall.state");
        if (state.equals("select_vsmcall")) {
            select_vsmcall(request, response);
        } else {
            if (state.equals("do_it")) {
                do_it(request, response);
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,
                         "Unknown state : " + state);
            }
        }
    }

    /**
    * Handles the {@code select_vsmcall } state of the CheckAuthentication call builder.
     * This is the state when the user has just chosen the call name
     *
     * @param  request            servlet request
     * @param  response           servlet response
     * @throws  ServletException  if a servlet-specific error occurs
     * @throws  IOException       if an I/O error occurs
     */ 
    public void select_vsmcall(HttpServletRequest request,
             HttpServletResponse response) throws ServletException,  IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<http><body>");
	out.println("<h1>CheckAuthentication</h1>");
	out.println("<form method=\"post\" action=\"/piglet/BuilderServlet\">");
	BuilderUtil.printBuilderUserHostHeader(request, response, out);
	out.println("<hr /><br />");
	out.println("<i>No further parameters needed</i><br /><br />");
	out.println("<input value=\"Do it!\" type=\"submit\">");
	out.println("<INPUT TYPE=HIDDEN NAME=\"piglet.buildcall.vsmcall\" value=\"CheckAuthentication\">");
	out.println("<INPUT TYPE=HIDDEN NAME=\"piglet.buildcall.state\" value=\"do_it\">");
        out.println("</form></body></http>");
        out.close();
    }

    /**
    * Handles the {@code do_it } state of the CheckAuthentication call builder.
     * This is the state when the user has click-confirmed executing the call.
     *
     * @param  request            servlet request
     * @param  response           servlet response
     * @throws  ServletException  if a servlet-specific error occurs
     * @throws  IOException       if an I/O error occurs
     */ 
    public void do_it(HttpServletRequest request,
             HttpServletResponse response) throws ServletException,  IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<http><body>CheckAuthentication.do_it()</body></http>");
        out.close();
    }
}

