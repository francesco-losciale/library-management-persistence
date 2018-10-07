package com.frank.mapper.datamap.mongodb;

import com.frank.mapper.datamap.DataMap;
import com.frank.mapper.maps.AbstractPersistenceMap;
import com.frank.mapper.maps.PersistenceMapFactory;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class MongoDbDataMap implements DataMap {

    private Class domainClass;
    private String collectionName;
    private List<AbstractPersistenceMap> abstractPersistenceMapList;
    private PersistenceMapFactory persistenceMapFactory;

    public MongoDbDataMap(Class domainClass, String collectionName) {
        this.domainClass = domainClass;
        this.collectionName = collectionName;
        this.abstractPersistenceMapList = new ArrayList<AbstractPersistenceMap>();
        this.persistenceMapFactory = new PersistenceMapFactory();
    }

    public void addField(String persistenceFieldName, String domainFieldName) {
        abstractPersistenceMapList.add(persistenceMapFactory.createMongoDbStringPersistenceMap(persistenceFieldName, domainFieldName, this));
    }

    public void addEnumField(String persistenceFieldName, String domainFieldName) {
        abstractPersistenceMapList.add(persistenceMapFactory.createMongoDbEnumPersistenceMap(persistenceFieldName, domainFieldName, this));
    }

    public void addBigDecimalField(String persistenceFieldName, String domainFieldName) {
        abstractPersistenceMapList.add(persistenceMapFactory.createMongoDbBigDecimalPersistenceMap(persistenceFieldName, domainFieldName, this));
    }

    public Class getDomainClass() {
        return domainClass;
    }

    public Document castToPersistent(Object domainObject) {
        final Document document = new Document();
        for (final AbstractPersistenceMap map : this.abstractPersistenceMapList) {
            document.append(map.getPersitenceFieldName(), map.getFieldValue(domainObject));
        }
        return document;
    }

    public Object castToDomain(Object persistenceObject) {
        Object instance = createDomainInstance();
        return populateDomainObject((Document) persistenceObject, instance);
    }

    private Object createDomainInstance() {
        try {
            return this.domainClass.newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException("Implementation error: impossible to create object " + this.domainClass.getName(), e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Implementation error: impossible to create object " + this.domainClass.getName(), e);
        }
    }

    private Object populateDomainObject(Document persistenceObject, Object domainObject) {
        Document document = persistenceObject;
        for (final AbstractPersistenceMap map : this.abstractPersistenceMapList) {
            map.setFieldValue(domainObject, document.get(map.getPersitenceFieldName()));
        }
        return domainObject;
    }
}
