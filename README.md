# Footvolley Event Planner

A full-stack web application for organizing, managing, and participating in footvolley events. This platform enables
event organizers to create tournaments, manage teams and match schedules, and provide participants with an easy
interface to engage with events.

## Features

- User registration and login
- Event and team management
- Match scheduling
- Admin panel for managing content
- Responsive UI

## Tech Stack

**Frontend:**

- HTML
- CSS
- JavaScript

**Backend:**

- Java
- Spring Boot
- SQL (Hosted on Azure Cloud)

## Deployment

You can access the project via our live URL (coming soon), or run it locally:

## Installation & Running Locally

### Backend (Spring Boot + SQL)

1. Clone the repository
2. Navigate to the backend folder
3. Set up your `.env` file (see `.env.example` below)
4. Run the project using your IDE or `mvn spring-boot:run`

### Frontend

1. Navigate to the frontend folder
2. Open `index.html` in your browser
    - or host it using a local server like `live-server`

## Environment Variables

Create a `.env` file in your backend directory with the following structure:

```bash
# .env.example
DB_URL=jdbc:sqlserver://<your_server>.database.windows.net:1433;database=<your_db>
DB_USERNAME=<your_username>
DB_PASSWORD=<your_password>
SPRING_PORT=8080
