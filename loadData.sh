#!/bin/bash

die () {
    echo >&2 "$@"
    exit 1
}

[ "$#" -eq 1 ] || die "argument 'indexName' required, eg: ./loadData.sh goRunarIndex"

INDEX_NAME=$1
INDEX_URL=http://leela:9200/$INDEX_NAME

echo -e "\nDeleting old index"
CMD="curl -XDELETE $INDEX_URL"
echo $CMD
$CMD

echo -e "\nCreate edna index"
CMD="curl -XPUT $INDEX_URL -d @createIndex.json"
$CMD

FILES=./timeLogDataImport_*

for f in $FILES
do
  echo "Processing file: $f"
  curl -s -XPUT 'http://leela:9200/_bulk' --data-binary @$f
done



