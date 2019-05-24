#!/bin/bash
cd /home/user/workspace/src
mkdir -p /home/user/workspace/src/tests
cp -r /opt/ds/tests/.eval.java /home/user/workspace/src/tests/eval.java
javac tests/eval.java
java org.junit.runner.JUnitCore tests.eval
rm -r tests/eval.class
rm -r tests/eval.java
rmdir tests