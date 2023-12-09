package com.mrdongshan.elasticsearch.service;

import com.mrdongshan.elasticsearch.pojo.Book;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface BookService {
    Book save(Book book);

    void delete(Book book);

    Iterable<Book> getAll();

    Page<Book> getPage(Integer current, Integer size);

    Long getCount();

    Optional<Book> getById(String id);

    boolean existsById(String id);
}