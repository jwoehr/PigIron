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

/* Autogenerated Tue Nov 24 05:20:44 UTC 2015
 * by pigfunc.m4 Copyright *C* 2015 Jack J. Woehr
 * Part of the PigIron Project http://pigiron.sourceforge.net
 */
package com.softwoehr.pigiron.functions;

import java.io.IOException;
import com.softwoehr.pigiron.access.*;

/**
 * <tt>VMRM_Configuration_Update</tt> VSMAPI Function
 */
public class VMRMConfigurationUpdate extends VSMCall {

    /**
     * The transmitted name of the function.
     */
    public static final String FUNCTION_NAME = "VMRM_Configuration_Update";

    /** both  a syntax check and configuration update */
    public static final int SYNCHECK_AND_UPDATE = 0;

    /** only a syntax check of the configuration is done */
    public static final int SYNCHECK_SYNTAX_ONLY = 1;

    /**
     *  Create an instance of the function call with important fields not instanced.
     */
    public VMRMConfigurationUpdate() {
    }

    /**
     * Create an instance with the variables filled in.
     * @param hostname  VSMAPI Host DNS name
     * @param port port VSMAPI Host is listening on
     * @param userid userid executing the function
     * @param password the password
     * @param target_identifier the target of the VSMAPI function
     * @param configuration_file_name instances {@code configurationFileName}
     * @param configuration_file_type instances {@code configurationFileType}
     * @param configuration_dir_name instances {@code configurationDirName}
     * @param syncheck_only instances {@code syncheckOnly}
     * @param update_file instances {@code updateFile}
     */
    public VMRMConfigurationUpdate(String hostname, int port, String userid, String password , String target_identifier, String configuration_file_name, String configuration_file_type, String configuration_dir_name, int syncheck_only, String update_file) {
        this();
        setHostname(hostname);
        setPort(port);
        setUserid(userid);
        setPassword(password);
        setTarget_identifier(target_identifier);
        set_configurationFileName(configuration_file_name);
        set_configurationFileType(configuration_file_type);
        set_configurationDirName(configuration_dir_name);
        set_syncheckOnly(syncheck_only);
        set_updateFile(update_file);
    }

    /** The name of the configuration file. */
    private String configurationFileName = "";

    /** The name of the configuration file. */
    private String configurationFileType = "";

    /** The fully-qualified Shared File System (SFS) directory name where the configuration file is located. */
    private String configurationDirName = "";

    /** Specify a 1 to choose the SYNCHECK option meaning that only a syntax check is done and a 0 to indicate both a syntax check and a configuration file update */
    private int syncheckOnly = 0;

    /** A new complete VMRM configuration file to syntax-check or to replace the old file. */
    private String updateFile = "";

    /** Set the value of {@code  configurationFileName }.
     * @param val The value to set {@code  configurationFileName }.
     */
    public final void set_configurationFileName(String val) {
        configurationFileName = val;
    }

    /** Get the value of {@code  configurationFileName }.
     * @return The value of {@code  configurationFileName }.
     */
    public String get_configurationFileName() {
        return configurationFileName;
    }

    /** Set the value of {@code  configurationFileType }.
     * @param val The value to set {@code  configurationFileType }.
     */
    public final void set_configurationFileType(String val) {
        configurationFileType = val;
    }

    /** Get the value of {@code  configurationFileType }.
     * @return The value of {@code  configurationFileType }.
     */
    public String get_configurationFileType() {
        return configurationFileType;
    }

    /** Set the value of {@code  configurationDirName }.
     * @param val The value to set {@code  configurationDirName }.
     */
    public final void set_configurationDirName(String val) {
        configurationDirName = val;
    }

    /** Get the value of {@code  configurationDirName }.
     * @return The value of {@code  configurationDirName }.
     */
    public String get_configurationDirName() {
        return configurationDirName;
    }

    /** Set the value of {@code  syncheckOnly }.
     * @param val The value to set {@code  syncheckOnly }.
     */
    public final void set_syncheckOnly(int val) {
        syncheckOnly = val;
    }

    /** Get the value of {@code  syncheckOnly }.
     * @return The value of {@code  syncheckOnly }.
     */
    public int get_syncheckOnly() {
        return syncheckOnly;
    }

    /** Set the value of {@code  updateFile }.
     * @param val The value to set {@code  updateFile }.
     */
    public final void set_updateFile(String val) {
        updateFile = val;
    }

    /** Get the value of {@code  updateFile }.
     * @return The value of {@code  updateFile }.
     */
    public String get_updateFile() {
        return updateFile;
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
        tempString = new VSMString(get_configurationFileName(), "configuration_file_name");
        parameterArray.add(new VSMInt4(tempString.paramLength(), "configuration_file_name_length"));
        parameterArray.add(tempString);
        tempString = new VSMString(get_configurationFileType(), "configuration_file_type");
        parameterArray.add(new VSMInt4(tempString.paramLength(), "configuration_file_type_length"));
        parameterArray.add(tempString);
        tempString = new VSMString(get_configurationDirName(), "configuration_dir_name");
        parameterArray.add(new VSMInt4(tempString.paramLength(), "configuration_dir_name_length"));
        parameterArray.add(tempString);
        parameterArray.add(new VSMInt1(get_syncheckOnly(), "syncheck_only"));
        tempString = new VSMString(get_updateFile(), "update_file");
        parameterArray.add(new VSMInt4(tempString.paramLength(), "update_file_length"));
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
     * You can execute the VSMAPI call from <tt>main()</tt>, try it
     * with no args to see the usage message.
     * @param argv array of commandline args
     * @throws IOException on comm error
     * @throws VSMException on internal Pigiron param marshalling error
     */
    public static void main(String[] argv) throws IOException, VSMException {

        VMRMConfigurationUpdate instance = null;

        if (argv.length != 10) {
            System.out.println("usage: args are:\ninetaddr port user pw target_namelist configuration_file_name configuration_file_type configuration_dir_name syncheck_only update_file");
            System.exit(1);
        }

        System.out.println("Args are: " + argv[0] + " " + argv[1] + " " + argv[2] + " " + argv[3] + " " + argv[4] + " " + argv[5] + " " + argv[6]  + " " + argv[7]  + " " + argv[8]  + " " + argv[9]);
        instance = new VMRMConfigurationUpdate(argv[0], Integer.valueOf(argv[1]).intValue(), argv[2], argv[3], argv[4], argv[5], argv[6], argv[7],  Integer.valueOf(argv[8]).intValue(), argv[9]);

        ParameterArray pA = instance.doIt();
        System.out.println("Returns from call to " + instance.getFunctionName() + ":");
        System.out.println(pA.prettyPrintAll());
    }
}
