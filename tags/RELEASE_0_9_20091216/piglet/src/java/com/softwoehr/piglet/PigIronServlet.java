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
package com.softwoehr.piglet;

import com.softwoehr.pigiron.webobj.topview.*;
import com.softwoehr.pigiron.webobj.Engine;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;

/**
 * Main dispatcher for servlet requests.
 *
 * @author     jax
 * @created    December 8, 2008
 */
public class PigIronServlet extends HttpServlet {

    /**
     * Constructor for the HttpServlet object does nothing.
     */ 
    public PigIronServlet() {
        super();
    }

    /**
     * Dispatches the HTTP <code>GET</code> method.
     *
     * @param  request            servlet request
     * @param  response           servlet response
     * @throws  ServletException  if a servlet-specific error occurs
     * @throws  IOException       if an I/O error occurs
     */ 
    protected void doGet(HttpServletRequest request,
             HttpServletResponse response) throws ServletException,  IOException {

        String myPathInfo = request.getPathInfo();
        if ("/topview/".regionMatches(0, myPathInfo, 0,
                 "/topview/".length()) | myPathInfo.equals("/topview")) {
            new TopviewDoer().doGet(request, response);
        } else {
            ProofOfConcept.processRequest(request, response);
        }
    }

    /**
     * Dispatches the HTTP <code>POST</code> method.
     *
     * @param  request            servlet request
     * @param  response           servlet response
     * @throws  ServletException  if a servlet-specific error occurs
     * @throws  IOException       if an I/O error occurs
     */ 
    protected void doPost(HttpServletRequest request,
             HttpServletResponse response) throws ServletException,  IOException {

        String myPathInfo = request.getPathInfo();
        if (myPathInfo.equals("/engine") | myPathInfo.equals("/engine/")) {
            (new EngineDoer()).doPost(request, response);
        } else {
            response.setContentType("application/json;charset=UTF-8");
            PrintWriter out = response.getWriter();
            try {
                out.println(new Response("PIGLET_ERR", "Unknown POST target \"" + myPathInfo + "\""));
            } catch (JSONException ex) {
                out.println("{\"result\":\"JSON_ERR\",\"messageText\":\"Error reporting unknown PUT target\\\"" + myPathInfo + "\\\" was " + ex + "\",\"requestor\":null}");
                Logger.getLogger(Requestor .class.getName()).log(Level.SEVERE,
                         null, ex);
            }
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
    protected void doPut(HttpServletRequest request,
             HttpServletResponse response) throws ServletException,  IOException {

        String myPathInfo = request.getPathInfo();
        if (myPathInfo.equals("/engine") | myPathInfo.equals("/engine/")) {
            (new EngineDoer()).doPut(request, response);
        } else {
            response.setContentType("application/json;charset=UTF-8");
            PrintWriter out = response.getWriter();
            try {
                out.println(new Response("PIGLET_ERR", "Unknown PUT target \"" + myPathInfo + "\""));
            } catch (JSONException ex) {
                out.println("{\"result\":\"JSON_ERR\",\"messageText\":\"Error reporting unknown PUT target\\\"" + myPathInfo + "\\\" was " + ex + "\",\"requestor\":null}");
                Logger.getLogger(Requestor .class.getName()).log(Level.SEVERE,
                         null, ex);
            }
        }
    }

    /**
     * Dispatches the HTTP <code>DELETE</code> method.
     *
     * @param  request            servlet request
     * @param  response           servlet response
     * @throws  ServletException  if a servlet-specific error occurs
     * @throws  IOException       if an I/O error occurs
     */ 
    protected void doDelete(HttpServletRequest request,
             HttpServletResponse response) throws ServletException,  IOException {

        // response.setContentType("application/json;charset=UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        // makes testing easier
        PrintWriter out = response.getWriter();
        String myPathInfo = request.getPathInfo();
        String myMethod = request.getMethod();
        out.println("DELETE Not implemented");
        out.close();
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return    a String containing servlet description
     */ 
    public String getServletInfo() {
        return "PigLet the PigIron Servlet: {\"uri\":\"http://pigiron.sourceforge.net\"}";
    }
}
