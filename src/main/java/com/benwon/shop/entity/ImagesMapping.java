/*
 * This file is generated by jOOQ.
 */
package com.benwon.shop.entity;


import java.io.Serializable;
import java.time.LocalDateTime;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * 圖檔使用參考表
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.12.3"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
@Entity
@Table(name = "images_mapping", schema = "shop", uniqueConstraints = {
    @UniqueConstraint(name = "KEY_images_mapping_PRIMARY", columnNames = {"id"})
}, indexes = {
    @Index(name = "image_id", columnList = "image_id ASC"),
    @Index(name = "PRIMARY", unique = true, columnList = "id ASC")
})
public class ImagesMapping implements Serializable {

    private static final long serialVersionUID = -58040751;

    private Long          id;
    private Long          imageId;
    private String        refTable;
    private String        refId;
    private String        createdBy;
    private LocalDateTime createdDate;
    private String        lastModifiedBy;
    private LocalDateTime lastModifiedDate;

    public ImagesMapping() {}

    public ImagesMapping(ImagesMapping value) {
        this.id = value.id;
        this.imageId = value.imageId;
        this.refTable = value.refTable;
        this.refId = value.refId;
        this.createdBy = value.createdBy;
        this.createdDate = value.createdDate;
        this.lastModifiedBy = value.lastModifiedBy;
        this.lastModifiedDate = value.lastModifiedDate;
    }

    public ImagesMapping(
        Long          id,
        Long          imageId,
        String        refTable,
        String        refId,
        String        createdBy,
        LocalDateTime createdDate,
        String        lastModifiedBy,
        LocalDateTime lastModifiedDate
    ) {
        this.id = id;
        this.imageId = imageId;
        this.refTable = refTable;
        this.refId = refId;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.lastModifiedBy = lastModifiedBy;
        this.lastModifiedDate = lastModifiedDate;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, precision = 19)
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "image_id", nullable = false, precision = 19)
    @NotNull
    public Long getImageId() {
        return this.imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    @Column(name = "ref_table", nullable = false, length = 200)
    @NotNull
    @Size(max = 200)
    public String getRefTable() {
        return this.refTable;
    }

    public void setRefTable(String refTable) {
        this.refTable = refTable;
    }

    @Column(name = "ref_id", nullable = false, length = 200)
    @NotNull
    @Size(max = 200)
    public String getRefId() {
        return this.refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    @Column(name = "created_by", nullable = false, length = 60)
    @NotNull
    @Size(max = 60)
    public String getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Column(name = "created_date", nullable = false)
    @NotNull
    public LocalDateTime getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    @Column(name = "last_modified_by", nullable = false, length = 60)
    @NotNull
    @Size(max = 60)
    public String getLastModifiedBy() {
        return this.lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    @Column(name = "last_modified_date", nullable = false)
    @NotNull
    public LocalDateTime getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
