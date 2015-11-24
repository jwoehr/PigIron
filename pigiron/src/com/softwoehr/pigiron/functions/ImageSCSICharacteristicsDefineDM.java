/*
 * Copyright (c) 2008, 2015, Jack J. Woehr jwoehr@softwoehr.com
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

/* Autogenerated Tue Nov 24 05:20:45 UTC 2015
 * by pigfunc.m4 Copyright *C* 2015 Jack J. Woehr
 * Part of the PigIron Project http://pigiron.sourceforge.net
 */
package com.softwoehr.pigiron.functions;

import java.io.IOException;
import com.softwoehr.pigiron.access.*;

/**
 * {@code Image_SCSI_Characteristics_Define_DM} VSMAPI Function
 */
public class ImageSCSICharacteristicsDefineDM extends VSMCall {

    /**
     * The transmitted name of the function.
     */
    public static final String FUNCTION_NAME = "Image_SCSI_Characteristics_Define_DM";

    /** Unspecified */
    public static final int SCP_DATA_TYPE_UNSPECIFIED = 0;

    /** delete the SCP_data for the image */
    public static final int SCP_DATA_TYPE_DELETE = 1;

    /** EBCDIC (codepage 924) data */
    public static final int SCP_DATA_TYPE_EBCDIC = 2;

    /** UTF-8 encoded hex data */
    public static final int SCP_DATA_TYPE_HEX = 3;

    /**
     *  Create an instance of the function call with important fields not instanced.
     */
    public ImageSCSICharacteristicsDefineDM() {
    }

    /**
     * Create an instance with the variables filled in.
     * @param hostname  VSMAPI Host DNS name
     * @param port port VSMAPI Host is listening on
     * @param userid userid executing the function
     * @param password the password
     * @param target_identifier the target of the VSMAPI function
     * @param boot_program instances {@code bootProgram}
     * @param BR_LBA instances {@code bRLBA}
     * @param LUN instances {@code lUN}
     * @param port_name instances {@code portName}
     * @param SCP_data_type instances {@code sCPDataType}
     * @param SCP_data instances {@code sCPData}
     */
    public ImageSCSICharacteristicsDefineDM(String hostname, int port, String userid, String password , String target_identifier, String boot_program, String BR_LBA, String LUN, String port_name, int SCP_data_type, String SCP_data) {
        this();
        setHostname(hostname);
        setPort(port);
        setUserid(userid);
        setPassword(password);
        setTarget_identifier(target_identifier);
        set_bootProgram(boot_program);
        set_bRLBA(BR_LBA);
        set_lUN(LUN);
        set_portName(port_name);
        set_sCPDataType(SCP_data_type);
        set_sCPData(SCP_data);
    }

    /** The boot program number, or the keyword "DELETE" to delete the existing boot program number. If null, the boot program number will be unchanged. */
    private String bootProgram = "";

    /** The logical-block address of the boot record, or the keyword "DELETE" to delete the existing logical-block address. If null, the logical-block address will be unchanged. */
    private String bRLBA = "";

    /** The logical unit number, or the keyword "DELETE" to delete the existing logical unit number. If null, the logical unit number will be unchanged. */
    private String lUN = "";

    /** The port name, or the keyword "DELETE" to delete the existing port name. If null, the port name will be unchanged. */
    private String portName = "";

    /** The type of data specified in the SCP_data parameter */
    private int sCPDataType = 0;

    /** The SCP data */
    private String sCPData = "";

    /** Set the value of {@code  bootProgram }.
     * @param val The value to set {@code  bootProgram }.
     */
    public final void set_bootProgram(String val) {
        bootProgram = val;
    }

    /** Get the value of {@code  bootProgram }.
     * @return The value of {@code  bootProgram }.
     */
    public String get_bootProgram() {
        return bootProgram;
    }

    /** Set the value of {@code  bRLBA }.
     * @param val The value to set {@code  bRLBA }.
     */
    public final void set_bRLBA(String val) {
        bRLBA = val;
    }

    /** Get the value of {@code  bRLBA }.
     * @return The value of {@code  bRLBA }.
     */
    public String get_bRLBA() {
        return bRLBA;
    }

