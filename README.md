# Gila Software Coding Challenge

## Requirements
* Java 17

## How to run

As this is a Spring Boot project, the following command is used to start the API server:

```shell
./mvnw spring-boot:run
```

With the server executing, the below API will be available:

**Notification Delivery**
----
A message will be sent with the chosen category to every user that has subscribed to it and will deliver using each 
notification type chosen by each user.<br>
The messages (and all information related to it) will be registered on the _notification_log.txt_ file which will be 
generated on the root of the project if it doesn't already exist and will also appear on the server log.

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

## Coding Challenge Requirements

### Back End – Notification APIs

It is required to create a notification system API, which is capable of receiving a message and depending on the
category of the message and the users subscribed to them, said users will be notified to the medium that they
themselves chose.

3 categories of messages will be handled:
*  Sports
* Finance
* Films

And it is required to send 3 types of notifications
* SMS 
* E-mail
* Push Notification

It is not required that any message is actually sent or communicates with any external API, only the sending of
said notification will be recorded in a Logs file or in the database.

In the log it is required to save all the necessary information to identify that the correct notification was made
to the corresponding user. Save information such as the type of message, type of notification, user data, time,
etc.

User administration is not required, a Mock of users can be managed in the system, they must have the
following information:
* ID
* Name
* E-mail
* Phone
* Subscribed [] List of categories you are subscribed to
* Channels [] List of user notification types (SMS | Email | PushNotification)

The API will receive 2 parameters:
1. Category. Validate from the list of available categories
2. Message. Just validate that the message is not empty.
   
What will be evaluated:
* Application architecture and design patterns
* OOP and Scalability (Ready to add more types of notifications).
* Handling requests to the Server through RESTful APIs
* For testing, register at least 3 users with different configurations

