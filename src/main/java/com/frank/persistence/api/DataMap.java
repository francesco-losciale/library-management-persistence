package com.frank.persistence.api;

import org.bson.Document;

public interface DataMap {

    void addField(String persistenceFieldName, String domainFieldName);

    void addEnumField(String persistenceFieldName, String domainFieldName);

    void addBigDecimalField(String persistenceFieldName, String domainFieldName);

    Class getDomainClass();

    Document castToPersistent(Object domainObject);

    Object castToDomain(Object persistenceObject);
}
