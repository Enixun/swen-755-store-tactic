package order;

import java.util.ArrayList;

import product.Product;

public class Order {
  public static String protocolTerminator = "OrderEnd";

  private ArrayList<Product> products;

  public Order() {
    products = new ArrayList<Product>();
  }

  public void addProduct(Product p) {
    products.add(p);
  }

  public String toProtocol() {
    String orderProtocol = "Order\n";
    for (Product p : products ) {
      orderProtocol = orderProtocol.concat(p.toString() + "\n");
    }
    return orderProtocol + Order.protocolTerminator;
  }
}