LINK: https://github.com/mikademo/cybersecuritybase-project

Installation and running:

mvn clean package
java -jar target/cybersecuritybase-project-1.0-SNAPSHOT.jar

Open the browser in the URL http://localhost:8080

The accounts and passwords are:
-one/yksi
-two/kaksi


FLAW 1:

Description

A2:2017 Broken Authentication

The application exposes the session ID in the URL.

The application writes the jsessionid in the URL with the Firefox browser (version 70.0.1, Ubuntu 18.04.3 LTS) (not with the Chrome browser version 72.0.3626.121).

When the user opens the new browser and enters the URL http://localhost:8080 the browser
goes to the URL where the session ID is exposed, like this:

http://localhost:8080/login;jsessionid=A00E5471120F09D9FA3FA69D5EE3A50D

How to fix it

Remove the line 

http.sessionManagement().enableSessionUrlRewriting(true);

in the method sec.project.config.SecurityConfiguration.configure.


FLAW 2:

Description

A6:2017 Security Misconfiguration

The Spring security's default CSRF protection is disabled.

How to fix it

Remove the line 

http.csrf().disable();

in the method sec.project.config.SecurityConfiguration.configure.


FLAW 3:

Description

A3:2017 Sensitive Data Exposure

The application saves the social security number in the plain text format.

How to fix it

In the method sec.project.controller.SignupController.submitForm use the commands:

TextEncryptor encryptor = Encryptors.delux("kddskRdls!klslsk", "5c0744940b5c369b");
ssn = encryptor.encrypt(ssn);

to encrypt the social security number with the Spring security's classes.






-------------------



--------

Mooc: Skannaa onko joku kirjasto haavoittunut? Delete injektio tallennukseen. XSS tallennus ja uusi haku. Hetun yms. tallennus ja paljastus. Csrf XSS:ää? -> helppo tehdä

