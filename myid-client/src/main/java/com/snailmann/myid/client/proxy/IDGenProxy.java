package com.snailmann.myid.client.proxy;

import com.snailmann.myid.common.model.dto.IDGenDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

/**
 * @author liwenjie
 */
@Slf4j
public class IDGenProxy extends BaseProxy {

    private static final String BASE_PATH = "/v1/id-gens";

    public IDGenDTO alloc(String tag) {
        return alloc(commonRestTemplate, tag);
    }

    public IDGenDTO alloc(RestTemplate restTemplate, String tag) {
        String path = "/alloc";
        UriComponents uriComponents = UriComponentsBuilder
                .fromHttpUrl(baseUrl + BASE_PATH + path)
                .queryParam("tag", tag)
                .encode()
                .build();
        URI uri = uriComponents.toUri();

        try {
            return restTemplate.postForObject(uri, null, IDGenDTO.class);
        } catch (Exception e) {
            log.error("idgen alloc error, url: {}", uri, e);
            throw e;
        }

    }

    public IDGenDTO get(String tag) {
        return get(commonRestTemplate, tag);
    }

    public IDGenDTO get(RestTemplate restTemplate, String tag) {
        String path = "";
        UriComponents uriComponents = UriComponentsBuilder
                .fromHttpUrl(baseUrl + BASE_PATH + path)
                .queryParam("tag", tag)
                .encode()
                .build();
        URI uri = uriComponents.toUri();

        try {
            return restTemplate.getForObject(uri, IDGenDTO.class);
        } catch (Exception e) {
            log.error("get idgen error, url: {}", uri, e);
            throw e;
        }

    }
}
