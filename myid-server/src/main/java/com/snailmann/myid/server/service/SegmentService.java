package com.snailmann.myid.server.service;

import com.snailmann.myid.common.buffer.Segment;

/**
 * @author liwenjie
 */
public interface SegmentService {

    Segment next(String tag);

}
