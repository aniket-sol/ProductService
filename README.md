# ProductService - E-commerce Backend Microservice

This repository contains the **ProductService** module for an e-commerce backend, developed using **Java Spring Boot** in a microservice architecture. The service is designed to handle product-related operations with scalability, performance, and maintainability in mind.

## Key Features

- **Architecture:** Built on the **MVC design pattern** for clear separation of concerns.
- **Caching:** Integrated **Redis** for caching to improve data retrieval speed and reduce database load.
- **Persistence:** Utilized **JPA** with **Hibernate ORM** for seamless database interaction and efficient CRUD operations.
- **Database:** Configured with **MySQL** for robust and reliable data storage.
- **API Design:** Exposed **RESTful APIs** for interaction with the service.
- **Data Handling:** Implemented **DTOs (Data Transfer Objects)** for structured and secure data exchange in API requests and responses.

## Core Functionality

The service includes the following primary methods:

1. **Get All Products:** 
   - Retrieves a list of all available products.
   - Exposed as a GET API: `/products`

2. **Get Product by ID:** 
   - Fetches details of a specific product by its unique identifier.
   - Exposed as a GET API: `/products/{id}`

3. **Create Product:** 
   - Allows adding a new product to the database with validation and error handling.
   - Exposed as a POST API: `/products`

## Tech Stack

- **Java Spring Boot**: Main framework for developing the backend service.
- **Spring Data JPA**: For easy integration with relational databases.
- **Hibernate ORM**: Object-relational mapping framework for working with databases.
- **MySQL**: Relational database used for persistent data storage.
- **Redis**: In-memory data store used for caching product data to reduce database load and speed up retrieval.
- **RESTful API**: For communication between microservices and client applications.
- **DTO (Data Transfer Objects)**: Used for sending and receiving structured data between APIs to ensure data integrity and security.

## Installation and Setup

### 1. Set Up MySQL and Redis

1. **MySQL Database:**
   - Create a new database for the **ProductService** (e.g., `product_db`).
     ```sql
     CREATE DATABASE product_db;
     ```

2. **Redis:**
   - Ensure **Redis** is running on your local machine or connect to a Redis service.
     - For local Redis setup, install Redis and start the server:
       ```bash
       redis-server
       ```
   - Alternatively, use a hosted Redis service like [Redis Labs](https://redislabs.com/).

### 2. Configure the `application.properties` File

Update the `application.properties` file with the following configurations:

- **MySQL:**
  ```properties
  spring.datasource.url=jdbc:mysql://localhost:3306/product_db
  spring.datasource.username=your_mysql_username
  spring.datasource.password=your_mysql_password
  spring.jpa.hibernate.ddl-auto=update
  spring.jpa.show-sql=true
- **Redis:**
  ```properties
  spring.redis.host=localhost
  spring.redis.port=6379

### 3. Run the Application

- **Using Maven:** You can run the application with the following command:

  ```bash
  mvn spring-boot:run

- **Using IDE:**
Alternatively, you can run the application directly from your IDE (such as IntelliJ IDEA or Eclipse).
