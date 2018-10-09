package com.frank.persistence.api;


import com.frank.capability.Hydratable;

public interface Repository {

    Object add(Hydratable hydratable, EntityMapper entityMapper);

    Hydratable get(Object id, EntityMapper entityMapper);
}
