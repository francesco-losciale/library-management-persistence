package com.frank.persistence.api.mapper;

import com.frank.entity.Hydratable;
import com.frank.persistence.api.DataMap;
import com.frank.persistence.api.EntityMapper;

public class OrderMapper extends AbstractEntityMapper implements EntityMapper {

    private final DataMap dataMap;

    public OrderMapper(DataMap dataMap) {
        super(dataMap.getDomainClass(), dataMap.getPersistenceClass());
        this.dataMap = dataMap;
        this.dataMap.addBigDecimalField(OrderFieldMetadataEnum.PRICE.getExternalFieldName(), OrderFieldMetadataEnum.PRICE.getEntityFieldName());
        this.dataMap.addEnumField(OrderFieldMetadataEnum.STATE.getExternalFieldName(), OrderFieldMetadataEnum.STATE.getEntityFieldName());
        this.dataMap.addField(OrderFieldMetadataEnum.ORDER_NUMBER.getExternalFieldName(), OrderFieldMetadataEnum.ORDER_NUMBER.getEntityFieldName());
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
