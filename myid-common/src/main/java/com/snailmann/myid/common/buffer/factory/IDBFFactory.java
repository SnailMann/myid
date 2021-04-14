package com.snailmann.myid.common.buffer.factory;

import com.snailmann.myid.common.model.bo.IDBean;

/**
 * @author liwenjie
 */
public interface IDBFFactory {

    IDBean alloc(String tag);

    IDBean get(String tag);

}
