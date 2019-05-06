package com.wx.algorithm.DP;

import java.lang.reflect.Array;
import java.util.*;

public class Solution {


    private Map<Integer, Integer> stockMap;

    public Solution() {
        stockMap = new HashMap<>();
    }

    /**
     * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
     * <p>
     * 如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。
     * <p>
     * 注意你不能在买入股票前卖出股票。
     * <p>
     * 示例 1:
     * <p>
     * 输入: [7,1,5,3,6,4]
     * 输出: 5
     * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
     * 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
     * 示例 2:
     * <p>
     * 输入: [7,6,4,3,1]
     * 输出: 0
     * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
     * 最多能赚多少钱
     * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/
     *
     * @return 最大赚钱数量
     */
    public int maxProfit(int[] x) {
        return maxProfit3V2(x);
    }

    /**
     * 硬上,死循环
     * 这个timeout了,开始V2 优化
     *
     * @param x 每天股票的价格
     * @return int
     */
    private int maxProfitV1(int[] x) {
        if (x.length < 1) {
            return 0;
        }
        int last = 0;
        //将数组映射成hashmap
        for (int n = 0; n < x.length; n++) {
            //把相同的过滤掉,剪枝
            if (n > 0 && last == x[n]) {
                System.out.println(n);
                continue;
            }
            last = x[n];
            stockMap.put(n, 0);

            //之后的数据判断出所有最大的
            for (int m = n; m < x.length; m++) {
                if (x[m] - x[n] > stockMap.get(n)) {
                    stockMap.put(n, x[m] - x[n]);
                }
            }
        }

        int maximumProfit = 0;
        for (Map.Entry<Integer, Integer> entry : stockMap.entrySet()) {
            if (entry.getValue() > maximumProfit) {
                maximumProfit = entry.getValue();
            }
            //System.out.println("Key->" + entry.getKey() + ",Value->" + entry.getValue());
        }
        //System.out.println(maximumProfit);
        return maximumProfit;
    }


    /**
     * 动态规划, 永远记n 之前的最小值,然后跟当前的进行对比,看赚多少, 每次对比结果取较大的值,
     * 最终得到的就是 最大的值  每次都是最优解 ->  最终即为最优解
     *
     * @param x 股票价格
     * @return 最大赚钱书
     */
    private int maxProfitV2(int[] x) {
        if (x.length < 1) {
            return 0;
        }
        int minBuyPrice = x[0];
        int maxProfit = 0;
        for (int n = 1; n < x.length; n++) {
            //当当前最小价格 小于最小价格的时候, 则 将其赋值给 最小价格
            if (minBuyPrice > x[n]) {
                //System.out.printf("minBuyPrice -> %s maxProfit ->%s nowPrice->%s" ,minBuyPrice,maxProfit,x[n]);
                minBuyPrice = x[n];
            } else if (x[n] - minBuyPrice > maxProfit) {
                //当前卖价卖掉盈利大于之前的,则将替换掉

                //System.out.printf("minBuyPrice -> %d maxProfit ->%d nowPrice->%d" ,minBuyPrice,maxProfit,x[n]);
                maxProfit = x[n] - minBuyPrice;
            } else {
                //System.out.printf("minBuyPrice -> %d maxProfit ->%d nowPrice->%d \n" ,minBuyPrice,maxProfit,x[n]);
            }
        }
        return maxProfit;
    }

    /**
     * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
     * <p>
     * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
     * <p>
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     * <p>
     * 示例 1:
     * <p>
     * 输入: [7,1,5,3,6,4]
     * 输出: 7
     * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
     * 随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
     * 示例 2:
     * <p>
     * 输入: [1,2,3,4,5]
     * 输出: 4
     * 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
     * 注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
     * 因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
     * 示例 3:
     * <p>
     * 输入: [7,6,4,3,1]
     * 输出: 0
     * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
     * <p>
     * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/
     *
     * @return 最大赚钱数量
     */
    public int maxProfit2V1(int[] x) {
        //如果少于一行
        if (x.length < 1) {
            //throw new IllegalArgumentException("报错");
            return 0;
        }

        int minBuyPrice = x[0];
        int maxSellPrice = 0;
        int maxProfit2 = 0;
        for (int n = 1; n < x.length; n++) {

            if (minBuyPrice > x[n]) {//如果当前价格比买价高,则
                minBuyPrice = x[n];

            } else if (x[n] > minBuyPrice) {//表示此时可卖,利润为 x[n] - minBuyPrice,并且将 下标 移动到卖价

                maxProfit2 += x[n] - minBuyPrice;
                //下标修改为当前卖价
                minBuyPrice = x[n];

            } else if (x[n] == minBuyPrice) {//如果相等,则跳过
                continue;
            }
        }
        return maxProfit2;
        //throw new IllegalArgumentException("报错");
    }


