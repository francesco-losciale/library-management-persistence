package com.frank.persistence.api.mapper;

import com.frank.entity.Hydratable;
import com.frank.persistence.api.EntityMapper;

public abstract class AbstractEntityMapper implements EntityMapper {

    private Class<? extends Hydratable> domainClass;
    private Class persistenceClass;

    public AbstractEntityMapper(Class<? extends Hydratable> domainClass, Class persistenceClass) {
        this.domainClass = domainClass;
        this.persistenceClass = persistenceClass;
    }

    public Class getDomainClass() {
        return domainClass;
    }

    public Class getPersistenceClass() {
        return persistenceClass;
    }
}
