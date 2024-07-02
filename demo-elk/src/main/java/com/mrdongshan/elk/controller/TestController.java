package com.mrdongshan.elk.controller;

import com.mrdongshan.elk.pojo.Book;
import com.mrdongshan.elk.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TestController {
    private final BookService bookService;

    @GetMapping("start")
    public void start() {
        log.info("test-start");
    }

    @GetMapping("/elk")
    public void index() {
        String message = "logback ELK成功接入了，时间：" + new Date();
        log.info(message);
    }

    @GetMapping("/delete")
    public String delete() {
        Book book = new Book();
        book.setName("钢铁是怎么炼成的");
        bookService.delete(book);
        return getAll();
    }

    @GetMapping("/insert/book")
    public String insertBook() {
        Book book = new Book();
        book.setName("钢铁是怎么炼成的");
        book.setSize(1000);
        bookService.save(book);
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

    @GetMapping("/existsById")
    public String existsById() {
        Boolean flag = bookService.existsById("钢铁是怎么炼成的");
        log.info("Book ES中的内容：{}", flag);
        return flag.toString();
    }

    @GetMapping("/findBookById")
    public String findBookById() {
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
