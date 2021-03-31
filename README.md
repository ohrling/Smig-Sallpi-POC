# Arrowhead integration
The endgoal of this parts is to be able to send data from an IIoT-device trough an adapter to a Provider in a Arrowhead-cloud in another location.
## Adapter
The adapter part is a Spring boot application using Gradle as build tool. It starts a REST API with a POST endpoint
(/adapter) that takes a string-value. This string value is then returned the same way that it was and also it will print
the string-value to the console.

This first implementation is only for testing the connection from the IIoT-device.
### How to start the adapter
###### Prerequisites
- Java needs to be installed, v.1.8 seems to work but v.11 is recommended.
- Gradle needs to be installed https://gradle.org/
- Every developer need to specify a gradle.properties file in the root-folder. This need to contain the fields userName
  and userToken since they are using GitHub packages, more info: https://docs.github.com/en/packages/guides/configuring-apache-maven-for-use-with-github-packages
  
 In CLI run **./gradlew bootRun**, works best with Git Bash or Powershell on Win.
 Then just perform a POST request to http.//localhost:8080/adapter (If you run it on your computer that is.)
 You should get the same message in the response, it will also be printed out in the console where the Spring boot application is running.
