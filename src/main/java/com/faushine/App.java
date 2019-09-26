package com.faushine;

/**
 * Hello world!
 */
public class App {

  public static void main(String[] args) throws Exception {
    String type = args[0];
    if (type.equals("tcp")) {
      TCPServer server = new TCPServer(Integer.valueOf(args[1]));
      server.run();
    }
    if (type.equals("udp")) {
      UDPServer server = new UDPServer(Integer.valueOf(args[1]), Integer.valueOf(args[2]));
      server.run();
    }
  }

}
