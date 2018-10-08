package com.frank.persistence.api;

public interface EntityMapper {

    Object convertToPersistence(Object domainObject);

    Object convertToDomain(Object persistenceObject);

    String getCollectionName();
}
