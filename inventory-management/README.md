# Inventory Management System

A RESTful Inventory Management System developed using **Java, Spring Boot, Spring Data JPA, Hibernate, Maven, and MySQL**.

The application provides REST APIs for managing inventory items, including creating, retrieving, updating, and deleting items. It also supports inventory quantity management, input validation, and centralized exception handling.

## 1. Features

- Create new inventory items.
- Retrieve all inventory items.
- Retrieve an inventory item by ID.
- Update existing inventory item details.
- Update an item's stock quantity.
- Delete inventory items.
- Validate item names, quantities, and prices.
- Return meaningful HTTP error responses.
- Persist inventory data in MySQL using Spring Data JPA.

## 2. Technology Stack

| Java 21 |
| Spring Boot |
| Spring Web |
| Spring Data JPA |
| Hibernate |
| MySQL 8.2 |
| Maven | 
| Eclipse IDE | 
| Postman |
| Git |

## 3. Project Architecture

The application follows a layered architecture:

```text
HTTP Request
       |
       v
ItemController
       |
       v
ItemService
       |
       v
ItemRepository
       |
       v
MySQL Database
```

**Controller Layer:** Handles incoming HTTP requests and returns appropriate responses.

**Service Layer:** Implements inventory management business logic and input validation.

**Repository Layer:** Uses Spring Data JPA to perform database operations.

**Model Layer:** Defines the Item entity and its database mapping.

**Exception Layer:** Handles application exceptions and returns consistent error responses.

## 4. Project Structure

```text
inventory-management/
├── src/
│   ├── main/
│   │   ├── java/com/example/inventorymanagement/
│   │   │   ├── InventoryManagementApplication.java
│   │   │   ├── model/
│   │   │   │   └── Item.java
│   │   │   ├── repository/
│   │   │   │   └── ItemRepository.java
│   │   │   ├── service/
│   │   │   │   └── ItemService.java
│   │   │   ├── controller/
│   │   │   │   └── ItemController.java
│   │   │   └── exception/
│   │   │       ├── ItemNotFoundException.java
│   │   │       └── GlobalExceptionHandler.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── pom.xml
├── .gitignore
└── README.md
```

## 5. Prerequisites

Ensure the following software is installed:

- Java JDK 21
- Eclipse IDE with Maven support
- MySQL Server
- MySQL Workbench
- Git
- Postman (For testing)

## 6. Installation and Setup

### Step 1: Clone the Repository

```bash
git clone <repository-url>
cd inventory-management
```

Alternatively, download and extract the project ZIP file.

### Step 2: Create the MySQL Database

Open MySQL Workbench, connect to your local MySQL server, and execute:

```sql
CREATE DATABASE IF NOT EXISTS inventory_db;
```

### Step 3: Configure Database Connection

Open:

`src/main/resources/application.properties`

Configure the application:

```properties
spring.application.name=inventory-management

spring.datasource.url=jdbc:mysql://localhost:3306/inventory_db
spring.datasource.username=root
spring.datasource.password=${DB_PASSWORD}

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

server.port=8080
```

Set the `DB_PASSWORD` environment variable to your local MySQL password before starting the application.

For Eclipse:

1. Open Run → Run Configurations.
2. Select the Java Application launch configuration.
3. Open the Environment tab.
4. Add a variable named `DB_PASSWORD`.
5. Enter your MySQL password as its value.
6. Click Apply.

### Step 4: Import the Project into Eclipse

1. Open Eclipse.
2. Select File → Import.
3. Choose Maven → Existing Maven Projects.
4. Browse to the project directory containing `pom.xml`.
5. Select the project and click Finish.
6. Wait for Maven dependencies to download.

### Step 5: Run the Application

Open `InventoryManagementApplication.java`.

Right-click the file and select Run As → Java Application, using the launch configuration containing the `DB_PASSWORD` environment variable.

Alternatively, with the environment variable configured, run:

```bash
mvn spring-boot:run
```

The application starts on:

`http://localhost:8080`

Hibernate automatically creates or updates the `items` table in the configured MySQL database during development.

The corresponding MySQL table is named `items`.