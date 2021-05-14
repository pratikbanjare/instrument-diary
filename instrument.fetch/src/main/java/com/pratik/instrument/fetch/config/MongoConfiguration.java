package com.pratik.instrument.fetch.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(MongoConfiguration.class);

    @Value("${mongodb.username}")
    private String username;

    @Value("${mongodb.password}")
    private String password;

    @Value("${mongodb.host}")
    private String host;

    @Value("${mongodb.port}")
    private String port;


    public MongoClient getMongoClient () {

        String URI = "mongodb://" + username + ":" + password + "@" + host + ":" + port;

        logger.info(URI);


        MongoClient mongoClient = MongoClients.create(URI);

        return mongoClient;
    }
}
