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

The application uses the plain text passwords.

How to fix it

Replace the MyPasswordEncoder class with the org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder class in the method sec.project.config.SecurityConfiguration.passwordEncoder.

FLAW 2:

Description

A6:2017 Security Misconfiguration

The Spring security's default CSRF protection is disabled.

How to fix it

Remove the line "http.csrf().disable();" in the method sec.project.config.SecurityConfiguration.configure.

FLAW 3:

Description

A3:2017 Sensitive Data Exposure

The application saves the social security number in the plain text format.

How to fix it

In the method sec.project.controller.SignupController.submitForm use the commands:

TextEncryptor encryptor = Encryptors.delux("kddskRdls!klslsk", "5c0744940b5c369b");
ssn = encryptor.encrypt(ssn);

to encrypt the social security number.






-------------------



--------

Mooc: Skannaa onko joku kirjasto haavoittunut? Delete injektio tallennukseen. XSS tallennus ja uusi haku. Hetun yms. tallennus ja paljastus. Csrf XSS:ää? -> helppo tehdä

