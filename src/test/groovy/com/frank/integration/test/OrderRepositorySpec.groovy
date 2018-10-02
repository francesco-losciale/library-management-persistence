package com.frank.integration.test

import com.frank.context.book.Book
import com.frank.context.book.BookCollection
import com.frank.context.book.Order
import com.frank.repository.OrderRepository
import spock.lang.Specification

class OrderRepositorySpec extends Specification {

    final Book book = createBookMock()

    def "given an Order object and a persistence unit when save operation is triggered then the object is persisted"() {
        given: "A set of book for ordering"
        final BookCollection bookCollection = new BookCollection()
        bookCollection.add(book)

        and: "A persistence unit"
        OrderRepository orderRepository = new OrderRepository()

        when: "An order is filled in"
        Order order = new Order()
        //order.add(bookCollection)
        String orderId = "orderId"

        and: "The order is persisted"
        orderRepository.save(order, orderId)

        then: "The order read from the persistence unit is what expected"
        order == orderRepository.read(orderId)
    }

    private Book createBookMock() {
        final Book book = new Book()
        book.setTitle("title")
        book.setAuthor("author")
        book.setIsbn("isbn")
        book.setActualPrice(BigDecimal.valueOf(10.45))
    }

}