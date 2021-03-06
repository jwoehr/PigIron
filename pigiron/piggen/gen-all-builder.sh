#!/bin/sh
#
# Copyright (c) 2008, 2016, Jack J. Woehr jwoehr@softwoehr.com
# PO Box 51, Golden, Colorado 80402-0051 USA
# All rights reserved.
# 
# This is the PigIron project. PigIron is a library of Java class wrappers for the
# IBM z/VM Virtual Machine Operating System Systems Management Application
# Programming Interface (sometimes called VSMAPI). http://pigiron.sourceforge.net
#
# Please read the file license.txt which is the license under which the
# PigIron open source project is coded, released and distributed.
#
# Run the autogen on all description files in a given dir sending output
# to the target dir. Convert the filenames on the way from underscore style
# to Java filename style (caps).
#
# gen-all-builder.sh
# Run the autogen for PigLet Builder assuming description file include()'s the
# autogenator m4 e.g., pigbuilderstruct.m4 pigbuilderarray.m4 or pigbuilderfunc.m4

# Find Gnu m4 
GM4="m4"
$GM4 --version 2>&1 | grep "Free Software Foundation" >/dev/null
FOUND_gm4=$?  # grep returns 0 for success
if [ $FOUND_gm4 -ne 0 ]
    then
    GM4="gm4"
    $GM4 --version 2>&1 | grep "Free Software Foundation" >/dev/null
    FOUND_gm4=$?
    if [ $FOUND_gm4 -ne 0 ]
	then
	echo "Didn't find Gnu m4" >&2
	exit 127
    fi
fi

if [ $# -eq 2 ]
then 
    SRCDIR=$1
    TARGDIR=$2
    for i in $SRCDIR/*.m4
    do
      echo $i | grep "piggen_prototype_" >/dev/null 2>&1 # test if it's an example file
      if [ $? -eq 1 ] # don't process the example files
      then
	BASENAME=`basename $i .m4`
	# echo "Basename is $BASENAME"
	TARGNAME=`echo javaize'('$BASENAME')' | $GM4 javaname.m4 -`.java
	# echo "Targname is $TARGNAME"
        ./pigbuildergen.sh $i ${TARGDIR}/${TARGNAME}
      fi
    done
else
    echo "usage: $0 srcdir targdir"
    echo " --  autogenerates in targdir all the params described in m4 files in srcdir"
fi

