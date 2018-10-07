package com.frank.repository.mongodb;

import com.frank.capabilities.Hydratable;
import com.frank.capabilities.Repository;
import com.frank.context.book.Order;
import com.frank.persistence.EntityMapper;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.types.ObjectId;

public class MongoDbRepository implements Repository {

    private final EntityMapper entityMapper;
    private final MongoClient mongoClient;
    private final MongoDatabase mongoDatabase;
    private final MongoCollection<Document> mongoCollection;

    public MongoDbRepository(EntityMapper entityMapper) {
        this.entityMapper = entityMapper;
        this.mongoClient = new MongoClient("10.0.2.15");
        this.mongoDatabase = mongoClient.getDatabase("library-management-database");
        this.mongoCollection = mongoDatabase.getCollection(this.entityMapper.getCollectionName());
    }

    public Object add(Hydratable object) {
        Order order = Order.class.cast(object);
        Document document = (Document)this.entityMapper.convertToPersistence(order);
        mongoCollection.insertOne(document);
        ObjectId objectId = (ObjectId) document.get("_id");
        return objectId;
    }

    public Hydratable get(Object id) {
        Document document = mongoCollection.find(Filters.eq("_id", new ObjectId(id.toString()))).first();
        if (document != null) {
            return (Order)this.entityMapper.convertToDomain(document);
        }
        throw new RuntimeException("Document _id " + id + " not found");
    }

}
