package com.ecommerce.thetasport.service.productABF;

public enum Category {
    FOOTBALL,TENNIS;

    @Override
    public String toString(){
        String name;
        switch (this){
            case FOOTBALL:
                name = "Football";
                break;
            case TENNIS:
                name = "Tennis";
                break;
            default:
                name = null;
                break;
        }
        return name;
    }
}
