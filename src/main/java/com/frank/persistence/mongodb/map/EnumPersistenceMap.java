package com.frank.persistence.mongodb.map;

import com.frank.persistence.api.DataMap;

public class EnumPersistenceMap extends AbstractPersistenceMap {

    public EnumPersistenceMap(String persistenceFieldName, String domainFieldName, DataMap dataMap) {
        super(persistenceFieldName, domainFieldName, dataMap);
    }

    @Override
    public Object castToPersistenceValue(Object value) {
        return value.toString();
    }

    @Override
    public Object castToDomainValue(Object value) throws ClassNotFoundException {
        Object castValue = Enum.valueOf((Class<Enum>) this.field.getType(), (String) value);
        return castValue;
    }

}
