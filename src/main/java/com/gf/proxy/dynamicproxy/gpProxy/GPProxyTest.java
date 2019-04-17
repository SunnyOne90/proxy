package com.gf.proxy.dynamicproxy.gpProxy;

import com.gf.proxy.PurchaseRoom;
import com.gf.proxy.Room;
import com.gf.proxy.dynamicproxy.JdkIntermediary;

public class GPProxyTest {
    public static void main(String[] args) {
        GPIntermediary gpIntermediary = new GPIntermediary();
        Room room = (Room)gpIntermediary.getInstance(new PurchaseRoom());
        room.buyRoom();
    }
}
