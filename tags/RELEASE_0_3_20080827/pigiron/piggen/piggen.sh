#!/bin/sh
#
# Copyright (c) 2008, Jack J. Woehr jwoehr@softwoehr.com
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
# piggen.sh
# Run the autogen assuming description file include()'s the autogenator m4
# e.g., pigstruct.m4 pigarray.m4 or pigfunc.m4

# Check args
if [ $# -lt 1 -o $# -gt 2 ]
then
    echo "usage: $0 description_file output_file_name"
    exit 1
fi

DESCFILE=$1
OUTFILE=$2
TWOARGS=`expr $# '>' 1`

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

# Run PigGen
if [ $TWOARGS -eq 0 ]
    then
    $GM4 $DESCFILE
    else
    echo "Generating $OUTFILE from $DESCFILE"
    $GM4 $DESCFILE > $OUTFILE
fi
