package com.frank.persistence.mongodb.map;

import com.frank.persistence.api.DataMap;
import org.bson.types.Decimal128;

public class BigDecimalPersistenceMap extends AbstractPersistenceMap {

    public BigDecimalPersistenceMap(String persistenceFieldName, String domainFieldName, DataMap dataMap) {
        super(persistenceFieldName, domainFieldName, dataMap);
    }

    @Override
    public Object castToPersistenceValue(Object value) {
        return Decimal128.parse(value.toString());
    }

    @Override
    public Object castToDomainValue(Object value) throws ClassNotFoundException {
        Object castValue = (Decimal128.class.cast(value)).bigDecimalValue();
        return castValue;
    }

}
