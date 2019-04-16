package com.gf.proxy.staticProxy;

import com.gf.proxy.PurchaseRoom;

public class Intermediary {
    private PurchaseRoom purchaseRoom;
    public Intermediary(PurchaseRoom purchaseRoom){
        this.purchaseRoom = purchaseRoom;
    }
    public void getRoom(){
        purchaseRoom.buyRoom();
    }
}
