package com.frank.persistence.mongodb.mapper;

import com.frank.context.book.Order;
import com.frank.persistence.api.DataMap;
import com.frank.persistence.api.EntityMapper;
import org.bson.Document;

public class OrderMapper implements EntityMapper {

    private static final String collectionName = "orders";
    private final DataMap dataMap;

    public OrderMapper(DataMap dataMap) {
        this.dataMap = dataMap;
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
