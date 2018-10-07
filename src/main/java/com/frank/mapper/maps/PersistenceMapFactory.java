package com.frank.mapper.maps;

import com.frank.mapper.datamap.DataMap;

public class PersistenceMapFactory {

    public AbstractPersistenceMap createMongoDbBigDecimalPersistenceMap(String persistenceFieldName, String domainFieldName, DataMap dataMap) {
        return new com.frank.mapper.maps.mongodb.BigDecimalPersistenceMap(persistenceFieldName, domainFieldName, dataMap);
    }

    public AbstractPersistenceMap createMongoDbEnumPersistenceMap(String persistenceFieldName, String domainFieldName, DataMap dataMap) {
        return new com.frank.mapper.maps.mongodb.EnumPersistenceMap(persistenceFieldName, domainFieldName, dataMap);
    }

    public AbstractPersistenceMap createMongoDbStringPersistenceMap(String persistenceFieldName, String domainFieldName, DataMap dataMap) {
        return new com.frank.mapper.maps.mongodb.StringPersistenceMap(persistenceFieldName, domainFieldName, dataMap);
    }
}
