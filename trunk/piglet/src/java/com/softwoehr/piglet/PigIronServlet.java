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
			directory.put(new DirectoryEntry("requestor",
			        new URI("/piglet/PigIronServlet/topview/requestor")));

		} catch (URISyntaxException ex) {
			Logger.getLogger(PigIronServlet .class.getName()).log(Level.SEVERE,
			        null, ex);

		} catch (JSONException ex) {
			Logger.getLogger(PigIronServlet .class.getName()).log(Level.SEVERE,
			        null, ex);
		}
	}

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */ 
	protected void processRequest(HttpServletRequest request,
	        HttpServletResponse response)
	throws ServletException,  IOException {
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
			} else if (myPathInfo.equals("/topview/requestor") | myPathInfo.equals("/topview/requestor/")) {
				try {
					out.println(new Requestor().toString(4));
				} catch (JSONException ex) {
					out.println("JSON problem : " + ex);
					Logger.getLogger(Requestor.class.getName()).log(Level.SEVERE, null, ex);
				}
			} else if (myPathInfo.equals("/topview/requestor.xml")) {
				response.setContentType("text/xml;charset=UTF-8");
				try {
					out.println(org.json.XML.toString(new Requestor(),"pigiron-requestor"));
				} catch (JSONException ex) {
					out.println("JSON problem : " + ex);
					Logger.getLogger(Requestor.class.getName()).log(Level.SEVERE, null, ex);
				}
			} else {
				ProofOfConcept.processRequest(request, response, out);
				/*
				              response.setContentType("text/html;charset=UTF-8");
				              out = response.getWriter();
				              out.println("<html>");
				              out.println("<head>");
				              out.println("<title>PigLet the PigIron Servlet</title>");
				              out.println("</head>");
				              out.println("<body>");
				              out.println("<h1>Servlet PigIronServlet at " + request.getContextPath() + "</h1>");
				              out.println("You requested " + request.getRequestURI() + ".");
				              try {
				                  out.println("Relativized that is " + URI.create("/piglet/PigIronServlet").relativize(URI.create(request.getRequestURI())));
				                  out.println("Resolved that is " + new URI(request.getRequestURI()).resolve(new URI("/piglet/PigIronServlet")));
				              } catch (URISyntaxException ex) {
				                  Logger.getLogger(PigIronServlet .class.getName()).log(Level.SEVERE, null, ex);
				                          

				              }
				              out.println("Your request was a  " + request.getMethod() + ".");
				              out.println("<p>Pigiron Main says " + com.softwoehr.pigiron.Main.servletString() + "</p>");
				              out.println(request.toString());
				              out.println("</body>");
				              out.println("</html>");
				*/
			}
		} finally {
			out.close();
		}
	}

	// out.println("Printout of Request" + "<br />");
	// out.println(request.getAuthType() + "<br />");
	// out.println(request.getContextPath() + "<br />");
	// out.println(request.getCookies() + "<br />");
	// out.println(request.getDateHeader("Last-Modified") + "<br />");
	// out.println(request.getHeader(String + "<br />");
	// out.println(request.getHeaderNames() + "<br />");
	// out.println(request.getHeaders(String + "<br />");
	// out.println(request.getIntHeader(String + "<br />");
	// out.println(request.getMethod() + "<br />");
	// out.println(request.getPathInfo() + "<br />");
	// out.println(request.getPathTranslated() + "<br />");
	// out.println(request.getQueryString() + "<br />");
	// out.println(request.getRemoteUser() + "<br />");
	// out.println(request.getRequestedSessionId() + "<br />");
	// out.println(request.getRequestURI() + "<br />");
	// out.println(request.getRequestURL() + "<br />");
	// out.println(request.getServletPath() + "<br />");
	// out.println(request.getSession() + "<br />");
	// out.println(request.getSession(boolean + "<br />");
	// out.println(request.getUserPrincipal() + "<br />");
	// out.println(request.isRequestedSessionIdFromCookie() + "<br />");
	// // out.println(request.isRequestedSessionIdFromUrl() + "<br />");
	// out.println(request.isRequestedSessionIdFromURL() + "<br />");
	// out.println(request.isRequestedSessionIdValid() + "<br />");
	// out.println(request.isUserInRole(String);
	// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
	/**
	 * Handles the HTTP <code>GET</code> method.
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */ 
	protected void doGet(HttpServletRequest request,
	        HttpServletResponse response)
	throws ServletException,  IOException {
		processRequest(request, response);
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */ 
	protected void doPost(HttpServletRequest request,
	        HttpServletResponse response)
	throws ServletException,  IOException {
		processRequest(request, response);
	}

	/**
	 * Returns a short description of the servlet.
	 * @return a String containing servlet description
	 */ 
	public String getServletInfo() {
		return "PigLet the PigIron Servlet: {\"uri\":\"http://pigiron.sourceforge.net\"}";
	}    // </editor-fold>
}
