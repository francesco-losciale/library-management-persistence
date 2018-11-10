package com.frank.persistence.api;

public interface DataMap {

    void addField(String persistenceFieldName, String domainFieldName);

    void addEnumField(String persistenceFieldName, String domainFieldName);

    void addBigDecimalField(String persistenceFieldName, String domainFieldName);

    Class getDomainClass();

    Class getPersistenceClass();

    String getCollectionName();

    Object castToPersistent(Object domainObject);

    Object castToDomain(Object persistenceObject);
}
