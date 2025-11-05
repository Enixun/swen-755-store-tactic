package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import order.Order;

public class CentralServer {

  private Thread serverThread;
  private ServerSocket socket;

  private CentralServer() {
    this(8080);
  }

  public CentralServer (int port) {
    try {
      socket = new ServerSocket(port);
    } catch (IOException ioe) {
      System.err.println("Unable to initialize CentralServerSocket.");
      ioe.printStackTrace();
    }
  }

  public void start() {
    System.out.println("Starting Central Server...");
    serverThread = new Thread("CentralServer Main Thread") {
      @Override
      public void run() {
        try {
          while (!socket.isClosed()) {
            Socket client = socket.accept();
            System.out.println("Received connection on " + Thread.currentThread().getName());
            BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter writer = new PrintWriter(client.getOutputStream(), true);
            new Thread() {
              @Override
              public void run() {
                System.out.println("Starting new thread on " + Thread.currentThread().getName());
                try {
                  String input = null;
                  while (!(input = reader.readLine()).equals(Order.protocolTerminator)) {

                    // input = reader.readLine();
                    System.out.println("Received: " + input);
                  }
                  System.out.println("input finished");
                  writer.println("From server: " + input);
                  client.close();
                  } catch (IOException ioe) {
                    System.err.println(ioe);
                  }
              }
            }.start();
          }
        } catch (IOException ioe) {
          System.err.println("Unable to start the server.");
          ioe.printStackTrace();
        }
      }
    };
    serverThread.start();
  }
  
  public void stop() {
    System.out.println("Stopping Central Server...");
    try {
      socket.close();
    } catch (Exception e) {
      System.err.println("Unable to stop the server.");
      e.printStackTrace();
    }
  }

  public static void main(String[] args) throws InterruptedException {
    CentralServer cs = new CentralServer();
    cs.start();
    Thread.sleep(10000);
    cs.stop();
  }
}