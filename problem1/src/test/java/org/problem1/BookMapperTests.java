package org.problem1;

import org.junit.jupiter.api.Test;
import org.problem1.dao.BookMapper;
import org.problem1.domain.Book;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BookMapperTests {
    private BookMapper bookMapper;

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
            bookMapper.insert(book);
        }
    }
}
