# Searching and Booking System

This repository contains four microservices: **Catalog Service**, **Booking Service**, **Notification Service**, and **API Gateway**, which together form a system for managing events, bookings, and related operations. The system is built using Spring Boot, Kafka, MongoDB, Elasticsearch, and Eureka for service discovery.

## Table of Contents

- [Overview](#overview)
- [Services](#services)
    - [Catalog Service](#catalog-service)
    - [Booking Service](#booking-service)
    - [Notification Service](#notification-service)
    - [API Gateway](#api-gateway)
- [Features](#features)
- [Architecture](#architecture)
- [Getting Started](#getting-started)
- [Technologies Used](#technologies-used)
- [Contributing](#contributing)

---

## Overview

The **Searching and Booking System** is designed to manage event catalogs and handle booking operations. It supports event creation, searching, booking requests, and cancellations. The system uses Kafka for event-driven communication, MongoDB/Elasticsearch for data storage and search, and Eureka for service discovery. The API Gateway provides a single entry point for all services, with dynamic routing and path rewriting.

---

## Services

### Catalog Service

- Manages and searches events.
- Registers with Eureka for service discovery.
- Kafka enabled for event-driven communication.
- Event creation, indexing, and booking management.

### Booking Service

- Handles booking operations for events.
- Registers with Eureka.
- Kafka integration for booking events.
- Waitlist management for fully booked events.
- Outbox pattern for reliable event publishing.
- WebSocket support for real-time updates.
- Scheduler integration for booking processing.

### Notification Service

- Sends notifications via email (SendGrid) and SMS (Twilio).
- Registers with Eureka.
- Kafka integration for notification events.
- MongoDB for notification history.
- Extensible notification providers.
- Template management for notifications.

### API Gateway

- Built with Spring Cloud Gateway.
- Registers with Eureka for dynamic service discovery.
- Routes requests to microservices using path-based routing.
- Path rewriting for internal endpoints.
- Centralized entry point for all services.

#### API Gateway Routing

Configured in `api-gateway/src/main/resources/application.yml`:

- `/booking/**` → `BOOKING-SERVICE`
- `/notification/**` → `NOTIFICATION-SERVICE`
- `/catalog/**` → `CATALOG-SERVICE`
- Path rewriting removes the service prefix for internal routing.

---

## Features

- **Spring Boot 3.4.5** and **Spring Cloud 2024.0.1**.
- Modular microservices architecture.
- Eureka for service registration and discovery.
- Spring Cloud Gateway as API Gateway.
- Kafka for asynchronous, event-driven communication.
- MongoDB and Elasticsearch for data storage and search.
- Gradle Kotlin DSL for build automation.
- Java 17 toolchain enforced in all modules.
- Centralized dependency management with Spring Cloud BOM.
- Docker support for running dependencies.

---

## Architecture

- **Catalog Service**: Manages event data, integrates with Elasticsearch.
- **Booking Service**: Handles bookings, integrates with Kafka.
- **Notification Service**: Sends notifications, persists history in MongoDB.
- **API Gateway**: Routes and rewrites requests, integrates with Eureka.
- **Kafka**: Asynchronous communication.
- **MongoDB**: Data storage.
- **Elasticsearch**: Advanced search.
- **WebSocket**: Real-time updates.

---

## Getting Started

### Prerequisites

- Java 17+
- Docker and Docker Compose
- Gradle 8+

Set up environment variables in a `.env` file:
```
TWILIO_ACCOUNT_SID=your_twilio_account_sid
TWILIO_AUTH_TOKEN=your_twilio_auth_token
TWILIO_PHONE_NUMBER=your_twilio_phone_number
APP_MAIL_ACCOUNT=your_email_account
SENDGRID_API_KEY=your_sendgrid_api_key
APP_USER_INFO_EMAIL=your_user_email
APP_USER_INFO_PHONE=your_user_phone
```

### Running the Services

1. Clone the repository:
   ```bash
   git clone https://github.com/your-repo/searching-and-booking.git
   cd searching-and-booking
    ```

2. Build all services:
    ```bash
    ./gradlew build
    ```

3. Start dependencies (Kafka, MongoDB, Elasticsearch, Eureka) using Docker Compose.

4. Run each service:
    ```bash
    ./gradlew :catalog-service:bootRun
    ./gradlew :booking-service:bootRun
    ./gradlew :notification-service:bootRun
    ./gradlew :api-gateway:bootRun
    ```

<hr/>

### Technologies Used
- Java 17
- Spring Boot 3.4.5
- Spring Cloud 2024.0.1
- Spring Cloud Gateway
- Eureka Discovery Client
- Kafka
- MongoDB
- Elasticsearch
- Gradle Kotlin DSL
- Docker

<hr/>

### Contributing
Contributions are welcome! Please open issues or submit pull requests for improvements.