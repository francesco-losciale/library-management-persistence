package com.frank.persistence.mongodb.map;

import com.frank.persistence.api.DataMap;
import com.frank.persistence.api.PersistenceMapFactory;

public class MongoDbPersistenceMapFactory implements PersistenceMapFactory {

    public AbstractPersistenceMap createBigDecimalPersistenceMap(String persistenceFieldName, String domainFieldName, DataMap dataMap) {
        return new BigDecimalPersistenceMap(persistenceFieldName, domainFieldName, dataMap);
    }

    public AbstractPersistenceMap createEnumPersistenceMap(String persistenceFieldName, String domainFieldName, DataMap dataMap) {
        return new EnumPersistenceMap(persistenceFieldName, domainFieldName, dataMap);
    }

    public AbstractPersistenceMap createStringPersistenceMap(String persistenceFieldName, String domainFieldName, DataMap dataMap) {
        return new StringPersistenceMap(persistenceFieldName, domainFieldName, dataMap);
    }
}
