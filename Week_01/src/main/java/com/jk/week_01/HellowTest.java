package com.jk.week_01;

/**
 * @author wei.huang
 * @version Id: HellowTest.java, v 0.1 2021年01月13日  19:26 wei.huang Exp $
 */
public class HellowTest {

    public static void main(String[] args) {
        double sum = 0;
        int[] numbers = new int[] { 1, 23, 13, 123, 12323 };
        for (int i = 0; i < numbers.length; i++) {
            sum += numbers[i];
            if(sum>100){
                return;
            }
        }
    }

}
