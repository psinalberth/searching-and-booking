# Searching and Booking System

This repository contains two microservices, **Catalog Service** and **Booking Service**, which together form a system for managing events, bookings, and related operations. The system is built using Spring Boot, Kafka, MongoDB, and Elasticsearch.

## Table of Contents

- [Overview](#overview)
- [Services](#services)
    - [Catalog Service](#catalog-service)
    - [Booking Service](#booking-service)
- [Features](#features)
- [Architecture](#architecture)
- [Getting Started](#getting-started)
- [Technologies Used](#technologies-used)
- [Contributing](#contributing)
- [License](#license)

---

## Overview

The **Searching and Booking System** is designed to manage event catalogs and handle booking operations. It supports event creation, searching, booking requests, and cancellations. The system uses Kafka for event-driven communication between services and MongoDB/Elasticsearch for data storage and search capabilities.

---

## Services

### Catalog Service

The **Catalog Service** is responsible for managing and searching events. It provides the following functionalities:

- Event creation and indexing.
- Searching for events by criteria (e.g., full-text search, available spots).
- Managing event bookings (confirmation and cancellation).

### Booking Service

The **Booking Service** handles booking operations for events. It provides the following functionalities:

- Creating booking requests.
- Processing booking results and cancellations.
- Managing an outbox pattern for reliable event publishing.
- WebSocket support for real-time updates.

---

## Features

### Catalog Service Features

- **Event Management**: Create, update, and manage events.
- **Search Events**: Full-text search and filtering using Elasticsearch.
- **Kafka Integration**: Publish and consume booking-related events.
- **Health Monitoring**: Exposes actuator endpoints for health checks.

### Booking Service Features

- **Booking Management**: Create and cancel bookings.
- **Outbox Pattern**: Ensures reliable event publishing for booking requests.
- **Kafka Integration**: Handles booking-related events via Kafka.
- **Swagger API Documentation**: Provides interactive API documentation.
- **WebSocket Integration**: Enables real-time notifications for booking updates.

---

## Architecture

The system follows a microservices architecture with the following components:

- **Catalog Service**: Manages event data and integrates with Elasticsearch for search capabilities.
- **Booking Service**: Handles booking operations and integrates with Kafka for event-driven communication.
- **Kafka**: Used for asynchronous communication between services.
- **MongoDB**: Stores event and booking data.
- **Elasticsearch**: Provides advanced search capabilities for events.
- **WebSocket**: Supports real-time communication for booking updates.

---

## Getting Started

### Prerequisites

- Java 17
- Docker and Docker Compose
- Gradle

### Running the Services

1. Clone the repository:
   ```bash
   git clone https://github.com/your-repo/searching-and-booking.git
   cd searching-and-booking