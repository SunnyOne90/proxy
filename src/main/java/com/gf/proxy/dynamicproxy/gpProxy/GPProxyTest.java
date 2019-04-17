package com.gf.proxy.dynamicproxy.gpProxy;

import com.gf.proxy.PurchaseRoom;
import com.gf.proxy.dynamicproxy.JdkIntermediary;

public class GPProxyTest {
    public static void main(String[] args) {
        GPIntermediary gpIntermediary = new GPIntermediary();
        gpIntermediary.getInstance(new PurchaseRoom());
    }
}
