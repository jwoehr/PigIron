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

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Just stuff left over from fooling around early in the project. Makes a good
 * "You are Lost" page.
 *
 * @author jax
 * @created December 8, 2008
 */
public class ProofOfConcept {

    /**
     * Print out stuff from the Servlet request just for fun.
     *
     * @param request D'oh
     * @param response D'oh
     * @exception ServletException D'oh
     * @exception IOException D'oh
     */
    public static void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Piglet the PigIron Servlet</title>");
        out.println("<script language=\"javascript\" type=\"text/javascript\">");
        out.println("function greeting() {");
        out.println("   window.alert(\"Welcome to slowly evolving PigLet ProofOfConcept\");");
        out.println("   return;");
        out.println("}");
        out.println("</script>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Servlet PigIronServlet at " + request.getContextPath() + "</h1>");
        out.println("You requested " + request.getRequestURI() + ".");
        try {
            out.println("Relativized that is " + URI.create("/piglet/PigIronServlet").relativize(URI.create(request.getRequestURI())));
            out.println("Resolved that is " + new URI(request.getRequestURI()).resolve(new URI("/piglet/PigIronServlet")));
        } catch (URISyntaxException ex) {
            Logger.getLogger(PigIronServlet.class.getName()).log(Level.SEVERE, null, ex);

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
        out.println("Query string = " + request.getQueryString() + "<br />");
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
        out.println("Your request was a  " + request.getMethod() + ".");
        out.println("Your session was a  " + request.getSession() + ".");
        out.println("<p>Pigiron Main says " + com.softwoehr.pigiron.Main.servletString() + "</p>");
        out.println(request.toString());
        out.println("<script language=\"javascript\" type=\"text/javascript\">");
        out.println("greeting();");
        out.println("</script>");
        out.println("</body>");
        out.println("</html>");
        out.close();
    }
}
