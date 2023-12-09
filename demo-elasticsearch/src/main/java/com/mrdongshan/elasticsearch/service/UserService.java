package com.mrdongshan.elasticsearch.service;

import com.mrdongshan.elasticsearch.pojo.User;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface UserService {
    User save(User user);

    void delete(User user);

    Iterable<User> getAll();

    Page<User> getPage(Integer current, Integer size);

    Long getCount();

    Optional<User> getById(String id);

    boolean existsById(String id);
}