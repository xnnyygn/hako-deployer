#!/bin/sh

mvn install
cd test-projects/simple/
mvn porter:jetty
