package com.mrdongshan.postgresql.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mrdongshan.postgresql.pojo.User;

import java.util.List;

    /** * @author pdai */
    public interface IUserService extends IService<User> {

        List<User> findList(User user);

    }

