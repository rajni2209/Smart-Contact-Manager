# 📇 Smart Contact Manager  

The **Smart Contact Manager** is a web application that allows users to securely manage their personal and professional contacts. It provides authentication, authorization, and CRUD operations for contact management, showcasing strong backend development skills with Spring Boot and Spring Ecosystem.  


Home page
<img width="1912" height="858" alt="Screenshot 2025-09-05 144304" src="https://github.com/user-attachments/assets/2641c786-9fdf-40a8-977f-6241b1f135e9" />
Dashboard
<img width="1917" height="870" alt="Screenshot 2025-09-05 144334" src="https://github.com/user-attachments/assets/502aa902-4cc3-4875-95d2-3e642eb5ec74" />
Contact list
<img width="1919" height="863" alt="image" src="https://github.com/user-attachments/assets/9a1be17d-59b2-4767-8585-b87eca746db1" />


## 🚀 Features  

- 🔐 **User Authentication & Authorization** with Spring Security  
- 👤 **Role-based access control** (Admin/User)  
- 📱 **Manage Contacts** – Add, update, delete, and search contacts  
- 🖼️ **Profile Management** – Users can update their profile  
- 🗄️ **Database Integration** – Contacts and user data stored in MySQL  
- 🌐 **Responsive UI** built with Thymeleaf, HTML, CSS, and Bootstrap  

---

## 🛠️ Tech Stack  

- **Backend**: Spring Boot, Spring MVC, Spring Security, Spring Data JPA  
- **Frontend**: Thymeleaf, HTML, CSS, Bootstrap  
- **Database**: MySQL  
- **Build Tool**: Maven  

---

## 📂 Project Structure  

```
Smart-Contact-Manager/
│── src/main/java/com/example/scm # Java source code (controllers, services, models)
│── src/main/resources/ # Application properties, templates, static files
│── pom.xml # Maven build file
│── README.md

```
---

## 💡 How It Works  

1. Users **register and log in** securely.  
2. Authenticated users can **add, edit, delete, and view contacts**.  
3. **Spring Security** ensures only authorized users can access specific pages.  
4. Data is stored in **MySQL** using Spring Data JPA/Hibernate.  
5. The UI is rendered with **Thymeleaf templates**, styled with Bootstrap.  

---

## 🧑‍💻 Backend Skills Demonstrated  

- **Spring Boot** for building the application  
- **Spring Security** for login, authentication, and role-based authorization  
- **Spring Data JPA + Hibernate** for database persistence  
- **MySQL integration** for data management  
- **Thymeleaf** for server-side rendering  
- **MVC architecture** for clean code structure  

---

## ⚙️ Getting Started  

### ✅ Prerequisites  

Make sure you have installed:  
- [Java 17+](https://adoptium.net/)  
- [Maven](https://maven.apache.org/)  
- [MySQL Server](https://dev.mysql.com/downloads/)  

### 📥 Installation  

1. **Clone the repository**  

```bash
git clone https://github.com/rajni2209/Smart-Contact-Manager.git
cd smart-contact-manager
```
Create Database
```
CREATE DATABASE smart_contact_manager;
```
Update src/main/resources/application.properties with your DB credentials:
```
spring.datasource.url=jdbc:mysql://localhost:3306/smart_contact_manager
spring.datasource.username=your-username
spring.datasource.password=your-password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```
Build and Run the Application
```
mvn spring-boot:run
```


## 🙋‍♂️ Author
---
- **Rajnikant**  
  [GitHub Profile](https://github.com/rajni2209)<br>
  [Linkedin Profile](https://www.linkedin.com/in/rajnikant-kumar-27bb22354/)


