package com.wx.algorithm.String;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {


    /**
     * https://leetcode-cn.com/problems/two-sum/
     * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
     *
     * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
     *
     * 示例:
     * 给定 nums = [2, 7, 11, 15], target = 9
     * 因为 nums[0] + nums[1] = 2 + 7 = 9
     * 所以返回 [0, 1]
     *
     * @param nums 给定数字
     * @param target 需要的结果
     * @return 返回下标
     */
    public static int[] twoSum(int[] nums, int target){
        Map<Integer, Integer> numMap = new HashMap<>();
        for (int i=0;i<nums.length;i++){
            if(numMap.containsKey(target-nums[i]) && numMap.get(target-nums[i]) != i){
                numMap.get(target - nums[i]);
                return new int[] { i, numMap.get(target-nums[i]) };
            }
            numMap.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }



}