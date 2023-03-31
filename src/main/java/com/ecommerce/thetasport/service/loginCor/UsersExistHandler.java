package com.ecommerce.thetasport.service.loginCor;

import java.util.Map;

public class UsersExistHandler extends Handler{
    private final Map<String, String> users;

    public UsersExistHandler(Map<String, String> users){
        this.users = users;
    }

    @Override
    public ToHandle handle(String username,String password){
        if (!users.containsKey(username)){
            System.out.println("User unregistred");
            return ToHandle.UNREGISTERED;
        }
        return handlerNext(username,password);
    }
}
