# Credit Card Management API

A Spring Boot (v3.4.5) based RESTful service to manage credit card information, including adding new credit cards and retrieving all stored cards. 

## 🧰 Tech Stack

- **Java 17**
- **Spring Boot 3.4.5**
- **Apache Derby** (Embedded database)
- **Spring Web / Validation / Data JPA**
- **Spring Boot Test**

---

## 🚀 Getting Started

### Prerequisites

- Java 17
- Maven 3.8+

### Clone the Repository

```bash
git clone https://github.com/sustiw/credit-card-api.git
cd credit-card-api


### API Endpoints




| Method | Endpoint           | Description           |
| ------ | ------------------ | --------------------- |
| POST   | `/creditCards/add` | Add a new credit card |
| GET    | `/creditCards/getAll` | Get all credit cards  |


----------------------------------------
----------------------------------------


###API Documentation
Swagger yaml is available at:

Path: src/main/resources/static/swagger.yaml

----------------------------------------
----------------------------------------

###Project Structure

src/
├── main/
│   ├── java/com/ps/app
│   │   ├── controller
│   │   ├── model
│   │   ├── service
│   │   └── CreditCardApplication.java
│   └── resources/
│       └── application.properties
├── test/
│   └── java/com/ps/app/controller
│       └── CreditCardControllerTest.java

----------------------------------------
----------------------------------------
