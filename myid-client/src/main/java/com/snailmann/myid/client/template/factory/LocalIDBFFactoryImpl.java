package com.snailmann.myid.client.template.factory;

import com.snailmann.myid.client.proxy.IDGenProxy;
import com.snailmann.myid.common.buffer.factory.IDBFFactory;
import com.snailmann.myid.common.model.bo.IDBean;
import com.snailmann.myid.common.model.dto.IDGenDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author liwenjie
 */
@Slf4j
@Component
public class LocalIDBFFactoryImpl implements IDBFFactory {

    private final IDGenProxy idGenProxy;

    public LocalIDBFFactoryImpl(IDGenProxy idGenProxy) {
        this.idGenProxy = idGenProxy;
    }

    @Override
    public IDBean alloc(String tag) {
        return toBean(idGenProxy.alloc(tag));
    }

    @Override
    public IDBean get(String tag) {
        throw new UnsupportedOperationException("unsupport operation");
    }

    private IDBean toBean(IDGenDTO gen) {
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
