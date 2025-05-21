# Contributing to Footvolley Event Planner

We’re excited that you’re interested in contributing to this project!

## How to Contribute

1. **Fork the repository**
2. **Create a new branch**: `git checkout -b feature/your-feature-name`
3. Make your changes
4. **Commit with a clear message** (see naming convention below)
5. **Push to your fork**
6. Submit a **Pull Request**

## Issues & Suggestions

We currently manage issues internally. If you'd like to propose a change, fork the repo and open a pull request with
your implementation. Include a clear description of what you're addressing or improving.

## Development Setup

### Backend (Spring Boot + SQL)

- Java 17+
- Spring Boot
- Configure `.env` using `.env.example`
- Run the application with `mvn spring-boot:run`

### Frontend

- HTML, CSS, JavaScript
- Open `index.html` in a browser or run with a local dev server

## Pull Request Naming Convention

Use the following format:

- `feat: <description>` – For new features
- `fix: <description>` – For bug fixes
- `docs: <description>` – For documentation changes
- `refactor: <description>` – For code cleanup

**Examples:**

- `feat: implement team signup page`
- `fix: null check in match controller`
- `docs: update README and contributing guide`

## Code Style

We have not enforced strict formatting tools yet. Please:

- Use consistent indentation
- Use descriptive variable and method names
- Add comments for non-trivial logic

> Future improvements may include Prettier (frontend) or Google Java Format (backend).

## Thank You!

You're helping make the Footvolley Event Planner better for everyone. 🙌
