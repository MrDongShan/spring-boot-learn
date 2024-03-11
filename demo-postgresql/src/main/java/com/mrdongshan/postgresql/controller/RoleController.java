package com.mrdongshan.postgresql.controller;


import com.mrdongshan.postgresql.pojo.Role;
import com.mrdongshan.postgresql.service.IRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author pdai
 */
@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleController {
    private final IRoleService roleService;

    /**
     * @return role list
     */
    @GetMapping("list")
    public List<Role> list(Role role) {
        return roleService.findList(role);
    }
}

