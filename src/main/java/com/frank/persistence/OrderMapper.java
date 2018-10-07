package com.frank.persistence;

public class OrderMapper extends MongoDbMapper {

    private static final String collectionName = "orders";

    public OrderMapper() {
        super(collectionName);
        this.dataMap.addBigDecimalField("price", "price");
        this.dataMap.addEnumField("state", "state");
        this.dataMap.addField("order_number", "orderNumber");
    }

    public String getCollectionName() {
        return collectionName;
    }

}
