package com.pm.springboot_shiro.dao;

import com.pm.springboot_shiro.pojo.Manager;

public interface ManagerMapper {
    int insert(Manager record);

    int insertSelective(Manager record);

    Manager findManagerByName(String name);
}