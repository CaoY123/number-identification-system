package com.mine.cni.encryption;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author CaoY
 * @date 2023-05-01 20:46
 * @description 加密 类似于数据库密码 等敏感信息
 */

public class JasyptEncryptionTest {
    @Test
    public void encryptDatabasePassword() {
        String passwordToEncrypt = "你要加密的用于配置的密码";
        String masterPassword = "你自己设置的密钥";

        StringEncryptor encryptor = getStringEncryptor(masterPassword);

        String encryptedPassword = encryptor.encrypt(passwordToEncrypt);

        System.out.println("Encrypted Password: " + encryptedPassword);

        assertNotNull(encryptedPassword);
    }

    private StringEncryptor getStringEncryptor(String masterPassword) {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(masterPassword); // 加密的主密码，也就是密钥，可以设置在环境变量中，供人读取
        config.setAlgorithm("PBEWithMD5AndDES"); // 加密采用的算法
        config.setKeyObtentionIterations("1000"); // 从主密码派生到密钥的迭代过程
        config.setPoolSize("1"); // 加密池的大小。加密池是用来存储加密过程中使用到的各种对象的。
        config.setProviderName("SunJCE"); // 加密算法实现提供者的名称
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator"); // 盐生成器的类名。盐生成器用来生成随机盐值，以增强加密的安全性
        config.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator"); // 指定了向量生成器的类名。向量生成器用来生成随机向量值，以增强加密的安全性。
        config.setStringOutputType("base64"); // 指定了加密后输出结果的类型。此处为输出结果被设置为base64格式。
        encryptor.setConfig(config);

        return encryptor;
    }
}
