#!/bin/sh

mvn install:install-file -Dfile=./libs/flurry-agent-1.0.jar -DgroupId=com.flurry \
    -DartifactId=flurry-agent -Dversion=1.0 -Dpackaging=jar
