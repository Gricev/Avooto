Avooto project
=


---
It's a CRUD buy-sell project with using Spring Framework.

---
**Used technologies:**

- JDK 17.0.3
- Apache Maven 2.7.2
- Spring Boot 2.6
- Spring MVC
- Spring Security
- MySQL
- Java Persistence API(Hibernate) 
- JMS(Java Mail Sender)
- Apache Freemarker
- CSS
- Html

**Additional software**
- Docker

---
**How to run this application:**

`docker pull mysql`

1. To start you need to install JDK 17 and install ide(Intellij idea, Net Beans and etc.)
2. Then to create scheme with name "avooto" in your local data base or pull by link dump of my db <https://disk.yandex.ru/d/2qVJWR8_ASxYcQ>;

You can also use docker's command: `docker push 89898152970/avooto:{tagname}` and then run it on your localhost;
_P.S. It's no need to configure JMS environment because it was done in project already._
___

**About this project**

![](https://github.com/Gricev/Avooto/blob/master/Scheme.png)

This project is a REST API built on the SOLID principle and a 3-link architecture, i.e. each layer of logic is divided among themselves, and also performs CRUD operations on objects.

---

**Test application from external API**

- To start testing run the command: `docker-compose up`

crudJDBC.jar will be run on localhost:8086. You can also clone project from repo and start it on your localhost:

`https://github.com/Gricev/crudJDBC.git`

**How to test basic commands in application**

- To create a product: `curl -X POST "http://localhost:8086/product/save" -H "accept: /" -H "Content-Type: application/json" -d 
"{
  "id": "",
  "title": "Some title",
  "description": "dscr",
  "price": "8086",
  "city": "some city",
  "category": "any category",
  "userId": "2",
  "previewImageId": "34",
  "dateOfCreated": "2022-08-15T12:07:01.264+00:00",
  "views": "8080"
}"`

- To get all products: `curl -X GET "http://localhost:8086/product" -H "accept: /"`

- To get a product by ID: `curl -X GET "http://localhost:8086/product/{id}" -H "accept: /"`

- To update a product by ID: `curl -X PUT "http://localhost:8086/product/{id}" -H "accept: /" -H "Content-Type: application/json" -d "
{
  "id": "",
  "title": "Some title",
  "description": "dscr",
  "price": "8086",
  "city": "some city",
  "category": "any category",
  "userId": "2",
  "previewImageId": "34",
  "dateOfCreated": "2022-08-15T12:07:01.264+00:00",
  "views": "8080"
}"`

- To delete product: `curl -X DELETE "http://localhost:8086/product/delete/{id}" -H "accept: /"`

___



