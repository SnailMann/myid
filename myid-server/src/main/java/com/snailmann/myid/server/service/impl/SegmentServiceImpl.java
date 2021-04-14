package com.snailmann.myid.server.service.impl;

import com.snailmann.myid.common.buffer.IDBuffer;
import com.snailmann.myid.common.buffer.Segment;
import com.snailmann.myid.server.service.IDBFHolderService;
import com.snailmann.myid.server.service.SegmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author liwenjie
 */
@Slf4j
@Service
public class SegmentServiceImpl implements SegmentService {

    private final IDBFHolderService idbfHolderService;

    public SegmentServiceImpl(IDBFHolderService idbfHolderService) {
        this.idbfHolderService = idbfHolderService;
    }

    @Override
    public Segment next(String tag) {
        IDBuffer buffer = idbfHolderService.getBuffer(tag);
        return buffer.avaliableSegment();
    }
}
