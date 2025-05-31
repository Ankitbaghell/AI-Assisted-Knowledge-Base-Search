# AI-Powered Knowledge Base Search (Assignment 3)

This is a Java Spring Boot backend application developed as part of an AI-native developer assignment. It simulates an AI-assisted search engine for IT knowledge base queries using hardcoded logic.

---

## ✨ Core Functionality Overview

This application allows users to:

1. **Add new knowledge base articles** via a REST API.
2. **Search existing articles** based on a text query.
3. The system returns a list of relevant articles and a simulated AI-generated summary.

### 🔄 User Journey

* **Step 1**: The user (or admin) creates articles via `/api/create-articles`.
* **Step 2**: A user sends a query to `/api/search-query?query=...`.
* **Step 3**: The system searches for relevant articles by content/title.
* **Step 4**: A hardcoded summary is returned based on the number of articles matched.

---

## 🔧 Setup Instructions

### 🔹 Prerequisites

* Java 17+
* Maven 3.6+
* MySQL database (running locally)

### 📂 Database Setup

Create a MySQL database named `ai_knowledge_base`:

```sql
CREATE DATABASE ai_knowledge_base;
```

### ⚙️ Configuration (application.properties)

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/ai_knowledge_base?useSSL=false&serverTimezone=UTC&createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=mysql
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```

### 🚀 Run the App

```bash
mvn spring-boot:run
```

The app starts on `http://localhost:8080`

---

## 🔐 API Usage

### ✉️ POST `/api/create-articles`

Create a knowledge base article.

**Request Body:**

```json
{
  "title": "Reset Password Guide",
  "content": "Steps to reset your password securely...",
  "url": "https://intranet.company/reset-password",
  "source": "internal-doc"
}
```

### 🤖 POST `/api/search-query?query=your-keyword`

Search for articles related to a keyword.

**Response:**

```json
{
  "relevant_articles": [
    {
      "id": 1,
      "title": "Reset Password Guide",
      "content": "Steps to reset your password securely...",
      "url": "https://intranet.company/reset-password",
      "source": "internal-doc"
    }
  ],
  "ai_summary_answer": "Summary of relevant articles: Reset Password Guide"
}
```

---

## 🏠 Software Design Choices & Justification

### ✔️ Chosen Technologies

* **Java + Spring Boot**: Enterprise-ready and familiar.
* **MySQL**: Relational DB for persistence.
* **Lombok**: To reduce boilerplate code.
* **SpringDoc + Swagger**: Auto-generate API documentation.

### 📄 Database Schema

* **Article** Entity:

  * `id` (PK, auto-increment)
  * `title`: string
  * `content`: text
  * `url`: string
  * `source`: string

### 📊 API Design

* RESTful design with meaningful endpoints.
* Clear separation of concerns via controller/service layers.
* Validation via Hibernate Validator on DTOs.

### 🔹 Module Selection Rationale

* **Mandatory Modules:**

  * Simulated AI logic (search and summary)
  * Data persistence with MySQL
  * Backend API ingestion

* **Unimplemented Frontend**: Could later integrate a React dashboard to consume the `/search-query` endpoint and display results.

---

| #  | Context / Task                          | Prompt Given                                                          | AI Suggestion                                                             | Action Taken                      |
| -- | --------------------------------------- | --------------------------------------------------------------------- | ------------------------------------------------------------------------- | --------------------------------- |
| 1  | MySQL config                            | "Spring Boot datasource properties for MySQL"                         | Provided complete configuration including `createDatabaseIfNotExist=true` | ✅ Used                            |
| 2  | Search controller method                | "Spring controller method with query param using ResponseEntity"      | Gave method using `@RequestParam`, `ResponseEntity.ok()`                  | ✅ Used and implemented            |
| 3  | Simulate AI summary                     | "Java logic to create a summary string from a list of article titles" | Used `StringBuilder` with `\n`                                            | ✅ Initially used, improved later  |
| 4  | Clean AI summary output                 | "Create a comma-separated string from article titles"                 | Suggested `String.join()`                                                 | ✅ Used to replace `\n` output     |
| 5  | Create ArticleRequest to Article mapper | "Map DTO to entity in Java"                                           | Provided conversion method                                                | ✅ Used                            |
| 6  | Global Exception Handler                | "Spring Boot @RestControllerAdvice handler"                           | Suggested handlers for Runtime and generic Exception                      | ✅ Implemented                     |
| 7  | ErrorResponse class                     | "Java class with status and message"                                  | Gave full class with fields and getters/setters                           | ✅ Implemented                     |
| 8  | Constructor injection                   | "Convert Spring `@Autowired` to constructor injection"                | Suggested constructor-based approach                                      | ✅ Applied across project          |
| 9  | Logging with SLF4J                      | "Use @Slf4j and log request and response in controller"               | Added log.info calls                                                      | ✅ Used after fixing Lombok config |
| 10 | Swagger documentation                   | "Add Swagger to Spring Boot 3 project"                                | Suggested correct Springdoc dependency and OpenAPI bean                   | ✅ Used, with fixes                |
| 11 | Article creation API                    | "Spring Boot POST endpoint to create DB entity"                       | Provided controller/service/repo structure                                | ✅ Used                            |
| 12 | URL fix for JDBC error                  | "Spring Boot JDBC URL error fix"                                      | Diagnosed duplicate/invalid `url` property                                | ✅ Fixed                           |
| 13 | Lombok errors in IDE                    | "Lombok not working in IntelliJ"                                      | Listed plugin, annotation processing, rebuild steps                       | ✅ Solved issue                    |
| 14 | Hibernate validation                    | "Use Hibernate Validator annotations on DTO"                          | Gave `@NotBlank`, `@Size`, etc.                                           | ✅ Used on `ArticleRequest`        |
| 15 | Swagger not loading                     | "Springdoc Swagger UI not working"                                    | Helped update dependency version and path                                 | ✅ Resolved                        |



---

## 🖋️ Assumptions Made

* No real OpenAI integration (summary logic is simulated).
* All data managed in MySQL, no caching.
* No authentication/authorization for admin endpoints.

---

## 🦄 Potential Improvements

* Integrate actual OpenAI via Spring AI for smarter summaries.
* Add a frontend using React or Next.js.
* Extend the AI summary to show recommended actions.
* Add search history and user feedback.

---

✅ End of README
