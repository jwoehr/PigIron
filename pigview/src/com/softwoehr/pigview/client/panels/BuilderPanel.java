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

import com.google.gwt.http.client.*;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.Window;
import com.softwoehr.pigview.client.enhanced.*;


public class BuilderPanel extends VerticalPanel /*   implements ClickListener , RequestCallback  */ {
    
    private EnhancedRequestBuilder requestBuilder = null;
    private Image img = null;
    
/*     private Button button = null;
 
    public final DialogBox dialogBox = new DialogBox();
    private String dialogBoxText = "PigView calls PigLet calls PigIron";
    private final Button closeButton = new Button("close"); */
    
    /* 
    public void onError(Request request, java.lang.Throwable exception) { Window.alert("HTTP failure"); }
    public void onResponseReceived(Request request, Response response) { dialogBox.setText("PigLet PigIron Engine responds:\n" + response.getText()); dialogBox.show();}
     */
     
    public BuilderPanel() {
	super();
	initWidgets();
	initRequestBuilder();
	initPanel();
	/* initDialogPanel(); */
    }
    
    public void initRequestBuilder() {
    }
    
    public void initWidgets() {
	img = new Image("images/pig_15.gif");
	/* button = new Button("Click me");
        // We can add style names
        button.addStyleName("pc-template-btn");
        // or we can set an id on a specific element for styling */
        img.getElement().setId("pc-template-img");
	/* dialogBox.setAnimationEnabled(true); */
    }
    
    public void initPanel() {
	setWidth("100%");
	setHorizontalAlignment(BuilderPanel.ALIGN_CENTER);
	add(img);
	/* add(button); */
    }
/* 	
    public void initDialogPanel() {
        VerticalPanel dialogVPanel = new VerticalPanel();
        dialogVPanel.setWidth("100%");
        dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
        dialogVPanel.add(closeButton);
        closeButton.addClickListener(new ClickListener() {
            public void onClick(Widget sender) {
                dialogBox.hide();
            }
        } );
        dialogBox.setWidget(dialogVPanel);
        button.addClickListener(this);
    } */
/* 
     public void onClick(Widget sender) {
        final String dialogBoxText = "Sending PigIron request to the server.";
        dialogBox.setText(dialogBoxText);
        dialogBox.center();
        dialogBox.show();
	try {
        requestBuilder.send();
	}
	catch (com.google.gwt.http.client.RequestException ex) {
	    dialogBox.setText(ex.getMessage());
	}
    }
     */
}
