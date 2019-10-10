package cn.zyy.union.filter;

import java.io.CharArrayWriter;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class ResponseWrapper extends HttpServletResponseWrapper {

  private CharArrayWriter bufferedWriter = new CharArrayWriter();
  private PrintWriter cachedWriter = new PrintWriter(bufferedWriter);

  public ResponseWrapper(HttpServletResponse response) {
    super(response);
  }


  @Override
  public PrintWriter getWriter() {
    return cachedWriter;

  }

  public String getResult() {
    return bufferedWriter.toString();
  }
}
