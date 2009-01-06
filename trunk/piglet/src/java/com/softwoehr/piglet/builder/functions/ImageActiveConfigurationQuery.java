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
import org.json.JSONException;

/**
 *  Description of the Class
 *
 * @author     jax
 * @created    January 5, 2009
 */
public class ImageActiveConfigurationQuery {
    /**
     * Constructor does nothing.
     */ 
    public ImageActiveConfigurationQuery() { }
 
    /**
     *  State machine for posts to ImageActiveConfigurationQuery from the call builder
     *
     * @param  request            servlet request
     * @param  response           servlet response
     * @throws  ServletException  if a servlet-specific error occurs
     * @throws  IOException       if an I/O error occurs
     */ 
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,  IOException {
        String state = request.getParameter("piglet.buildcall.state");
        if (state.equals("select_vsmcall")) {
            select_vsmcall(request, response);
        } else {
            if (state.equals("do_it")) {
                do_it(request, response);
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Unknown state : " + state);
            }
        }
    }

    /**
     * Handles the {@code select_vsmcall } state of the ImageActiveConfigurationQuery call builder.
     * This is the state when the user has just chosen the call name
     *
     * @param  request            servlet request
     * @param  response           servlet response
     * @throws  ServletException  if a servlet-specific error occurs
     * @throws  IOException       if an I/O error occurs
     */ 
    public void select_vsmcall(HttpServletRequest request, HttpServletResponse response) throws ServletException,  IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<http><body>");
        out.println("<h1>ImageActiveConfigurationQuery</h1>");
        out.println("<form method=\"post\" action=\"/piglet/BuilderServlet\">");
        BuilderUtil.printBuilderUserHostHeader(request, response, out);
        out.println("<hr /><br />");
        out.println("<input type=\"text\"  name=\"target_identifier\" value = \"\"/>");
        out.println("Target Identifier<br>");
        BuilderUtil.printDoItButtonPanel(request, response, out);
        out.println("</form></body></http>");
        out.close();
    }

    /**
     * Handles the {@code do_it } state of the ImageActiveConfigurationQuery call builder.
     * This is the state when the user has click-confirmed executing the call.
     *
     * @param  request            servlet request
     * @param  response           servlet response
     * @throws  ServletException  if a servlet-specific error occurs
     * @throws  IOException       if an I/O error occurs
     */ 
    public void do_it(HttpServletRequest request, HttpServletResponse response) throws ServletException,  IOException {
	response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            com.softwoehr.pigiron.webobj.topview.Response pigiron_response = new com.softwoehr.pigiron.webobj.topview.Response();
            com.softwoehr.pigiron.webobj.topview.Requestor pigiron_requestor = new com.softwoehr.pigiron.webobj.topview.Requestor();
            com.softwoehr.pigiron.webobj.topview.User user = BuilderUtil.fromUserHeader(request, response);
            com.softwoehr.pigiron.webobj.topview.Host host = BuilderUtil.fromHostHeader(request, response);
            com.softwoehr.pigiron.webobj.topview.InputArgumentArray inArray = new com.softwoehr.pigiron.webobj.topview.InputArgumentArray();
	    inArray.put(new com.softwoehr.pigiron.webobj.topview.Argument("target_identifier", request.getParameter("target_identifier")));
    	    pigiron_requestor.setUser(user);
	    pigiron_requestor.setHost(host);com.softwoehr.pigiron.webobj.topview.Function function =
	    	new com.softwoehr.pigiron.webobj.topview.Function
		(
		"ImageActiveConfigurationQuery"
		, inArray
		, new com.softwoehr.pigiron.webobj.topview.OutputArgumentArray()
		);
	    pigiron_requestor.setFunction(function);	    
	    out.println("<http><body>");
	    out.println("<h1>ImageActiveConfigurationQuery</h1>");
	    String buttonPressed = request.getParameter("button_pressed");
	    if (buttonPressed == null) {
		Logger.getLogger(ImageActiveConfigurationQuery.class.getName()).log(Level.SEVERE, "Null button press received by " + this.getClass().getName() + ".do_it()");
		out.println("Oops, got a null button press.");
	    } else {
		if (buttonPressed.equals("Do it!")) {
		    pigiron_response = new com.softwoehr.pigiron.webobj.Engine().execute(pigiron_requestor);
		    out.println(pigiron_response.toHTML());
		} else {
		    if (buttonPressed.equals("Show JSON")) {
			out.println("<form method=\"post\" action=\"/piglet/BuilderServlet\">");
			BuilderUtil.printBuilderUserHostHeader(request, response, out);
			out.println("<hr /><br />");
			String target_identifier = request.getParameter("target_identifier");
			out.println("<input type=\"text\" name=\"target_identifier\" value = \"" + (target_identifier == null ? new String() : target_identifier) + "\" />");
			out.println("Target Identifier<br />");
			BuilderUtil.printDoItButtonPanel(request, response, out);
			out.println("<p><tt>" + pigiron_requestor.toString(1).replaceAll("\040", "&nbsp;").replaceAll("\n", "<br />") + "</tt></p>");
		    } else {
			Logger.getLogger(ImageActiveConfigurationQuery.class.getName()).log(Level.SEVERE, "Unknown button press \"" + buttonPressed + "\" received by " + this.getClass().getName() + ".do_it()");
			out.println("Oops, got an unknown  button press: " + buttonPressed);
		    }
		}
	    }
	} catch (JSONException ex) {
	    Logger.getLogger(ImageActiveConfigurationQuery.class.getName()).log(Level.SEVERE, null, ex);
	    out.println("\n Error logged processing ImageActiveConfigurationQuery.do_it: " + ex);
        } finally {
	    out.println("</body></http>");
            out.close();
	}
    }
}

