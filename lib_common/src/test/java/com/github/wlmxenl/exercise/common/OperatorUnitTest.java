package com.github.wlmxenl.exercise.common;

import org.junit.Test;

/**
 * 操作符示例
 *
 * @Author wangzhengfu
 * @Date 2022/2/20
 */
public class OperatorUnitTest {

    /**
     * 位逻辑运算
     */
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


    /**
     * 位移运算
     */
    @Test
    public void shiftOperator() {
        /*
         * 右移运算 297 >> 5 = 9504
         *
         * 297 二进制 = 100101001
         * 左移5位 = 10010100100000
         * 1001 转换 10 进制 = 9
         *
         * 简化：297 / 2^5 = 9
         */
        System.out.printf("297 >> 5 = %s%n", 297 >> 5);

        /*
         * 左移运算 2 << 5 = 9
         *
         * 2 二进制 = 10
         * 左移5位 = 1000000
         * 1000000 转换 10 进制 = 64
         *
         * 简化：2 * 2^5 = 64
         */
        System.out.printf("2 << 5 = %s%n", 2 << 5);


        /*
         * 无符号右移 2 >>> 1 = 1
         * 等同于 2 >> 1
         */
        System.out.printf("2 >>> 1 = %s%n", 2 >>> 1);


        /*
         * 无符号右移最终得到的是正数
         *
         * -2 >>> 1 = 2147483647
         *
         *
         */
        System.out.printf("-2 >>> 1 = %s%n", -2 >>> 1);
    }

    @Test
    public void castTest() {
        // (byte)128 = -128
        System.out.println((byte)128);
        // 128 二进制 0000 0000 0000 0000 0000 0000 1000 0000
        // 强转为 byte 时，就是一个强制高位截断的过程，得到：0000 0000 1000 0000
        // byte 范围区间值 -128 ~ 127 = 1000 0000 ~ 0111 1111
    }
}
