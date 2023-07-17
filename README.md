# Gila Software Coding Challenge

## Requirements

* Java 17
* Docker

## How to run

The MongoDB container should be up and running before starting the application:
> PS: On the first execution the database will be created and loaded with data from dump files that are on the _db-dump_ folder.
```shell
docker compose up
```

As this is a Spring Boot project, the following command is used to start the API server:

```shell
./mvnw spring-boot:run
```
\
 With the server executing, a webpage (http://localhost:8080) and also the API below will be available to send notifications:

**Notification Delivery**
----
A message will be sent with the chosen category to every user that has subscribed to it and will deliver using each 
notification type chosen by each user.<br>
The messages (and all information related to it) will be registered on thee database and will appear on the application log.

* **URL**

  http://localhost:8080/notification/send

* **Method:**

  `POST`

* **Data Params**

  **category** (Required)

  _The category of the message that is going to be sent._

  _Available categories: Films, Finance, Sports._

  **message** (Required)

  _The message which will be sent to users subscribed to the category._

---

## Improvement Ideas

* Do more componentization on the React frontend code.
* Define more types with Typescript and use for every object and variable.
* Create automated tests on the frontend project.
* Use websocket on the frontend to update the log without having to refresh the page.
* Better handling of situations in which could result in an error on backend requests by showing a message to the user.
* Pagination and filtering of log lines.
* Animation showing clearly which lines were recently added to the Log History.

---

## Coding Challenge Requirements

### Notification Test

We have to create a notification system that has the ability to receive a message and depending on
the category and subscribers, notify these users in the channels they are registered.

It will be 3 message categories:
* Sports
* Finance
* Movies

And there will be 3 types of notifications, each type should have its own class to manage the logic of
sending the message independently.
* SMS
* E-Mail
* Push Notification

No notification will actually be sent or the need to communicate with any external APIs, only will
register the sent notification in an archive of Logs or in a database.\
In the log, it will need to save all the information necessary to identify that the notification has been
sent correctly to the respective subscriber, such as the type of message, type of notification, user
data, time, etc.\
No user administration is required, you can use a Mock of users in the source code, and they must have
the following information:
* ID
* Name
* Email
* Phone number
* Subscribed [] here you need to list all the categories where the user is subscribed
* Channels [] a list of the notification's channels (SMS | E-Mail | Push Notification)

As user interface you need to display 2 main elements.
1. **Submission form.** A simple form to send the message, which will have 2 fields:
   * **Category.** List of available categories.
   * **Message.** Text area, only validate that the message is not empty.
2. **Log history.** A list of all records in the log, sorted from newest to oldest.

What will be evaluated:
* Best practices: Validations and handle exceptions, Intuitive names, and OOP.
* Solid Principles: Separation of concerns, Abstraction and scalability, Inferface usage, Dependency
inversion.
* Design Patterns: One or more design patterns implemented to solve the main problem, select the proper
channels and send notifications.
* Architecture: Well architecture design, have a well defined folder structure and separation of concerns,
scalable and prepared for minimum changes on new requirements in the future.
(Routes/Controllers/Services/Repositories, DTOs / Interfaces / etc.).
* Unit Testing: Tests for each service and function, Multiple test scenarios per function.
* Database: Migrations and Seeders, Foreign keys usage (In case of RDBMS), Indexing, Proper data types
and length and Load all catalogs in the database (Is a plus).
* Challenge: Requirements accomplish, Performance and search methods, Fault tolerant on sending
  notifications and Scalable to add more notification channels.