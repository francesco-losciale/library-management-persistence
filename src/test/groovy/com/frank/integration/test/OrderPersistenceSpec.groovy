package com.frank.integration.test

import com.frank.capabilities.Dehydrator
import com.frank.capabilities.Hydrator
import com.frank.context.book.Book
import com.frank.context.book.BookCollection
import com.frank.context.book.Order
import com.frank.persistence.OrderDehydrator
import com.frank.persistence.OrderHydrator
import spock.lang.Specification

class OrderPersistenceSpec extends Specification {

    final Book book = createBookMock()

    def "given an Order object and a persistence unit when save operation is triggered then the object is persisted"() {
        given: "A set of book for ordering"
        final BookCollection bookCollection = new BookCollection()
        bookCollection.add(book)

        and: "A persistence unit"
        Hydrator hydrator = new OrderHydrator()
        Dehydrator dehydrator = new OrderDehydrator()

        when: "An order is filled in"
        Order order = new Order()
        //order.add(bookCollection) // TODO uncomment
        String orderId = "orderId"

        and: "The order is persisted"
        dehydrator.dehydrate(order)

        then: "The order read from the persistence unit is what expected"
        order == hydrator.hydrate(order.getId())
    }

    private Book createBookMock() {
        final Book book = new Book()
        book.setTitle("title")
        book.setAuthor("author")
        book.setIsbn("isbn")
        book.setActualPrice(BigDecimal.valueOf(10.45))
    }

}