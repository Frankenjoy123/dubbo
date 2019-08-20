package com.alibaba.dubbo.common;

import javassist.*;

/**
 * Created by xiaowu.zhou on 2019/8/20.
 * http://www.tianshouzhi.com/api/tutorials/bytecode/354
 */
public class JavassistTest2 {
    public static void main(String[] args) {
        try {
            //需要修改的已有的类名和方法名

            String className="com.alibaba.dubbo.common.Looper";

            String methodName="loop";

            ClassPool classPool = ClassPool.getDefault();

            CtClass clazz = classPool.get(className);

            //修改原有方法名为loop$impl
            CtMethod method = clazz.getDeclaredMethod(methodName);
            String newName = methodName + "$impl";
            method.setName(newName);

            //新方法为loop，调用loop$impl
            CtMethod newMethod =  CtNewMethod.make("    public void " + methodName+ "(){\n" +
                    "        long start = System.currentTimeMillis();\n" +
                    "        " + newName +"();\n" +
                    "        System.out.println(\"耗时：\" + (System.currentTimeMillis() - start) +\"ms\");\n" +
                    "    }",clazz);
            clazz.addMethod(newMethod);

//            method.insertBefore("long start=System.currentTimeMillis();");
//            method.insertAfter("System.out.println(\"耗时:\"+(System.currentTimeMillis()-start)+\"ms\");");


            //调用修改的Looper类的loop方法
            Looper looper = (Looper) clazz.toClass().newInstance();

            looper.loop();

        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (CannotCompileException e) {
            e.printStackTrace();
        }

    }

}
