package com.wx.algorithm.DP;

import java.util.HashMap;
import java.util.Map;

public class StairCase {

    private Map<Integer,Integer> stepMap ;
    private int c;

    public int climbStairs(int n) {
        //return climbStairsAction(0, n);

        stepMap = new HashMap<>();
        int stepCount = climbStairsActionV3(0, n);

        //打印总共需要循环的次数
        System.out.println(c);
        return stepCount;
    }

    /**
     * 暴利方法, 递归
     *
     * @param i 步数
     * @param n 楼梯总数
     * @return int
     */
    private int climbStairsAction(int i, int n) {
        //如果传过来的值 刚好等于n,则返回1 表示当前前进一步有效
        if(i==n){
            return 1;
        }
        //如果前进一步过后, 大于n 则表示当前步伐过大, 不成立 返回0
        if(i>n){
            return 0;
        }
        c++;
        return climbStairsAction(i+1,n) + climbStairsAction(i+2,n);
    }


    /**
     * 缓存递归 固定步数所有的情况 ,放到map
     * @param i 当前步数
     * @param n 楼梯总数
     * @return int
     */
    private int climbStairsActionV2(int i, int n){
        if(i==n){
            return 1;
        }
        if(i>n){
            return 0;
        }
        if(stepMap.containsKey(i)){
            return stepMap.get(i);
        }

        c++;
        //获取创建的数组
        stepMap.put(i,climbStairsActionV2(i+1,n) + climbStairsActionV2(i+2,n)) ;

        return stepMap.get(i);
    }


    /**
     * 动态规划
     * @param i 当前步数
     * @param n 楼梯总数
     * @return int
     */
    private int climbStairsActionV3(int i, int n){
        stepMap.put(1,1);
        stepMap.put(2,2);
        for(int s=3;s<=n;s++){
            stepMap.put(s,stepMap.get(s-1)+stepMap.get(s-2));
        }
        return stepMap.get(n);
    }



}
