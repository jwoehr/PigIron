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
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;

/**
 * Static methods used by the Builder web application.
 *
 * @author     jax
 * @created    December 23, 2008
 */
public class BuilderUtil {

    /**
     * Class has only static members
     */ 
    private BuilderUtil() { }

    /**
     *  Sets the default_host attribute of the Builder in the caller's Session
     *
     * @param  request  The http request
     * @param  host     The new defaultHost value
     */ 
    public static void setDefaultHost(HttpServletRequest request, Host host) {
        request.getSession(true).setAttribute("default_host", host);
    }

    /**
     *  Gets the default_host attribute of the Builder in the caller's Session
     *
     * @param  request  The http request
     * @return          The defaultHost value
     */ 
    public static Host getDefaultHost(HttpServletRequest request) {
        Host host = null;
        Object obj = request.getSession(true).getAttribute("default_host");
        try {
            host = obj != null ? Host .class.cast(obj) : new Host();
        } catch (org.json.JSONException ex) {
            Logger.getLogger(BuilderUtil .class.getName()).log(Level.SEVERE,
                     null, ex);
        }
        return host;
    }

    /**
     *  Sets the default_user attribute of the Builder in the caller's Session
     *
     * @param  request  The http request
     * @param  user     The new defaultUser value
     */ 
    public static void setDefaultUser(HttpServletRequest request, User user) {
        request.getSession(true).setAttribute("default_user", user);
    }

    /**
     *  Gets the default_user attribute of the Builder in the caller's Session
     *
     * @param  request  The http request
     * @return          The defaultUser value
     */ 
    public static User getDefaultUser(HttpServletRequest request) {
        User user = null;
        Object obj = request.getSession(true).getAttribute("default_user");
        try {
            user = obj != null ? User .class.cast(obj) : new User();
        } catch (org.json.JSONException ex) {
            Logger.getLogger(BuilderUtil .class.getName()).log(Level.SEVERE,
                     null, ex);
        }
        return user;
    }

    /**
     *  Description of the Method
     *
     * @param  request                     Description of the Parameter
     * @param  response                    Description of the Parameter
     * @return                             Description of the Return Value
     * @exception  org.json.JSONException  Description of the Exception
     */ 
    public static User fromUserHeader(HttpServletRequest request, HttpServletResponse response) throws org.json.JSONException {
        User user = new User();
        String uid = request.getParameter("uid");
        String password = request.getParameter("password");
        user.setUid(uid);
        user.setPassword(password);
        return user;
    }

    /**
     *  Description of the Method
     *
     * @param  request                     Description of the Parameter
     * @param  response                    Description of the Parameter
     * @return                             Description of the Return Value
     * @exception  org.json.JSONException  Description of the Exception
     */ 
    public static Host fromHostHeader(HttpServletRequest request, HttpServletResponse response) throws org.json.JSONException {
        Host host = new Host();
        String name = request.getParameter("name");
        String dns_name = request.getParameter("dns_name");
        String ip_address = request.getParameter("ip_address");
        String string_port_number = request.getParameter("port_number");
        int port_number = - 1;
        try {
            port_number = string_port_number == null ? - 1 : Integer.valueOf(string_port_number).intValue();
        } catch (NumberFormatException ex) {
            port_number = - 1;
        }
        boolean ssl = request.getParameter("ssl") == null ? false : true;
        host.setName(name);
        host.setDnsName(dns_name);
        host.setIpAddress(ip_address);
        host.setPortNumber(port_number);
        host.setSSL(ssl);
        return host;
    }

