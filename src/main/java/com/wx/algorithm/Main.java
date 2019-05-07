package com.wx.algorithm;


import com.wx.algorithm.DP.Solution;
import com.wx.algorithm.DP.StairCase;
import com.wx.algorithm.String.TwoSum;
import org.junit.Test;

import java.util.Arrays;

public class Main {

    @Test
    public void TwoSum(){

        //求和
        int[] numMap = {1,2,3,4,5};
        int[] aa = TwoSum.twoSum(numMap,4);
        System.out.println(Arrays.toString(aa));


        //翻转
//        int x = 123456;
//        com.wx.dp.Reverse.action(x);

        //DP 爬楼梯
        //int algorithm = com.wx.dp.StairCase.climbStairs(3);

    }


    /**
     * 爬楼梯问题
     * #https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/23/dynamic-programming/54/
     */
    @Test
    public void StairCase(){
        StairCase stepCounts = new StairCase();
        int a = stepCounts.climbStairs(10);

        System.out.println(a);
    }


    @Test
    public void bestTimingStocks(){
        Solution bts = new Solution();

        //考虑的方向有问题
        int[] stocksDate = {1,2,3,4,5,6,5,9,10};
        int maxProfit = bts.maxProfit(stocksDate);
        System.out.println(maxProfit);
    }
}
