package Availability;

import order.Order;

import java.util.ArrayList;

import product.Product;

public class TimeStampManager {

    public void checkProductOrder(Order order){
        ArrayList<Product> products = order.getProducts();
        for (int i =0; i<products.size();i+=1){
            if (products.get(i).getCreationTimeStamp() >= order.getCreationTimeStamp()){
                System.err.println("Time Stamp error: Product instance created before order");
            }
        }

    }
    
}
