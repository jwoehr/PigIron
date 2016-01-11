/* Generate an ObjectRexx binding to a PigIron function using reflection.
 *
 * Copyright (c) 2016, Jack J. Woehr jwoehr@softwoehr.com
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

parse arg classname
say .Fragments~header
say "::CLASS" classname "PUBLIC"
say ""
say "   ::METHOD my.pigfunc ATTRIBUTE"
say ""
say "   ::METHOD INIT"
say "      USE ARG args"
say '      self~my.pigfunc=.PigFunc~new("'classname'", args)'
say ""
classname="com.softwoehr.pigiron.functions."classname
theClass=.bsf4rexx~class.class ~forName(classname)
ctors=theClass~getConstructors
do i over ctors
	typeArray =  i~getGenericParameterTypes
	typeArraySize =  typeArray~size
/*	say "ctor/" || typeArray~size i*/
	if typeArraySize > 0 then do
		say .CtorGen~genConstructInstance(typeArray)
		end
	/* say ctors */
end
say .Fragments~functions
exit 0

::CLASS PigFuncClassGen PUBLIC

	::METHOD funcDec CLASS
	use arg classname
	return "::CLASS" classname "PUBLIC"
	
		
::CLASS CtorGen PRIVATE
	::METHOD strictArgList ATTRIBUTE
	
	::METHOD typeToStrict CLASS
	use arg aType
	if aType == "java.lang.String" then return "ST" 
		else if aType == "int" then return "I"
			else return "O"

	::METHOD emit_funcarg CLASS
	use arg aType, anIndex
	return '"' || self~typeToStrict(aType) ||'",' "my.pigfunc~argarray[" || anIndex || "]"
	
	::METHOD typeArrayToFuncArgs CLASS
	use arg aTypeArray
	anIndex = 1
	output = "("
	do j over aTypeArray
		output = output || self~emit_funcarg(j~getname, anIndex)
		if anIndex < aTypeArray~size then do
			if anIndex // 2 == 0 then output = output || ",," || x2c(0a) || "      "
			else output = output || ", "
		end
		anIndex += 1
	end
	output = output || ")"
	return output
	
	::METHOD genConstructInstance CLASS
	use arg aTypeArray
	output = "   ::METHOD construct_instance" || x2c(0a)
        output = output || "      EXPOSE my.pigfunc" || x2c(0a)
        output = output || "      my.pigfunc~function_instance=my.pigfunc~class_instance~newStrict"
        output = output || self~typeArrayToFuncArgs(aTypeArray)
        return output
	
::CLASS Fragments PRIVATE
        ::METHOD header ATTRIBUTE CLASS
        ::METHOD functions ATTRIBUTE CLASS
	::METHOD init CLASS
	theDate= .DateTime~today
	theDate~init
	self~header=,
"/*" || x2c(0a) ||,
" * Copyright (c) 2008-2016, Jack J. Woehr jwoehr@softwoehr.com" || x2c(0a) ||,
" * PO Box 51, Golden, Colorado 80402-0051 USA" || x2c(0a) ||,
" * All rights reserved." || x2c(0a) ||,
" *" || x2c(0a) ||,
" * Redistribution and use in source and binary forms, with or without" || x2c(0a) ||,
" * modification, are permitted provided that the following conditions" || x2c(0a) ||,
" * are met:" || x2c(0a) ||,
" *" || x2c(0a) ||,
" *     * Redistributions of source code must retain the above copyright" || x2c(0a) ||,
" *         notice, this list of conditions and the following disclaimer." || x2c(0a) ||,
" *     * Redistributions in binary form must reproduce the above copyright" || x2c(0a) ||,
" *         notice, this list of conditions and the following disclaimer" || x2c(0a) ||,
" *         in the documentation and/or other materials provided with the" || x2c(0a) ||,
" *         distribution." || x2c(0a) ||,
" *     * Neither the name of the PigIron Project nor the names of its" || x2c(0a) ||,
" *         contributors may be used to endorse or promote products derived" || x2c(0a) ||,
" *         from this software without specific prior written permission." || x2c(0a) ||,
" *" || x2c(0a) ||,
" * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"" || x2c(0a) ||,
" * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE" || x2c(0a) ||,
" * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE" || x2c(0a) ||,
" * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE" || x2c(0a) ||,
" * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR" || x2c(0a) ||,
" * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF" || x2c(0a) ||,
" * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS" || x2c(0a) ||,
" * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN" || x2c(0a) ||,
" * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)" || x2c(0a) ||,
" * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF" || x2c(0a) ||,
" * THE POSSIBILITY OF SUCH DAMAGE." || x2c(0a) ||,
" */" || x2c(0a) ||,
x2c(0a) ||,
" /* Autogenerated" theDate "by" UserId() "*/" || x2c(0a) ||,
x2c(0a) ||,
"::REQUIRES 'pigfunc.cls'" || x2c(0a)
	self~functions=,
x2c(0a) ||,
'   ::METHOD do_it' || x2c(0a) ||,
'      EXPOSE my.pigfunc' || x2c(0a) ||,
'      say my.pigfunc~invocation_message' || x2c(0a) ||,
'      my.pigfunc~do_it' || x2c(0a) ||,
'      say "Returns from call:"' || x2c(0a) ||,
'      say "(Total parameter length is" my.pigfunc~output_array~totalParameterLength()")"' || x2c(0a) ||,
'      say my.pigfunc~pretty_print()' || x2c(0a) ||,
x2c(0a) ||,
'   ::METHOD doit' || x2c(0a) ||,
'      EXPOSE my.pigfunc' || x2c(0a) ||,
'      my.pigfunc~do_it' || x2c(0a) ||,
'      return my.pigfunc~output_array' || x2c(0a) ||,
'/* End */'

::REQUIRES BSF.CLS
