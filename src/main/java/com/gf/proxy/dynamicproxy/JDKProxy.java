package com.gf.proxy.dynamicproxy;

import com.gf.proxy.PurchaseRoom;

public class JDKProxy {
    public static void main(String[] args) {
        JdkIntermediary jdkIntermediary = new JdkIntermediary();
        jdkIntermediary.getInstance(new PurchaseRoom());
    }
}
