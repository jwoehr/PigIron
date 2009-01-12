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
import java.util.Enumeration;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONException;

/**
 *  Handles HTTP requests to the PigIron WebObject Engine
 *
 * @author     jax
 * @created    December 8, 2008
 */
public class EngineDoer {

    /**
     *Constructor for the EngineDoer object. Does nothing.
     */ 
    public EngineDoer() { }

    /**
     * Handles the HTTP <code>PUT</code> method. Reads and passes the request
     * document to PigIron's web object support which parses the JSON  and
     * performs  the JSON request payload and returns JSON response payload.
     * <br><br>
     * {@code doPut} does all the output and closes the Writer.
     *
     * @param  request            servlet request
     * @param  response           servlet response
     * @throws  ServletException  if a servlet-specific error occurs
     * @throws  IOException       if an I/O error occurs
     */ 
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException,  IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        ServletInputStream in = request.getInputStream();
        StringBuffer sb = new StringBuffer();
        while (true) {
            byte []bytes = new byte [in.available()];
            int numread = in.read(bytes);
            if (numread != - 1) {
                sb.append(new String(bytes, 0, numread));
            } else {
                break;
            }
        }
        if (sb.length() == 0) {
            out.println("{\"result\":\"PIGLET_ERR\",\"messageText\":\"Empty PUT to PigLet PigIron Servlet.\",\"requestor\":null}"); 
        } else {
            String inString = sb.toString();
            com.softwoehr.pigiron.webobj.topview.Requestor pigiron_requestor = null;
            com.softwoehr.pigiron.webobj.topview.Response pigiron_response = null;
            if (inString.startsWith("[")) {
                try {
                    JSONArray requestors = new JSONArray(inString);
                    if (requestors.length() > 0) {
                        for (int i = 0; i < requestors.length(); i++) {
                            pigiron_requestor = new com.softwoehr.pigiron.webobj.topview.Requestor(requestors.getString(i));
                            pigiron_response = new Engine().execute(pigiron_requestor);
                            out.println(pigiron_response.toString(1));
                        }
                    } else {
                        out.println("{\"result\":\"PIGLET_ERR\",\"messageText\":\"Empty request array was PUT to PigLet PigIron Servlet.\",\"requestor\":null}"); 
                    }
                } catch (JSONException ex) {
		    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                    out.println("{\"result\":\"PIGLET_ERR\",\"messageText\":\"EngineDoer.doPut() executing a requestor array logged a JSONException to default logger -- " + ex.getMessage() + "\",\"requestor\":null}");
                }
            } else {
                try {
                    pigiron_requestor = new com.softwoehr.pigiron.webobj.topview.Requestor(inString);
                    pigiron_response = new Engine().execute(pigiron_requestor);
                    out.println(pigiron_response.toString(1));
                } catch (JSONException ey) {
                    Logger.getLogger(com.softwoehr.pigiron.webobj.topview.Requestor .class.getName()).log(Level.SEVERE, null, ey);
                    out.println("{\"result\":\"PIGLET_ERR\",\"messageText\":\"EngineDoer.doPut() executing a single requestor logged a JSONException to default logger -- " + ey.getMessage() + "\",\"requestor\":null}");
                }
            }
        }
        out.close();
    }

    /**
     * Handles the HTTP <code>POST</code> method. Reads and passes the request
     * document to PigIron's web object support which parses the JSON  and
     * performs  the JSON request payload and returns JSON response payload.
     * <br><br>
     * {@code doPost} does all the output and closes the Writer.
     *
     * @param  request            servlet request
     * @param  response           servlet response
     * @throws  ServletException  if a servlet-specific error occurs
     * @throws  IOException       if an I/O error occurs
     */ 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,  IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String in = null;
        com.softwoehr.pigiron.webobj.topview.Requestor pigiron_requestor = null;
        com.softwoehr.pigiron.webobj.topview.Response pigiron_response = null;
        Map map = request.getParameterMap();
        if (map.containsKey("requestor")) {
            in = request.getParameter("requestor").trim();
            if (in == null | in.equals("")) {
                out.println("{\"result\":\"PIGLET_ERR\",\"messageText\":\"Empty single request was POSTed to PigLet PigIron Servlet.\",\"requestor\":null}");
            } else {
                if (in.startsWith("[")) {
                    try {
                        JSONArray requestors = new JSONArray(in);
                        if (requestors.length() > 0) {
                            for (int i = 0; i < requestors.length(); i++) {
                                pigiron_requestor = new com.softwoehr.pigiron.webobj.topview.Requestor(requestors.getString(i));
                                pigiron_response = new Engine().execute(pigiron_requestor);
                                out.println(pigiron_response.toString(1));
                            }
                        } else {
                            out.println("{\"result\":\"PIGLET_ERR\",\"messageText\":\"Empty request array was POSTed to PigLet PigIron Servlet.\",\"requestor\":null}"); 
                        }
                    } catch (JSONException ex) {
                        out.println("{\"result\":\"PIGLET_ERR\",\"messageText\":\"EngineDoer.doPost() executing a requestor array logged a JSONException to default logger -- " + ex.getMessage() + "\",\"requestor\":null}");
                    }
                } else {
                    try {
                        pigiron_requestor = new com.softwoehr.pigiron.webobj.topview.Requestor(in.toString());
                        pigiron_response = new Engine().execute(pigiron_requestor);
                        out.println(pigiron_response.toString(1));
                    } catch (JSONException ey) {
                        Logger.getLogger(com.softwoehr.pigiron.webobj.topview.Requestor .class.getName()).log(Level.SEVERE, null, ey);
                        out.println("{\"result\":\"PIGLET_ERR\",\"messageText\":\"EngineDoer.doPost() executing a single requestor logged a JSONException to default logger -- " + ey.getMessage() + "\",\"requestor\":null}");
                    }
                }
            }
        } else {
            out.println("{\"result\":\"PIGLET_ERR\",\"messageText\":\"Empty request was POSTed to PigLet PigIron Servlet.\",\"requestor\":null}");
        }
        out.close();
    }
}
