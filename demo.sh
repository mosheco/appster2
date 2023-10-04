#!/bin/bash

docker-compose up &

sleep 10  #  Till Mysql starts up, probably can be done smarter with wait-for-it

# Load test data
mysql  --host=127.0.0.1  -u demouser -pdemopw the_bank < data.dmp

# Do the GET with injection
echo
echo "Results of GET with SQL injection:"
echo "=================================="
curl -s http://localhost:8080/get_balance?account_id=9%20OR%201 | jq
echo

echo "Results of GET with attempt at SQL injection, but using parameterized query:"
echo "============================================================================"
curl -s http://localhost:8080/get_balance_parameterized?account_id=9%20OR%201 | jq
echo

echo "Results of Hutool call, exception name is returned:"
echo "============================================================================"
curl -s http://localhost:8080/jsonobject
echo
echo
echo


docker-compose down

