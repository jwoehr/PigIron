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

/* Autogenerated Thu Jan  8 21:04:51 UTC 2009
 * by pigfunc.m4 Copyright *C* 2008 Jack J. Woehr
 * Part of the PigIron Project http://pigiron.sourceforge.net
 */
package com.softwoehr.pigiron.functions;

import java.io.IOException;
import com.softwoehr.pigiron.access.*;

/**
 * {@code Asynchronous_Notification_Enable_DM} VSMAPI Function
 */
public class AsynchronousNotificationEnableDM extends VSMCall {

    /**
     * The transmitted name of the function.
     */
    public static final String FUNCTION_NAME = "Asynchronous_Notification_Enable_DM";

    /** Unspecified */
    public static final int ENCODING_UNSPECIFIED = 0;

    /** ASCII */
    public static final int ENCODING_ASCII = 1;

    /** EBCDIC */
    public static final int ENCODING_EBCDIC = 2;

    /** DIRECTORY */
    public static final int ENTITY_TYPE_DIRECTORY = 1;

    /** target_identifier will receive notifications for associated directory changes */
    public static final int SUBSCRIPTION_TYPE_INCLUDE = 1;

    /** target_identifier will not receive notifications for associated directory changes */
    public static final int SUBSCRIPTION_TYPE_EXCLUDE = 2;

    /** TCP */
    public static final int COMMUNICATION_TYPE_TCP = 1;

    /** UDP */
    public static final int COMMUNICATION_TYPE_UDP = 2;

    /**
     *  Create an instance of the function call with important fields not instanced.
     */
    public AsynchronousNotificationEnableDM() {
    }

    /**
     * Create an instance with the variables filled in.
     * @param hostname  VSMAPI Host DNS name
     * @param port port VSMAPI Host is listening on
     * @param userid userid executing the function
     * @param password the password
     * @param target_identifier the target of the VSMAPI function
     * @param entity_type instances {@code entityType}
     * @param subscription_type instances {@code subscriptionType}
     * @param communication_type instances {@code communicationType}
     * @param port_number instances {@code portNumber}
     * @param ip_address instances {@code ipAddress}
     * @param encoding instances {@code encoding}
     * @param subscriber_data instances {@code subscriberData}
     */
    public AsynchronousNotificationEnableDM(String hostname, int port, String userid, String password, String target_identifier, int entity_type, int subscription_type, int communication_type, int port_number, String ip_address, int encoding, String subscriber_data) {
        this();
        setHostname(hostname);
        setPort(port);
        setUserid(userid);
        setPassword(password);
        setTarget_identifier(target_identifier);
        set_entityType(entity_type);
        set_subscriptionType(subscription_type);
        set_communicationType(communication_type);
        set_portNumber(port_number);
        set_ipAddress(ip_address);
        set_encoding(encoding);
        set_subscriberData(subscriber_data);
    }

    /** The entity type for which notifications will be sent. */
    private int entityType = 0;

    /** The subscription type */
    private int subscriptionType = 0;

    /** The communication used for notifications */
    private int communicationType = 0;

    /** The port number of the socket that will receive the notifications */
    private int portNumber = -1;

    /** he IPV4 dotted-decimal IP address of the socket that will receive the notifications. */
    private String ipAddress = "*";

    /** The encoding of the notification data string */
    private int encoding = 0;

    /** Anything the subscriber wishes to receive along with the notifications */
    private String subscriberData = "";

    /** Set the value of {@code  entityType }.
     * @param val The value to set {@code  entityType }.
     */
    public void set_entityType(int val) {
        entityType = val;
    }

    /** Get the value of {@code  entityType }.
     * @return The value of {@code  entityType }.
     */
    public int get_entityType() {
        return entityType;
    }

    /** Set the value of {@code  subscriptionType }.
     * @param val The value to set {@code  subscriptionType }.
     */
    public void set_subscriptionType(int val) {
        subscriptionType = val;
    }

