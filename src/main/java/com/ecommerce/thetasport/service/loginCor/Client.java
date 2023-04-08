package com.ecommerce.thetasport.service.loginCor;

import java.util.HashMap;
import java.util.Map;

public class Client {
    public static void main( String[] args ) {
        Map< String, String > users = new HashMap<>();
        users.put( "attilio", "password" );
        users.put( "mario", "password" );
        users.put( "lorenzo", "password" );
        Handler handler;
        handler = HelperCoR.linkHandler( new UsersExistHandler( users ), new ValidPasswordHandler( users ),
                new RoleCheckHandler( "mario" ) );
        AuthService authService = new AuthService( handler );
        System.out.println( "\n*** TEST ACCESS ADMIN ( MARIO ) ***" );
        System.out.println( authService.login( "mario", "password" ) );
        System.out.println( "\n*** TEST ACCESS USER ***" );
        System.out.println( authService.login( "attilio", "password" ) );
        System.out.println( "\n*** TEST UNREGISTERED ***" );
        System.out.println( authService.login( "lor", "password" ) );
        System.out.println( "\n*** TEST PASSWORD WRONG ***" );
        System.out.println( authService.login( "attilio", "pas" ) );
    }
}
