#!/bin/bash
#
# Use this shell script to compile (if necessary) your code and then execute it. Below is an example of what might be found in this file if your program was written in Python
#
javac ./src/*.java
java -cp ./src Driver ./input/itcont.txt ./output/medianvals_by_zip.txt ./output/medianvals_by_date.txt