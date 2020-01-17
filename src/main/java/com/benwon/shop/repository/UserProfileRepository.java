package com.benwon.shop.repository;

import com.benwon.shop.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface UserProfileRepository extends JpaRepository<UserProfile, String> {
}
