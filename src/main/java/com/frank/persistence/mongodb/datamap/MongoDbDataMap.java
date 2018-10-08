package com.frank.persistence.mongodb.datamap;

import com.frank.persistence.api.DataMap;
import com.frank.persistence.api.PersistenceMap;
import com.frank.persistence.api.PersistenceMapFactory;
import com.frank.persistence.mongodb.map.MongoDbPersistenceMapFactory;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class MongoDbDataMap implements DataMap {

    private Class domainClass;
    private String collectionName;
    private List<PersistenceMap> persistenceMapList;
    private PersistenceMapFactory persistenceMapFactory;

    public MongoDbDataMap(Class domainClass, String collectionName, PersistenceMapFactory persistenceMapFactory) {
        this.persistenceMapFactory = persistenceMapFactory;
        this.domainClass = domainClass;
        this.collectionName = collectionName;
        this.persistenceMapList = new ArrayList<PersistenceMap>();
        this.persistenceMapFactory = new MongoDbPersistenceMapFactory();
    }

    public void addField(String persistenceFieldName, String domainFieldName) {
        persistenceMapList.add(persistenceMapFactory.createStringPersistenceMap(persistenceFieldName, domainFieldName, this));
    }

    public void addEnumField(String persistenceFieldName, String domainFieldName) {
        persistenceMapList.add(persistenceMapFactory.createEnumPersistenceMap(persistenceFieldName, domainFieldName, this));
    }

    public void addBigDecimalField(String persistenceFieldName, String domainFieldName) {
        persistenceMapList.add(persistenceMapFactory.createBigDecimalPersistenceMap(persistenceFieldName, domainFieldName, this));
    }

    public Class getDomainClass() {
        return domainClass;
    }

    public Document castToPersistent(Object domainObject) {
        final Document document = new Document();
        for (final PersistenceMap map : this.persistenceMapList) {
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
        for (final PersistenceMap map : this.persistenceMapList) {
            map.setFieldValue(domainObject, document.get(map.getPersitenceFieldName()));
        }
        return domainObject;
    }
}
