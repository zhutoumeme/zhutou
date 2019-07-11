package cn.cm.union.http;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

public class HttpUtils {

  private static CloseableHttpClient httpClient; // 发送请求的客户端单例

  private static RequestConfig config = RequestConfig.copy(RequestConfig.DEFAULT)
      .setSocketTimeout(10000)
      .setConnectTimeout(5000)
      .setConnectionRequestTimeout(100).build();

  // .setProxy(new HttpHost("127.0.0.1",8888,"http")).build();
  static {

    PoolingHttpClientConnectionManager manager = new PoolingHttpClientConnectionManager();
    manager.setMaxTotal(200); //连接池最大并发连接数
    manager.setDefaultMaxPerRoute(200);//单路由最大并发数,路由是对maxTotal的细分
    httpClient = HttpClients.custom().setConnectionManager(manager).build();
  }

  public static String doGet(String url, Map<String, Object> header)
      throws HttpClientException {
    String ret = "";
    HttpGet get = new HttpGet(url);
    get.setConfig(config);
    get.addHeader(HTTP.CONTENT_ENCODING, "UTF-8");
    CloseableHttpResponse closeableHttpResponse = null;
    try {
      if (header != null) {
        for (Map.Entry<String, Object> entry : header.entrySet()) {
          get.setHeader(entry.getKey(), entry.getValue().toString());
        }
      }
      closeableHttpResponse = httpClient.execute(get);
      if (closeableHttpResponse.getStatusLine().getStatusCode() == 200) {
        ret = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
      } else {
        throw new HttpClientException(
            "System level error, Code=[" + closeableHttpResponse.getStatusLine().getStatusCode()
                + "].");
      }
    } catch (ClientProtocolException e) {
      throw new HttpClientException("HttpClient error," + e.getMessage());
    } catch (IOException e) {
      throw new HttpClientException("IO error," + e.getMessage());
    } finally {
      if (closeableHttpResponse != null) {
        try {
          closeableHttpResponse.close();
        } catch (IOException e) {
        }
      }
    }
    return ret;
  }

  public static String doPost(String url, Map<String, Object> params, Map<String, Object> header)
      throws HttpClientException {
    String ret = "";
    HttpPost post = new HttpPost(url);
    post.setConfig(config);
    post.addHeader(HTTP.CONTENT_ENCODING, "UTF-8");
    CloseableHttpResponse closeableHttpResponse = null;
    HttpEntity postEntity = null;
    try {
      if (params != null) {
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
          list.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
        }
        postEntity = new UrlEncodedFormEntity(list);
        post.setEntity(postEntity);
      }

      if (header != null) {
        for (Map.Entry<String, Object> entry : header.entrySet()) {
          post.setHeader(entry.getKey(), entry.getValue().toString());
        }
      }
      closeableHttpResponse = httpClient.execute(post);
      if (closeableHttpResponse.getStatusLine().getStatusCode() == 200) {
        ret = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
      } else {
        throw new HttpClientException(
            "System level error, Code=[" + closeableHttpResponse.getStatusLine().getStatusCode()
                + "].");
      }
    } catch (ClientProtocolException e) {
      throw new HttpClientException("HttpClient error," + e.getMessage());
    } catch (IOException e) {
      throw new HttpClientException("IO error," + e.getMessage());
    } finally {
      if (postEntity != null) {
        try {
          EntityUtils.consume(postEntity);
        } catch (IOException e) {
        }
      }
      if (closeableHttpResponse != null) {
        try {
          closeableHttpResponse.close();
        } catch (IOException e) {
        }
      }
    }
    return ret;
  }

  static class HttpClientException extends Exception {

    public HttpClientException(String message) {
      super(message);
    }
  }

  public static void main(String[] args) {
    String result = null;
    String url = "https://www.91xinbei.cn/unionProduct/ajaxArea?code=0&packIdEn=j26gh3m3jivzh&type=1&_=1562859899918";
    try {
      result = doGet(
          "https://www.91xinbei.cn/unionProduct/ajaxArea?code=110000&packIdEn=j26gh3m3jivzh&type=1&_=1562859944976",
          null);
      String result1 = doGet(url, null);
      System.out.println(result);
    } catch (HttpClientException e) {
      e.printStackTrace();
    }
    Map<String, Object> params = new HashMap();
    params.put("trialReqData", "{\"packId\":\"3002000\",\"inputs\":[{\"k\":103507,\"v\":395704}]}");

    try {
      result = doPost("http://www.xyz.cn/p/packtrial.do?xcase=trialLinkage", params, null);
    } catch (HttpClientException e) {
      e.printStackTrace();
    }
    System.out.println(result);
  }
}
