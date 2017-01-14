#!/bin/bash
echo "begin backup sql"

dateStr=`date "+%Y-%m-%d"`
echo $dateStr

`cd "/root/backsql/"`
backFile="teardowall$dateStr.sql"
tarFile="teardowall${dateStr}.tar.gz"
echo "$backFile"
`mysqldump -ujerry -pjerry! --databases teardowall > $backFile`
`tar zcvf ${tarFile} ${backFile}`
`rm -rf $backFile`
`mv ${tarFile} backsql/`

echo "end backup sql"
