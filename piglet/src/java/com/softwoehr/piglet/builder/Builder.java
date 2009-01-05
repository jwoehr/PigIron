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
import java.lang.reflect.Method;
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
    public void doGet(HttpServletRequest request,
             HttpServletResponse response) throws ServletException,  IOException {
        printForm(request, response);
        response.getWriter().close();
    }

    /**
     * Performs the HTTP <code>POST</code> method, including closing
     * the PrintWriter (actually closed by the builder.function VSMCall building
     * methods to which it is dispatched).
     *
     * @param  request            servlet request
     * @param  response           servlet response
     * @throws  ServletException  if a servlet-specific error occurs
     * @throws  IOException       if an I/O error occurs
     */ 
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,  IOException {
        String vsmcall_name = request.getParameter("piglet.buildcall.vsmcall");
        try {
            Class c = Class.forName("com.softwoehr.piglet.builder.functions." + vsmcall_name);
            Method m = c.getMethod("doPost", new Class [] { HttpServletRequest .class , HttpServletResponse .class} );
            m.invoke(c.newInstance(), new Object [] { request,response} );
        } catch (java.lang.ClassNotFoundException ex) {
            Logger.getLogger(Builder.class.getName()).log(Level.SEVERE, null,
                     ex);
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<http><body>");
            out.println("Error in finding builder class " + ex.getMessage());
            out.println("</body></http>");
            out.close();
        } catch (java.lang.NoSuchMethodException ex) {
            Logger.getLogger(Builder.class.getName()).log(Level.SEVERE, null,
                     ex);
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<http><body>");
            out.println("Error in finding builder class " + ex.getMessage());
            out.println("</body></http>");
            out.close();
        } catch (java.lang.InstantiationException ex) {
            Logger.getLogger(Builder.class.getName()).log(Level.SEVERE, null,
                     ex);
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<http><body>");
            out.println("Error in finding builder class " + ex.getMessage());
            out.println("</body></http>");
            out.close();
        } catch (java.lang.IllegalAccessException ex) {
            Logger.getLogger(Builder.class.getName()).log(Level.SEVERE, null,
                     ex);
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<http><body>");
            out.println("Error in finding builder class " + ex.getMessage());
            out.println("</body></http>");
            out.close();
        } catch (java.lang.reflect.InvocationTargetException ex) {
            Logger.getLogger(Builder.class.getName()).log(Level.SEVERE, null,
                     ex);
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<http><body>");
            out.println("Error in finding builder class " + ex.getMessage());
            out.println("</body></http>");
            out.close();
        }
    } 
 
    /**
     * Dispatches the HTTP <code>PUT</code> method.
     *
     * @param  request            servlet request
     * @param  response           servlet response
     * @throws  ServletException  if a servlet-specific error occurs
     * @throws  IOException       if an I/O error occurs
     */ 
    public void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException,  IOException {
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
    public void printForm(HttpServletRequest request,
             HttpServletResponse response) throws ServletException,  IOException {
        String uid = null;
        String password = null;
        String name = null;
        String dns_name = null;
        String ip_address = null;
        int port_number = - 1;
        boolean ssl = false;
        User currentUser = BuilderUtil.getDefaultUser(request);
        Host currentHost = BuilderUtil.getDefaultHost(request);
        try {
            uid = currentUser == null ? "_userid_" : currentUser.getUid();
            password = currentUser == null ? "_password_" : currentUser.getPassword();
            name = currentHost == null ? "_name_" : currentHost.getName();
            dns_name = currentHost == null ? "_dns_name_" : currentHost.getDnsName();
            ip_address = currentHost == null ? "_ip_address_" : currentHost.getIpAddress();
            port_number = currentHost == null ? - 1 : currentHost.getPortNumber();
            ssl = currentHost == null ? false : currentHost.getSSL();
        } catch (org.json.JSONException ex) {
            Logger.getLogger(Builder .class.getName()).log(Level.SEVERE, null,
                     ex);
        }
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<http><body><h1>Build a VSMAPI Call</h1>");
        out.println("<form method=\"post\" action=\"/piglet/BuilderServlet\">");
	BuilderUtil.printBuilderUserHostHeader(request, response, out);
        out.println("<hr /><br>");
        out.println("<b>Select Function</b><br />");
        out.println("<SELECT NAME=\"piglet.buildcall.vsmcall\">");
        out.println("   <OPTION VALUE=\"CheckAuthentication\">CheckAuthentication");
        out.println("   <OPTION VALUE=\"QueryAPIFunctionalLevel\">QueryAPIFunctionalLevel");
        out.println("</SELECT>");
        out.println("<input value=\"Next\" type=\"submit\">");
        out.println("<INPUT TYPE=HIDDEN NAME=\"piglet.buildcall.state\" value=\"select_vsmcall\">");
        out.println("</form></body></http>");
    }
}

