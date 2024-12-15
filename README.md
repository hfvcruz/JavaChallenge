# Java Challenge_2024

RESTful API that provides the basic functionalities of a calculator.
- Exposes the following operations: sum, subtraction, multiplication and division.
- Support for 2 operands only (a and b).
- Support to arbitrary precision signed decimal numbers.

## **Project Modules:**
## **'rest'**

Exposes the RESTful API on port 8080 (can be changed on [application.properties](https://github.com/hfvcruz/JavaChallenge/blob/main/rest/src/main/resources/application.properties), property `server.port`.  

**Examples of API methods:**

### *sum*
Example of an HTTP request for the operation 1 + 2:

```code
GET /sum?a=1&b=2 HTTP/1.1
Accept: application/json
(…)
```

Example of a response for the above request:

```code
HTTP/1.1 200 OK
Content-Type: application/json
(…)
{
    "result": 3
}
```

### *subtraction*
Example of an HTTP request for the operation 5 - 1:

```code
GET /subtraction?a=5&b=1 HTTP/1.1
Accept: application/json
(…)
```

Example of a response for the above request:

```code
HTTP/1.1 200 OK
Content-Type: application/json
(…)
{
    "result": 4
}
```

### *multiplication*
Example of an HTTP request for the operation 3 * 2:

```code
GET /multiplication?a=3&b=2 HTTP/1.1
Accept: application/json
(…)
```

Example of a response for the above request:

```code
HTTP/1.1 200 OK
Content-Type: application/json
(…)
{
    "result": 6
}
```

### *division*
Example of an HTTP request for the operation 6 / 2:

```code
GET /division?a=6&b=2 HTTP/1.1
Accept: application/json
(…)
```

Example of a response for the above request:

```code
HTTP/1.1 200 OK
Content-Type: application/json
(…)
{
    "result": 3
}
```

Example of a response if value of 'b' is zero:

```code
HTTP/1.1 400 BAD REQUEST
Content-Type: application/json
(…)
```

## **'calculator'**

Receive requests from API module with the calculation to perform, performs calculation and return the result to API module.  
The communication between the two modules is made with [Apache Kafka](https://kafka.apache.org/)

## **'model'**

This module contains the common classes to all modules (e.g. DTO classes).

## **Instalation:**

To install Project Title, follow these steps:

1. Clone the repository: **`git clone https://github.com/hfvcruz/JavaChallenge.git`**
2. Navigate to the project directory: **`cd JavaChallenge`**
3. Install [Gradle](https://gradle.org/install/)
4. Apache Kafka must be installed and running on local machine, using zookeper. [Apache Kafka installation steps](https://kafka.apache.org/quickstart)
5. Build the project 'calculator': **`./gradlew :calculator:build`** 
5. Build the project 'rest': **`./gradlew :rest:build`** 
5. Start the project 'calculator': **`java -jar ./calculator/build/libs/calculator-1.0.jar`** 
5. Start the project 'rest': **`java -jar ./rest/build/libs/rest-1.0.jar`**
   
Apache Kafka runs on default port 9092. Can be changed on [application.properties](https://github.com/hfvcruz/JavaChallenge/blob/main/rest/src/main/resources/application.properties), property `kafka.bootstrap-servers`.  


