package com.snailmann.myid.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;


/**
 * @author liwenjie
 */
@Slf4j
@Configuration
@ComponentScan
public class MyidClientAutoConfiguration {

    @PostConstruct
    public void init() {
        log.info("MyidClientAutoConfiguration Initialization");
    }

}
