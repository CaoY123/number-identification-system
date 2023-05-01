package com.mine.cni.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.TestPropertySource;

/**
 * @author CaoY
 * @date 2023-05-01 23:22
 * @description 加密测试类
 *  @TestPropertySource 是一个JUnit测试用例中用于指定属性源的注解，它允许您在Spring Boot
 *  测试环境中自定义属性值。通过这个注解，您可以在测试中覆盖默认的配置文件中的属性值，
 *  或者为测试提供额外的属性。
 */
@SpringBootTest
@TestPropertySource(properties = {"jasypt.encryptor.password=${MY_SECRET_PASSWORD}"})
public class PasswordEncoderConfigTest {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // 对于一个密码进行 BCryptPasswordEncoder 加密，注意，要运行下面的方法需要在环境变量中
    // 配置 MY_SECRET_PASSWORD 全局变量，或者是直接将 jasypt.encryptor.password 设置为密钥的值
    @Test
    public void testEncoding() {
        String password = "123456";
        String encodedPassword = passwordEncoder.encode(password);
        System.out.println("加密后的密码：" + encodedPassword);
    }

    // 前后密码匹配
    @Test
    public void testMatch() {
        String password = "123456";
        String encodedPassword = passwordEncoder.encode(password);
        System.out.println("加密后的密码：" + encodedPassword);
        boolean matchesResult = passwordEncoder.matches(password, encodedPassword);
        System.out.println("匹配结果：" + matchesResult); // true
    }

    // 前后密码不匹配
    @Test
    public void testNotMatch() {
        String password1 = "123456";
        String encodedPassword = passwordEncoder.encode(password1);
        System.out.println("加密后的密码：" + encodedPassword);
        String password2 = "1234567";
        boolean matchesResult = passwordEncoder.matches(password2, encodedPassword);
        System.out.println("匹配结果：" + matchesResult); // false
    }

}
