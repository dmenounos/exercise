Exercise Java Web Application
=============================

A Java web application built with the Spring 5 platform.
It demonstrates the following features:

* A service layer for managing a Legal Entity model
* A REST resource for exposing Legal Entity objects
* Multi module project based on Maven


Build instructions
------------------

	$ mvn clean install


Run instructions
----------------

	$ cd exercise-web
	$ mvn spring-boot:run

Access the REST service through:
http://localhost:8080/api/legal-entities

Access the UI at:
http://localhost:8080/
