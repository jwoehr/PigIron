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

/* Autogenerated Wed Sep 24 08:31:03 GMT 2008
 * by pigfunc.m4 Copyright *C* 2008 Jack J. Woehr
 * Part of the PigIron Project http://pigiron.sourceforge.net
 */
package com.softwoehr.pigiron.functions;

import java.io.IOException;
import com.softwoehr.pigiron.access.*;

/**
 * {@code Image_CPU_Define_DM} VSMAPI 5.4 Function
 * @see com.softwoehr.pigiron.access.paramstructs.
 * @since <a href="http://publib.boulder.ibm.com/infocenter/zvm/v5r4/index.jsp">VSMAPI 5.4</a>
 */
public class ImageCPUDefineDM extends VSMCall {

    /**
     * The transmitted name of the function.
     */
    public static final String FUNCTION_NAME = "Image_CPU_Define_DM";

    /** Unspecified */
    public static final int BASE_CPU_UNSPECIFIED = 0;

    /** BASE */
    public static final int BASE_CPU_BASE = 1;

    /** Unspecified */
    public static final int DEDICATE_CPU_UNSPECIFIED = 0;

    /** NODEDICATE */
    public static final int DEDICATE_CPU_NODEDICATE = 1;

    /** DEDICATE */
    public static final int DEDICATE_CPU_DEDICATE = 2;

    /** Unspecified - no CRYPTO */
    public static final int CRYPTO_UNSPECIFIED = 0;

    /** ZAAP */
    public static final int CRYPTO_CRYPTO = 1;

    /**
     *  Create an instance of the function call with important fields not instanced.
     */
    public ImageCPUDefineDM() {
    }

    /**
     * Create an instance with the variables filled in.
     * @param hostname  VSMAPI Host DNS name
     * @param port port VSMAPI Host is listening on
     * @param userid userid executing the function
     * @param password the password
     * @param target_identifier the target of the VSMAPI function
     * @param cpu_address instances {@code cpuAddress}
     * @param base_cpu instances {@code baseCpu}
     * @param cpu_id instances {@code cpuId}
     * @param dedicate_cpu instances {@code dedicateCpu}
     * @param crypto instances {@code crypto}
     */
    public ImageCPUDefineDM(String hostname, int port, String userid, String password, String target_identifier, String cpu_address, int base_cpu, String cpu_id, int dedicate_cpu, int crypto) {
        this();
        setHostname(hostname);
        setPort(port);
        setUserid(userid);
        setPassword(password);
        setTarget_identifier(target_identifier);
        set_cpuAddress(cpu_address);
        set_baseCpu(base_cpu);
        set_cpuId(cpu_id);
        set_dedicateCpu(dedicate_cpu);
        set_crypto(crypto);
    }

    /** The virtual CPU address to add to the static definition of the virtual image in the hexadecimal range of 0-3F. */
    private String cpuAddress = "";

    /** Whether this CPU defines the base virtual processor. */
    private int baseCpu = 0;

    /** The processor identification number to be stored in bits 8 through 31 of the CPU ID. */
    private String cpuId = "";

    /** Whether the virtual processor is to be dedicated at LOGON time to a real processor. */
    private int dedicateCpu = 0;

    /** Whether the virtual Cryptographic Coprocessor Facility (CCF) should be defined automatically for the virtual CPU at LOGON time. */
    private int crypto = 0;

    /** Set the value of {@code  cpuAddress }.
     * @param val The value to set {@code  cpuAddress }.
     */
    public void set_cpuAddress(String val) {
        cpuAddress = val;
    }

    /** Get the value of {@code  cpuAddress }.
     * @return The value of {@code  cpuAddress }.
     */
    public String get_cpuAddress() {
        return cpuAddress;
    }

    /** Set the value of {@code  baseCpu }.
     * @param val The value to set {@code  baseCpu }.
     */
    public void set_baseCpu(int val) {
        baseCpu = val;
    }

    /** Get the value of {@code  baseCpu }.
     * @return The value of {@code  baseCpu }.
     */
    public int get_baseCpu() {
        return baseCpu;
    }

    /** Set the value of {@code  cpuId }.
     * @param val The value to set {@code  cpuId }.
     */
    public void set_cpuId(String val) {
        cpuId = val;
    }

    /** Get the value of {@code  cpuId }.
     * @return The value of {@code  cpuId }.
     */
    public String get_cpuId() {
        return cpuId;
    }

    /** Set the value of {@code  dedicateCpu }.
     * @param val The value to set {@code  dedicateCpu }.
     */
    public void set_dedicateCpu(int val) {
        dedicateCpu = val;
    }

    /** Get the value of {@code  dedicateCpu }.
     * @return The value of {@code  dedicateCpu }.
     */
    public int get_dedicateCpu() {
        return dedicateCpu;
    }

