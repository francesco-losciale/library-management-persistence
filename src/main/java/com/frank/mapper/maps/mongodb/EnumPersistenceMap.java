package com.frank.mapper.maps.mongodb;

import com.frank.mapper.datamap.DataMap;
import com.frank.mapper.maps.AbstractPersistenceMap;

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
