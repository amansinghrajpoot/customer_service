package com.project.customerservice.config;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.project.customerservice.util.DynamoBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DynamoDBConfig {
    @Autowired
    private DynamoBuilder dynamoBuilder;

    @Bean
    public DynamoDBMapper getDynamoDBMapper(){
       return dynamoBuilder.getAWSDynamoDbClient();
    }
}
