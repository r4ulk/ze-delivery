# README #

This a code challenge of Zé Delivery using:
- Spring-Boot (Java 11), 
- MongoDB
- Gradle build
- Docker
- npm
- Swagger 2 (API Doc)

Plus +
- ReactJS 

### About the challenge ###

Create an API Restful that provide the requirements: 

1 - Create partner<br />
All fields are required and must follow the rules set above.
    
2 - Get partner by id<br />
Get a specific partner by its id.

3 - Search partner<br />
Given a specific location (coordinates lng and lat), search the nearest partner considering each partner's coverage area.

### Requirements ###
To build and run we will need:

1 - npm
2 - Gradle
3 - Docker

### How do I get set up? ###

1 - Run the followed codes in your terminal:
```
npm i npm to update
npm install
```
*It will download React dependencies.

2 - Build Spring-Boot application
```
gradle build
```
*It will run unit tests and compile

To run integrated tests (require a up local MongoDB server)
```
gradle integrationTest
```

3 - Building local Docker image
```
docker build -t zedelivery .
```

$ - Start up the containers
```
docker-compose up
```
*It will put up db container and app

### Troubleshooting ###

If you have some problem with npm dependencies, try to run to install manually dependencies:
```
npm install webpack --save
npm install babel-core babel-loader babel-preset-es2015 babel-preset-react --save
npm install react react-dom --save
npm install webpack-dev-server --dev-install
./node_modules/.bin/webpack -d
```
and again to up spring-boot with gradle:
`./gradlew build && java -jar build/libs/jumbo-0.1.0.jar`

### Lets try ###

1 - API Documentation
http://localhost:8080/swagger-ui.html

- #### Exposed API's ###

  - 1 - Get Store by Id<br />
GET - http://localhost:8080/stores/1

  - 2 - Get Stores list by near location<br />
GET - http://localhost:8080/stores?lng=-43.36556&lat=-22.99669

  - 3 - Create Store<br />
POST - http://localhost:8080/stores?lng=-43.36556&lat=-22.99669

Home
`http://localhost:8080`

### Credits ###

Raul Klumpp <raulklumpp@gmail.com>