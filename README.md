# DIContainer Project

A simple Dependency Injection (DI) container implemented in Kotlin, demonstrating the principles of dependency injection with a focus on automatic registration and resolution of services. This project also includes example services and tests to validate the functionality of the DI container.

## Table of Contents

- [Features](#features)
- [Getting Started](#getting-started)
- [Usage](#usage)

## Features

- Register services with automatic resolution of dependencies.
- Supports singleton and transient scopes.
- Simple API for service registration and resolution.
- Example services and controllers demonstrating usage.

## Getting Started

### Prerequisites

Make sure you have the following installed:

- [JDK 11+](https://openjdk.java.net/install/)
- [Gradle](https://gradle.org/install/)
- [Git](https://git-scm.com/)

### Clone the Repository

```bash
git clone https://github.com/yourusername/your-repo-name.git
cd your-repo-name
```

## Build the Project

You can build the project using Gradle:
```bash
./gradlew build
```

## Usage
```bash
./gradlew run
```

### Service Registration
In the Application.module() function, services are registered automatically. For example:

```kotlin
fun Application.module() {
    val diContainer = DIContainer()

    // Register services
    diContainer.register(GreetingService::class, GreetingService::class)
    diContainer.register(InvoiceController::class, InvoiceController::class)

    configureRouting(diContainer)
}
```
