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
        * `aws dynamodb create-table --cli-input-json file://customer-table-definition.json --endpoint-url http://localhost:8000`
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
    mvn spring-boot:run -Dspring.profiles.active=local
    ```

3. Package the jar file and run it

```bash
mvn clean package
java -jar ./target/customer-service-exec.jar --spring.profiles.active=local
```

### Invoking Customer Service

You can use your favourite REST client (e.g. [Postman](https://blog.postman.com/postman-now-supports-grpc/))

Or you can use ```curl```

```bash
curl \
  -X POST \
  -H "Content-Type: application/json" \
  -H "client-id: test" \
  -H "Authorization: Bearer <JWT TOKEN>" \
  http://localhost:7979/v1/customerservice/addcustomer \
  -d '{<DATA>}'
```
```bash
curl \
  -X GET \
  -H "Content-Type: application/json" \
  -H "client-id: test" \
  -H "Authorization: Bearer <JWT TOKEN>" \
  "http://localhost:7979/v1/customerservice/getcustomer?id=johndoe"
```
```bash
curl \
  -X GET \
  -H "Content-Type: application/json" \
  -H "client-id: test" \
  -H "Authorization: Bearer <JWT TOKEN>" \
  "http://localhost:7979/v1/customerservice/deletecustomer?id=johndoe"
```
```bash
curl \
  -X PUT -i \
  -H "client-id: test" \
  -H "Authorization: Bearer <JWT TOKEN>" \
   http://localhost:7979/v1/customerservice/updatecustomer \  
  -d '{<DATA>}'      
```
You can bypass Authentication in local environment by sending header "client-id: test"
```bash
curl \
  -X POST -i \
  -H "Content-Type: application/json" \
  -H "client-id: test" \
  http://localhost:7979/v1/customerservice/addcustomer \
  -d '{<DATA>}'
```
```bash
curl \
  -X GET -i \
  -H "Content-Type: application/json" \
  -H "client-id: test" \
  "http://localhost:7979/v1/customerservice/getcustomer?id=johndoe"
```
```bash
curl \
  -X GET -i \
  -H "Content-Type: application/json" \
  -H "client-id: test" \
  "http://localhost:7979/v1/customerservice/deletecustomer?id=johndoe"
```
```bash
curl \
  -X PUT -i \
  -H "Content-Type: application/json" \
  -H "client-id: test" \
  http://localhost:7979/v1/customerservice/updatecustomer \        
  -d '{<DATA>}'
```
Or send this JWT on local environment
```bash
curl \
  -X POST -i \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJpYXQiOjE2OTc0NTA0NTksImlzcyI6Ind3dy5hY21lLmNvbSIsInN1YiI6ImYxZTMzYWIzLTAyN2YtNDdjNS1iYjA3LThkZDhhYjM3YTJkMyJ9.A2zgvSQ6IK4W0hRnDlPkEMVEMmz6fGGQI-6qzT20PSVS8auoXA7FPA67yK-t-YtXWE2qOsSuzgWWb1TyD0zpS2IZNiJ03augB2zRSYq6cOGVyP5wfllGxJMjc7Bpje6chqOqUetG_2H0I8tnp-qd0GBbShCmK7-ZwV1WXQR6b7fyzSqAxf13TR4gCMiD1A8AnkVJqXlEGJBejhy3ooQ24osxo3QMoWpHFR-8Jr33-9USs8_zesBUdRqEbJLDU_eVBiCDZEOipPHF6o_ZmW1emBQLRc7d24f6W2omi5kzHN4npiOraFkaNeS1QeasOM002GcoHo4ClJslUME_3sEVnw" \
  http://localhost:7979/v1/customerservice/addcustomer \
  -d '{<DATA>}'
