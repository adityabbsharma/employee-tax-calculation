Steps to run the application:
1. Run Mysql server(prerequisite) and Set  the application.properties for the properties spring.datasource.username and spring.datasource.password
2. Run the application (default port 8080)

   
API-1(Save Employee):
URI: http://localhost:8080/employee/save 
Request method: POST,

Request Body Sample: 
{
  "firstname": "John", 
  "lastName": "Doe",
  "email": "string",
  "phoneNumbers": [
    "string"
  ],
  "dateOfJoining": "2024-03-02T12:45:51.575Z",
  "salaryPerMonth": 0
}
Validations: 
1. firstname must not be blank
2. firstname and lastName must contain only alphabets and spaces, without trailing spaces
3. Enter email in small case in proper email format
4. phoneNumbers is an array of strings
5. "dateOfJoining" can be of a simple date type too e.g "2022-03-01",
6. salaryPerMonth must be an interger value
----------------------------------------------------------------------------------------------
API-2(Get tax for the current year for the employee):
URI: http://localhost:8080/employee/currentyeartax/{employeeId}
Request method: GET,
Validations:
1. Pathvariable employeeId Must be a valid employee id


http://localhost:8080/swagger-ui/index.html
