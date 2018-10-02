package com.frank.repository;

import com.frank.capabilities.Dehydrator;
import com.frank.capabilities.Hydratable;
import com.frank.capabilities.Hydrator;
import com.frank.context.book.Order;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.Decimal128;

import java.math.BigDecimal;

public class OrderRepository implements Hydrator, Dehydrator {

    private MongoDatabase mongoDatabase;

    public OrderRepository() {
        final MongoClient mongoClient = new MongoClient("10.0.2.15");
        mongoDatabase = mongoClient.getDatabase("library-management-database");
        mongoDatabase.createCollection("orders");
    }

    public void dehydrate(Hydratable hydratable) {
        Order order = Order.class.cast(hydratable);
        save(order, id(order));
    }

    public Hydratable hydrate(Object id) {
        return read(id);
    }

    private void save(Order order, Object id) {
        Document document = new Document("price", order.getBooksPrice())
                .append("receivedByCourier", order.getOrderState().isReceived())
                .append("id", id);
        mongoDatabase.getCollection("orders").insertOne(document);
    }

    private Order read(Object id) {
        final Order order = new Order();
        FindIterable<Document> orderFindIterable = mongoDatabase.getCollection("orders").find(new Document("id", id));
        for (Document document : orderFindIterable) {
            Boolean isReceivedByCourier = document.getBoolean("receivedByCourier");
            if (isReceivedByCourier) {
                order.getOrderState().received();
            }
            BigDecimal price = ((Decimal128)document.get("price")).bigDecimalValue();
            // TODO fix: you are not in the right context to write the price in Order
        }
        return order;
    }

    private Object id(Order order) {
        Object id = order.getId();
        if (id != null) {
            return id;
        }
        return "1"; // TODO generate
    }
}