```
```bash
curl \
  -X GET -i \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJpYXQiOjE2OTc0NTA0NTksImlzcyI6Ind3dy5hY21lLmNvbSIsInN1YiI6ImYxZTMzYWIzLTAyN2YtNDdjNS1iYjA3LThkZDhhYjM3YTJkMyJ9.A2zgvSQ6IK4W0hRnDlPkEMVEMmz6fGGQI-6qzT20PSVS8auoXA7FPA67yK-t-YtXWE2qOsSuzgWWb1TyD0zpS2IZNiJ03augB2zRSYq6cOGVyP5wfllGxJMjc7Bpje6chqOqUetG_2H0I8tnp-qd0GBbShCmK7-ZwV1WXQR6b7fyzSqAxf13TR4gCMiD1A8AnkVJqXlEGJBejhy3ooQ24osxo3QMoWpHFR-8Jr33-9USs8_zesBUdRqEbJLDU_eVBiCDZEOipPHF6o_ZmW1emBQLRc7d24f6W2omi5kzHN4npiOraFkaNeS1QeasOM002GcoHo4ClJslUME_3sEVnw" \
  "http://localhost:7979/v1/customerservice/getcustomer?id=johndoe"
```
```bash
curl \
  -X GET -i \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJpYXQiOjE2OTc0NTA0NTksImlzcyI6Ind3dy5hY21lLmNvbSIsInN1YiI6ImYxZTMzYWIzLTAyN2YtNDdjNS1iYjA3LThkZDhhYjM3YTJkMyJ9.A2zgvSQ6IK4W0hRnDlPkEMVEMmz6fGGQI-6qzT20PSVS8auoXA7FPA67yK-t-YtXWE2qOsSuzgWWb1TyD0zpS2IZNiJ03augB2zRSYq6cOGVyP5wfllGxJMjc7Bpje6chqOqUetG_2H0I8tnp-qd0GBbShCmK7-ZwV1WXQR6b7fyzSqAxf13TR4gCMiD1A8AnkVJqXlEGJBejhy3ooQ24osxo3QMoWpHFR-8Jr33-9USs8_zesBUdRqEbJLDU_eVBiCDZEOipPHF6o_ZmW1emBQLRc7d24f6W2omi5kzHN4npiOraFkaNeS1QeasOM002GcoHo4ClJslUME_3sEVnw" \
  "http://localhost:7979/v1/customerservice/deletecustomer?id=johndoe"
```
```bash
curl \
  -X PUT -i \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJpYXQiOjE2OTc0NTA0NTksImlzcyI6Ind3dy5hY21lLmNvbSIsInN1YiI6ImYxZTMzYWIzLTAyN2YtNDdjNS1iYjA3LThkZDhhYjM3YTJkMyJ9.A2zgvSQ6IK4W0hRnDlPkEMVEMmz6fGGQI-6qzT20PSVS8auoXA7FPA67yK-t-YtXWE2qOsSuzgWWb1TyD0zpS2IZNiJ03augB2zRSYq6cOGVyP5wfllGxJMjc7Bpje6chqOqUetG_2H0I8tnp-qd0GBbShCmK7-ZwV1WXQR6b7fyzSqAxf13TR4gCMiD1A8AnkVJqXlEGJBejhy3ooQ24osxo3QMoWpHFR-8Jr33-9USs8_zesBUdRqEbJLDU_eVBiCDZEOipPHF6o_ZmW1emBQLRc7d24f6W2omi5kzHN4npiOraFkaNeS1QeasOM002GcoHo4ClJslUME_3sEVnw" \
  http://localhost:7979/v1/customerservice/updatecustomer \        
  -d '{<DATA>}'
```
Sample Data for POST and PUT requests
```json
{"username": "johndoe", "firstName": "John", "lastName": "Doe", "address": "123 Main Street", "city": "Anytown", "pincode": "12345", "email": "johndoe@example.com"}
```

### JWT token algo

```json
{
  "alg": "RS256",
  "typ": "JWT"
}
```
Use local-customer-service-public.pem to encode the token on local machine. 