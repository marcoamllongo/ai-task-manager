# Development Log

This document records important development decisions, debugging situations, and lessons learned while building the AI Task Manager.

---

## 2026-09-24

### Goal

Build a complete backend project using Java, Spring Boot, PostgreSQL, Docker, automated tests, OpenAPI documentation, and generative AI integration.

---

### Project Setup

#### What I implemented

- Spring Boot project with Maven
- Java 25 configuration
- PostgreSQL dependency
- Spring Data JPA
- Validation
- Spring Web MVC
- SpringDoc OpenAPI
- Spring AI with OpenAI

#### Problem

The project initially had issues recognizing the Java installation correctly.

#### Cause

The installed JDK configuration was incomplete or incorrectly recognized by IntelliJ.

#### Fix

Reconfigured the project SDK using Microsoft OpenJDK 25.

#### Learned

The Java version configured in the IDE, Maven configuration, and runtime environment must be compatible.

---

### Layered Architecture

#### What I implemented

Separated the application into:

- Controller
- Service
- Repository
- DTO
- Entity
- Exception
- AI integration layers

#### Learned

Each layer has a different responsibility.

The controller handles HTTP concerns.

The service coordinates application operations.

The repository handles persistence.

DTOs define API contracts.

Entities represent persistent data.

Keeping these responsibilities separate makes the application easier to maintain and test.

---

### PostgreSQL and JPA

#### What I implemented

Created the `Task` entity with:

- UUID identifier
- Title
- Purpose
- Description
- Deadline
- Status
- Creation timestamp

Created `TaskRepository` using Spring Data JPA.

#### Learned

Spring Data JPA can generate repository implementations automatically from interfaces.

Hibernate maps Java entities to relational database tables.

Using `EnumType.STRING` keeps enum values readable in the database and avoids depending on enum ordinal positions.

---

### REST API

#### What I implemented

Created endpoints for:

- Creating tasks
- Retrieving all tasks
- Retrieving one task
- Updating tasks
- Updating task status
- Deleting tasks
- AI task analysis

#### Learned

HTTP methods should represent the intended operation.

For example:

- POST for creation and AI operations
- GET for retrieval
- PUT for replacing editable task information
- PATCH for updating task status
- DELETE for deletion

---

### Error Handling

#### Problem

Requesting a nonexistent task originally required handling the error manually.

#### Fix

Created:

- `TaskNotFoundException`
- `GlobalExceptionHandler`

Used Spring `ProblemDetail` to return a structured HTTP 404 response.

#### Learned

Centralized exception handling keeps controllers simpler and creates consistent API error responses.

---

### Automated Testing

#### What I implemented

Added unit tests for `TaskService` using JUnit and Mockito.

Tests cover:

- Creating a task
- Retrieving a task
- Throwing an exception when a task does not exist

#### Problem

Java 25 displayed a Mockito warning about dynamic agent loading.

#### Result

The tests still passed successfully.

#### Learned

JUnit provides the test structure and assertions.

Mockito provides mock dependencies, stubbing, and interaction verification.

Mocks allow the service layer to be tested without connecting to the real database.

---

### OpenAI Integration

#### What I implemented

Created `AIAnalysisService` using Spring AI.

The service sends task information to the OpenAI API and returns:

- Priority
- Reason
- Suggested action

The response is mapped into `AIAnalysisResponse`.

#### Problem

The first real AI request returned HTTP 429.

#### Cause

The OpenAI API account had no available API credits.

#### Fix

Added API credits to the OpenAI account and retried the request.

#### Result

The endpoint successfully returned HTTP 200 with a structured AI response.

#### Learned

A valid API key does not necessarily mean API requests can be completed.

Authentication, billing, provider availability, and application configuration are separate concerns.

I also learned the difference between prompt instructions and structured output:

- Instructions define what the model should analyze.
- Structured output defines the required response shape.

---

### Secure Configuration

#### What I implemented

Moved sensitive configuration to environment variables.

Examples:

- Database password
- OpenAI API key

Created a local `.env` file for Docker Compose.

#### Problem

The `.env` file was accidentally staged before being added to `.gitignore`.

#### Fix

Added `.env` to `.gitignore` and removed it from the staging area using:

```bash
git restore --staged .env