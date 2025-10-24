# CineHub



## 📝 Description

CineHub is a robust Java-based application meticulously crafted using Maven for dependency management and build automation. While the current feature set includes testing, the project lays a solid foundation for future expansion into a comprehensive movie management or streaming platform. The application's architecture prioritizes modularity and scalability, making it well-suited for incorporating features like user authentication, movie browsing, personalized recommendations, and potentially even video playback capabilities. With its clean codebase and reliance on industry-standard tools, CineHub presents an excellent starting point for developers seeking to build a sophisticated media application.

## ✨ Features

- 🧪 Testing


## 🛠️ Tech Stack

- ☕ Java (Maven)


## 📦 Key Dependencies

```
spring-context: 6.2.11
spring-data-jpa: 3.3.1
hibernate-core: 6.4.4.Final
jakarta.persistence-api: 3.1.0
mysql-connector-java: 8.0.33
jakarta.servlet-api: 6.0.0
spring-webmvc: 6.2.10
jackson-databind: 2.17.0
spring-orm: 6.1.5
spring-tx: 6.1.5
jackson-annotations: 2.17.0
jackson-core: 2.17.0
jackson-datatype-jsr310: 2.17.0
validation-api: 2.0.1.Final
spring-test: 6.1.4
```

## 📁 Project Structure

```
.
├── pom.xml
└── src
    ├── main
    │   ├── java
    │   │   ├── DTO
    │   │   │   ├── CategoryDTO.java
    │   │   │   ├── DirectorDTO.java
    │   │   │   └── FilmDTO.java
    │   │   ├── Main.java
    │   │   ├── config
    │   │   │   └── SwaggerConfig.java
    │   │   ├── controller
    │   │   │   ├── CategoryController.java
    │   │   │   ├── DirectorController.java
    │   │   │   └── FilmController.java
    │   │   ├── model
    │   │   │   ├── Category.java
    │   │   │   ├── Director.java
    │   │   │   └── Film.java
    │   │   ├── repository
    │   │   │   ├── ICategoryRepository.java
    │   │   │   ├── IDirectorRepository.java
    │   │   │   └── IFilmRepository.java
    │   │   └── service
    │   │       ├── CategoryService.java
    │   │       ├── DirectorService.java
    │   │       └── FilmService.java
    │   ├── resources
    │   │   ├── dispatcher-servlet.xml
    │   │   └── spring.xml
    │   └── webapp
    │       └── WEB-INF
    │           └── web.xml
    └── test
        └── java
            └── service
                ├── CategoryServiceTest.java
                ├── DirectorServiceTest.java
                └── FilmServiceTest.java
```

## 🛠️ Development Setup

### Java (Maven) Setup
1. Install Java (JDK 11+ recommended)
2. Install Maven
3. Install dependencies: `mvn install`
4. Run the project: `mvn exec:java` or check `pom.xml` for specific run commands


## 👥 Contributing

Contributions are welcome! Here's how you can help:

1. **Fork** the repository
2. **Clone** your fork: `git clone https://github.com/kirito554jbr/CineHub.git`
3. **Create** a new branch: `git checkout -b feature/your-feature`
4. **Commit** your changes: `git commit -am 'Add some feature'`
5. **Push** to your branch: `git push origin feature/your-feature`
6. **Open** a pull request

Please ensure your code follows the project's style guidelines and includes tests where applicable.

