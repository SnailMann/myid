package com.snailmann.myid.server.service.impl;

import com.snailmann.myid.common.buffer.IDBuffer;
import com.snailmann.myid.common.model.bo.IDResult;
import com.snailmann.myid.server.service.IDBFHolderService;
import com.snailmann.myid.server.service.IDService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liwenjie
 */
@Slf4j
@Service
public class IDServiceImpl implements IDService {

    private final IDBFHolderService idbfHolderService;

    public IDServiceImpl(IDBFHolderService idbfHolderService) {
        this.idbfHolderService = idbfHolderService;
    }

    @Override
    public Long next(String tag) {
        IDBuffer buffer = idbfHolderService.getBuffer(tag);
        IDResult res = buffer.nextId();
        return res.getId();
    }

    @Override
    public List<Long> next(int batchSize, String tag) {
        List<Long> ids = new ArrayList<>();
        for (int i = 0; i < batchSize; i++) {
            ids.add(next(tag));
        }
        return ids;
    }
}
