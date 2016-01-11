# autogenerate pigfunc.cls
#

function usage {
echo "$0 pigiron_root [prefix]"
echo " ... generates oorexx dictionary load section of pigfunc.cls"
echo " ... pigiron_root is the directory above src/com/softwoehr ... etc"
echo " ... prefix, if supplied, is an optional prefix to the class names."
}

pigroot=$1
pigprefix=$2
if [ "$pigroot" == "" ]
then
	usage
	exit 1
fi
cat << ENDENDEND
/* 
 * Copyright (c)) 2008, 2016 Jack J. Woehr jwoehr@softwoehr.com
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

/* Utility for creating OORexx classes of PigIron VSMAPI Functions */

::REQUIRES BSF.CLS
::CLASS PigFunc PUBLIC

  ::METHOD PigDirectory ATTRIBUTE CLASS

   ::METHOD assimilate_arguments
   	USE ARG arguments
	self~argument_array=bsf.createArray("String.class", arguments~words)
	DO i = 1 TO self~argument_array_length
	      self~argument_array[i]=arguments~word(i)
	      END
	RETURN self~argument_array
	
   ::METHOD pigfunc_name ATTRIBUTE
   ::METHOD class_instance ATTRIBUTE
   ::METHOD function_instance ATTRIBUTE
   ::METHOD argument_array ATTRIBUTE
   ::METHOD argument_array_length
        RETURN self~argument_array~items
   ::METHOD output_array ATTRIBUTE
   
   ::METHOD INIT
   	USE ARG classname, arguments
	self~pigfunc_name=classname
	self~assimilate_arguments(arguments)
	self~class_instance=self~class~PigDirectory~at(self~pigfunc_name)

   ::METHOD DirectoryAt CLASS
        USE ARG aName
        RETURN self~PigDirectory~at(aName)

   /* Allow strings to be passed in with substitute chars
    * e.g., underscore for spaces, backslash for empty string,
    * etc., then convert to spaces or whatever is desired.
    */
   ::METHOD translate_argument
        USE ARG index, translate_to, to_translate
        self~argument_array[index]=TRANSLATE(self~argument_array[index], translate_to, to_translate)

   /* Allow strings to be passed in with substitute chars
    * using specifically:
    *   - underscore ("_") for space
    *   - backslash  ("\") for empty string (and *only* that one character for a blank arg)
    * which seems to be a pretty safe assigment of substitute
    * chars w/r/t VSMAPI.
    */
   ::METHOD translate_argument_conventionally
        USE ARG index
        self~argument_array[index]=TRANSLATE(self~argument_array[index], ' ', '_')
        self~argument_array[index]=STRIP(self~argument_array[index], , '\')

   /* This is overloaded by the subclass. */
   ::METHOD construct_instance
   	SAY "Not implemented yet"
	self~function_instance="Some invocation of self~class_instance~newStrict() using the data in argument_array"
	exit 1
	
   ::METHOD do_it
         self~output_array=self~function_instance~doIt();
	 
   ::METHOD pretty_print
   	return self~output_array~prettyPrintAll()

   ::METHOD invocation_message
        invoke_msg = "Invoking" self~pigfunc_name"("
          do i = 1 to self~argument_array_length
            invoke_msg = invoke_msg || self~argument_array[i]
            if i < self~argument_array_length then invoke_msg = invoke_msg', '
            end
        invoke_msg = invoke_msg')'
        return invoke_msg

   ::METHOD INIT CLASS
   	super~init
	self~init_dictionary

   ::METHOD init_dictionary CLASS
	self~PigDirectory=.Directory~new
   	class_path='com.softwoehr.pigiron.functions'
ENDENDEND
#
echo '        /* PigIron functions */'
cd ${pigroot}/src/com/softwoehr/pigiron/functions
for i in *.java
do
	classname=`basename $i .java`
	if [ "$classname" != "package-info" ]
	then
		echo '        self~PigDirectory~put(bsf.import(class_path".'${classname}'"), "'${pigprefix}${classname}'")'
	fi
done
echo '        /* PigIron basic SMAPI data types */'
echo "        class_path='com.softwoehr.pigiron.access'"
cd ${pigroot}/src/com/softwoehr/pigiron/access
for i in Counted*.java Parameter*.java String*.java VSM*.java
do
	classname=`basename $i .java`
	if [ "$classname" != "package-info" ]
	then
		echo '        self~PigDirectory~put(bsf.import(class_path".'${classname}'"), "'${pigprefix}${classname}'")'
	fi
done
echo '        /* PigIron SMAPI derived data types */'
echo "        class_path='com.softwoehr.pigiron.access.paramstructs'"
cd ${pigroot}/src/com/softwoehr/pigiron/access/paramstructs
for i in *.java
do
	classname=`basename $i .java`
	if [ "$classname" != "package-info" ]
	then
		echo '        self~PigDirectory~put(bsf.import(class_path".'${classname}'"), "'${pigprefix}${classname}'")'
	fi
done
echo
echo '/* End of file */'
exit 0
end
