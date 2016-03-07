/*
 *  Copyright (c) 2008, 2016 Jack J. Woehr jwoehr@softwoehr.com
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
import java.lang.reflect.Method;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Does the primary work of the Builder web application.
 *
 * @author jax
 * @created December 23, 2008
 */
public class Builder {

    /**
     * Constructor does nothing.
     */
    public Builder() {
    }

    /**
     * Performs the HTTP <code>GET</code> method for Builder, including closing
     * the PrintWriter.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html><head><title>Build a VSMAPI Call</title></head><body><h1>Build a VSMAPI Call</h1>");
        printForm(request, response, out);
        out.println("</body></html>");
        out.close();
    }

    /**
     * Performs the HTTP <code>POST</code> method, including closing the
     * PrintWriter.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String vsmcall_name = request.getParameter("piglet.buildcall.vsmcall");
        try {
            Class c = Class.forName("com.softwoehr.piglet.builder.functions." + vsmcall_name);
            Method m = c.getMethod("doPost", new Class[]{HttpServletRequest.class, HttpServletResponse.class});
            m.invoke(c.newInstance(), new Object[]{request, response});
        } catch (java.lang.ClassNotFoundException ex) {
            Logger.getLogger(Builder.class.getName()).log(Level.SEVERE, null,
                    ex);
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<html><head><title></title></head><body>");
            out.println("Error in finding builder class " + ex.getMessage());
            out.println("</body></html>");
            out.close();
        } catch (java.lang.NoSuchMethodException ex) {
            Logger.getLogger(Builder.class.getName()).log(Level.SEVERE, null,
                    ex);
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<html><head><title></title></head><body>");
            out.println("Error in finding builder class " + ex.getMessage());
            out.println("</body></html>");
            out.close();
        } catch (java.lang.InstantiationException ex) {
            Logger.getLogger(Builder.class.getName()).log(Level.SEVERE, null,
                    ex);
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<html><head><title></title></head><body>");
            out.println("Error in finding builder class " + ex.getMessage());
            out.println("</body></html>");
            out.close();
        } catch (java.lang.IllegalAccessException ex) {
            Logger.getLogger(Builder.class.getName()).log(Level.SEVERE, null,
                    ex);
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<html><head><title></title></head><body>");
            out.println("Error in finding builder class " + ex.getMessage());
            out.println("</body></html>");
            out.close();
        } catch (java.lang.reflect.InvocationTargetException ex) {
            Logger.getLogger(Builder.class.getName()).log(Level.SEVERE, null,
                    ex);
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<html><head><title></title></head><body>");
            out.println("Error in finding builder class " + ex.getMessage());
            out.println("</body></html>");
            out.close();
        }
    }

    /**
     * Dispatches the HTTP <code>PUT</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html><head><title></title></head><body>doPut() not implemented in Builder.java</body></html>");
        out.close();
    }

    /**
     * Compose the form for setting the default Host who is used for VSMAPI
     * calls unless overridden.
     *
     * @param request The servlet request
     * @param response The servlet response
     * @param out output stream, effectively
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public void printForm(HttpServletRequest request,
            HttpServletResponse response, PrintWriter out) throws ServletException, IOException {
        String uid;
        String password;
        String name;
        String dns_name;
        String ip_address;
        int port_number;
        boolean ssl = false;
        User currentUser = BuilderUtil.getDefaultUser(request);
        Host currentHost = BuilderUtil.getDefaultHost(request);
        try {
            uid = currentUser == null ? "_userid_" : currentUser.getUid();
            password = currentUser == null ? "_password_" : currentUser.getPassword();
            name = currentHost == null ? "_name_" : currentHost.getName();
            dns_name = currentHost == null ? "_dns_name_" : currentHost.getDnsName();
            ip_address = currentHost == null ? "_ip_address_" : currentHost.getIpAddress();
            port_number = currentHost == null ? - 1 : currentHost.getPortNumber();
            ssl = currentHost == null ? false : currentHost.getSSL();
        } catch (org.json.JSONException ex) {
            Logger.getLogger(Builder.class.getName()).log(Level.SEVERE, null,
                    ex);
        }
        out.println("<form method=\"post\" action=\"/piglet/BuilderServlet\">");
        BuilderUtil.printBuilderUserHostHeader(request, response, out);
        out.println("<hr /><br>");
        out.println("<b>Select Function</b><br />");
        out.println("<SELECT NAME=\"piglet.buildcall.vsmcall\" size=\"20\">");
        out.println("   <OPTION VALUE=\"AsynchronousNotificationDisableDM\">AsynchronousNotificationDisableDM</OPTION>");
        out.println("   <OPTION VALUE=\"AsynchronousNotificationEnableDM\">AsynchronousNotificationEnableDM</OPTION>");
        out.println("   <OPTION VALUE=\"AsynchronousNotificationQueryDM\">AsynchronousNotificationQueryDM</OPTION>");
        out.println("   <OPTION VALUE=\"AuthorizationListAdd\">AuthorizationListAdd</OPTION>");
        out.println("   <OPTION VALUE=\"AuthorizationListQuery\">AuthorizationListQuery</OPTION>");
        out.println("   <OPTION VALUE=\"AuthorizationListRemove\">AuthorizationListRemove</OPTION>");
        out.println("   <OPTION VALUE=\"CheckAuthentication\" SELECTED>CheckAuthentication</OPTION>");
        out.println("   <OPTION VALUE=\"DirectoryManagerLocalTagDefineDM\">DirectoryManagerLocalTagDefineDM</OPTION>");
        out.println("   <OPTION VALUE=\"DirectoryManagerLocalTagDeleteDM\">DirectoryManagerLocalTagDeleteDM</OPTION>");
        out.println("   <OPTION VALUE=\"DirectoryManagerLocalTagQueryDM\">DirectoryManagerLocalTagQueryDM</OPTION>");
        out.println("   <OPTION VALUE=\"DirectoryManagerLocalTagSetDM\">DirectoryManagerLocalTagSetDM</OPTION>");
        out.println("   <OPTION VALUE=\"DirectoryManagerSearchDM\">DirectoryManagerSearchDM</OPTION>");
        out.println("   <OPTION VALUE=\"DirectoryManagerTaskCancelDM\">DirectoryManagerTaskCancelDM</OPTION>");
        out.println("   <OPTION VALUE=\"EchoParameters\">EchoParameters</OPTION>");
        out.println("   <OPTION VALUE=\"EventStreamAdd\">EventStreamAdd</OPTION>");
        out.println("   <OPTION VALUE=\"EventSubscribe\">EventSubscribe</OPTION>");
        out.println("   <OPTION VALUE=\"EventUnsubscribe\">EventUnsubscribe</OPTION>");
        out.println("   <OPTION VALUE=\"ImageActivate\">ImageActivate</OPTION>");
        out.println("   <OPTION VALUE=\"ImageActiveConfigurationQuery\">ImageActiveConfigurationQuery</OPTION>");
        out.println("   <OPTION VALUE=\"ImageCPUDefine\">ImageCPUDefine</OPTION>");
        out.println("   <OPTION VALUE=\"ImageCPUDefineDM\">ImageCPUDefineDM</OPTION>");
        out.println("   <OPTION VALUE=\"ImageCPUDelete\">ImageCPUDelete</OPTION>");
        out.println("   <OPTION VALUE=\"ImageCPUDeleteDM\">ImageCPUDeleteDM</OPTION>");
        out.println("   <OPTION VALUE=\"ImageCPUQuery\">ImageCPUQuery</OPTION>");
        out.println("   <OPTION VALUE=\"ImageCPUQueryDM\">ImageCPUQueryDM</OPTION>");
        out.println("   <OPTION VALUE=\"ImageCPUSetMaximumDM\">ImageCPUSetMaximumDM</OPTION>");
        out.println("   <OPTION VALUE=\"ImageCreateDM\">ImageCreateDM</OPTION>");
        out.println("   <OPTION VALUE=\"ImageDeactivate\">ImageDeactivate</OPTION>");
        out.println("   <OPTION VALUE=\"ImageDeleteDM\">ImageDeleteDM</OPTION>");
        out.println("   <OPTION VALUE=\"ImageDeviceDedicate\">ImageDeviceDedicate</OPTION>");
        out.println("   <OPTION VALUE=\"ImageDeviceDedicateDM\">ImageDeviceDedicateDM</OPTION>");
        out.println("   <OPTION VALUE=\"ImageDeviceReset\">ImageDeviceReset</OPTION>");
        out.println("   <OPTION VALUE=\"ImageDeviceUndedicate\">ImageDeviceUndedicate</OPTION>");
        out.println("   <OPTION VALUE=\"ImageDeviceUndedicateDM\">ImageDeviceUndedicateDM</OPTION>");
        out.println("   <OPTION VALUE=\"ImageDiskCopy\">ImageDiskCopy</OPTION>");
        out.println("   <OPTION VALUE=\"ImageDiskCopyDM\">ImageDiskCopyDM</OPTION>");
        out.println("   <OPTION VALUE=\"ImageDiskCreate\">ImageDiskCreate</OPTION>");
        out.println("   <OPTION VALUE=\"ImageDiskCreateDM\">ImageDiskCreateDM</OPTION>");
        out.println("   <OPTION VALUE=\"ImageDiskDelete\">ImageDiskDelete</OPTION>");
        out.println("   <OPTION VALUE=\"ImageDiskDeleteDM\">ImageDiskDeleteDM</OPTION>");
        out.println("   <OPTION VALUE=\"ImageDiskShare\">ImageDiskShare</OPTION>");
        out.println("   <OPTION VALUE=\"ImageDiskShareDM\">ImageDiskShareDM</OPTION>");
        out.println("   <OPTION VALUE=\"ImageDiskUnshare\">ImageDiskUnshare</OPTION>");
        out.println("   <OPTION VALUE=\"ImageDiskUnshareDM\">ImageDiskUnshareDM</OPTION>");
        out.println("   <OPTION VALUE=\"ImageIPLDeleteDM\">ImageIPLDeleteDM</OPTION>");
        out.println("   <OPTION VALUE=\"ImageIPLQueryDM\">ImageIPLQueryDM</OPTION>");
        out.println("   <OPTION VALUE=\"ImageIPLSetDM\">ImageIPLSetDM</OPTION>");
        out.println("   <OPTION VALUE=\"ImageLockDM\">ImageLockDM</OPTION>");
        out.println("   <OPTION VALUE=\"ImageMDISKLinkQuery\">ImageMDISKLinkQuery</OPTION>");
        out.println("   <OPTION VALUE=\"ImageNameQueryDM\">ImageNameQueryDM</OPTION>");
        out.println("   <OPTION VALUE=\"ImagePasswordSetDM\">ImagePasswordSetDM</OPTION>");
        out.println("   <OPTION VALUE=\"ImageQueryActivateTime\">ImageQueryActivateTime</OPTION>");
        out.println("   <OPTION VALUE=\"ImageQueryDM\">ImageQueryDM</OPTION>");
        out.println("   <OPTION VALUE=\"ImageRecycle\">ImageRecycle</OPTION>");
        out.println("   <OPTION VALUE=\"ImageReplaceDM\">ImageReplaceDM</OPTION>");
        out.println("   <OPTION VALUE=\"ImageSCSICharacteristicsDefineDM\">ImageSCSICharacteristicsDefineDM</OPTION>");
        out.println("   <OPTION VALUE=\"ImageSCSICharacteristicsQueryDM\">ImageSCSICharacteristicsQueryDM</OPTION>");
        out.println("   <OPTION VALUE=\"ImageStatusQuery\">ImageStatusQuery</OPTION>");
        out.println("   <OPTION VALUE=\"ImageUnlockDM\">ImageUnlockDM</OPTION>");
        out.println("   <OPTION VALUE=\"ImageVolumeAdd\">ImageVolumeAdd</OPTION>");
        out.println("   <OPTION VALUE=\"ImageVolumeDelete\">ImageVolumeDelete</OPTION>");
        out.println("   <OPTION VALUE=\"ImageVolumeSpaceDefineDM\">ImageVolumeSpaceDefineDM</OPTION>");
        out.println("   <OPTION VALUE=\"ImageVolumeSpaceQueryDM\">ImageVolumeSpaceQueryDM</OPTION>");
        out.println("   <OPTION VALUE=\"ImageVolumeSpaceRemoveDM\">ImageVolumeSpaceRemoveDM</OPTION>");
        out.println("   <OPTION VALUE=\"NameListAdd\">NameListAdd</OPTION>");
        out.println("   <OPTION VALUE=\"NameListDestroy\">NameListDestroy</OPTION>");
        out.println("   <OPTION VALUE=\"NameListQuery\">NameListQuery</OPTION>");
        out.println("   <OPTION VALUE=\"NameListRemove\">NameListRemove</OPTION>");
        out.println("   <OPTION VALUE=\"ProfileCreateDM\">ProfileCreateDM</OPTION>");
        out.println("   <OPTION VALUE=\"ProfileDeleteDM\">ProfileDeleteDM</OPTION>");
        out.println("   <OPTION VALUE=\"ProfileLockDM\">ProfileLockDM</OPTION>");
        out.println("   <OPTION VALUE=\"ProfileQueryDM\">ProfileQueryDM</OPTION>");
        out.println("   <OPTION VALUE=\"ProfileReplaceDM\">ProfileReplaceDM</OPTION>");
        out.println("   <OPTION VALUE=\"ProfileUnlockDM\">ProfileUnlockDM</OPTION>");
        out.println("   <OPTION VALUE=\"PrototypeCreateDM\">PrototypeCreateDM</OPTION>");
        out.println("   <OPTION VALUE=\"PrototypeDeleteDM\">PrototypeDeleteDM</OPTION>");
        out.println("   <OPTION VALUE=\"PrototypeNameQueryDM\">PrototypeNameQueryDM</OPTION>");
        out.println("   <OPTION VALUE=\"PrototypeQueryDM\">PrototypeQueryDM</OPTION>");
        out.println("   <OPTION VALUE=\"PrototypeReplaceDM\">PrototypeReplaceDM</OPTION>");
        out.println("   <OPTION VALUE=\"QueryAllDM\">QueryAllDM</OPTION>");
        out.println("   <OPTION VALUE=\"QueryAPIFunctionalLevel\">QueryAPIFunctionalLevel</OPTION>");
        out.println("   <OPTION VALUE=\"QueryAsynchronousOperationDM\">QueryAsynchronousOperationDM</OPTION>");
        out.println("   <OPTION VALUE=\"QueryDirectoryManagerLevelDM\">QueryDirectoryManagerLevelDM</OPTION>");
        out.println("   <OPTION VALUE=\"ResponseRecovery\">ResponseRecovery</OPTION>");
        out.println("   <OPTION VALUE=\"SharedMemoryAccessAddDM\">SharedMemoryAccessAddDM</OPTION>");
        out.println("   <OPTION VALUE=\"SharedMemoryAccessQueryDM\">SharedMemoryAccessQueryDM</OPTION>");
        out.println("   <OPTION VALUE=\"SharedMemoryAccessRemoveDM\">SharedMemoryAccessRemoveDM</OPTION>");
        out.println("   <OPTION VALUE=\"SharedMemoryCreate\">SharedMemoryCreate</OPTION>");
        out.println("   <OPTION VALUE=\"SharedMemoryDelete\">SharedMemoryDelete</OPTION>");
        out.println("   <OPTION VALUE=\"SharedMemoryQuery\">SharedMemoryQuery</OPTION>");
        out.println("   <OPTION VALUE=\"SharedMemoryReplace\">SharedMemoryReplace</OPTION>");
        out.println("   <OPTION VALUE=\"SMAPIStatusCapture\">SMAPIStatusCapture</OPTION>");
        out.println("   <OPTION VALUE=\"StaticImageChangesActivateDM\">StaticImageChangesActivateDM</OPTION>");
        out.println("   <OPTION VALUE=\"StaticImageChangesDeactivateDM\">StaticImageChangesDeactivateDM</OPTION>");
        out.println("   <OPTION VALUE=\"StaticImageChangesImmediateDM\">StaticImageChangesImmediateDM</OPTION>");
        out.println("   <OPTION VALUE=\"VMRMConfigurationQuery\">VMRMConfigurationQuery</OPTION>");
        out.println("   <OPTION VALUE=\"VMRMConfigurationUpdate\">VMRMConfigurationUpdate</OPTION>");
        out.println("   <OPTION VALUE=\"VMRMMeasurementQuery\">VMRMMeasurementQuery</OPTION>");
        out.println("   <OPTION VALUE=\"VirtualChannelConnectionCreate\">VirtualChannelConnectionCreate</OPTION>");
        out.println("   <OPTION VALUE=\"VirtualChannelConnectionCreateDM\">VirtualChannelConnectionCreateDM</OPTION>");
        out.println("   <OPTION VALUE=\"VirtualChannelConnectionDelete\">VirtualChannelConnectionDelete</OPTION>");
        out.println("   <OPTION VALUE=\"VirtualChannelConnectionDeleteDM\">VirtualChannelConnectionDeleteDM</OPTION>");
        out.println("   <OPTION VALUE=\"VirtualNetworkAdapterConnectLAN\">VirtualNetworkAdapterConnectLAN</OPTION>");
        out.println("   <OPTION VALUE=\"VirtualNetworkAdapterConnectLANDM\">VirtualNetworkAdapterConnectLANDM</OPTION>");
        out.println("   <OPTION VALUE=\"VirtualNetworkAdapterConnectVswitch\">VirtualNetworkAdapterConnectVswitch</OPTION>");
        out.println("   <OPTION VALUE=\"VirtualNetworkAdapterConnectVswitchDM\">VirtualNetworkAdapterConnectVswitchDM</OPTION>");
        out.println("   <OPTION VALUE=\"VirtualNetworkAdapterCreate\">VirtualNetworkAdapterCreate</OPTION>");
        out.println("   <OPTION VALUE=\"VirtualNetworkAdapterCreateDM\">VirtualNetworkAdapterCreateDM</OPTION>");
        out.println("   <OPTION VALUE=\"VirtualNetworkAdapterDelete\">VirtualNetworkAdapterDelete</OPTION>");
        out.println("   <OPTION VALUE=\"VirtualNetworkAdapterDeleteDM\">VirtualNetworkAdapterDeleteDM</OPTION>");
        out.println("   <OPTION VALUE=\"VirtualNetworkAdapterDisconnect\">VirtualNetworkAdapterDisconnect</OPTION>");
        out.println("   <OPTION VALUE=\"VirtualNetworkAdapterDisconnectDM\">VirtualNetworkAdapterDisconnectDM</OPTION>");
        out.println("   <OPTION VALUE=\"VirtualNetworkAdapterQuery\">VirtualNetworkAdapterQuery</OPTION>");
        out.println("   <OPTION VALUE=\"VirtualNetworkLANAccess\">VirtualNetworkLANAccess</OPTION>");
        out.println("   <OPTION VALUE=\"VirtualNetworkLANAccessQuery\">VirtualNetworkLANAccessQuery</OPTION>");
        out.println("   <OPTION VALUE=\"VirtualNetworkLANCreate\">VirtualNetworkLANCreate</OPTION>");
        out.println("   <OPTION VALUE=\"VirtualNetworkLANDelete\">VirtualNetworkLANDelete</OPTION>");
        out.println("   <OPTION VALUE=\"VirtualNetworkLANQuery\">VirtualNetworkLANQuery</OPTION>");
        out.println("   <OPTION VALUE=\"VirtualNetworkVswitchCreate\">VirtualNetworkVswitchCreate</OPTION>");
        out.println("   <OPTION VALUE=\"VirtualNetworkVswitchDelete\">VirtualNetworkVswitchDelete</OPTION>");
        out.println("   <OPTION VALUE=\"VirtualNetworkVswitchQuery\">VirtualNetworkVswitchQuery</OPTION>");
        out.println("   <OPTION VALUE=\"VirtualNetworkVswitchSet\">VirtualNetworkVswitchSet</OPTION>");
        out.println("</SELECT>");
        out.println("<input value=\"Next\" type=\"submit\">");
        out.println("<INPUT TYPE=HIDDEN NAME=\"piglet.buildcall.state\" value=\"select_vsmcall\">");
        out.println("</form>");
        BuilderUtil.printTrailer(request, response, out);
    }
}
