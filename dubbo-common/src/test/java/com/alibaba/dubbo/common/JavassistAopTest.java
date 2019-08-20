package com.alibaba.dubbo.common;

import javassist.util.proxy.MethodFilter;
import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;

import java.lang.reflect.Method;

/**
 * Created by xiaowu.zhou on 2019/8/20.
 */
public class JavassistAopTest {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        ProxyFactory factory=new ProxyFactory();
        //设置父类，ProxyFactory将会动态生成一个类，继承该父类
        factory.setSuperclass(Looper.class);
        factory.setFilter(new MethodFilter() {
            @Override
            public boolean isHandled(Method m) {
                if(m.getName().equals("loop")){
                    return true;
                }
                return false;
            }
        });
        //设置拦截处理
        factory.setHandler(new MethodHandler() {
            @Override
            public Object invoke(Object self, Method thisMethod, Method proceed,
                                 Object[] args) throws Throwable {
                long start=System.currentTimeMillis();
                Object result = proceed.invoke(self, args);
                System.out.println("耗时:"+(System.currentTimeMillis()-start)+"ms");
                return result;
            }
        });

        Class<?> c=factory.createClass();
        Looper object=(Looper) c.newInstance();
        object.loop();
    }
}
