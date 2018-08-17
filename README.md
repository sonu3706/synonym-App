<!-- Tech Stack used for the application -->
1. Angular-6 for frontend
2. Spring Boot version 2.0.4
3. Mysql version 5.6
4. Docker- compose version 1.22.0

<!-- Steps to package and run the frontend:--- -->

1. Navigate to the inside synonyms-Frontend and type `npm install`.(Node and npm has to be installed).
2. Then type `npm start` which will bootstrap the frontend on port number 4200.

<!-- Steps to package and run Backend service -->

1. Type following command to package and the backend `mvn clean package -DskipTests`.
2. If Docker is installed in the machine the navigate to to the docker-compose file and run the following command over terminal
  `docker-compose up`.

  Which will start the backend on port number 8081.


  Note:
  To login to the application kindly move inside the mysql docker container with the given command
     `docker ps -a` -----> will give you list of docker conatiner running.
     `docker exec -it [container-name]/[conatiner id] bash` ---> by this you can navigate to the contianer which is running mysql inside it.

     Once you are inside the conatiner then provide the following credential to login in into the mysql application.

     `mysql -u chandan -p demodb`
     password: `root123`.
     `use demodb;`
    ` insert into user (user_id, user_name, user_password) values ("chandan3706", 'Chandan', '123456');`
    Then 

    ` INSERT INTO `synonym_word` VALUES (1,'revelation','Apocalypse'),(2,'New Testament ','Apocalypse'),(3,'rush ','Run'),(4,'zip ','Run'),(5,'hare ','Run'); `


  Once done then Navigate to Browser  and type http://localhost:4200 ---> to access frontend
  To check the rest endpoints through postman :-- http://localhost:8081/api/v1/user(POST request)---> to Login
                                                    
                                                   http://localhost:8081/api/v1/word/[anyword-from db](Get request)----> to fetch the synonyms
Note: KIndly refer to the demodb.sql for data entry.
     

