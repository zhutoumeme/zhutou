package cn.zyy.union.utils;

import java.util.concurrent.atomic.AtomicInteger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class Aop {

  @Pointcut("execution(* cn.zyy.union.utils.User.*(..))")
  public void pointCut() {

  }

  @Before("pointCut()")
  public void begin() {
    System.out.println("开始事务...");
  }

  @AfterThrowing("pointCut()")
  public void close() {
    System.out.println("关闭事务...");
  }

  public static void main(String[] args) {
    AtomicInteger a = new AtomicInteger(0);
    while(true){
      System.out.println(a.addAndGet(1));
    }
  }
}
