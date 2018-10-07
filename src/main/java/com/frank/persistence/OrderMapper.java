package com.frank.persistence;

import com.frank.context.book.Order;
import com.frank.mapper.maps.AbstractPersistenceMap;
import com.frank.mapper.datamap.mongodb.MongoDbDataMap;
import org.bson.Document;

public class OrderMapper implements EntityMapper {

    private final MongoDbDataMap dataMap;
    private final String collectionName;

    public OrderMapper() {
        this.collectionName = "orders";
        this.dataMap = new MongoDbDataMap(Order.class, this.collectionName);
        this.dataMap.addBigDecimalField("price", org.bson.types.Decimal128.class.getName(), "price");
        this.dataMap.addEnumField("state", com.frank.context.book.OrderState.class.getName(), "state");
        this.dataMap.addField("order_number", java.lang.String.class.getName(), "orderNumber");
    }

    public Document convertToPersistence(Object domainObject) {
        Order order = Order.class.cast(domainObject);
        final Document document = new Document();
        for (final AbstractPersistenceMap map : this.dataMap.getAbstractPersistenceMapList()) {
            document.append(map.getPersitenceFieldName(), map.getFieldValue(order));
        }
        return document;
    }

    public Order convertToDomain(Object persistenceObject) {
        Document document = Document.class.cast(persistenceObject);
        final Order order = new Order();
        for (final AbstractPersistenceMap map : this.dataMap.getAbstractPersistenceMapList()) {
            map.setFieldValue(order, document.get(map.getPersitenceFieldName()));
        }
        return order;
    }

    public String getCollectionName() {
        return collectionName;
    }

}
