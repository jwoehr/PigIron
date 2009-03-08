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

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.softwoehr.pigview.client.enhanced.*;

/**
 *  Description of the Class
 *
 * @author     jax
 * @created    February 27, 2009
 */
public class InfoDialog extends DialogBox {

    private final Button closeButton = new Button("Close");

    /**
     *Constructor for the InfoDialog object
     */ 
    public InfoDialog() {
        super();
        initWidgets();
        initDialogPanel();
    }

    /**
     *  Description of the Method
     */ 
    public void initWidgets() {
        setAnimationEnabled(true);
    }

    /**
     *  Description of the Method
     */ 
    public void initDialogPanel() {
        VerticalPanel dialogVPanel = new VerticalPanel();
        dialogVPanel.setWidth("100%");
        dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
        dialogVPanel.add(closeButton);
        closeButton.addClickListener (new ClickListener() {
            public void onClick(Widget sender) {
                hide();
            }
        } );
        setWidget(dialogVPanel);
    }

    /**
     *  Quick show-the-message-now.
     *
     * @param  messageText  Message to display
     */ 
    public void say(String messageText) {
        setText(messageText);
        center();
        show();
    }

    /**
     *  Quick show-the-message-now creating a new InfoDialog.
     *
     * @param  messageText  Message to display
     * @return              the new InfoDialog
     */ 
    public static InfoDialog sayInfo(String messageText) {
        InfoDialog id = new InfoDialog();
        id.say(messageText);
        return id;
    }
}

