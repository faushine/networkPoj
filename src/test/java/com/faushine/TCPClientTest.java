package com.faushine;

import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

/**
 * @author Yuxin Fan
 * @create 2019-09-24
 */
@Ignore
public class TCPClientTest {


  @Test
  public void oneRRt() throws IOException {
    TCPClient client = new TCPClient("192.168.0.101", 30000);
    client.runOnce(1);
    System.out.println(client.rrt);
  }

  @Test
  public void oneMBThroughput() throws IOException {
    TCPClient client = new TCPClient("192.168.0.101", 30000);
    client.runOnce(1024*1024);
    long time = client.rrt;
    System.out.println(time);
    System.out.println(1024*1024*8/(time/1000));
  }

  @Test
  public void oneHundredWithNewSocket() throws IOException{
    long total = 0;
    for (int i = 0; i<100; i++){
      TCPClient client = new TCPClient("192.168.0.101", 30000);
      client.runOnce(1);
      total += client.rrt;
    }
    System.out.println(total);
    System.out.println(total/100);
  }

  @Test
  public void oneHundredWithOldSocket() throws IOException{
    TCPClient client = new TCPClient("192.168.0.101", 30000);
    client.runOnce(100);
    System.out.println(client.rrt);
  }
}
