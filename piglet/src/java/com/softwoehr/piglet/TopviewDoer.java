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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;

/**
 * Handles all HTTP requests aimed at /piglet/PigIronServlet/topview or the
 * /piglet/PigIronServlet/topview/** tree
 *
 * @author jax
 * @created December 8, 2008
 */
public class TopviewDoer {

    private final static Directory DIRECTORY = new Directory();

    static {
        try {
            DIRECTORY.put(new DirectoryEntry("argument",
                    new URI("/piglet/PigIronServlet/topview/argument")));
            DIRECTORY.put(new DirectoryEntry("function",
                    new URI("/piglet/PigIronServlet/topview/function")));
            DIRECTORY.put(new DirectoryEntry("host",
                    new URI("/piglet/PigIronServlet/topview/host")));
            DIRECTORY.put(new DirectoryEntry("requestor",
                    new URI("/piglet/PigIronServlet/topview/requestor")));
            DIRECTORY.put(new DirectoryEntry("response",
                    new URI("/piglet/PigIronServlet/topview/response")));
            DIRECTORY.put(new DirectoryEntry("user",
                    new URI("/piglet/PigIronServlet/topview/user")));
        } catch (URISyntaxException ex) {
            Logger.getLogger(PigIronServlet.class.getName()).log(Level.SEVERE,
                    null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(PigIronServlet.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
    }

    /**
     * Constructor for the TopviewDoer object. Does nothing.
     */
    public TopviewDoer() {
    }

    /**
     * Handles the HTTP <code>GET</code> method. Composes and writes the
     * document contents. Closes the writer.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        // response.setContentType("text/html;charset=UTF-8");        // makes testing easier
        PrintWriter out = response.getWriter();
        String myPathInfo = request.getPathInfo();
        String myMethod = request.getMethod();
        try {
            if (myPathInfo.equals("/topview") | myPathInfo.equals("/topview/")) {
                try {
                    out.println(DIRECTORY.toString(1));
                } catch (JSONException ex) {
                    out.println("JSON problem : " + ex);
                    Logger.getLogger(Directory.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (myPathInfo.equals("/topview/requestor")) {
                try {
                    out.println(new Requestor().toString(1));
                } catch (JSONException ex) {
                    out.println("{\"JSON_ERR\":\"" + ex + "\"}");
                    Logger.getLogger(Requestor.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else /*
                    if (myPathInfo.equals("/topview/requestor.xml")) {
                    response.setContentType("text/xml;charset=UTF-8");
                    try {
                    out.println(org.json.XML.toString(new Requestor(), "pigiron-requestor"));
                    } catch (JSONException ex) {
                    out.println("{\"result\":\"JSON_ERR\",\"messageText\":\"" + ex + "\",\"requestor\":null}");
                    Logger.getLogger(Requestor.class.getName()).log(Level.SEVERE, null, ex);
                    }
             */ if (myPathInfo.equals("/topview/response")) {
                try {
                    out.println(new Response().toString(1));
                } catch (JSONException ex) {
                    out.println("{\"JSON_ERR\":\"" + ex + "\"}");
                    Logger.getLogger(Response.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (myPathInfo.equals("/topview/host")) {
                try {
                    out.println(new Host().toString(1));
                } catch (JSONException ex) {
                    out.println("{\"JSON_ERR\":\"" + ex + "\"}");
                    Logger.getLogger(Host.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (myPathInfo.equals("/topview/user")) {
                try {
                    out.println(new User().toString(1));
                } catch (JSONException ex) {
                    out.println("{\"JSON_ERR\":\"" + ex + "\"}");
                    Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (myPathInfo.equals("/topview/argument")) {
                try {
                    out.println(new Argument().toString(1));
                } catch (JSONException ex) {
                    out.println("{\"JSON_ERR\":\"" + ex + "\"}");
                    Logger.getLogger(Argument.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (myPathInfo.equals("/topview/function")) {
                try {
                    out.println(new Function().toString(1));
                } catch (JSONException ex) {
                    out.println("{\"JSON_ERR\":\"" + ex + "\"}");
                    Logger.getLogger(Function.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                out.println("{\"PIGLET_ERR\":\"Unknown topview path " + myPathInfo + "\"}");
            }
        } finally {
            out.close();
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method. Closes the writer.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        // response.setContentType("application/json;charset=UTF-8");
        response.setContentType("text/html;charset=UTF-8");        // makes testing easier
        PrintWriter out = response.getWriter();
        String myPathInfo = request.getPathInfo();
        String myMethod = request.getMethod();
        out.println("POST Not implemented");
        out.close();
    }

    /**
     * Handles the HTTP <code>PUT</code> method. Closes the writer.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        ServletInputStream in = request.getInputStream();
        StringBuffer sb = new StringBuffer();
        while (true) {
            byte[] bytes = new byte[in.available()];
            int numread = in.read(bytes);
            if (numread != - 1) {
                sb.append(new String(bytes, 0, numread));
            } else {
                break;
            }
        }
        try {
            com.softwoehr.pigiron.webobj.topview.Requestor pigiron_requestor = new com.softwoehr.pigiron.webobj.topview.Requestor(sb.toString());
            com.softwoehr.pigiron.webobj.topview.Response pigiron_response = new Engine().execute(pigiron_requestor);
            out.println(pigiron_response);
        } catch (JSONException ex) {
            Logger.getLogger(com.softwoehr.pigiron.webobj.topview.Requestor.class.getName()).log(Level.SEVERE, null, ex);
            out.println("{\"result\":\"PIGLET_ERR\",\"messageText\":\"TopviewDoer.doPut() logged a JSONException to default logger.\",\"requestor\":null}");
        }
        out.close();
    }

    /**
     * Handles the HTTP <code>DELETE</code> method. Closes the writer.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // response.setContentType("application/json;charset=UTF-8");
        response.setContentType("text/html;charset=UTF-8");        // makes testing easier
        PrintWriter out = response.getWriter();
        String myPathInfo = request.getPathInfo();
        String myMethod = request.getMethod();
        out.println("DELETE Not implemented");
        out.close();
    }
}
