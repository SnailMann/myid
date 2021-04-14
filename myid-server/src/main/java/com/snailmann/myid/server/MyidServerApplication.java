package com.snailmann.myid.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @author liwenjie
 */
@EnableJpaAuditing
@SpringBootApplication
public class MyidServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyidServerApplication.class, args);
    }

}
