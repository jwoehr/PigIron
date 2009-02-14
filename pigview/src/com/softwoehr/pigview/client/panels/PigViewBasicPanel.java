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
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import com.softwoehr.pigview.client.enhanced.*;
import com.softwoehr.pigview.client.panels.widgets.*;

/**
 *  Superclass of all PigView panels that live in the tabbed notebook
 *
 * @author     jax
 * @created    January 30, 2009
 */
public class PigViewBasicPanel extends VerticalPanel {

    /**
     *  Description of the Field
     */ 
    protected static final String COMMUNICATING_WITH_SERVER = "Communicating with the server.";
    /**
     *  Description of the Field
     */ 
    protected static final String HTTP_FAILURE = "HTTP failure";
    /**
     *  Description of the Field
     */ 
    protected static final String CLEAR = "Clear";
    /**
     *  Description of the Field
     */ 
    protected static final String SUBMIT = "Submit";
    /**
     *  Description of the Field
     */ 
    protected static final String RESET = "Reset";
    /**
     *  Description of the Field
     */ 
    protected EnhancedRequestBuilder requestBuilder = null;
    /**
     *  Description of the Field
     */ 
    protected final Button submitButton = new Button(SUBMIT);
    /**
     *  Description of the Field
     */ 
    protected final Button resetButton = new Button(RESET);
    /**
     *  Description of the Field
     */ 
    protected final InfoDialog infoDialog = new InfoDialog();
    /**
     *  Description of the Field
     */ 
    protected Request request = null;

    /**
     *Constructor for the PigViewBasicPanel object
     */ 
    public PigViewBasicPanel() {
        super();
        initWidgets();
        initPanel();
    }

    /**
     *  Create all widgets to be used in the initial view.
     */ 
    protected void initWidgets() {
    }

    /**
     * Layout the panel for the initial view.
     */ 
    protected void initPanel() {
        setWidth("100%");
        setHorizontalAlignment(ALIGN_CENTER);
        HorizontalPanel buttonPanel = new HorizontalPanel();
        buttonPanel.add(submitButton);
        buttonPanel.add(resetButton);
        add(buttonPanel);
    }

}

