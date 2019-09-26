package com.faushine;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @author Yuxin Fan
 * @create 2019-09-23
 */
public class UDPServer {

  private int clientPort;
  private int serverPort;

  public UDPServer(int clientPort, int serverPort) {
    this.serverPort = serverPort;
    this.clientPort = clientPort;
  }

  public void run() throws IOException {
    DatagramSocket clientDs = new DatagramSocket(serverPort);
    byte[] receive = new byte[1];
    DatagramPacket dpReceive = null;
    while (true) {
      dpReceive = new DatagramPacket(receive, receive.length);
      clientDs.receive(dpReceive);
      System.out.println("Received data length is " + receive.length+" byte");

      DatagramSocket serverDs = new DatagramSocket();
      InetAddress client = dpReceive.getAddress();
      System.out.println("Received from "+client.toString());
      byte[] ack = new byte[1];
      ack[0] = 2;
      DatagramPacket ackSend = new DatagramPacket(ack, ack.length, client, clientPort);
      serverDs.send(ackSend);
      receive = new byte[1];
    }
  }
  public static void main(String[] args) throws IOException {
    UDPServer server = new UDPServer(30001, 30002);
    server.run();
  }
}
