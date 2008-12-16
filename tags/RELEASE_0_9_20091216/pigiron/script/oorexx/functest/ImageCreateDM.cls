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


::REQUIRES 'pigfunc.cls'

::CLASS ImageCreateDM PUBLIC

    ::METHOD my.pigfunc ATTRIBUTE

    ::METHOD INIT
    	USE ARG args
	self~my.pigfunc=.PigFunc~new("ImageCreateDM", args)
	
    ::METHOD construct_instance
    	EXPOSE my.pigfunc
        my.image_record_array = .PigFunc~DirectoryAt('ImageRecordArray')~new("image_record_array")
        my.pigfunc~translate_argument_conventionally(6) -- allowing the passing of empty prototype_name argument
        my.pigfunc~translate_argument_conventionally(7) -- allowing the passing of empty initial_password argument
        my.pigfunc~translate_argument_conventionally(8) -- allowing the passing of empty account_number argument
	-- Count the params and form an ImageRecordArray
	-- However, allow an empty ImageRecordArray if we are using the prototype_name argument
	-- Apparently VSMAPI won't allow us tot just ignore the (useless) ImageRecordArray if we use prototype_name
	-- We have to pass a zero count array
	IF my.pigfunc~argument_array~items >= 9 THEN
	do i = 9 to my.pigfunc~argument_array~items
            my.pigfunc~translate_argument_conventionally(i) -- allowing the passing of blanks in arguments and empty args
            my.image_record_array~add(.PigFunc~DirectoryAt('ImageRecordStructure')~new(.PigFunc~DirectoryAt("VSMString")~new(my.pigfunc~argument_array[i]), "image_record"))
            end
        /* debug */ SAY 'my.image_record_array~paramLength() ==' my.image_record_array~paramLength()
        my.pigfunc~function_instance=my.pigfunc~class_instance~newStrict("ST", my.pigfunc~argument_array[1], "I", my.pigfunc~argument_array[2], "ST", my.pigfunc~argument_array[3], "ST", my.pigfunc~argument_array[4], "ST", my.pigfunc~argument_array[5],,
        "ST", my.pigfunc~argument_array[6], "ST", my.pigfunc~argument_array[7], "ST", my.pigfunc~argument_array[8], "I", my.image_record_array~paramLength(), "O", my.image_record_array)

    ::METHOD do_it
        EXPOSE my.pigfunc
        say my.pigfunc~invocation_message
	my.pigfunc~do_it
	say "Returns from call:"
	say "(Total parameter length is" my.pigfunc~output_array~totalParameterLength()")"
	say my.pigfunc~pretty_print()

    ::METHOD doit
        EXPOSE my.pigfunc
        my.pigfunc~do_it
        return my.pigfunc~output_array
/* End */
