FROM java:8-alpine
ADD ./app/phonebook-be.jar /app/
WORKDIR /app
CMD ["java","-jar", "phonebook-be.jar", "--spring.datasource.url=jdbc:postgresql://phonebook-db/contacts"]