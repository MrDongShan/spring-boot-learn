package com.mrdongshan.postgresql.controller;


import com.mrdongshan.postgresql.pojo.User;
import com.mrdongshan.postgresql.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;


/**
 * @author pdai
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final IUserService userService;

    /**
     * @param user user param * @return user
     */
    @PostMapping("add")
    public User add(User user) {
        if (user == null) {
            user = new User();
            user.setUserName("username");
            user.setEmail("email");
            user.setDescription("description");
            user.setPassword("password");
            user.setPhoneNumber(2312312L);
        }

        if (user.getId() == null) {
            user.setCreateTime(Timestamp.valueOf(LocalDateTime.now()));
        }
        user.setUpdateTime(Timestamp.valueOf(LocalDateTime.now()));
        boolean save = userService.save(user);
        if (save) {
            System.err.println("保存成功");
        } else {
            System.err.println("保存失败");
        }
        return userService.getById(user.getId());
    }


    /**
     * @return user list
     */
    @GetMapping("getById")
    public User get(Long userId) {
        if (userId == null) {
            userId = 1L;
        }
        return userService.getById(userId);
    }

    /**
     * @return user list
     */
    @GetMapping("list")
    public List<User> list(User user) {
        List<User> list = userService.findList(user);
        list.forEach(System.err::println);
        return list;
    }
}

