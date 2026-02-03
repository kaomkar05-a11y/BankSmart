# BankSmart Branch Guide

BankSmart is a production-ready virtual bank branch learning platform for first-time users in India. It teaches real counter workflows, documents, and form requirements so customers can visit branches confidently.

## Tech Stack

- Frontend: React + Tailwind CSS + GSAP
- Backend: Spring Boot + JWT Authentication + H2 Database (swap for PostgreSQL in production)

## Project Structure

```
backend/   Spring Boot REST API
frontend/  React client
```

## Running the backend

```bash
cd backend
mvn spring-boot:run
```

The API will start on `http://localhost:8080`.

### Optional environment variables

| Variable | Purpose | Default |
| --- | --- | --- |
| `JWT_SECRET` | JWT signing secret | `banksmart-dev-secret-change-in-production` |
| `JWT_EXPIRATION_MINUTES` | Token lifetime in minutes | `240` |

To override defaults, update `application.yml` or use Spring environment overrides:

```bash
JWT_SECRET=your-secret JWT_EXPIRATION_MINUTES=240 mvn spring-boot:run
```

## Running the frontend

```bash
cd frontend
npm install
npm run dev
```

The frontend runs on `http://localhost:5173`.

### Frontend environment variables

Create a `frontend/.env` file if the backend runs elsewhere:

```
VITE_API_BASE=http://localhost:8080
```

## Usage flow

1. Open the landing page and click **Enter Bank Branch**.
2. Sign in or create an account to track learning progress.
3. Explore each counter module and mark it completed.
4. Review your learning progress dashboard.

## Production notes

- Swap H2 with PostgreSQL in `application.yml`.
- Use a strong JWT secret.
- Configure CORS for your deployment domain.
