package com.gf.proxy.dynamicproxy.gpProxy;

public class GPProxy {

    public static final String In = "\r\n";

    public static Object newProxyInstace(GPClassLoader classLoader,Class<?>[] interfaces,GPInvocationHandler h){
        try{
            String src = generateSrc(interfaces);
        }catch (Exception e){

        }
    }

    private static String generateSrc(Class<?>[] interfaces) {

    }
}
