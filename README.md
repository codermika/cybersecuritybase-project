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


FLAW 4:

Description

A7:2017 Cross-Site Scripting (XSS)

When the user inputs as the name the value

<script>alert("Hello!");</script>

in the last page the application runs the Javascript alert.

How to fix it

In the template done.html replace the attribute name

th:utext

with the

th:text

attribute name. The th:utext attribute is for the unescaped text and enables the XSS flaw.

  

-------------------
Pääsetkö katsomaan kannan sisältöä? Voi olla hyvä hetu salauksessa ja injektiossa.

Delete injektio tallennukseen

XSS tallennus ja uusi haku (ei tämäkään ole vaikea)
tai helpompi voisiko formin lähetyksen jälkeen olla GOOD BYE ja nimi (XSS:n kanssa)


-----

Jos tarvitsee tekstiä, niin esittele näytöt.

--------

Mooc: Skannaa onko joku kirjasto haavoittunut? Delete injektio tallennukseen. XSS tallennus ja uusi haku. Hetun yms. tallennus ja paljastus. Csrf XSS:ää? -> helppo tehdä

---

Windows:issa session id tulee url:iin molemmilla selaimilla

Käyttöjärjestelmä             Microsoft Windows 10 Pro
Versio    10.0.18362 Koontikäännös 18362

Firefox
71.0 (64-bittinen)

Google Chrome is up to date
Version 79.0.3945.88 (Official Build) (32-bit)

---
        // http.headers().xssProtection().disable();
        http.headers().disable();
        
------------------

kipa@kipa-HP-ENVY-6-Notebook-PC:~/mooc/cybersecuritybase-project$ mvn dependency-check:check
[INFO] Scanning for projects...
[INFO] 
[INFO] -------------------< sec:cybersecuritybase-project >--------------------
[INFO] Building cybersecuritybase-project 1.0-SNAPSHOT
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- dependency-check-maven:5.2.4:check (default-cli) @ cybersecuritybase-project ---
[INFO] Checking for updates
[INFO] Skipping NVD check since last check was within 4 hours.
[INFO] Skipping RetireJS update since last update was within 24 hours.
[INFO] Check for updates complete (45 ms)
[INFO] 

Dependency-Check is an open source tool performing a best effort analysis of 3rd party dependencies; false positives and false negatives may exist in the analysis performed by the tool. Use of the tool and the reporting provided constitutes acceptance for use in an AS IS condition, and there are NO warranties, implied or otherwise, with regard to the analysis or its use. Any use of the tool and the reporting provided is at the user’s risk. In no event shall the copyright holder or OWASP be held liable for any damages whatsoever arising out of or in connection with the use of this tool, the analysis performed, or the resulting report.


[INFO] Analysis Started
[INFO] Finished Archive Analyzer (4 seconds)
[INFO] Finished File Name Analyzer (0 seconds)
[INFO] Finished Jar Analyzer (5 seconds)
[INFO] Finished Dependency Merging Analyzer (0 seconds)
[INFO] Finished Version Filter Analyzer (0 seconds)
[INFO] Finished Hint Analyzer (0 seconds)
[INFO] Created CPE Index (10 seconds)
[INFO] Finished CPE Analyzer (16 seconds)
[INFO] Finished False Positive Analyzer (0 seconds)
[INFO] Finished NVD CVE Analyzer (23 seconds)
[INFO] Finished RetireJS Analyzer (0 seconds)
[INFO] Finished Sonatype OSS Index Analyzer (8 seconds)
[INFO] Finished Vulnerability Suppression Analyzer (0 seconds)
[INFO] Finished Dependency Bundling Analyzer (0 seconds)
[INFO] Analysis Complete (60 seconds)
[WARNING] 

One or more dependencies were identified with known vulnerabilities in cybersecuritybase-project:

