# Expenses Tracker
Project to parse the Transactions log from a bank and provide an REST API.

## Project Stack
* Kotlin
* SpringBoot 
* Docker
* Postgres

### How to Run
#### 1. Locally:
Please create a folder called uploads at the root
Create a postgres.env file inside docker folder:

```dotenv
POSTGRESQL_USERNAME=example
POSTGRESQL_PASSWORD=example_pwd
POSTGRESQL_DATABASE=example_db_name
```
#### 2. Docker Image:
Provide the environment variables below:
```dotenv
PGSQL_HOST=
PGSQL_PORT=
PGSQL_USER=
PGSQL_PWD=
APP_UPLOAD_PATH=
```