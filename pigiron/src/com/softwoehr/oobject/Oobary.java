package com.softwoehr.oobject;

import java.util.Hashtable;
import java.util.Enumeration;

public class Oobary
{
    private Hashtable oobs = new Hashtable();
    
    public Oobary() {
    }
    
    public void put(Oob oob) {
	oobs.put(oob.key, oob.value);
    }
    
    public Oob get(Object key) {
	Oob result = null;
	if (oobs.containsKey(key)) {
	    result = new Oob(key, oobs.get(key));
	}
	return result;
    }
    
    public Oobary oobary(Class aclass) {
	Oobary result = new Oobary ();
	Enumeration e = oobs.keys();
	while (e.hasMoreElements()) {
	    Object k = e.nextElement();
	    if (k.getClass() == aclass) {
		result.put(get(k));
	    }
	}
	return result;
    }
    
    public String toString () {
	StringBuffer sb = new StringBuffer(super.toString());
	Enumeration e = oobs.keys();
	while (e.hasMoreElements()) {
	    Object k = e.nextElement();
	    sb.append(" an Oob: " + get(k));
	}
	return sb.toString();
    }
    
    public static void main(String[] args)
    {    
        
    }
}

