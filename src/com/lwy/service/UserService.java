package com.lwy.service;

public class UserService implements IUserService {
    @Override
    public int addUser() {
        System.out.println("添加用户");
        return 1;
    }

    @Override
    public void upDateUser() {
        System.out.println("更新用户");
    }

    @Override
    public void deleteUser() {
        System.out.println("删除用户");
    }
}
