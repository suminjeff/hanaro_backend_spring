package org.problem1.service;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.problem1.dao.BookMapper;
import org.problem1.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class BookService {
    @Setter(onMethod_ = @Autowired)
    private BookMapper mapper;

    public void insert(Book book) {
        mapper.insert(book);
        log.info("insert...{}", mapper.read(book.getBno()));
    }

    public Book read(Integer bno) {
        log.info("read...{}", mapper.read(bno));
        return mapper.read(bno);
    }

    public List<Book> getList() {
        log.info("getList...");
        return mapper.getList();
    }

    public boolean borrow(int bno, String borrowerId) {
        Book book = mapper.read(bno);
        book.setBorrowerId(borrowerId);
        book.setAvailability(false);
        log.info("borrow...{}", mapper.read(bno));
        return mapper.borrow(book) == 1;
    }

    public boolean remove(int bno) {
        log.info("remove...{}", bno);
        return mapper.delete(bno) == 1;
    }
}
