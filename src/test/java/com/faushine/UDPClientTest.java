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
  String host = "";
  @Test
  public void oneRRt() throws IOException {
    UDPClient client = new UDPClient(host, 30001, 30002);
    client.runOnce();
    System.out.println(client.rrt);
  }

}