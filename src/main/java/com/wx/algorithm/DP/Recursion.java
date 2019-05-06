package com.wx.algorithm.DP;

public class Recursion {


    /**
     * 问题1：一列数的规则如下: 1、1、2、3、5、8、13、21、34 ，求第30位数是多少？使用递归实现
     */
    public static void sumNum(){

        int a = recursionSum(5);
        System.out.println(a);
    }


    /**
     * 阶乘
     * @param x 最大
     * @return int
     */
    private static int recursionSum(int x){

        if(x<=1){
            return 1;
        }
        return x*recursionSum(x-1);
    }

    /**
     * 递归
     * @param x x y z ... z=x+y
     * @return int
     */
    private static int recursion(int x){

        if(x <2){
            return 1;
        }
        System.out.printf("%s %s \n",x-1,x-2);
        return recursion(x-1) + recursion(x-2);
    }
}
