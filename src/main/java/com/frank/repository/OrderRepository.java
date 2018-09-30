package com.frank.repository;

import com.frank.book.Order;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class OrderRepository {

    private MongoDatabase mongoDatabase;

    public OrderRepository() {
        final MongoClient mongoClient = new MongoClient("10.0.2.15");
        mongoDatabase = mongoClient.getDatabase("library-management-database");
        mongoDatabase.createCollection("orders");
    }

    public void save(Order order, Object id) {
        Document document = new Document("price", order.getBooksPrice())
                .append("state", order.getOrderState())
                .append("id", id);
        mongoDatabase.getCollection("orders").insertOne(document);
    }

    public Order read(Object id) {
        final Order order = new Order();
        FindIterable<Document> orderFindIterable = mongoDatabase.getCollection("orders").find(new Document("id", id));
        for (Document document : orderFindIterable) {
            order.hydrateFrom(document);
        }
        return order;
    }
}
