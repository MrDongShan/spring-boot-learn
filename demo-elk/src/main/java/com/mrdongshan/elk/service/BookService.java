package com.mrdongshan.elk.service;

import com.mrdongshan.elk.dao.BookRepository;
import com.mrdongshan.elk.pojo.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;


    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public void delete(Book book) {
        bookRepository.delete(book);
    }

    public Iterable<Book> getAll() {
        return bookRepository.findAll();
    }

    public Page<Book> getPage(Integer current, Integer size) {
        PageRequest pageRequest = PageRequest.of(current, size);
        return bookRepository.findAll(pageRequest);
    }

    public Long getCount() {
        return bookRepository.count();
    }

    public Optional<Book> getById(String id) {
        return bookRepository.findById(id);
    }

    public boolean existsById(String id) {
        return bookRepository.existsById(id);
    }
}
