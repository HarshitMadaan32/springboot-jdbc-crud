Overview

This project is a backend practice application built using Spring Boot and JdbcTemplate to implement full CRUD operations for Authors and Books.

The main goal of this project is to understand how Spring Boot interacts with a relational database at a low level using JDBC, and how to properly test DAO logic using both unit tests and integration tests.

Features - 

Create, Read, Update, Delete Authors

Create, Read, Update, Delete Books

JDBC-based persistence using JdbcTemplate

Custom RowMapper implementations

Clean layered architecture (Domain → DAO → Implementation)

Unit testing with Mockito

Integration testing with H2 in-memory database

Tech Stack- 

Java 21

Spring Boot

Spring JDBC (JdbcTemplate)

H2 Database

JUnit 5

Mockito

AssertJ


Testing Strategy- 

Unit Tests

DAO logic is tested using Mockito

Database calls are mocked

Focused on verifying correct SQL execution and method interactions

Integration Tests - 

Use H2 in-memory database

Test real SQL execution

Validate schema, queries, and RowMapper behavior

Ensure full data flow works correctly

How to Run the Project -
Run tests
mvn clean test

Run application
mvn spring-boot:run

What I Learned - 

How Spring Boot manages database connections using DataSource

Writing SQL queries using JdbcTemplate

Mapping ResultSet rows using RowMapper

Difference between unit tests and integration tests

Proper use of Dependency Injection

Importance of interfaces over implementations

Writing clean, testable backend code

Future Improvements - 

Add REST API layer (Controllers)

Add validation using Bean Validation

Introduce pagination and search

Implement JPA version for comparison

Add Docker support for database

Notes

This project was built as a learning exercise to strengthen backend fundamentals and understand real-world Spring Boot application structure and testing practices.
