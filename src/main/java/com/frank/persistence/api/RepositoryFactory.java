package com.frank.persistence.api;

public interface RepositoryFactory {

    PersistenceMapFactory createPersistenceMapFactory();

    EntityMapper createEntityMapper(Class domainClass, String collectionName);

    DataMap createDataMap(Class domainClass, String collectionName);
}
