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

import com.softwoehr.pigview.client.panels.widgets.NavigatorTree;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 *  The Host Details Panel is the right panel of the navigator composite panel
 * on the nav tab. The top of this rightmost panel is the host properties and
 * the bottom is the explorer view.
 *
 * @author     jax
 * @created    January 30, 2009
 */
public class HostDetailsPanel extends VerticalPanel {
    private String displayName = null;
    private NavigatorTree navigatorTree = null;
    private NavigatorCompositePanel navigatorCompositePanel = null;
    private HostPropertiesPanel hostPropertiesPanel = null;
    private HostExplorerPanel hostExplorerPanel = null;

    /**
     *Constructor for the HostDetailsPanel object
     */ 
    protected HostDetailsPanel() {
        super();
    }

    /**
     *Constructor for the HostDetailsPanel object
     *
     * @param  displayName              Description of the Parameter
     * @param  navigatorTree            Description of the Parameter
     * @param  navigatorCompositePanel  Description of the Parameter
     */ 
    public HostDetailsPanel(String displayName, NavigatorTree navigatorTree, NavigatorCompositePanel navigatorCompositePanel) {
        super();
        this.displayName = displayName;
        this.navigatorTree = navigatorTree;
        this.navigatorCompositePanel = navigatorCompositePanel;
        initWidgets();
        initPanel();
    }

    /**
     *  Create all widgets to be used in the initial view.
     */ 
    public void initWidgets() {
        hostPropertiesPanel = new HostPropertiesPanel(displayName, navigatorTree);
    }

    /**
     *  Layout the panel for the initial view.
     */ 
    public void initPanel() {
        setSize("100%", "100%");
        setHorizontalAlignment(ALIGN_LEFT);
        setVerticalAlignment(ALIGN_TOP);
        add(hostPropertiesPanel);
	hostEmptyExplorerView("Foobar");
    }

    /**
     *  Description of the Method
     *
     * @param  hostExplorerPanel  Description of the Parameter
     * @param  objects            Description of the Parameter
     */ 
    public void effectuateExplorerView(HostExplorerPanel hostExplorerPanel, Object [] objects) {
        if (this.hostExplorerPanel != null) {
            remove(this.hostExplorerPanel);
        }
        this.hostExplorerPanel = hostExplorerPanel;
        this.hostExplorerPanel.doIt(objects);
	add(this.hostExplorerPanel);
    }

    /**
     *  Description of the Method
     *
     * @param  displayName  Description of the Parameter
     */ 
    public void hostApiLevelExplorerView(String displayName) {
        effectuateExplorerView(new HostApiLevelExplorerPanel(displayName, navigatorTree, this), null);
    }

    /**
     *  Description of the Method
     *
     * @param  displayName  Description of the Parameter
     */ 
    public void hostCheckAuthenticationExplorerView(String displayName) {
        effectuateExplorerView(new HostCheckAuthenticationExplorerPanel(displayName, navigatorTree, this), null);
    }

    /**
     *  Description of the Method
     *
     * @param  displayName  Description of the Parameter
     */ 
    public void hostImageQueryExplorerView(String displayName) {
        effectuateExplorerView(new HostImageQueryExplorerPanel(displayName, navigatorTree, this), null);
    }
    
    /**
     *  Description of the Method
     *
     * @param  displayName  Description of the Parameter
     */ 
    public void hostEmptyExplorerView(String displayName) {
        effectuateExplorerView(new HostEmptyExplorerPanel(displayName, navigatorTree, this), null);
    }
}

