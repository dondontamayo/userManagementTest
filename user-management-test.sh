#!/bin/bash

MASTER_PASSWORD=$1
ENVIRONMENT=$2

echo "Running user management test"

java -Dapplication-name=user-management-test -jar /userManagementTest-0.0.1-SNAPSHOT.jar