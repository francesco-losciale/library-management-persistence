package com.frank.persistence.api.mapper;

import com.frank.capability.Hydratable;
import com.frank.persistence.api.DataMap;
import com.frank.persistence.api.EntityMapper;

public class Mapper extends AbstractEntityMapper implements EntityMapper {

    private final DataMap dataMap;

    public Mapper(DataMap dataMap) {
        super(dataMap.getDomainClass(), dataMap.getPersistenceClass());
        this.dataMap = dataMap;
        this.dataMap.addBigDecimalField("price", "price");
        this.dataMap.addEnumField("state", "state");
        this.dataMap.addField("order_number", "orderNumber");
    }

    public String getCollectionName() {
        return this.dataMap.getCollectionName();
    }

    public Object convertToPersistence(Object domainObject) {
        return this.dataMap.castToPersistent(domainObject);
    }

    public Hydratable convertToDomain(Object persistenceObject) {
        return (Hydratable)this.dataMap.castToDomain(persistenceObject);
    }
}
