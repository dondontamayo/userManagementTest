Overview
--------

The user management test service is a standalone springboot service that can onle be executed as a batch process. It uses
a commandLinerunner interface to execute it's task and exit from springboot. This will test all endpoints functionality
and spit a json file into your nominated file system folder(located in application.yml).

 
Technologies
------------
- Spring Boot - To launch the application in tomcat.
- Spring Data - Java Persistence to communicate with DISC.
- Spring Boot CommandLineRunner - No endpoints created. This will run as batch and produce json file test report.
- Spring Profiles - only the default profile is use but could have more to handle different environments

Source url
------------

    1. Source codes are stored in https://github.com/dondontamayo/userManagementTest
    
Local Development Setup
-----------------------
1. unzip file to your folder OR clone git@github.com:dondontamayo/userManagement.git
    - modify your target folder in application.yml where the json file is going to be created
2. Build the project: "mvn package"
3. Run the application:
    
    From Maven:
   
        java -Dapplication-name=user-management-test -jar /user-management-test.jar

    From Intellij:
    
        Right click on Application class and "Run Application"
       
4. Testing

    run bash script included in this project:
    /user-management-test.sh   
    
    -> only applies to unix. This can run in a cron job that will test the application periodically and
        produce json files on every run.
        
5. Sample Test result json file:
            {
            	"testResult": [{
            		"testUrl": "http://localhost:8080/users",
            		"method": "PUT",
            		"httpStatus": 201,
            		"latency": -657000000
            	}, {
            		"testUrl": "http://localhost:8080/users/36",
            		"method": "POST",
            		"httpStatus": 200,
            		"latency": 253000000
            	}, {
            		"testUrl": "http://localhost:8080/users/36",
            		"method": "GET",
            		"httpStatus": 200,
            		"latency": 125000000
            	}, {
            		"testUrl": "http://localhost:8080/users?&page=0&size=3",
            		"method": "GET",
            		"httpStatus": 200,
            		"latency": 140000000
            	}, {
            		"testUrl": "http://localhost:8080/users/36",
            		"method": "DELETE",
            		"httpStatus": 204,
            		"latency": 203000000
            	}]
            }

    