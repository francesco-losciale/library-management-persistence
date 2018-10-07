package com.frank.persistence;

import com.frank.context.book.Order;
import com.frank.mapper.datamap.mongodb.MongoDbDataMap;
import com.frank.mapper.maps.AbstractPersistenceMap;
import org.bson.Document;

public abstract class MongoDbMapper implements EntityMapper {

    protected final MongoDbDataMap dataMap;

    public MongoDbMapper(String collectionName) {
        this.dataMap = new MongoDbDataMap(Order.class, collectionName);
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
}
