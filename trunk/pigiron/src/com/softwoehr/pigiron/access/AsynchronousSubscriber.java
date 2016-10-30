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

import java.io.PrintStream;
import java.io.DataInputStream;
import java.net.InetAddress;
import javax.net.ServerSocketFactory;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * A simple TCP subscriber to Directory Manager async notifications as described
 * in the VSMAPI documentation for
 * <a href="http://publib.boulder.ibm.com/infocenter/zvm/v5r4/index.jsp?topic=/com.ibm.zvm.v54.dmse6/cslane.htm">
 * Asynchronous_Notification_Enable_DM</a>. Reads notifications and prints them
 * on a PrintStream. The example {@code main()} routine uses {@code System.out}
 * as the PrintStream.
 *
 * This is the basic use case:
 * <ol>
 * <li>Create the server</li>
 * <li>Loop either {@code accept()}ing or timing out</li>
 * <li>If the quit flag gets set (e.g., from another thread), exit the loop</li>
 * </ol>
 * To use this subscriber in conjunction with a subscription:
 * <ol>
 * <li>Pick an ip addr, a port, a backlog (e.g., 50) and a timeout in ms (0
 * means "never") and invoke the ctor</li>
 * <li>Use {@code subscriptionLoop()} to loop around the implicit
 * {@code accept()} waiting for the quit flag.</li>
 * <li>Use
 * {@code com.softwoehr.pigiron.functions.AsynchronousNotificationEnableDM} to
 * start subscriptions to this port (on the host on which you started the
 * AsynchronousSubscriber).</li>
 * <li>Eventually perhaps cancel your subscription via
 * {@code com.softwoehr.pigiron.functions.AsynchronousNotificationDisableDM}</li>
 * <li>AsynchronousSubscriber.quit() (e.g., issued from another thread) sets the
 * quit flag so the next time the server falls out of the accept() loop, it will
 * exit.</li>
 * </ol>
 *
 * @author jax
 * @created November 16, 2008
 * @see com.softwoehr.pigiron.access.ParameterArray
 * @see com.softwoehr.pigiron.functions.AsynchronousNotificationEnableDM
 * @see com.softwoehr.pigiron.functions.AsynchronousNotificationDisableDM
 */
public class AsynchronousSubscriber {

    private boolean quitFlag = false;

    /**
     * The actual server socket
     */
    protected ServerSocket myServerSocket = null;

    /**
     * Simple Constructor for the AsynchronousSubscriber object is not useful.
     */
    protected AsynchronousSubscriber() {
    }

    /**
     * Parameterized constructor for the AsynchronousSubscriber object expects
     * an address, a port, a backlog queue size for the server, and a timeout
     * for falling out of {@code accept()}.
     *
     * @param inetaddress interface to bind on
     * @param port port to bind on
     * @param backlog backlog queue size
     * @param timeout accept timeout in ms -- Since {@code subscriptionLoop()}
     * loops around this timeout in the accept(), it is best that the timeout
     * not be 0 but some reasonable value between 1500 - 15000
     * @exception java.io.IOException on I/O error
     * @exception java.net.UnknownHostException on bad interface name or num
     * @see #subscriptionLoop
     */
    public AsynchronousSubscriber(InetAddress inetaddress, int port, int backlog, int timeout) throws java.io.IOException, java.net.UnknownHostException {
        myServerSocket = ServerSocketFactory.getDefault()
                .createServerSocket(port, backlog, inetaddress == null ? InetAddress.getLocalHost() : inetaddress);
        /*
		 *  myServerSocket = new ServerSocket(port, 50, inetaddress == null ? InetAddress.getLocalHost() : inetaddress);
         */
        setTimeout(timeout);
        System.out.println("My server socket is:   " + myServerSocket);
        System.out.println("My inet address  is:   " + myServerSocket.getInetAddress() + ":" + myServerSocket.getLocalPort());
        System.out.println("My backlog was set to: " + backlog);
        System.out.println("My socket  is bound:   " + myServerSocket.isBound());
    }

