# Project Analysis

## Overview
This repository is a Spring Boot portfolio site with a static landing page and an optional contact form that sends email via SMTP. It uses Thymeleaf for templating and runs on Java 17.

## Tech Stack
- Java 17, Spring Boot 3.4.x
- Spring Web, Thymeleaf, Spring Mail, Spring Data JPA
- H2 in-memory database (dev defaults)
- Gradle build, Docker runtime

## Key Features
- Single-page portfolio experience rendered from `index.html` (Thymeleaf template).
- Optional contact form endpoint that sends an email via SMTP.
- PDF export trigger using browser print (`window.print()`).

## Application Flow
- `GET /` renders `index.html` via `HomeController`.
- `GET /contact` renders `contact.html`.
- `POST /contact/send` accepts form fields and triggers SMTP email send, then redirects to `/`.

## Project Structure
- `src/main/java/com/hans/portfolio/PortfolioApplication.java`: Spring Boot entry point.
- `src/main/java/com/hans/portfolio/controller/HomeController.java`: Home route.
- `src/main/java/com/hans/portfolio/controller/ContactController.java`: Contact routes.
- `src/main/java/com/hans/portfolio/service/MailService.java`: SMTP email sending.
- `src/main/java/com/hans/portfolio/dto/ContactDto.java`: Contact payload.
- `src/main/resources/templates/index.html`: Main portfolio page.
- `src/main/resources/templates/contact.html`: Contact form page.
- `src/main/resources/application.yml`: Local config, including SMTP settings.
- `Dockerfile`: Two-stage Gradle build and runtime image.

## Configuration Notes
- SMTP credentials are loaded via environment variables:
  - `MAIL_USERNAME`
  - `MAIL_PASSWORD`
- `spring.mail.username` is used as the destination address for contact emails.
- H2 is configured as an in-memory datasource for local use.

## Build and Run
- Build: `./gradlew build`
- Run: `./gradlew bootRun`
- Docker build: `docker build -t my-portfolio .`
- Docker run: `docker run -p 8080:8080 my-portfolio`

## UI Notes
- The contact section in `index.html` is commented out; the contact form still exists at `/contact`.
- The PDF download button uses `window.print()` and temporarily modifies `document.title`.

## Risks and Gaps
- No persistence layer is actively used despite JPA and H2 configuration.
- No validation or spam protection on the contact form.
- No tests in the repository beyond default setup.