logback-core-1.1.7.jar (pkg:maven/ch.qos.logback/logback-core@1.1.7, cpe:2.3:a:logback:logback:1.1.7:*:*:*:*:*:*:*) : CVE-2017-5929
tomcat-embed-core-8.5.6.jar (pkg:maven/org.apache.tomcat.embed/tomcat-embed-core@8.5.6, cpe:2.3:a:apache:tomcat:8.5.6:*:*:*:*:*:*:*, cpe:2.3:a:apache_software_foundation:tomcat:8.5.6:*:*:*:*:*:*:*, cpe:2.3:a:apache_tomcat:apache_tomcat:8.5.6:*:*:*:*:*:*:*) : CVE-2016-6816, CVE-2016-6817, CVE-2016-8735, CVE-2016-8745, CVE-2017-12617, CVE-2017-5647, CVE-2017-5648, CVE-2017-5650, CVE-2017-5651, CVE-2017-5664, CVE-2017-7674, CVE-2017-7675, CVE-2018-11784, CVE-2018-1304, CVE-2018-1305, CVE-2018-1336, CVE-2018-8014, CVE-2018-8034, CVE-2018-8037, CVE-2019-0199, CVE-2019-0221, CVE-2019-0232, CVE-2019-10072
hibernate-validator-5.2.4.Final.jar (pkg:maven/org.hibernate/hibernate-validator@5.2.4.Final, cpe:2.3:a:hibernate:hibernate-validator:5.2.4:*:*:*:*:*:*:*, cpe:2.3:a:redhat:hibernate_validator:5.2.4:*:*:*:*:*:*:*) : CVE-2017-7536
jackson-databind-2.8.4.jar (pkg:maven/com.fasterxml.jackson.core/jackson-databind@2.8.4, cpe:2.3:a:fasterxml:jackson:2.8.4:*:*:*:*:*:*:*, cpe:2.3:a:fasterxml:jackson-databind:2.8.4:*:*:*:*:*:*:*) : CVE-2017-15095, CVE-2017-17485, CVE-2017-7525, CVE-2018-1000873, CVE-2018-11307, CVE-2018-12022, CVE-2018-12023, CVE-2018-14718, CVE-2018-14719, CVE-2018-14720, CVE-2018-14721, CVE-2018-19360, CVE-2018-19361, CVE-2018-19362, CVE-2018-5968, CVE-2018-7489, CVE-2019-12086, CVE-2019-12384, CVE-2019-12814, CVE-2019-14379, CVE-2019-14439, CVE-2019-14540, CVE-2019-16335, CVE-2019-16942, CVE-2019-16943, CVE-2019-17267, CVE-2019-17531
spring-webmvc-4.3.4.RELEASE.jar (pkg:maven/org.springframework/spring-webmvc@4.3.4.RELEASE, cpe:2.3:a:pivotal_software:spring_framework:4.3.4.release:*:*:*:*:*:*:*, cpe:2.3:a:springsource:spring_framework:4.3.4.release:*:*:*:*:*:*:*, cpe:2.3:a:vmware:springsource_spring_framework:4.3.4:*:*:*:*:*:*:*) : CVE-2016-9878, CVE-2018-11039, CVE-2018-11040, CVE-2018-1199, CVE-2018-1257, CVE-2018-1270, CVE-2018-1271, CVE-2018-1272, CVE-2018-1275, CVE-2018-15756
ognl-3.0.8.jar (pkg:maven/ognl/ognl@3.0.8, cpe:2.3:a:ognl_project:ognl:3.0.8:*:*:*:*:*:*:*) : CVE-2016-3093
groovy-2.4.7.jar (pkg:maven/org.codehaus.groovy/groovy@2.4.7, cpe:2.3:a:apache:groovy:2.4.7:*:*:*:*:*:*:*) : CVE-2016-6814
dom4j-1.6.1.jar (pkg:maven/dom4j/dom4j@1.6.1, cpe:2.3:a:dom4j_project:dom4j:1.6.1:*:*:*:*:*:*:*) : CVE-2018-1000632
spring-data-commons-1.12.5.RELEASE.jar (pkg:maven/org.springframework.data/spring-data-commons@1.12.5.RELEASE, cpe:2.3:a:pivotal_software:spring_data_commons:1.12.5.release:*:*:*:*:*:*:*) : CVE-2018-1273
spring-security-web-4.1.3.RELEASE.jar (pkg:maven/org.springframework.security/spring-security-web@4.1.3.RELEASE, cpe:2.3:a:pivotal_software:spring_security:4.1.3.release:*:*:*:*:*:*:*) : CVE-2016-9879, CVE-2018-1199, CVE-2018-1258
spring-core-4.3.4.RELEASE.jar (pkg:maven/org.springframework/spring-core@4.3.4.RELEASE, cpe:2.3:a:pivotal_software:spring_framework:4.3.4.release:*:*:*:*:*:*:*, cpe:2.3:a:springsource:spring_framework:4.3.4.release:*:*:*:*:*:*:*, cpe:2.3:a:vmware:springsource_spring_framework:4.3.4:*:*:*:*:*:*:*) : CVE-2018-11039, CVE-2018-11040, CVE-2018-1199, CVE-2018-1257, CVE-2018-1270, CVE-2018-1271, CVE-2018-1272, CVE-2018-1275, CVE-2018-15756
spring-security-test-4.1.3.RELEASE.jar (pkg:maven/org.springframework.security/spring-security-test@4.1.3.RELEASE, cpe:2.3:a:pivotal_software:spring_security:4.1.3.release:*:*:*:*:*:*:*) : CVE-2018-1199, CVE-2018-1258
spring-security-core-4.1.3.RELEASE.jar (pkg:maven/org.springframework.security/spring-security-core@4.1.3.RELEASE, cpe:2.3:a:pivotal_software:spring_security:4.1.3.release:*:*:*:*:*:*:*) : BREACH attack possible in CSRF tokens, CVE-2016-9879, CVE-2018-1199, CVE-2018-1258
spring-boot-1.4.2.RELEASE.jar (pkg:maven/org.springframework.boot/spring-boot@1.4.2.RELEASE, cpe:2.3:a:pivotal_software:spring_boot:1.4.2.release:*:*:*:*:*:*:*) : CVE-2017-8046, CVE-2018-1196


See the dependency-check report for more details.


[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  02:32 min
[INFO] Finished at: 2019-12-21T17:24:14+02:00
[INFO] ------------------------------------------------------------------------
kipa@kipa-HP-ENVY-6-Notebook-PC:~/mooc/cybersecuritybase-project$
