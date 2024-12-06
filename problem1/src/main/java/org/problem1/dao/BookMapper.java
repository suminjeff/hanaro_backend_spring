package org.problem1.dao;

import org.apache.ibatis.annotations.Mapper;
import org.problem1.domain.Book;


import java.util.List;

@Mapper
public interface BookMapper {
    public List<Book> getList();
    public int insert(Book book);
    public Book read(Integer bno);
    public int update(Book book);
    public int delete(Integer bno);
}
