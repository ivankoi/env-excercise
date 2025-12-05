Feedback Form Web App (Spring Boot)

A simple Spring Boot application that provides:
- A public feedback form (`/feedback`)
- An admin feedback list with filtering and sorting (`/admin/feedback`) protected by login

Built with Spring Boot, Thymeleaf, Spring Data JPA, H2, and Spring Security.

## Prerequisites
- Java 21 (JDK 21)
- Git
- Maven is optional (project includes Maven Wrapper `mvnw`/`mvnw.cmd`)

Check Java version:
```
java -version
```
It should report version 21.

## Run after cloning
1. Clone the repository and enter the project directory:
   - Using SSH:
     ```
     git clone <your_repo_ssh_url>.git
     cd env-excercise
     ```
   - Or using HTTPS:
     ```
     git clone <your_repo_https_url>.git
     cd env-excercise
     ```

2. Start the application (uses the Maven Wrapper):
   - macOS/Linux:
     ```
     ./mvnw spring-boot:run
     ```
   - Windows (PowerShell or CMD):
     ```
     mvnw.cmd spring-boot:run
     ```

   Alternatively, build the jar and run it:
   - Build:
     ```
     ./mvnw clean package
     ```
   - Run the jar:
     ```
     java -jar target/env-excercise-0.0.1-SNAPSHOT.jar
     ```

3. Open in your browser:
   - Feedback form: http://localhost:8080/feedback
   - Admin list (login required): http://localhost:8080/admin/feedback
     - Username: `admin`
     - Password: `admin123`
   - H2 console (optional): http://localhost:8080/h2-console
     - JDBC URL: `jdbc:h2:mem:feedbackdb`
     - User: `sa` (no password)

## Configuration
- App configuration: `src/main/resources/application.properties`
  - Default port: `8080` (change with `server.port=...`)
  - In-memory H2 database (data resets on restart)
- Security user is defined in `SecurityConfig` (in-memory). Change credentials there if needed.

## Project structure (key parts)
- `src/main/java/.../EnvExcerciseApplication.java` – main application
- `src/main/java/.../feedback/*` – entity, enum, repository
- `src/main/java/.../web/*` – controllers
- `src/main/java/.../config/SecurityConfig.java` – security setup
- `src/main/resources/templates/*` – Thymeleaf templates
- `src/main/resources/application.properties` – app settings

## Troubleshooting
- Port already in use: change `server.port` in `application.properties` or stop the process using port 8080.
- Permission on Maven Wrapper (macOS/Linux):
  ```
  chmod +x mvnw
  ```
- Java version errors: ensure `JAVA_HOME` points to JDK 21 and `java -version` reports 21.

## Notes
- This is a demo app; authentication uses an in-memory user and an in-memory database.
- Thymeleaf cache is disabled for easier development.
