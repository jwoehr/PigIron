package com.softwoehr.oobject;

public class Oob
{
    public Object key;
    public Object value;
    
    public Oob(Object key, Object value) {
	this.key=key;
	this.value=value;
    }
    
    public String toString () {
	StringBuffer sb = new StringBuffer(super.toString());
	sb.append(" Oob.key: " + key + " Oob.value: " + value);
	return sb.toString();
    }
    
    public boolean equals (Oob oob) {
	boolean result = (oob.key.equals(key) & oob.value.equals(value));
	return result;
    }
    
    public static void main(String[] args)
    {    
        
    }
}

