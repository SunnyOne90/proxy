package com.gf.proxy.staticProxy;/**
 * Created by Sunny on 2019/4/16.
 */

/**
 * @author bjgaofeng
 * @create 2019-04-16 下午3:27
 **/
public class FatherProxyTest {

    public static void main(String[] args) {
        Father father = new Father(new Son());
        father.fatherLove();
    }
}
