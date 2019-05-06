package com.wx.algorithm;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommonlyUsed {

    /**
     * 二维map循环
     */
    @Test
    public void commonUsed() {

        //定义二维map
        Map<Integer, Map<String, Integer>> profitMap = new HashMap<>();

        Map<String, Integer> nProfit = new HashMap<>();
        nProfit.put("buyDom", 1);
        nProfit.put("sellDom", 2);
        nProfit.put("profit", 3);

        profitMap.put(1, nProfit);

        //map循环
        for (Map.Entry<Integer, Map<String, Integer>> entry : profitMap.entrySet()) {

            System.out.println("Key->" + entry.getKey() + ",Value->" + entry.getValue());
            System.out.println(entry.getValue().get("buyDom"));
        }


        //定义List

        List<Map> profitList = new ArrayList<>();
        for (int n = 0; n < profitList.size(); n++) {

            System.out.println(profitList.get(n).get("buyDom"));
        }

//        for (int x1 : x) {
//
//        }
    }

}
