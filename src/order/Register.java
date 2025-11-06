package order;

import server.CentralClient;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import product.Product;

public class Register {
  private CentralClient client;
  private Order order;
  private File output;

  public Register () throws IOException {
    client = new CentralClient();
    output = new File("./orders.txt");
    if (output.isFile()) {
      output.delete();
      output.createNewFile();
    }
  }

  public void startTransaction() {
    order = new Order();
  }

  public void scan(Product p) {
    order.addProduct(p);
  }

  public void voidTransaction() {
    order = null;
  }

  public void completeTransaction() {
    try {
      client.connect();
      client.sendOrder(order);
      client.disconnect();
    } catch (Exception e) {}
    writeOrder();
    order = null;
  }

  public void writeOrder() {
    FileWriter writer;
    try {
      writer = new FileWriter(output, true);
      writer.write(order.toProtocol() + "\n\n");
      writer.close();
    } catch (FileNotFoundException fnf) {
      System.err.println("File not found");
    } catch (IOException ioe) {
      System.err.println(ioe);
    }
  };

  public static void main(String[] args) throws IOException {
    Product milk = new Product("milk", 4.5);
    Register r = new Register();

    for (int i = 1; i <= 20; i++) {
      r.startTransaction();
      for (int j = 1; j <= i; j++) {
        r.scan(milk);
      }
      r.completeTransaction();
      try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    }
  }
}