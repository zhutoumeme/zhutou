package cn.zyy.union.utils.DTO;

import java.io.Serializable;
import java.util.List;

/**
 * @program: union
 * @description: ${description}
 * @author: ${author}
 * @create: 2019-10-13 22:50
 **/
public class ResponseDTO<T> implements Serializable {

  private static final long serialVersionUID = 3090141275354972503L;

  public static final String OK = "ok";
  public static final String ERROR = "error";

  private T data;
  private String code;
  private List<String> msg;


  public ResponseDTO(T data) {
    this.code = OK;
    this.data = data;
  }

  public ResponseDTO(String code, List<String> msg) {
    this.code = code;
    this.msg = msg;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public List<String> getMsg() {
    return msg;
  }

  public void setMsg(List<String> msg) {
    this.msg = msg;
  }

  @Override
  public String toString() {
    return "ResponseDTO{" +
        "data=" + data +
        ", code='" + code + '\'' +
        ", msg=" + msg +
        '}';
  }
}