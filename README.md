## Setup Database

### Run PostgreSQL

```bash
$ docker network create phonebook-net
$ docker volume create pgdata
$ docker run --rm --name phonebook-db --net=phonebook-net -v pgdata:/var/lib/postgresql/data -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=contacts -p 5432:5432 -d postgres:9.6-alpine
```

## Run Application

### Local

```bash
$ mvn spring-boot:run --spring.datasource.url=jdbc:postgresql://localhost/contacts
```

### Docker container

```bash
$ mvn clean package
$ mv target/phonebook-1.0-SNAPSHOT.jar docker/app/phonebook-be.jar
$ cd docker
$ docker build -t phonebook-be .
$ docker run --rm --name phonebook-backend --net=phonebook-net -p 9000:9000 -it phonebook-be:latest
```

## API

### Post a Contact

```bash
$ curl -H "Content-Type: application/json" -X POST -d '{"firstName":"Matteo","lastName":"Pierro", "phoneNumber":"+39 123 123456"}' localhost:9000/contacts
```

### Get all contacts

```bash
$ curl localhost:9000/contacts
```

### Get a contact by id

```bash
curl localhost:9000/contacts/<id>
```

## Run Test

### Run Unit Test

```bash
$ mvn clean test
```

### Run Integration Test

#### Create test database

```bash
$ docker run --rm --name phonebook-db-test -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=contacts_test -p 5432:5432 -d postgres:9.6-alpine
```

#### Run
```bash
$ mvn integration-test -P integration-test
```