    /** Set the value of {@code  crypto }.
     * @param val The value to set {@code  crypto }.
     */
    public void set_crypto(int val) {
        crypto = val;
    }

    /** Get the value of {@code  crypto }.
     * @return The value of {@code  crypto }.
     */
    public int get_crypto() {
        return crypto;
    }

    /**
     * Marshall parameters for the VSMAPI function call.
     * "Input" as in "input to VSMAPI".
     * @return the composed input ParameterArray
     * @see #composeOutputArray()
     * @see com.softwoehr.pigiron.access.ParameterArray
     */
    protected ParameterArray composeInputArray() {
        VSMString tempString = null;
        ParameterArray parameterArray = new ParameterArray();
        tempString = new VSMString(getFunctionName(), getFunctionName());
        parameterArray.add(new VSMInt4(tempString.paramLength(), "function_name_length"));
        parameterArray.add(tempString);
        tempString = new VSMString(getUserid(), "authenticated_userid");
        parameterArray.add(new VSMInt4(tempString.paramLength(), "authenticated_userid_length"));
        parameterArray.add(tempString);
        tempString = new VSMString(getPassword(), "password");
        parameterArray.add(new VSMInt4(tempString.paramLength(), "password_length"));
        parameterArray.add(tempString);
        tempString = new VSMString(getTarget_identifier(), "target_identifier");
        parameterArray.add(new VSMInt4(tempString.paramLength(), "target_identifier_length"));
        parameterArray.add(tempString);
        tempString = new VSMString(get_cpuAddress(), "cpu_address");
        parameterArray.add(new VSMInt4(tempString.paramLength(), "cpu_address_length"));
        parameterArray.add(tempString);
        parameterArray.add(new VSMInt1(get_baseCpu(), "base_cpu"));
        tempString = new VSMString(get_cpuId(), "cpu_id");
        parameterArray.add(new VSMInt4(tempString.paramLength(), "cpu_id_length"));
        parameterArray.add(tempString);
        parameterArray.add(new VSMInt1(get_dedicateCpu(), "dedicate_cpu"));
        parameterArray.add(new VSMInt1(get_crypto(), "crypto"));
        VSMInt4 outputLength = new VSMInt4(new Long(parameterArray.totalParameterLength()).intValue(), "output_length");
        parameterArray.insertElementAt(outputLength, 0);
        setInParams(parameterArray);
        return parameterArray;
    }

    /**
     * Marshall parameters for the return of the VSMAPI function call.
     * "output" as in "output from VSMAPI"
     * @return the composed output ParameterArray
     * @see #composeInputArray()
     * @see com.softwoehr.pigiron.access.ParameterArray
     */
    protected ParameterArray composeOutputArray() {
        ParameterArray parameterArray = new ParameterArray();
        parameterArray.add(new VSMInt4(-1, "request_id_immediate"));
        parameterArray.add(new VSMInt4(-1, "output_length"));
        parameterArray.add(new VSMInt4(-1, "request_id"));
        parameterArray.add(new VSMInt4(-1, "return_code"));
        parameterArray.add(new VSMInt4(-1, "reason_code"));
        setOutParams(parameterArray);
        return parameterArray;
    }

    /**
     * Get the formal name of the VSMAPI function.
     * @return the formal name of the VSMAPI function.
     */
    @Override
    public String getFunctionName() {
        return FUNCTION_NAME;
    }

    /**
     * You can execute the VSMAPI call from {@code main()}, try it
     * with no args to see the usage message.
     * @param argv array of commandline args
     * @throws IOException on comm error
     * @throws VSMException on internal Pigiron param marshalling error
     */
    public static void main(String[] argv) throws IOException, VSMException {

        ImageCPUDefineDM instance = null;

        if (argv.length != 10) {
            System.out.println("usage: args are:\ninetaddr port user pw target_id cpu_address base_cpu cpu_id dedicate_cpu crypto");
            System.exit(1);
        }

        System.out.println("Args are: " + argv[0] + " " + argv[1] + " " + argv[2] + " " + argv[3] + " " + argv[4] + " " + argv[5] + " " + argv[6] + " " + argv[7] + " " + argv[8] + " " + argv[9]);
        instance = new ImageCPUDefineDM(argv[0], Integer.valueOf(argv[1]).intValue(), argv[2], argv[3], argv[4], argv[5], Integer.valueOf(argv[6]).intValue(), argv[7],Integer.valueOf(argv[8]).intValue(), Integer.valueOf(argv[9]).intValue());

        ParameterArray pA = instance.doIt();
        System.out.println("Returns from call to " + instance.getFunctionName() + ":");
        System.out.println(pA.prettyPrintAll());
    }
}
