package com.frank.persistence;

import com.frank.capabilities.Hydratable;
import com.frank.capabilities.Hydrator;
import com.frank.repository.MongoDbRepository;
import com.frank.repository.Repository;

public class OrderHydrator implements Hydrator {

    private final Repository repository = new MongoDbRepository();

    public Hydratable hydrate(Object id) {
        return repository.get(id);
    }

}
