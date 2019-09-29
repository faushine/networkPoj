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

  String host = "";

  @Test
  public void oneRRt() throws IOException {
    long[] times = new long[100];
    TCPClient client = new TCPClient(host, 30000);
    for (int i = 0; i < 100; i++) {
      client.runOnce(1);
      times[i] = client.rrt;
    }
    Utils.formatResult(times, "TCP-Q1", "A1-1");
  }


  @Test
  public void tenMBThroughput() throws IOException {
    TCPClient client = new TCPClient(host, 30000);
    long[] times = new long[100];
    long[] throughput = new long[100];
    for (int i = 0; i < 100; i++) {
      client.runOnce(1024 * 1024);
      long time = client.rrt;
      times[i] = time;
      throughput[i] = 1024 * 1024 * 8 / (time / 1000);
    }
    Utils.formatResult(times, "TCP-Q2-times", "A1-1");
    Utils.formatResult(throughput, "TCP-Q2-throughput", "A1-1");
  }

  @Test
  public void oneHundredWithNewSocket() throws IOException {
    long[] times = new long[100];
    for (int j = 0; j < 100; j++) {
      long total = 0;
      for (int i = 0; i < 100; i++) {
        TCPClient client = new TCPClient(host, 30000);
        client.runOnce(1);
        total += client.rrt;
      }
      times[j] = total;
    }
    Utils.formatResult(times, "TCP-Q3-E1", "A1-1");
  }

  @Test
  public void oneHundredWithOldSocket() throws IOException {
    TCPClient client = new TCPClient(host, 30000);
    long[] times = new long[100];
    for (int i = 0; i < 100; i++) {
      client.runOnce(100);
      times[i] = client.rrt;
    }
    Utils.formatResult(times, "TCP-Q3-E2", "A1-1");
  }
}
