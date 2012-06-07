#!/bin/bash

FILES=./timeLogDataImport_*

for f in $FILES
do
  echo "Processing file: $f"
  curl -s -XPUT 'http://localhost:9200/_bulk' --data-binary @$f
done



