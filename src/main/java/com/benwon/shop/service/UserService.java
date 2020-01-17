package com.benwon.shop.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.benwon.shop.entity.UserAccounts;
import com.benwon.shop.entity.UserLine;
import com.benwon.shop.entity.UserRoleMapping;
import com.benwon.shop.repository.UserAccountsRepository;
import com.benwon.shop.repository.UserLineRepository;
import com.benwon.shop.repository.UserRoleMappingRepository;

import lombok.RequiredArgsConstructor;
import xyz.downgoon.snowflake.Snowflake;

@Service
@RequiredArgsConstructor
public class UserService {
    private final Snowflake snowflake;

    @Value("${benwon.defaultRoleId}")
    private String benwon_defaultRoleId;

    private final UserAccountsRepository userAccountsRepository;
    private final UserLineRepository userLineRepository;
    private final UserRoleMappingRepository userRoleMappingRepository;

    public UserAccounts createLineUser(String lineId) {
        String uid = String.valueOf(snowflake.nextId());
        LocalDateTime now = LocalDateTime.now();
        UserAccounts userAccounts = new UserAccounts();
        userAccounts.setId(uid);
        userAccounts.setUserName("line_" + uid);
        userAccounts.setIsEnabled(false);
        userAccounts.setIsExpired(false);
        userAccounts.setIsLocked(false);
        userAccounts.setCredentialsExpired(false);
        userAccounts.setCreatedBy("");
        userAccounts.setCreatedDate(now);
        userAccounts.setLastModifiedBy("");
        userAccounts.setLastModifiedDate(now);
        userAccounts = userAccountsRepository.save(userAccounts);
        UserLine userLine = new UserLine();
        userLine.setLineId(lineId);
        userLine.setUserId(uid);
        userLine.setCreatedBy("");
        userLine.setCreatedDate(now);
        userLine.setLastModifiedBy("");
        userLine.setLastModifiedDate(now);
        userLine = userLineRepository.save(userLine);
        UserRoleMapping userRoleMapping = new UserRoleMapping();
        userRoleMapping.setUserId(uid);
        userRoleMapping.setRoleId(benwon_defaultRoleId);
        userRoleMapping.setCreatedBy("");
        userRoleMapping.setCreatedDate(now);
        userRoleMapping.setLastModifiedBy("");
        userRoleMapping.setLastModifiedDate(now);
        userRoleMapping = userRoleMappingRepository.save(userRoleMapping);
        return userAccounts;
    }

    public Optional<UserAccounts> findById(String id){
        return userAccountsRepository.findById(id);
    }
}
