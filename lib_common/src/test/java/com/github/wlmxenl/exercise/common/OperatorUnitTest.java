package com.github.wlmxenl.exercise.common;

import org.junit.Test;

/**
 * 操作符示例
 *
 * @Author wangzhengfu
 * @Date 2022/2/20
 */
public class OperatorUnitTest {

    @Test
    public void bitWiseOperator() {
        /*
         * 按位取反 ~-2 = 1
         *
         * -2 的二进制为 110，取反后 001
         * 001 转换为 10 进制 -> 1 * 2^0 = 1
         */
        System.out.printf("~-2 = %s%n", ~-2);


        /*
         * and 1 & 2 = 0
         *
         * 1 对应二进制 01， 2 对应二进制 10
         *
         * 01
         * 10
         * 按位运算 0 = false, 1 = true
         * 00 -> 对应 10 进制 = 0
         */
        System.out.printf("1 & 2 = %s%n", 1 & 2);


        /*
         * 1 | 2 = 3
         *
         * 1 对应二进制 01， 2 对应二进制 10
         *
         * 01
         * 10
         * 按位运算 0 = false, 1 = true
         * 11 -> 对应 10 进制 = 3
         */
        System.out.printf("1 | 2 = %s%n", 1 | 2);

        /*
         * 1 ^ 2 = 3
         *
         * 1 对应二进制 01， 2 对应二进制 10
         *
         * 01
         * 10
         * 异或运算示例： 00=0，11=0，01=1，10=1
         * 11 -> 对应 10 进制 = 3
         */
        System.out.printf("1 ^ 2 = %s%n", 1 ^ 2);
    }
}
