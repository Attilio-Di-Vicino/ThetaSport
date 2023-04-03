package com.ecommerce.thetasport.service.loginCor;

public class RoleCheckHandler extends Handler {
    private final String USERNAME_ADMIN;

    public RoleCheckHandler( String usernameAdmin ) { this.USERNAME_ADMIN = usernameAdmin; }

    @Override
    public ToHandle handle( String username, String password ) {
        if ( USERNAME_ADMIN.equals( username ) ) {
            System.out.println( "Admin page" );
            return ToHandle.ADMIN_ACCESS;
        }
        return handlerNext( username, password );
    }
}
