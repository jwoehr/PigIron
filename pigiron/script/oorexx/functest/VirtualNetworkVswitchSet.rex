/*
 * Copyright (c) 2008-2016, Jack J. Woehr jwoehr@softwoehr.com
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

 /* Autogenerated 2016-10-23T16:35:47.135024 by jax */


/*
 * VirtualNetworkVswitchSet.rex
 * Tests PigIron VSMAPI Function VirtualNetworkVswitchSet
 * which is represented by ObjectRexx VirtualNetworkVswitchSet.cls
 * Requires ObjectRexx http://sourceforge.net/projects/oorexx
 *          BSF4ooREXX http://sourceforge.net/projects/bsf4oorexx/
 */

/* Invoke SMAPI VirtualNetworkVswitchSet and prettyprint result of execution */

PARSE ARG args
PARSE SOURCE my.platform my.invocation my.command
if args~words < 29 then signal usage
it=.VirtualNetworkVswitchSet~new(args)
it~construct_instance()
it~do_it
exit 0

usage:
say "Usage:" my.command "arg1(ST) arg2(I) arg3(ST) arg4(ST) arg5(ST) arg6(ST) arg7(ST) arg8(ST) arg9(ST) arg10(ST) arg11(ST) arg12(ST) arg13(I) arg14(I) arg15(I) arg16(I) arg17(I) arg18(ST) arg19(ST) arg20(ST) arg21(ST) arg22(ST) arg23(ST) arg24(ST) arg25(ST) arg26(ST) arg27(ST) arg28(I) arg29(ST) "
exit 1

::REQUIRES 'VirtualNetworkVswitchSet.cls'
/* end of file */
