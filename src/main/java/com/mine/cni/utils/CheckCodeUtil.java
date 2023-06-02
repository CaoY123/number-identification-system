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

    public static String repair(String code) {
        if (code == null || code.length() == 0) {
            throw new RuntimeException("传入的码值为空");
        }
        if (code.length() < 10 || code.length() > 11) {
            throw new RuntimeException("传入的码值长度出错，不为10或11");
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 4; i++) {
            char ch = code.charAt(i);
            if (ch < 'A' || ch > 'Z') {
                repairAlphabet(sb, ch);
            } else {
                sb.append(ch);
            }
        }

        for (int i = 4; i < 10; i++) {
            char ch = code.charAt(i);
            if (ch < '0' || ch > '9') {
                repairNumber(sb, ch);
            } else {
                sb.append(ch);
            }
        }

        return new String(sb);
    }

    private static void repairAlphabet(StringBuffer sb, char ch){
        char cEnd = ' ';
        switch (ch) {
            case '0':
                cEnd = 'O';
                break;
            case '1':
                cEnd = 'I';
                break;
            case '2':
                cEnd = 'Z';
                break;
            case '4':
                cEnd = 'A';
                break;
            case '5':
                cEnd = 'S';
                break;
            case '7':
                cEnd = 'T';
                break;
            case '8':
                cEnd = 'B';
                break;
        }
        sb.append(cEnd);
    }

    private static void repairNumber(StringBuffer sb, char ch) {
        char cEnd = ' ';
        switch (ch) {
            case 'A':
                cEnd = '4';
                break;
            case 'B':
                cEnd = '8';
                break;
            case 'D':
                cEnd = '0';
                break;
            case 'I':
                cEnd = '1';
                break;
            case 'O':
                cEnd = '0';
                break;
            case 'S':
                cEnd = '5';
                break;
            case 'T':
                cEnd = '7';
                break;
            case 'Z':
                cEnd = '2';
                break;
        }
        sb.append(cEnd);
    }

    public static int calculate(String code) {
        if (StringUtils.isEmpty(code)) {
            return -1;
        }

        int sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += map.get(code.charAt(i)) * Math.pow(2, i);
        }

        return sum % 11 % 10;
    }
}
