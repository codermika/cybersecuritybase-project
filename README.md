LINK: https://github.com/mikademo/cybersecuritybase-project

Installation:

mvn clean package
java -jar target/cybersecuritybase-project-1.0-SNAPSHOT.jar

Open the browser in the URL http://localhost:8080
The accounts and passwords are:
-one/yksi
-two/kaksi

FLAW 1:

Description

A2:2017 Broken Authentication

The application uses the plain text passwords.

How to fix it

Replace the MyPasswordEncoder class with the org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder class in the method sec.project.config.SecurityConfiguration.passwordEncoder.

FLAW 2:

Description

A6:2017 Security Misconfiguration

The Spring security's default CSRF protection is disabled.

How to fix it

Remove the line "http.csrf().disable();" in the method sec.project.config.SecurityConfiguration.configure.

-------------------

A3:2017 Sensitive Data Exposure

laita hetu kantaan

--------

