package com.ecommerce.thetasport.service.loginCor;

import java.util.Map;

public class ValidPasswordHandler extends Handler {
    private final Map<String, String> USERS;

    public ValidPasswordHandler( Map<String, String> users ){
        this.USERS = users;
    }

    @Override
    public ToHandle handle( String username, String password ) {
        if ( !USERS.get( username ).equals( password ) ) {
            System.out.println( "Password wrong" );
            return ToHandle.WRONG_PASSWORD;
        }
        return handlerNext( username,password );
    }
}
