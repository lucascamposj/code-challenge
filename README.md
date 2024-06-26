<h1 align="center">
  Full Stack application Store using FakeStoreAPI
</h1>

## Screenshots

<div align="center">
<img width="200" src="https://github.com/lucascamposj/code-challenge/assets/22333534/4871f28e-1daf-4bc5-b380-aca6c67a901a">
<img width="200" src="https://github.com/lucascamposj/code-challenge/assets/22333534/3832c404-4106-44d2-ae7b-b109903f2b71">
<img width="200" src="https://github.com/lucascamposj/code-challenge/assets/22333534/dfe85fe2-df0e-44b5-917f-2ba80f48f323">
<img width="200" src="https://github.com/lucascamposj/code-challenge/assets/22333534/6df197b4-4bf7-48c6-814b-8ad8ebca2ffd">
</div>

## Video Demo

Click to watch the video!

[![Watch the video demo](https://img.youtube.com/vi/cfqMql8btEk/maxresdefault.jpg)](https://youtu.be/cfqMql8btEk)

## Tools

Backend:

- [Java](https://www.java.com/pt-BR/)
- [Quarkus](https://pt.quarkus.io/)
- [ProjectLombok](https://projectlombok.org/)
- [Hibernate](https://hibernate.org/)
- [Hibernate with Panache](https://pt.quarkus.io/guides/hibernate-orm-panache)
- [Postgres Image](https://hub.docker.com/_/postgres)
- [Resteasy-jackson](https://quarkus.io/extensions/io.quarkus/quarkus-resteasy-jackson/)
- [Swagger](https://swagger.io/specification/)
- [Mockito](https://site.mockito.org/)
- [RestAssured](https://rest-assured.io/)
- [JUnit5](https://junit.org/junit5/)
- [SmallRye Fault-Tolerance](https://github.com/smallrye/smallrye-fault-tolerance/)
- [H2 Database for testing](https://www.h2database.com/html/main.html)

Frontend:

- [AngularJS](https://angularjs.org/)
- [TailwindCSS](https://tailwindcss.com/)
- [Jest](https://jestjs.io/)
- [FakeStoreAPI](https://fakestoreapi.com/)
- [NgRx](https://ngrx.io/)
- [NgRx Effects](https://ngrx.io/guide/component-store/effect)
- [Primeng](https://primeng.org/)

# Backend

All the backend code can be found on the folder `/backend`

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Requirements

JDK 17+ installed with JAVA_HOME configured appropriately

Apache Maven 3.9.6

Optionally the Quarkus CLI if you want to use it: https://pt.quarkus.io/get-started/

You will need to have a Postgres database available. This can be done with [Docker](https://www.docker.com/). With docker installed you can run the following command

```shell script
docker run --name my_db -e POSTGRES_USER=username -e POSTGRES_PASSWORD=password -e POSTGRES_DB=my_db -p 5432:5432 postgres:latest
```

To change the configuration of the database connection (i.e. user, password, port, etc), you will need to change the variables on `/backend/src/main/resources/application.properties`.

With the docker container running, you are ready to run the application on dev mode.

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_** Quarkus ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Dev UI

Quarkus provides a Quarkus Dev Ui were you will be able to manage the application extensions and configuration.

### Swagger UI

Swagger UI for the API can be found at http://localhost:8080/q/swagger-ui/

### Tests interface

Quarkus tests interface can be found at http://localhost:8080/q/dev-ui/continuous-testing

You can also run the test with the command:

```shell script
./mvnw compile quarkus:test
```

## Packaging and running the application

The application can be packaged using:

```shell script
./mvnw package
```

It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

## Creating a native executable

You can create a native executable using:

```shell script
./mvnw package -Dnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/backend-1.0.0-SNAPSHOT-runner`

## Fault Tolerance

This project includes an example of fault tolerance using SmallRye Fault Tolerance.

The example configuration can be found on `backend/src/main/java/dev/lucas/controller/CustomerController.java`

The configuration includes fault tolerance strategies such as:

- Retries
- Timeouts
- Circuit Breaker
- Rate Limits

# Frontend

All the frontend code can be found on the folder `/frontend`

## Requirements

Before you can install Angular, you’ll need to have Node.js and npm (Node Package Manager) installed on your machine.

Then, install angular CLI on your machine

```shell script
npm install -g @angular/cli
```

## Running the application

To run the application use the following command:

```shell script
ng serve
```

The application will be available at http://localhost:4200

## Routes

- / - Login Page
- /home - Catalog Page
- /cart - View cart page
- /orders - View orders history page

## TODOs & Improvements

This project have improvements to be done on the frontend:

- More error states fallbacks
- Protected routing
- Improve footer

On the backend some improvements can be made as well:

- Improve validation and constrains of requests using Hibernate
- Generic payload and error abstractions
- Add more endpoints
