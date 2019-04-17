//package com.gf.proxy.dynamicproxy.gpProxy;
//
//import com.gf.proxy.Room;
//
//import com.gf.proxy.Room;
//
//import java.lang.reflect.*;
//
//public class $Proxy0 implements com.gf.proxy.Room {
//    GPInvocationHandler h;
//
//    public $Proxy0(GPInvocationHandler h) {
//        this.h = h;
//    }
//
//    public void buyRoom() {
//        try {
//            Method m = com.gf.proxy.Room.class.getMethod("buyRoom", new Class[]{});
//            this.h.invoke(this, m, new Object[]{});
//        } catch (Error _ex) {
//        } catch (Throwable e) {
//            throw new UndeclaredThrowableException(e);
//        }
//    }
//}
//
