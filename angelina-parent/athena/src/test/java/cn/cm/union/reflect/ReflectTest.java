/*
Copyright 2018 Focus Technology, Co., Ltd. All rights reserved.
*/
package cn.cm.union.reflect;

import cn.cm.union.concurrent.DeadLockDemo;
import sun.java2d.cmm.PCMM;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author zhuyingye
 * @date 2019-03-15
 */
public class ReflectTest {

    public static void main(String[] args) {
        DeadLockDemo deadLockDemo = new DeadLockDemo();
        Type type  = deadLockDemo.getClass().getGenericSuperclass();
        Type[] types = ((ParameterizedType) type).getActualTypeArguments();

    }
}
