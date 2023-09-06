# GlobalLogic Application

Test exercise for GlobalLogic

## Previous requirements

Make sure you have the following installed before you begin:

- Java Development Kit (JDK) 8
- Gradle
- Git

Check if port 8092 is available

## Project configuration

Clone this repository to your local machine

Run this command in the main folder

.\gradlew clean build

.\gradlew run

Services will be available in the follow url: 

POST: http://localhost:8092/sign-up
POST: http://localhost:8092/login

If you want to use the login service, you should to get the jwtToken first in the sign-up service
and then put the token in the Authorization headers before you call the login

For diagrams, check the follow route in the project: /MicroserviceDemoApplication/diagrams

Contact: 
Patricio Berríos
patricio.berrios.m@gmail.com
Linkedin: https://www.linkedin.com/in/patricio-berrios-maureira/


