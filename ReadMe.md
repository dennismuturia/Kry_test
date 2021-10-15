# KRY test Submission backend
While on the root of this project run docker-compose up --build

The application runs on port 8093

##Requests
when running the base url for running the request is http://localhost:8093/api/**. There are a couple of requests in the application.
1. baseurl + /login  and base url + /register: These urls are responsible for user authentication and registration respectively. They are both POST requests and thir body carries the USer object and responsd with a user object coupled with a token for validation for the rest of the application. Here is an example of a login
```curl --location --request POST 'http://localhost:8093/api/login' --header 'Content-Type: application/json' \--data-raw '{"email": "dmuturia2922@gmail.com","password": "hello"}'```

First you must register for you to use the service below is an example for registering
   
``` curl --location --request POST 'http://localhost:8093/api/register' --header 'Content-Type: application/json' --data-raw '{"email": "dmuturia@gmail.com","password": "hello"}'```   
   
   
    
2. baseurl + /createservice this url is a POST url and when a service is successfully created, it responds with a Service Object.
```curl --location --request POST 'http://localhost:8093/api/2/createservice/1634299745037dmuturia@gmail.com' --header 'Content-Type: application/json'--data-raw '{"serviceName":"test1","url":"google.com","servicePort": ""}'```


3. baseurl + /deleteService this url is also a POST  url. It returns a boolean once the operation is conducted
```curl --location --request POST 'http://localhost:8093/api/2/deleteservice/1634299745037dmuturia@gmail.com' --header 'Content-Type: application/json'--data-raw '{"serviceName":"test1","url":"google.com","servicePort": ""}'``````
4. baseurl + /allservices this GET url returns a list of service objects .
```curl --location --request GET 'http://localhost:8093/api/2/services/1634299745037dmuturia@gmail.com'--header 'Authorization: Basic bXktdHJ1c3RlZC1jbGllbnQ6dXNlcg==' \--header 'Content-Type: application/json' \--data-raw '{"token": "1634136245049dmuturia2922@gmail.com"}'```

After every second a Schedule task runs in the background pinging the servers that are available in the database and updating them on their status.



