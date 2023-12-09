package com.mrdongshan.elasticsearch.service.impl;

import com.mrdongshan.elasticsearch.dao.BookRepository;
import com.mrdongshan.elasticsearch.pojo.Book;
import com.mrdongshan.elasticsearch.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void delete(Book book) {
        bookRepository.delete(book);
    }

    @Override
    public Iterable<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    public Page<Book> getPage(Integer current, Integer size) {
        PageRequest pageRequest = PageRequest.of(current, size);
        return bookRepository.findAll(pageRequest);
    }

    @Override
    public Long getCount() {
        return bookRepository.count();
    }

    @Override
    public Optional<Book> getById(String id) {
        return bookRepository.findById(id);
    }

    @Override
    public boolean existsById(String id) {
        return bookRepository.existsById(id);
    }
}
