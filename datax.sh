#!/bin/sh
. /home/postgres/.bash_profile
#echo $1
/home/taobao/datax/bin/datax.py "-p$1" $2
