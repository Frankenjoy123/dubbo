package com.alibaba.dubbo.demo.consumer.callback;

public class CallbackImpl {

    public void before(String param){
        System.out.println("调用前 onInvoke , 参数:" + param);
    }

    public void done(String result) {
        System.out.println("结果 callback onReturn：" + result);
    }

    public void handleException(Throwable e) {
        System.out.println("异常：" + e.getMessage());
    }

}
