package com.frank.mapper;

import java.util.ArrayList;
import java.util.List;

public class DataMap {

    private Class domainClass;
    private String collectionName;
    private List<PersistenceMap> persistenceMapList;

    public DataMap(Class domainClass, String collectionName) {
        this.domainClass = domainClass;
        this.collectionName = collectionName;
        this.persistenceMapList = new ArrayList<PersistenceMap>();
    }

    public void addField(String persistenceFieldName, String persistenceTypeName, String domainFieldName) {
        persistenceMapList.add(new PersistenceMap(persistenceFieldName, persistenceTypeName, domainFieldName, this));
    }

    public void addEnumField(String persistenceFieldName, String persistenceTypeName, String domainFieldName) {
        persistenceMapList.add(new EnumPersistenceMap(persistenceFieldName, persistenceTypeName, domainFieldName, this));
    }

    public void addBigDecimalField(String persistenceFieldName, String persistenceTypeName, String domainFieldName) {
        persistenceMapList.add(new BigDecimalPersistenceMap(persistenceFieldName, persistenceTypeName, domainFieldName, this));
    }

    public Class getDomainClass() {
        return domainClass;
    }

    public List<PersistenceMap> getPersistenceMapList() {
        return persistenceMapList;
    }
}
