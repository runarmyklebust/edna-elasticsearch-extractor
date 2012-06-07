#!/bin/bash

echo "Deleting old index"
curl -XDELETE 'http://localhost:9200/test/'

FILES=./timeLogDataImport_*

for f in $FILES
do
  echo "Processing file: $f"
  curl -s -XPUT 'http://localhost:9200/_bulk' --data-binary @$f
done



