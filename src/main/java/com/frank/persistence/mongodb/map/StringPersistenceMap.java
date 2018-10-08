package com.frank.persistence.mongodb.map;

import com.frank.persistence.api.DataMap;

public class StringPersistenceMap extends AbstractPersistenceMap {

    public StringPersistenceMap(String persistenceFieldName, String domainFieldName, DataMap dataMap) {
        super(persistenceFieldName, domainFieldName, dataMap);
    }

    @Override
    public Object castToPersistenceValue(Object value) {
        return value;
    }

    @Override
    public Object castToDomainValue(Object value) throws ClassNotFoundException {
        Object castValue = String.class.cast(value);
        return castValue;
    }

}
