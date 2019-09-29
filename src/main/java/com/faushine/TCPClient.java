package com.faushine;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * @author Yuxin Fan
 * @create 2019-09-23
 */
public class TCPClient {

  private String ip;
  private int port;
  public long rrt;
  private Socket socket = null;
  private DataOutputStream out = null;
  private DataInputStream ack = null;

  public TCPClient(String ip, int port) {
    this.ip = ip;
    this.port = port;
  }

  public void runOnce(int size) throws IOException {
    long startTime = System.currentTimeMillis();
    socket = new Socket(ip, port);
    out = new DataOutputStream(socket.getOutputStream());
    ack = new DataInputStream(socket.getInputStream());
    byte[] buffer = new byte[size];
    for (int i = 0; i < size; i++) {
      out.writeByte(buffer[i]);
    }
    socket.shutdownOutput();
    ack.read();
    out.close();
    socket.close();
    rrt = System.currentTimeMillis() - startTime;
    System.out.println(rrt+" ms");
  }

}
