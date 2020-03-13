# springboot-microservices-kafka

Simple example of microservices with springboot.

## Requirements:
```
jdk 11
docker
docker-compose
```

### Run
1. Go to kafka-docker-compose folder, open terminal and run:
    ```
    docker-compose up -d
    ```
2. Import sample-random-generator-client-service to your IDE and run it.

3. Import sample-random-generator-service to your IDE and run it.

4. List of availavle urls:
    ```
   1. http://localhost:8100/random-generator-client/number?sync&randomThreshold=<maxRandomValue>
      Sends request to the random-generator service via restTemplate. 
      <maxRandomValue> - Integer number of max random value;
   
   2. http://localhost:8100/random-generator-client/number?sync&feign&randomThreshold=<maxRandomValue>
      Sends request to the random-generator service via feign client. 
      <maxRandomValue> - Integer number of max random value;
   
   3. http://localhost:8100/random-generator-client/number?async&randomThreshold=<maxRandomValue>
      Sends request to the random-generator service asynchronously via restTemplate. 
      <maxRandomValue> - Integer number of max random value;
   
   4. http://localhost:8100/random-generator-client/number?async&feign&randomThreshold=<maxRandomValue>
      Sends request to the random-generator service asynchronously via feign client. 
      <maxRandomValue> - Integer number of max random value;
   
   5. http://localhost:8100/random-generator-client/number?event&randomThreshold=<maxRandomValue>
      Sends request to the random-generator through message to kafka. 
      <maxRandomValue> - Integer number of max random value;