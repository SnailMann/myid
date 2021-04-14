package com.snailmann.myid.server.service.impl;

import com.snailmann.myid.common.exception.TagNoRegisterException;
import com.snailmann.myid.server.entity.IDGen;
import com.snailmann.myid.server.service.IDBFHolderService;
import com.snailmann.myid.server.service.IDGenService;
import com.snailmann.myid.server.service.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author liwenjie
 */
@Slf4j
@Service
public class TagServiceImpl implements TagService {

    private final IDGenService idGenService;

    private final IDBFHolderService idbfHolderService;

    public TagServiceImpl(IDGenService idGenService,
                          IDBFHolderService idbfHolderService) {
        this.idGenService = idGenService;
        this.idbfHolderService = idbfHolderService;
    }

    @Override
    public synchronized void register(String tag, long sid, int step) {
        IDGen gen = idGenService.get(tag);
        if (Objects.isNull(gen)) {
            throw new TagNoRegisterException(String.format("tag no register, tag: %s", tag));
        }
        gen = IDGen.builder()
                .tag(tag)
                .sid(sid)
                .step(step)
                .mid(sid + step)
                .version(1L)
                .build();
        idGenService.save(gen);
        idbfHolderService.refresh();
    }

    @Override
    public void unregister(String tag, String editor) {
        IDGen gen = idGenService.get(tag);
        if (Objects.isNull(gen)) {
            throw new TagNoRegisterException(String.format("tag no register, tag: %s", tag));
        }
        idGenService.delete(tag);
    }

}
