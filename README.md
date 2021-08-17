# test

Step 1:
install mysql and run the data.sql in resource package
======================================================

step 2:
After Starting the Server goto postman app 
Get all customers

GET  http://localhost:8080/api/search/customers
-------------------------
Get Customer by ID

GET  http://localhost:8080/api/search/customer/{id}
------------------------
Get Customer  Names

GET  http://localhost:8080/api/search/customernames
------------------------
Get by Age

GET  http://localhost:8080/api/search/customerAgeLT/25
GET  http://localhost:8080/api/search/customerAgeGT/25
GET  http://localhost:8080/api/search/customerAgeLTEq/25
GET  http://localhost:8080/api/search/customerAgeGTEq/25
GET  http://localhost:8080/api/search/customerAgeEq/25
=======================================
Add Employee

POST http://localhost:8080/api/employees
in body 
{
    "displayName":"Anil Kumar",
    "firstName":"Anil",
    "lastName":"Kumar",
    "email":"Anil@gmail.com"
}
Hereyou can perform validations for name length and mail format by chaning the length of the chars and mail format
------------------------------------
Get by Employee ID
GET http://localhost:8080/api/employees/{id}  // also check the null and not found exceptions
-----------------------------------
Get by Displayname
GET http://localhost:8080/api/employeesName/{name} // also check the null and not found exceptions
----------------------------------
delete a record using ID

DELETE http://localhost:8080/api/deleteemployee/{id}







