package com.ecommerce.thetasport.service.loginCor;

public class RoleCheckHandler extends Handler {
    private final String USERNAMEADMIN;
    public RoleCheckHandler( String usernameAdmin ) { this.USERNAMEADMIN = usernameAdmin; }
    @Override
    public ToHandle handle( String username, String password ) {
        if ( USERNAMEADMIN.equals( username ) ) {
            System.out.println( "Admin page" );
            return ToHandle.ADMIN_ACCESS;
        }
        return handlerNext( username, password );
    }
}
