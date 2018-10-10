package com.frank.integration.test

import com.frank.context.book.Book
import com.frank.context.book.BookCollection
import com.frank.context.book.Order
import com.frank.persistence.api.EntityMapper
import com.frank.persistence.api.Repository
import com.frank.persistence.mongodb.MongoDbRepositoryFactory
import com.frank.persistence.mongodb.mapper.OrderMapper
import spock.lang.Specification

class OrderPersistenceSpec extends Specification {

    def repositoryFactory = new MongoDbRepositoryFactory()

    def "GIVEN an EMPTY Order object and a persistence unit WHEN save operation is triggered THEN the object is persisted"() {
        given: "A persistence unit"
        EntityMapper entityMapper = repositoryFactory.createEntityMapper(Order.class, OrderMapper.class, "orders")
        Repository repository = repositoryFactory.createRepository()

        when: "An EMPTY order is filled in"
        Order order = new Order()

        and: "The order is persisted"
        Object orderId = repository.add(order, entityMapper)

        then: "The order read from the persistence unit is what expected"
        order == repository.get(orderId, entityMapper)
    }

    def "GIVEN an Order and a collection of books WHEN save operation is triggered THEN the objects are persisted"() {
        given: "A persistence unit"
        EntityMapper entityMapper = repositoryFactory.createEntityMapper(Order.class, OrderMapper.class, "orders")
        Repository repository = repositoryFactory.createRepository()

        when: "An order of two books is filled in"
        Order order = new Order()
        BookCollection bookCollection = new BookCollection()
        Book book = new Book()
        book.setTitle("title")
        book.setAuthor("author")
        book.setIsbn("isbn")
        book.setActualPrice(BigDecimal.valueOf(12.39))
        bookCollection.add(book)
        bookCollection.add(book)
        order.add(bookCollection)

        and: "The order is persisted"
        Object orderId = repository.add(order, entityMapper)

        then: "The order read from the persistence unit is what expected"
        order == repository.get(orderId, entityMapper)
        order.getBookCollection() == 0 // TODO this call must not be possible, Order have to save the books internally. But how to pass the Book's entity mapper then?...
    }
}