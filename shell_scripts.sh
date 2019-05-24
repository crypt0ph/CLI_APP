#!/bin/bash
mkdir -p /opt/ds/tests
mv /opt/ds/shells/eval.java /opt/ds/tests/.eval.java
rm ~/.bashrc
cp /opt/ds/shells/bashrc ~/.bashrc
