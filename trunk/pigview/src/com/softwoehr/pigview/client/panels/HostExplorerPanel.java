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

import com.softwoehr.pigview.client.panels.widgets.NavigatorTree;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 *  The Host Explorer Panel is the bottom panel of the right side of the
 * navigator composite panel on the nav tab. It provides extended info,
 * operations and extended navigation.
 *
 * @author     jax
 * @created    January 30, 2009
 */
public class HostExplorerPanel extends VerticalPanel {
    
    protected String displayName = null;
    protected NavigatorTree navigatorTree = null;
    protected HostDetailsPanel hostDetailsPanel = null;
    protected EnhancedRequestBuilder requestBuilder = null;
    protected final InfoDialog infoDialog = new InfoDialog();
    protected final static String SENDING_PIGIRON_REQUEST = "Sending PigIron request to the server.";
    protected final static String HTTP_FAILURE = "HTTP failure";

    /**
     *Constructor for the HostExplorerPanel object
     */ 
    protected HostExplorerPanel() {
        super();
    }

    /**
     *Constructor for the HostExplorerPanel object
     *
     * @param  displayName       Description of the Parameter
     * @param  navigatorTree     Description of the Parameter
     * @param  hostDetailsPanel  Description of the Parameter
     */ 
    public HostExplorerPanel(String displayName, NavigatorTree navigatorTree,
             HostDetailsPanel hostDetailsPanel) {
        super();
        this.displayName = displayName;
        this.navigatorTree = navigatorTree;
        this.hostDetailsPanel = hostDetailsPanel;
        initWidgets();
        initPanel();
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
     *  Description of the Method
     *
     * @param  objects  Description of the Parameter
     */ 
    public void doIt(Object [] objects) {
    }
}

