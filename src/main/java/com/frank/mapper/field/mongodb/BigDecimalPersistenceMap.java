package com.frank.mapper.field.mongodb;

import com.frank.mapper.AbstractPersistenceMap;
import com.frank.mapper.DataMap;
import org.bson.types.Decimal128;

public class BigDecimalPersistenceMap extends AbstractPersistenceMap {

    public BigDecimalPersistenceMap(String persistenceFieldName, String persistenceTypeName, String domainFieldName, DataMap dataMap) {
        super(persistenceFieldName, persistenceTypeName, domainFieldName, dataMap);
    }

    @Override
    public Object castToPersistenceValue(Object value) {
        return Decimal128.parse(value.toString());
    }

    @Override
    public Object castToDomainValue(Object value) throws ClassNotFoundException {
        Object castValue = ((Decimal128) Class.forName(this.persistenceTypeName).cast(value)).bigDecimalValue();
        return castValue;
    }

}
