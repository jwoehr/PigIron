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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import org.json.JSONException;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Map;

/**
 * Does the primary work of the Builder web application.
 *
 * @author     jax
 * @created    December 23, 2008
 */

public class Builder {
    /**
     * Constructor does nothing.
     */ 
    public Builder() { }

    /**
     * Performs the HTTP <code>GET</code> method for Builder, including
     * closing the PrintWriter.
     *
     * @param  request            servlet request
     * @param  response           servlet response
     * @throws  ServletException  if a servlet-specific error occurs
     * @throws  IOException       if an I/O error occurs
     */ 
    public void doGet(HttpServletRequest request,
             HttpServletResponse response) throws ServletException,  IOException {
        printForm(request, response);
        response.getWriter().close();
    }

    /**
     * Performs the HTTP <code>POST</code> method, including closing
     * the PrintWriter (actually closed by the builder.function VSMCall building
     * methods to which it is dispatched).
     *
     * @param  request            servlet request
     * @param  response           servlet response
     * @throws  ServletException  if a servlet-specific error occurs
     * @throws  IOException       if an I/O error occurs
     */ 
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,  IOException {
        String vsmcall_name = request.getParameter("piglet.buildcall.vsmcall");
        try {
            Class c = Class.forName("com.softwoehr.piglet.builder.functions." + vsmcall_name);
            Method m = c.getMethod("doPost", new Class [] { HttpServletRequest .class , HttpServletResponse .class} );
            m.invoke(c.newInstance(), new Object [] { request,response} );
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
     * @param  request            servlet request
     * @param  response           servlet response
     * @throws  ServletException  if a servlet-specific error occurs
     * @throws  IOException       if an I/O error occurs
     */ 
    public void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException,  IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html><head><title></title></head><body>doPut() not implemented in Builder.java</body></html>"); 
        out.close();
    }

    /**
     *  Compose the form for setting the default Host who is used for VSMAPI
     * calls unless overridden.
     *
     * @param  request               The servlet request
     * @param  response              The servlet response
     * @throws  ServletException  if a servlet-specific error occurs
     * @throws  IOException       if an I/O error occurs
     */ 
    public void printForm(HttpServletRequest request,
             HttpServletResponse response) throws ServletException,  IOException {
        String uid = null;
        String password = null;
        String name = null;
        String dns_name = null;
        String ip_address = null;
        int port_number = - 1;
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
            Logger.getLogger(Builder .class.getName()).log(Level.SEVERE, null,
                     ex);
        }
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html><head><title></title></head><body><h1>Build a VSMAPI Call</h1>");
        out.println("<form method=\"post\" action=\"/piglet/BuilderServlet\">");
	BuilderUtil.printBuilderUserHostHeader(request, response, out);
        out.println("<hr /><br>");
        out.println("<b>Select Function</b><br />");
        out.println("<SELECT NAME=\"piglet.buildcall.vsmcall\" size=\"20\">");
        out.println("   <OPTION VALUE=\"AsynchronousNotificationDisableDM\">AsynchronousNotificationDisableDM");
        out.println("   <OPTION VALUE=\"AsynchronousNotificationEnableDM\">AsynchronousNotificationEnableDM");
        out.println("   <OPTION VALUE=\"AsynchronousNotificationQueryDM\">AsynchronousNotificationQueryDM");
        out.println("   <OPTION VALUE=\"AuthorizationListAdd\">AuthorizationListAdd");
        out.println("   <OPTION VALUE=\"AuthorizationListQuery\">AuthorizationListQuery");
        out.println("   <OPTION VALUE=\"AuthorizationListRemove\">AuthorizationListRemove");
        out.println("   <OPTION VALUE=\"CheckAuthentication\">CheckAuthentication");
        out.println("   <OPTION VALUE=\"DirectoryManagerLocalTagDefineDM\">DirectoryManagerLocalTagDefineDM");
        out.println("   <OPTION VALUE=\"DirectoryManagerLocalTagDeleteDM\">DirectoryManagerLocalTagDeleteDM");
        out.println("   <OPTION VALUE=\"DirectoryManagerLocalTagQueryDM\">DirectoryManagerLocalTagQueryDM");
        out.println("   <OPTION VALUE=\"DirectoryManagerLocalTagSetDM\">DirectoryManagerLocalTagSetDM");
        out.println("   <OPTION VALUE=\"DirectoryManagerSearchDM\">DirectoryManagerSearchDM");
        out.println("   <OPTION VALUE=\"DirectoryManagerTaskCancelDM\">DirectoryManagerTaskCancelDM");
        out.println("   <OPTION VALUE=\"ImageActivate\">ImageActivate");
        out.println("   <OPTION VALUE=\"ImageActiveConfigurationQuery\">ImageActiveConfigurationQuery");
        out.println("   <OPTION VALUE=\"ImageCPUDefine\">ImageCPUDefine");
        out.println("   <OPTION VALUE=\"ImageCPUDefineDM\">ImageCPUDefineDM");
        out.println("   <OPTION VALUE=\"ImageCPUDelete\">ImageCPUDelete");
        out.println("   <OPTION VALUE=\"ImageCPUDeleteDM\">ImageCPUDeleteDM");
        out.println("   <OPTION VALUE=\"ImageCPUQuery\">ImageCPUQuery");
        out.println("   <OPTION VALUE=\"ImageCPUQueryDM\">ImageCPUQueryDM");
        out.println("   <OPTION VALUE=\"ImageCPUSetMaximumDM\">ImageCPUSetMaximumDM");
        out.println("   <OPTION VALUE=\"ImageCreateDM\">ImageCreateDM");
        out.println("   <OPTION VALUE=\"ImageDeactivate\">ImageDeactivate");
        out.println("   <OPTION VALUE=\"ImageDeleteDM\">ImageDeleteDM");
        out.println("   <OPTION VALUE=\"ImageDeviceDedicate\">ImageDeviceDedicate");
        out.println("   <OPTION VALUE=\"ImageDeviceDedicateDM\">ImageDeviceDedicateDM");
        out.println("   <OPTION VALUE=\"ImageDeviceReset\">ImageDeviceReset");
        out.println("   <OPTION VALUE=\"ImageDeviceUndedicate\">ImageDeviceUndedicate");
        out.println("   <OPTION VALUE=\"ImageDeviceUndedicateDM\">ImageDeviceUndedicateDM");
        out.println("   <OPTION VALUE=\"ImageDiskCopy\">ImageDiskCopy");
        out.println("   <OPTION VALUE=\"ImageDiskCopyDM\">ImageDiskCopyDM");
        out.println("   <OPTION VALUE=\"ImageDiskCreate\">ImageDiskCreate");
        out.println("   <OPTION VALUE=\"ImageDiskCreateDM\">ImageDiskCreateDM");
        out.println("   <OPTION VALUE=\"ImageDiskDelete\">ImageDiskDelete");
        out.println("   <OPTION VALUE=\"ImageDiskDeleteDM\">ImageDiskDeleteDM");
        out.println("   <OPTION VALUE=\"ImageDiskShare\">ImageDiskShare");
        out.println("   <OPTION VALUE=\"ImageDiskShareDM\">ImageDiskShareDM");
        out.println("   <OPTION VALUE=\"ImageDiskUnshare\">ImageDiskUnshare");
        out.println("   <OPTION VALUE=\"ImageDiskUnshareDM\">ImageDiskUnshareDM");
        out.println("   <OPTION VALUE=\"ImageIPLDeleteDM\">ImageIPLDeleteDM");
        out.println("   <OPTION VALUE=\"ImageIPLQueryDM\">ImageIPLQueryDM");
        out.println("   <OPTION VALUE=\"ImageIPLSetDM\">ImageIPLSetDM");
        out.println("   <OPTION VALUE=\"ImageLockDM\">ImageLockDM");
        out.println("   <OPTION VALUE=\"ImageNameQueryDM\">ImageNameQueryDM");
        out.println("   <OPTION VALUE=\"ImagePasswordSetDM\">ImagePasswordSetDM");
        out.println("   <OPTION VALUE=\"ImageQueryActivateTime\">ImageQueryActivateTime");
        out.println("   <OPTION VALUE=\"ImageQueryDM\">ImageQueryDM");
        out.println("   <OPTION VALUE=\"ImageRecycle\">ImageRecycle");
        out.println("   <OPTION VALUE=\"ImageReplaceDM\">ImageReplaceDM");
        out.println("   <OPTION VALUE=\"ImageSCSICharacteristicsDefineDM\">ImageSCSICharacteristicsDefineDM");
        out.println("   <OPTION VALUE=\"ImageSCSICharacteristicsQueryDM\">ImageSCSICharacteristicsQueryDM");
        out.println("   <OPTION VALUE=\"ImageStatusQuery\">ImageStatusQuery");
        out.println("   <OPTION VALUE=\"ImageUnlockDM\">ImageUnlockDM");
        out.println("   <OPTION VALUE=\"ImageVolumeAdd\">ImageVolumeAdd");
        out.println("   <OPTION VALUE=\"ImageVolumeDelete\">ImageVolumeDelete");
        out.println("   <OPTION VALUE=\"ImageVolumeSpaceDefineDM\">ImageVolumeSpaceDefineDM");
        out.println("   <OPTION VALUE=\"ImageVolumeSpaceQueryDM\">ImageVolumeSpaceQueryDM");
        out.println("   <OPTION VALUE=\"ImageVolumeSpaceRemoveDM\">ImageVolumeSpaceRemoveDM");
        out.println("   <OPTION VALUE=\"NameListAdd\">NameListAdd");
        out.println("   <OPTION VALUE=\"NameListDestroy\">NameListDestroy");
        out.println("   <OPTION VALUE=\"NameListQuery\">NameListQuery");
        out.println("   <OPTION VALUE=\"NameListRemove\">NameListRemove");
        out.println("   <OPTION VALUE=\"ProfileCreateDM\">ProfileCreateDM");
        out.println("   <OPTION VALUE=\"ProfileDeleteDM\">ProfileDeleteDM");
        out.println("   <OPTION VALUE=\"ProfileLockDM\">ProfileLockDM");
        out.println("   <OPTION VALUE=\"ProfileQueryDM\">ProfileQueryDM");
        out.println("   <OPTION VALUE=\"ProfileReplaceDM\">ProfileReplaceDM");
        out.println("   <OPTION VALUE=\"ProfileUnlockDM\">ProfileUnlockDM");
        out.println("   <OPTION VALUE=\"PrototypeCreateDM\">PrototypeCreateDM");
        out.println("   <OPTION VALUE=\"PrototypeDeleteDM\">PrototypeDeleteDM");
        out.println("   <OPTION VALUE=\"PrototypeNameQueryDM\">PrototypeNameQueryDM");
        out.println("   <OPTION VALUE=\"PrototypeQueryDM\">PrototypeQueryDM");
        out.println("   <OPTION VALUE=\"PrototypeReplaceDM\">PrototypeReplaceDM");
        out.println("   <OPTION VALUE=\"QueryAPIFunctionalLevel\">QueryAPIFunctionalLevel");
        out.println("   <OPTION VALUE=\"QueryAsynchronousOperationDM\">QueryAsynchronousOperationDM");
        out.println("   <OPTION VALUE=\"QueryDirectoryManagerLevelDM\">QueryDirectoryManagerLevelDM");
        out.println("   <OPTION VALUE=\"SharedMemoryAccessAddDM\">SharedMemoryAccessAddDM");
        out.println("   <OPTION VALUE=\"SharedMemoryAccessQueryDM\">SharedMemoryAccessQueryDM");
        out.println("   <OPTION VALUE=\"SharedMemoryAccessRemoveDM\">SharedMemoryAccessRemoveDM");
        out.println("   <OPTION VALUE=\"SharedMemoryCreate\">SharedMemoryCreate");
        out.println("   <OPTION VALUE=\"SharedMemoryDelete\">SharedMemoryDelete");
        out.println("   <OPTION VALUE=\"SharedMemoryQuery\">SharedMemoryQuery");
        out.println("   <OPTION VALUE=\"SharedMemoryReplace\">SharedMemoryReplace");
        out.println("   <OPTION VALUE=\"StaticImageChangesActivateDM\">StaticImageChangesActivateDM");
        out.println("   <OPTION VALUE=\"StaticImageChangesDeactivateDM\">StaticImageChangesDeactivateDM");
        out.println("   <OPTION VALUE=\"StaticImageChangesImmediateDM\">StaticImageChangesImmediateDM");
        out.println("   <OPTION VALUE=\"VMRMConfigurationQuery\">VMRMConfigurationQuery");
        out.println("   <OPTION VALUE=\"VMRMConfigurationUpdate\">VMRMConfigurationUpdate");
        out.println("   <OPTION VALUE=\"VMRMMeasurementQuery\">VMRMMeasurementQuery");
        out.println("   <OPTION VALUE=\"VirtualChannelConnectionCreate\">VirtualChannelConnectionCreate");
        out.println("   <OPTION VALUE=\"VirtualChannelConnectionCreateDM\">VirtualChannelConnectionCreateDM");
        out.println("   <OPTION VALUE=\"VirtualChannelConnectionDelete\">VirtualChannelConnectionDelete");
        out.println("   <OPTION VALUE=\"VirtualChannelConnectionDeleteDM\">VirtualChannelConnectionDeleteDM");
        out.println("   <OPTION VALUE=\"VirtualNetworkAdapterConnectLAN\">VirtualNetworkAdapterConnectLAN");
        out.println("   <OPTION VALUE=\"VirtualNetworkAdapterConnectLANDM\">VirtualNetworkAdapterConnectLANDM");
        out.println("   <OPTION VALUE=\"VirtualNetworkAdapterConnectVswitch\">VirtualNetworkAdapterConnectVswitch");
        out.println("   <OPTION VALUE=\"VirtualNetworkAdapterConnectVswitchDM\">VirtualNetworkAdapterConnectVswitchDM");
        out.println("   <OPTION VALUE=\"VirtualNetworkAdapterCreate\">VirtualNetworkAdapterCreate");
        out.println("   <OPTION VALUE=\"VirtualNetworkAdapterCreateDM\">VirtualNetworkAdapterCreateDM");
        out.println("   <OPTION VALUE=\"VirtualNetworkAdapterDelete\">VirtualNetworkAdapterDelete");
        out.println("   <OPTION VALUE=\"VirtualNetworkAdapterDeleteDM\">VirtualNetworkAdapterDeleteDM");
        out.println("   <OPTION VALUE=\"VirtualNetworkAdapterDisconnect\">VirtualNetworkAdapterDisconnect");
        out.println("   <OPTION VALUE=\"VirtualNetworkAdapterDisconnectDM\">VirtualNetworkAdapterDisconnectDM");
        out.println("   <OPTION VALUE=\"VirtualNetworkAdapterQuery\">VirtualNetworkAdapterQuery");
        out.println("   <OPTION VALUE=\"VirtualNetworkLANAccess\">VirtualNetworkLANAccess");
        out.println("   <OPTION VALUE=\"VirtualNetworkLANAccessQuery\">VirtualNetworkLANAccessQuery");
        out.println("   <OPTION VALUE=\"VirtualNetworkLANCreate\">VirtualNetworkLANCreate");
        out.println("   <OPTION VALUE=\"VirtualNetworkLANDelete\">VirtualNetworkLANDelete");
        out.println("   <OPTION VALUE=\"VirtualNetworkLANQuery\">VirtualNetworkLANQuery");
        out.println("   <OPTION VALUE=\"VirtualNetworkVswitchCreate\">VirtualNetworkVswitchCreate");
        out.println("   <OPTION VALUE=\"VirtualNetworkVswitchDelete\">VirtualNetworkVswitchDelete");
        out.println("   <OPTION VALUE=\"VirtualNetworkVswitchQuery\">VirtualNetworkVswitchQuery");
        out.println("   <OPTION VALUE=\"VirtualNetworkVswitchSet\">VirtualNetworkVswitchSet");	
        out.println("</SELECT>");
        out.println("<input value=\"Next\" type=\"submit\">");
        out.println("<INPUT TYPE=HIDDEN NAME=\"piglet.buildcall.state\" value=\"select_vsmcall\">");
        out.println("</form>");
	BuilderUtil.printTrailer(request, response, out);
	out.println("</body></html>");
    }
}

