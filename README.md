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

## 📄 AI Code Assistant Usage Log (Detailed)

| Prompts | AI Response | Actions | Context |
|---------|-------------|---------|---------|
| in this file please write all the datasource related properties required to connect with mysql database in spring boot | `# MySQL Database Configuration`<br>`spring.datasource.url=jdbc:mysql://localhost:3306/ai_knowledge_base?useSSL=false&serverTimezone=UTC`<br>`spring.datasource.username=root`<br>`spring.datasource.password=mysql`<br>`spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver`<br><br>`# Hibernate Configuration`<br>`spring.jpa.hibernate.ddl-auto=update`<br>`spring.jpa.show-sql=true`<br>`spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect` | Used with some modification in datasource url | MySQL configurations |
| in this class create the controller method which will take serach query in parameter and then will call the service call, and also use responseEntity class to send success response code and response | ```@RestController`<br>`@RequestMapping("/api/")`<br>`public class AISearchController {`<br><br>`@Autowired`<br>`private AISearchService aiSearchService;`<br><br>`@PostMapping("/search-query")`<br>`public ResponseEntity<String> searchQuery(@RequestParam("query") String query) {`<br>`String result = aiSearchService.search(query);`<br>`return ResponseEntity.ok(result);`<br>`}`<br>`}```` | Used and implemented | Controller method for searching functionality |
| Write the logic for both methods getRelevantArticles and getAiSummaryAnswer , getAiSummaryAnswer will give the summary of all these articles provided in parameter and the getRelevantArticles method will return relevent artciles based on query from all the articles | ```public class AiSimulator {`<br><br>`public List<Article> getRelevantArticles(List<Article> allArticles, String query) {`<br>`return allArticles.stream()`<br>`.filter(article -> article.getTitle().toLowerCase().contains(query.toLowerCase()) ||`<br>`article.getContent().toLowerCase().contains(query.toLowerCase()))`<br>`.collect(Collectors.toList());`<br>`}`<br><br>`public String getAiSummaryAnswer(List<Article> relevantArticles, String query) {`<br>`if (relevantArticles.isEmpty()) {`<br>`return "No relevant articles found for the query: " + query;`<br>`}`<br><br>`StringBuilder summary = new StringBuilder("Summary of relevant articles:\n");`<br>`for (Article article : relevantArticles) {`<br>`summary.append("- ").append(article.getTitle()).append("\n");`<br>`}`<br><br>`return summary.toString();`<br>`}`<br>`}```` | Used with some modification | To Mock AI logic |
| selected the files and - change the autowire to constructor injection | ```@RestController`<br>`@RequestMapping("/api/")`<br>`public class AISearchController {`<br><br>`private final AISearchService aiSearchService;`<br><br>`public AISearchController(AISearchService aiSearchService) {`<br>`this.aiSearchService = aiSearchService;`<br>`}`<br><br>`@PostMapping("/search-query")`<br>`public ResponseEntity<SearchResponse> searchQuery(@RequestParam("query") String query) {`<br>`SearchResponse result = aiSearchService.searchQuery(query);`<br>`return ResponseEntity.ok(result);`<br>`}`<br>`}```` | Used | Best Practice to use constructor injection |
| write global exception handling in this class, handle runtime exception, Exception class using @RestControllerAdvice | ```@RestControllerAdvice`<br>`public class GlobalExceptionHandler {`<br><br>`@ExceptionHandler(RuntimeException.class)`<br>`public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {`<br>`return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)`<br>`.body("Runtime Exception occurred: " + ex.getMessage());`<br>`}`<br><br>`@ExceptionHandler(Exception.class)`<br>`public ResponseEntity<String> handleException(Exception ex) {`<br>`return ResponseEntity.status(HttpStatus.BAD_REQUEST)`<br>`.body("An error occurred: " + ex.getMessage());`<br>`}`<br>`}```` | Used with some modification, added my own custom exception handler method as well | Global Exception Handling |
| in all these files please put slf4j logs and using @Slf4j lombok anotation | ```@RestController`<br>`@RequestMapping("/api/")`<br>`@Slf4j`<br>`public class AISearchController {`<br><br>`private final AISearchService aiSearchService;`<br><br>`public AISearchController(AISearchService aiSearchService) {`<br>`this.aiSearchService = aiSearchService;`<br>`}`<br><br>`@PostMapping("/search-query")`<br>`public ResponseEntity<SearchResponse> searchQuery(@RequestParam("query") String query) {`<br>`log.info("Received search query: {}", query);`<br>`SearchResponse result = aiSearchService.searchQuery(query);`<br>`log.info("Search response: {}", result);`<br>`return ResponseEntity.ok(result);`<br>`}`<br>`}```` | Used | To achive Logging |
| i need to add swagger documentation in this project, give me its dependency and required configurations | ```@Configuration`<br>`public class SwaggerConfig {`<br>`@Bean`<br>`public OpenAPI customOpenAPI() {`<br>`return new OpenAPI()`<br>`.info(new Info()`<br>`.title("AI Knowledge Base Search API")`<br>`.version("1.0")`<br>`.description("API documentation for the AI Knowledge Base Search application"));`<br>`}`<br>`}`<br><br>`http://localhost:8080/swagger-ui.html` | Used with some changes, instead of bean creation i put the configuration in application.properties file and dependency version changed to stablish compatiblity with spring boot version | Swagger documentation, easy way to test APIs |
| i need to create api to create articles in my db, then i gives me code changes to do in classes, and create a Function to convert articlerequest to article | ```public Article convertToArticle(ArticleRequest articleRequest) {`<br>`Article article = new Article();`<br>`article.setTitle(articleRequest.getTitle());`<br>`article.setContent(articleRequest.getContent());`<br>`article.setUrl(articleRequest.getUrl());`<br>`article.setSource(articleRequest.getSource());`<br>`return article;`<br>`}```` | used | API to create articles in DB and a mapped function to map DTO object to DAO object and vice versa |
| Implement Hibernate Validator in ArticleRequest class | ```@Data`<br>`public class ArticleRequest {`<br><br>`@NotBlank(message = "Title cannot be blank")`<br>`@Size(max = 255, message = "Title cannot exceed 255 characters")`<br>`private String title;`<br><br>`@NotBlank(message = "Content cannot be blank")`<br>`private String content;`<br><br>`@NotBlank(message = "URL cannot be blank")`<br>`@Size(max = 2083, message = "URL cannot exceed 2083 characters")`<br>`private String url;`<br><br>`@NotBlank(message = "Source cannot be blank")`<br>`@Size(max = 255, message = "Source cannot exceed 255 characters")`<br>`private String source;`<br>`}```` | Used with some changes, used @URL for url field | server side validation |


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
