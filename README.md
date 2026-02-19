![Java CI](https://github.com/Muskan0739/watchlist-app/actions/workflows/ci.yml/badge.svg)

# 🎬 Watchlist App

A secure full-stack Watchlist application built using Spring Boot and Java 21.  
Users can search movies and manage a personal watchlist.  
The application is containerized using Docker and deployed on AWS Elastic Beanstalk.


This project demonstrates backend development skills using Spring Boot, Spring Security, external API integration, Containerization(Docker) and server-side rendering.

---

## 🚀 Features

- 🔍 Search movies using the OMDb API  
- ➕ Add movies to a personal watchlist  
- ✏️ Edit movie details  
- 📋 View all saved movies in one place  
- 🖥️ Clean UI using Thymeleaf templates
- 🔐 User authentication using Spring Security with BCrypt password encryption
---

## 🛠 Tech Stack

- **Backend:** Java, Spring Boot  
- **Frontend:** Thymeleaf, HTML, CSS  
- **Security:** Spring Security, BCrypt  
- **Containerization:** Docker  
- **Cloud:** AWS Elastic Beanstalk  
- **API:** OMDb API  
- **Build Tool:** Maven  

---

## 📸 Screenshots

### Home Page
![Home Page](screenshots/home.png)

### SignUp Page
![SignUp Form](screenshots/Signup_Form.png)

### Submit Movie
![Add Movie Page](screenshots/add_movie.png)

### Watchlist Page
![Watchlist Page](screenshots/watchlist.png)

---

## ⚙️ Installation & Setup

Follow these steps to run the project locally.

### Prerequisites

- Java 21 or higher  
- Maven  
- Git  

---

### Steps

 1. Clone the repository
```bash
git clone https://github.com/Muskan0739/watchlist-app.git
```

2. Navigate to the project directory
```bash
cd watchlist-app
```
3. Build the Docker image
```bash
docker build -t watchlist-app .
```

4. Run the container (with persistent database)
```bash
docker run -d \
  -p 8082:8080 \
  -v watchlist-data:/data \
  --name watchlist-container \
  watchlist-app

```
5. Once started, open your browser and go to:
```bash
http://localhost:5000
```
---
## 🗄️ Database Configuration (H2 – File Mode)
The application uses H2 in file mode.
```bash
spring.datasource.url=jdbc:h2:file:/data/watchlist-db
```
✅ Benefits

Data is stored on disk

Data persists across container restarts

Docker volume (watchlist-data) keeps DB safe

---

## 🔑 OMDb API Configuration

This project uses the OMDb API to fetch movie details.

Steps to configure:

   1. Get a free API key from: https://www.omdbapi.com/

   2. Add the API key to your application.properties file:
```bash
omdb.api.key=YOUR_API_KEY
```

---

## 📂 Project Structure
```bash
watchlist-app
│── src/main/java
│── src/main/resources
│   ├── templates
│   ├── static
│── Dockerfile
│── pom.xml
│── README.md
```
---
## 🚧 Live Demo

http://Watchlist-app-env.eba-hfapqfpv.eu-north-1.elasticbeanstalk.com

---

## 📌 Deployment

The application is deployed using Docker on AWS Elastic Beanstalk.

---

## 📄 License

This project is licensed under the MIT License.

See the `LICENSE` file for details.

---
## 👩‍💻 Author

Muskan Shukla

GitHub: https://github.com/Muskan0739

LinkedIn: https://www.linkedin.com/in/muskan-shukla-974410263/
