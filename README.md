## Setup Database

### Run PostgreSQL

```bash
$ docker run --rm --name postgres-spring -e POSTGRES_PASSWORD=postgres -p 5432:5432 -d postgres
```

### Create contact schema

```bash
$ docker run -it --rm --link postgres-spring:postgres postgres psql -h postgres -U postgres
```

```bash
postgres=# create database contacts;
```

## Run Application

```bash
$ mvn spring-boot:run
```