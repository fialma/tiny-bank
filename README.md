# Tiny Bank Application

This is a simple web application that simulates a tiny bank. The application allows for the creation and deactivation of users, depositing and withdrawing money from accounts, transferring money between accounts, viewing account balances, and viewing transaction history. The application is built using Spring Boot and uses in-memory storage.

## Table of Contents

- [Prerequisites](#prerequisites)
- [Launching the App Standalone](#launching-the-app-standalone)
- [Launching the App in Docker](#launching-the-app-in-docker)
- [API Endpoints](#api-endpoints)

## Prerequisites

- Java Development Kit (JDK) 17 or higher
- Apache Maven
- Docker (for Docker deployment)

## Launching the App Standalone

1. **Clone the repository:**
   ```bash
   git clone https://github.com/fialma/tiny-bank.git
   cd tiny-bank
   
2. **Build the project:**
   ```bash
   ./mvnw clean package
   
3. **Run the application:**
   ```bash
   java -jar target/tiny-bank-0.0.1.jar

4. **The application should now be running on http://localhost:8080**

