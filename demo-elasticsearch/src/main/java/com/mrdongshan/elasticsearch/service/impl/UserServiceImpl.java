package com.mrdongshan.elasticsearch.service.impl;

import com.mrdongshan.elasticsearch.dao.UserRepository;
import com.mrdongshan.elasticsearch.pojo.User;
import com.mrdongshan.elasticsearch.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public Iterable<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public Page<User> getPage(Integer current, Integer size) {
        PageRequest pageRequest = PageRequest.of(current, size);
        return userRepository.findAll(pageRequest);
    }

    @Override
    public Long getCount() {
        return userRepository.count();
    }

    @Override
    public Optional<User> getById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public boolean existsById(String id) {
        return userRepository.existsById(id);
    }
}