    /**
     * maxProfit1 的变种  但是要考虑的问题更多
     * <p>
     * 此答案不合格 只考虑到了 相邻最大值 只考虑到了比如 1,3,4,5,6 没考虑到  1,3,4,8  此时最优解应该是 7 而非4
     *
     * @param x 股票数组
     * @return int
     */
    public int maxProfit3V1(int[] x) {
        List<Map> profitList = new ArrayList<>();
        Map<Integer, Map<String, Integer>> profitMap = new HashMap<>();

        int minBuyPriceDom = 0;
        int profit = 0;

        for (int n = 1; n < x.length; n++) {
            if (x[minBuyPriceDom] > x[n]) {//如果最小价格大于 当前价格, 则用 当前价格替换最小价格
                minBuyPriceDom = n;
            } else if (x[minBuyPriceDom] < x[n]) {//如果最小价格小于当前价格, 则表示可以盈利 , 进行最小刻度买卖, 并且将最小买价标记为当前价
                //计算盈利
                profit = x[n] - x[minBuyPriceDom];

                //记录当前盈利情况,构建当前盈利map
                Map<String, Integer> nProfit = new HashMap<>();
                nProfit.put("buyDom", minBuyPriceDom);
                nProfit.put("sellDom", n);
                nProfit.put("profit", profit);

                //赋值
                //profitMap.put(n, nProfit);
                profitList.add(nProfit);

                //替换最小价格
                minBuyPriceDom = n;
            }
        }


//        for (Map.Entry<Integer, Map<String, Integer>> entry : profitMap.entrySet()) {
//
////            System.out.println("Key->" + entry.getKey() + ",Value->" + entry.getValue());
////            System.out.println(entry.getValue().get("buyDom"));
//            //如果首尾相接,则合并
//            if (entry.getValue().get("buyDom") == entry.getValue().get("buyDom")
//        }

        if (profitList.isEmpty()) {
            return 0;
        }


        Map<Integer, Integer> maxProfitMap = new HashMap<>();

        maxProfitMap.put(0, (Integer) profitList.get(0).get("profit"));
        for (int n = 1; n < profitList.size(); n++) {

            //如果首尾相接,则合并
            if (profitList.get(n - 1).get("sellDom") == profitList.get(n).get("buyDom")) {


                //构建新的map
                Map<String, Integer> nProfit = new HashMap<>();
                nProfit.put("buyDom", (Integer) profitList.get(n - 1).get("buyDom"));//取上一个的买价格
                nProfit.put("sellDom", (Integer) profitList.get(n).get("sellDom"));//取当前的卖价格
                nProfit.put("profit", (Integer) profitList.get(n - 1).get("profit") + (Integer) profitList.get(n).get("profit"));

                //删除之前旧的
                //profitList.remove(n-1);

                //修改为新的盈利属性
                profitList.set(n, nProfit);
                maxProfitMap.put((Integer) profitList.get(n).get("buyDom"), (Integer) profitList.get(n).get("profit"));
            } else {
                maxProfitMap.put((Integer) profitList.get(n).get("buyDom"), (Integer) profitList.get(n).get("profit"));
            }
        }

        System.out.println(maxProfitMap);

        if (maxProfitMap.size() < 1) {
            return 0;
        }

        //排序
        List<Map.Entry<Integer, Integer>> mappingList = null;
        mappingList = new ArrayList<Map.Entry<Integer, Integer>>(maxProfitMap.entrySet());

        //通过比较器实现比较排序
        mappingList.sort(new Comparator<Map.Entry<Integer, Integer>>() {
            public int compare(Map.Entry<Integer, Integer> mapping1, Map.Entry<Integer, Integer> mapping2) {
                return mapping1.getValue().compareTo(mapping2.getValue());
            }
        });

        if (mappingList.size() == 1) {
            return mappingList.get(mappingList.size() - 1).getValue();
        } else {
            return mappingList.get(mappingList.size() - 1).getValue() + mappingList.get(mappingList.size() - 2).getValue();
        }
    }


    public int maxProfit3V2(int[] x) {

        int fstBuy = Integer.MAX_VALUE, secBuy = Integer.MAX_VALUE;
        int fstSell = 0, secSell = 0;

        System.out.println(Arrays.toString(x));
        for (int x1 : x) {
            fstBuy = Math.min(fstBuy, x1);
            fstSell = Math.max(fstSell,  x1 - fstBuy);

            secBuy = Math.min(secBuy, x1 - fstSell);
            secSell = Math.max(secSell, x1 - secBuy);
            System.out.printf("当前值 %s fstBuy-> %s fstSell -> %s secBuy -> %s secSell -> %s \n", x1, fstBuy, fstSell, secBuy, secSell);
        }


        return secSell;
    }
}