    /**
     *  Print to an extent writer the portion of the view pertaining to the
     * User and Host for the VSMAPI call. Does not print the HTML header.
     * Doesn't create the form. Just fetches session data and displays it
     * in the edit widgets.
     *
     * @param  request            servlet request
     * @param  response           servlet response
     * @param  out                print writer for the servlet's output
     * @throws  ServletException  if a servlet-specific error occurs
     * @throws  IOException       if an I/O error occurs
     */ 
    public static void printBuilderUserHostHeader(HttpServletRequest request, HttpServletResponse response, PrintWriter out) throws ServletException, IOException {
        String uid = request.getParameter("uid");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String dns_name = request.getParameter("dns_name");
        String ip_address = request.getParameter("ip_address");
        String string_port_number = request.getParameter("port_number");
        int port_number = - 1;
        try {
            port_number = string_port_number == null ? - 1 : Integer.valueOf(string_port_number).intValue();
        } catch (NumberFormatException ex) {
            port_number = - 1;
        }
        boolean ssl = false;
        User defaultUser = BuilderUtil.getDefaultUser(request);
        Host defaultHost = BuilderUtil.getDefaultHost(request);
        try {
            uid = uid != null ? uid : (defaultUser == null ? "_userid_" : defaultUser.getUid());
            password = password != null ? password : (defaultUser == null ? "_password_" : defaultUser.getPassword());
            name = name != null ? name : (defaultHost == null ? "_name_" : defaultHost.getName());
            dns_name = dns_name != null ? dns_name : (defaultHost == null ? "_dns_name_" : defaultHost.getDnsName());
            ip_address = ip_address != null ? ip_address : (defaultHost == null ? "_ip_address_" : defaultHost.getIpAddress());
            port_number = port_number != - 1 ? port_number : (defaultHost == null ? - 1 : defaultHost.getPortNumber());
            ssl = request.getParameter("ssl") == null ? (defaultHost == null ? false : defaultHost.getSSL()) : true;
        } catch (org.json.JSONException ex) {
            Logger.getLogger(Builder .class.getName()).log(Level.SEVERE, null, ex);
        }
        out.println("<table style=\"text-align: left; width: 100%;\" border=\"0\" cellpadding=\"2\"");
        out.println(" cellspacing=\"2\">");
        out.println("  <tbody>");
        out.println("    <tr>");
        out.println("      <th style=\"vertical-align: top;\">User<br>");
        out.println("      </th>");
        out.println("      <th style=\"vertical-align: top;\">Host<br>");
        out.println("      </th>");
        out.println("    </tr>");
        out.println("    <tr>");
        out.println("      <td style=\"vertical-align: top;\">");
        out.println("      <table style=\"text-align: left; width: 100%;\" border=\"0\"");
        out.println(" cellpadding=\"2\" cellspacing=\"2\">");
        out.println("        <tbody>");
        out.println("          <tr>");
        out.println("            <td style=\"vertical-align: top;\">");
        out.println("<input type=\"text\"  name=\"uid\" value = \"" + uid + "\"/>");
        out.println("User ID<br>");
        out.println("            </td>");
        out.println("          </tr>");
        out.println("         <tr>");
        out.println("            <td style=\"vertical-align: top;\">");
        out.println("<input type=\"password\"  name=\"password\" value = \"" + password + "\"/>");
        out.println("Password<br>");
        out.println("            </td>");
        out.println("          </tr>");
        out.println("        </tbody>");
        out.println("      </table>");
        out.println("     <br>");
        out.println("      </td>");
        out.println("      <td style=\"vertical-align: top;\">");
        out.println("      <table style=\"text-align: left; width: 100%;\" border=\"0\"");
        out.println(" cellpadding=\"2\" cellspacing=\"2\">");
        out.println("        <tbody>");
        out.println("          <tr>");
        out.println("            <td style=\"vertical-align: top;\">");
        out.println("<input type=\"text\"  name=\"name\" value = \"" + name + "\"/>");
        out.println("Host Name (only used symbolically)<br>");
        out.println("            </td>");
        out.println("          </tr>");
        out.println("          <tr>");
        out.println("            <td style=\"vertical-align: top;\">");
        out.println("<input type=\"text\"  name=\"dns_name\" value = \"" + dns_name + "\"/>");
        out.println("DNS Name (lookup name -- if present, <tt>IP Address</tt> field is ignored)<br>");
        out.println("            </td>");
        out.println("          </tr>");
        out.println("          <tr>");
        out.println("            <td style=\"vertical-align: top;\">");
        out.println("<input type=\"text\"  name=\"ip_address\" value = \"" + ip_address + "\"/>");
        out.println("IP Address (ignored if <tt>DNS Name</tt> is present)<br>");
        out.println("            </td>");
        out.println("          </tr>");
        out.println("          <tr>");
        out.println("            <td style=\"vertical-align: top;\">");
        out.println("<input type=\"text\"  name=\"port_number\" value = \"" + port_number + "\"/>");
        out.println("Port Number<br>");
        out.println("            </td>");
        out.println("          </tr>");
        out.println("          <tr>");
        out.println("            <td style=\"vertical-align: top;\">");
        out.println("<input type=\"checkbox\"  name=\"ssl\"" + (ssl ? "checked" : "") + "\"/>");
        out.println("Use SSL<br>");
        out.println("            </td>");
        out.println("          </tr>");
        out.println("        </tbody>");
        out.println("      </table>");
        out.println("      <br>");
        out.println("      </td>");
        out.println("    </tr>");
        out.println("  </tbody>");
        out.println("</table>");
    }

    /**
     *  Print to an extent writer the portion of the view pertaining to the
     * the Do It! and Show JSON buttons along with setting the hidden fields.
     * Doesn't create the form. 
     *
     * @param  request            servlet request
     * @param  response           servlet response
     * @param  out                print writer for the servlet's output
     * @throws  ServletException  if a servlet-specific error occurs
     * @throws  IOException       if an I/O error occurs
     */ 
    public static void printDoItButtonPanel(HttpServletRequest request, HttpServletResponse response, PrintWriter out) throws ServletException, IOException {
	out.println("<input name=\"button_pressed\" value=\"Do it!\" type=\"submit\">");
	out.println("<input name=\"button_pressed\" value=\"Show JSON\" type=\"submit\">");
        out.println("<INPUT TYPE=HIDDEN NAME=\"piglet.buildcall.vsmcall\" value=\"" + request.getParameter("piglet.buildcall.vsmcall") + "\">");
        out.println("<INPUT TYPE=HIDDEN NAME=\"piglet.buildcall.state\" value=\"do_it\">");
    }
    
    /**
     *  Print to an extent writer a little trailer with pretty piggy.
     * Doesn't create the form. 
     *
     * @param  request            servlet request
     * @param  response           servlet response
     * @param  out                print writer for the servlet's output
     * @throws  ServletException  if a servlet-specific error occurs
     * @throws  IOException       if an I/O error occurs
     */ 
    public static void printTrailer(HttpServletRequest request, HttpServletResponse response, PrintWriter out) throws ServletException, IOException {
        out.println("<br />");
	out.println("<img src=\"images/pig_15.gif\" align=\"center\">");
    }

    /*
     *  // Really not necessary in view of ServletRequest.getParameter(String).
     *  public static String flatten (String [] stringArray) {
     *  StringBuffer sb = new StringBuffer();
     *  for (int i = 0; i <stringArray.length; i++) {
     *  sb.append(stringArray[i]);
     *  }
     *  return sb.toString();
     *  }
     */ 
}

