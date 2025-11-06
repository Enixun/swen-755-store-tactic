package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import order.Order;
import product.Product;

public class CentralClient {
  private String host;
  private int port;
  private Socket client;
  private BufferedReader reader;
  private PrintWriter writer;

  public CentralClient () {
    host = "localhost";
    port = 8080;
  }

  public void sendOrder(Order o) {
    writer.println(o.toProtocol());
    try {
      System.out.println(reader.readLine());
    } catch (IOException ioe) {
      System.err.println("Unable to read response from sendProduct");
      // ioe.printStackTrace();
    }
  }

  public void connect() {
    try {
      client = new Socket(host, port);
      reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
      writer = new PrintWriter(client.getOutputStream(), true);
    } catch (IOException ioe) {
      System.out.println("Unable to create CentralClient. Skipping...");
      // ioe.printStackTrace();
    }
  }

  public void disconnect() {
    try {
      client.close();
    } catch (IOException ioe) {
      System.err.println(ioe);
    }
  }

  public static void main(String[] args) {
    CentralClient client = new CentralClient();
    Order o = new Order();
    o.addProduct(new Product("Milk", 2.49));
    o.addProduct(new Product("Eggs", 4.49));
    o.addProduct(new Product("Bread", 3.49));
    client.sendOrder(o);
    client.disconnect();
  }
}