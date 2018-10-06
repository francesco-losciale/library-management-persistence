package com.frank.repository;

import com.frank.capabilities.Hydratable;
import com.frank.context.book.Order;
import com.frank.context.book.OrderState;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import org.bson.Document;
import org.bson.types.Decimal128;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MongoDbRepository implements Repository {

    private final MongoClient mongoClient = new MongoClient("10.0.2.15");
    private final MongoDatabase mongoDatabase = mongoClient.getDatabase("library-management-database");

    public MongoDbRepository() {
        if (!isCollectionInDatabase("orders", mongoDatabase)) {
            mongoDatabase.createCollection("orders");
        }
    }

    public void add(Hydratable object) {
        Order order = Order.class.cast(object);
        Document document = new Document("price", order.getPrice())
                .append("state", (order.getState().isReceived())?"received":"not_received")
                .append("id", order.getId());
        mongoDatabase.getCollection("orders").insertOne(document);
    }

    public void remove(Hydratable id) {

    }

    public void remove(Object id) {

    }

    public Hydratable get(Object id) {
        Document document = mongoDatabase.getCollection("orders").findOneAndDelete(new Document("id", id));
        if (document != null) {
            Set<Map.Entry<String, Object>> entrySet = document.entrySet();
            final Hydratable hydratable = new Order();
            for (Map.Entry<String, Object> entry : entrySet.toArray(new Map.Entry[entrySet.size()])) {
                try {
                    if (!"_id".equals(entry.getKey())) {
                        Field field = hydratable.getClass().getDeclaredField(entry.getKey());
                        field.setAccessible(true);
                        Object value = castObject(entry.getValue());
                        if (entry.getKey().equals("state")) {
                            if ("received".equals(value)) {
                                OrderState state = new OrderState();
                                state.received();
                                value = state;
                            } else {
                                OrderState state = new OrderState();
                                value = state;
                            }

                        }
                        field.set(hydratable, value);
                    }
                } catch (NoSuchFieldException e) {
                    throw new RuntimeException("Implementation error: field " + entry.getKey() + " doesn't exists in the bean");
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Implementation error: field " + entry.getKey() + " not accessible from the bean");
                }
            }
            return hydratable;
        }
        throw new RuntimeException("Document id " + id + " not found");
    }

    public void update(Object id, Hydratable object) {

    }

    private boolean isCollectionInDatabase(String collectionName, MongoDatabase mongoDatabase) {
        MongoIterable<String> mongoIterable = mongoDatabase.listCollectionNames();
        Iterator iterator = mongoIterable.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().equals(collectionName)) {
                return true;
            }
        }
        return false;
    }

    private Object castObject(Object value) {
        if (Decimal128.class.isInstance(value)) {
            return BigDecimal.valueOf(((Decimal128)value).bigDecimalValue().doubleValue());
        }
        if (String.class.isInstance(value)) {
            return String.class.cast(value);
        }

        throw new RuntimeException("Implementation error: type " + value.getClass() + " not mapped from MongoDb to Java");
    }
}
