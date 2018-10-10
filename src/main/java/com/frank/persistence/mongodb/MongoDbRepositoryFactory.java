package com.frank.persistence.mongodb;

import com.frank.persistence.api.*;
import com.frank.persistence.mongodb.datamap.MongoDbDataMap;
import com.frank.persistence.mongodb.map.MongoDbPersistenceMapFactory;

import java.lang.reflect.InvocationTargetException;

public class MongoDbRepositoryFactory implements RepositoryFactory {

    public EntityMapper createEntityMapper(Class domainClass, Class<EntityMapper> entityMapperClass, String collectionName) {
        DataMap dataMap = createDataMap(domainClass, collectionName);
        return newEntityMapperInstance(entityMapperClass, dataMap);
    }

    public Repository createRepository() {
        return new MongoDbRepository();
    }

    private PersistenceMapFactory createPersistenceMapFactory() {
        return new MongoDbPersistenceMapFactory();
    }

    private DataMap createDataMap(Class domainClass, String collectionName) {
        return new MongoDbDataMap(domainClass, collectionName, createPersistenceMapFactory());
    }

    private EntityMapper newEntityMapperInstance(Class<EntityMapper> entityMapperClass, DataMap dataMap) {
        try {
            return entityMapperClass.getConstructor(DataMap.class).newInstance(dataMap);
        } catch (InstantiationException e) {
            throw new RuntimeException("Implementation error: impossible to create an entity mapper", e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Implementation error: impossible to create an entity mapper", e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException("Implementation error: impossible to create an entity mapper", e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Implementation error: impossible to create an entity mapper", e);
        }
    }
}
