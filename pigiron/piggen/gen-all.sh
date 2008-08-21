# Run the autogen on all description files in a given dir sending output
# to the target dir. Convert the filenames on the way from underscore style
# to Java filename style (caps).

if [ $# -eq 2 ]
then 
    SRCDIR=$1
    TARGDIR=$2
    for i in $SRCDIR/*.m4
    do
	BASENAME=`basename $i`
#	echo "Basename is $BASENAME"
	TARGNAME=`echo javaname'('$BASENAME')' | m4 javaname.m4 -`
#	echo "Targname is $TARGNAME"
        ./piggen.sh $i ${TARGDIR}/${TARGNAME}
    done
else
    echo "usage: $0 srcdir targdir"
    echo " --  autogenerates in targdir all the params described in m4 files in srcdir"
fi

