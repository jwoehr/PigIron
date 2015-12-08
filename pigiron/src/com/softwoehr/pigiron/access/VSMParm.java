/*
 * Copyright (c) 2008, 2015 Jack J. Woehr jwoehr@softwoehr.com
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

/**
 * Interface implemented by all parameter types. VSMAPI has a limited number of
 * basic parameter types embodied here in the classes which implement VSMParm.
 *
 * The formal parameter types discussed in the VSMAPI documentation are:
 * <tt>int1</tt>, <tt>int4</tt>,
 * <tt>int8</tt>, <tt>string</tt>, <tt>struct</tt>, and <tt>array</tt>.
 *
 * Pigiron also recognizes <tt>counted_struct</tt>
 * as an extra type above and beyond the base types enumerated by the VSMAPI
 * documentation.
 *
 * @author jax
 */
public interface VSMParm {

    /**
     * Returns the data payload length in bytes contained in this parameter.
     *
     * @return the byte length of the data payload contained in this parameter.
     */
    public int paramLength();

    /**
     * VSMAPI parameters are named when they are used in the call documentation.
     * We try to mimic those documentation formal names Here is the interface
     * spec to retrieve the formal name, e.g., cpu_info_structure.
     *
     * This name has no computational impact on the call and is a convenience
     * for documentation and output display.
     *
     * @return the formal name of the parameter at usage time as documented.
     */
    public String getFormalName();

    /**
     * Get the formal type of the parmeter, one of the formal parameter types
     * discussed in the VSMAPI documentation: <tt>int1</tt>, <tt>int4</tt>,
     * <tt>int8</tt>, <tt>string</tt>, <tt>struct</tt>, <tt>array</tt>.
     *
     * Pigiron recognizes <tt>counted_struct</tt>
     * as an extra type above and beyond the base types enumerated by the VSMAPI
     * documentation.
     *
     * @return the fornal type in a string with the case set as in the docs
     */
    public String getFormalType();

    /**
     * Return a deep copy of the instance.
     *
     * @return copy or null
     */
    public VSMParm copyOf();

    /**
     * Write a VSMParm implementor instance on a stream.
     *
     * @param out the output stream
     * @throws java.io.IOException on comm error
     */
    public void write(DataOutputStream out) throws java.io.IOException;

    /**
     * Read in a VSMParm implementor instance from a stream.
     *
     * @param in the input stream
     * @param length length to write
     * @throws java.io.IOException on comm error
     * @throws VSMException on internal Pigiron parameter marshalling error
     */
    public void read(DataInputStream in, int length) throws java.io.IOException, VSMException;

    /**
     * Prettyprint the instance for debugging or simple output display.
     *
     * @return Prettyprint of the instance for debugging or simple output
     * display
     */
    public String prettyPrint();
}
