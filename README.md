# MediCore – Healthcare Management Platform

A production-style healthcare management backend built with **Java 21 and Spring Boot**, providing secure REST APIs for managing patients, doctors, appointments, and user access.

The project demonstrates backend engineering practices including **JWT authentication, OAuth2, Role-Based Access Control (RBAC), method-level security, JPA/Hibernate, PostgreSQL, DTO-based API design, validation, centralized exception handling, testing, Swagger/OpenAPI, and Docker**.

---

## 🚀 Key Features

### 🔐 Authentication & Security

- JWT-based authentication
- OAuth2 login with Google
- OAuth2 login with GitHub
- Role-Based Access Control (RBAC)
- Method-level authorization
- Custom `UserDetailsService`
- JWT authentication filter
- Secure password handling
- Permission-based access control

### 🏥 Healthcare Management

- Patient management
- Doctor management
- Appointment management
- Hospital/department management
- Insurance information management
- User and role management

### 📡 REST APIs

- 30+ RESTful API endpoints
- Layered architecture
- DTO-based request/response handling
- Server-side pagination
- Bean Validation
- Centralized exception handling
- Consistent API error responses

### 🗄️ Persistence

- PostgreSQL database
- Spring Data JPA
- Hibernate ORM
- Entity relationships
- Repository abstraction
- DTO projections

### 🧪 Testing

- JUnit 5
- Mockito
- Spring Security Test
- Controller tests
- Service-layer tests
- Repository tests

### 📚 API Documentation

- Swagger/OpenAPI integration
- Interactive API documentation
- API testing through Swagger UI

### 🐳 Containerization

- Dockerized Spring Boot application
- Docker Compose configuration
- PostgreSQL container
- Persistent PostgreSQL volume
- Container-to-container database communication

---

## 🏗️ Architecture

The application follows a clean layered backend architecture:

