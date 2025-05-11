# Searching and Booking System

This repository contains three microservices: **Catalog Service**, **Booking Service**, and **Notification Service**, which together form a system for managing events, bookings, and related operations. The system is built using Spring Boot, Kafka, MongoDB, and Elasticsearch.

## Table of Contents

- [Overview](#overview)
- [Services](#services)
    - [Catalog Service](#catalog-service)
    - [Booking Service](#booking-service)
    - [Notification Service](#notification-service)
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

### Notification Service

The **Notification Service** is responsible for sending notifications via multiple channels, including email and SMS. It provides the following functionalities:

- **Email Notifications**: Uses SendGrid for sending emails.
- **SMS Notifications**: Uses Twilio for sending SMS messages.
- **Extensible Notification Providers**: Built with a `NotificationProvider` interface to support additional notification channels.
- **MongoDB Integration**: Stores notification history for tracking purposes.
- **Kafka Integration**: Publishes notification events to Kafka topics.

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

### Notification Service Features

- **Email Notifications**: Configurable sender email address via `app.config.mail.account` property.
- **SMS Notifications**: Configurable Twilio account credentials and phone number.
- **MongoDB Integration**: Stores notification history for tracking purposes.
- **Extensibility**: Easily add new notification channels by implementing the `NotificationProvider` interface.
- **Kafka Integration**: Publishes notifications to Kafka for further processing.

---

## Architecture

The system follows a microservices architecture with the following components:

- **Catalog Service**: Manages event data and integrates with Elasticsearch for search capabilities.
- **Booking Service**: Handles booking operations and integrates with Kafka for event-driven communication.
- **Notification Service**: Sends notifications via email and SMS, with MongoDB for persistence.
- **Kafka**: Used for asynchronous communication between services.
- **MongoDB**: Stores event, booking, and notification data.
- **Elasticsearch**: Provides advanced search capabilities for events.
- **WebSocket**: Supports real-time communication for booking updates.

---

## Getting Started

### Prerequisites

- Java 17
- Docker and Docker Compose
- Gradle

Set up the environment variables in a `.env` file:  
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