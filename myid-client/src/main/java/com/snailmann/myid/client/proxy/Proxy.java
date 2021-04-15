package com.snailmann.myid.client.proxy;

import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

/**
 * @author liwenjie
 */
public abstract class Proxy {

    protected int connectionTimeOut = 10000;

    protected int readTimeOut = 5000;

    protected int maxConnTotal = 200;

    protected int maxConnPerRoute = 200;

    protected String baseUrl;

    protected final RestTemplate commonRestTemplate;

    public Proxy() {
        // default template
        // if you want to change the parameter, call setCustomRequestFactory
        this.commonRestTemplate = new RestTemplateBuilder()
                .setConnectTimeout(Duration.ofMillis(connectionTimeOut))
                .setReadTimeout(Duration.ofMillis(readTimeOut))
                .requestFactory(() -> new HttpComponentsClientHttpRequestFactory(
                        HttpClientBuilder.create()
                                .setMaxConnTotal(maxConnTotal)
                                .setMaxConnPerRoute(maxConnPerRoute)
                                .build()
                )).build();
    }

    /**
     * set custom request factory
     *
     * @param connectionTimeOut connectionTimeOut
     * @param readTimeOut       readTimeOut
     * @param maxConnTotal      maxConnTotal
     * @param maxConnPerRoute   maxConnPerRoute
     */
    protected void setCustomRequestFactory(
            int connectionTimeOut, int readTimeOut, int maxConnTotal,
            int maxConnPerRoute) {

        HttpComponentsClientHttpRequestFactory httpRequestFactory
                = new HttpComponentsClientHttpRequestFactory();
        httpRequestFactory.setReadTimeout(readTimeOut);
        httpRequestFactory.setConnectTimeout(connectionTimeOut);
        httpRequestFactory.setHttpClient(HttpClientBuilder.create()
                .setMaxConnTotal(maxConnTotal)
                .setMaxConnPerRoute(maxConnPerRoute)
                .build());
        this.commonRestTemplate.setRequestFactory(httpRequestFactory);
    }

}
