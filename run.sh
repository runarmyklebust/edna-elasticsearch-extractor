#!/bin/bash

die () {
    echo >&2 "$@"
    exit 1
}

[ "$#" -eq 1 ] || die "argument 'indexName' required, eg: ./run.sh goRunarIndex"

java -Xms2g -Xmx2g -jar target/edna-dataextractor-1.0.1-SNAPSHOT-jar-with-dependencies.jar $1 10000 50000
