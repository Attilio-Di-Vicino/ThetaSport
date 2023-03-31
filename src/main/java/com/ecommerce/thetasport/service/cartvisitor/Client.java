package com.ecommerce.thetasport.service.cartvisitor;

import com.ecommerce.thetasport.model.ProductBean;
import com.ecommerce.thetasport.service.paymentstrategy.CashStrategy;
import com.ecommerce.thetasport.service.paymentstrategy.HelperStrategy;
import com.ecommerce.thetasport.service.productabstractfactory.*;

import java.sql.SQLException;

public class Client {
    public static void main(String[] agrs) throws SQLException, ClassNotFoundException {

        Cart cart = new Cart();

        ProductBean productBean = new ProductBean();
        productBean.setName("primo");
        productBean.setDescription("prova");
        productBean.setPrice(100);
        productBean.setStock(10);
        productBean.setImage("image");
        productBean.setCategory(Category.TENNIS);
        productBean.setSubCategory(SubCategory.TSHIRT);

        Product newProduct = null;
        switch (productBean.getCategory()){
            case FOOTBALL: newProduct = DirectorFootball.createProduct(productBean);
                break;
            case TENNIS: newProduct = DirectorTennis.createProduct(productBean);
                break;
            default: newProduct = null;
                System.out.println("c");
                break;
        }
        cart.add(newProduct);
        /*List<Product> list = new ArrayList<>();
        list.add(newProduct);*/
        productBean.setCategory(Category.FOOTBALL);
        productBean.setSubCategory(SubCategory.SHOES);
        productBean.setName("secondo");
        /*list.add(DirectorFootball.createProduct(productBean));

        for ( Product product : list ){
            product.accept(new ShoppingCartVisitorImpl());
        }*/

        newProduct = DirectorFootball.createProduct(productBean);

        cart.add(newProduct);

        productBean.setName("terzo");
        productBean.setSubCategory(SubCategory.TSHIRT);
        newProduct = DirectorFootball.createProduct(productBean);
        cart.add(newProduct,4);
        System.out.println("My Cart");
        System.out.println(cart);

        cart.decreaseQuantity(newProduct);
        System.out.println("My cart decrease");
        System.out.println(cart);

        cart.remove(newProduct);
        System.out.println("My cart remove");
        System.out.println(cart);

        productBean.setName("terzo");
        productBean.setSubCategory(SubCategory.TSHIRT);
        newProduct = DirectorFootball.createProduct(productBean);
        cart.add(newProduct);
        System.out.println("My cart add terzo");
        System.out.println(cart);

        System.out.println("Add");
        cart.add(newProduct, 10);
        System.out.println(cart);

        ShoppingCartVisitorImpl shoppingCartVisitor = new ShoppingCartVisitorImpl(cart);
        double total = shoppingCartVisitor.getTotal();
        System.out.println("My cart total");
        System.out.println(total);

        /*cart.removeAll();
        System.out.println("My cart remove all");
        System.out.println(cart);*/

        HelperStrategy.pay(new CashStrategy("a","3","and"),shoppingCartVisitor.getTotal());

    }

    /*private static double calculatePrice(@NotNull List<ItemElement> items){
        ShoppingCartVisitor visitor = new ShoppingCartVisitorImpl();
        double sum = 0;

        for (ItemElement item : items){
            sum = sum + item.accept(visitor);
        }
        return sum;
    }*/
}
