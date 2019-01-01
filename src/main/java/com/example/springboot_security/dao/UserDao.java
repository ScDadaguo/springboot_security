package com.example.springboot_security.dao;


import com.example.springboot_security.domain.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
    public SysUser findByUserName(String username);
}
