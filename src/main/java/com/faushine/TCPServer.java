package com.faushine;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Yuxin Fan
 * @create 2019-09-23
 */
public class TCPServer {

  private int port;
  //initialize socket and input stream
  private Socket socket = null;
  private ServerSocket server = null;
  private DataInputStream in = null;
  private DataOutputStream ack = null;

  public TCPServer(int port) {
    this.port = port;
  }

  public void run() throws IOException {
    server = new ServerSocket(port);
    System.out.println("Server started");
    System.out.println("Waiting for a client ...");
    while (true) {
      socket = server.accept();
      System.out.println("Client accepted");
      ack = new DataOutputStream(socket.getOutputStream());
      in = new DataInputStream(socket.getInputStream());
      int read = 0;
      int count = 0;
      while ((read = in.read())!=-1){
        count++;
      }
      ack.write(9);
      System.out.println("Received data "+count+" bytes");
      System.out.println("Closing connection");
      ack.close();
      socket.close();
      in.close();
    }
  }

  public static void main(String args[]) throws IOException {
    TCPServer server = new TCPServer(30000);
    server.run();
  }
}