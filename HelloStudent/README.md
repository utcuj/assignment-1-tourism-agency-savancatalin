# laborator2

This is an example of a layered JSF application which uses domain model + active record patterns.

You can use this application as a start point in your assignment or you can start the assignment from scratch.

In order to run the application locally you need to perform the following steps after cloning the repository.

1. Install Maven
   [How to Install Maven on Windows](https://www.mkyong.com/maven/how-to-install-maven-in-windows/)
   [What is Maven? ](https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html)

2. Install Tomcat Server
   [Download tomcat server](http://tomcat.apache.org/)

3. Install MySql Workbench

4. Create a database student with a table student in MySql Workbench
   Script can be found in resources folder: student.sql

5. Build the application with maven by running the command: 
   mvn clean install

6. Copy the generated war file in the installation directory of tomcat in webapps
   ...apache-tomcat-9.0.5\webapps
   
7. Start tomcat server 
   [Start tomcat server](https://www.webucator.com/how-to/how-start-stop-apache-tomcat-from-the-command-line-windows.cfm)

8. Reach application at following url on localhost:
   http://localhost:8080/HelloStudent
   
Further reading:

1. What is a JSF application?
2. What is a layered application?
3. More about the patterns
