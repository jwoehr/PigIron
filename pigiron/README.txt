Copyright (c) 2008, Jack J. Woehr jwoehr@softwoehr.com
PO Box 51, Golden, Colorado 80402-0051 USA
All rights reserved.

This is the PigIron project. PigIron is a library of Java class wrappers for the
IBM z/VM Virtual Machine Operating System Systems Management Application
Programming Interface (sometimes called VSMAPI). http://pigiron.sourceforge.net

Additionally, in the course of testing PigIron we have effectively created an
Open ObjectRexx bridge to VSMAPI functionality via PigIron + ObjectRexx + BSF4Rexx.
    - Open ObjectRexx is found at http://sourceforge.net/projects/oorexx
    - BSF4Rexx is found at http://wi.wu-wien.ac.at/rgf/rexx/bsf4rexx/current/

Please read the file license.txt which is the license under which the
PigIron open source project is coded, released and distributed. license.txt is
to be found in the same directory as this README.TXT file.

PigIron incorporates some open source code from JSON (http://www.json.org).
Please see the file doc/JSON.license.txt.

Note: PigIron is not associated with the IBM corporation, which
owns most or all of the trademarks mentioned in PigIron documentation.

The file license.txt is found in the same directory with this README.TXT file.

The general layout for PigIron open source  as installed from distribution
archive(s) is:

    pigiron/ -+
              + doc/ -+                          ... documention and doc source
              |       + pigiron.sourceforge.net/ ... webbage for PigIron's sf site
              + piggen                           ... autogenerates Java for VSMAPI parms and funcs
              + javadoc/                         ... the Javadocs for PigIron
              + nbproject/                       ... build scripts and properties NetBeans style
              + script/                          ... scripts primarily in Open ObjectRexx to test PigIron
              + src/                             ... the Java source code

You can start at the root of all documentation by opening doc/index.html in
your browser.

NOTE ON SECURITY (LACK THEREOF):
    * Currently NO secure sockets implementation. Sends your password in
      CLEAR TEXT. Use ONLY on a secure LAN behind a firewall.

PigIron currently supports z/VM r5.3 SMAPI.

Fairmount, Colorado
2008-08-20

