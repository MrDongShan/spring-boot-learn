package com.mrdongshan.postgresql.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mrdongshan.postgresql.pojo.Role;

import java.util.List;

public interface IRoleService extends IService<Role> {

    List<Role> findList(Role role);

}

