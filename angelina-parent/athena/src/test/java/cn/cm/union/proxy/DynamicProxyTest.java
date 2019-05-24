package cn.cm.union.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxyTest {

  interface IHello {

    void sayHello();
  }

  abstract static class Hehe implements IHello{

  }

  static class Hello implements IHello {

    @Override
    public void sayHello() {
      System.out.println("hello World!!!");
    }
  }

  static class HelloV2 implements IHello{

    @Override
    public void sayHello() {
      System.out.println("hello World V2!!!");
    }
  }

  static class DynamicProxy implements InvocationHandler {

    Object originObject;

    Object bind(Object originObject) {
      this.originObject = originObject;
      return Proxy.newProxyInstance(originObject.getClass().getClassLoader(),
          originObject.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
      System.out.println("welcome,老姐");
      return method.invoke(originObject,args);
    }
  }

  public static void main(String[] args) {
    System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");
    IHello hello = (IHello) new DynamicProxy().bind(new Hello());
    hello.sayHello();
    IHello helloV2 = (IHello) new DynamicProxy().bind(new HelloV2());
    helloV2.sayHello();
  }
}
