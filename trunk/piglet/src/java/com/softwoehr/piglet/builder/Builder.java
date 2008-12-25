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
 * Does the primary work of the Builder web application.
 *
 * @author     jax
 * @created    December 23, 2008
 */

public class Builder {
    /**
     * Constructor does nothing.
     */ 
    public Builder() { }

    /**
     * Performs the HTTP <code>GET</code> method for Builder, including
     * closing the PrintWriter.
     *
     * @param  request            servlet request
     * @param  response           servlet response
     * @throws  ServletException  if a servlet-specific error occurs
     * @throws  IOException       if an I/O error occurs
     */ 
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,  IOException {
        printForm(request, response);
        response.getWriter().close();
    }

    /**
     * Performs the HTTP <code>POST</code> method, including closing
     * the PrintWriter.
     *
     * @param  request            servlet request
     * @param  response           servlet response
     * @throws  ServletException  if a servlet-specific error occurs
     * @throws  IOException       if an I/O error occurs
     */ 
    public void doPost(HttpServletRequest request,
             HttpServletResponse response) throws ServletException,  IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<http><body>doPost() not implemented in Builder.java</body></http>"); 
	out.close();
    }
    
    /**
     * Dispatches the HTTP <code>PUT</code> method.
     *
     * @param  request            servlet request
     * @param  response           servlet response
     * @throws  ServletException  if a servlet-specific error occurs
     * @throws  IOException       if an I/O error occurs
     */ 
    public void doPut(HttpServletRequest request,
             HttpServletResponse response) throws ServletException,  IOException {
	response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<http><body>doPut() not implemented in Builder.java</body></http>"); 
	out.close();
    }

    /**
     *  Compose the form for setting the default Host who is used for VSMAPI
     * calls unless overridden.
     *
     * @param  request               The servlet request
     * @param  response              The servlet response
     * @throws  ServletException  if a servlet-specific error occurs
     * @throws  IOException       if an I/O error occurs
     */ 
    public void printForm(HttpServletRequest request, HttpServletResponse response) throws ServletException,  IOException {
        /*
        String name = null;
        String dns_name = null;
        String ip_address = null;
        int port_number = - 1;
        boolean ssl = false;
        Host currentBuilder = BuilderUtil.getBuilder(request);
        try {
        name = currentBuilder == null ? "_name_" : currentBuilder.getName();
        dns_name = currentBuilder == null ? "_dns_name_" : currentBuilder.getDnsName();
        ip_address = currentBuilder == null ? "_ip_address_" : currentBuilder.getIpAddress();
        port_number = currentBuilder == null ? - 1 : currentBuilder.getPortNumber();
        ssl = currentBuilder == null ? false : currentBuilder.getSSL();
        } catch (org.json.JSONException ex) {
        Logger.getLogger(Builder .class.getName()).log(Level.SEVERE, null, ex);
        }
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<http><body><h1>Set Default Host</h1>");
        out.println("<form method=\"post\" action=\"/piglet/BuilderServlet/default_host\">");
        out.println("<input type=\"text\"  name=\"name\" value = \"" + name + "\"/>");
        out.println("Host Name (only used symbolically)<br>");
        out.println("<input type=\"text\"  name=\"dns_name\" value = \"" + dns_name + "\"/>");
        out.println("DNS Name (lookup name -- if present, <tt>IP Address</tt> field is ignored)<br>");
        out.println("<input type=\"text\"  name=\"ip_address\" value = \"" + ip_address + "\"/>");
        out.println("IP Address (ignored if <tt>DNS Name</tt> is present)<br>");
        out.println("<input type=\"text\"  name=\"port_number\" value = \"" + port_number + "\"/>");
        out.println("Port Number<br>");
        out.println("<input type=\"checkbox\"  name=\"ssl\"" + (ssl ? "checked" : "") + "\"/>");
        out.println("Use SSL<br>");
        out.println("<p><input value=\"Submit\" type=\"submit\"></p>");
        out.println("</form></body></http>");
         */ 
    }
}

