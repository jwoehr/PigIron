/*
 *  Copyright (c) 2008, Jack J. Woehr jwoehr@softwoehr.com
 *  PO Box 51, Golden, Colorado 80402-0051 USA
 *  All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions
 *  are met:
 *
 *  * Redistributions of source code must retain the above copyright
 *  notice, this currentListIterator of conditions and the following disclaimer.
 *  * Redistributions in binary form must reproduce the above copyright
 *  notice, this currentListIterator of conditions and the following disclaimer
 *  in the documentation and/or other materials provided with the
 *  distribution.
 *  * Neither the name of the PigIron Project nor the names of its
 *  contributors may be used to endorse or promote products derived
 *  from this software without specific prior written permission.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 *  AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 *  IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 *  ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 *  LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 *  SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 *  INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 *  CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 *  ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 *  THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.softwoehr.pigiron.access;

import java.io.InputStream;
import java.io.PrintStream;
import java.io.DataInputStream;
import java.net.InetAddress;
import javax.net.ServerSocketFactory;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *  A simple TCP subscriber to Directory Manager async notifications as
 * described in the VSMAPI documentation for
 * <a href="http://publib.boulder.ibm.com/infocenter/zvm/v5r4/index.jsp?topic=/com.ibm.zvm.v54.dmse6/cslane.htm">
 * Asynchronous_Notification_Enable_DM</a>. Reads notifications and prints them
 * on an PrintStream. The example {@code main()} routine uses {@code System.out}
 * as the PrintStream.
 *
 * To use this subscriber:
 * <ol>
 * <li>Pick a port and invoke the ctor on that port.</li>
 * <li>Use com.softwoehr.pigiron.functions.AsynchronousNotificationEnableDM to start subscriptions to this port (on the host on which you started the AsynchronousSubscriber)<li>
 * </ol>
 *
 * @author     jax
 * @created    November 16, 2008
 * @see        com.softwoehr.pigiron.access.ParameterArray
 */
public class AsynchronousSubscriber {
	private ServerSocket myServerSocket = null;
	private boolean quitFlag = false;

	/**
	 *Constructor for the AsynchronousSubscriber object
	 *
	 * @param  port                               Description of the Parameter
	 * @exception  java.io.IOException            Description of the Exception
	 * @exception  java.net.UnknownHostException  Description of the Exception
	 */
	AsynchronousSubscriber(int port) throws java.io.IOException, java.net.UnknownHostException {
		// myServerSocket = ServerSocketFactory.getDefault().createServerSocket(port);
		myServerSocket = new ServerSocket(port, 50, InetAddress.getLocalHost());
		myServerSocket.setSoTimeout(0);
		System.out.println("My server socket is: " + myServerSocket);
		System.out.println("My inet address  is: " + myServerSocket.getInetAddress());
		System.out.println("My socket  is bound: " + myServerSocket.isBound());
	}

	/**
	 * Marshall parameters to read the Notification.
	 * "output" as in "output from VSMAPI"
	 * Modelled on the same function used in all VSMCall extenders.
	 *
	 * @return    the composed output ParameterArray
	 * @see       com.softwoehr.pigiron.access.ParameterArray
	 */
	protected ParameterArray composeOutputArray() {
		ParameterArray parameterArray = new ParameterArray();
		parameterArray.add(new VSMInt4(-1, "userid_length"));
		parameterArray.add(new VSMString("", "userid"));
		parameterArray.add(new VSMInt4(-1, "user_word_length"));
		parameterArray.add(new VSMString("", "user_word"));
		parameterArray.add(new VSMInt4(-1, "sub_data_length"));
		parameterArray.add(new VSMString("", "sub_data"));
		return parameterArray;
	}

	/**
	 *  Description of the Method
	 *
	 * @return                                                Description of the Return Value
	 * @exception  java.io.IOException                        Description of the Exception
	 * @exception  com.softwoehr.pigiron.access.VSMException  Description of the Exception
	 */
	ParameterArray readNotification() throws java.io.IOException, com.softwoehr.pigiron.access.VSMException {
		Socket socket = myServerSocket.accept();
		DataInputStream din = new DataInputStream(socket.getInputStream());
		ParameterArray pa = composeOutputArray();
		pa.readAll(din);
		socket.close();
		return pa;
	}

	/**
	 *  Description of the Method
	 *
	 * @param  os                       Description of the Parameter
	 * @exception  java.io.IOException  Description of the Exception
	 */
	public void subscriptionLoop(PrintStream os) throws java.io.IOException, com.softwoehr.pigiron.access.VSMException {
		while (!quitFlag) {
			os.println(readNotification().prettyPrintParams());
		}
	}

	/**
	 *  The main program for the AsynchronousSubscriber class
	 *
	 * @param  argv                               The command line arguments
	 * @exception  java.io.IOException            Description of the Exception
	 * @exception  java.net.UnknownHostException  Description of the Exception
	 */
	public static void main(String[] argv) throws java.io.IOException, java.net.UnknownHostException, com.softwoehr.pigiron.access.VSMException  {
		int port = new Integer(argv[0]).intValue();
		System.out.println("Will bind to port " + port + ".");
		AsynchronousSubscriber as = new AsynchronousSubscriber(port);
		as.subscriptionLoop(System.out);
	}
}

