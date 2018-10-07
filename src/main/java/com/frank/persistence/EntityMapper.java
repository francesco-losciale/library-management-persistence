package com.frank.persistence;

public interface EntityMapper {

    Object convertToPersistence(Object domainObject);

    Object convertToDomain(Object persistenceObject);
}
