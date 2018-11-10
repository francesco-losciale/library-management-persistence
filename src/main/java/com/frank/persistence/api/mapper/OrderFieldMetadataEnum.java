package com.frank.persistence.api.mapper;

public enum OrderFieldMetadataEnum {

    PRICE("price", "price"),
    STATE("state", "state"),
    ORDER_NUMBER("orderNumber", "order_number");

    private String entityFieldName;
    private String externalFieldName;

    OrderFieldMetadataEnum(String entityFieldName, String externalFieldName) {
        this.entityFieldName = entityFieldName;
        this.externalFieldName = externalFieldName;
    }

    public String getEntityFieldName() {
        return entityFieldName;
    }

    public String getExternalFieldName() {
        return externalFieldName;
    }
}

