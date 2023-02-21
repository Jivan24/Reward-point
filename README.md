### Points Application

#### Question
```
A retailer offers a rewards program to its customers, awarding points based on each recorded
purchase.
 
A customer receives 2 points for every dollar spent over $100 in each transaction, plus 1 point for
every dollar spent between $50 and $100 in each transaction.
(e.g. a $120 purchase = 2x$20 + 1x$50 = 90 points).
```

#### Basic Structure

```
This project is created using spring initializer website. 
I am using spring 2.7.4 starter pom with java 17 and maven. I am using H2 as in memory database for storing the entities.
For documentation of APIs I am using swagger, for monitoring I have added actuatror endpoint.
```

Package Names :<br>
`controller` : All controller classes<br>
`entity` : Database object<br>
`actuator` : Custom health endpoint<br>
`exception` : Exception classes and global exception handler <br>
`model` : API request and Response objects<br>
`repository` : JPA Repositories<br>
`service` : Service classes<br>
`util` : Utility class

Steps to import the application:
```
You can import the application in intellij, eclipse or STS. 
And you can start the application by starting the main method in class PointsApplication
```

Once the application is up you can access the application on these URLs:

`Swagger URL`: http://localhost:8080/swagger-ui.html

`Health URL`: http://localhost:8080/actuator/health

I am initially loading the data for user with ids 257 and 258.
So we can get the points for these users using the API:

`http://localhost:8080/api/points/257`

Response for this URL is:
```
{
  "userId": 257,
  "points": {
    "Feb-2023": 230,
    "Dec-2022": 160,
    "Jan-2023": 140
  },
  "total": 530
}
```