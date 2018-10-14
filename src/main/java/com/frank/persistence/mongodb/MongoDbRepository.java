package com.frank.persistence.mongodb;

import com.frank.capability.Hydratable;
import com.frank.persistence.api.EntityMapper;
import com.frank.persistence.api.Repository;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.types.ObjectId;

public class MongoDbRepository implements Repository {

    private final MongoClient mongoClient;
    private final MongoDatabase mongoDatabase;

    public MongoDbRepository() {
        this.mongoClient = new MongoClient("10.0.2.15");
        this.mongoDatabase = mongoClient.getDatabase("library-management-database");
    }

    public Object add(Hydratable object, EntityMapper entityMapper) {
        final MongoCollection<Document> mongoCollection = getMongoCollection(entityMapper.getCollectionName());
        Hydratable domainObject = getDomainObject(entityMapper.getDomainClass());
        Document document = (Document)entityMapper.convertToPersistence(domainObject); // TODO improve: you should remove Document from here
        mongoCollection.insertOne(document);
        ObjectId objectId = (ObjectId) document.get("_id");
        return objectId;
    }

    public Hydratable get(Object id, EntityMapper entityMapper) {
        final MongoCollection<Document> mongoCollection = getMongoCollection(entityMapper.getCollectionName());
        Document document = mongoCollection.find(Filters.eq("_id", new ObjectId(id.toString()))).first();
        if (document != null) {
            return entityMapper.getDomainClass().cast(entityMapper.convertToDomain(document));
        }
        throw new RuntimeException("Document _id " + id + " not found");
    }

    private MongoCollection<Document> getMongoCollection(String collectionName) {
        return mongoDatabase.getCollection(collectionName);
    }

    private Hydratable getDomainObject(Class<? extends Hydratable> domainClass) {
        try {
            return domainClass.newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException("Implementation error: Not possible to instantiate the domain object", e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Implementation error: Not possible to instantiate the domain object", e);
        }
    }
}
