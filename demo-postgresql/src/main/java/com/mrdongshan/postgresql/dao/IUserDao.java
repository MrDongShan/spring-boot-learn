package com.mrdongshan.postgresql.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mrdongshan.postgresql.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author pdai
 */
@Mapper
public interface IUserDao extends BaseMapper<User> {

    List<User> findList(User user);
}

