package com.frank.repository;

import com.frank.capabilities.Hydratable;
import com.frank.capabilities.Repository;
import com.frank.context.book.Order;
import com.frank.persistence.OrderMapper;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.types.ObjectId;

public class MongoDbRepository implements Repository {

    private OrderMapper orderMapper = new OrderMapper();
    private final MongoClient mongoClient = new MongoClient("10.0.2.15");
    private final MongoDatabase mongoDatabase = mongoClient.getDatabase("library-management-database");
    private final MongoCollection<Document> mongoCollection = mongoDatabase.getCollection(this.orderMapper.getCollectionName());

    public Object add(Hydratable object) {
        Order order = Order.class.cast(object);
        Document document = this.orderMapper.convertToPersistence(order);
        mongoCollection.insertOne(document);
        ObjectId objectId = (ObjectId) document.get("_id");
        return objectId;
    }

    public Hydratable get(Object id) {
        Document document = mongoCollection.find(Filters.eq("_id", new ObjectId(id.toString()))).first();
        if (document != null) {
            return this.orderMapper.convertToDomain(document);
        }
        throw new RuntimeException("Document _id " + id + " not found");
    }

}
