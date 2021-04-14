package com.snailmann.myid.server.service;

import com.snailmann.myid.server.entity.IDGen;

import java.util.List;

/**
 * @author liwenjie
 */
public interface IDGenService {

    IDGen get(String tag);

    List<IDGen> list();

    IDGen alloc(String tag);

    IDGen save(IDGen gen);

    void delete(String tag);
}
