# Online store (java Spring)

The online store is developed in Java using Spring libraries, based on a micro-service architecture.
The application implements authorization functions using JWT Tokens, the cart stores the order using NoSQL Redis.
Orders are generated using Apache Kafka.
The front is implemented on the basis of JS Angular.
The possibility of receiving payment for orders using QIWI has been implemented.

# The project applied:
Apache Kafka
Redis
Spring Boot
Spring MVC
Spring Data JPA
Spring Security
WildFLy
HTML & CSS & JS Angular
Docker
QIWI-кошелек

# Startup of the application:
Through the launch of Docker and launch Redis and Kafka.
For online payment, specify your secret-key in the micro service order-service/src/main/resources/secrets.properties
Start all microservices.
In the browser, follow the link http://localhost:3000/front

Visualization of the online store page: ![1234](https://user-images.githubusercontent.com/77875474/193206368-56fc14c4-4a4e-4852-a87f-421e18931f13.jpg)
