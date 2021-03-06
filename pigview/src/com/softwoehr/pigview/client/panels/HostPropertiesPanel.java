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
import com.softwoehr.pigview.client.enhanced.*;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 *  Host properties panel is one panel of the host details panel on the
 *  right side of the navigator composite panel on the nav tab.
 *
 * @author     jax
 * @created    January 30, 2009
 */
public class HostPropertiesPanel extends VerticalPanel {
    private NavigatorTree navigatorTree = null;
    private String displayName = null;
    private final TextBox displayNameTextBox = new TextBox();
    private final TextBox dnsNameTextBox = new TextBox();
    private final TextBox ipAddrTextBox = new TextBox();
    private final TextBox portNumberTextBox = new TextBox();
    private final TextBox uidTextBox = new TextBox();
    private final TextBox passwordTextBox = new PasswordTextBox();
    private final CheckBox useSSLCheckBox = new CheckBox();
    private final Button saveButton = new Button("Save");
    private final Button restoreButton = new Button("Restore");
    private final Button deleteButton = new Button("Delete");
    private final VerticalPanel textVPanel = new VerticalPanel();
    private final HorizontalPanel buttonHPanel = new HorizontalPanel();

    /**
     *Constructor for the HostPropertiesPanel object
     */ 
    protected HostPropertiesPanel() {
        super();
    }

    /**
     *Constructor for the HostPropertiesPanel object
     *
     * @param  navigatorTree  Description of the Parameter
     * @param  displayName    Description of the Parameter
     */ 
    public HostPropertiesPanel(String displayName, NavigatorTree navigatorTree) {
        super();
        this.displayName = displayName;
        this.navigatorTree = navigatorTree;
        initWidgets();
        initPanel();
    }

    /**
     *  Create all widgets to be used in the initial view.
     */ 
    public void initWidgets() {
        displayNameTextBox.setText(PersistenceManager.fetch("host.DisplayName." + displayName));
        dnsNameTextBox.setText(PersistenceManager.getHostProperty(displayName, "DnsName"));
        ipAddrTextBox.setText(PersistenceManager.getHostProperty(displayName, "IpAddr"));
        portNumberTextBox.setText(PersistenceManager.getHostProperty(displayName, "PortNumber"));
	uidTextBox.setText(PersistenceManager.getHostProperty(displayName, "Uid"));
	passwordTextBox.setText(PersistenceManager.getHostProperty(displayName, "Password"));
        useSSLCheckBox.setValue(PersistenceManager.getHostProperty(displayName, "UseSSL").equals("true") ? true : false);
        saveButton.addClickHandler (new ClickHandler() {
            public void onClick(ClickEvent event) {
                saveHostProperties();
            }
        } );
        restoreButton.addClickHandler (new ClickHandler() {
            public void onClick(ClickEvent event) {
                restoreHostProperties();
            }
        } );
        deleteButton.addClickHandler (new ClickHandler() {
            public void onClick(ClickEvent event) {
                deleteHost();
            }
        } );
    }

    /**
     *  Create all widgets to be used in the initial view.
     */ 
    public void initPanel() {
        clear();
        setSize("100%", "100%");
	setHorizontalAlignment(ALIGN_LEFT);
	setVerticalAlignment(ALIGN_TOP);
        textVPanel.setHorizontalAlignment(ALIGN_LEFT);
        textVPanel.setVerticalAlignment(ALIGN_TOP);
	HorizontalPanel tempH = new HorizontalPanel();
        tempH.add(displayNameTextBox);
	tempH.add(new Label("Display name"));
	textVPanel.add(tempH);
	tempH = new HorizontalPanel();
        tempH.add(dnsNameTextBox);
	tempH.add(new Label("DNS name"));
	textVPanel.add(tempH);
	tempH = new HorizontalPanel();
        tempH.add(ipAddrTextBox);
	tempH.add(new Label("IP Address (ignored if DNS Name is present)"));
	textVPanel.add(tempH);
	tempH = new HorizontalPanel();
        tempH.add(portNumberTextBox);
	tempH.add(new Label("Port number"));
	textVPanel.add(tempH);
	tempH = new HorizontalPanel();
	tempH.add(uidTextBox);
	tempH.add(new Label("User name"));
	textVPanel.add(tempH);
	tempH = new HorizontalPanel();
	tempH.add(passwordTextBox);
	tempH.add(new Label("Password"));
	textVPanel.add(tempH);
	tempH = new HorizontalPanel();
        tempH.add(useSSLCheckBox);
	tempH.add(new Label("Use SSL"));
	textVPanel.add(tempH);
        buttonHPanel.setHorizontalAlignment(buttonHPanel.ALIGN_CENTER);
        buttonHPanel.add(saveButton);
        buttonHPanel.add(restoreButton);
        buttonHPanel.add(deleteButton);
	add(textVPanel);
        add(buttonHPanel);
    }

    /**
     *  Description of the Method
     */ 
    public void saveHostProperties() {
        navigatorTree.saveHost
		(
		displayNameTextBox.getText()
		, dnsNameTextBox.getText()
		, ipAddrTextBox.getText()
		, portNumberTextBox.getText()
		, uidTextBox.getText()
		, passwordTextBox.getText()
		, useSSLCheckBox.getValue()
		);

    }

    /**
     *  Description of the Method
     */ 
    public void restoreHostProperties() {
        displayNameTextBox.setText(PersistenceManager.fetch("host.DisplayName." + displayName));
        dnsNameTextBox.setText(PersistenceManager.getHostProperty(displayName, "DnsName"));
        ipAddrTextBox.setText(PersistenceManager.getHostProperty(displayName, "IpAddr"));
        portNumberTextBox.setText(PersistenceManager.getHostProperty(displayName, "PortNumber"));
	uidTextBox.setText(PersistenceManager.getHostProperty(displayName, "Uid"));
	passwordTextBox.setText(PersistenceManager.getHostProperty(displayName, "Password"));
        useSSLCheckBox.setValue(PersistenceManager.getHostProperty(displayName, "UseSSL").equals("true") ? true : false);
    }

    /**
     *  Description of the Method
     */ 
    public void deleteHost() {
        navigatorTree.deleteHost(displayName);
    }
}

