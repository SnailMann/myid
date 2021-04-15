package com.snailmann.myid.client.proxy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * @author liwenjie
 */
@Slf4j
public class IDProxy extends BaseProxy {

    private static final String BASE_PATH = "/v1/ids";

    public Long nextId(String tag) {
        return nextId(commonRestTemplate, tag);
    }

    public Long nextId(RestTemplate restTemplate, String tag) {
        String path = "";
        UriComponents uriComponents = UriComponentsBuilder
                .fromHttpUrl(baseUrl + BASE_PATH + path)
                .queryParam("tag", tag)
                .encode()
                .build();
        URI uri = uriComponents.toUri();

        try {
            return restTemplate.getForObject(uri, Long.class);
        } catch (Exception e) {
            log.error("next id error, url: {}", uri, e);
            throw e;
        }

    }

    public List<Long> nextId(int batchSize, String tag) {
        return nextId(commonRestTemplate, batchSize, tag);
    }

    public List<Long> nextId(RestTemplate restTemplate, int batchSize, String tag) {
        String path = "/batch";
        UriComponents uriComponents = UriComponentsBuilder
                .fromHttpUrl(baseUrl + BASE_PATH + path)
                .queryParam("tag", tag)
                .queryParam("batchSize", batchSize)
                .encode()
                .build();
        URI uri = uriComponents.toUri();

        try {
            return restTemplate.exchange(uri, HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<Long>>() {
                    }).getBody();
        } catch (Exception e) {
            log.error("batch next id error, url: {}", uri, e);
            throw e;
        }
    }

}