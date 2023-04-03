package com.ecommerce.thetasport.service.loginCor;

import java.util.Map;

public class UsersExistHandler extends Handler {
    private final Map<String, String> USERS;

    public UsersExistHandler( Map<String, String> users ){
        this.USERS = users;
    }

    @Override
    public ToHandle handle( String username, String password ) {
        if ( !USERS.containsKey( username ) ) {
            System.out.println( "User unregistred" );
            return ToHandle.UNREGISTERED;
        }
        return handlerNext( username, password );
    }
}
