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

// import com.google.gwt.user.client.ui.Button;
// import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
// import com.google.gwt.user.client.ui.Widget;
import com.softwoehr.pigview.client.enhanced.*;
import com.softwoehr.pigview.client.panels.NavigatorPanel;

// import java.util.Collection;
import java.util.Iterator;

/**
 * Represents the tree view of the navigator
 *
 * @author     jax
 * @created    February 13, 2009
 */
public class NavigatorTree extends Tree {
    private Image mainframeImage = new Image("images/mainframe.png");
    private final AddHostDialog addHostDialog = new AddHostDialog(this);
    private final NavigatorPanel navigatorPanel;
    private TreeItem root = null;

    /**
     *Constructor for the NavigatorTree object
     *
     * @param  navigatorPanel  Description of the Parameter
     */ 
    public NavigatorTree(NavigatorPanel navigatorPanel) {
        super();
        this.navigatorPanel = navigatorPanel;
        initItems();
    }

    /**
     *  Init the tree's contents.
     */ 
    public void initItems() {
        mainframeImage = new Image("images/mainframe.png");
        mainframeImage.addClickHandler (new ClickHandler() {
            public void onClick(ClickEvent event) {
                addHostDialog.setHTML("<center>Add Host to View</center>");
                addHostDialog.center();
                addHostDialog.show();
            }
        } );
        rebuildTree();
        setSelectedItem(getItem(0));
        ensureSelectedItemVisible();
    }

    /**
     *  Adds a feature to the Host attribute of the NavigatorTree object
     *
     * @param  dialog  The feature to be added to the Host attribute
     */ 
    public void addHost(AddHostDialog dialog) {
        saveHost(dialog.getDisplayName(), dialog.getDnsName(), dialog.getIpAddr(), dialog.getPortNumber(), dialog.getUid(),
         dialog.getPassword(), dialog.getUseSSL());
    }

    /**
     *  Description of the Method
     *
     * @param  displayName  Description of the Parameter
     * @param  dnsName      Description of the Parameter
     * @param  ipAddr       Description of the Parameter
     * @param  portNumber   Description of the Parameter
     * @param  useSSL       Description of the Parameter
     * @param  uid          Description of the Parameter
     * @param  password     Description of the Parameter
     */ 
    public void saveHost(String displayName, String dnsName, String ipAddr, String portNumber, String uid, String password, boolean useSSL) {
        PersistenceManager.persist("host.DisplayName." + displayName, displayName);
        PersistenceManager.setHostProperty(displayName, "DnsName", dnsName);
        PersistenceManager.setHostProperty(displayName, "IpAddr", ipAddr);
        PersistenceManager.setHostProperty(displayName, "PortNumber", portNumber);
        PersistenceManager.setHostProperty(displayName, "Uid", uid);
        PersistenceManager.setHostProperty(displayName, "Password", password);
        PersistenceManager.setHostProperty(displayName, "UseSSL", useSSL ? "true" : "false");
        rebuildTree();
        setSelectedItem(findHostInTree(displayName));
        ensureSelectedItemVisible();
    }

    /**
     *  Description of the Method
     *
     * @param  displayName  Description of the Parameter
     */ 
    public void deleteHost(String displayName) {
        PersistenceManager.remove("host.DisplayName." + displayName);
        PersistenceManager.removeHostProperty(displayName, "DnsName");
        PersistenceManager.removeHostProperty(displayName, "IpAddr");
        PersistenceManager.removeHostProperty(displayName, "PortNumber");
        PersistenceManager.removeHostProperty(displayName, "Uid");
        PersistenceManager.removeHostProperty(displayName, "Password");
        PersistenceManager.removeHostProperty(displayName, "UseSSL");
        rebuildTree();
        setSelectedItem(null);
        navigatorPanel.dropHostDetailsView();
    }

    /**
     *  Description of the Method
     *
     * @param  displayName  Description of the Parameter
     * @return              Description of the Return Value
     */ 
    public TreeItem findHostInTree(String displayName) {
        TreeItem result = null;
        for (int i = 0; i < root.getChildCount(); i++) {
            TreeItem found = root.getChild(i);
            String text = found.getText();
            if (text != null & text.equals(displayName)) {
                result = found;
                break;
            }
        }
        return result;
    }

