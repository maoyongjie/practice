package com.practice.datastructure.designpatterns.builderpattern3;

/**
 * @author MaoYongjie
 * @date 2022/3/30 19:17
 * @Description:
 * 3 生成器模式
 * 定义：封装一个复杂对象构造过程，并允许按步骤构造。
 *
 * 定义解释： 我们可以将生成器模式理解为，假设我们有一个对象需要建立，这个对象是由多个组件（Component）组合而成，
 * 每个组件的建立都比较复杂，但运用组件来建立所需的对象非常简单，所以我们就可以将构建复杂组件的步骤与运用组件构建对象分离，
 * 使用builder模式可以建立。

 */
public abstract class ComputerBuilder {

    protected Computer computer;

    public Computer getComputer() {
        return computer;
    }

    public void buildComputer() {
        computer = new Computer();
        System.out.println("生成了一台电脑！！！");
    }
    public abstract void buildMaster();
    public abstract void buildScreen();
    public abstract void buildKeyboard();
    public abstract void buildMouse();
    public abstract void buildAudio();

}
