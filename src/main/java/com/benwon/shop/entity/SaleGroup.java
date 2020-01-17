/*
 * This file is generated by jOOQ.
 */
package com.benwon.shop.entity;


import java.io.Serializable;
import java.time.LocalDateTime;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * 開團目錄頁
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
@Table(name = "sale_group", schema = "shop", uniqueConstraints = {
    @UniqueConstraint(name = "KEY_sale_group_PRIMARY", columnNames = {"id"})
}, indexes = {
    @Index(name = "PRIMARY", unique = true, columnList = "id ASC")
})
public class SaleGroup implements Serializable {

    private static final long serialVersionUID = 710514940;

    private String        id;
    private String        groupName;
    private Long          groupImgId;
    private String        groupImgResourceUrl;
    private LocalDateTime saleStartDate;
    private LocalDateTime saleEndDate;
    private Integer       saleStatus;
    private String        createdBy;
    private LocalDateTime createdDate;
    private String        lastModifiedBy;
    private LocalDateTime lastModifiedDate;

    public SaleGroup() {}

    public SaleGroup(SaleGroup value) {
        this.id = value.id;
        this.groupName = value.groupName;
        this.groupImgId = value.groupImgId;
        this.groupImgResourceUrl = value.groupImgResourceUrl;
        this.saleStartDate = value.saleStartDate;
        this.saleEndDate = value.saleEndDate;
        this.saleStatus = value.saleStatus;
        this.createdBy = value.createdBy;
        this.createdDate = value.createdDate;
        this.lastModifiedBy = value.lastModifiedBy;
        this.lastModifiedDate = value.lastModifiedDate;
    }

    public SaleGroup(
        String        id,
        String        groupName,
        Long          groupImgId,
        String        groupImgResourceUrl,
        LocalDateTime saleStartDate,
        LocalDateTime saleEndDate,
        Integer       saleStatus,
        String        createdBy,
        LocalDateTime createdDate,
        String        lastModifiedBy,
        LocalDateTime lastModifiedDate
    ) {
        this.id = id;
        this.groupName = groupName;
        this.groupImgId = groupImgId;
        this.groupImgResourceUrl = groupImgResourceUrl;
        this.saleStartDate = saleStartDate;
        this.saleEndDate = saleEndDate;
        this.saleStatus = saleStatus;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.lastModifiedBy = lastModifiedBy;
        this.lastModifiedDate = lastModifiedDate;
    }

    @Id
    @Column(name = "id", nullable = false, length = 36)
    @NotNull
    @Size(max = 36)
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "group_name", nullable = false, length = 100)
    @NotNull
    @Size(max = 100)
    public String getGroupName() {
        return this.groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Column(name = "group_img_id", nullable = false, precision = 19)
    @NotNull
    public Long getGroupImgId() {
        return this.groupImgId;
    }

    public void setGroupImgId(Long groupImgId) {
        this.groupImgId = groupImgId;
    }

    @Column(name = "group_img_resource_url", nullable = false, length = 300)
    @NotNull
    @Size(max = 300)
    public String getGroupImgResourceUrl() {
        return this.groupImgResourceUrl;
    }

    public void setGroupImgResourceUrl(String groupImgResourceUrl) {
        this.groupImgResourceUrl = groupImgResourceUrl;
    }

    @Column(name = "sale_start_date", nullable = false)
    @NotNull
    public LocalDateTime getSaleStartDate() {
        return this.saleStartDate;
    }

    public void setSaleStartDate(LocalDateTime saleStartDate) {
        this.saleStartDate = saleStartDate;
    }

    @Column(name = "sale_end_date", nullable = false)
    @NotNull
    public LocalDateTime getSaleEndDate() {
        return this.saleEndDate;
    }

    public void setSaleEndDate(LocalDateTime saleEndDate) {
        this.saleEndDate = saleEndDate;
    }

    @Column(name = "sale_status", nullable = false, precision = 10)
    @NotNull
    public Integer getSaleStatus() {
        return this.saleStatus;
    }

    public void setSaleStatus(Integer saleStatus) {
        this.saleStatus = saleStatus;
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
