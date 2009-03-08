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

import com.softwoehr.pigview.client.enhanced.EnhancedTreeItem;

import java.util.HashSet;
import java.util.Iterator;

/**
 *  Description of the Class
 *
 * @author     jax
 * @created    March 7, 2009
 */
public class NavigatorTreeManager extends HashSet <EnhancedTreeItem> {
    private NavigatorTree navigatorTree = null;

    /**
     *Constructor for the NavigatorTreeManager object
     *
     * @param  navigatorTree  Description of the Parameter
     */ 
    public NavigatorTreeManager(NavigatorTree navigatorTree) {
        this();
        setNavigatorTree(navigatorTree);
    }

    /**
     *Constructor for the NavigatorTreeManager object
     */ 
    public NavigatorTreeManager() { }

    /**
     *  Sets the navigatorTree attribute of the NavigatorTreeManager object
     *
     * @param  navigatorTree  The new navigatorTree value
     */ 
    public void setNavigatorTree(NavigatorTree navigatorTree) {
        this.navigatorTree = navigatorTree;
    }

    /**
     *  Gets the navigatorTree attribute of the NavigatorTreeManager object
     *
     * @return    The navigatorTree value
     */ 
    public NavigatorTree getNavigatorTree() {
        return navigatorTree;
    }

    /**
     *  Gets an iterator over a hash set of all contained tree items
     * that represent a host.
     *
     * @return    Iterator over collection of host tree items
     */    
    public Iterator<EnhancedTreeItem> getHostTreeItems() {
	HashSet<EnhancedTreeItem> set = new HashSet<EnhancedTreeItem>();
	Iterator<EnhancedTreeItem> it = iterator();
	while(it.hasNext()) {
	   EnhancedTreeItem e = it.next();
	   if (e instanceof HostTreeItem) {
	       set.add(e);
	   }
	}
	return set.iterator();
    }
}

