package com.ecommerce.thetasport.service.loginCor;

public class AuthService {
    private final Handler HANDLER;
    public AuthService( Handler handler ) { this.HANDLER = handler; }
    public ToHandle login( String username, String password ){
        ToHandle resultLogin = HANDLER.handle( username,password );
        if ( resultLogin == ToHandle.ADMIN_ACCESS || resultLogin == ToHandle.USER_ACCESS ) {
            System.out.println( "Successful login!" );
        }
        return resultLogin;
    }
}
