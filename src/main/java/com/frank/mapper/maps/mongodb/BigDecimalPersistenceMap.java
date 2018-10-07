package com.frank.mapper.maps.mongodb;

import com.frank.mapper.maps.AbstractPersistenceMap;
import com.frank.mapper.datamap.mongodb.MongoDbDataMap;
import org.bson.types.Decimal128;

public class BigDecimalPersistenceMap extends AbstractPersistenceMap {

    public BigDecimalPersistenceMap(String persistenceFieldName, String persistenceTypeName, String domainFieldName, MongoDbDataMap dataMap) {
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
