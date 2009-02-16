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
package com.softwoehr.pigview.client.panels.widgets;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import com.softwoehr.pigview.client.enhanced.*;

/**
 *  Description of the Class
 *
 * @author     jax
 * @created    February 13, 2009
 */
public class AddHostDialog extends DialogBox {

    private final HorizontalPanel buttonHPanel = new HorizontalPanel();
    private final VerticalPanel dialogVPanel = new VerticalPanel();
    private final VerticalPanel dialogTopPanel = new VerticalPanel();
    private final VerticalPanel dialogBottomPanel = new VerticalPanel();

    private final TextBox displayNameTextBox = new TextBox();
    private final TextBox dnsNameTextBox = new TextBox();
    private final TextBox ipAddrTextBox = new TextBox();
    private final TextBox portNumberTextBox = new TextBox();
    private final CheckBox useSSLCheckBox = new CheckBox();

    private final Button addButton = new Button("Add");
    private final Button clearButton = new Button("Clear");
    private final Button cancelButton = new Button("Cancel");

    private final NavigatorTree associatedNavigatorTree;

    /**
     *Constructor for the AddHostDialog object
     *
     * @param  associatedNavigatorTree  Description of the Parameter
     */ 
    public AddHostDialog(NavigatorTree associatedNavigatorTree) {
        super();
        this.associatedNavigatorTree = associatedNavigatorTree;
        initWidgets();
        initDialogPanel();
    }

    /**
     *  Create and set up the widgets used in the dialog.
     */ 
    public void initWidgets() {
        setAnimationEnabled(true);
        addButton.addClickHandler (new ClickHandler() {
            public void onClick(ClickEvent event) {
                addHost();
                hide();
            }
        } );
        clearButton.addClickHandler (new ClickHandler() {
            public void onClick(ClickEvent event) {
                clearDialog();
            }
        } );
        cancelButton.addClickHandler (new ClickHandler() {
            public void onClick(ClickEvent event) {
                hide();
            }
        } );
    }

    /**
     *  Layout the dialog panel.
     */ 
    public void initDialogPanel() {
        dialogTopPanel.setWidth("100%");
        dialogTopPanel.setHorizontalAlignment(VerticalPanel.ALIGN_LEFT);
        dialogTopPanel.add(new Label("Display name:"));
        dialogTopPanel.add(displayNameTextBox);
        dialogTopPanel.add(new Label("DNS name:"));
        dialogTopPanel.add(dnsNameTextBox);
        dialogTopPanel.add(new Label("IP Address (ignored if DNS Name is present):"));
        dialogTopPanel.add(ipAddrTextBox);
        dialogTopPanel.add(new Label("Port number:"));
        dialogTopPanel.add(portNumberTextBox);
        dialogTopPanel.add(new Label("Use SSL:"));
        dialogTopPanel.add(useSSLCheckBox);
        buttonHPanel.setHorizontalAlignment(buttonHPanel.ALIGN_CENTER);
        buttonHPanel.add(addButton);
        buttonHPanel.add(clearButton);
        buttonHPanel.add(cancelButton);
        dialogBottomPanel.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
        dialogBottomPanel.add(buttonHPanel);
	dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
        dialogVPanel.add(dialogTopPanel);
        dialogVPanel.add(dialogBottomPanel);
        setWidget(dialogVPanel);
    }

    /**
     *  Blank the fields of the dialog.
     */ 
    protected void clearDialog() {
        displayNameTextBox.setText("");
        dnsNameTextBox.setText("");
        ipAddrTextBox.setText("");
        portNumberTextBox.setText("");
        useSSLCheckBox.setValue(false);
    }

    /**
     *  Adds Host to the tree from the contents of the dialog.
     */ 
    protected void addHost() {
        String text = displayNameTextBox.getText();
        if (text != null & ! text.equals("")) {
            associatedNavigatorTree.setSelectedItem(associatedNavigatorTree.addHost(displayNameTextBox.getText()));
            associatedNavigatorTree.ensureSelectedItemVisible();
        }
    }
}

