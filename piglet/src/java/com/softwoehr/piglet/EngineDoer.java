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
 *  Description of the Class
 *
 * @author     jax
 * @created    December 8, 2008
 */
public class EngineDoer {

    /**
     *Constructor for the EngineDoer object
     */ 
    public EngineDoer() { }

    /**
     * Handles the HTTP <code>PUT</code> method.
     *
     * @param  request            servlet request
     * @param  response           servlet response
     * @throws  ServletException  if a servlet-specific error occurs
     * @throws  IOException       if an I/O error occurs
     */ 
    protected void doPut(HttpServletRequest request,
             HttpServletResponse response) throws ServletException,  IOException {

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
	
        try {
            com.softwoehr.pigiron.webobj.topview.Requestor pigiron_requestor = new com.softwoehr.pigiron.webobj.topview.Requestor(sb.toString());
            com.softwoehr.pigiron.webobj.topview.Response pigiron_response = new Engine().execute(pigiron_requestor);
            out.println(pigiron_response.toString(4));
        } catch (JSONException ex) {
            out.println("JSON problem : " + ex);
            out.println("Your input was " + sb.length() + " characters long.");
            out.println("You sent: \n" + sb.toString());
            Logger.getLogger(com.softwoehr.pigiron.webobj.topview.Requestor .class.getName()).log(Level.SEVERE, null, ex);
        }

        out.close();
    }

}

