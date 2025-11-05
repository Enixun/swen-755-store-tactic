package order;

import java.util.ArrayList;

import product.Product;

public class Order {
  public static String protocolTerminator = "OrderEnd";

  private ArrayList<Product> products;
  private long creationTimestamp;

  public Order() {
    products = new ArrayList<Product>();
    creationTimestamp = System.currentTimeMillis();
  }

  public long addProduct(Product p) {
    products.add(p);
    long currentTS = System.currentTimeMillis();
    return(currentTS);
  }

  public ArrayList<Product> getProducts(){
    return products;
  }

  public long getCreationTimeStamp(){
    return creationTimestamp;
  }

  public String toProtocol() {
    String orderProtocol = "Order\n";
    for (Product p : products ) {
      orderProtocol = orderProtocol.concat(p.toString() + "\n");
    }
    return orderProtocol + Order.protocolTerminator;
  }
}