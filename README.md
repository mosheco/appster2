# appster

## Regular deployment

### Notes:

- The code is in file `DemoApplication.java`.  (`HelloController.java` also exist with the exec-command example, can be removed but it is useful to have another service around for testing things out in Spring)
- The code assumes a local Mysql server running with a DB named `the_bank` and with hardcoded user name and pw (see lines 35-37)
- File `data.dmp` is a mysqldmp dump of the DB - one `balance` table with some data


### Build and run:

`mvn install && java  -Dmysqlhost=localhost -jar  target/demo-0.0.1-SNAPSHOT.jar`


### Examples:

    curl -s http://localhost:8080/get_balance?account_id=7  | jq
    [
      {
	"account_id": 7,
	"dollar_balance": 5455
      }
    ]


    curl -s http://localhost:8080/get_balance?account_id=7%20OR%201  | jq
    [
      {
	"account_id": 1,
	"dollar_balance": 22
      },
      {
	"account_id": 2,
	"dollar_balance": 555
      },
      {
	"account_id": 3,
	"dollar_balance": 222
      },
      {
	"account_id": 4,
	"dollar_balance": 78
      },
      {
	"account_id": 5,
	"dollar_balance": 32
      },
      {
	"account_id": 6,
	"dollar_balance": 242
      },
      {
	"account_id": 7,
	"dollar_balance": 5455
      },
      {
	"account_id": 8,
	"dollar_balance": 9222
      },
      {
	"account_id": 9,
	"dollar_balance": 708
      },
      {
	"account_id": 10,
	"dollar_balance": 322
      }
    ]

## Docker deployment

### Notes:
 - have `mysql-client` installed
 - have `jq` installed (this is used just for JSON pretty output)

### Build and run:

`./demo.sh`

The command 
- creates and runs the containers
- loads the Mysql DB with test data
- runs a GET with SQL injection
- runs an attempted GET with injection but parameterized
- shuts down containers and removes them.
