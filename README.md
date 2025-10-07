# 🎬 Movies API (POC)

This repository contains a simple **Proof of Concept (POC)** project developed as part of my **FEDE Bachelor’s degree in Computer Science and Networks (Specialization: Développement et Bases de Données - D31a)**.  

The goal of this project is to demonstrate how to:  
- Build a **REST API** using **Java 8, Servlets, and JAX-RS (Jersey)**  
- Package and deploy the project as a **WAR** on **Apache Tomcat**  
- Return JSON responses from endpoints without using advanced frameworks  

---

## 🚀 Features
- REST endpoints built with **JAX-RS (Jersey)**  
- Packaged as a `.war` file with **Maven**  
- Deployable on **Apache Tomcat 8.5+**  
- Returns JSON responses (static data, no database)  
- Educational project (not intended for production)  

---

## 📂 Project Structure
.
├── pom.xml
├── src
│   └── main
│       ├── java
│       │   └── com
│       │       └── labgeek
│       │           └── moviesapi
│       │               └── resources
│       │                   └── MovieResource.java
│       └── webapp
│           └── WEB-INF
│               └── web.xml
└── target
    └── movies-api.war


---

## 📡 Example Endpoint
- **GET** `/movies-api/api/movies`  

Response:
```json
[
  { "id": 1, "title": "The Matrix", "director": "Wachowski" },
  { "id": 2, "title": "Inception", "director": "Christopher Nolan" }
]
```


⚙️ Tech Stack

Java 8

Maven

Jersey (JAX-RS 2.x)

Servlet API

Apache Tomcat 8.5+

▶️ How to Run

Build the WAR file:
```bash
mvn clean package

```

Copy the generated WAR:
```bash
target/movies-api.war

```

into Tomcat’s webapps/ directory.

Start Tomcat.

Open in your browser:
```bash
http://localhost:8080/movies-api/api/movies
```

🎓 Academic Context

This project was developed as part of the FEDE Bachelor’s degree in Computer Science and Networks, specialization:
Développement et Bases de Données (D31a).

It serves as a POC (Proof of Concept) to practice:

Java web development (Servlets & JAX-RS)

REST API design

Maven project structuring

WAR packaging & deployment on Tomcat
