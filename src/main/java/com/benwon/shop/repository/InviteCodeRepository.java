package com.benwon.shop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import com.benwon.shop.entity.InviteCode;

@RestResource
public interface InviteCodeRepository extends JpaRepository<InviteCode, String>{
	
	Optional<InviteCode> findByInviteCode(String inviteCode);

}
