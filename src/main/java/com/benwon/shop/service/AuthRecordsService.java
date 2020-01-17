package com.benwon.shop.service;

import com.benwon.shop.entity.AuthRecords;
import com.benwon.shop.repository.AuthRecordsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthRecordsService {

    private final AuthRecordsRepository authRecordsRepository;

    public AuthRecords findByAuthCode(String authcode) {
        AuthRecords authRecords = authRecordsRepository.findByAuthCode(authcode);
        return authRecords;
    }
}
