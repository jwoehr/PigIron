#!/bin/sh
#
# Copyright (c) 2008, 2015, Jack J. Woehr jwoehr@softwoehr.com
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
if [ $# -eq 2 ]
then 
    FUNCFILE_SRCDIR=$1
    TARGDIR=$2
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
    # Erase every ublu file so all_pigiron.ublu include file doesn't include itself!
    rm -rf ${TARGDIR}/*.ublu
    # Go get the m4 specs
    cd $FUNCFILE_SRCDIR
    for i in *.m4
    do
      echo $i | grep "piggen_prototype_" >/dev/null 2>&1 # test if it's an example file
      if [ $? -eq 1 ] # don't process the example files
      then
	  OUTPUT_FILENAME=`basename ${i} .m4`.ublu
	  OUTFILE=${TARGDIR}/${OUTPUT_FILENAME}
          echo "$0 Processing ${i} to ${OUTFILE}"
	  cat $i | sed 's/include(`/include(`ublu_/g' | \
	      $GM4 -D OUTPUT_FILENAME=${OUTPUT_FILENAME} -I $SCRIPTDIR > ${OUTFILE}
      fi
    done
    if [ $? -ne 0 ]
    then
    	echo "Error generating from funcfiles:" $?
	exit 4
    fi
    # Generate all_pigiron.ublu include file
    cd ${TARGDIR}
    UBLU_FILES=`ls *.ublu`
    echo '# include all pigiron support' >all_pigiron.ublu
    echo "include pigiron_host.ublu" >all_pigiron.ublu
    for i in $UBLU_FILES
    do
      echo "include $i" >>all_pigiron.ublu
    done
    echo "# end" >>all_pigiron.ublu
else
    echo "usage: $0 funcfile_srcdir targdir"
    echo " --  autogenerates in targdir/ublu_pigfunc Ublu support for"
    echo " --  all the PigIron functions described in m4 files in"
    echo " --  funcfile_srcdir. The targdir should be fully qualified."
    exit 1
fi
# End
