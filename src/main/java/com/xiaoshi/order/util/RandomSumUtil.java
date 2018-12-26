package com.xiaoshi.order.util;

import java.util.*;

/**
 * 随机数生成工具类
 */
public class RandomSumUtil {

    /**
     * 随机生成字符 或 者数字
     */
    public static String getRandom() {
        String value = "";
        Random random = new Random();
        int gen = random.nextInt(2);
        String charOrNum = gen % 2 == 0 ? "char" : "num";
        if ("char".equals(charOrNum)) {
            //字符
            int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
            int ascii = random.nextInt(26);
            value += (char) (ascii + temp);
        } else if ("num".equals(charOrNum)) {
            //是数字
            value += String.valueOf(random.nextInt(10));
        }
        return value;
    }

    /**
     * 随机生成字符串（包含字符和数字）
     */
    public static Set<String> getStrAndNum(int length) {
        Set<String> set = new HashSet<>();
        for (int i = 0; i < length; i++) {
            String value = getRandom();
            set.add(value);
        }
        //若生成的字符串没达到指定长度 继续生成
        if (set.size() < length) {
            String value = getRandom();
            set.add(value);
        }
        return set;
    }

    /**
     * 存放在set中的字符组拼接成字符串
     */
    public static String printSet(Set set) { //打印set的方法
        Iterator iterator = set.iterator();
        String value = "";
        while (iterator.hasNext()) {
            //String ele = (String) iterator.next();
            value += (String) iterator.next();
        }
        return value;
    }

    /**
     * 返回生成的随机字符串
     */
    public static String getRandonString(int length) {
        String value = "";
        if (length > 0) {
            //如果返回的字符串小于指定长度 重新生成
            if (value.length() < length) {
                Set<String> store = getStrAndNum(length);
                value = printSet(store);
            }
            return value;
        } else {
            return value;
        }
    }
}
