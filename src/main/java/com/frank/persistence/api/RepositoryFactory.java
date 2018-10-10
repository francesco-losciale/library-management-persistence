package com.frank.persistence.api;

public interface RepositoryFactory {

    EntityMapper createEntityMapper(Class domainClass, Class<EntityMapper> entityMapperClass, String collectionName);

    Repository createRepository();
}
