LINK: https://github.com/mikademo/cybersecuritybase-project

Installation:

mvn clean package
java -jar target/cybersecuritybase-project-1.0-SNAPSHOT.jar

FLAW 1:

Description

A2:2017 Broken Authentication

The application uses the plain text passwords.

How to fix it

Replace the MyPasswordEncoder class with the org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder class in the method SecurityConfiguration.passwordEncoder.