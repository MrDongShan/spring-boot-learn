package com.mrdongshan.elasticsearch.controller;

import com.mrdongshan.elasticsearch.pojo.Book;
import com.mrdongshan.elasticsearch.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("book")
public class BookController {
    private final BookService bookService;

    @GetMapping("/insert")
    public String insertBook() {
        Book book = new Book();
        book.setName("钢铁是怎么炼成的");
        book.setSize(1000);
        bookService.save(book);
        return getAll();
    }

    @GetMapping("/delete")
    public String delete() {
        Book book = new Book();
        book.setName("钢铁是怎么炼成的");
        bookService.delete(book);
        return getAll();
    }

    @GetMapping("/getAll")
    public String getAll() {
        List<Book> list = new ArrayList<>();
        Iterable<Book> iterable = bookService.getAll();
        iterable.forEach(list::add);
        log.info("ES中的内容：{}", list);
        return list.toString();
    }

    @GetMapping("/getPage")
    public String getBookPage() {
        Page<Book> page = bookService.getPage(0, 10);
        log.info("ES中的内容：{}", page);
        List<Book> collect = page.get().collect(Collectors.toList());
        collect.forEach(System.err::println);
        return page.toString();
    }

    @GetMapping("/getCount")
    public String getBookCount() {
        Long count = bookService.getCount();
        log.info("ES中的内容：{}", count);
        return count.toString();
    }

    @GetMapping("/existsById")
    public String existsById() {
        Boolean flag = bookService.existsById("钢铁是怎么炼成的");
        log.info("Book ES中的内容：{}", flag);
        return flag.toString();
    }

    @GetMapping("/findBookById")
    public String findBookById() {
//        Optional<Book> optional = bookService.getById("1");
        Optional<Book> optional = bookService.getById("钢铁是怎么炼成的");
        boolean flag = optional.isPresent();
        if (flag) {
            Book book = optional.get();
            log.info("ES中的内容：{}", book);
            return book.toString();
        } else {
            log.info("没找到");
            return null;
        }
    }


}
