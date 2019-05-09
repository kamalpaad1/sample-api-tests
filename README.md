# Sample API Testing
sample-api-tests repository contains needed basic setup to get quick understanding of API testing. This project uses the below mentioned resource URI to test the needed API operations/verbs

Test URL used: http://jsonplaceholder.typicode.com/

### Softwares and Requirements:

> - IDE: Eclipse Photon (4.8.0) - Optional
> - Language: Java 8
> - API testing: RestAssured
> - Reporting: Extent Reports
> - Build tool: Maven (3.0.0-M3)

> All other used dependencies are mentioned in the pom.xml file of the project
      
### Project Structure:
> - com.commons - This folder can comprise of commonly used java classes, here BaseTest.java contains setup, teardown and other needed testng methods  
> - com.utils - This folder is to comprise of commonly used utilities across tests, here TestUtils contains payload setup, hearder setup etc.
> - com.tests - This folder contains all the necessary tests (GET, PUT, POST, DELETE). Tests are segregarted according to the Rest operations to increase readability
> - resources/json-schemas - This folder contains the reference schemas for validation
> - Reports - This folder contains the user readble friendly extentTestReport.html

![Project Structure](https://github.com/kamalpaad1/sample-api-tests/blob/master/pics/projectStructure.png)

### Execution:

```
> - clone the git repository to local machine: git clone git@github.com/kamalpaad1/APITesting.git
> - Run Maven command: mvn clean install -Duser.Env=QA
Here user.Env is a parameter which accepts user environment (currently QA, Prod)

```

Success Case
![Success Scenario](https://github.com/kamalpaad1/sample-api-tests/blob/master/pics/successScenario.png)

Failure Case
![Error Scenario](https://github.com/kamalpaad1/sample-api-tests/blob/master/pics/errorScenario.png)

### Reporting:
When project build is success, then reports will be generated in Reports folder where extentTestReport.html will be created with test results

In Case of Sucess or Failure, information on each test scenario is available in details

Success Report:
![Success Report](https://github.com/kamalpaad1/sample-api-tests/blob/master/pics/successExtentReport.PNG)
      
Failure Report : (Deliberately in invalid posts scenario, 403 was mentioned instead of 404 for failure)

![Error Report](https://github.com/kamalpaad1/sample-api-tests/blob/master/pics/errorExtentReport.PNG)

Details of specific test case

![Error Details](https://github.com/kamalpaad1/sample-api-tests/blob/master/pics/extentReportDetails1.PNG)
