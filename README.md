# WISHLIST BACKEND TEST

given that one of the most interesting features of an e-commerce 
is the wish list, where from the product screen or product details, 
the user is able to add that product to their wish list.

Since because it is a microservice, the product information and user logged in 
will be managed by other services, this API therefore treats these 
entities as Id's and saves them to a Nosql bank (mongoDb) 
including creation date.

The API was developed using the concepts of a REST API, clean architecture, and BDD
The database will run into a mongo docker container if you want to change the connection, 
simply change the settings in application.properties.

## Getting Started

#### Run test suite:
    mvn test
#### start the database container:
    docker compose up -d
#### start application:
    mvn spring-boot:run

##### To see API SWAGGER doc access: http://localhost:8080/swagger-ui/

## Features available

### ADD WISH
    feature: Add a wish on wishlist
    endpoint: /api/v1/{userId}/wishlist
    method: POST
    path-variable: userId must be a UUID
    response-status: 201 CREATED
body:
```json
    {
      "product": "4d2e1274-16ac-40b6-a6cd-9cfc0f05303d"
    }
```
Note: the wishlist allows only 20 items per user

### REMOVE
    feature: REMOVE a item of user wishlist
    endpoint: /api/v1/{userId}/wishlist/{productId}
    method: DELETE
    path-variable: userId must be a UUID
    path-variable: productId must be a UUID
    response-status: 203 NO-CONTENT

Note: invalid params should be return status 400 and error on body message.

### CONSULT ALL
    feature: GetAll itens from a user wishlist
    endpoint: /api/v1/{userId}/wishlist
    method: GET
    path-variable: userId must be a UUID
    response-status: 200 ok

Note: A empty list will be returned when user not found.

### CONSULT IF A PRODUCT IS ON USER WISHLIST
    feature: verify if a produt is present on user wishlist
    endpoint: /api/v1/{userId}/wishlist/{productId}
    method: GET
    path-variable: userId must be a UUID
    path-variable: productId must be a UUID
    response-status: 200 ok
Response body sample:
```json
    {
      "is-present": true
    }
```
##### Used technologies
 - [SpringBoot](https://spring.io/)
 - [MongoDB](https://www.mongodb.com/pt-br?msclkid=ea97f84ad07f11ec9dc819959e755a61)
 - [Docker](https://www.docker.com/?msclkid=fde569b7d07f11ecaad68af68f8774c4)
 - [Swagger](https://swagger.io/tools/swagger-ui/?msclkid=091cfe69d08011ec8fa50fe356ac0f85)
 - [Cucumber](https://cucumber.io/?msclkid=14a46b40d08011eca3361d2e6ed535cd☺)












