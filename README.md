| Lib/framework| |
| ----------- | ----------- |
| Spring Boot | Very easy to build applications. Autoconfiguration for almost every component.|
| Rabbit | Message broker really strong. Asynchronous communication, mainly with the mailer-service in this project. |
| Docker-compose| Build and run containers. Easy way to build an architecture.|
| Feign| Write client request as if it were a controller. It is very simple to understand what you are doing in a declarative way.|
| Sleuth| Generates a unique correlation id for each request. Great to be able to understand the flow of a request between microservices behind this identifier and logs.|
| Eureka| Service discovery and load balancer for services. Can be used to raise more instances of an application.|
| MySQL| Powerful database system and very simple to configure. I believe it is quite suitable for our case.|
| Prometheus| Metrics provider already integrated with spring boot. We were able to create custom metrics and thus create alerts using Grafana, for example.|
| Jacoco | Check test coverage level in maven build phases. Used in CI/CL to stop the pipeline when it fails.|

### Usage
1. Build Maven projects with using command: `mvn clean package`
2. Build Docker images for each module using command, for example: `docker-compose build`
3. Run projects with: `docker-compose up -d`
4. Check logs with `docker-compose logs -f`


#### Create Subscription
```json
curl --location --request POST 'http://localhost:8080/api/v1/subscriptions' \
--header 'Content-Type: application/json' \
--data-raw '{
  "consent": true,
  "date_of_birth": "10-10-2021",
  "email": "rafael@gmail.com",
  "first_name": "string",
  "gender": "MALE",
  "newsletter_id": 1
}'
   ```

#### Cancel Subscription
```json
curl --location --request DELETE 'http://localhost:8080/api/v1/subscriptions/1'
   ```

#### Get all subscriptions
```json
curl --location --request GET 'http://localhost:8080/api/v1/subscriptions'
   ```


#### Get subscription by id
```json
curl --location --request GET 'http://localhost:8080/api/v1/subscriptions/1'
   ```


### Subscription BFF
- [x] Sync controllers
- [x] Dockerfile
- [x] Docker-compose
- [X] Unit tests
- [x] Swagger
- [x] Jacoco
- [x] Feign
- [x] Bean validation
- [ ] Cache
- [ ] Pagination
- [X] Handle exception
- [X] Controller Advise
- [ ] Environment configuration
- [X] Tracing
- [X] Logs
- [ ] Integration tests
- [ ] Security
- [ ] Gateway
- [ ] Client credentials
- [X] Load balancer
- [ ] Secrets
- [x] Metrics 
- [ ] Reusable lib
- [ ] Circuit break
- [ ] Minimal JRE
- [ ] Class Data Sharing

### Subscription Api

- [x] Sync controllers
- [x] Database model
- [x] Dockerfile
- [x] Docker-compose
- [x] Queue producer
- [x] Bean validation
- [X] Unit tests
- [x] Swagger
- [x] Jacoco
- [ ] Cache
- [ ] Pagination
- [X] Handle exception
- [X] Centralized messages
- [ ] Controller Advise
- [X] Environment configuration
- [X] Tracing
- [X] Logs
- [ ] Integration tests
- [ ] Security
- [ ] Gateway
- [ ] Client credentials
- [x] Load balancer
- [ ] Secrets
- [x] Metrics 
- [ ] Reusable lib
- [ ] Circuit break
- [ ] Minimal JRE
- [ ] Class Data Sharing

### Mailer service

- [x] Dockerfile
- [x] Docker-compose
- [x] Queue consumer
- [X] Unit tests
- [x] Jacoco
- [ ] Handle exception
- [ ] Environment configuration
- [ ] Cache
- [X] Tracing
- [X] Logs
- [ ] Integration tests
- [ ] Security
- [ ] Gateway
- [ ] Client credentials
- [ ] Secrets
- [ ] Reusable lib
- [ ] Circuit break
- [ ] Minimal JRE
- [ ] Class Data Sharing

### MySql

- [x] Docker-compose
- [ ] Environment configuration
- [ ] Security
- [ ] Gateway
- [ ] Client credentials
- [ ] Load balancer
- [ ] Secrets


### RabbitMQ

- [x] Docker-compose
- [ ] Environment configuration
- [ ] Security
- [ ] Gateway
- [ ] Client credentials
- [ ] Load balancer
- [ ] Secrets