```text
                    Client
                      │
                      ▼
              ┌───────────────┐
              │   Controller  │
              └───────┬───────┘
                      │
                      ▼
              ┌───────────────┐
              │    Service    │
              └───────┬───────┘
                      │
                      ▼
              ┌───────────────┐
              │   Repository  │
              └───────┬───────┘
                      │
                      ▼
              ┌───────────────┐
              │  PostgreSQL   │
              └───────────────┘

**SECURITY FLOW: **

Client
  │
  │ Login / OAuth2
  ▼
Authentication
  │
  ▼
JWT Token
  │
  ▼
JwtAuthFilter
  │
  ▼
Spring Security
  │
  ▼
Role / Permission Checks
  │
  ▼
Protected REST API

**AUTHENTICATION FLOW**

POST /auth/login
        │
        ▼
AuthenticationManager
        │
        ▼
UserDetailsService
        │
        ▼
Validate Credentials
        │
        ▼
Generate JWT
        │
        ▼
Return Token

🧩 Project Structure

src/
├── main/
│   ├── java/
│   │   └── com/example/SecurityLearning/
│   │       │
│   │       ├── config/
│   │       │   ├── AppConfig.java
│   │       │   └── SwaggerConfig.java
│   │       │
│   │       ├── controller/
│   │       │   ├── AdminController.java
│   │       │   ├── AppointmentController.java
│   │       │   ├── AuthController.java
│   │       │   ├── DoctorController.java
│   │       │   ├── HospitalController.java
│   │       │   ├── PatientController.java
│   │       │   └── MethodSecurityController.java
│   │       │
│   │       ├── dto/
│   │       │
│   │       ├── entity/
│   │       │
│   │       ├── error/
│   │       │   ├── ApiError.java
│   │       │   └── GlobalExceptionHandler.java
│   │       │
│   │       ├── repository/
│   │       │
│   │       ├── security/
│   │       │   ├── AuthService.java
│   │       │   ├── AuthUtil.java
│   │       │   ├── CustomUserDetailsService.java
│   │       │   ├── JwtAuthFilter.java
│   │       │   ├── OAuth2SuccessHandler.java
│   │       │   ├── PermissionMapping.java
│   │       │   └── WebSecurityConfig.java
│   │       │
│   │       └── service/
│   │
│   └── resources/
│       └── application.properties
│
└── test/
    └── java/
        └── com/example/SecurityLearning/
            ├── controller/
            ├── repository/
            └── service/

🛠️ Tech Stack
Category	Technologies
Language	Java 21
Framework	Spring Boot 3.5
Web	Spring MVC, REST APIs
Security	Spring Security, JWT, OAuth2
Persistence	Spring Data JPA, Hibernate
Database	PostgreSQL
Validation	Jakarta Bean Validation
Testing	JUnit 5, Mockito, Spring Security Test
API Documentation	Swagger / OpenAPI
Build Tool	Maven
Containerization	Docker, Docker Compose
Utilities	Lombok, ModelMapper

📦 Dependencies

Major technologies used by the project include:

Spring Boot Web
Spring Boot Data JPA
Spring Boot Security
Spring Boot OAuth2 Client
Spring Boot Validation
PostgreSQL Driver
JJWT
ModelMapper
Jackson
SpringDoc OpenAPI
Lombok
JUnit 5
Mockito

🐳 Running with Docker
Prerequisites

Make sure the following are installed:

Java 21
Maven
Docker Desktop
Start the application

Build and start the containers:

docker compose up --build

This starts:

Spring Boot Application
        │
        │
        ▼
security-app :8080
        │
        │
        ▼
PostgreSQL :5432
Stop the application
docker compose down
Rebuild the application
docker compose up --build

PostgreSQL data is persisted through a Docker volume.

⚙️ Environment Configuration

Sensitive configuration should never be committed to GitHub.

Create a local .env file using:

.env.example

Example:

DB_URL=jdbc:postgresql://localhost:5432/securityDB
DB_USERNAME=postgres
DB_PASSWORD=your_database_password

JWT_SECRET_KEY=your_jwt_secret_key

GOOGLE_CLIENT_ID=your_google_client_id
GOOGLE_CLIENT_SECRET=your_google_client_secret

GITHUB_CLIENT_ID=your_github_client_id
GITHUB_CLIENT_SECRET=your_github_client_secret

The actual .env file is excluded from version control using .gitignore.

📖 API Documentation

After starting the application, Swagger UI can be accessed at:

http://localhost:8080/swagger-ui/index.html

Swagger provides an interactive interface for exploring and testing the REST APIs.

🧪 Running Tests

Run all tests using Maven:

Windows
mvnw.cmd test
Linux / macOS
./mvnw test

The project contains tests covering:

Controller layer
Service layer
Repository layer
Security-related functionality


🔍 Error HandlinG

The application uses centralized exception handling through:

GlobalExceptionHandler

API errors are returned through a consistent error response structure.

The project also uses Bean Validation for validating incoming API requests.

📄 API Desig
The backend uses DTOs to separate API contracts from persistence entities.

Example request flow:

HTTP Request
     │
     ▼
Request DTO
     │
     ▼
Controller
     │
     ▼
Service
     │
     ▼
Entity
     │
     ▼
Repository
     │
     ▼
PostgreSQL

Responses are mapped back to dedicated response DTOs rather than exposing persistence entities directly.

🎯 Engineering Practices

The project focuses on backend engineering practices commonly used in production Spring Boot applications:

Layered architecture
Dependency Injection
DTO pattern
Repository abstraction
Role-Based Access Control
Method-level authorization
JWT-based stateless authentication
OAuth2 authentication
Centralized exception handling
Bean Validation
Pagination
API documentation
Unit and integration-oriented testing
Docker-based environment setup
Environment-based secret management
📌 Future Improvements

Potential extensions to the platform include:

Redis-based distributed caching
Kafka-based asynchronous communication
Microservices architecture
CI/CD pipeline
Cloud deployment
Distributed tracing
Centralized monitoring
Rate limiting


👨‍💻 Author
Rohan Mehta
Backend Developer specializing in:

Java
Spring Boot
Spring Security
REST APIs
PostgreSQL
JPA / Hibernate
Docker
Microservices


⭐ Project Highlights
30+ REST APIs
5+ User Roles
JWT + OAuth2 Authentication
Method-Level Security
PostgreSQL + JPA/Hibernate
Bean Validation
Global Exception Handling
JUnit 5 + Mockito
Swagger/OpenAPI
Docker + Docker Compose

