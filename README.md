# Tiny Bank Application

This is a simple web application that simulates a tiny bank. The application allows for the creation and deactivation of users, depositing and withdrawing money from accounts, transferring money between accounts, viewing account balances, and viewing transaction history. The application is built using Spring Boot and uses in-memory storage (H2).

With this url:

https://elements.getpostman.com/redirect?entityId=23793263-bd2745d5-d9f3-438b-85fe-9ff45ec30fd9&entityType=collection

is possible to fork all the possible requests using Postman.

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
   ```
2. **Build the project:**
   ```bash
   ./mvnw clean package
   ```
3. **Run the application:**
   ```bash
   java -jar target/tiny-bank-0.0.1.jar
   ```
4. **The application should now be running on http://localhost:8080**

## Launching the App in Docker
be sure Docker running on the system

1. **Clone the repository:**
   ```bash
   git clone https://github.com/fialma/tiny-bank.git
   cd tiny-bank
   ```
2. **Build the project:**
   ```bash
   ./mvnw clean package
   ```
3. **Build the docker image:**
   ```bash
   docker build -t tiny-bank . 
   ```
4. **The application should now be running on http://localhost:8080**

## Execute Tests
Inside the main folder tiny-bank:
   ```bash
   ./mvnw test
   ```
   
## API endpoints
### User Management
1. **Create a new User**
   ```http 
   POST /users/create
   ```
2. **Retrieve all the users created**
   ```http 
   GET /users/findAll
   ```
3. **Retrieve the users by the id**
   ```http 
   GET /users/findById/{userId}
   
   Path Parameters:
   userId (Long): ID of the user
   ```
4. **Deactivate a user by the id**
   ```http 
   POST /users/deactivate/{userId}
   
   Path Parameters:
   userId (Long): ID of the user
   ```
5. **Activate a user by the id**
   ```http 
   POST /users/activate/{userId}
   
   Path Parameters:
   userId (Long): ID of the user
   ```
### Account Management
1. **Create a new Account for a UserId**
   ```http 
   POST /accounts/create/{userId}
   
   Path Parameters:
   userId (Long): ID of the user
   ```
2. **Retrieve all the accounts of a UserId**
   ```http 
   GET /accounts/findByUserId/{userId}
   
   Path Parameters:
   userId (Long): ID of the user
   ```
3. **Retrieve the account by the accountId**
   ```http 
   GET /accounts/findById/{accountId}
   
   Path Parameters:
   accountId (Long): ID of the account
   ```
4. **Get the balance of an accountId**
   ```http 
   GET /accounts/balance/{accountId}
   
   Path Parameters:
   accountId (Long): ID of the account
   ```
5. **Deposit a sum on an account**
   ```http 
   POST /accounts/deposit/{accountId}
   
   Path Parameters:
   accountId (Long): ID of the account
   
   Body Parameters: amount (BigDecimal): Amount to deposit
   ```
6. **Withdraw a sum from an account**
   ```http 
   POST /accounts/withdraw/{accountId}
   
   Path Parameters:
   accountId (Long): ID of the account
   
   Body Parameters: amount (BigDecimal): Amount to withdraw
   ```

7. **Transfer a sum from an account to another**
   ```http 
   POST /accounts/transfer/{fromAccountId}/{toAccountId}
   
   Path Parameters:
   fromAccountId (Long): ID of the account source 
   toAccountId (Long): ID of the account destination 
   
   Body Parameters: amount (BigDecimal): Amount to withdraw
   ```
### Transactions
1. **Retrieve all the transaction of an AccountId**
   ```http 
   GET /transactions/findByAccountId/{accountId}
   
   Path Parameters:
   accountId (Long): ID of the account
   ```
