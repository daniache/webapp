MVC AND RESTAPI PROJECT INSTRUCTIONS

Both projects were created to consolidate the understanding of building MVC Pattern design web application and Restful web services by creating a CRUD film collection database.
At the time of writing, both projects are at their finishing stage however room for improvement in terms of additional functionalities and design is potentially expected.

MVC PROJECT

[![film collection MVC(https://github.com/user-attachments/assets/8a21cc86-e54e-4c18-bc88-0457ea6b4a28)](https://github.com/daniache/webapp/issues/2#issue-2612681848)

Functionalities:
- It adds Film details in the database
- It view Film details on the web page
- It updates Film Details
- It deletes Film details
- It searches for a specific Film based on title, year, id,  director, stars or review.

Technologies used:

 - Frameworks and Libraries: jQuery, Bootstrap, mysql.jar, jstl 1-2.jar
 - Languages: Java, Javascript/jQuery
 - Https Servlets
 - Java Web Pages(JSP)

HOW TO INVOKE THE MVC APPLICATION

This project requires:

Java 8 or higher,
Eclipse,
Tomcat 9 or higher,
mysql server v. 5.7,
mysql workbench v. 5.7 or higher

1. Make sure that the project has the following files: 
   - in the controller package there should be five servlets: FilmAdd,-Delete,-List,-Retrive-Update Servlet
   - in the database package the FILMDAO class
   - in the model package the Film class
   - under webapp four jsp file named film-add-details-list-update.
   - under webapp/webinf, a lib folder with two lib mysql.jar and jstl-1.2.jar 
   
2. To run the application, right-click on FilmListServlet and running it using Tomcat and on port 8080



RESTAPI PROJECT 
![Film Collection REST API v](https://github.com/user-attachments/assets/9fa15957-7aa4-4f35-8fbc-5e2682dd6c09)

Functionalities:
- It adds Film details in the database
- It view Film details on the web page
- It updates Film Details
- It deletes Film details
- It searches for a specific Film based on title, year, id,  director, stars or review.

Technologies used:

 - Frameworks and Libraries: jQuery, Bootstrap, mysql.jar, jstl 1-2.jar, jaxb and gson.
 - Languages: Java, Javascript/jQuery, HTML, CSS.
 - API:  XMLHttpRequest (XHR)(ajax)
 - Restful APi servlet with doGET, doPost, doPut and doDelete

HOW TO INVOKE REST API APPLICATION

This project requires:

Java 8 or higher,
JAVASE -11 in Eclipse(for deployment purpose),
Eclipse,
Tomcat 9 or higher,
mysql server v. 5.7,
mysql workbench v. 5.7 or higher,

1. Make sure that the project has the following files: 
   - in the controllers package there should be one api servlets named ApisFilm and ResponseUtils.
   - in the database package the FILMDAO class
   - in the model package the Film class and FilmList class(Parentclass of XML)
   - under webapp, there are two folders named css with tablecss.css, scripts where there are three javascript files ajaxhandler, dataHandler and main and an html named index.html.
   - under webapp/webinf, a lib folder with jaxb and gson jar files.
   
   
2. To run the application, right-click on "index.html" and running it using Tomcat and on port 8080


NB. If you would like to see the cloud implementation of the project, I have uploaded a file named "Cloud Implementation" that show the entire process.

