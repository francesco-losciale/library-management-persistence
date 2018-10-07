package com.frank.mapper.field.mongodb;

import com.frank.mapper.AbstractPersistenceMap;
import com.frank.mapper.DataMap;

public class StringPersistenceMap extends AbstractPersistenceMap {

    public StringPersistenceMap(String persistenceFieldName, String persistenceTypeName, String domainFieldName, DataMap dataMap) {
        super(persistenceFieldName, persistenceTypeName, domainFieldName, dataMap);
    }

    public Object castToPersistenceValue(Object value) {
        return value;
    }

    public Object castToDomainValue(Object value) throws ClassNotFoundException {
        Object castValue = Class.forName(persistenceTypeName).cast(value);
        return castValue;
    }

}
