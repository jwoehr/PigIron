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

/**
 *  Key-value pair of any types
 *
 * @author     jax
 * @created    November 10, 2008
 */
public class Oob {
	/**
	 *  Key
	 */
	public Object key;
	/**
	 *  Value
	 */
	public Object value;

	/**
	 *Constructor for the Oob object
	 *
	 * @param  key    key
	 * @param  value  value
	 */
	public Oob(Object key, Object value) {
		this.key = key;
		this.value = value;
	}

	/**
	 *  Printable
	 *
	 * @return    printable
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer(super.toString());
		sb.append(" Oob.key: " + key + " Oob.value: " + value);
		return sb.toString();
	}

	/**
	 *  equivalence is equivalence of key/value
	 *
	 * @param  oob  Description of the Parameter
	 * @return      Description of the Return Value
	 */
	public boolean equals(Oob oob) {
		boolean result = (oob.key.equals(key) & oob.value.equals(value));
		return result;
	}

	/**
	 *  The main program for the Oob class
	 *
	 * not currently used
	 *
	 * @param  args  The command line arguments
	 */
	public static void main(String[] args) {

	}
}

