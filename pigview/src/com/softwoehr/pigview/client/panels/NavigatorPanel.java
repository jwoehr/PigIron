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

// import com.google.gwt.user.client.ui.CheckBox;
// import com.google.gwt.user.client.ui.Image;
// import com.google.gwt.user.client.ui.Tree;
// import com.google.gwt.user.client.ui.TreeItem;
// import com.google.gwt.user.client.ui.VerticalPanel;

import com.softwoehr.pigview.client.panels.widgets.NavigatorTree;

/**
 *  The navigator lives in this panel on the nav tab.
 *
 * @author     jax
 * @created    January 30, 2009
 */
public class NavigatorPanel extends PigViewBasicPanel {
    // private Image mainframeImage = null;
    private NavigatorTree navigatorTree = null;

    /**
     *Constructor for the NavigatorPanel object
     */ 
    public NavigatorPanel() {
        super();
    }

    /**
     *  Create all widgets to be used in the initial view.
     */ 
    public void initWidgets() {
        super.initWidgets();
        // mainframeImage = new Image("images/mainframe.png");
        initTree();
    }

    /**
     *  Layout the panel for the initial view.
     */ 
    public void initPanel() {
        setWidth("100%");
        setHorizontalAlignment(ALIGN_LEFT);
        // add(mainframeImage);
        add(navigatorTree);
    }

    /**
     *  Description of the Method
     */ 
    private void initTree() {
	navigatorTree = new NavigatorTree();
	
        /* TreeItem root = new TreeItem("root");
        root.addItem("item0");
        root.addItem("item1");
        root.addItem("item2");
        // Add a CheckBox to the tree
        TreeItem item = new TreeItem(new CheckBox("item3"));
        root.addItem(item);
        tree = new Tree();
        tree.addItem(root); */
    }
}

