package org.problem1.service;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.problem1.dao.BookMapper;
import org.problem1.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class BookService {
    @Setter(onMethod_ = @Autowired)
    private BookMapper bookMapper;

    public void insert(Book book) {
        System.out.println(book);
    }
}
