# 🚆 Train Booking System

A backend-focused full-stack railway reservation system inspired by real-world train booking platforms like IRCTC.

This project is designed to learn and implement:

* Spring Boot
* REST APIs
* JWT Authentication
* Spring Security
* PostgreSQL
* JPA/Hibernate
* Pagination
* Concurrency Handling
* Pessimistic Locking
* Segment-Based Seat Allocation
* Clean Architecture
* Production-Level Backend Design

---

# ✨ Features

## 🔐 Authentication & Authorization

* User Signup
* User Login
* JWT Token Generation
* JWT Authentication Filter
* Protected APIs
* Stateless Authentication
* Spring Security Integration

---

## 🚆 Train Management

* Add Train
* Add Coaches
* Add Stations
* Dynamic Seat Creation
* Route Scheduling
* Validation Support

---

## 🔍 Train Search

Users can search trains based on:

* Source Station
* Destination Station
* Date Of Travel

Search API supports:

* Pagination
* Available Seat Count
* Segment-Based Availability

---

## 🎟️ Ticket Booking

* Book Single or Multiple Seats
* Segment-Based Seat Allocation
* Route Validation
* Dynamic Seat Availability
* Prevent Overlapping Seat Booking
* Concurrency Safe Booking

---

## ⚡ Advanced Backend Concepts Implemented

* JWT Authentication
* Pessimistic Locking
* JPQL Queries
* Pagination
* DTO Pattern
* Global Exception Handling
* Validation Handling
* Transaction Management
* Segment Overlap Detection
* Realistic Railway Seat Reuse Logic

---

# 🧠 Segment-Based Seat Allocation

This project implements realistic railway reservation logic.

Example:

```text
Mumbai → Kota → Delhi
```

If:

* Seat is booked from Mumbai → Kota

Then:

* Same seat can still be booked from Kota → Delhi

because journey segments do not overlap.

This is implemented using:

```text
existing.sourceOrder < new.destinationOrder
AND
existing.destinationOrder > new.sourceOrder
```

This allows:

* Efficient seat reuse
* Real-world reservation behavior
* Better seat utilization

---

# 🏗️ Tech Stack

## Backend

* Java
* Spring Boot
* Spring Security
* Spring Data JPA
* Hibernate
* JWT

## Database

* PostgreSQL

## Build Tool

* Maven

---

# 📂 Project Structure

```text
src/main/java/com/project
│
├── config
├── controller
├── dto
├── entities
├── exception
├── jwt
├── repository
├── services
└── util
```

---

# 🔐 Authentication Flow

1. User Signup
2. User Login
3. JWT Token Generated
4. Client Sends Token In Header
5. JWT Filter Validates Token
6. SecurityContextHolder Stores Authentication
7. Protected APIs Become Accessible

---

# 🗄️ Database Design

## Main Entities

* User
* Train
* Seat
* StationSchedule
* Ticket
* SeatBooking

---

# 🚀 API Endpoints

## Authentication APIs

### Signup

```http
POST /auth/signup
```

### Login

```http
POST /auth/login
```

---

## Train APIs

### Add Train

```http
POST /train/add
```

### Search Train

```http
GET /train/search
```

---

## Ticket APIs

### Book Ticket

```http
POST /ticket/book
```

---

# 📌 Sample Booking Request

```json
{
  "trainNo": "12471",
  "source": "Mumbai Central",
  "destination": "New Delhi",
  "dateOfTravel": "2026-05-20T00:00:00",
  "numberOfSeats": 2
}
```

---

# ⚙️ Running The Project

## Clone Repository

```bash
git clone <your-repo-url>
```

---

## Configure Database

Update:

```properties
application.properties
```

Example:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/train_booking
spring.datasource.username=postgres
spring.datasource.password=password
```

---

## Run Application

```bash
mvn spring-boot:run
```

---

# 🔒 Security

Protected APIs require:

```http
Authorization: Bearer <jwt-token>
```

---

# 📈 Future Improvements

* Ticket Cancellation
* Waiting List
* RAC System
* Payment Integration
* Admin Dashboard
* Microservices Architecture
* Redis Caching
* Kafka Integration
* Email Notifications
* Docker Deployment
* CI/CD Pipeline

---

# 📚 Concepts Learned

* Spring Security
* JWT Authentication
* Stateless APIs
* REST API Design
* JPA Relationships
* JPQL
* Pagination
* Transactions
* Pessimistic Locking
* Concurrency Handling
* Interval Conflict Detection
* Backend System Design

---

# 🤝 Contributing

Contributions are welcome.

Feel free to fork the repository and create pull requests.

---

# ⭐ Acknowledgement

This project was built for learning backend engineering concepts and implementing production-level railway reservation system logic using Spring Boot.
