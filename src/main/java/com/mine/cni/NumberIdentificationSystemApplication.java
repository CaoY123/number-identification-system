package com.mine.cni;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEncryptableProperties
public class NumberIdentificationSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(NumberIdentificationSystemApplication.class, args);
    }

}
