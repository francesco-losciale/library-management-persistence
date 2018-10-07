package com.frank.mapper.maps;

import com.frank.mapper.datamap.DataMap;
import com.frank.mapper.datamap.mongodb.MongoDbDataMap;

public class PersistenceMapFactory {

    public AbstractPersistenceMap createMongoDbBigDecimalPersistenceMap(String persistenceFieldName, String persistenceTypeName, String domainFieldName, DataMap dataMap) {
        return new com.frank.mapper.maps.mongodb.BigDecimalPersistenceMap(persistenceFieldName, persistenceTypeName, domainFieldName, (MongoDbDataMap)dataMap);
    }

    public AbstractPersistenceMap createMongoDbEnumPersistenceMap(String persistenceFieldName, String persistenceTypeName, String domainFieldName, DataMap dataMap) {
        return new com.frank.mapper.maps.mongodb.EnumPersistenceMap(persistenceFieldName, persistenceTypeName, domainFieldName, (MongoDbDataMap)dataMap);
    }

    public AbstractPersistenceMap createMongoDbStringPersistenceMap(String persistenceFieldName, String persistenceTypeName, String domainFieldName, DataMap dataMap) {
        return new com.frank.mapper.maps.mongodb.StringPersistenceMap(persistenceFieldName, persistenceTypeName, domainFieldName, (MongoDbDataMap)dataMap);
    }
}
