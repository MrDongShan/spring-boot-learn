package com.mrdongshan.elasticsearch.controller;

import com.mrdongshan.elasticsearch.pojo.User;
import com.mrdongshan.elasticsearch.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {
    private final UserService userService;

    @GetMapping("/insert")
    public String insertUser() {
        User user = new User();
        user.setId("1");
        user.setUsername("张三");
        user.setPassword("zhangsan");
        userService.save(user);
        return getAll();
    }

    @GetMapping("/delete")
    public String delete() {
        User user = new User();
        user.setId("1");
        userService.delete(user);
        return getAll();
    }

    @GetMapping("/getAll")
    public String getAll() {
        List<User> list = new ArrayList<>();
        Iterable<User> iterable = userService.getAll();
        iterable.forEach(list::add);
        log.info("ES中的内容：{}", list);
        return list.toString();
    }

    @GetMapping("/getPage")
    public String getUserPage() {
        Page<User> page = userService.getPage(0, 10);
        log.info("ES中的内容：{}", page);// ES中的内容：Page 1 of 1 containing com.mrdongshan.elasticsearch.pojo.User instances
        return page.toString();
    }

    @GetMapping("/getCount")
    public String getUserCount() {
        Long count = userService.getCount();
        log.info("ES中的内容：{}", count);
        return count.toString();
    }

    @GetMapping("/existsById")
    public String existsById() {
        Boolean flag = userService.existsById("1");
        log.info("User ES中的内容：{}", flag);
        return flag.toString();
    }

    @GetMapping("/findUserById")
    public String findUserById() {
        Optional<User> optional = userService.getById("1");
        boolean flag = optional.isPresent();
        if (flag) {
            User user = optional.get();
            log.info("ES中的内容：{}", user);
            return user.toString();
        } else {
            log.info("没找到");
            return null;
        }
    }

}
