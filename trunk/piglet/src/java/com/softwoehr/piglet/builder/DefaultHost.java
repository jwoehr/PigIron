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

public class DefaultHost {
    /**
     * Constructor does nothing.
     */ 
    public DefaultHost() { }

    /**
     * Performs the HTTP <code>GET</code> method for Default Host, including
     * closing the PrintWriter. Gets the default Host into a form for setting
     * the default host.
     *
     * @param  request            servlet request
     * @param  response           servlet response
     * @throws  ServletException  if a servlet-specific error occurs
     * @throws  IOException       if an I/O error occurs
     */ 
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,  IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html><head><title>Set Default Host</title></head><body><h1>Set Default Host</h1>");
        printForm(request, response, out);
        out.println("</body></html>");
        out.close();
    }

    /**
     * Performs the HTTP <code>POST</code> method. Sets the default Host from
     * form data and then gets it into a form for setting the default host from
     * form data. Closes writer.
     *
     * @param  request            servlet request
     * @param  response           servlet response
     * @throws  ServletException  if a servlet-specific error occurs
     * @throws  IOException       if an I/O error occurs
     */ 
    public void doPost(HttpServletRequest request,
             HttpServletResponse response) throws ServletException,  IOException {
        String whatIDid = null;
        Host host = BuilderUtil.getDefaultHost(request);
        Map map = request.getParameterMap();
        if (map.containsKey("submit")) {
            if (request.getParameter("submit").equals("Submit")) {
                whatIDid = "set";
                try {
                    if (map.containsKey("name")) {
                        host.setName(request.getParameter("name"));
                    }
                    if (map.containsKey("dns_name")) {
                        host.setDnsName(request.getParameter("dns_name"));
                    }
                    if (map.containsKey("ip_address")) {
                        host.setIpAddress(request.getParameter("ip_address"));
                    }
                    if (map.containsKey("port_number")) {
                        host.setPortNumber(Integer.valueOf(request.getParameter("port_number")).intValue());
                    }
                    if (map.containsKey("ssl")) {
                        host.setSSL(true);
                    } else {
                        host.setSSL(false);
                    }
                } catch (org.json.JSONException ex) {
                    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                whatIDid = "cleared";
                if (! request.getParameter("submit").equals("Clear")) {
                    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Weird submit button press: " + request.getParameter("submit"));
                }
                try {
                    host.setName(""); host.setDnsName(""); host.setIpAddress(""); host.setPortNumber(- 1); host.setSSL(false);
                } catch (org.json.JSONException ex) {
                    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                }
            }
            BuilderUtil.setDefaultHost(request, host);
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<html><head><title>Set Default Host</title></head><body><h1>Set Default Host</h1>");
            printForm(request, response, out);
            out.println("<p><b>Default host " + whatIDid + ".</b></p>");
            out.println("</body></html>");
            out.close();
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Received a post without a submit button press.");
            try { host.setName(""); host.setDnsName(""); host.setIpAddress(""); host.setPortNumber(- 1); host.setSSL(false); }
	    catch (org.json.JSONException ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            }
            BuilderUtil.setDefaultHost(request, host);
            PrintWriter out = response.getWriter();
            out.println("<html><head><title>Set Default Host</title></head><body><h1>Set Default Host</h1>");
            printForm(request, response, out);
            out.println("<p><b>Received a post without a submit button press!? Logged error.</b></p>");
            out.println("</body></html>");
            out.close(); 
        }
    }

    /**
     * Performs the HTTP <code>PUT</code> method for Default Host, including
     * closing the PrintWriter. Currently is unimplemented.
     *
     * @param  request            servlet request
     * @param  response           servlet response
     * @throws  ServletException  if a servlet-specific error occurs
     * @throws  IOException       if an I/O error occurs
     */ 
    public void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException,  IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html><head><title>doPut() not implemented in DefaultHost.java</title></head><body>doPut() not implemented in DefaultHost.java</body></html>"); 
        out.close();
    }

    /**
     *  Compose the form for setting the default Host who is used for VSMAPI
     * calls unless overridden. Does not close the output writer.
     *
     * @param  request               The servlet request
     * @param  response              The servlet response
     * @throws  ServletException  if a servlet-specific error occurs
     * @throws  IOException       if an I/O error occurs
     */ 
    public void printForm(HttpServletRequest request, HttpServletResponse response, PrintWriter out) throws ServletException, 
         IOException {
        String name = null;
        String dns_name = null;
        String ip_address = null;
        int port_number = - 1;
        boolean ssl = false;
        Host currentDefaultHost = BuilderUtil.getDefaultHost(request);
        try {
            name = currentDefaultHost == null ? "_name_" : currentDefaultHost.getName();
            dns_name = currentDefaultHost == null ? "_dns_name_" : currentDefaultHost.getDnsName();
            ip_address = currentDefaultHost == null ? "_ip_address_" : currentDefaultHost.getIpAddress();
            port_number = currentDefaultHost == null ? - 1 : currentDefaultHost.getPortNumber();
            ssl = currentDefaultHost == null ? false : currentDefaultHost.getSSL();
        } catch (org.json.JSONException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null,
                     ex);
        }
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
        out.println("<p><input name=\"submit\" value=\"Submit\" type=\"submit\">");
        out.println("<input name=\"submit\" value=\"Clear\" type=\"submit\"></p>");
        out.println("</form>");
    }
}

