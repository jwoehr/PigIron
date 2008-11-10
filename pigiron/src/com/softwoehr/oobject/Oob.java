package com.softwoehr.oobject;

/**
 *  Description of the Class
 *
 * @author     jax
 * @created    November 10, 2008
 */
public class Oob {
	/**
	 *  Description of the Field
	 */
	public Object key;
	/**
	 *  Description of the Field
	 */
	public Object value;

	/**
	 *Constructor for the Oob object
	 *
	 * @param  key    Description of the Parameter
	 * @param  value  Description of the Parameter
	 */
	public Oob(Object key, Object value) {
		this.key = key;
		this.value = value;
	}

	/**
	 *  Description of the Method
	 *
	 * @return    Description of the Return Value
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer(super.toString());
		sb.append(" Oob.key: " + key + " Oob.value: " + value);
		return sb.toString();
	}

	/**
	 *  Description of the Method
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
	 * @param  args  The command line arguments
	 */
	public static void main(String[] args) {

	}
}

