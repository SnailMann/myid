package com.snailmann.myid.server.service.impl;

import com.snailmann.myid.common.exception.CasAllocException;
import com.snailmann.myid.server.entity.IDGen;
import com.snailmann.myid.server.service.IDGenService;
import com.snailmann.myid.server.storage.IDGenDao;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author liwenjie
 */
@Slf4j
@Service
public class IDGenServiceImpl implements IDGenService {

    private static final int ALLOC_RETRY = 100;

    private final IDGenDao idGenDao;

    public IDGenServiceImpl(IDGenDao idGenDao) {
        this.idGenDao = idGenDao;
    }

    @Override
    public IDGen get(String tag) {
        return idGenDao.findByTag(tag);
    }

    @Override
    public List<IDGen> list() {
        return idGenDao.findAll();
    }

    @SneakyThrows
    @Override
    public IDGen alloc(String tag) {
        for (int i = 0; i < ALLOC_RETRY; i++) {
            IDGen idGen = idGenDao.findByTag(tag);
            int row = idGenDao.alloc(tag, idGen.getVersion());
            // insert/update success
            if (row >= 1) {
                // re-query is not allowd in high concurrency environment
                return idGen;
            }
        }
        throw new CasAllocException(String.format("alloc segment error, tag: %s", tag));
    }

    @Override
    public IDGen save(IDGen gen) {
        gen.setModification(new Date());
        return idGenDao.save(gen);
    }

    @Override
    public void delete(String tag) {
        IDGen gen = idGenDao.findByTag(tag);
        idGenDao.deleteById(gen.getId());
    }


}
