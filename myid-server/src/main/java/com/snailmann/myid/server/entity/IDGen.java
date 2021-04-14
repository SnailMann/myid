package com.snailmann.myid.server.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * @author liwenjie
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "IDGen", indexes = {@Index(name = "tag", columnList = "tag", unique = true)})
@EntityListeners(AuditingEntityListener.class)
public class IDGen {

    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 业务组
     */
    @Column(columnDefinition = "VARCHAR(64) NOT NULL DEFAULT 'GLOBAL'")
    private String tag;

    /**
     * 步长
     */
    @Column(columnDefinition = "INT(11) NOT NULL DEFAULT '100'")
    private Integer step;

    /**
     * 初始 ID，不会再变化
     */
    @Column(columnDefinition = "BIGINT(20) NOT NULL DEFAULT '1'")
    private Long sid;

    /**
     * 当前可分配的极限 ID（不包含）
     */
    @Column(columnDefinition = "BIGINT(20) NOT NULL DEFAULT '1'")
    private Long mid;

    /**
     * 乐观锁版本
     */
    @Column(name = "`version`", columnDefinition = "BIGINT(20) NOT NULL DEFAULT '1'")
    private Long version;

    /**
     * 编辑者
     */
    @Column(columnDefinition = "VARCHAR(64) NOT NULL DEFAULT 'SYSTEM'")
    private String editor;

    /**
     * 记录创建时间
     */
    @CreatedDate
    private Date creation;

    /**
     * 记录修改时间
     */
    @LastModifiedDate
    private Date modification;

}

