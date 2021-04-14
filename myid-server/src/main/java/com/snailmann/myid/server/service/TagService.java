package com.snailmann.myid.server.service;

/**
 * @author liwenjie
 */
public interface TagService {

    void register(String tag, long sid, int step);

    void unregister(String tag, String editor);
}
