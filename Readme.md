# Spring Boot Authentication App 🔐

A complete full-stack authentication system built with **Spring Boot** (Backend) and **Thymeleaf** (Frontend) featuring JWT authentication, MySQL database integration, and Bootstrap UI.

## 🚀 Features

- ✅ **User Registration** - Secure user signup with validation
- ✅ **User Authentication** - Login with JWT token generation
- ✅ **Protected Dashboard** - JWT-secured user dashboard
- ✅ **Password Security** - BCrypt password hashing
- ✅ **MySQL Integration** - Persistent data storage
- ✅ **Responsive UI** - Bootstrap 5 with Font Awesome icons
- ✅ **Form Validation** - Client and server-side validation

## 🛠️ Tech Stack

### Backend

- **Spring Boot 3.1.5** - Main framework
- **Spring Data JPA** - Database operations
- **MySQL 8+** - Database
- **JWT (JSON Web Tokens)** - Authentication
- **BCrypt** - Password hashing
- **Maven** - Dependency management

### Frontend

- **Thymeleaf** - Template engine
- **Bootstrap 5** - CSS framework
- **Font Awesome** - Icons
- **HTML5/CSS3** - Markup and styling

## 📋 Prerequisites

Before running this application, make sure you have:

- **Java 17+** installed
- **Maven 3.6+** installed
- **MySQL 8+** installed and running
- **Git** installed (for cloning)

## ⚙️ Installation & Setup

### 1. Clone the Repository

git clone https://github.com/yourusername/spring-boot-auth-app.git
cd spring-boot-auth-app

### 2. Database Setup

-- Create database
CREATE DATABASE authapp CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

### 3. Configure Database Connection

Update `src/main/resources/application.properties` with your database credentials:

MySQL Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/authapp?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=YOUR_MYSQL_PASSWORD
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

### 4. Build and Run

mvn clean install
Run the application
mvn spring-boot:run

Run the application
mvn spring-boot:run
