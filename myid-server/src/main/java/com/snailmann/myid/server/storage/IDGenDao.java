package com.snailmann.myid.server.storage;

import com.snailmann.myid.server.entity.IDGen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * @author liwenjie
 */
@Repository
public interface IDGenDao extends JpaRepository<IDGen, Long> {

    IDGen findByTag(String tag);

    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(value = "update IDGen "
            + "set mId = mId + step, version = version + 1, modification = now() "
            + "where tag = ?1 and version = ?2 ", nativeQuery = true)
    int alloc(String tag, Long version);
}
