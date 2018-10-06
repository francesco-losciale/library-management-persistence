package com.frank.persistence;

import com.frank.capabilities.Dehydrator;
import com.frank.capabilities.Hydratable;
import com.frank.repository.MongoDbRepository;
import com.frank.repository.Repository;

public class OrderDehydrator implements Dehydrator {

    private final Repository repository = new MongoDbRepository();

    public void dehydrate(Hydratable hydratable) {
        repository.add(hydratable);
    }

}
