package com.snailmann.myid.client.config;

import com.snailmann.myid.client.proxy.IDGenProxy;
import com.snailmann.myid.client.proxy.IDProxy;
import com.snailmann.myid.client.template.impl.RpcIDTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liwenjie
 */
@Slf4j
@Configuration
public class IDTemplateConfiguration {

    @Bean
    @ConditionalOnMissingBean(name = "idGenProxy")
    public IDGenProxy idGenProxy() {
        return new IDGenProxy();
    }

    @Bean
    @ConditionalOnMissingBean(name = "idProxy")
    public IDProxy idProxy() {
        return new IDProxy();
    }

    @Bean
    @ConditionalOnMissingBean(RpcIDTemplate.class)
    public RpcIDTemplate rpcIDTemplate(IDProxy idProxy) {
        return new RpcIDTemplate(idProxy);
    }
}
