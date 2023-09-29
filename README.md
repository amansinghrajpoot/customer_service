### Setup
Note:- If You have already setup JAVA_17 then skip this step
1. Java 17 Installation on macOS
   ```
   brew tap homebrew/cask-versions
   brew install --cask temurin17
   ```
2. Set JAVA_17 Env value. Follow step 2 [here](https://www.java.com/en/download/help/path.html)

How build & run this project?
### Running locally
Install and run DynamoDB on your local machine:

1. Install AWS CLI  and Configure
    1. Install AWS CLI Run following commands
        * `curl "https://awscli.amazonaws.com/AWSCLIV2.pkg" -o "AWSCLIV2.pkg"`
        * `sudo installer -pkg ./AWSCLIV2.pkg -target /`
    2. To verify AWS CLI Run following commands
        * `which aws`
        * `aws --version`
    3. Configure AWS CLI
        * `aws configure`
            - Enter AccessKey : "local"
            - Enter SecretKey : "12345"
            - Enter region : "us-west-2"
            - Enter output : "json"
    4. [Reference](https://docs.aws.amazon.com/cli/latest/userguide/getting-started-install.html)


2. Install DynamoDB

    1. [Download DynamoDB](https://s3.us-west-2.amazonaws.com/dynamodb-local/dynamodb_local_latest.tar.gz)
    2. Extract downloaded file
    3. navigate to the directory where you extracted DynamoDBLocal.jar, and enter the following command.
        * `java -Djava.library.path=./DynamoDBLocal_lib -jar DynamoDBLocal.jar -sharedDb`
    4. Verify DynamoDB
        * `aws dynamodb list-tables --endpoint-url http://localhost:8000`
    5. Create table for Customer
        * `aws dynamodb create-table --cli-input-json file://create-table.json --endpoint-url http://localhost:8000`
    6. [Reference](https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/DynamoDBLocal.DownloadingAndRunning.html)


3. Visualizing the DynamoDB Table data

    1. Download and Install NoSQL Workbench
        * [Click_here](https://s3.amazonaws.com/nosql-workbench/NoSQL%20Workbench-mac-3.3.0.dmg)
    2. Open NoSQL Workbench and Setup DynamoDB local connection
        * Click on `operation builder`
        * Click on `Add connection`
        * Click on `DynamoDB Local`
    3. [Reference](https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/workbench.settingup.html)

There are a few options to run the application (from more flexible to least)

1. You can just use the SpringBoot run functionality that is default in IntelliJ or SpringTools in Eclipse. 

2. Start the service with Maven.
    ```bash
    mvn spring-boot:run -Dspring.profiles.active=default
    ```

3. Package the jar file and run it

    ```bash
    mvn clean package
    java -jar grpc/target/cart-data-service-exec.jar
    ```

### Invoking CDaS

You can use your favourite REST client (e.g. [Postman](https://blog.postman.com/postman-now-supports-grpc/) 

Or you can use ```curl```

```bash
curl 
  -X POST 
  -H "Content-Type: application/json" 
  http://localhost:7979/v1/customerservice/addcustomer 
  -d '{<DATA>}'
```
```bash
curl \
  -X GET \
  -H "Content-Type: application/json" \
  "http://localhost:7979/v1/customerservice/getcustomer?id=<CUSTOMER_ID>"
```