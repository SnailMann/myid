package com.snailmann.myid.server.factory;


import com.snailmann.myid.common.buffer.factory.IDBFFactory;
import com.snailmann.myid.common.model.bo.IDBean;
import com.snailmann.myid.server.entity.IDGen;
import com.snailmann.myid.server.service.IDGenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author liwenjie
 */
@Slf4j
@Service
public class IDBFFactoryImpl implements IDBFFactory {

    private final IDGenService idGenService;

    public IDBFFactoryImpl(IDGenService idGenService) {
        this.idGenService = idGenService;
    }

    @Override
    public IDBean alloc(String tag) {
        return toBean(idGenService.alloc(tag));
    }

    @Override
    public IDBean get(String tag) {
        return toBean(idGenService.get(tag));
    }

    private IDBean toBean(IDGen gen) {
        if (Objects.isNull(gen)) {
            return null;
        }

        return IDBean.builder()
                .id(gen.getId())
                .sid(gen.getSid())
                .mid(gen.getMid())
                .tag(gen.getTag())
                .step(gen.getStep())
                .version(gen.getVersion() + "")
                .build();
    }
}
