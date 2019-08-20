package com.alibaba.dubbo.common;

/**
 * Created by xiaowu.zhou on 2019/8/20.
 */
public class Looper {

    public void loop(){
        try {
            System.out.println("Looper.loop() invoked");
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
