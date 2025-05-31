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

## 🤖 AI Code Assistant Usage Log (Detailed)

| #  | Context / Task           | Prompt Given to AI                        | AI Output / Suggestion              | Action Taken            |
| -- | ------------------------ | ----------------------------------------- | ----------------------------------- | ----------------------- |
| 1  | MySQL Config             | Spring Boot MySQL config                  | Gave full datasource properties     | ✅ Used                  |
| 2  | Create search controller | REST API with query param and service     | ResponseEntity and method signature | ✅ Implemented           |
| 3  | Search logic             | Find articles by keyword                  | `filter().collect()` logic          | ✅ Used                  |
| 4  | Improve summary output   | Remove newlines in string summary         | Suggested `String.join()`           | ✅ Replaced              |
| 5  | DTO mapping              | Convert request to entity                 | Field mapping method                | ✅ Used                  |
| 6  | Exception handling       | Global handler with @RestControllerAdvice | Runtime & Exception handlers        | ✅ Implemented           |
| 7  | ErrorResponse class      | Java class with status + message          | Provided class with getters/setters | ✅ Added                 |
| 8  | Logging with SLF4J       | Use @Slf4j in Spring classes              | Added log.info(...) calls           | ✅ Used after IDE config |
| 9  | Swagger integration      | Swagger 3 + Spring Boot 3 support         | SpringDoc config class + UI URL     | ✅ Working               |
| 10 | Validation               | Hibernate Validator for request DTO       | `@NotBlank`, `@Size`, etc.          | ✅ Annotated DTO         |


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
