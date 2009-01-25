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
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.Window;

import com.softwoehr.pigview.client.enhanced.*;
import com.softwoehr.pigview.client.panels.widgets.*;

/**
 *  Description of the Class
 *
 * @author     jax
 * @created    January 23, 2009
 */
public class InputPanel extends VerticalPanel implements RequestCallback {

    private final static String SENDING_PIGIRON_REQUEST = "Sending PigIron request to the server.";
    private final static String HTTP_FAILURE = "HTTP failure";
    private final static String SUBMIT = "Submit";
    private final static String CLEAR = "Clear";

    private EnhancedRequestBuilder requestBuilder = null;
    private final TextArea textArea = new TextArea();
    private final Button submitButton = new Button(SUBMIT);
    private final Button clearButton = new Button(CLEAR);
    private final InfoDialog infoDialog = new InfoDialog();
    private Request request = null;

    /**
     *Constructor for the InputPanel object
     */ 
    public InputPanel() {
        super();
        initWidgets();
        initPanel();
    }

    /**
     *  Description of the Method
     *
     * @param  request    Description of the Parameter
     * @param  exception  Description of the Parameter
     */ 
    public void onError(Request request, java.lang.Throwable exception) {
        Window.alert(HTTP_FAILURE);
    }

    /**
     *  Description of the Method
     *
     * @param  request   Description of the Parameter
     * @param  response  Description of the Parameter
     */ 
    public void onResponseReceived(Request request, Response response) {
        infoDialog.setText(response.getText());
        infoDialog.center();
        infoDialog.show();
    }

    /**
     *  Description of the Method
     */ 
    public void initWidgets() {
	textArea.setCharacterWidth(100);
        textArea.setVisibleLines(30);
        submitButton.addClickListener (new ClickListener() {
            public void onClick(Widget sender) {
                buildRequest("http://localhost:8080/piglet/PigIronServlet/engine", textArea.getText().trim());
                try {
                    request = requestBuilder.send();
		    infoDialog.setText(SENDING_PIGIRON_REQUEST);
                    infoDialog.center();
                    infoDialog.show();
                } catch (com.google.gwt.http.client.RequestException ex) {
                    infoDialog.setText(ex.getMessage());
                    infoDialog.center();
                    infoDialog.show();
                }
            }
        } );
        clearButton.addClickListener (new ClickListener() {
            public void onClick(Widget sender) {
                // textArea.clear();
                textArea.setText("");
            }
        } );
    }

    /**
     *  Description of the Method
     */ 
    public void initPanel() {
        setWidth("100%");
	setHorizontalAlignment(ALIGN_CENTER);
	HorizontalPanel buttonPanel = new HorizontalPanel();
	buttonPanel.add(submitButton);
	buttonPanel.add(clearButton);
	add(textArea);
	add(buttonPanel);
    }

    /**
     *  Description of the Method
     *
     * @param  url        Description of the Parameter
     * @param  requestor  Description of the Parameter
     */ 
    public void buildRequest(String url, String requestor) {
        requestBuilder = new EnhancedRequestBuilder(RequestBuilder.POST, url, this);
        requestBuilder.appendParameter("requestor", requestor);
    }
}

