package com.frank.mapper.field.mongodb;

import com.frank.mapper.AbstractPersistenceMap;
import com.frank.mapper.DataMap;

public class EnumPersistenceMap extends AbstractPersistenceMap {

    public EnumPersistenceMap(String persistenceFieldName, String persistenceTypeName, String domainFieldName, DataMap dataMap) {
        super(persistenceFieldName, persistenceTypeName, domainFieldName, dataMap);
    }

    @Override
    public Object castToPersistenceValue(Object value) {
        return value.toString();
    }

    @Override
    public Object castToDomainValue(Object value) throws ClassNotFoundException {
        Object castValue = Enum.valueOf((Class<Enum>) Class.class.forName(persistenceTypeName), (String) value);
        return castValue;
    }

}
