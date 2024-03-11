package com.mrdongshan.postgresql.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mrdongshan.postgresql.pojo.Role;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author pdai
 */
@Mapper
public interface IRoleDao extends BaseMapper<Role> {
}

