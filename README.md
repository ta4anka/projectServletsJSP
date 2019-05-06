**The console crud application that interacts with the database through Hibernate, Servlets ans JSP**

**Task**

Implement a console application that interacts with the database.

**Entities:**
* Skill (id, name)
* User(id, firstName, lastName, specialty, Set<Skill> skills).
* Team(id, name, Set<User> users)
* Project(id, name, budget, Set<Team> teams)
* Customer(id, name, Set<Project> projects)

**Requirements:**
* All CRUD operations for each of the entities.
* Follow MVC approach
* To build a project use Maven.
* To interact with the database - Hibernate
* To configure Hibernate use annotations
* Database initialization should be implemented using liquibase
* User interaction should be implemented using Servlets + JSP