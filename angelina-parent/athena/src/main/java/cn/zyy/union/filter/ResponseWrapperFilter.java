package cn.zyy.union.filter;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class ResponseWrapperFilter implements Filter {

  @Override
  public void init(FilterConfig filterConfig) {

  }

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
      FilterChain filterChain) throws IOException, ServletException {
// 使用我们自定义的响应包装器来包装原始的ServletResponse
    ResponseWrapper wrapper = new ResponseWrapper((HttpServletResponse) servletResponse);
// 这句话非常重要，注意看到第二个参数是我们的包装器而不是response
    filterChain.doFilter(servletRequest, wrapper);
// 处理截获的结果并进行处理，比如替换所有的“名称”为“铁木箱子”
    String result = wrapper.getResult();
    result = result.replace("ok", "铁木箱子");
    System.out.println("result:" + result);
// 输出最终的结果
    PrintWriter out = servletResponse.getWriter();
    servletResponse.setContentLength(-1);
    out.write(result);
    out.flush();
    out.close();

  }

  @Override
  public void destroy() {

  }
}
