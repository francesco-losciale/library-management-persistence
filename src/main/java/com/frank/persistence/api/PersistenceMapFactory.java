package com.frank.persistence.api;


public interface PersistenceMapFactory {

    PersistenceMap createBigDecimalPersistenceMap(String persistenceFieldName, String domainFieldName, DataMap dataMap);

    PersistenceMap createEnumPersistenceMap(String persistenceFieldName, String domainFieldName, DataMap dataMap);

    PersistenceMap createStringPersistenceMap(String persistenceFieldName, String domainFieldName, DataMap dataMap);

}
