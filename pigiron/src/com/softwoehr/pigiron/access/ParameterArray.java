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

import com.softwoehr.pigiron.bizobj.VsmapiRC;
import com.softwoehr.pigiron.functions.VSMCall;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

/**
 * Parameter composition, marshalling and transmission nexus.
 *
 * @author jax
 */
public class ParameterArray extends ArrayList<VSMParm> {

    /**
     * Byte size of <tt>int1</tt> datatype
     */
    public static final int SIZEOF_INT1 = 1;
    /**
     * Byte size of <tt>int4</tt> datatype
     */
    public static final int SIZEOF_INT4 = 4;
    /**
     * Byte size of <tt>int8</tt> datatype
     */
    public static final int SIZEOF_INT8 = 8;

    /**
     * The VSMCall function using this ParameterArray. ParameterArray needs this
     * for interpreting return/reason code pairs which are overloaded.
     */
    protected VSMCall function = null;

    /**
     * Create an instance with no parameters.
     */
    protected ParameterArray() {
        super();
    }

    /**
     * Create an instance with {@code function} instanced.
     *
     * @param function the function creating the parameter array so that this
     * information is available for return/reason code interpretation
     */
    public ParameterArray(VSMCall function) {
        super();
        this.function = function;
    }

    /**
     * Replace the contents of this ParameterArray with the contents of another.
     *
     * @param value the container of the replacement data
     */
    public void setValue(ParameterArray value) {
        clear();
        if (value != null) {            // null is legal value
            addAll(value);            // means "just clear me"
        }
    }

    /**
     * Get the function to an instance of which this is assigned.
     *
     * @return function to an instance of which this is assigned
     */
    public VSMCall getFunction() {
        return this.function;
    }

    /**
     * Searches for a parameter with the specified formal name.
     *
     * @param name formal name of parameter
     * @return parameter if found, else null.
     */
    public VSMParm parameterNamed(String name) {
        VSMParm result = null;
        for (VSMParm v : this) {
            if (v.getFormalName().equals(name)) {
                result = v;
                break;
            }
        }
        return result;
    }

    /**
     * Write the indexed parameter to the output stream.
     *
     * @param index index in this ParameterArray to find the sought parameter at
     * @param out the output stream
     * @throws java.io.IOException on comm error
     */
    public void writeParameterAt(int index, DataOutputStream out) throws IOException {

        VSMParm p = get(index);
        p.write(out);
    }

    /**
     * Return a sum arrived at by recursive examination of members of the byte
     * length of all the parameters combined (including the byte length of their
     * transmitted counts. Note that if you have already run the composeInput()
     * function, that function typically calls totalParameterLength() at the
     * very end and prepends the total to the head of this ParameterArray. So if
     * you call totalParameterLength() after the array is composed, it will
     * already contain the length <tt>int4</tt> sent at the head of every packet
     * so the new count from totalParameterLength() at that time will be 4
     * greater (length of the length <tt>int4</tt> now included) than sent as a
     * length at transmission time.
     * <p>
     * In other words, there's no reason to call this at a high level except to
     * manufacture the subsequently prepended length <tt>int4</tt> at
     * composeInput().
     *
     * @return the sum arrived at by recursive examination of members of the
     * byte length of all the parameters found at the time of examination in
     * this ParameterArray.</p>
     */
    public long totalParameterLength() {
        long total = 0;
        Iterator<VSMParm> thisIterator = iterator();
        while (thisIterator.hasNext()) {
            total += thisIterator.next().paramLength();
        }
        return total;
    }

    /**
     * Write all parameters in order to the output stream.
     *
     * @param out
     * @throws java.io.IOException
     * @see #readAll
     * @see
     * <a href="http://publib.boulder.ibm.com/infocenter/zvm/v5r3/topic/com.ibm.zvm.v53.dmse6/hcsl8b20.htm" target="top">z/VM
     * V5R3.0 Systems Management Application Programming SC24-6122-03</a>
     */
    public void writeAll(DataOutputStream out) throws IOException {
        /* Write all the params */
        Iterator<VSMParm> myIterator = iterator();
        while (myIterator.hasNext()) {
            myIterator.next().write(out);
        }
        out.flush();
    }

    /**
     * Read in the overall message that is modeled by the compose() method of
     * query/call classes by stepping through the model and copying and
     * instancing the model elements as needed.
     *
     * @param in
     * @throws java.io.IOException
     * @throws VSMException
     * @see #writeAll
     * @see
     * <a href="http://publib.boulder.ibm.com/infocenter/zvm/v5r3/topic/com.ibm.zvm.v53.dmse6/hcsl8b20.htm" target="top">z/VM
     * V5R3.0 Systems Management Application Programming SC24-6122-03</a>
     */
    public void readAll(DataInputStream in) throws IOException, VSMException {
        /* Walk through the model created by the VSMCall */
        ListIterator<VSMParm> currentListIterator = listIterator();
        // Create the instance that will provide the new value this parameter array.
        ParameterArray replacement = new ParameterArray();
        // Get the first two components of any SMAPI reply
        VSMInt4 request_id = VSMInt4.class.cast(currentListIterator.next().copyOf());
        request_id.read(in, -1);
        replacement.add(request_id);
        VSMInt4 output_length = VSMInt4.class.cast(currentListIterator.next().copyOf());
        output_length.read(in, -1);
        replacement.add(output_length);
        int length_left = output_length.getValue();
        VSMParm copyOfCurrentParm;
        while (currentListIterator.hasNext() && length_left > 0) {
            copyOfCurrentParm = currentListIterator.next().copyOf();
            copyOfCurrentParm.read(in, length_left);
            replacement.add(copyOfCurrentParm);
            length_left -= copyOfCurrentParm.paramLength();
        }
        setValue(replacement);
    }

    /**
     * Gen an interpretive string of params and fuc ret and reason codes.
     *
     * @return an interpretive string of params and fuc ret and reason codes.
     */
    public String prettyPrintAll() {
        StringBuilder sb = new StringBuilder();
        sb.append(prettyPrintRCAndReason());
        sb.append(prettyPrintParams());
        return sb.toString();
    }

    /**
     * Prettyprint to a string each param in the array.
     *
     * @return an interpretive string containing the prettyprint of each param
     * in the array
     */
    public String prettyPrintParams() {
        StringBuilder sb = new StringBuilder();
        Iterator<VSMParm> i = iterator();
        while (i.hasNext()) {
            sb.append(i.next().prettyPrint());
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Examine the array for ret code and reason code and return an interpretive
     * string.
     *
     * @return an interpretive string for ret code and reason code
     */
    public String prettyPrintRCAndReason() {
        String result;
        VSMParm rc = parameterNamed("return_code");
        VSMParm reason = parameterNamed("reason_code");
        if (rc == null) {
            result = "No parameter named return_code found in " + this;
        } else if (reason == null) {
            result = "No parameter named reason_code found in " + this;
        } else {
            VSMInt4 rc_int4 = VSMInt4.class.cast(rc);
            VSMInt4 reason_int4 = VSMInt4.class.cast(reason);
            result = VsmapiRC.prettyPrint(rc_int4.getValue(), reason_int4.getValue(), getFunction()) + "\n";
        }
        return result;
    }

    /**
     * Exception to throw if there's a Pigiron internal marshalling error in the
     * read.
     */
    public class ParameterArrayReadAllException extends VSMException {

        /**
         * Instance the exception with a specific message.
         *
         * @param message the message
         */
        public ParameterArrayReadAllException(String message) {
            super(message);
        }
    }
}
