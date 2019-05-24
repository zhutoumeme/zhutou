package cn.cm.union.thread;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class SimpleHttpServer {

  private static ThreadPool<HttpRequestHandler> threadPool = new DefaultThreadPool<>(1);
  private static String basePath;
  private static ServerSocket serversocket;
  private static int port = 8080;

  public static void setPort(int port) {
    if (port > 0) {
      SimpleHttpServer.port = port;
    }
  }

  public static void setBasePath(String path) {
    if (null != path && new File(path).exists() && new File(path).isDirectory()) {
      SimpleHttpServer.basePath = path;
    }
  }

  public void start() throws IOException {
    ServerSocket serverSocket = new ServerSocket(port);
    Socket socket = null;
    while ((socket = serverSocket.accept()) != null) {
      threadPool.execute(new HttpRequestHandler(socket));
    }
    serversocket.close();
  }

  static class HttpRequestHandler implements Runnable {

    Socket socket = null;

    public HttpRequestHandler(Socket socket) {
      socket = socket;
    }

    @Override
    public void run() {
      String line = null;
      BufferedReader br = null;
      BufferedReader reader = null;
      PrintWriter out = null;
      InputStream in = null;
      try {
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String header = reader.readLine();
        String filePath = basePath + header.split(" ")[1];
        out = new PrintWriter(socket.getOutputStream());
        if (filePath.endsWith("jpg") || filePath.endsWith("ico")) {
          in = new FileInputStream(filePath);
          ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
          int i = 0;
          while ((i = reader.read()) != -1) {
            byteArrayOutputStream.write(i);
          }
          byte[] array = byteArrayOutputStream.toByteArray();
          out.println("HTTP/1.1 200 OK");
          out.println("Content-Type:image/jpeg");
          out.println("Content-Length:" + array.length);
          out.println("");
        } else {
          br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
          out = new PrintWriter(socket.getOutputStream());
          out.println("HTTP/1.1 200 OK");
          out.println("Content-Type: text/html;charset=UTF-8");
          out.println("");
          while ((line = br.readLine()) != null) {
            out.println(line);
          }
        }
        out.flush();
      } catch (Exception e) {
        out.println("HTTP/1.1 500");
        out.println("");
        out.flush();
      } finally {
        closed(br, in, reader, out, socket);
      }
    }

    private static void closed(Closeable... closeables) {
      if (closeables != null) {
        Arrays.stream(closeables).forEach(closeable -> {
          try {
            closeable.close();
          } catch (IOException e) {
            e.printStackTrace();
          }
        });
      }
    }
  }
}
