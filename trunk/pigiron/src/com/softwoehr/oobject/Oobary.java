/*
 *  Copyright (c) 2008, Jack J. Woehr jwoehr@softwoehr.com
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
package com.softwoehr.oobject;

import java.util.Hashtable;
import java.util.Enumeration;

/**
 *  A boobery of an Oobary full of Oobs which
 * are searchable by unique key or non-unique
 * key class.
 *
 * @author     jax
 * @created    November 10, 2008
 */
public class Oobary {
	private Hashtable oobs = new Hashtable();

	/**
	 *Constructor for the Oobary object
	 */
	public Oobary() { }

	/**
	 * puts an Oob
	 *
	 * @param  oob  Description of the Parameter
	 */
	public void put(Oob oob) {
		oobs.put(oob.key, oob.value);
	}

	/**
	 *  Gets Oob by key
	 *
	 * @param  key  Description of the Parameter
	 * @return      Description of the Return Value
	 */
	public Oob get(Object key) {
		Oob result = null;
		if (oobs.containsKey(key)) {
			result = new Oob(key, oobs.get(key));
		}
		return result;
	}

	/**
	 *  Gets an Oobary of Oobs by key class
	 *
	 * @param  aclass  Class to look for in Oob keys
	 * @return         Oobary of Oobs whose keys are instances of specified class
	 */
	public Oobary oobary(Class aclass) {
		Oobary result = new Oobary();
		Enumeration e = oobs.keys();
		while (e.hasMoreElements()) {
			Object k = e.nextElement();
			if (k.getClass() == aclass) {
				result.put(get(k));
			}
		}
		return result;
	}

	/**
	 *  Printable
	 *
	 * @return    printable string
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer(super.toString());
		Enumeration e = oobs.keys();
		while (e.hasMoreElements()) {
			Object k = e.nextElement();
			sb.append(" an Oob: " + get(k));
		}
		return sb.toString();
	}

	/**
	 *  The main program for the Oobary class
	 *
	 * not currently used
	 *
	 * @param  args  The command line arguments
	 */
	public static void main(String[] args) {
	}
}

