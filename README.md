NUMBER CONVERTERS

Prerequisites

    - Java 8
    - Maven cli
    - Postman for test the endpoints

Running the project
For running the project download the code from the following repository:
https://github.com/nexushamer/numbers-to-words/archive/master.zip

Unzip the file in any location that you desire and open the location with a Terminal or Cmd console.

With your Terminal/CMD console use the command 'cd' to browse to the folder where you unzip the code,
this can be done with the following command:
cd folderWhereYouUnzipTheCode

For execute the application with mvn run the following commands:
 - mvn compile -> this will compile the code
 - mvn spring-boot:run -> this will start the project with the maven command

For executing the application jar file run the following commands:
 - mvn clean -> this will clean the project
 - mvn compile -> this will compile the code
 - mvn package -> this will generate the jar file in the target folder

Finally execute the jar file with the following command:
 - java -jar target/numbers-to-english-words-0.0.1-SNAPSHOT.jar

Now the service is running

Running the test
Execute the command 'mvn test' for running the unit testing

Deployment
Execute the command 'mvn package' for generate the jar file

How to test the API in local
After start the project with maven or start the jar file the project is running in the port 8089.

For test the service you can use any rest client like Postman or SoapUI, also you can copy the
url of the service and test it in a browser like google chrome.

The url of the service is the following:
http://localhost:8089/online-converter/services/api/numbers-converter/{replaceThisWithANumber}

You have to replace the pathVariable {replaceThisWithANumber} with any number that you want to test,
For example:

Example with positive number
http://localhost:8089/online-converter/services/api/numbers-converter/12458

Example with negative number
http://localhost:8089/online-converter/services/api/numbers-converter/-138

How to test the API in AWS
The service is deployed in AWS in the following URL:
http://Numberstoenglishwords-env.gy2tsxbcek.us-west-2.elasticbeanstalk.com/online-converter/services/api/numbers-converter/{replaceThisWithANumber}

You have to replace the pathVariable {replaceThisWithANumber} with any number that you want to test,
For example:

Example with positive number
http://Numberstoenglishwords-env.gy2tsxbcek.us-west-2.elasticbeanstalk.com/online-converter/services/api/numbers-converter/12458

Example with negative number
http://Numberstoenglishwords-env.gy2tsxbcek.us-west-2.elasticbeanstalk.com/online-converter/services/api/numbers-converter/-138

