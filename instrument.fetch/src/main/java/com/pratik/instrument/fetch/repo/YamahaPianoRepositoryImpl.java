package com.pratik.instrument.fetch.repo;

import com.mongodb.client.*;
import com.pratik.instrument.fetch.config.MongoConfiguration;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;


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
    public Optional addYamahaPianoInfo (Document pianoInfo) {

        MongoClient mongoClient = mongoConfiguration.getMongoClient();
        MongoDatabase mongoDatabase = mongoClient.getDatabase(databaseName);
        MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
        collection.insertOne(pianoInfo);
        mongoClient.close();

        Map<String, Object> specMap = new HashMap<>();
        Set<String> keySet = pianoInfo.keySet();
        for (String key : keySet){
            specMap.put(key,pianoInfo.get(key));
        }
        return Optional.of(specMap);

    }
}
