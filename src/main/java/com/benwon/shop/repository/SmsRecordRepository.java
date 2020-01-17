package com.benwon.shop.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.benwon.shop.entity.SmsRecord;

@RepositoryRestResource(exported = false)
public interface SmsRecordRepository extends JpaRepository<SmsRecord, String> {
	
	long countByCreatedDateAfter(LocalDateTime beforeTime);
	
	Optional<SmsRecord> findTopByPhoneNumberOrderByCreatedDateDesc(String phoneNumber);

}
