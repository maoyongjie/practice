package com.practice.datastructure.designpatterns.builderpattern3;

/**
 * @author MaoYongjie
 * @date 2022/3/30 19:30
 * @Description:
 */
public class Test {
    public static void main(String[] args) {
        Director director = new Director();

        HPComputerBuilder hpComputerBuilder = new HPComputerBuilder();

        director.setComputerBuilder(hpComputerBuilder);

        director.constructComputer();

        Computer computer = director.getComputer();

        StringBuilder stringBuilder = new StringBuilder();
    }

}
