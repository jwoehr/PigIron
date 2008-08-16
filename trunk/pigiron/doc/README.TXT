Copyright (c) 2008, Jack J. Woehr jwoehr@softwoehr.com
PO Box 51, Golden, Colorado 80402-0051 USA
All rights reserved.

This is the PigIron project. PigIron is a library of Java class wrappers for the
IBM z/VM Virtual Machine Operating System Systems Management Application
Programming Interface (sometimes called VSMAPI).

Note: PigIron is not associated with the IBM corporation.

Please read the file license.txt which is the license under which the
PigIron open source project is coded, released and distributed.

The general layout for PigIron open source is:

    pigiron/ -+
              + doc/ -+     ... documention source
              |       + pigiron.sourceforge.net/ ... webbage for PigIron's sf site
              + nbproject/  ... build scripts and properties NetBeans style
              + src/        ... the Java source code             


NOTE ON SECURITY (LACK THEREOF):
    * Currently NO secure sockets implementation. Sends your password in
      CLEAR TEXT. Use ONLY on a secure LAN behind a firewall.

Fairmount, Colorado
2008-08-17

