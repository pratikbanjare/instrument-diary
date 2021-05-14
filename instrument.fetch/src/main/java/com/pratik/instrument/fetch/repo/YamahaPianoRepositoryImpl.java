package com.pratik.instrument.fetch.repo;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.pratik.instrument.fetch.config.MongoConfiguration;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;


@Repository
public class YamahaPianoRepositoryImpl implements YamahaPianoRepository {

    private static final Logger loggger = LoggerFactory.getLogger(YamahaPianoRepositoryImpl.class);

    @Autowired
    private MongoConfiguration mongoConfiguration;

    @Value("${mongodb.database.piano}")
    private String databaseName;

    @Value("${mongodb.collection.yamahaPiano}")
    private String collectionName;

    @Override
    public void addYamahaPianoInfo (Document pianoInfo) {

        MongoClient mongoClient = mongoConfiguration.getMongoClient();
        MongoDatabase mongoDatabase = mongoClient.getDatabase(databaseName);
        MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
        collection.insertOne(pianoInfo);
        mongoClient.close();

    }
}
