package com.ecommerce.thetasport.service.productABF;

public enum SubCategory {
    SHOES,TSHIRT;

    @Override
    public String toString(){
        String name;
        switch (this){
            case SHOES:
                name = "Shoes";
                break;
            case TSHIRT:
                name = "TShirt";
                break;
            default:
                name = null;
                break;
        }
        return name;
    }
}
