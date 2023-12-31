# Locket Application RESTful APIs

## Introduction
Welcome to the documentation for the Locket Application RESTful APIs! This document provides a comprehensive guide to understanding and interacting with the APIs built using Spring Boot, PostgreSQL, RabbitMQ, OAuth2, S3, and Google Cloud.

### Table of Contents
- [Introduction](#introduction)
- [Technologies Used](#technologies-used)
- [Getting Started](#getting-started)
- [Authentication](#authentication)
- [API Endpoints](#api-endpoints)
- [Message Queue](#message-queue)
- [Storage](#storage)
- [Google Cloud Integration](#google-cloud-integration)
- [Contributing](#contributing)
- [License](#license)

## Technologies Used
- **Spring Boot:** Framework for building Java-based web applications.
- **PostgreSQL:** Relational database for storing application data.
- **RabbitMQ:** Message broker for handling asynchronous communication between components.
- **OAuth2:** Authentication and authorization framework for securing APIs.
- **S3:** Object storage service for managing and storing multimedia content.
- **Google Cloud:** Cloud services integration for additional functionality.

## Getting Started
To run the Locket Application APIs locally, follow these steps:
1. Clone the repository.
2. Configure the database connection in the `application.yml` file located in the src/main/resources directory.
3. Set up OAuth2 and obtain client credentials.
4. Configure refresh token settings.
5. Set up S3 credentials for storage.
6. Integrate Google Cloud services as needed.
7. Build and run the Spring Boot application.

## Authentication
The APIs use OAuth2 for authentication. Obtain an access token using the OAuth2 flow to authenticate requests to protected endpoints.

## API Endpoints
Detailed information about the available endpoints and their usage is provided in the [API Documentation](API_DOCUMENTATION.md).

## Message Queue
RabbitMQ is utilized for asynchronous communication between different components of the application. Understand the message queue flow for effective communication.

## Storage
S3 is used for storing multimedia content. Learn how to interact with the storage service for uploading, retrieving, and managing media files.

## Google Cloud Integration
Explore the integration with Google Cloud services for additional functionality and features.

## Contributing
We welcome contributions! Read the [Contribution Guidelines](CONTRIBUTING.md) for information on how to contribute to the development of the Locket Application.

## License
This project is licensed under the MIT License. Feel free to use and modify the codebase according to your needs.

Thank you for choosing Locket Application's RESTful APIs. If you have any questions or need assistance, please refer to the support documentation or reach out to our community.

`Happy coding!` 🚀
