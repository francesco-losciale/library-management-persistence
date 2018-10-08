package com.frank.persistence.mongodb;

import com.frank.capabilities.Repository;
import com.frank.persistence.api.DataMap;
import com.frank.persistence.api.EntityMapper;
import com.frank.persistence.api.PersistenceMapFactory;
import com.frank.persistence.api.RepositoryFactory;
import com.frank.persistence.mongodb.datamap.MongoDbDataMap;
import com.frank.persistence.mongodb.map.MongoDbPersistenceMapFactory;
import com.frank.persistence.mongodb.mapper.OrderMapper;

public class MongoDbRepositoryFactory implements RepositoryFactory {

    public PersistenceMapFactory createPersistenceMapFactory() {
        return new MongoDbPersistenceMapFactory();
    }

    public EntityMapper createEntityMapper(Class domainClass, String collectionName) {
        return new OrderMapper(createDataMap(domainClass, collectionName));
    }

    public DataMap createDataMap(Class domainClass, String collectionName) {
        return new MongoDbDataMap(domainClass, collectionName, createPersistenceMapFactory());
    }

    public Repository createRepository(EntityMapper entityMapper) {
        return new MongoDbRepository(entityMapper);
    }
}