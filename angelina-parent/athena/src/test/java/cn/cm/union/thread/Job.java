package cn.cm.union.thread;

import java.util.Date;
import java.util.UUID;

public class Job implements Runnable {

  @Override
  public void run() {
    System.out.println("job is running...:" + System.currentTimeMillis());
  }

  public static void main(String[] args) {
    UUID uuid = UUID.randomUUID();
    System.out.println(uuid.toString());
    System.out.println(UUID.fromString("670754ad-0e32-4090-8047-e56cc462815b"));
    System.out.println(compressUUID(uuid));
    byte[] nbyte = {10, 20, 30};
    System.out.println(UUID.nameUUIDFromBytes(nbyte));
    System.out.println(new Date(1420041600000L));
  }

  private static String compressUUID(UUID uuid) {
    StringBuilder resultBuilder = new StringBuilder();
    String uuidStr = uuid.toString();
    boolean isFirst = false;
    int tmp = 0;
    for (int i = 0; i < 36; i++) {
      if (i == 8 || i == 13 || i == 18 || i == 23) {
        continue;
      }
      char c = uuidStr.charAt(i);
      short shortValue = Short.valueOf(String.valueOf(c), 16);
      if (!isFirst) {
        tmp += shortValue;
        isFirst = true;
      } else {
        tmp = tmp << 4;
        tmp += shortValue;
        resultBuilder.append((char) tmp);
        tmp = 0;
        isFirst = false;
      }
    }
    return resultBuilder.toString();
  }
}
