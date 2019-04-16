package com.gf.proxy.staticProxy;/**
 * Created by Sunny on 2019/4/16.
 */

/**
 * @author bjgaofeng
 * @create 2019-04-16 下午3:24
 **/
public class Father {
    private Son son;

    public Father(Son son){
        this.son = son;
    }

    public void fatherLove(){
        son.findLove();
    }
}
