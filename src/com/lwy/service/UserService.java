package com.lwy.service;

public class UserService implements IUserService {
    @Override
    public int addUser() {
        System.out.println("����û�");
        return 1;
    }

    @Override
    public void upDateUser() {
        System.out.println("�����û�");
    }

    @Override
    public void deleteUser() {
        System.out.println("ɾ���û�");
    }
}
