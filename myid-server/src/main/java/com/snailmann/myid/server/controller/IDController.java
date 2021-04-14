package com.snailmann.myid.server.controller;

import com.snailmann.myid.server.service.IDService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author liwenjie
 */
@Slf4j
@RestController
@RequestMapping("/myid-server/v1/ids")
public class IDController {

    private static final int BATCH_SIZE_MAX = 1000;

    private final IDService idService;

    public IDController(
            IDService idService) {
        this.idService = idService;
    }

    @GetMapping
    public Long next(@RequestParam String tag) {
        return idService.next(tag);
    }

    @GetMapping("/batch")
    public List<Long> batchNext(
            @RequestParam(required = false, defaultValue = "1") int batchSize,
            @RequestParam String tag) {
        Assert.isTrue(StringUtils.isNoneBlank(tag), "tag is blank");
        checkSize(batchSize);

        return idService.next(batchSize, tag);
    }

    private void checkSize(int batchSize) {
        Assert.isTrue(
                batchSize >= 1 && batchSize <= BATCH_SIZE_MAX,
                String.format("batch size over, y: %s, m: %s", batchSize, BATCH_SIZE_MAX));
    }

}
