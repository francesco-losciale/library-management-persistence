package com.frank.integration.test

import com.frank.capabilities.Repository
import com.frank.context.book.Order
import com.frank.persistence.EntityMapper
import com.frank.persistence.OrderMapper
import com.frank.repository.mongodb.MongoDbRepository

import spock.lang.Specification

class OrderPersistenceSpec extends Specification {

    def "GIVEN an EMPTY Order object and a persistence unit WHEN save operation is triggered THEN the object is persisted"() {
        given: "A persistence unit"
        EntityMapper entityMapper = new OrderMapper()
        Repository repository = new MongoDbRepository(entityMapper)

        when: "An EMPTY order is filled in"
        Order order = new Order()

        and: "The order is persisted"
        Object orderId = order.save(repository)

        then: "The order read from the persistence unit is what expected"
        order == repository.get(orderId)
    }

}