package com.gf.proxy.staticProxy;

import com.gf.proxy.PurchaseRoom;

public class IntermediaryTest {
    public static void main(String[] args) {
        Intermediary intermediary = new Intermediary(new PurchaseRoom());
        System.out.println("帮你找到合适的房源啦！");
        intermediary.getRoom();
        System.out.println("看好了，那就办理手续吧！");
    }
}
