#!/bin/bash
# fail if any commands fails
set -e
./gradlew test --stacktrace
