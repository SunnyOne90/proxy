package com.gf.proxy.staticProxy;/**
 * Created by Sunny on 2019/4/16.
 */

import com.gf.proxy.Person;

/**
 * @author bjgaofeng
 * @create 2019-04-16 下午3:23
 **/
public class Son implements Person{
    public void findLove() {
        System.out.println("儿子的要求："+"肤白，貌美，大长腿");
    }
}
