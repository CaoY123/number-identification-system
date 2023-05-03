package com.mine.cni.utils;

import org.thymeleaf.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author CaoY
 * @date 2023-05-04 0:44
 * @description 集装箱编号最后一位校验码计算，输入至少前10位的识别结果，用于计算最后一位校验码
 * 规则参考链接：https://www.jianshu.com/p/272a44381911
 */
public class CheckCodeUtil {
    private static Map<Character, Integer> map = new HashMap<>();
    static {
        for (int i = 0; i < 10; i++) {
            map.put((char) (i - 0 + 48), i);
        }

        map.put('A', 10);
        for (int i = 1; i < 26; i++) {
            char key = (char) (i + 65);
            char preKey = (char) (key - 1);
            int value = map.get(preKey) + 1;
            if (value % 11 == 0) {
                value++;
            }
            map.put(key, value);
        }
    }

    public static int calculate(String code) {
        if (StringUtils.isEmpty(code)) {
            return -1;
        }

        int sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += map.get(code.charAt(i)) * Math.pow(2, i);
        }

        return sum % 11;
    }
}
