# README #

![alt text](https://github.com/r4ulk/ze-delivery/blob/master/src/main/resources/static/logo-white-text.png?raw=true)

This a code challenge of Zé Delivery using:

- Spring-Boot (Java 11), 
- MongoDB
- Gradle build
- Docker
- npm
- Swagger 2 (API Doc)

Plus (+)

- ReactJS 

### About the challenge ###

Create an API Restful that provide the requirements: 

1 - Create partner - All fields are required and must follow the rules set above.
    
2 - Get partner by id - Get a specific partner by its id.

3 - Search partner - Given a specific location (coordinates lng and lat), search the nearest partner considering each partner's coverage area.

### Requirements ###

To build and run we will need:

1 - npm

2 - Gradle

3 - Docker

### How do I get set up? ###

1 - Run the followed codes in your terminal:
```
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

3 - Start up the containers
```
docker-compose up
```
*It will put up db container and app

### Troubleshooting ###

If you have some problem with npm dependencies, try to run to install manually dependencies:
```
npm i npm to update
npm install webpack --save
npm install babel-core babel-loader babel-preset-es2015 babel-preset-react --save
npm install react react-dom --save
npm install webpack-dev-server --dev-install
./node_modules/.bin/webpack -d
```

### Lets try ###

1 - API Documentation
http://localhost:8080/swagger-ui.html

![alt text](https://github.com/r4ulk/ze-delivery/blob/master/src/main/resources/static/ze-swagger.png?raw=true)

- #### Exposed API's ###

  - 1 - Get Store by Id - (GET - http://localhost:8080/stores/1)

  - 2 - Get Stores list by near location - (GET - http://localhost:8080/stores?lng=-43.36556&lat=-22.99669)

  - 3 - Create Store - (POST - http://localhost:8080/stores?lng=-43.36556&lat=-22.99669)

##### Home #####
`http://localhost:8080`

###### You will see: ######

![alt text](https://github.com/r4ulk/ze-delivery/blob/master/src/main/resources/static/ze-home.png?raw=true)

###### Search engine ######

![alt text](https://github.com/r4ulk/ze-delivery/blob/master/src/main/resources/static/ze-auto-complete.png?raw=true)

### Credits ###

Raul Klumpp <raulklumpp@gmail.com>

https://www.linkedin.com/in/raulk/
