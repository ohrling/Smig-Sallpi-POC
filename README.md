# Arrowhead integration
The endgoal of this parts is to be able to send data from an IIoT-device trough an adapter to a Provider in a Arrowhead-
cloud in another location.
## Adapter
The adapter part is a Spring boot application using Gradle as build tool. It starts a REST API with a POST endpoint
(/adapter) that takes a string-value. This string value is then forwarded to the datamanager provided by the Arrowhead-
cloud, the provided data is also returned the same way that it was and it will also print
the string-value to the console for verification.

**Notice that this is a PoC for a specific Use Case, therefore there is
hardcoded URL's and id's present in the code.** 


### Using the adapter
###### Prerequisites
- Java needs to be installed, v.1.8 seems to work but v.11 is recommended.
- Gradle needs to be installed https://gradle.org/
  
In CLI run `./gradlew bootRun`, works best with Git Bash or Powershell on Win.
Then just perform a POST-request to `http://localhost:8080/adapter` (If you run it on your computer that is.)
You should get the same message in the response, it will also be printed out in the console where the Spring boot
application is running.
In the CLI there will be logs printed displaying the status of the application, i.e when the system is registered
or when data is pushed on to the Arrowhead-provider.

If the Spring application needs to be shutdown it is possible to make a POST-request to the endpoint
`http://localhost:8080/actuator/shutdown` to do a graceful shutdown instead of needing to do the hard shutdown, Ctrl-c.

