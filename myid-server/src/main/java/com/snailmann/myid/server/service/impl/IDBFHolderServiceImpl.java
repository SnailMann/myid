package com.snailmann.myid.server.service.impl;

import com.snailmann.myid.common.buffer.IDBuffer;
import com.snailmann.myid.common.buffer.factory.IDBFFactory;
import com.snailmann.myid.common.exception.BufferNoReadyException;
import com.snailmann.myid.common.exception.TagNoRegisterException;
import com.snailmann.myid.server.entity.IDGen;
import com.snailmann.myid.server.service.IDBFHolderService;
import com.snailmann.myid.server.service.IDGenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * buffer holder
 *
 * @author liwenjie
 */
@Slf4j
@Lazy
@Service
public class IDBFHolderServiceImpl implements IDBFHolderService {

    private static ConcurrentHashMap<String, IDBuffer> bfHolder = new ConcurrentHashMap<>();

    private final IDGenService idGenService;

    private final IDBFFactory idbfFactory;

    public IDBFHolderServiceImpl(IDGenService idGenService,
                                 IDBFFactory idbfFactory) {
        this.idGenService = idGenService;
        this.idbfFactory = idbfFactory;
    }

    /**
     * it will be execute when the server is started or the new tag is registered
     */
    @Override
    public void refresh() {
        log.info("refresh buffer holder");
        List<IDGen> gens = idGenService.list();
        List<String> tags = gens.stream().map(IDGen::getTag)
                .filter(Objects::nonNull).distinct()
                .collect(Collectors.toList());

        // fill new tags
        List<String> newTags = tags.stream()
                .filter(s -> !bfHolder.containsKey(s))
                .collect(Collectors.toList());
        log.info("fill new tags to holder: {}", newTags);
        newTags.forEach(tag -> EXECUTOR.execute(() -> this.fillHolder(tag)));

        // remove tags was delete
        List<String> removeTags = bfHolder.keySet().stream()
                .filter(s -> !tags.contains(s))
                .collect(Collectors.toList());
        log.info("remove tags from holder: {}", removeTags);
        removeTags.forEach(s -> bfHolder.remove(s));
    }

    private void fillHolder(String tag) {
        IDBuffer buffer = bfHolder.get(tag);

        // avoid load buffer again
        boolean expression = Objects.isNull(buffer) || !buffer.isReady();
        if (expression) {
            buffer = new IDBuffer(tag, idbfFactory);
            bfHolder.put(tag, buffer);
            log.info("[{}] tag refresh buffer success", tag);
        }
    }

    @Override
    public IDBuffer getBuffer(String tag) {
        IDBuffer buffer = bfHolder.get(tag);

        // tag no register or buffer no ready
        if (Objects.isNull(buffer)) {
            IDGen gen = idGenService.get(tag);
            if (Objects.isNull(gen)) {
                throw new TagNoRegisterException(String.format("tag [%s] no register", tag));
            } else {
                throw new BufferNoReadyException(String.format("buffer empty, tag: %s", tag));
            }
        }

        // buffer no ready
        if (!buffer.isReady()) {
            throw new BufferNoReadyException(String.format("buffer no ready, tag: %s", tag));
        }
        return buffer;
    }


}
