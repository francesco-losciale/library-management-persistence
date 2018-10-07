package com.frank.mapper.maps.mongodb;

import com.frank.mapper.datamap.DataMap;
import com.frank.mapper.maps.AbstractPersistenceMap;

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
