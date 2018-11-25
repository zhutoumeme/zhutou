package cn.zyy.union.classloading;

import java.io.IOException;
import java.io.InputStream;

public class ClassLoaderTest {

  public static void main(String[] args)
      throws ClassNotFoundException, IllegalAccessException, InstantiationException {

    ClassLoader myLoader = new ClassLoader() {
      @Override
      public Class<?> loadClass(String name) throws ClassNotFoundException {
        try {
          String fieldName = name.substring(name.lastIndexOf(".") + 1) + ".class";
          InputStream inputStream = getClass().getResourceAsStream(fieldName);
          if (inputStream == null) {
            return super.loadClass(name);
          }

          byte[] b = new byte[inputStream.available()];
          inputStream.read();
          return defineClass(name,b,0,b.length);
        } catch (IOException e) {
          throw new ClassNotFoundException(name);
        }
      }

    };
    Object obj = myLoader.loadClass("cn.zyy.union.classloading.SuperClass").newInstance();
    System.out.println(obj.getClass());
    System.out.println(obj instanceof cn.zyy.union.classloading.SuperClass);
  }
}
