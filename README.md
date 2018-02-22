Overview
--------


 
Technologies
------------
- Spring Boot - To launch the application in tomcat.
- Spring Data - Java Persistence to communicate with DISC.
- Spring Boot CommandLineRunner - No endpoints created. This will run as batch and produce json file test report.
- Spring Profiles - only the default profile is use but could have more to handle different environments


Local Development Setup
-----------------------
1. unzip file to your folder:
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

    