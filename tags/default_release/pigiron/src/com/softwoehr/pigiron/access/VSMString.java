/*
 * Copyright (c) 2008, Jack J. Woehr jwoehr@softwoehr.com
 * PO Box 51, Golden, Colorado 80402-0051 USA
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *     * Redistributions of source code must retain the above copyright
 *         notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *         notice, this list of conditions and the following disclaimer
 *         in the documentation and/or other materials provided with the
 *         distribution.
 *     * Neither the name of the PigIron Project nor the names of its
 *         contributors may be used to endorse or promote products derived
 *         from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.softwoehr.pigiron.access;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 *
 * @author jax
 */
public class VSMString implements VSMParm {

    private String value;

    /**
     * 
     */
    public VSMString() {
    }

    /**
     * 
     * @param value
     */
    public VSMString(String value) {
        this();
        setValue(value);
    }

    /**
     * 
     * @return
     */
    public String getValue() {
        return value;
    }

    /**
     * 
     * @param value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /*
     * Interface methods
     */
    /**
     * 
     * @return
     */
    public int paramLength() {
        return value.length();
    }

    /**
     * 
     * @param in
     * @throws java.io.IOException
     */
    public void read(DataInputStream in, int length) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Write the String parameter with a prepended count.
     * Apparently this count is always int4.
     * @param d
     * @throws java.io.IOException
     */
    public void write(DataOutputStream d)
            throws java.io.IOException {
        new VSMInt4(paramLength()).write(d);
        d.writeBytes(value);
    }

    public String formalName() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    /*     
    // indicates any non-null (x’00’) characters
    char 
    char10 // indicates numeric digits 0-9
    char16 // indicates hexadecimal digits 0-9 and A-F
    char17 // indicates hexadecimal digits 0-9 and A-F,
    // plus the hyphen (-) or minus sign.
    char26 // indicates alphabetics A-Z
    char36 // indicates alphanumerics A-Z plus 0-9
    char37 // indicates alphanumerics A-Z, 0-9, and
    // the hyphen (-) or minus sign
    char42 // indicates A-Z plus 0-9 plus @#$+-:
    char43 // indicates A-Z plus 0-9 plus @#$+-: plus
    // underscore (_)
    char44 // indicates A-Z plus 0-9 plus @#$+-: plus
    // underscore (_) and the equal sign (=)
    charNA // no known character set restrictions
    charNB // indicates non-blank (x’20’), non-null
    // (x’00’), non-delimiter (x’FF’), non-carriage
    // return (x’0D’), and non-line-feed (x’0A’).
     */
}