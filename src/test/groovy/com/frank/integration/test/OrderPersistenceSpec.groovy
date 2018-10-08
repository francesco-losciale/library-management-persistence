package com.frank.integration.test

import com.frank.capabilities.Repository
import com.frank.context.book.Order
import com.frank.persistence.api.EntityMapper
import com.frank.persistence.mongodb.MongoDbRepositoryFactory
import spock.lang.Specification

class OrderPersistenceSpec extends Specification {

    def repositoryFactory = new MongoDbRepositoryFactory()

    def "GIVEN an EMPTY Order object and a persistence unit WHEN save operation is triggered THEN the object is persisted"() {
        given: "A persistence unit"
        EntityMapper entityMapper = repositoryFactory.createEntityMapper(Order.class, "orders")
        Repository repository = repositoryFactory.createRepository(entityMapper)

        when: "An EMPTY order is filled in"
        Order order = new Order()

        and: "The order is persisted"
        Object orderId = order.save(repository)

        then: "The order read from the persistence unit is what expected"
        order == repository.get(orderId)
    }

}