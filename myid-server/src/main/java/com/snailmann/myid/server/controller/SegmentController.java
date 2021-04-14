package com.snailmann.myid.server.controller;

import com.snailmann.myid.common.buffer.Segment;
import com.snailmann.myid.server.service.SegmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liwenjie
 */
@Slf4j
@RestController
@RequestMapping("/ms-id-server/v1/segments")
public class SegmentController {

    private final SegmentService segmentService;

    public SegmentController(SegmentService segmentService) {
        this.segmentService = segmentService;
    }

    @GetMapping
    public Segment segment(@RequestParam String tag) {
        return segmentService.next(tag);
    }

}
