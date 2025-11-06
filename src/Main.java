import java.io.IOException;

import order.Register;
import product.Product;
import server.CentralServer;

public class Main {

  public static void main(String[] args) throws IOException, InterruptedException {
    int port = 8080;
    CentralServer cs = new CentralServer(port);
    cs.start();

    Register register = new Register();
    
    int limit = 10;
    int stop = 1 + (int) (Math.random() * (double) limit);
    System.out.println("Stops at " + stop);
    for (int i = 1; i <= 10; i++) {
      register.startTransaction();
      Product p = new Product("Product " + i, i);
      if (i == stop) {
        cs.stop();
      }
      for (int j = 1; j <= i; j++) {
        register.scan(p);
      }
      register.completeTransaction();
      Thread.sleep(500);
    }
  }
}