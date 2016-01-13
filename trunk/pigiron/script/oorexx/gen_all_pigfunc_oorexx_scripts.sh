# gen_all_pigfunc_oorexx_scripts.sh
# autogenerate all the oorexx test driver scripts to exercise PigIron functions
#
# Copyright (c) 2016, Jack J. Woehr jwoehr@softwoehr.com
# PO Box 51, Golden, Colorado 80402-0051 USA
# All rights reserved.
#
# Redistribution and use in source and binary forms, with or without
# modification, are permitted provided that the following conditions
# are met:
#
#     * Redistributions of source code must retain the above copyright
#         notice, this list of conditions and the following disclaimer.
#     * Redistributions in binary form must reproduce the above copyright
#         notice, this list of conditions and the following disclaimer
#         in the documentation and/or other materials provided with the
#         distribution.
#     * Neither the name of the PigIron Project nor the names of its
#         contributors may be used to endorse or promote products derived
#         from this software without specific prior written permission.
#
# THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
# AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
# IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
# ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
# LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
# CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
# SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
# INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
# CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
# ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
# THE POSSIBILITY OF SUCH DAMAGE.
#

function usage {
echo "$0 pigiron_root [dest_dir] [prefix]"
echo " ... generates oorexx scripts exercising PigIron functions registered in pigfunc.cls"
echo " ... scripts are generated in dest_dir if present, otherwise, current directory"
echo " ... pigiron_root is the directory above src/com/softwoehr ... etc"
echo " ... prefix, if supplied, is an optional prefix to the class names."
echo " ... The pigiron jar and BSFooRexx classes must be in CLASSPATH."
echo " ... ObjectRexx must be in the executable search path."
}

if [ "$1" == "-h" ] || [ "$1" == "--help" ]
then
	usage
	exit 0
fi

pigroot=$1
dest_dir=$2
pigprefix=$3

if [ "$pigroot" == "" ]
then
	usage
	exit 1
fi

if [ "$dest_dir" != "" ]
then
	dest_dir="${dest_dir}/"
fi

curdir=`pwd`
cd ${pigroot}/src/com/softwoehr/pigiron/functions

for i in *.java
do
	classname=`basename $i .java`
	if [ "$classname" != "package-info" ] && [ "$classname" != "VSMCall" ]
	then
		rexx ${curdir}/genPigFuncOORexxRex.rex $classname $pigprefix > ${dest_dir}${pigprefix}${classname}".rex"
	fi
done

exit 0
# end of file
