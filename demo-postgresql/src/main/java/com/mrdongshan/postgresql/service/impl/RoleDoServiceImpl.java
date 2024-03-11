package com.mrdongshan.postgresql.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mrdongshan.postgresql.dao.IRoleDao;
import com.mrdongshan.postgresql.pojo.Role;
import com.mrdongshan.postgresql.service.IRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleDoServiceImpl extends ServiceImpl<IRoleDao, Role> implements IRoleService {

    @Override
    public List<Role> findList(Role role) {
        return lambdaQuery().like(StringUtils.isNotEmpty(role.getName()), Role::getName, role.getName())
                .like(StringUtils.isNotEmpty(role.getDescription()), Role::getDescription, role.getDescription())
                .like(StringUtils.isNotEmpty(role.getRoleKey()), Role::getRoleKey, role.getRoleKey())
                .list();
    }
}

