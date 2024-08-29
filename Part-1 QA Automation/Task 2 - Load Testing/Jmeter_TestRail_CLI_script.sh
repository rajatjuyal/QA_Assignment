#!/bin/bash

clear
# bold=$(tput bold)

echo "jmeter script-------starts"

echo "verify trcli is installed"

trcli
echo

# Set the path to your JMeter installation
JMETER_HOME="D:\NCLE_work\apache-jmeter-5.6.3\bin\jmeter"

# Set the path to your JMeter script
JMX_FILE="jmeter_getAPI.jmx"

# Set the path to the desired output directory
OUTPUT_DIR="jmeter_results"

# Create the output directory if it doesn't exist
mkdir -p "$OUTPUT_DIR"

# Run the JMeter test
"$JMETER_HOME" -n -t "$JMX_FILE" -l "$OUTPUT_DIR/results.jtl"

# Convert the results to JUnit XML format
"$JMETER_HOME" -g "$OUTPUT_DIR/results.jtl" -o "$OUTPUT_DIR/junit_results"

trcli -y -c "trcli-config.yml" parse_junit -f "./junit-jmeter.xml" --title "Jmeter automation run"
echo

echo "Jmeter automation execution is completed with test results upload to TestRail and report generation"