# NoteOrg - Smart Class Notes Organizer

NoteOrg is a modern web application built with Spring Boot to help students organize their class notes effectively.

## Features
- **User Authentication**: Secure login and signup.
- **Note Management**: Create, edit, and delete notes.
- **Categorization**: Organize notes by subjects.
- **Favorites**: Mark important notes as favorites.
- **Modern UI**: Clean, responsive pastel-themed dashboard.

## Prerequisites
- Java 17
- Maven
- MySQL

## Setup Instructions

1. **Database Setup**:
   - Create a MySQL database named `noteorg`.
   - Update `src/main/resources/application.properties` with your MySQL username and password if they differ from the defaults (`root` / no password).

2. **Build the Project**:
   ```bash
   mvn clean install
   ```

3. **Run the Application**:
   ```bash
   mvn spring-boot:run
   ```

4. **Access the App**:
   - Open your browser and go to `http://localhost:8080`.
   - Sign up for a new account and start organizing!

## Technologies Used
- **Backend**: Spring Boot 3.2.2, Spring Security, Spring Data JPA
- **Frontend**: Thymeleaf, Vanilla CSS
- **Database**: MySQL
