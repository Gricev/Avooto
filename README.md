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

**Required software**
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


`Entity--->Repository--->Service--->Controller`

---

**Building a project:**

There are 2 entities, a product and a user, marked with an annotation @Entity, because Hibernate is at the heart of JPA technology – he takes over part of the work on creating database tables.
The relationship between these two classes is carried out using @OneToMany and
@ManyToOne – there can be several product entities for each user entity.

Since Spring Security was chosen as the basis of security, it was advisable to apply the Principal pattern – an interface, the implementation of which allows you to remember a specific user in the session who authenticated with the database. Thus, we configured the loadUserByUsername() method, redefining which, we can use the Email field to search for an account in the database. The configure() method is used to authenticate the user. @Bean PasswordEncoder is used to encrypt passwords in the database, implements BCrypt encryption. We also use the configure() method to restrict traffic to unauthenticated users by certain URIs.

We use Roles to differentiate access to project tools.
To implement images in the project, the Images entity was created. Its relationship between the product and the user is carried out using @OneToOne and @ManyToOne – one avatar is used for each user, and many images are mapped for one product. In the database, the bytes of this entity are stored as a BLOB — a binary large object for storing binary data, such as an image, audio or video.

The Service layer describes the logic of the main CRUD operations performed in the project.
In the Controller layer, the type of variables returned is ftlh. template.
Thanks to the Apache Freemarker template engine, you can generate dynamic pages with logic embedded in the template. This is done using the model method. Attribute.
___

**Test application from external API**

- To start testing run the command: `docker-compose up`

crudJDBC.jar will be run on localhost:8086

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



