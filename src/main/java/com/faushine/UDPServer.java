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
    byte[] buff = new byte[1];
    DatagramPacket dpReceive = null;
    while (true) {
      dpReceive = new DatagramPacket(buff, buff.length);
      clientDs.receive(dpReceive);
      System.out.println("Received data length is " + buff.length+" byte");

      DatagramSocket serverDs = new DatagramSocket();
      InetAddress clientHost = dpReceive.getAddress();
      System.out.println("Send data to "+clientHost.toString());
      byte[] ack = new byte[1];
      DatagramPacket ackSend = new DatagramPacket(ack, ack.length, clientHost, clientPort);
      serverDs.send(ackSend);
    }
  }
  public static void main(String[] args) throws IOException {
    UDPServer server = new UDPServer(30001, 30002);
    server.run();
  }
}
