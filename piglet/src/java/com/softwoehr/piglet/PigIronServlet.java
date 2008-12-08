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
package com.softwoehr.piglet;

import com.softwoehr.pigiron.webobj.topview.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;

/**
 *
 * @author jax
 */
public class PigIronServlet extends HttpServlet {

    private static Directory directory = new Directory(); { try {
            directory.put(new DirectoryEntry("requestor", new URI("/piglet/PigIronServlet/topview/requestor")));

        } catch (URISyntaxException ex) {
            Logger.getLogger(PigIronServlet .class.getName()).log(Level.SEVERE, null, ex);

        } catch (JSONException ex) {
            Logger.getLogger(PigIronServlet .class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */ 
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,  IOException {

        // response.setContentType("application/json;charset=UTF-8");
        response.setContentType("text/html;charset=UTF-8");        // makes testing easier
        PrintWriter out = response.getWriter();
        String myPathInfo = request.getPathInfo();
        String myMethod = request.getMethod();
        try {
            if (myPathInfo.equals("/topview") | myPathInfo.equals("/topview/")) {
                if (myMethod.equals("GET")) {
                    out.println(directory);
                }
            } else {
                if (myPathInfo.equals("/topview/requestor") | myPathInfo.equals("/topview/requestor/")) {
                    try {
                        out.println(new Requestor().toString(4));
                    } catch (JSONException ex) {
                        out.println("JSON problem : " + ex);
                        Logger.getLogger(Requestor .class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    if (myPathInfo.equals("/topview/requestor.xml")) {
                        response.setContentType("text/xml;charset=UTF-8");
                        try {
                            out.println(org.json.XML.toString(new Requestor(),
                                     "pigiron-requestor"));
                        } catch (JSONException ex) {
                            out.println("JSON problem : " + ex);
                            Logger.getLogger(Requestor .class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        ProofOfConcept.processRequest(request, response, out);
                    }
                }
            }
        } finally {
            out.close();
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */ 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,  IOException {

        // response.setContentType("application/json;charset=UTF-8");
        response.setContentType("text/html;charset=UTF-8");        // makes testing easier
        PrintWriter out = response.getWriter();
        String myPathInfo = request.getPathInfo();
        String myMethod = request.getMethod();
        out.println("POST Not implemented");
        out.close();
    }
 
    /**
     * Handles the HTTP <code>PUT</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */ 
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException,  IOException {

        // response.setContentType("application/json;charset=UTF-8");
        response.setContentType("text/html;charset=UTF-8");        // makes testing easier
        PrintWriter out = response.getWriter();
        String myPathInfo = request.getPathInfo();
        String myMethod = request.getMethod();
        out.println("PUT Not implemented");
        out.close();
    }
 
    /**
     * Handles the HTTP <code>DELETE</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */ 
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException,  IOException {

        // response.setContentType("application/json;charset=UTF-8");
        response.setContentType("text/html;charset=UTF-8");        // makes testing easier
        PrintWriter out = response.getWriter();
        String myPathInfo = request.getPathInfo();
        String myMethod = request.getMethod();
        out.println("DELETE Not implemented");
        out.close();
    }
 
 
    /**
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */ 
    public String getServletInfo() {
        return "PigLet the PigIron Servlet: {\"uri\":\"http://pigiron.sourceforge.net\"}";
    }
}
