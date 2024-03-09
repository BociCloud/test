# REST test project[Description Here](https://netbeans.apache.org/images/nblogo48x48.png)

***

## Environment
Start postgres

bin/start-pg.sh
```
docker run --rm --name pg_dev -p 5432:5432 -e POSTGRES_HOST_AUTH_METHOD=trust postgres:16.2
```
Use POSTGRES_HOST_AUTH_METHOD=trust only in dev environment

export HOSTNAME=yourhostname
It should be accessible from inside containers

## Build

```mvn clean package```

build container

```mvn clean compile jib:dockerBuild```

## Run

bin/run-container.sh
```
docker run --rm --name dpd-test -e HOSTNAME=$HOSTNAME -p 8080:8080 dpd-test
```