    /**
     *  Description of the Method
     */ 
    public void rebuildTree() {
        clear();
        Iterator hostNamesIterator = PersistenceManager.hostNames().iterator();
        root = new TreeItem(mainframeImage);
        root.addItem("Click the mainframe image to add a New host");
        while (hostNamesIterator.hasNext()) {
            final Label l = new Label(hostNamesIterator.next().toString());
            final TreeItem t = new TreeItem(l);
            l.addClickHandler (new ClickHandler() {
                public void onClick(ClickEvent event) {
                    TreeItem parent = t.getParentItem();
                    int childCount = parent.getChildCount();
                    for (int i = 0; i < childCount; i++) {
                        parent.getChild(i).removeStyleName("gwt-TreeItem-selected");
                    }
                    t.setStyleName("gwt-TreeItem-selected");
                    t.setSelected(true);
                    navigatorPanel.hostDetailsView(l.getText());
                }
            } );
            addOperations(t);
            root.addItem(t);
        }
        addItem(root);
    }

    /**
     *  Adds a feature to the Operations attribute of the NavigatorTree object
     *
     * @param  treeItem  The feature to be added to the Operations attribute
     */ 
    public void addOperations(TreeItem treeItem) {
        addAPILevelOperation(treeItem);
	addCheckAuthenticationOperation(treeItem);
	addImageQueryOperation(treeItem);
    }

    /**
     *  Adds a feature to the APILevelOperation attribute of the NavigatorTree object
     *
     * @param  treeItem  The feature to be added to the APILevelOperation attribute
     */ 
    public void addAPILevelOperation(TreeItem treeItem) {
        final Label l = new Label("API Level");
        final TreeItem t = new TreeItem(l);
        l.addClickHandler (new ClickHandler() {
            public void onClick(ClickEvent event) {
                TreeItem parent = t.getParentItem();
                int childCount = parent.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    parent.getChild(i).removeStyleName("gwt-TreeItem-selected");
                }
                t.setStyleName("gwt-TreeItem-selected");
                t.setSelected(true);
		/* Cheesy way of getting display name of host */
		/* which its the key to the Cookie database   */
		/* from the label of the tree parent (== host)*/ 
                navigatorPanel.hostApiLevelExplorerView(((Label) parent.getWidget()).getText());
            }
        } );
        treeItem.addItem(t);
    }

    /**
     *  Adds a feature to the CheckAuthenticationOperation attribute of the NavigatorTree object
     *
     * @param  treeItem  The feature to be added to the CheckAuthenticationOperation attribute
     */ 
    public void addCheckAuthenticationOperation(TreeItem treeItem) {
        final Label l = new Label("Check Authentication");
        final TreeItem t = new TreeItem(l);
        l.addClickHandler (new ClickHandler() {
            public void onClick(ClickEvent event) {
                TreeItem parent = t.getParentItem();
                int childCount = parent.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    parent.getChild(i).removeStyleName("gwt-TreeItem-selected");
                }
                t.setStyleName("gwt-TreeItem-selected");
                t.setSelected(true);
                navigatorPanel.hostCheckAuthenticationExplorerView(((Label) parent.getWidget()).getText());
            }
        } );
        treeItem.addItem(t);
    }
    
   /**
     *  Adds a feature to the ImageQueryOperation attribute of the NavigatorTree object
     *
     * @param  treeItem  The feature to be added to the ImageQueryOperation attribute
     */ 
    public void addImageQueryOperation(TreeItem treeItem) {
        final Label l = new Label("Images");
        final TreeItem t = new TreeItem(l);
        l.addClickHandler (new ClickHandler() {
            public void onClick(ClickEvent event) {
                TreeItem parent = t.getParentItem();
                int childCount = parent.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    parent.getChild(i).removeStyleName("gwt-TreeItem-selected");
                }
                t.setStyleName("gwt-TreeItem-selected");
                t.setSelected(true);
                navigatorPanel.hostImageQueryExplorerView(((Label) parent.getWidget()).getText());
            }
        } );
        treeItem.addItem(t);
    }
}

