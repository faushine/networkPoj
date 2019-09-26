package com.faushine;

import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

/**
 * @author Yuxin Fan
 * @create 2019-09-26
 */
@Ignore
public class UDPClientTest {


  @Test
  public void oneRRt() throws IOException {
    UDPClient client = new UDPClient("192.168.0.101", 30002, 30001);
    client.run();
    System.out.println(client.rrt);
  }

}