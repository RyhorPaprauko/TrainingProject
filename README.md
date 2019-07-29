# Online Book Store(IT-Academy TrainingProject)
  Web-application based on Java Spring, Hibernate ORM with PostgreSQL managed by Maven.
Then used: Spring Security, MapStruct, for View Layer - Thymeleaf.
Also I tried to make some Rest Controllers just for experience, but JS that processes data from them can look terrible.

  What about logic, unauthenticated user can search for books according to the required parameters, register and log in.
Authenticated - add book to his cart and send completed order to admin,  leave a comment and remove it.
Admin able to add another admin, add new book and update existing one, delete comments by all users.

Also I added validation to RegistrationDto with custom annotations.
