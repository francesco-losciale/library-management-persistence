package com.frank.repository;

import com.frank.capabilities.Hydratable;

public interface Repository {

    void add(Hydratable object);

    void remove(Hydratable object);

    void remove(Object id);

    Hydratable get(Object id);

    void update(Object id, Hydratable object);
}
