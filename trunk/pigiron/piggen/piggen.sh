#!/bin/sh
# Run the autogen assuming description file includes the autogenator m4
# e.g., pigparm.m4 or pigfunc.m4
if [ $# -eq 1 ]
then m4 < $1
else
    if [ $# -eq 2 ]
    then echo "Generating $2 from $1"; m4 < $1 > $2
    else echo "usage: $0 description_file output_file_name"
        exit 1
        fi
fi

