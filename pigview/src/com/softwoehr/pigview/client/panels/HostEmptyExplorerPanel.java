/*
 *  Copyright (c) 2009, Jack J. Woehr jwoehr@softwoehr.com
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
package com.softwoehr.pigview.client.panels;

import com.softwoehr.pigview.client.enhanced.*;
import com.softwoehr.pigview.client.panels.widgets.*;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.Response;
import com.google.gwt.json.client.*;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.Window;

import com.softwoehr.pigview.client.enhanced.*;

/**
 *  The Host Explorer Panel is the bottom panel of the right side of the
 * navigator composite panel on the nav tab. It provides extended info,
 * operations and extended navigation. This explorer is empty to be used
 * as a replacement view when the user moves out of exploring whatever was
 * last explored.
 *
 * @author     jax
 * @created    January 30, 2009
 */
public class HostEmptyExplorerPanel extends HostExplorerPanel implements RequestCallback {

    /**
     *Constructor for the HostExplorerPanel object
     */ 
    protected HostEmptyExplorerPanel() {
        super();
    }

    /**
     *Constructor for the HostEmptyExplorerPanel object
     *
     * @param  displayName       Display name of the Host to query
     * @param  navigatorTree     Navigator tree associated with the Navigator
     * composite panel that parents this panel's Host details parent
     * @param  hostDetailsPanel  Host details parent
     */ 
    public HostEmptyExplorerPanel(String displayName,
             NavigatorTree navigatorTree, HostDetailsPanel hostDetailsPanel) {
        super(displayName,navigatorTree,hostDetailsPanel);
    }

    /**
     *  Create all widgets to be used in the initial view.
     */ 
    public void initWidgets() {
    }

    /**
     *  Layout the panel for the initial view.
     */ 
    public void initPanel() {
        setSize("100%", "100%");
        setHorizontalAlignment(ALIGN_LEFT);
        setVerticalAlignment(ALIGN_TOP);
    }

    /**
     *  Run the JSON request and thus build the panel in the callback.
     * In the present instance, does nothing.
     * @param  objects any additional parameters to provide to the operation
     */ 
    public void doIt(Object [] objects) {
    }

   /**
     *  Formulate the request to be executed in doIt() In the present
     * instance, just returns null since it is not needed.
     *
     * @return    an EnhancedRequestBuilder embodying the request
     * @see #doIt
     */
    public EnhancedRequestBuilder buildRequest() {
       return null;
    }

    /**
     *  Description of the Method
     *
     * @param  request    Description of the Parameter
     * @param  exception  Description of the Parameter
     */ 
    public void onError(Request request, java.lang.Throwable exception) {
        infoDialog.hide();
        Window.alert(HTTP_FAILURE);
    }

    /**
     *  Description of the Method
     *
     * @param  request   Description of the Parameter
     * @param  response  Description of the Parameter
     */ 
    public void onResponseReceived(Request request, Response response) {
        infoDialog.hide();
    }
     
    /**
     *  Description of the Method
     *
     * @return    Description of the Return Value
     */ 
    public String jsonRequest() {
        return null;
    }
}

