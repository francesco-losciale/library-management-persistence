package com.frank.persistence;

import com.frank.context.book.Order;
import com.frank.mapper.AbstractPersistenceMap;
import com.frank.mapper.DataMap;
import org.bson.Document;

public class OrderMapper {

    private final DataMap dataMap;
    private final String collectionName;

    public OrderMapper() {
        this.collectionName = "orders";
        this.dataMap = new DataMap(Order.class, this.collectionName);
        this.dataMap.addBigDecimalField("price", org.bson.types.Decimal128.class.getName(), "price");
        this.dataMap.addEnumField("state", com.frank.context.book.OrderState.class.getName(), "state");
        this.dataMap.addField("order_number", java.lang.String.class.getName(), "orderNumber");
    }

    public Document convertToDocument(Order order) {
        final Document document = new Document();
        for (final AbstractPersistenceMap map : this.dataMap.getAbstractPersistenceMapList()) {
            document.append(map.getPersitenceFieldName(), map.getFieldValue(order));
        }
        return document;
    }

    public Order convertToOrder(Document document) {
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
