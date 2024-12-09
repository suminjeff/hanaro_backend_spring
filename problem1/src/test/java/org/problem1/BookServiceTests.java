package org.problem1;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.problem1.domain.Book;
import org.problem1.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Log4j2
@SpringBootTest
public class BookServiceTests {
    @Autowired
    BookService service;

    @Test
    public void testGetList() {
        service.getList().forEach(log::info);
    }

    @Test
    public void testInsert() {
        Book book = Book.builder()
                .title("testBook")
                .author("testAuthor")
                .publisher("testPublisher")
                .description("testDescription")
                .isbn("testISBN")
                .build();
        service.insert(book);
        log.info("inserted book...{}", book);
    }

    @Test
    public void testRead() {
        log.info("read...{}", service.read(1));
    }

    @Test
    public void testDelete() {
        service.remove(2);
        log.info("book bno 2 removed...{}", service.getList());
    }

    @Test
    public void testBorrow() {
        Book book = service.read(1);
        if (book == null) return;
        service.borrow(1, "ID_1");
        log.info("borrow...{}", service.read(1));
    }
}
