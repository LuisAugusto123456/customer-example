package pe.entity.microservice.customer.example.util;

import java.util.Date;


public class Utils {
  public static void print(Object s) {
    System.out.printf("%s:%s%n", new Date(), s);
  }

  public static void sleep(long time) {
    try {
      Thread.sleep(time);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  private static long start = System.currentTimeMillis();

  public static Boolean isSlowTime() {
    boolean b = (System.currentTimeMillis() - start) % 30_000 >= 15_000;
//      System.out.println(new Date() + String.format("is %sslow time", b?"":"NOT "));
    return b;
  }

}
