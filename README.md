# <u>Spring Boot Microservices Kafka Project with Spring Security</u>

This is a demo **Spring Boot** appliation developed to understand the basics of **Microservices** Architecture, **Async Communication** between microservices using **Kafka** and **Security** within Microservices.

This project is designed based on <a href="https://www.mysqltutorial.org/getting-started-with-mysql/mysql-sample-database/">MYSQL Sample Database</a>.    

<img width="879" height="761" alt="mysql-sample-database" src="https://github.com/user-attachments/assets/3bcb83c6-110d-4da5-b7f7-32c9efe95e6e" />

Entities in the above image have been split into 4 different microservices **Employee**(employees and offices), **Customer**(customers and payments), **Order**(orders and orderdetails) 
and **Product**(products and productdetails) communicating with each other using **Kafka(kafka_2.13-3.9.1 - KRaft version)**. Spring security is added in **Security** and **Gateway** microservices. **Eureka** microservice is added to provide service registration.    

<hr>

## Steps to Run the Application
### Installations Required: 
1. JDK
2. Eclipse or IntelliJ
3. MYSQL
4. Kafka(kafka_2.13-3.9.1 - KRaft version)
5. Maven
6. Postman(for Testing)

spring.datasource.url=jdbc:mysql://localhost:3306/**DBName** <br>
Replace **DBName** in spring.datasource.url application property with respective Database schema name from MYSQL for Security, Employee, Customer, Order and Product DBs.

Start the Kafka Server. 

