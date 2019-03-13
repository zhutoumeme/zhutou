package cn.cm.union.thread;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

public class Piped {

  public static void main(String[] args) throws IOException {
    PipedReader pipedReader = new PipedReader();
    PipedWriter pipedWriter = new PipedWriter();
    pipedReader.connect(pipedWriter);
    Thread printThread = new Thread(new Print(pipedReader), "printThread");
    printThread.start();
    int receive;
    try {
      while ((receive = System.in.read()) != -1) {
        pipedWriter.write(receive);
      }
    } catch (IOException e) {

    } finally {
      pipedWriter.close();
    }


  }

  static class Print implements Runnable {

    private PipedReader pipedReader;

    public Print(PipedReader pipedReader) {
      this.pipedReader = pipedReader;
    }

    @Override
    public void run() {
      int receive;
      try {

        while ((receive = pipedReader.read()) != -1) {
          System.out.print((char) receive);
        }
      } catch (IOException e) {

      }
    }
  }

}
