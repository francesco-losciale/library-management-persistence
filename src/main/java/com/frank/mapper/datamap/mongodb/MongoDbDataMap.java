package com.frank.mapper.datamap.mongodb;

import com.frank.mapper.maps.AbstractPersistenceMap;
import com.frank.mapper.datamap.DataMap;
import com.frank.mapper.maps.PersistenceMapFactory;

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

    public List<AbstractPersistenceMap> getAbstractPersistenceMapList() {
        return abstractPersistenceMapList;
    }
}
