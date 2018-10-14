package com.frank.persistence.mongodb;

import com.frank.persistence.api.DataMap;
import com.frank.persistence.api.EntityMapper;
import com.frank.persistence.api.PersistenceMapFactory;
import com.frank.persistence.api.Repository;
import com.frank.persistence.api.RepositoryFactory;
import com.frank.persistence.api.mapper.OrderMapper;
import com.frank.persistence.mongodb.datamap.MongoDbDataMap;
import com.frank.persistence.mongodb.map.MongoDbPersistenceMapFactory;
import org.bson.Document;

import java.lang.reflect.InvocationTargetException;

public class MongoDbRepositoryFactory implements RepositoryFactory {

    public EntityMapper createEntityMapper(Class domainClass, String collectionName) {
        DataMap dataMap = createDataMap(domainClass, Document.class, collectionName);
        return newEntityMapperInstance(dataMap);
    }

    public Repository createRepository() {
        return new MongoDbRepository();
    }

    private PersistenceMapFactory createPersistenceMapFactory() {
        return new MongoDbPersistenceMapFactory();
    }

    private DataMap createDataMap(Class domainClass, Class persistenceClass, String collectionName) {
        return new MongoDbDataMap(domainClass, persistenceClass, collectionName, createPersistenceMapFactory());
    }

    private EntityMapper newEntityMapperInstance(DataMap dataMap) {
        try {
            return OrderMapper.class.getConstructor(DataMap.class).newInstance(dataMap);
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
