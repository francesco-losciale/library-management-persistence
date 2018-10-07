package com.frank.persistence;

import com.frank.context.book.Order;
import com.frank.mapper.datamap.mongodb.MongoDbDataMap;
import org.bson.Document;

public class OrderMapper implements EntityMapper {

    private static final String collectionName = "orders";
    private final MongoDbDataMap dataMap;

    public OrderMapper() {
        this.dataMap = new MongoDbDataMap(Order.class, collectionName);
        this.dataMap.addBigDecimalField("price", "price");
        this.dataMap.addEnumField("state", "state");
        this.dataMap.addField("order_number", "orderNumber");
    }

    public String getCollectionName() {
        return collectionName;
    }

    public Document convertToPersistence(Object domainObject) {
        return this.dataMap.castToPersistent(domainObject);
    }

    public Order convertToDomain(Object persistenceObject) {
        return (Order)this.dataMap.castToDomain(persistenceObject);
    }
}
