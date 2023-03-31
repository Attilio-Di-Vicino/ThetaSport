package com.ecommerce.thetasport.service.loginCor;

public class Login {

    /*public static ToHandle login(String name, String password, String usernameAdmin){
        Map<String, String> listaUtenti;
        try {
            listaUtenti = UsersMapDAO.getUtenti();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Handler handler = new UsersExistHandler(listaUtenti);
        handler.setNextHandler(new ValidPasswordHandler(listaUtenti));
        handler.getNextHandler().setNextHandler(new RoleCheckHandler(usernameAdmin));
        AuthService authService = new AuthService(handler);
        return authService.login(name,password);
    }*/
}
