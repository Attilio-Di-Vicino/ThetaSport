package com.ecommerce.thetasport.service.loginCor;

public abstract class Handler {
    private Handler next;

    public Handler setNextHandler(Handler next){
        this.next = next;
        return next;
    }

    public Handler getNextHandler(){
        return this.next;
    }

    public abstract ToHandle handle(String username, String password);

    protected ToHandle handlerNext(String username, String password){
        if (this.next == null) {
            System.out.println("User page");
            return ToHandle.USER_ACCESS;
        }
        return next.handle(username,password);
    }
}