    /** Get the value of {@code  subscriptionType }.
     * @return The value of {@code  subscriptionType }.
     */
    public int get_subscriptionType() {
        return subscriptionType;
    }

    /** Set the value of {@code  communicationType }.
     * @param val The value to set {@code  communicationType }.
     */
    public void set_communicationType(int val) {
        communicationType = val;
    }

    /** Get the value of {@code  communicationType }.
     * @return The value of {@code  communicationType }.
     */
    public int get_communicationType() {
        return communicationType;
    }

    /** Set the value of {@code  portNumber }.
     * @param val The value to set {@code  portNumber }.
     */
    public void set_portNumber(int val) {
        portNumber = val;
    }

    /** Get the value of {@code  portNumber }.
     * @return The value of {@code  portNumber }.
     */
    public int get_portNumber() {
        return portNumber;
    }

    /** Set the value of {@code  ipAddress }.
     * @param val The value to set {@code  ipAddress }.
     */
    public void set_ipAddress(String val) {
        ipAddress = val;
    }

    /** Get the value of {@code  ipAddress }.
     * @return The value of {@code  ipAddress }.
     */
    public String get_ipAddress() {
        return ipAddress;
    }

    /** Set the value of {@code  encoding }.
     * @param val The value to set {@code  encoding }.
     */
    public void set_encoding(int val) {
        encoding = val;
    }

    /** Get the value of {@code  encoding }.
     * @return The value of {@code  encoding }.
     */
    public int get_encoding() {
        return encoding;
    }

    /** Set the value of {@code  subscriberData }.
     * @param val The value to set {@code  subscriberData }.
     */
    public void set_subscriberData(String val) {
        subscriberData = val;
    }

    /** Get the value of {@code  subscriberData }.
     * @return The value of {@code  subscriberData }.
     */
    public String get_subscriberData() {
        return subscriberData;
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
        parameterArray.add(new VSMInt1(get_entityType(), "entity_type"));
        parameterArray.add(new VSMInt1(get_subscriptionType(), "subscription_type"));
        parameterArray.add(new VSMInt1(get_communicationType(), "communication_type"));
        parameterArray.add(new VSMInt4(get_portNumber(), "port_number"));
        tempString = new VSMString(get_ipAddress(), "ip_address");
        parameterArray.add(new VSMInt4(tempString.paramLength(), "ip_address_length"));
        parameterArray.add(tempString);
        parameterArray.add(new VSMInt1(get_encoding(), "encoding"));
        tempString = new VSMString(get_subscriberData(), "subscriber_data");
        parameterArray.add(new VSMInt4(tempString.paramLength(), "subscriber_data_length"));
        parameterArray.add(tempString);
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

        AsynchronousNotificationEnableDM instance = null;

        if (argv.length != 12) {
            System.out.println("usage: args are:\ninetaddr port user pw target_id entity_type subscription_type communication_type port_number ip_address encoding subscriber_data");
            System.exit(1);
        }

        System.out.println("Args are: " + argv[0] + " " + argv[1] + " " + argv[2] + " " + argv[3] + " " + argv[4] + " " + argv[5] + " " + argv[6] + " " + argv[7] + " " + argv[8] + " " + argv[9] + " " + argv[10] + " " + argv[11]);
        instance = new AsynchronousNotificationEnableDM(argv[0], Integer.valueOf(argv[1]).intValue(), argv[2], argv[3], argv[4],Integer.valueOf(argv[5]).intValue(), Integer.valueOf(argv[6]).intValue(), Integer.valueOf(argv[7]).intValue(), Integer.valueOf(argv[8]).intValue(), argv[9], Integer.valueOf(argv[10]).intValue(), argv[11]);
        ParameterArray pA = instance.doIt();
        System.out.println("Returns from call to " + instance.getFunctionName() + ":");
        System.out.println(pA.prettyPrintAll());
    }
}
