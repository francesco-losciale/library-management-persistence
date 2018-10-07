package com.frank.mapper.maps.mongodb;

import com.frank.mapper.maps.AbstractPersistenceMap;
import com.frank.mapper.datamap.mongodb.MongoDbDataMap;

public class StringPersistenceMap extends AbstractPersistenceMap {

    public StringPersistenceMap(String persistenceFieldName, String persistenceTypeName, String domainFieldName, MongoDbDataMap dataMap) {
        super(persistenceFieldName, persistenceTypeName, domainFieldName, dataMap);
    }

    @Override
    public Object castToPersistenceValue(Object value) {
        return value;
    }

    @Override
    public Object castToDomainValue(Object value) throws ClassNotFoundException {
        Object castValue = Class.forName(persistenceTypeName).cast(value);
        return castValue;
    }

}
