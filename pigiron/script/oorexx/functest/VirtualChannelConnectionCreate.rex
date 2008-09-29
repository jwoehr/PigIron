#!/opt/ooRexx/bin/rexx
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

/*
 *
 * Requires ObjectRexx http://sourceforge.net/projects/oorexx
 *          BSF4REXX   http://wi.wu-wien.ac.at/rgf/rexx/bsf4rexx/current/
 */

/* Invoke VirtualChannelConnectionCreate */

PARSE ARG args 
PARSE SOURCE my.platform my.invocation my.command
if args~words < 6 then signal usage
it=.Test_VirtualChannelConnectionCreate~new(args)
it~construct_instance()
it~do_it
exit

usage:
say "Usage:" my.command "arg0 arg1 .. .. arg5"
exit 1

::REQUIRES 'pigfunctest.cls'

::CLASS Test_VirtualChannelConnectionCreate

    ::METHOD my.test ATTRIBUTE

    ::METHOD INIT
    	USE ARG args
	self~my.test=.PigFuncTest~new("VirtualChannelConnectionCreate", args)
	
    ::METHOD construct_instance
    	EXPOSE my.test
        my.test~function_instance=my.test~class_instance~newStrict("ST", my.test~argument_array[1], "I", my.test~argument_array[2], "ST", my.test~argument_array[3], "ST", my.test~argument_array[4], "ST", my.test~argument_array[5], "ST", my.test~argument_array[6])

    ::METHOD do_it
        EXPOSE my.test
	say my.test~invocation_message
	my.test~do_it
	say "Returns from call:"
	say "(Total parameter length is" my.test~output_array~totalParameterLength()")"
	say my.test~pretty_print()

/* End */
