package com.frank.persistence.api;

public interface RepositoryFactory {

    EntityMapper createEntityMapper(Class domainClass, String collectionName);

    Repository createRepository();
}
