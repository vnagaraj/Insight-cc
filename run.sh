#!/usr/bin/env bash

# example of the run script for running the word count

# I'll launch my programs, with the input directory tweet_input and output the files in the directory tweet_output
#python ./src/average_degree.py ./tweet_input/tweets.txt ./tweet_output/output.txt


cd src
export CLASSPATH=$PWD
javac launch/Executor.java
java -cp $CLASSPATH launch.Executor ../tweet_input/tweets.txt ../tweet_output/output.txt




