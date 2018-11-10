package com.frank.persistence.api;


import com.frank.entity.Hydratable;

public interface EntityMapper {

    Object convertToPersistence(Object domainObject);

    Object convertToDomain(Object persistenceObject);

    String getCollectionName();

    Class<? extends Hydratable> getDomainClass();
}
