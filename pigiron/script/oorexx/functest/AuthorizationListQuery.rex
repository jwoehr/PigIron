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

 /* Autogenerated 2016-10-23T16:35:02.314066 by jax */


/*
 * AuthorizationListQuery.rex
 * Tests PigIron VSMAPI Function AuthorizationListQuery
 * which is represented by ObjectRexx AuthorizationListQuery.cls
 * Requires ObjectRexx http://sourceforge.net/projects/oorexx
 *          BSF4ooREXX http://sourceforge.net/projects/bsf4oorexx/
 */

/* Invoke SMAPI AuthorizationListQuery and prettyprint result of execution */

PARSE ARG args
PARSE SOURCE my.platform my.invocation my.command
if args~words < 7 then signal usage
it=.AuthorizationListQuery~new(args)
it~construct_instance()
it~do_it
exit 0

usage:
say "Usage:" my.command "arg1(ST) arg2(I) arg3(ST) arg4(ST) arg5(ST) arg6(ST) arg7(ST) "
exit 1

::REQUIRES 'AuthorizationListQuery.cls'
/* end of file */
