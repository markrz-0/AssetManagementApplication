# Enterprise Asset Management API

A robust, enterprise-grade REST API built with **Spring Boot** and **Java 21** to manage internal company hardware checkouts.

This project demonstrates modern backend architecture patterns, strict data validation, Domain-Driven Design (DDD) principles, and advanced ORM optimization techniques designed to handle concurrent transactions safely.

## 🚀 Tech Stack

* **Language:** Java 21
* **Framework:** Spring Boot 4.0.4 (Spring Web, Spring Data JPA)
* **Database:** PostgreSQL
* **Tooling:** Maven, Lombok, Jakarta Validation

## 🧠 Core Architectural Features

* **Domain-Driven Design (DDD):** Cleanly separates `Device` (inventory), `Employee` (users), and `Checkout` (transactions) into distinct domains with their own encapsulated logic and DTO contracts.
* **ACID Compliant Transactions:** Utilizes Spring's `@Transactional` boundaries to ensure that device status updates and checkout records are committed atomically, preventing race conditions.
* **N+1 Query Elimination:** Implements custom JPQL `JOIN FETCH` queries to optimize complex relational data retrieval, reducing database network trips to exactly one query for heavily nested dashboard views.
* **DTO Firewall Pattern:** Separates database entities from the web layer using modern Java Records, preventing mass-assignment security vulnerabilities and infinite JSON recursion.
* **RFC 7807 Error Handling:** Utilizes `@RestControllerAdvice` and Spring's native `ProblemDetail` to intercept exceptions globally and return standardized, predictable JSON error contracts to the client.

## 🔌 API Endpoints

### Employees
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `GET` | `/api/v1/employees` | Retrieve all employees |
| `POST` | `/api/v1/employees` | Register a new employee in the system |

### Devices
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `GET` | `/api/v1/devices` | Retrieve all devices |
| `GET` | `/api/v1/devices/available` | Retrieve only available devices |
| `POST` | `/api/v1/devices` | Register a new device to the inventory |

### Checkouts
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `POST` | `/api/v1/checkouts` | Assign an available device to an employee |
| `GET` | `/api/v1/checkouts/employee/{id}/history` | Get an employee's profile and full checkout history |

## 📂 Project Structure (Layered Architecture)

The codebase strictly adheres to standard Spring Boot layered architecture to maintain high cohesion and low coupling:

- `controller/` - REST endpoints and HTTP routing.
- `service/` - Business logic and @Transactional boundaries.
- `repository/` - Spring Data JPA interfaces and custom JPQL queries.
- `model/entity/` - Hibernate domain models representing PostgreSQL tables.
- `model/dto/` - Immutable Java Records defining the API contracts.
- `exception/` - Global error interception and formatting.
