package com.frank.mapper;

import com.frank.mapper.field.mongodb.BigDecimalPersistenceMap;
import com.frank.mapper.field.mongodb.EnumPersistenceMap;
import com.frank.mapper.field.mongodb.StringPersistenceMap;

import java.util.ArrayList;
import java.util.List;

public class DataMap {

    private Class domainClass;
    private String collectionName;
    private List<AbstractPersistenceMap> abstractPersistenceMapList;

    public DataMap(Class domainClass, String collectionName) {
        this.domainClass = domainClass;
        this.collectionName = collectionName;
        this.abstractPersistenceMapList = new ArrayList<AbstractPersistenceMap>();
    }

    public void addField(String persistenceFieldName, String persistenceTypeName, String domainFieldName) {
        abstractPersistenceMapList.add(new StringPersistenceMap(persistenceFieldName, persistenceTypeName, domainFieldName, this));
    }

    public void addEnumField(String persistenceFieldName, String persistenceTypeName, String domainFieldName) {
        abstractPersistenceMapList.add(new EnumPersistenceMap(persistenceFieldName, persistenceTypeName, domainFieldName, this));
    }

    public void addBigDecimalField(String persistenceFieldName, String persistenceTypeName, String domainFieldName) {
        abstractPersistenceMapList.add(new BigDecimalPersistenceMap(persistenceFieldName, persistenceTypeName, domainFieldName, this));
    }

    public Class getDomainClass() {
        return domainClass;
    }

    public List<AbstractPersistenceMap> getAbstractPersistenceMapList() {
        return abstractPersistenceMapList;
    }
}
