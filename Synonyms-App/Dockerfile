FROM java:8-jre
WORKDIR usr/src
ADD ./target/synonymsapp-0.0.1-SNAPSHOT.jar /usr/src/synonymsapp-0.0.1-SNAPSHOT.jar
ADD *.sql /docker-entrypoint-initdb.d/
RUN chmod +x /usr/src/synonymsapp-0.0.1-SNAPSHOT.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/usr/src/synonymsapp-0.0.1-SNAPSHOT.jar"]