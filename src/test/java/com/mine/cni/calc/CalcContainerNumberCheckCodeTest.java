package com.mine.cni.calc;

import com.mine.cni.utils.CheckCodeUtil;
import org.junit.jupiter.api.Test;
import org.thymeleaf.util.StringUtils;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * @author CaoY
 * @date 2023-05-03 23:52
 * @description 根据前10位字符 计算集装箱编号的校验码值
 * 集装箱编号规则参考连接：https://www.jianshu.com/p/272a44381911
 */
public class CalcContainerNumberCheckCodeTest {

    public static void main(String[] args) {
//        String code = "LCGU8004378";
        String code = "TBJUA5816AU";
        String repairCode = CheckCodeUtil.repair(code);
        int checkNum = CheckCodeUtil.calculate(repairCode);
        String resultCode = repairCode + checkNum;
        System.out.println("result: " + resultCode);
    }

}
