package com.softwoehr.oobject;

import java.util.Hashtable;
import java.util.Enumeration;

/**
 *  Description of the Class
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
	 *  Description of the Method
	 *
	 * @param  oob  Description of the Parameter
	 */
	public void put(Oob oob) {
		oobs.put(oob.key, oob.value);
	}

	/**
	 *  Description of the Method
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
	 *  Description of the Method
	 *
	 * @param  aclass  Description of the Parameter
	 * @return         Description of the Return Value
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
	 *  Description of the Method
	 *
	 * @return    Description of the Return Value
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
	 * @param  args  The command line arguments
	 */
	public static void main(String[] args) {
	}
}

