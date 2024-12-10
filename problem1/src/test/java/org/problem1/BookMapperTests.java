package org.problem1;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.problem1.dao.BookMapper;
import org.problem1.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@Log4j2
@SpringBootTest
class BookMapperTests {
    @Setter(onMethod_ = {@Autowired})
    private BookMapper mapper;

    @Test
    public void testInsert() {
        for (int i = 0; i < 5; i++) {
            Book book = Book.builder()
                    .title("Book" + i+1)
                    .author("Author" + i+1)
                    .publisher("Publisher" + i+1)
                    .description("Description" + i+1)
                    .isbn("ISBN" + i+1)
                    .build();
            mapper.insert(book);
        }
    }

    @Test
    public void testRead() {
        Book book = mapper.read(1);
        log.info(book);
    }

    @Test
    public void testGetList() {
        List<Book> books = mapper.getList();
        log.info(books);
    }

    @Test
    public void testBorrow() {
        Integer bno = 2;
        String borrowerId = "ID_2";
        Book book = mapper.read(bno);
        book.setBorrowerId(borrowerId);
        book.setAvailability(false);
        mapper.borrow(book);
        log.info(book);
    }
}
