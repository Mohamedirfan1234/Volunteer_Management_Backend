# Volunteer Management Backend

A Spring Boot application for volunteer management with JWT authentication and MySQL database.

## Deployment on Render

This application is configured to deploy on Render with Railway MySQL as the database.

### Prerequisites

- Java 17
- Maven
- Railway MySQL database (already configured)

### Database Configuration

The application is configured to use Railway MySQL with the following connection details:
- Host: crossover.proxy.rlwy.net:33095
- Database: railway
- Username: root
- Password: CKgGvEbWhZBZTXTAgrYrPRbWtNWWPjoH

### Deployment Steps

1. **Connect your GitHub repository to Render:**
   - Go to [Render Dashboard](https://dashboard.render.com)
   - Click "New +" and select "Web Service"
   - Connect your GitHub repository

2. **Configure the service:**
   - **Name:** volunteer-backend
   - **Environment:** Java
   - **Build Command:** `./mvnw clean package -DskipTests`
   - **Start Command:** `java -jar target/volunteer_backend-0.0.1-SNAPSHOT.jar`
   - **Port:** 8080

3. **Environment Variables (optional - already in application.properties):**
   - `JAVA_VERSION`: 17
   - `PORT`: 8080

4. **Deploy:**
   - Click "Create Web Service"
   - Render will automatically build and deploy your application

### Local Development

To run the application locally:

```bash
./mvnw spring-boot:run
```

The application will be available at `http://localhost:8080`

### API Endpoints

The application includes:
- Authentication endpoints (login, register)
- Volunteer management endpoints
- Event management endpoints
- JWT-based security

### Database Schema

The application uses Hibernate with `ddl-auto=update`, so the database schema will be automatically created/updated based on your entity classes.

### Security

- JWT-based authentication
- Spring Security configuration
- Custom user details service
- JWT filter for request authentication 