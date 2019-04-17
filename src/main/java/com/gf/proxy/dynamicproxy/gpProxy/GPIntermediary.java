package com.gf.proxy.dynamicproxy.gpProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class GPIntermediary implements GPInvocationHandler {
    private Object object;
    public Object getInstance(Object obj){
        this.object = obj;
        Class<?> clazz = obj.getClass();
        return GPProxy.newProxyInstance(new GPClassLoader(),clazz.getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("房子给你找好了，是你想要的。");
        Object object = method.invoke(this.object,args);
        System.out.println("那就赶紧办手续吧！");
        return  object;
    }
}
