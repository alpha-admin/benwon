/*
 * This file is generated by jOOQ.
 */
package com.benwon.shop.entity;


import javax.annotation.Generated;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * 主帳號
 */
@Generated(
        value = {
                "http://www.jooq.org",
                "jOOQ version:3.11.11"
        },
        comments = "This class is generated by jOOQ"
)
@SuppressWarnings({"all", "unchecked", "rawtypes"})
@Entity
@Table(name = "user_accounts", indexes = {
        @Index(name = "PRIMARY", unique = true, columnList = "id ASC")
})
public class UserAccounts implements Serializable {

    private static final long serialVersionUID = 1417054538;

    private String id;
    private String userName;
    private Boolean isEnabled;
    private Boolean isExpired;
    private Boolean isLocked;
    private Boolean credentialsExpired;
    private String createdBy;
    private LocalDateTime createdDate;
    private String lastModifiedBy;
    private LocalDateTime lastModifiedDate;

    public UserAccounts() {
    }

    public UserAccounts(UserAccounts value) {
        this.id = value.id;
        this.userName = value.userName;
        this.isEnabled = value.isEnabled;
        this.isExpired = value.isExpired;
        this.isLocked = value.isLocked;
        this.credentialsExpired = value.credentialsExpired;
        this.createdBy = value.createdBy;
        this.createdDate = value.createdDate;
        this.lastModifiedBy = value.lastModifiedBy;
        this.lastModifiedDate = value.lastModifiedDate;
    }

    public UserAccounts(
            String id,
            String userName,
            Boolean isEnabled,
            Boolean isExpired,
            Boolean isLocked,
            Boolean credentialsExpired,
            String createdBy,
            LocalDateTime createdDate,
            String lastModifiedBy,
            LocalDateTime lastModifiedDate
    ) {
        this.id = id;
        this.userName = userName;
        this.isEnabled = isEnabled;
        this.isExpired = isExpired;
        this.isLocked = isLocked;
        this.credentialsExpired = credentialsExpired;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.lastModifiedBy = lastModifiedBy;
        this.lastModifiedDate = lastModifiedDate;
    }

    @Id
    @Column(name = "id", unique = true, nullable = false, length = 60)
    @NotNull
    @Size(max = 60)
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "user_name", nullable = false, length = 60)
    @NotNull
    @Size(max = 60)
    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Column(name = "is_enabled", nullable = false, precision = 1)
    @NotNull
    public Boolean getIsEnabled() {
        return this.isEnabled;
    }

    public void setIsEnabled(Boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    @Column(name = "is_expired", nullable = false, precision = 1)
    @NotNull
    public Boolean getIsExpired() {
        return this.isExpired;
    }

    public void setIsExpired(Boolean isExpired) {
        this.isExpired = isExpired;
    }

    @Column(name = "is_locked", nullable = false, precision = 1)
    @NotNull
    public Boolean getIsLocked() {
        return this.isLocked;
    }

    public void setIsLocked(Boolean isLocked) {
        this.isLocked = isLocked;
    }

    @Column(name = "credentials_expired", nullable = false, precision = 1)
    @NotNull
    public Boolean getCredentialsExpired() {
        return this.credentialsExpired;
    }

    public void setCredentialsExpired(Boolean credentialsExpired) {
        this.credentialsExpired = credentialsExpired;
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
