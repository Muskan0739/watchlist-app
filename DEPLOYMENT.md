# 🚀 Deployment Guide - WatchList App

This guide provides comprehensive instructions to deploy the WatchList App on various platforms.

## Table of Contents
- [Prerequisites](#prerequisites)
- [Local Deployment](#local-deployment)
- [Docker Deployment](#docker-deployment)
- [Cloud Platform Deployment](#cloud-platform-deployment)
  - [Render](#render)
  - [Heroku](#heroku)
  - [Railway](#railway)
  - [AWS Elastic Beanstalk](#aws-elastic-beanstalk)
- [Manual JAR Deployment](#manual-jar-deployment)

---

## Prerequisites

- **Java 17** or higher
- **Maven 3.6+**
- **Git** (for cloning the repository)
- **Docker** (optional, for containerized deployment)

---

## Local Deployment

### 1. Clone the Repository
```bash
git clone https://github.com/Muskan0739/watchlist-app.git
cd watchlist-app
```

### 2. Build the Application
```bash
mvn clean package -DskipTests
```

### 3. Run the Application
```bash
mvn spring-boot:run
```

Or run the JAR directly:
```bash
java -jar target/WatchList-App-0.0.1-SNAPSHOT.jar
```

### 4. Access the Application
Open your browser and navigate to:
```
http://localhost:8082
```

---

## Docker Deployment

### Using Docker

1. **Build the Docker Image**
```bash
docker build -t watchlist-app .
```

2. **Run the Container**
```bash
docker run -p 8082:8082 watchlist-app
```

3. **Access the Application**
```
http://localhost:8082
```

### Using Docker Compose

1. **Start the Application**
```bash
docker-compose up -d
```

2. **View Logs**
```bash
docker-compose logs -f
```

3. **Stop the Application**
```bash
docker-compose down
```

---

## Cloud Platform Deployment

### Render

[Render](https://render.com) offers free hosting for web services.

#### Method 1: Using the Web Dashboard

1. **Fork/Push this repository to GitHub**

2. **Sign up/Login to Render** at https://render.com

3. **Create a New Web Service**
   - Click "New +" → "Web Service"
   - Connect your GitHub repository
   - Configure the service:
     - **Name**: `watchlist-app`
     - **Environment**: `Docker`
     - **Branch**: `main` (or your default branch)
     - **Plan**: Free

4. **Deploy**
   - Click "Create Web Service"
   - Wait for the build to complete
   - Access your app at the provided URL

#### Method 2: Using render.yaml (Infrastructure as Code)

The repository includes a `render.yaml` file for automated deployment.

1. **Connect Repository to Render**
   - Go to Render Dashboard
   - Click "New +" → "Blueprint"
   - Connect your GitHub repository
   - Render will automatically detect `render.yaml`

2. **Deploy**
   - Render will create all services defined in the YAML
   - Monitor the deployment progress

**Note**: Render's free tier may spin down services after 15 minutes of inactivity.

---

### Heroku

[Heroku](https://heroku.com) provides easy deployment for Java applications.

#### Prerequisites
- Install [Heroku CLI](https://devcenter.heroku.com/articles/heroku-cli)

#### Deployment Steps

1. **Login to Heroku**
```bash
heroku login
```

2. **Create a New Heroku App**
```bash
heroku create your-watchlist-app
```

3. **Set Java Version**
```bash
heroku config:set JAVA_VERSION=17
```

4. **Deploy to Heroku**
```bash
git push heroku main
```

5. **Open Your App**
```bash
heroku open
```

**Note**: The repository includes `Procfile` and `system.properties` for Heroku configuration.

---

### Railway

[Railway](https://railway.app) offers simple deployment with GitHub integration.

1. **Sign up/Login to Railway** at https://railway.app

2. **Create New Project**
   - Click "New Project"
   - Select "Deploy from GitHub repo"
   - Choose your repository

3. **Configure**
   - Railway auto-detects Java applications
   - Set environment variable if needed:
     - `PORT`: Railway automatically assigns this

4. **Deploy**
   - Railway automatically builds and deploys
   - Access via the generated domain

---

### AWS Elastic Beanstalk

#### Prerequisites
- AWS Account
- AWS CLI installed and configured

#### Deployment Steps

1. **Package the Application**
```bash
mvn clean package -DskipTests
```

2. **Initialize Elastic Beanstalk**
```bash
eb init -p "Corretto 17 running on 64bit Amazon Linux 2" watchlist-app
```

3. **Create Environment and Deploy**
```bash
eb create watchlist-env
```

4. **Open Your Application**
```bash
eb open
```

5. **Update Deployment**
```bash
mvn clean package -DskipTests
eb deploy
```

---

## Manual JAR Deployment

If you want to deploy the JAR file on your own server:

### 1. Build the JAR
```bash
mvn clean package -DskipTests
```

### 2. Transfer JAR to Server
```bash
scp target/WatchList-App-0.0.1-SNAPSHOT.jar user@your-server:/path/to/deploy
```

### 3. Run on Server
```bash
ssh user@your-server
cd /path/to/deploy
nohup java -jar WatchList-App-0.0.1-SNAPSHOT.jar > app.log 2>&1 &
```

### 4. Set up as System Service (Linux)

Create a systemd service file `/etc/systemd/system/watchlist-app.service`:

```ini
[Unit]
Description=WatchList Spring Boot Application
After=syslog.target network.target

[Service]
User=appuser
ExecStart=/usr/bin/java -jar /path/to/deploy/WatchList-App-0.0.1-SNAPSHOT.jar
SuccessExitStatus=143
StandardOutput=journal
StandardError=journal
SyslogIdentifier=watchlist-app

[Install]
WantedBy=multi-user.target
```

Enable and start the service:
```bash
sudo systemctl daemon-reload
sudo systemctl enable watchlist-app
sudo systemctl start watchlist-app
sudo systemctl status watchlist-app
```

---

## Environment Variables

For production deployments, you can configure these environment variables:

- `SERVER_PORT`: Application port (default: 8082)
- `SPRING_PROFILES_ACTIVE`: Set to `prod` for production configuration
- `OMDB_API_KEY`: Your OMDb API key (if using external API)

Example:
```bash
export SERVER_PORT=8080
export SPRING_PROFILES_ACTIVE=prod
java -jar WatchList-App-0.0.1-SNAPSHOT.jar
```

---

## Production Considerations

### Database
- The application uses H2 in-memory database by default
- For production, consider using a persistent database:
  - PostgreSQL
  - MySQL
  - MongoDB

### Security
- Change default database credentials
- Enable HTTPS/SSL
- Configure CORS properly
- Implement rate limiting

### Monitoring
- Set up application monitoring (New Relic, Datadog, etc.)
- Configure logging aggregation
- Set up health check endpoints

### Scaling
- Use load balancers for horizontal scaling
- Configure session management for distributed systems
- Consider using Redis for session storage

---

## Troubleshooting

### Application won't start
- Check Java version: `java -version` (must be 17+)
- Verify port 8082 is not in use: `lsof -i :8082`
- Check logs for errors

### Build fails
- Clean Maven cache: `mvn clean`
- Update dependencies: `mvn dependency:resolve`
- Check internet connection for dependency downloads

### Docker issues
- Ensure Docker is running: `docker ps`
- Check Docker logs: `docker logs <container-id>`
- Rebuild image: `docker build --no-cache -t watchlist-app .`

---

## Support

For issues and questions:
- Open an issue on GitHub
- Check existing documentation
- Review application logs

---

## License

This project is licensed under the MIT License.
