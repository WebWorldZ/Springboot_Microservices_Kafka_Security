This is a demo **Spring Boot** appliation developed to understand the basics of **Microservices** Architecture, **Async Communication** between microservices using **Kafka** and **Security** within Microservices.

This project is designed based on <a href="https://www.mysqltutorial.org/getting-started-with-mysql/mysql-sample-database/">MYSQL Sample Database</a> .    

<img width="879" height="761" alt="mysql-sample-database" src="https://github.com/user-attachments/assets/3bcb83c6-110d-4da5-b7f7-32c9efe95e6e" />

Entities in the above image have been split into 4 different microservices **Employee**(employees and offices), **Customer**(customers and payments), **Order**(orders and orderdetails) 
and **Product**(products and productdetails) communicating with each other using **Kafka(kafka_2.13-3.9.1 - KRaft version)**. Spring security is added in **Security** and **Gateway** microservices. **Eureka** microservice is added to provide service registration.    