    /** Set the value of {@code  lUN }.
     * @param val The value to set {@code  lUN }.
     */
    public final void set_lUN(String val) {
        lUN = val;
    }

    /** Get the value of {@code  lUN }.
     * @return The value of {@code  lUN }.
     */
    public String get_lUN() {
        return lUN;
    }

    /** Set the value of {@code  portName }.
     * @param val The value to set {@code  portName }.
     */
    public final void set_portName(String val) {
        portName = val;
    }

    /** Get the value of {@code  portName }.
     * @return The value of {@code  portName }.
     */
    public String get_portName() {
        return portName;
    }

    /** Set the value of {@code  sCPDataType }.
     * @param val The value to set {@code  sCPDataType }.
     */
    public final void set_sCPDataType(int val) {
        sCPDataType = val;
    }

    /** Get the value of {@code  sCPDataType }.
     * @return The value of {@code  sCPDataType }.
     */
    public int get_sCPDataType() {
        return sCPDataType;
    }

    /** Set the value of {@code  sCPData }.
     * @param val The value to set {@code  sCPData }.
     */
    public final void set_sCPData(String val) {
        sCPData = val;
    }

    /** Get the value of {@code  sCPData }.
     * @return The value of {@code  sCPData }.
     */
    public String get_sCPData() {
        return sCPData;
    }

    /**
     * Marshall parameters for the VSMAPI function call.
     * "Input" as in "input to VSMAPI".
     * @return the composed input ParameterArray
     * @see #composeOutputArray()
     * @see com.softwoehr.pigiron.access.ParameterArray
     */
    protected ParameterArray composeInputArray() {
        VSMString tempString;
        ParameterArray parameterArray = new ParameterArray(this);
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
        tempString = new VSMString(get_bootProgram(), "boot_program");
        parameterArray.add(new VSMInt4(tempString.paramLength(), "boot_program_length"));
        parameterArray.add(tempString);
        tempString = new VSMString(get_bRLBA(), "BR_LBA");
        parameterArray.add(new VSMInt4(tempString.paramLength(), "BR_LBA_length"));
        parameterArray.add(tempString);
        tempString = new VSMString(get_lUN(), "LUN");
        parameterArray.add(new VSMInt4(tempString.paramLength(), "LUN_length"));
        parameterArray.add(tempString);
        tempString = new VSMString(get_portName(), "port_name");
        parameterArray.add(new VSMInt4(tempString.paramLength(), "port_name_length"));
        parameterArray.add(tempString);
        parameterArray.add(new VSMInt1(get_sCPDataType(), "SCP_data_type"));
        tempString = new VSMString(get_sCPData(), "SCP_data");
        parameterArray.add(new VSMInt4(tempString.paramLength(), "SCP_data_length"));
        parameterArray.add(tempString);
        VSMInt4 outputLength = new VSMInt4(new Long(parameterArray.totalParameterLength()).intValue(), "output_length");
        parameterArray.add(0,outputLength);
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
        ParameterArray parameterArray = new ParameterArray(this);
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

        ImageSCSICharacteristicsDefineDM instance = null;

        if (argv.length != 11) {
            System.out.println("usage: args are:\ninetaddr port user pw target_id boot_program BR_LBA LUN port_name SCP_data_type SCP_data");
            System.exit(1);
        }

        System.out.println("Args are: " + argv[0] + " " + argv[1] + " " + argv[2] + " " + argv[3] + " " + argv[4] + " " + argv[5] + " " + argv[6] + " " + argv[7] + " " + argv[8] + " " + argv[9] + " " + argv[10]);
        instance = new ImageSCSICharacteristicsDefineDM(argv[0], Integer.valueOf(argv[1]).intValue(), argv[2], argv[3], argv[4], argv[5], argv[6], argv[7], argv[8], Integer.valueOf(argv[9]).intValue(), argv[10]);

        ParameterArray pA = instance.doIt();
        System.out.println("Returns from call to " + instance.getFunctionName() + ":");
        System.out.println(pA.prettyPrintAll());
    }
}
