package com.ecommerce.thetasport.service.loginCor;

import com.ecommerce.thetasport.dao.UserDAO;
import com.ecommerce.thetasport.model.UserBean;

import java.sql.SQLException;
import java.util.Map;

public class ManagerLogin {

    public static ToHandle login( String name, String password, String usernameAdmin ) {
        Map<String, String> usersMap;
        try {
            usersMap = UserDAO.getUsersMap();
        } catch ( SQLException e ) {
            throw new RuntimeException( "Error in ManagerLogin/Login " + e );
        }
        Handler handler = HelperCoR.linkHandler( new UsersExistHandler( usersMap ),
                new ValidPasswordHandler( usersMap ),
                new RoleCheckHandler( usernameAdmin ));
        AuthService authService = new AuthService( handler );
        return authService.login( name, password );
    }

    public static UserBean getSingleUser(String email) throws SQLException {
        return new UserDAO().getUser( email );
    }

    public static void registrationUser(String name, String email, String password) throws SQLException {
        UserDAO.registration( name, email, password );
    }
}
