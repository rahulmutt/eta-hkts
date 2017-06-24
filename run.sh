#!/usr/bin/env sh

rm -rf target
mkdir -p target
javac -sourcepath src -d target -Xlint:unchecked src/HKT.java
java -cp target HKT
