The example project for StringBoot service

<div align="center">
    <img src="./assets/images/spring_boot_icon.png"/>
</div>

## Getting Started

## Project structure
```
.
├── hello-world
│   ├── Dockerfile
│   ...
├── docker-compose.yaml
|
└── README.md
```

## Prerequisites
- Make sure that you have Docker and Docker Compose installed
  - Windows or macOS:
    [Install Docker Desktop](https://www.docker.com/get-started)
  - Linux: [Install Docker](https://www.docker.com/get-started) and then
    [Docker Compose](https://github.com/docker/compose)

## Start project
### Start project in local

- Install infrastructure

- Build project
```shell script
$ ./mvnw clean package
$ cd hello-word
$ ./mvnw spring-boot:run
...
```

### Start project in docker 

- Start project
```shell script
docker-compose up -d
```

- Stop project
```shell script
docker-compose down
```

## Run testing

```shell script
curl http://localhost:8081/greet?name=World
```


## Contribute

## Reference