# AI Task Manager

A backend REST API for task management built with Java and Spring Boot, with PostgreSQL persistence, automated tests, Docker containerization, OpenAPI documentation, and AI-assisted task analysis using OpenAI through Spring AI.

The project demonstrates backend engineering concepts including layered architecture, REST APIs, persistence, dependency injection, testing, containerization, secure configuration, and generative AI integration.

---

## Resumo

API backend para gerenciamento de tarefas desenvolvida com Java e Spring Boot, utilizando PostgreSQL, JPA/Hibernate, Docker, testes automatizados, documentação com OpenAPI/Swagger e integração com Inteligência Artificial por meio do Spring AI e da API da OpenAI.

O projeto utiliza arquitetura em camadas, separação entre DTOs e entidades, tratamento centralizado de exceções, configuração segura por variáveis de ambiente e respostas estruturadas geradas por IA.

> The complete technical documentation is available below in English.

## Features

- Create, retrieve, update, and delete tasks
- Update task lifecycle status independently
- Persist tasks in PostgreSQL
- Validate incoming API requests
- Handle missing resources with structured HTTP errors
- Generate AI-assisted task analysis
- Return structured AI responses
- Interactive API documentation with Swagger UI
- Unit testing with JUnit and Mockito
- Run the application and database using Docker Compose
- Secure API credentials using environment variables

---

## AI Task Analysis

Each task can be analyzed using an OpenAI model through Spring AI.

The AI receives information such as:

- Title
- Purpose
- Description
- Deadline
- Current status

It returns a structured response containing:

```json
{
  "priority": "HIGH",
  "reason": "The deadline is close and the task has not yet been started.",
  "suggestedAction": "Begin with the highest-priority deliverable and define the next concrete action."
}