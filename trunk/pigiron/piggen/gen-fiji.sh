#!/bin/sh
#
# Copyright (c) 2015, Jack J. Woehr jwoehr@softwoehr.com
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
# gen-fiji.sh
# Run an autogen of one massive FIJI source file. This source file supports
# makes it easier to use PigIron from within FIJI. 
# gen-fiji.sh consumes the same description files based on pigstruct.m4
# pigarray.m4 and pigfunc.m4 .. gen-fiji.sh modfies in the stream the
# include()'s embedded in the autogenator m4 to cause them to include instead
# the autogenerator source which produces FIJI source

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

# Find where we are running from
SCRIPTDIR=`pwd`

# Check args and process
if [ $# -ge 3 ]
then 
    PARMFILE_SRCDIR=$1
    FUNCFILE_SRCDIR=$2
    TARGDIR=$3
    OUTPUT_FILENAME=${4:-pigiron_support.fiji}
    OUTFILE=${TARGDIR}/${OUTPUT_FILENAME}
    if [ ! -d $PARMFILE_SRCDIR ]
    then
    	echo "$PARMFILE_SRCDIR is not a directory"
	exit 2
    fi
    if [ ! -d $FUNCFILE_SRCDIR ]
    then
    	echo "$FUNCFILE_SRCDIR is not a directory"
	exit 2
    fi
    if [ ! -d $TARGDIR ]
    then
    	echo "$TARGDIR is not a directory"
	exit 2
    fi
    # Test TARGDIR for absoluteness
    echo $TARGDIR | grep '^/' >/dev/null
    if [ $? -ne 0 ]
    then
    	echo "$TARGDIR" 'is not a fully qualified path (must start with ''/'').'
	exit 2
    fi
    cat pigiron_copyright_fijicomment.txt > ${OUTFILE} # clear the output file

    cd $PARMFILE_SRCDIR
    for i in *.m4
    do
        echo >> ${OUTFILE}
	echo '\\' $i '\\' >> ${OUTFILE}
    	cat $i | sed 's/include(`/include(`fiji_/g' | \
	$GM4 -I $SCRIPTDIR >> ${OUTFILE}
    done
    if [ $? -ne 0 ]
    then
    	echo "Error generating from parmfiles:" $?
	exit 3
    fi
    cd $SCRIPTDIR
    cd $FUNCFILE_SRCDIR
    for i in *.m4
    do
        echo >> ${OUTFILE}
        echo '\\' $i '\\' >> ${OUTFILE}
    	cat $i | sed 's/include(`/include(`fiji_/g' | \
	$GM4 -I $SCRIPTDIR >> ${OUTFILE}
    done
    if [ $? -ne 0 ]
    then
    	echo "Error generating from funcfiles:" $?
	exit 4
    fi
else
    echo "usage: $0 parmfile_srcdir funcfile_srcdir targdir [output_filename]"
    echo " --  autogenerates in targdir/output_filename FIJI support for"
    echo "     all the PigIron params  described in m4 files in"
    echo "     parmfile_srcdir and all the functions described in"
    echo "     funcfile_srcdir. The default output file name"
    echo "     is pigiron_support.fiji and targdir should be fully qualified."
    exit 1
fi
# End
