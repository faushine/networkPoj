package com.faushine;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/**
 * @author Yuxin Fan
 * @create 2019-09-23
 */
public class UDPClient {

  private String ip;
  private int clientPort;
  private int serverPort;
  public long rrt;

  public UDPClient(String ip, int clientPort, int serverPort) {
    this.ip = ip;
    this.serverPort = serverPort;
    this.clientPort = clientPort;
  }

  public void run() throws IOException {
    while (true){
      long startTime = System.currentTimeMillis();
      DatagramSocket clientDs = new DatagramSocket();
      InetAddress server = InetAddress.getByName(ip);
      byte[] buf = new byte[1];
      buf[0]=3;
      DatagramPacket dpSend = new DatagramPacket(buf, buf.length, server, serverPort);
      clientDs.send(dpSend);

      DatagramSocket serverDs = new DatagramSocket(clientPort);
      byte[] ack = new byte[1];
      DatagramPacket ackReceive = new DatagramPacket(ack, ack.length);
      serverDs.receive(ackReceive);
      if (ack[0] == 2){
        rrt = System.currentTimeMillis() - startTime;
        break;
      }else {
        System.out.println("Loss packet and try again");
      }
    }
    System.out.println("Completed");
    System.out.println(rrt+" ms");
  }

  public static void main(String[] args) throws IOException {
    UDPClient client = new UDPClient("192.168.0.101", 30002, 30001);
    client.run();
  }
}