    /**
     * Marshall parameters to read the Notification. "output" as in "output from
     * VSMAPI" Modelled on the same function used in all VSMCall extenders.
     *
     * @return the composed output ParameterArray
     * @see com.softwoehr.pigiron.access.ParameterArray
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
     * Read one of the messages from the notification you subscribed to via
     * AsynchronousNotificationEnableDM
     *
     * @return ParameterArray with the message from notifier
     * @exception java.io.IOException Any I/O exception other than
     * java.net.SocketTimeoutException (which we catch and ignore, this is just
     * {@code accept()} timing out in a loop)
     * @exception com.softwoehr.pigiron.access.VSMException Error in parameter
     * marshalling
     */
    ParameterArray readNotification() throws java.io.IOException, com.softwoehr.pigiron.access.VSMException {
        ParameterArray pa = null;
        try {
            Socket socket = myServerSocket.accept();
            DataInputStream din = new DataInputStream(socket.getInputStream());
            pa = composeOutputArray();
            pa.readAll(din);
            socket.close();
        } catch (java.net.SocketTimeoutException ex) {
            // do nothing on this particular exception
            // maybe log?
        }

        return pa;
        // possibly null
    }

    /**
     * Loop either reading notifications or timing out in {@code accept()} and
     * listening again.
     *
     * @param printstream PrintStream to report on
     * @exception java.io.IOException Any I/O exception other than
     * java.net.SocketTimeoutException (which we ignore, this is a loop)
     * @exception com.softwoehr.pigiron.access.VSMException Error in parameter
     * marshalling
     */
    public void subscriptionLoop(PrintStream printstream) throws java.io.IOException, com.softwoehr.pigiron.access.VSMException {
        ParameterArray pa;
        while (!quitFlag) {
            pa = readNotification();
            if (pa != null) {
                printstream.println(pa.prettyPrintParams());
            } else {
                // debug
                System.out.println("accept timeout");
            }
        }
    }

    /**
     * Sets the timeout attribute of the AsynchronousSubscriber object
     *
     * @param timeout The new timeout value
     * @exception java.net.SocketException Description of the Exception
     */
    public final void setTimeout(int timeout) throws java.net.SocketException {
        if (myServerSocket != null) {
            myServerSocket.setSoTimeout(timeout);
        }
    }

    /**
     * A flag to terminate the server after the next time it either services a
     * notification or simply times out in accept()
     *
     * @param quitOrNot If true, signal subscriptionLoop to quit, if false, can
     * continue
     * @see #subscriptionLoop
     */
    public void quit(boolean quitOrNot) {
        quitFlag = quitOrNot;
    }

    /**
     * The main program for the AsynchronousSubscriber class illustrates the
     * basic use case:
     * <ol>
     * <li>Create the server</li>
     * <li>Loop either {@code accept()}ing or timing out</li>
     * <li>If the quit flag gets set (e.g., from another thread), exit the
     * loop</li>
     * </ol>
     *
     * @param argv The four command line arguments (inetaddress port backlog
     * timeout)
     * @exception java.io.IOException Description of the Exception
     * @exception java.net.UnknownHostException Description of the Exception
     * @exception com.softwoehr.pigiron.access.VSMException Description of the
     * Exception
     */
    public static void main(String[] argv) throws java.io.IOException, java.net.UnknownHostException, com.softwoehr.pigiron.access.VSMException {
        if (argv.length != 4) {
            System.out.println("usage: AsynchronousSubscribe inetaddress port backlog timeout");
            System.exit(1);
        }

        InetAddress inetaddr;
        if (argv[0].equals("")) {
            inetaddr = InetAddress.getLocalHost();
        } else {
            inetaddr = InetAddress.getByName(argv[0]);
        }

        int port = Integer.parseInt(argv[1]);
        int backlog = Integer.parseInt(argv[2]);
        int timeout = Integer.parseInt(argv[3]);

        System.out.println("Creating AsynchronousSubscriber on " + inetaddr + ":" + port + " with backlog of " + backlog + " with timeout of " + timeout + ".");
        AsynchronousSubscriber as = new AsynchronousSubscriber(inetaddr, port, backlog, timeout);
        as.subscriptionLoop(System.out);
    }
}
