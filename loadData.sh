#!/bin/bash

die () {
    echo >&2 "$@"
    exit 1
}

[ "$#" -eq 1 ] || die "argument 'indexName' required, eg: ./loadData.sh goRunarIndex"

echo "Deleting old index"
curl -XDELETE 'http://leela:9200/$1/'

FILES=./timeLogDataImport_*

for f in $FILES
do
  echo "Processing file: $f"
  curl -s -XPUT 'http://leela:9200/_bulk' --data-binary @$f
done



