<h2>School System.</h2></br>

Java 17, Spring 3.0, PostgreSQL...

To run this back-end you need Java 17.<br>
The Database is in-memory (h2).

<b>Server Configuration:</b></br>
To start the server, navigate to project root and run:

.\mvnw clean install spring-boot:run<br>

Access http://127.0.0.1:8080/login to authenticate.

There is an user with Admin privileges for development:<br>
-Username: dev<br>
-Password: 1<br>

You can go to SwaggerUI page to visualize and use the endpoints and better understand what to send or expect, available
at: http://127.0.0.1:8080/docs </br>

<b>Basic functions:</b></br>
-User CRUD operations. You need a user to authenticate. User roles are in UserRoleEnum.java</br>
-Student CRUD operations. A student will have an user ID for reference, besides it's personal data such as name,
emergency contact, grade...</br>
-Generate Students PDF report</br>
-Authentication with Spring Security 3.0</br>

TODO:</br>
-discover how to make Spring Security 3 redirect to /docs after login
-make Spring Security consider the database created user instead of the java in-built one</br>
-create some logic to edit Students in a batch, for example to alter the grade of a whole approved class and etc.</br>
</br>