Run all microservices and ensure all services are up and running through Eureka(http://localhost:8761).

Server URLs: 

Gateway: http://localhost:8080 <br>
Employee: http://localhost:8081 <br>
Customer: http://localhost:8082 <br>
Order: http://localhost:8083 <br>
Product: http://localhost:8084 <br>
Security: http://localhost:8085 <br>

<hr>

## API DETAILS
## Public APIs
 
### Users: 

BaseURL : http://localhost:8080 <br>
Path: /auth/register <br>
Endpoint: http://localhost:8080/auth/register <br>
HTTP Medhod: POST <br>
Descriptioin: Register an app user <br>
RequestBody:  <br>
{ <br>
"username": "String", <br>
"password": "String", <br>
"role": "String" <br>
} <br>

BaseURL : http://localhost:8080 <br>
Path: /auth/authenticate <br>
Endpoint: http://localhost:8080/auth/authenticate <br>
HTTP Medhod: POST <br>
Descriptioin: Authenticate an app user <br>
RequestBody:  <br>
{ <br>
"username": "String", <br>
"password": "String" <br>
} <br>

## Secured APIs (JWT)

### Offices: 

BaseURL : http://localhost:8080 <br>
Path: /employees/offices <br>
EndPoint: http://localhost:8080/employees/offices <br>
HTTP Medhod: GET <br>
Descriptioin: Get all offices <br>

BaseURL : http://localhost:8080 <br>
Path: /employees/offices <br>
EndPoint: http://localhost:8080/employees/offices <br>
HTTP Medhod: POST <br>
Descriptioin: Add office <br>
RequestBody:  <br>
{ <br>
"city": "String", <br>
"phone": "String", <br>
"addressLine1": "String", <br> 
"addressLine2": "String", <br> 
"state": "String", <br> 
"country": "String", <br> 
"postalcode": "String", <br>
"territory": "String" <br>
} <br>

BaseURL : http://localhost:8080 <br>
Path: /employees/offices/pagination?page=0&size=5 <br>
EndPoint: http://localhost:8080/employees/offices/pagination?page=0&size=5 <br>
HTTP Medhod: GET <br>
Descriptioin: Get all offices paginated <br>

BaseURL : http://localhost:8080 <br>
Path: /employees/offices/{id} <br>
EndPoint: http://localhost:8080/employees/offices/{id} <br>
HTTP Medhod: GET <br>
Descriptioin: Get office with specific ID <br>

BaseURL : http://localhost:8080 <br>
Path: /employees/offices/{id} <br>
EndPoint: http://localhost:8080/employees/offices/{id} <br>
HTTP Medhod: PUT <br>
Descriptioin: Update office with specific ID <br>
RequestBody: <br> 
{ <br>
"city": "String", <br> 
"phone": "String", <br> 
"addressLine1": "String", <br> 
"addressLine2": "String", <br> 
"state": "String", <br> 
"country": "String", <br> 
"postalcode": "String", <br>
"territory": "String" <br>
} <br>

BaseURL : http://localhost:8080 <br>
Path: /employees/offices/{id} <br>
EndPoint: http://localhost:8080/employees/offices/{id} <br>
HTTP Medhod: DELETE <br>
Descriptioin: Delete office with specific ID <br>


### Employees:

BaseURL : http://localhost:8080 <br>
Path: /employees <br>
EndPoint: http://localhost:8080/employees <br>
HTTP Medhod: GET <br>
Descriptioin: Get all employees <br>

BaseURL : http://localhost:8080 <br>
Path: /employees <br>
EndPoint: http://localhost:8080/employees <br>
HTTP Medhod: POST <br>
Descriptioin: Add employee <br>
RequestBody: <br> 
{ <br>
"firstName": "String", <br> 
"lastName": "String", <br> 
"email": "String", <br> 
"jobTitle": "String", <br> 
"extension": "String", <br> 
"reportsTo": Long, <br> 
"officeCode": Integer <br>
} <br>

BaseURL : http://localhost:8080 <br>
Path: /employees/{id} <br>
EndPoint: http://localhost:8080/employees/{id} <br>
HTTP Medhod: GET <br>
Descriptioin: Get employee with specific ID <br>

BaseURL : http://localhost:8080 <br>
Path: /employees/{id} <br>
EndPoint: http://localhost:8080/employees/{id} <br>
HTTP Medhod: PUT <br>
Descriptioin: Update employee with specific ID <br>
RequestBody: <br> 
{ <br>
"firstName": "String", <br> 
"lastName": "String", <br> 
"email": "String", <br> 
"jobTitle": "String", <br> 
"extension": "String", <br> 
"reportsTo": Long, <br> 
"officeCode": Integer <br>
} <br>

BaseURL : http://localhost:8080 <br>
Path: /employees/{id} <br>
EndPoint: http://localhost:8080/employees/{id} <br>
HTTP Medhod: DELETE <br>
Descriptioin: Delete employee with specific ID <br> 


### Customers:

BaseURL : http://localhost:8080 <br>
Path: /customers <br>
EndPoint: http://localhost:8080/customers <br>
HTTP Medhod: GET <br>
Descriptioin: Get all customers <br>

BaseURL : http://localhost:8080 <br>
Path: /customers <br>
EndPoint: http://localhost:8080/customers <br>
HTTP Medhod: POST <br>
Descriptioin: Add customer <br>
RequestBody: <br> 
{ <br>
"customerName": "String", <br> 
"contactFirstName": "String", <br> 
"contactLastName": "String", <br> 
"phone": "String", <br> 
"addressLine1": "String", <br> 
"addressLine2": "String", <br>
"city": "String", <br>
"state": "String", <br>
"postalCode": "String", <br>
"country": "String", <br>
"creditlimit": BigDecimal, <br> 
"salesRepEmployeeNumber": Long <br>
} <br>

BaseURL : http://localhost:8080 <br>
Path: /customers/{id} <br>
EndPoint: http://localhost:8080/customers/{id} <br>
HTTP Medhod: GET <br>
Descriptioin: Get customer with specific ID <br>

BaseURL : http://localhost:8080 <br>
Path: /customers/{id} <br>
EndPoint: http://localhost:8080/customers/{id} <br>
HTTP Medhod: PUT <br>
Descriptioin: Update customer with specific ID <br>
RequestBody: <br> 
{ <br>
"customerName": "String", <br> 
"contactFirstName": "String", <br> 
"contactLastName": "String", <br> 
"phone": "String", <br> 
"addressLine1": "String", <br> 
"addressLine2": "String", <br>
"city": "String", <br>
"state": "String", <br>
"postalCode": "String", <br>
"country": "String", <br>
"creditlimit": BigDecimal, <br> 
"salesRepEmployeeNumber": Long <br>
} <br>

BaseURL : http://localhost:8080 <br>
Path: /customers/{id} <br>
EndPoint: http://localhost:8080/customers/{id} <br>
HTTP Medhod: DELETE <br>
Descriptioin: Delete customer with specific ID <br> 

### Payments:

BaseURL : http://localhost:8080 <br>
Path: /payments <br>
EndPoint: http://localhost:8080/payments <br>
HTTP Medhod: GET <br>
Descriptioin: Get all payments <br>

BaseURL : http://localhost:8080 <br>
Path: /payments <br>
EndPoint: http://localhost:8080/payments <br>
HTTP Medhod: POST <br>
Descriptioin: Add payment <br>
RequestBody: <br> 
{ <br>
"checkNumber": "String", <br> 
"amount": BigDecimal, <br> 
"paymentDate": Date, <br> 
"customerNumber": Long <br>
} <br>

BaseURL : http://localhost:8080 <br>
Path: /payments/{id} <br>
EndPoint: http://localhost:8080/payments/{id} <br>
HTTP Medhod: GET <br>
Descriptioin: Get payment with specific ID <br>

BaseURL : http://localhost:8080 <br>
Path: /payments/{id} <br>
EndPoint: http://localhost:8080/payments/{id} <br>
HTTP Medhod: PUT <br>
Descriptioin: Update payment with specific ID <br>
RequestBody:  <br>
{ <br>
"checkNumber": "String", <br> 
"amount": BigDecimal, <br> 
"paymentDate": Date, <br> 
"customerNumber": Long <br>
} <br>

BaseURL : http://localhost:8080 <br>
Path: /payments/{id} <br>
EndPoint: http://localhost:8080/payments/{id} <br>
HTTP Medhod: DELETE <br>
Descriptioin: Delete payment with specific ID <br> 

### Orders:

BaseURL : http://localhost:8080 <br>
Path: /orders <br>
EndPoint: http://localhost:8080/orders <br>
HTTP Medhod: GET <br>
Descriptioin: Get all orders <br>

BaseURL : http://localhost:8080 <br>
Path: /orders <br>
EndPoint: http://localhost:8080/orders <br>
HTTP Medhod: POST <br>
Descriptioin: Add order <br>
RequestBody: <br> 
{ <br>
"orderDate": Date, <br>
"requiredDate": Date, <br>
"shippedDate": Date, <br>
"status": "String", <br> 
"comments": "String", <br> 
"customerNumber": Long <br>
} <br>

BaseURL : http://localhost:8080 <br>
Path: /orders/{id} <br>
EndPoint: http://localhost:8080/orders/{id} <br>
HTTP Medhod: GET <br>
Descriptioin: Get order with specific ID <br>

BaseURL : http://localhost:8080 <br>
Path: /orders/{id} <br>
EndPoint: http://localhost:8080/orders/{id} <br>
HTTP Medhod: PUT <br>
Descriptioin: Update order with specific ID <br>
RequestBody: <br> 
{ <br>
"orderDate": Date, <br>
"requiredDate": Date, <br>
"shippedDate": Date, <br>
"status": "String", <br> 
"comments": "String", <br> 
"customerNumber": Long <br>
} <br>

BaseURL : http://localhost:8080 <br>
Path: /orders/{id} <br>
EndPoint: http://localhost:8080/orders/{id} <br>
HTTP Medhod: DELETE <br>
Descriptioin: Delete order with specific ID <br> 

### Orderdetails:

BaseURL : http://localhost:8080 <br>
Path: /orderdetails <br>
EndPoint: http://localhost:8080/orderdetails <br>
HTTP Medhod: GET <br>
Descriptioin: Get all orderdetails <br>

BaseURL : http://localhost:8080 <br>
Path: /orderdetails <br>
EndPoint: http://localhost:8080/orderdetails <br>
HTTP Medhod: POST <br>
Descriptioin: Add orderdetail <br>
RequestBody: <br>
{ <br>
"orderNumber": Long, <br>
"productCode": "String", <br>
"quantityOrdered": Integer, <br>
"priceEach": BigDecimal, <br> 
"orderLineNumber": Integer <br>
} <br>

BaseURL : http://localhost:8080 <br>
Path: /orderdetails/{id} <br>
EndPoint: http://localhost:8080/orderdetails/{id} <br>
HTTP Medhod: GET <br>
Descriptioin: Get orderdetail with specific ID <br>

BaseURL : http://localhost:8080 <br>
Path: /orderdetails/{id} <br>
EndPoint: http://localhost:8080/orderdetails/{id} <br>
HTTP Medhod: PUT <br>
Descriptioin: Update orderdetail with specific ID <br>
RequestBody: <br> 
{ <br>
"orderNumber": Long, <br>
"productCode": "String", <br>
"quantityOrdered": Integer, <br>
"priceEach": BigDecimal, <br> 
"orderLineNumber": Integer <br>
} <br>

BaseURL : http://localhost:8080 <br>
Path: /orderdetails/{id} <br>
EndPoint: http://localhost:8080/orderdetails/{id} <br>
HTTP Medhod: DELETE <br>
Descriptioin: Delete orderdetail with specific ID <br> 

### Products:

BaseURL : http://localhost:8080 <br>
Path: /products <br>
EndPoint: http://localhost:8080/products <br>
HTTP Medhod: GET <br>
Descriptioin: Get all products <br>

BaseURL : http://localhost:8080 <br>
Path: /products <br>
EndPoint: http://localhost:8080/products <br>
HTTP Medhod: POST <br>
Descriptioin: Add product <br>
RequestBody: <br>
{ <br>
"productCode": "String", <br>
"productName": "String", <br>
"productLine": "String", <br>
"productScale": "String", <br>
"productVendor": "String", <br>
"productDescription": "String", <br>
"quantityInStock": Integer, <br>
"buyPrice": BigDecimal, <br> 
"MSRP": BigDecimal <br>
} <br>

BaseURL : http://localhost:8080 <br>
Path: /products/{productcode} <br>
EndPoint: http://localhost:8080/products/{productcode} <br>
HTTP Medhod: GET <br>
Descriptioin: Get product with specific ID <br>

BaseURL : http://localhost:8080 <br>
Path: /products/{productcode} <br>
EndPoint: http://localhost:8080/products/{productcode} <br>
HTTP Medhod: PUT <br>
Descriptioin: Update product with specific ID <br>
RequestBody: <br> 
{ <br>
"productCode": "String", <br>
"productName": "String", <br>
"productLine": "String", <br>
"productScale": "String", <br>
"productVendor": "String", <br>
"productDescription": "String", <br>
"quantityInStock": Integer, <br>
"buyPrice": BigDecimal, <br> 
"MSRP": BigDecimal <br>
} <br>

BaseURL : http://localhost:8080 <br>
Path: /products/{product} <br>
EndPoint: http://localhost:8080/products/{product} <br>
HTTP Medhod: DELETE <br>
Descriptioin: Delete product with specific ID <br> 

### Productlines:

BaseURL : http://localhost:8080 <br>
Path: /productlines <br>
EndPoint: http://localhost:8080/productlines <br>
HTTP Medhod: GET <br>
Descriptioin: Get all productlines <br>

BaseURL : http://localhost:8080 <br>
Path: /productlines <br>
EndPoint: http://localhost:8080/productlines <br>
HTTP Medhod: POST <br>
Descriptioin: Add productline <br>
RequestBody: <br> 
{ <br>
"productLine": "String", <br>
"textDescription": "String", <br>
"htmlDescription": "String", <br>
"image": "String" <br>
} <br>

BaseURL : http://localhost:8080 <br>
Path: /productlines/{productline} <br>
EndPoint: http://localhost:8080/productlines/{productline} <br>
HTTP Medhod: GET <br>
Descriptioin: Get productline with specific ID <br>

BaseURL : http://localhost:8080 <br>
Path: /productlines/{productline} <br>
EndPoint: http://localhost:8080/productlines/{productline} <br>
HTTP Medhod: PUT <br>
Descriptioin: Update productline with specific ID <br>
RequestBody: <br> 
{ <br>
"productLine": "String", <br>
"textDescription": "String", <br>
"htmlDescription": "String", <br>
"image": "String" <br>
} <br>

BaseURL : http://localhost:8080 <br>
Path: /productlines/{productline} <br>
EndPoint: http://localhost:8080/productlines/{productline} <br>
HTTP Medhod: DELETE <br>
Descriptioin: Delete productline with specific ID <br> 
