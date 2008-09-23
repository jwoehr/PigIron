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

/* Utility for creating PigIron function tests */

::REQUIRES BSF.CLS

::CLASS PigFuncTest PUBLIC

   ::METHOD classname ATTRIBUTE
   ::METHOD class_instance ATTRIBUTE
   ::METHOD class_import_name ATTRIBUTE
   ::METHOD class_stem CLASS ATTRIBUTE
   ::METHOD object_instance ATTRIBUTE
   ::METHOD argument_array ATTRIBUTE
   
   ::METHOD INIT CLASS
   	self~class_stem='com.softwoehr.pigiron.functions'
	
   ::METHOD INIT
	USE ARG aClassName, arguments
	self~classname=aClassName
	self~class_import_name="pigfunc_"self~classname
	self~class_instance=.bsf~bsf.import(self~class~class_stem'.'self~classname, self~class_import_name)
	self~argument_array=bsf.createArray("String.class", arguments~words)
	DO i = 1 TO arguments~words
	      self~argument_array[i]= arguments~word(i)
	      END
/* End of file */
