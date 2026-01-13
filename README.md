# 🎬 WatchList_App

A web application to track movies, view ratings from OMDb API, and manage a personalized watchlist.

## 🚀 Features
- 🎥 **Add & Manage Watchlist**: Add movies with title, rating, priority, and comments.
- 🌟 **OMDb API Integration**: Fetches IMDb ratings automatically if available; otherwise, stores user-provided ratings.
- ✏️ **Edit & Update**: Modify movie details anytime after submission.
- ✅ **Form Validation**: Ensures accurate and valid data input.

## 🛠️ Tech Stack
- **Backend**: Spring Boot, Java
- **Frontend**: HTML, CSS, JavaScript
- **Template Engine**: Thymeleaf
- **Database**: H2 (In-Memory Database)
- **API Integration**: OMDb API

## 🔧 Setup & Installation

### Prerequisites
- Java 17 or higher
- Maven 3.6+

### Local Development

1. **Clone the Repository**:
   ```sh
   git clone https://github.com/Muskan0739/watchlist-app.git
   cd watchlist-app
   ```

2. **Build the Application**:
   ```sh
   mvn clean package -DskipTests
   ```

3. **Run the Application**:
   ```sh
   mvn spring-boot:run
   ```

4. **Access the App**: Open your browser and go to:
   ```
   http://localhost:8082
   ```

## 🚀 Deployment

This application can be deployed on various platforms. See **[DEPLOYMENT.md](DEPLOYMENT.md)** for detailed deployment instructions including:

- 🐳 **Docker** - Containerized deployment
- ☁️ **Render** - Free cloud hosting
- 🟣 **Heroku** - Platform as a Service
- 🚂 **Railway** - Modern deployment platform
- 📦 **AWS Elastic Beanstalk** - Scalable AWS deployment
- 🖥️ **Manual JAR Deployment** - Traditional server deployment

### Quick Deploy with Docker
```sh
docker build -t watchlist-app .
docker run -p 8082:8082 watchlist-app
```

### Quick Deploy with Docker Compose
```sh
docker-compose up -d
```

For detailed deployment instructions, please refer to [DEPLOYMENT.md](DEPLOYMENT.md).

## 📸 Screenshots (To Be Added Later)

## 📜 License

This project is licensed under the MIT License.

## 🤝 Contributing

Contributions are welcome! Feel free to fork this repo and submit a pull request.

## ⭐ Show some support by starring this repo!

If you found this project helpful, don't forget to give it a ⭐ on GitHub!

