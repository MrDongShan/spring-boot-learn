package com.mrdongshan.postgresql.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mrdongshan.postgresql.dao.IUserDao;
import com.mrdongshan.postgresql.pojo.User;
import com.mrdongshan.postgresql.service.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDoServiceImpl extends ServiceImpl<IUserDao, User> implements IUserService {

    @Override
    public List<User> findList(User user) {
        return baseMapper.findList(user);
    }
}

