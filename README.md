![Java CI](https://github.com/Muskan0739/watchlist-app/actions/workflows/ci.yml/badge.svg)

# 🎬 Watchlist App

A backend Watchlist application built using **Spring Boot** and **Java 21**, allows users to search movies and manage a personal watchlist. It is fully containerized with **Docker** and configured with a persistent **H2 database**.

This project demonstrates backend development skills using Spring Boot, external API integration, and server-side rendering.

---

## 🚀 Features

- 🔍 Search movies using the OMDb API  
- ➕ Add movies to a personal watchlist  
- ✏️ Edit movie details  
- 📋 View all saved movies in one place  
- 🖥️ Clean UI using Thymeleaf templates
---

## 🛠 Tech Stack

- **Backend:** Java, Spring Boot  
- **Frontend:** Thymeleaf, HTML, CSS
- **Containerization:** Docker
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
http://localhost:8082
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

## 🔍 H2 Console

URL: http://localhost:8082/h2-console

JDBC URL:
```bash
jdbc:h2:file:/data/watchlist-db

Username: sa

Password: (empty)
```

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

🚀 Live demo coming soon

The application will be deployed shortly and the live URL will be added here.

---
## 🤝 Contributing

Contributions are welcome!

1. Fork the repository
2. Create a new branch
3. Commit your changes
4. Open a Pull Request
---
## 📄 License

This project is licensed under the MIT License.

See the `LICENSE` file for details.

---
## 👩‍💻 Author

Muskan Shukla

GitHub: https://github.com/Muskan0739

LinkedIn: https://www.linkedin.com/in/muskan-shukla-974410263/
