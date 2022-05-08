package com._异常的概念;

/*
    异常的体系：

        java中异常继承的根类是：Throwable

            Throwable(根类，不是异常类)
            /               \
          Error          Exception 异常，需要处理和研究
                            /       \
                        编译时异常   runtimeException运行时异常

         Error 无法通过处理的错误，一旦出现，无能为力，只能重启、优化代码
         比如内存崩溃

         Exception，需要提前处理
         分类：
         1。编译时异常：继承自Exception的异常或其子类，编译阶段就会报错
                担心程序员水平不行，在编译阶段就爆出一个错误，目的在于提醒！
                提醒程序员这里很可能出错

         2。运行时异常，继承了RunTimeException编译阶段不会报错


 */

public class ExceptionDemo {
}
